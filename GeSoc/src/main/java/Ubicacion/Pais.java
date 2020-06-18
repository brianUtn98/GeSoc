package Ubicacion;

public class Pais {
	private String nombre;
	private String idioma;
	private String separadorDecimal;
	private String separadorMil;
	private String zonaHoraria;
	private String codigoMoneda;
	private InformacionGeografica informacionGeografica;
	
	public Pais(String nombre, String idioma, String separadorD, 
				String separadorM, String zonaHoraria,
				String codigoMoneda,InformacionGeografica infoGeografica) {
		this.setNombre(nombre);
		this.setIdioma(idioma);
		this.setSeparadorDecimal(separadorD);
		this.setSeparadorMil(separadorM);
		this.setZonaHoraria(zonaHoraria);
		this.setCodigoMoneda(codigoMoneda);
		this.setInformacionGeografica(infoGeografica);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getSeparadorDecimal() {
		return separadorDecimal;
	}

	public void setSeparadorDecimal(String separadorDecimal) {
		this.separadorDecimal = separadorDecimal;
	}

	public String getSeparadorMil() {
		return separadorMil;
	}

	public void setSeparadorMil(String separadorMil) {
		this.separadorMil = separadorMil;
	}

	public String getZonaHoraria() {
		return zonaHoraria;
	}

	public void setZonaHoraria(String zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public InformacionGeografica getInformacionGeografica() {
		return informacionGeografica;
	}

	public void setInformacionGeografica(InformacionGeografica informacionGeografica) {
		this.informacionGeografica = informacionGeografica;
	}
	
}
