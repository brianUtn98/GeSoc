import Dominio.*;
import Dominio.Pago.Efectivo;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.Presupuesto;
import Dominio.Presupuesto.ValidacionCantidadPresupuestos;
import Dominio.Ubicacion.Moneda;
import Dominio.Usuario.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Sistema GeSoc");
        CorredorValidaciones corredorValidaciones = CorredorValidaciones.getInstance();
        CreadorDeUsuario creador = new CreadorDeUsuario("Pepe");
        creador.setearTipoUsuario(new TipoEstandar());
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        validadores.add(new ValidadorLongitud());
        creador.crearPassword("aloja125",validadores);
        Usuario unUsuario = creador.crearUsuario();

        ItemOperacion unItem3 = new ItemOperacion("UnProducto3",new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 200));
        Provedor proveedorHomero = new Provedor("Homero", "Thompson", "Pato feliz", 29256328,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        Provedor proveedorBart = new Provedor("Bart", "Thompson", "Pato infeliz", 29256329,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        List<ItemOperacion> items1 = new ArrayList<ItemOperacion>();
        items1.add(new ItemOperacion("UnProducto1", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 1000)));
        items1.add(new ItemOperacion("UnProducto2",new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 2000)));

        List<ItemOperacion> items2 = new ArrayList<ItemOperacion>();
        items2.add(new ItemOperacion("UnProducto3",new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 200)));

        Operacion unEgreso = new Operacion(41665156,proveedorBart,LocalDate.now(),new Efectivo(),items1,null,true,true);
        unEgreso.addPresupusto(new Presupuesto(proveedorBart,items1));
        unEgreso.addPresupusto(new Presupuesto(proveedorHomero,items2));
        Operacion otroEgreso = new Operacion(41665156,proveedorHomero,LocalDate.now(),new Efectivo(),items2,null,true,true);
        otroEgreso.addPresupusto(new Presupuesto(proveedorBart,items2));
        corredorValidaciones.agregarOperacion(unEgreso);
        corredorValidaciones.agregarOperacion(otroEgreso);

        //Como todavía no trabajamos persistencia, instancio algunos objetos de dominio para poder correr la tarea calendarizada.

        unEgreso.altaRevisor(unUsuario);
        otroEgreso.altaRevisor(unUsuario);

        try {
            JobDetail tarea = JobBuilder.newJob(TareaValidar.class).build();
            Trigger activador = TriggerBuilder.newTrigger().withIdentity("cronTrigger","group1").withSchedule(CronScheduleBuilder.cronSchedule("* 1 * * * ?")).build();
            Scheduler planificador = new StdSchedulerFactory().getScheduler();
            planificador.start();
            planificador.scheduleJob(tarea,activador);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}