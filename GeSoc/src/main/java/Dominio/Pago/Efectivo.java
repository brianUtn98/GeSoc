package Dominio.Pago;

import java.util.Date;

public class Efectivo implements MedioDePago {

	private int numeroDeTicket;
	private Date fechaVencimiento;
	
	public Efectivo() {
		
	}

	public int getNumeroDeTicket() {
		return numeroDeTicket;
	}

	public void setNumeroDeTicket(int numeroDeTicket) {
		this.numeroDeTicket = numeroDeTicket;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	

}
