import Dominio.*;
import Dominio.Pago.Efectivo;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.Presupuesto;
import Dominio.Ubicacion.Moneda;
import Dominio.Usuario.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static Usuario initUsuario() {
        CreadorDeUsuario creador = new CreadorDeUsuario("Pepe");
        creador.setearTipoUsuario(new TipoEstandar());
        List<ValidadorPassword> validadores = new ArrayList<>();
        validadores.add(new ValidadorLongitud());
        creador.crearPassword("aloja125",validadores);
        return creador.crearUsuario();
    }

    private static void init() {
        Provedor proveedorHomero = new Provedor("Homero", "Thompson", "Pato feliz", 29256328,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        Provedor proveedorBart = new Provedor("Bart", "Thompson", "Pato infeliz", 29256329,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

        Moneda unaMoneda = new Moneda("0", "ARS", "Peso", 2); // Tener un mock de moneda para esto y los tests seria genial

        List<ItemOperacion> items1 = new ArrayList<>();
        items1.add(new ItemOperacion("UnProducto1", new ValorMonetario(unaMoneda, 1000)));
        items1.add(new ItemOperacion("UnProducto2", new ValorMonetario(unaMoneda, 2000)));

        List<ItemOperacion> items2 = new ArrayList<>();
        items2.add(new ItemOperacion("UnProducto3", new ValorMonetario(unaMoneda, 200)));

        Operacion unEgreso = new Operacion(41665156,proveedorBart,LocalDate.now(),new Efectivo(),items1,null,true,true);
        unEgreso.addPresupusto(new Presupuesto(proveedorBart,items1));
        unEgreso.addPresupusto(new Presupuesto(proveedorHomero,items2));
        Operacion otroEgreso = new Operacion(41665156,proveedorHomero,LocalDate.now(),new Efectivo(),items2,null,true,true);
        otroEgreso.addPresupusto(new Presupuesto(proveedorBart,items2));

        Usuario unUsuario = initUsuario();
        unEgreso.altaRevisor(unUsuario);
        otroEgreso.altaRevisor(unUsuario);

        CorredorValidaciones corredorValidaciones = CorredorValidaciones.getInstance();
        corredorValidaciones.agregarOperacion(unEgreso);
        corredorValidaciones.agregarOperacion(otroEgreso);
    }

    public static void main(String[] args) {
        System.out.println("Sistema GeSoc");

        //Como todavía no trabajamos persistencia, instancio algunos objetos de dominio para poder correr la tarea calendarizada:
        init();

        // Configuramos la tarea programada:
        try {
            JobDetail tarea = JobBuilder.newJob(TareaValidar.class).build();
            Trigger activador = TriggerBuilder.newTrigger().withIdentity("cronTrigger","group1").withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * * *")).build();
            Scheduler planificador = new StdSchedulerFactory().getScheduler();
            planificador.start();
            planificador.scheduleJob(tarea,activador);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}