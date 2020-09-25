package Dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;

import Dominio.Pago.ValorMonetario;
import Dominio.Ubicacion.Moneda;

@Entity
public class Reporte {
	
	@Id
	@GeneratedValue
	private long reporte_id;
	
	private String nombre;
	private LocalDate fecha;
	
	@OneToMany
	private Collection<Operacion> operaciones;
	
	public Reporte(Collection<Operacion> _entradas, String _nombre, LocalDate _fecha) {
		operaciones = _entradas;
		nombre = _nombre;
		fecha = _fecha;
	}
	
	public  ValorMonetario obtenerTotal() {
		ValorMonetario total =new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 0);
		for(Operacion m : operaciones){
			total = total.sumar(m.getTotal());
		}
		return total;
	}
	
	public Map<Etiqueta, Collection<Operacion>> devolverDetalle(){
		Map<Etiqueta, Collection<Operacion>> mapeos = new HashMap<>();
		Collection<Etiqueta> etiquetas = operaciones.stream()
				.flatMap(x -> x.getEtiquetas().stream())
				.collect(Collectors.toList());
		
		 for (Etiqueta e: etiquetas) {
			 mapeos.put(e, this.devolverDetallePorEtiqueta(e));		 
		 }
		return mapeos;
	}
	
	public Collection<Operacion> devolverDetallePorEtiqueta(Etiqueta e){	
		Collection<Operacion> op = operaciones.stream()
				.filter(x -> x.getEtiquetas().contains(e))
				.collect(Collectors.toList());
		
		return op;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public ValorMonetario obtenerTotal(Etiqueta et) {
		Collection<Operacion> op = operaciones.stream()
				.filter(x -> x.getEtiquetas().contains(et))
				.collect(Collectors.toList());
		
		ValorMonetario total =new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 0);
		for(Operacion m : op){
			total = total.sumar(m.getTotal());
		}
		return total;
	}
}
