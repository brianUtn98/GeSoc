package Dominio.Entidad;

import Dominio.Operacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Entity
public class CategoriaDeEntidad {
	@Id
	@GeneratedValue
	private long categoriaEntidad_id;
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

	private void todasLasReglasCumplen(Predicate<ReglaDeCategoria> condicion) {
		reglas.stream().allMatch(condicion);
	}

	public void puedeAgregarOperacion(EntidadOrganizacional entidad, Operacion operacion) {
		for(ReglaDeCategoria re : reglas) {
			re.sePuedeAgregarOperacion(entidad ,operacion);
		}
	}

	public void puedeAgregarEntidadBase() {
		for(ReglaDeCategoria re : reglas) {
			re.sePuedeAgregarEntidadBase();
		}
	}

	public void puedeSerParteDeEntidadJuridica() {
		for(ReglaDeCategoria re : reglas) {
			re.puedeSerParteDeEntidadJuridica();
		}
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
