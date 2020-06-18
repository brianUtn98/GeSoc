package Dominio.Pago;

public class DineroEnCuenta implements MedioDePago {

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
