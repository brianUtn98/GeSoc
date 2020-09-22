package Dominio.Pago;

import javax.persistence.Entity;

@Entity
public class CajeroAutomatico extends MedioDePago {

	private String numeroCajero;
	private String sucursal;
	
	
	public String getNumeroCajero() {
		return numeroCajero;
	}
	public void setNumeroCajero(String numeroCajero) {
		this.numeroCajero = numeroCajero;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
