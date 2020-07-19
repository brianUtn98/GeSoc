package Dominio;

import Dominio.Pago.ValorMonetario;

public class ItemOperacion {
	private String descripcion;
	private ValorMonetario valorTotal;
	
	public ItemOperacion(String descripcion, ValorMonetario valorTotal) {
		this.descripcion = descripcion;
		this.valorTotal = valorTotal;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ValorMonetario getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(ValorMonetario valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
	
}
