package Dominio;

import Dominio.Pago.MedioDePago;

import java.util.List;
import java.util.Optional;
import java.time.*;

public class Operacion {

	private Integer numeroDocumento;
	private Provedor provedor;
	private LocalDate fecha;
	private MedioDePago medioPago;
	private List <ItemOperacion> detalle;
	private Optional<DocumentoComercial> documentoComercial;
	
	
	public Operacion(Integer numeroDocumento, Provedor provedor, LocalDate fecha, MedioDePago medioPago,
			List<ItemOperacion> detalle, Optional<DocumentoComercial> documentoComercial) {
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
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
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
	
	public Optional<DocumentoComercial> getDocumentoComercial() {
		return this.documentoComercial;
	}
	
	public void setDocumentoComercial(Optional<DocumentoComercial> documentoComercial) {
		this.documentoComercial = documentoComercial;
	}
}
