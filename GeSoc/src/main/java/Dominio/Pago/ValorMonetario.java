package Dominio.Pago;

import Dominio.Ubicacion.Moneda;

public class ValorMonetario {
    private Moneda moneda;
    private Integer monto;

    public ValorMonetario(Moneda moneda, Integer monto) {
        this.moneda = moneda;
        this.monto = monto;
    }

    public ValorMonetario sumar(ValorMonetario otroValor) {
        if(this.moneda.getCodigo().equals(otroValor.getMoneda().getCodigo())){
        	ValorMonetario v= new ValorMonetario(moneda, monto + otroValor.getMonto());
            return v;
        }
        else {
            return this; // TODO: Que pasa si son 2 monedas distintas? Habria que resolver esto
        }
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public Integer getMonto() {
        return monto;
    }
}
