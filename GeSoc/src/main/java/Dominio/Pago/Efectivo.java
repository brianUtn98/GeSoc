package Dominio.Pago;

import org.hibernate.type.TimeType;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Efectivo extends MedioDePago {

	private int numeroDeTicket;
	@Temporal(TemporalType.TIMESTAMP)
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
