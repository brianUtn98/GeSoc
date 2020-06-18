package Dominio.Pago;

public class TarjetaDeCredito implements MedioDePago {

	private Integer numeroIdentificatorio;
	private String nombreCompleto;
	private Integer mesVencimiento; 
	private Integer anioVencimiento;
	private Integer codigoVerificacion;
	
	public Integer getNumeroIdentificatorio() {
		return numeroIdentificatorio;
	}

	public void setNumeroIdentificatorio(Integer numeroIdentificatorio) {
		this.numeroIdentificatorio = numeroIdentificatorio;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Integer getAnioVencimiento() {
		return anioVencimiento;
	}

	public void setAnioVencimiento(Integer anioVencimiento) {
		this.anioVencimiento = anioVencimiento;
	}

	public Integer getMesVencimiento() {
		return mesVencimiento;
	}

	public void setMesVencimiento(Integer mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}

	public Integer getCodigoVerificacion() {
		return codigoVerificacion;
	}

	public void setCodigoVerificacion(Integer codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}

}
