package Dominio;

import java.sql.Date;
import java.util.List;

import javax.print.Doc;

public class Operacion {

	private Integer numeroDocumento;
	private Provedor provedor;
	private Date fecha;
	private MedioDePago medioPago;
	private List <ItemOperacion> detalle;
	private DocumentoComercial documentoComercial;
	
	
	public Operacion(Integer numeroDocumento, Provedor provedor, Date fecha, MedioDePago medioPago,
			List<ItemOperacion> detalle, DocumentoComercial documentoComercial) {
		this.numeroDocumento = numeroDocumento;
		this.provedor = provedor;
		this.fecha = fecha;
		this.medioPago = medioPago;
		this.detalle = detalle;
		this.documentoComercial = documentoComercial;
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
	
	public DocumentoComercial getDocumentoComercial() {
		return this.documentoComercial;
	}
	
	public void setDocumentoComercial(DocumentoComercial documentoComercial) {
		this.documentoComercial = documentoComercial;
	}
}
