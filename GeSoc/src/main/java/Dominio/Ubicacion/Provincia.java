package Dominio.Ubicacion;

public class Provincia {
	private String nombre;
	private String zonaHoraria;
	private InformacionGeografica informacionGeografica;
	
	public Provincia(String nombre, String zonaHoraria, InformacionGeografica informacionGeografica) {
		this.setNombre(nombre);
		this.setZonaHoraria(zonaHoraria);
		this.setInformacionGeografica(informacionGeografica);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getZonaHoraria() {
		return zonaHoraria;
	}

	public void setZonaHoraria(String zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}

	public InformacionGeografica getInformacionGeografica() {
		return informacionGeografica;
	}

	public void setInformacionGeografica(InformacionGeografica informacionGeografica) {
		this.informacionGeografica = informacionGeografica;
	}
}
