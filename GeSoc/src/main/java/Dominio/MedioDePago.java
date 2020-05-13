package Dominio;


public class MedioDePago {
	
	private Integer numeroIdentificatorio;
	private TipoDeMedioDePago tipoDeMedioDePago; 
	
	public MedioDePago(Integer numeroIdentificatorio, TipoDeMedioDePago tipoDeMedioDePago) {
		this.numeroIdentificatorio = numeroIdentificatorio;
		this.tipoDeMedioDePago = tipoDeMedioDePago;
	}
	
	public Integer getNumeroIdentificatorio() {
		return numeroIdentificatorio;
	}
	public void setNumeroIdentificatorio(Integer numeroIdentificatorio) {
		this.numeroIdentificatorio = numeroIdentificatorio;
	}

	public TipoDeMedioDePago getTipoDeMedioDePago() {
		return tipoDeMedioDePago;
	}
	
	public void setTipoDeMedioDePago(TipoDeMedioDePago tipoDeMedioDePago) {
		this.tipoDeMedioDePago = tipoDeMedioDePago;
	}
}
