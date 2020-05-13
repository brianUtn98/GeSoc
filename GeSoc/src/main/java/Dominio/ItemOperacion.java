package Dominio;

public class ItemOperacion {
	private String descripcion;
	private Integer valorTotal;
	
	public ItemOperacion(String descripcion, Integer valorTotal) {
		this.descripcion = descripcion;
		this.valorTotal = valorTotal;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Integer valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
	
}
