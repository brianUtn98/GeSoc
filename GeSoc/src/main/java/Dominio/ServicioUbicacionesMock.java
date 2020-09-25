package Dominio;

import java.util.ArrayList;
import java.util.List;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Moneda;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

public class ServicioUbicacionesMock implements ServicioInformacionUbicaciones{

	@Override
	public List<String> getPaises() {
		List<String> paises  = new ArrayList<String>();		
		
		paises.add("Argentina");
		paises.add("Chile");
		paises.add("Uruguay");
		
		return paises;
	}

	@Override
	public List<String> getProvincias(String pais) {
		
		List<String> provincias  = new ArrayList<String>();		
		
		switch(pais) {
			case "Argentina" : {
				provincias.add("Capital Federal");
				provincias.add("Catamarca");
				provincias.add("Corrientes");
				provincias.add("Santa Fe");
				break;
			}
			case "Chile" : {
				provincias.add("Atacama");
				provincias.add("Coquimbo");
				provincias.add("Magallanes");
				provincias.add("Maule");
				break;
			}
			case "Uruguay" : {
				provincias.add("Canelones");
				provincias.add("Colonia");
				provincias.add("Maldonado");
				provincias.add("Montevideo");
				break;
			}
		}
		
		return provincias;
	}

	@Override
	public List<String> getCiudades(String pais, String provincia) {
		
		List<String> ciudades  = new ArrayList<String>();		
		
		switch(pais) {
			case "Argentina" : {
				switch (provincia) {
					case "Capital Federal":{
						ciudades.add("Capital Federal");
						break;
					}
					case "Catamarca" : {
						ciudades.add("Ancasti");
						ciudades.add("El Alto");
						ciudades.add("La Paz");
						ciudades.add("Tinogasta");
						break;
					}
					case "Corrientes" : {
						ciudades.add("Esquina");
						ciudades.add("General Paz");
						ciudades.add("Lavalle");
						ciudades.add("Mercedes");
						break;
					}
					case "Santa Fe" : {
						ciudades.add("Caseros");
						ciudades.add("Garay");
						ciudades.add("Rosario");
						ciudades.add("Vera");
						break;
					}
				}
				break;
			}
			case "Chile" : {
				switch (provincia) {
					case "Atacama":{
						ciudades.add("Caldera");
						ciudades.add("Freirina");
						ciudades.add("Huasco");
						ciudades.add("Vallenar");
						break;
					}
					case "Coquimbo" : {
						ciudades.add("Canela");
						ciudades.add("Coquimbo");
						ciudades.add("Ovalle");
						ciudades.add("Paiguano");
						break;
					}
					case "Magallanes" : {
						ciudades.add("Laguna Blanca");
						ciudades.add("Natales");
						ciudades.add("Porvenir");
						ciudades.add("Punta Arenas");
						break;
					}
					case "Maule" : {
						ciudades.add("Cauquenes");
						ciudades.add("Chanco");
						ciudades.add("Linares");
						ciudades.add("Rauco");
						break;
					}
				}
				break;
			}
			case "Uruguay" : {
				switch (provincia) {
					case "Canelones":{
						ciudades.add("Acapulco");
						ciudades.add("Arenal");
						ciudades.add("Barra Del Tala");
						ciudades.add("Barrio Obrero");
						break;
					}
					case "Colonia" : {
						ciudades.add("Arrivillaga");
						ciudades.add("Barker");
						ciudades.add("Bonjour");
						ciudades.add("Carmelo");
						break;
					}
					case "Maldonado" : {
						ciudades.add("Alfaro");
						ciudades.add("Alferez");
						ciudades.add("Carape");
						ciudades.add("Chihuahua");
						break;
					}
					case "Montevideo" : {
						ciudades.add("Atahualpa");
						ciudades.add("Bolivar");
						ciudades.add("Casabo");
						ciudades.add("Marconi");
						break;
					}
				}
				break;
			}
		}
		
		return ciudades;
	}

	@Override
	public List<String> getMonedas() {
		List<String> monedas  = new ArrayList<String>();		
		
		monedas.add("Peso argentino");
		monedas.add("Peso Chileno");
		monedas.add("Peso Uruguayo");
		
		return monedas;
	}

	@Override
	public Pais getPais(String pais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provincia getProvincia(String pais, String provincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ciudad getCiudad(String pais, String provincia, String ciudad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Moneda getMonedaLocal(String pais) {
		Moneda moneda = null;
		
		switch(pais) {
			case "Argentina" : {
				moneda = new Moneda("ARS", "Peso argentino", "$", 2);
				break;
			}
			case "Chile" : {
				moneda = new Moneda("CLP", "Peso Chileno", "$", 0);
				break;
			}
			case "Uruguay" : {
				moneda = new Moneda("UYU", "Peso Uruguayo", "$", 2);
				break;
			}
		}
		
		return moneda;
	}

	
}
