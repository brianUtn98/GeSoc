import Dominio.*;
import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Mensajes.Mensaje;
import Dominio.Pago.Efectivo;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.Presupuesto;
import Dominio.Ubicacion.Moneda;
import Dominio.Usuario.GeneradorDeHash;
import Dominio.Usuario.TipoAdministrador;
import Dominio.Usuario.TipoDeUsuario;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public static void main(String[] args) {
        new Bootstrap().run();
    }

    public void run() {
        Provedor proveedorHomero = new Provedor("Homero", "Thompson", "Pato feliz", 29256328,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        Provedor proveedorBart = new Provedor("Bart", "Thompson", "Pato infeliz", 29256329,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

        Moneda unaMoneda = new Moneda("0", "ARS", "Peso", 2); // Tener un mock de moneda para esto y los tests seria genial

        List<ItemOperacion> items1 = new ArrayList<>();
        items1.add(new ItemOperacion("UnProducto1", new ValorMonetario(unaMoneda, 1000)));
        items1.add(new ItemOperacion("UnProducto2", new ValorMonetario(unaMoneda, 2000)));

        List<ItemOperacion> items2 = new ArrayList<>();
        items2.add(new ItemOperacion("UnProducto3", new ValorMonetario(unaMoneda, 200)));

        Operacion unEgreso = new Operacion(41665156,proveedorBart, LocalDate.now(),new Efectivo(),items1,null,true,true);
        unEgreso.addPresupusto(new Presupuesto(proveedorBart,items1));
        unEgreso.addPresupusto(new Presupuesto(proveedorHomero,items2));
        Operacion otroEgreso = new Operacion(41665156,proveedorHomero,LocalDate.now(),new Efectivo(),items2,null,true,true);
        otroEgreso.addPresupusto(new Presupuesto(proveedorBart,items2));


        TipoDeUsuario tipoAdmin = new TipoAdministrador();
        Usuario jose = new Usuario("jose55", new GeneradorDeHash().getHash("123456"), tipoAdmin, new BandejaDeMensajes());

        unEgreso.altaRevisor(jose);
        withTransaction(() -> {
            persist(tipoAdmin);
            jose.updateBandeja(new Mensaje("aaaa", unEgreso));
            jose.updateBandeja(new Mensaje("SOY UN MENSAJE", unEgreso));
            persist(unEgreso.getMedioPago());
            persist(proveedorBart.getDireccionPostal());
            persist(proveedorBart);
            persist(proveedorHomero.getDireccionPostal());
            persist(proveedorHomero);
            items1.forEach(itemOperacion -> persist(itemOperacion));
            items2.forEach(itemOperacion -> persist(itemOperacion));
            unEgreso.getPresupuestos().forEach(presupuesto -> persist(presupuesto));
            persist(unEgreso);
            jose.verBandejaMensajes().forEach(mensaje -> persist(mensaje));
            persist(jose);
        });
    }

}