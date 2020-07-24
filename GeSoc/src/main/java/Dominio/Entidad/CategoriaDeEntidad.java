package Dominio.Entidad;

import Dominio.Operacion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CategoriaDeEntidad {
	private String Nombre;
	private List<ReglaDeCategoria> reglas;

	public CategoriaDeEntidad(String nombre) {
		Nombre = nombre;
		reglas = new ArrayList<>();
	}

	public CategoriaDeEntidad(String nombre, List<ReglaDeCategoria> reglas) {
		this(nombre);
		this.reglas = reglas;
	}

	private boolean todasLasReglasCumplen(Predicate<ReglaDeCategoria> condicion) {
		return reglas.stream().allMatch(condicion);
	}

	public boolean puedeAgregarOperacion(EntidadOrganizacional entidad, Operacion operacion) {
		return todasLasReglasCumplen(regla -> regla.sePuedeAgregarOperacion(entidad ,operacion));
	}

	public boolean puedeAgregarEntidadBase() {
		return todasLasReglasCumplen(ReglaDeCategoria::sePuedeAgregarEntidadBase);
	}

	public boolean puedeSerParteDeEntidadJuridica() {
		return todasLasReglasCumplen(ReglaDeCategoria::puedeSerParteDeEntidadJuridica);
	}
	
	public void agregarRegla(ReglaDeCategoria regla) {
		if(!reglas.contains(regla))
			reglas.add(regla);
	}
	
	public void quitarRegla(ReglaDeCategoria regla) {
		if(reglas.contains(regla))
			reglas.remove(regla);
	}
}
