package Dominio.Entidad;

import Dominio.Operacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import  static java.util.stream.Collectors.*;


@Entity
public class CategoriaDeEntidad {
	@Id
	@GeneratedValue
	private long categoriaEntidad_id;
	private String Nombre;

	@OneToMany
	private List<ReglaDeCategoria> reglas;

	public CategoriaDeEntidad(String nombre) {
		Nombre = nombre;
		reglas = new ArrayList<>();
	}

	public CategoriaDeEntidad(String nombre, List<ReglaDeCategoria> reglas) {
		this(nombre);
		this.reglas = reglas;
	}
	
	public CategoriaDeEntidad() {
		
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
	public String getNombre() {
		return Nombre;
	}
	
	/*public String getReglasNombre() {
		if(reglas == null) return "";
		return reglas.stream().map(Object::toString).collect(joining(", ")); 
	}*/
	
	public List<ReglaDeCategoria> getReglas(){
		return reglas;
	}
	
	public long getId(){
		return categoriaEntidad_id;
	}

	public void setNombre(String value) {
		// TODO Auto-generated method stub
		Nombre = value;
	}
}
