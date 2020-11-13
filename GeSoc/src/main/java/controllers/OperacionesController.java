package controllers;

import Dominio.*;
import Dominio.Pago.Efectivo;
import Dominio.Pago.MedioDePago;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.Presupuesto;
import Dominio.Ubicacion.Moneda;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.*;

public class OperacionesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView cargarOperacion(Request request, Response response){
        /*Arrays.stream(request.queryMap("item").values()).iterator().forEachRemaining(str -> System.out.println(str));
        System.out.println("aaaa");*/
        /*Arrays.stream(request.queryMap("item").get("1").values()).iterator().forEachRemaining(str -> System.out.println(str));
        System.out.println(request.queryMap("item").value());
        System.out.println(request.queryMap().toMap().get("item").length);*/


        //Arrays.stream(request.queryMap("item").values()).iterator().forEachRemaining(str -> System.out.println(str));


        // !! Empieza codigo cavernicola: !!
        String[] detalles = request.queryMap("item").values();
        int[] montos = Arrays.stream(request.queryMap("monto").values()).mapToInt(str -> Integer.parseInt(str)).toArray();
        String[] monedas = request.queryMap("monto").values();

        List<ItemOperacion> items = new ArrayList<>();
        for (int i = 0; i < detalles.length; i++) {
            ValorMonetario valor = new ValorMonetario(new Moneda(monedas[i], "$", "", 2), montos[i]); // TODO: revisar esto hardcodeadp
            items.add(new ItemOperacion(detalles[i], valor));
        }

        Provedor proveedor = RepositorioProveedor.instancia.getById(Long.parseLong(request.queryParams("proveedor")));
        MedioDePago medioDePago = new Efectivo(); // TODO
        Integer numeroDocumento = Integer.parseInt(request.queryParams("numeroDocumento"));
        DocumentoComercial documento = DocumentoComercial.valueOf(request.queryParams("documentoComercial"));
        boolean requierePresupuesto = Boolean.parseBoolean(request.queryParams("requierePresupuesto"));
        Operacion operacion = new Operacion(numeroDocumento, proveedor, LocalDate.now(), medioDePago, items, documento, requierePresupuesto, true); // TODO: Terminar de obtener todos los datos

        withTransaction(() -> {
            items.forEach(item -> persist(item));
            persist(medioDePago);
            RepositorioOperacion.instancia.agregar(operacion);
        });

        response.redirect("/operaciones");

        return null;
    }


    public ModelAndView getFormularioOperaciones(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }


        List<Provedor> proveedores = RepositorioProveedor.instancia.listar();

        modelo.put("proveedores", proveedores);

        //DocumentoComercial[] documentos = DocumentoComercial.values();
        modelo.put("documentos", DocumentoComercial.values());


        return new ModelAndView(modelo,"operacion.html.hbs");
    }


    public ModelAndView vistaOperaciones(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }


        List<Operacion> operaciones = RepositorioOperacion.instancia.listar();

        modelo.put("operaciones", operaciones);


        return new ModelAndView(modelo,"operaciones.html.hbs");
    }


    public ModelAndView vistaOperacion(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }

        Long id = Long.parseLong(request.params("id"));

        Operacion operacion = RepositorioOperacion.instancia.getById(id);

        modelo.put("items", operacion.getDetalle());


        return new ModelAndView(modelo,"detalle-operacion.html.hbs");
    }


    public ModelAndView vistaPresupuestos(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }

        Long id = Long.parseLong(request.params("id"));

        Operacion operacion = RepositorioOperacion.instancia.getById(id);

        modelo.put("operacion", operacion);
        modelo.put("presupuestos", operacion.getPresupuestos().stream().filter(presupuesto -> !presupuesto.equals(operacion.getPresupuestoSeleccionado().get())).toArray());
        if(operacion.getPresupuestoSeleccionado().isPresent())
            modelo.put("seleccionado", operacion.getPresupuestoSeleccionado().get());


        return new ModelAndView(modelo,"presupuestos.html.hbs");
    }


    public ModelAndView seleccionPresupuesto(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }

        Long idOperacion = Long.parseLong(request.params("idOperacion"));
        Operacion operacion = RepositorioOperacion.instancia.getById(idOperacion);
        Long idPresupuesto = Long.parseLong(request.params("idPresupuesto"));
        Optional<Presupuesto> presupuesto = operacion.getPresupuestos().stream().filter(unPresupuesto -> unPresupuesto.getId() == idPresupuesto).findFirst();

        if(!presupuesto.isPresent()){
            response.redirect("/operaciones");
            return null;
        }

        withTransaction(() -> {
            operacion.setPresupuestoSeleccionado(presupuesto.get());
        });
        response.redirect("/operaciones/"+idOperacion+"/presupuestos");
        return null;
    }

    public ModelAndView detallePresupuesto(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }


        Long idOperacion = Long.parseLong(request.params("idOperacion"));
        Operacion operacion = RepositorioOperacion.instancia.getById(idOperacion);
        Long idPresupuesto = Long.parseLong(request.params("idPresupuesto"));
        Optional<Presupuesto> presupuesto = operacion.getPresupuestos().stream().filter(unPresupuesto -> unPresupuesto.getId() == idPresupuesto).findFirst();

        if(!presupuesto.isPresent()){
            response.redirect("/operaciones");
            return null;
        }

        modelo.put("items", presupuesto.get().getDetalle());


        return new ModelAndView(modelo,"detalle-operacion.html.hbs");
    }
}



