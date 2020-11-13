package controllers;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.ServicioInformacionUbicaciones;
import Dominio.ServicioMercadoLibre;

public class MonedaController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	public static List<String> getMonedasFromApi() {
		ServicioInformacionUbicaciones service = new ServicioMercadoLibre();
		return service.getMonedas();
	}
}
