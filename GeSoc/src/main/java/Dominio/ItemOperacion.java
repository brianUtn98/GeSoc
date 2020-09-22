package Dominio;

import Dominio.Pago.ValorMonetario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ItemOperacion {
	@Id
	@GeneratedValue
	private long item_id;

	private String descripcion;

	@OneToOne
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
