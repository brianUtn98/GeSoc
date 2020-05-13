package Dominio;

import java.sql.Date;
import java.util.List;

public class Operacion {

	private Integer numeroDocumento;
	private Provedor provedor;
	private Date fecha;
	private MedioDePago medioPago;
	private List <ItemOperacion> detalle;
	enum DocumentoComercial{
		Factura,
		ticket,
		NC,
		ND,
		Remito
	}
	
	
	public Operacion(Integer numeroDocumento, Provedor provedor, Date fecha, MedioDePago medioPago,
			List<ItemOperacion> detalle) {
		this.numeroDocumento = numeroDocumento;
		this.provedor = provedor;
		this.fecha = fecha;
		this.medioPago = medioPago;
		this.detalle = detalle;
	}
	
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Provedor getProvedor() {
		return provedor;
	}
	public void setProvedor(Provedor provedor) {
		this.provedor = provedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public MedioDePago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioDePago medioPago) {
		this.medioPago = medioPago;
	}
	public List<ItemOperacion> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<ItemOperacion> detalle) {
		this.detalle = detalle;
	}
}
