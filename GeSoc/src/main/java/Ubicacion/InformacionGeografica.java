package Ubicacion;

public class InformacionGeografica {
	private Double latitud;
	private Double longitud;
	
	public InformacionGeografica(Double latitud, Double longitud) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
}
