package Dominio;

public class MedioDePago {
	
	private Integer numeroIdentificatorio;
	enum TipoDeMedioDePago{
		TarjetaDeCredito,
		TarjetaDeDebito,
		Efectivo,
		CajeroAutomatico,
		DineroEnCuenta
	} 

	
	public MedioDePago(Integer numeroIdentificatorio) {
		this.numeroIdentificatorio = numeroIdentificatorio;
	}
	
	public Integer getNumeroIdentificatorio() {
		return numeroIdentificatorio;
	}
	public void setNumeroIdentificatorio(Integer numeroIdentificatorio) {
		this.numeroIdentificatorio = numeroIdentificatorio;
	}

}
