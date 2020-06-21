package Dominio;

import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

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
	private Client cliente;
	
	public ServicioMercadoLibre(){
		this.cliente = new Client();
	}
	
	@Override
	public List<String> getPaises(){
		ClientResponse respuestaPaises =	this.recursoMercadoLibre()
	  			.path(this.recursoPais(null))
	  			.accept(MediaType.APPLICATION_JSON) 
	  			.get(ClientResponse.class);

		if(!(respuestaPaises.getStatus() == 200))
		throw new RuntimeException("Failed : HTTP error code : " + respuestaPaises.getStatus());
		
		JsonReader lectorPaises = Json.createReader(respuestaPaises.getEntityInputStream());
		
		JsonArray paisesJson = lectorPaises.readArray();

		return paisesJson.stream().map(p -> 
				p.asJsonObject().getString("name"))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<String> getProvincias(String pais){		
		
		JsonObject recursoPais = this.getJsonPais(pais);
		
		JsonArray provinciasJson = recursoPais.getJsonArray("states");
		
		return provinciasJson.stream().map(p -> 
				p.asJsonObject().getString("name"))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<String> getCiudades(String pais, String provincia){
		
		JsonObject recursoProvincia = this.getJsonProvincia(pais, provincia);
		
		JsonArray ciudadesJson = recursoProvincia.getJsonArray("cities");
		
		return ciudadesJson.stream().map(c -> 
				c.asJsonObject().getString("name"))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<String> getMonedas(){
		ClientResponse respuestaMonedas =	this.recursoMercadoLibre()
	  			.path(this.recursoMoneda(null))
	  			.accept(MediaType.APPLICATION_JSON) 
	  			.get(ClientResponse.class);
		
		JsonReader lectorMonedas = Json.createReader(respuestaMonedas.getEntityInputStream());
		
		JsonArray monedasJson = lectorMonedas.readArray();

		return monedasJson.stream().map(p -> 
				p.asJsonObject().getString("description"))
				.collect(Collectors.toList());	
	}
	
	@Override
	public Pais getPais(String pais) {
		
		JsonObject paisJson = this.getJsonPais(pais);
		
		JsonObject geoInfo = paisJson.getJsonObject("geo_information").getJsonObject("location");
	
		Double latitud = geoInfo.getJsonNumber("latitude").doubleValue();
		Double longitud = geoInfo.getJsonNumber("longitude").doubleValue();
		
		return new Pais(
				paisJson.getString("name"),
				paisJson.getString("locale"), 
				paisJson.getString("decimal_separator"),
				paisJson.getString("thousands_separator"), 
				paisJson.getString("time_zone"),
				paisJson.getString("currency_id"),
				new InformacionGeografica(latitud,longitud));
	}
	
	@Override
	public Provincia getProvincia(String pais, String provincia) {
		JsonObject provinciaJson = this.getJsonProvincia(pais, provincia);
		JsonObject geoInfo = provinciaJson.getJsonObject("geo_information").getJsonObject("location");
		
		Double latitud = geoInfo.getJsonNumber("latitude").doubleValue();
		Double longitud = geoInfo.getJsonNumber("longitude").doubleValue();
		
		return new Provincia(	
				provinciaJson.getString("name"), 
				provinciaJson.getString("time_zone"),		
				new InformacionGeografica(latitud, longitud));
	}
	
	@Override
	public Ciudad getCiudad(String pais, String provincia, String ciudad) {
		JsonObject ciudadJson = this.getJsonCiudad(pais, provincia, ciudad);
		JsonObject geoInfo = ciudadJson.getJsonObject("geo_information").getJsonObject("location");
		
		Double latitud = geoInfo.getJsonNumber("latitude").doubleValue();
		Double longitud = geoInfo.getJsonNumber("longitude").doubleValue();
		
		return new Ciudad(	
				ciudadJson.getString("name"), 
				new InformacionGeografica(latitud, longitud));
	}

	@Override
	public Moneda getMonedaLocal(String pais) {
		JsonObject monedaJson = this.getJsonMoneda(pais);

		return new Moneda(	
				monedaJson.getString("id"),
				monedaJson.getString("symbol"),
				monedaJson.getString("description"), 
				monedaJson.getInt("decimal_places"));
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
	
	private JsonObject getJson(String recurso) {
		ClientResponse response = this.recursoMercadoLibre()
	  			.path(recurso)
	  			.accept(MediaType.APPLICATION_JSON) 
	  			.get(ClientResponse.class);
		
		JsonReader lector =  Json.createReader(response.getEntityInputStream());
		
		return lector.readObject();
	}
	
	private JsonObject getJsonPais(String pais) {
		ClientResponse respuestaPaises =	this.recursoMercadoLibre()
	  			.path(this.recursoPais(null))
	  			.accept(MediaType.APPLICATION_JSON) 
	  			.get(ClientResponse.class);

		if(!(respuestaPaises.getStatus() == 200))
		throw new RuntimeException("Failed : HTTP error code : " + respuestaPaises.getStatus());
		
		JsonReader lectorPaises = Json.createReader(respuestaPaises.getEntityInputStream());
		
		JsonArray paisesJson = lectorPaises.readArray();
		
		String paisId = paisesJson.stream().filter( p -> 
					p.asJsonObject().getString("name").equals(pais)
				).findFirst().get().asJsonObject().getString("id");
		
		return this.getJson(this.recursoPais(paisId));
	}
	
	private JsonObject getJsonProvincia(String pais, String provincia) {
		JsonObject paisJson = this.getJsonPais(pais);
		
		JsonArray provinciasJson = paisJson.getJsonArray("states");
		
		String provinciaId= provinciasJson.stream().filter( p -> 
					p.asJsonObject().getString("name").equals(provincia)
				).findFirst().get().asJsonObject().getString("id");
			
		return this.getJson(this.recursoProvincia(provinciaId));
	}

	
	private JsonObject getJsonCiudad(String pais, String provincia, String ciudad) {
		
		JsonObject provinciaJson = this.getJsonProvincia(pais, provincia);
		
		JsonArray ciudadesJson = provinciaJson.getJsonArray("cities");
		
		String ciudadId = ciudadesJson.stream().filter( p -> 
					p.asJsonObject().getString("name").equals(ciudad)
				).findFirst().get().asJsonObject().getString("id");;
		
		return this.getJson(this.recursoCiudad(ciudadId));
	}

	
	private JsonObject getJsonMoneda(String pais) {
		
		JsonObject paisJson = this.getJsonPais(pais);
		
		String monedaId = paisJson.getString("currency_id");
		
		return this.getJson(this.recursoMoneda(monedaId));
	}


}
