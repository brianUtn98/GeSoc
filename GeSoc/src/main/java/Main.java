import Dominio.*;
import Dominio.Pago.Efectivo;
import Dominio.Presupuesto.Presupuesto;
import Dominio.Presupuesto.ValidacionCantidadPresupuestos;
import Dominio.Usuario.*;

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
        List<Operacion> operaciones = new ArrayList<Operacion>();
        CreadorDeUsuario creador = new CreadorDeUsuario("Pepe");
        creador.setearTipoUsuario(new TipoEstandar());
        ValidadorLongitud unValidador = new ValidadorLongitud();
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        validadores.add(unValidador);
        creador.crearPassword("aloja125",validadores);
        Usuario unUsuario = creador.crearUsuario();

        ItemOperacion unItem = new ItemOperacion("UnProducto1",1000);
        ItemOperacion unItem2 = new ItemOperacion("UnProducto2",2000);
        ItemOperacion unItem3 = new ItemOperacion("UnProducto3",200);
        Provedor proveedorHomero = new Provedor("Homero", "Thompson", "Pato feliz", 29256328,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        Provedor proveedorBart = new Provedor("Bart", "Thompson", "Pato infeliz", 29256329,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        List<ItemOperacion> items1 = new ArrayList<ItemOperacion>();
        items1.add(unItem);
        items1.add(unItem2);

        List<ItemOperacion> items2 = new ArrayList<ItemOperacion>();
        items2.add(unItem3);
        Presupuesto unPresupuesto = new Presupuesto(proveedorBart,items1);
        Presupuesto otroPresupuesto = new Presupuesto(proveedorHomero,items2);
        Presupuesto otroPresupuesto2 = new Presupuesto(proveedorBart,items2);

        Efectivo unMedio = new Efectivo();
        Operacion unEgreso = new Operacion(41665156,proveedorBart,LocalDate.now(),unMedio,items1,null,true,true);
        unEgreso.addPresupusto(unPresupuesto);
        unEgreso.addPresupusto(otroPresupuesto);
        Operacion otroEgreso = new Operacion(41665156,proveedorHomero,LocalDate.now(),unMedio,items2,null,true,true);
        otroEgreso.addPresupusto(otroPresupuesto2);
        operaciones.add(unEgreso);
        operaciones.add(otroEgreso);

        unEgreso.altaRevisor(unUsuario);
        otroEgreso.altaRevisor(unUsuario);


        System.out.println("Para validar las operaciones, ingrese VALIDAR");
        InputStreamReader streamLeido = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader (streamLeido);
        String lectura = buffer.readLine();
        System.out.println("Se leyó: " + lectura);

        if(lectura.equals("VALIDAR")){
            operaciones.stream().forEach(operacion -> {
                ValidacionCantidadPresupuestos unaValidacion = new ValidacionCantidadPresupuestos(operacion);              
                String mensaje = unaValidacion.getNombre()+(unaValidacion.validar()?"OK":"Fallo");
                operacion.notificar(mensaje);
            }
            );

            System.out.println("Se validaron las operaciones de egreso");

            System.out.println("Se mostraran los mensajes del usuario: " + unUsuario.getNombre());
            unUsuario.verBandejaMensajes().stream().forEach(mensaje -> System.out.println(mensaje));

        }
        else
        {
            System.out.println("Saliendo del sistema");
            return;
        }
    }
}