package Dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dominio.Pago.ValorMonetario;
import Dominio.Ubicacion.Moneda;

public class Reporte {
	private Map<Etiqueta, ValorMonetario> entradas;
	
	public Reporte() {
		entradas = new HashMap<>();
	}
	
	public  ValorMonetario obtenerTotal() {
		ValorMonetario total =new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 0);
		for(Map.Entry<Etiqueta, ValorMonetario> m : entradas.entrySet()){
			total = total.sumar(m.getValue());
		}
		return total;
	}


	public void agregarDetalle(Etiqueta e, ValorMonetario v) {
		entradas.put(e, v);
	}
	
	public Map<Etiqueta, ValorMonetario> devolverDetalle(){
		return entradas;
	}
}
