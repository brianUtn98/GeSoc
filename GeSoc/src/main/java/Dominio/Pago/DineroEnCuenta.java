package Dominio.Pago;

import javax.persistence.Entity;

@Entity
public class DineroEnCuenta extends MedioDePago {

	private Integer numeroDeCuenta;
	
	public DineroEnCuenta(Integer numero) {
		numeroDeCuenta = numero;
	}

	public Integer getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(Integer numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

}
