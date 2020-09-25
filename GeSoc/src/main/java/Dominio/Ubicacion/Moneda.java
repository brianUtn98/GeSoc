package Dominio.Ubicacion;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Embeddable
public class Moneda {

	private String codigo;
	@Transient
	private String simbolo;
	@Transient
	private String descripcion;
	@Transient
	private Integer lugaresDecimal;
	
	public Moneda(String codigo, String simbolo, String descripcion, Integer lugaresDecimal) {
		this.setCodigo(codigo);
		this.setSimbolo(simbolo);
		this.setDescripcion(descripcion);
		this.setLugaresDecimal(lugaresDecimal);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getLugaresDecimal() {
		return lugaresDecimal;
	}

	public void setLugaresDecimal(Integer lugaresDecimal) {
		this.lugaresDecimal = lugaresDecimal;
	}
}
