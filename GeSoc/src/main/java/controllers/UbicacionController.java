package controllers;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.ServicioInformacionUbicaciones;
import Dominio.ServicioMercadoLibre;

public class UbicacionController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	public static List<String> getPaisesFromAPI(){
		ServicioInformacionUbicaciones service = new ServicioMercadoLibre();
		return service.getPaises();
	}
	
	public List<String> getProvinciasFromAPI(String pais){
		ServicioInformacionUbicaciones service = new ServicioMercadoLibre();
		return service.getProvincias(pais);
	}
	
	public List<String> getCiudadesFromAPI(String pais, String provincia){
		ServicioInformacionUbicaciones service = new ServicioMercadoLibre();
		return service.getCiudades(pais, provincia);
	}
}
