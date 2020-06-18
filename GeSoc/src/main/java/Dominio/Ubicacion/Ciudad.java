package Dominio.Ubicacion;

public class Ciudad {
	private String nombre;
	private InformacionGeografica informacionGeografica;
	
	public Ciudad(String nombre, InformacionGeografica informacionGeografica) {
		this.setNombre(nombre);
		this.setInformacionGeografica(informacionGeografica);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InformacionGeografica getInformacionGeografica() {
		return informacionGeografica;
	}

	public void setInformacionGeografica(InformacionGeografica informacionGeografica) {
		this.informacionGeografica = informacionGeografica;
	}	
}
