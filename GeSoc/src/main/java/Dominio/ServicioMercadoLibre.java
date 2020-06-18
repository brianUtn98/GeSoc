package Dominio;

import javax.ws.rs.core.MediaType;
import javax.json.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.InformacionGeografica;
import Dominio.Ubicacion.Moneda;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

public class ServicioMercadoLibre implements ServicioInformacionUbicaciones{
	private Client cliente;//Combiene tenerlo aca o crearlo cada vez que se hace un request
	private String idPais;
	private String idProvincia;
	private String idCiudad;
	private String idMoneda;

	
	public ServicioMercadoLibre(String pais, String provincia, String ciudad){
		this.cliente = new Client();
		ClientResponse respuestaPaises =	this.recursoMercadoLibre()
								  			.path(this.recursoPais(null))
								  			.accept(MediaType.APPLICATION_JSON) 
								  			.get(ClientResponse.class);
		
		if(!(respuestaPaises.getStatus() == 200))
            throw new RuntimeException("Failed : HTTP error code : " + respuestaPaises.getStatus());
		
		JsonReader lectorPaises = Json.createReader(respuestaPaises.getEntityInputStream());
		
		JsonArray paisesJson = lectorPaises.readArray();
			
		try {
			JsonValue infoPais = paisesJson.stream().filter(p -> {
					JsonObject country = p.asJsonObject();
					return country.getString("name").equals(pais);
			}).findFirst().get();
			
			this.idPais = infoPais.asJsonObject().getString("id");
			this.idMoneda = infoPais.asJsonObject().getString("currency_id");
			
			JsonObject recursoPais = getJson(this.recursoPais(this.idPais));
				
			JsonArray provinciasJson = recursoPais.getJsonArray("states");
			
			JsonValue infoProvincia = provinciasJson.stream().filter(p ->{
					JsonObject state = p.asJsonObject();
					return state.getString("name").equals(provincia);
			}).findFirst().get();
			
			this.idProvincia = infoProvincia.asJsonObject().getString("id");
			
			JsonObject recursoProvincia = getJson(this.recursoProvincia(this.idProvincia));
			
			JsonArray ciudadesJson = recursoProvincia.getJsonArray("cities");
			
			JsonValue infoCiudad= ciudadesJson.stream().filter(p ->{
				JsonObject state = p.asJsonObject();
				return state.getString("name").equals(ciudad);
			}).findFirst().get();
			
			this.idCiudad = infoCiudad.asJsonObject().getString("id");
		}
		catch(Exception e) {
			//Pensar bien las excepctions y como hacer para que tire distintas segun el error
			throw new RuntimeException("Datos mal ingresados");
		}		
	}
	
	@Override
	public Pais getPais() {
		
		JsonObject pais = this.getJson(this.recursoPais(this.idPais));
		
		JsonObject geoInfo = pais.getJsonObject("geo_information").getJsonObject("location");
	
		Double latitud = geoInfo.getJsonNumber("latitude").doubleValue();
		Double longitud = geoInfo.getJsonNumber("longitude").doubleValue();
		
		return new Pais(pais.getString("name"),
						pais.getString("locale"), 
						pais.getString("decimal_separator"),
						pais.getString("thousands_separator"), 
						pais.getString("time_zone"),
						pais.getString("currency_id"),
						new InformacionGeografica(latitud,longitud));
	}
	
	@Override
	public Provincia getProvincia() {
		JsonObject provincia = this.getJson(this.recursoProvincia(this.idProvincia));
		JsonObject geoInfo = provincia.getJsonObject("geo_information").getJsonObject("location");
		
		Double latitud = geoInfo.getJsonNumber("latitude").doubleValue();
		Double longitud = geoInfo.getJsonNumber("longitude").doubleValue();
		
		return new Provincia(	provincia.getString("name"), 
								provincia.getString("time_zone"),
		
								new InformacionGeografica(latitud, longitud));
	}
	
	@Override
	public Ciudad getCiudad() {
		JsonObject ciudad = this.getJson(this.recursoCiudad(idCiudad));
		JsonObject geoInfo = ciudad.getJsonObject("geo_information").getJsonObject("location");
		
		Double latitud = geoInfo.getJsonNumber("latitude").doubleValue();
		Double longitud = geoInfo.getJsonNumber("longitude").doubleValue();
		
		return new Ciudad(	ciudad.getString("name"), 
							new InformacionGeografica(latitud, longitud));
	}

	@Override
	public Moneda getMonedaLocal() {
		JsonObject moneda = this.getJson(this.recursoMoneda(idMoneda));

		return new Moneda(	moneda.getString("id"),
							moneda.getString("symbol"),
							moneda.getString("description"), 
							moneda.getInt("decimal_places"));
	}
	
	private JsonObject getJson(String recurso) {
		ClientResponse response = this.recursoMercadoLibre().
		path(recurso).
		accept(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);

		if(!(response.getStatus() == 200))
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		
		JsonReader reader = Json.createReader(response.getEntityInputStream());
		return reader.readObject();
	}
	
	private WebResource recursoMercadoLibre() {
		return this.cliente.resource("https://api.mercadolibre.com/");
	}
	
	private String recursoPais(String id) {
		String recurso = new String("classified_locations/countries/");
		if(id!=null && !id.isEmpty())
			recurso = recurso.concat(id);
		return recurso;
	}
	
	private String recursoProvincia(String id) {
		String recurso = new String("classified_locations/states/");
		//if(id!=null && !id.isEmpty())
			
		recurso = recurso.concat(id);	
		return recurso;
	}
	
	private String recursoCiudad(String id) {
		String recurso = new String("classified_locations/cities/");
		//if(id!=null && !id.isEmpty())
		recurso = recurso.concat(id);
		return recurso;
	}
	
	private String recursoMoneda(String id) {
		String recurso = new String("currencies/");
		if(id!=null && !id.isEmpty())
			recurso = recurso.concat(id);	
		return recurso;
	}


}
