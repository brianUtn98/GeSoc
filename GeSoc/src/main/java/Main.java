import Dominio.Usuario.*;
import controllers.CategoriaController;
import controllers.EntidadController;
import controllers.EntidadesBaseController;
import controllers.CategoriasController;
import controllers.EntidadesController;
import controllers.EntidadesJuridicasController;
import controllers.MensajesController;
import controllers.OperacionesController;
import controllers.UbicacionController;
import controllers.UsuariosController;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.after;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema GeSoc");

        //Como todavía no trabajamos persistencia, instancio algunos objetos de dominio para poder correr la tarea calendarizada:
        //init();

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

        //new Bootstrap().run(); // Deberia estar comemntado en el final

        System.out.println("Iniciando servidor spark");

        Spark.port(8080);
        Spark.staticFileLocation("/public");

        after((request, response) -> {
            PerThreadEntityManagers.getEntityManager();
            PerThreadEntityManagers.closeEntityManager();
        });

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

        UsuariosController usuariosController = new UsuariosController();
        EntidadController entidadController = new EntidadController();
        CategoriaController categoriaBuscada = new CategoriaController();

        MensajesController mensajesController = new MensajesController();
        
        EntidadesController entidadesController = new EntidadesController();
        EntidadesBaseController entidadesBaseController = new EntidadesBaseController();
        EntidadesJuridicasController entidadesJuridicasController = new EntidadesJuridicasController();
        CategoriasController categoriasController = new CategoriasController();
        
        UbicacionController ubicacionController = new UbicacionController();

        OperacionesController operacionesController = new OperacionesController();

        Spark.get("/", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();

            Optional<Usuario> usuarioLogueado = usuariosController.getUsuarioLogueado(request);

            if(!usuarioLogueado.isPresent())
                response.redirect("/login");
            else
                modelo.put("usuario", usuarioLogueado.get());

            return new ModelAndView(modelo, "index.html.hbs");
        }, engine);

        Spark.get("/login", (request, response) -> usuariosController.getFormularioLogin(request, response), engine);

        Spark.post("/login", (request, response) -> usuariosController.loginUsuario(request, response));

        Spark.get("/logout", (request, response) -> usuariosController.logoutUsuario(request, response), engine);

        Spark.get("/categoriasBuscar",(request, response) -> entidadController.mostrarEntidadCategoria(request,response),engine);
        Spark.get("/mensajes", (request, response) -> mensajesController.getVistaMensajes(request, response), engine);
        Spark.get("/mensajes/leer/:id", (request, response) -> mensajesController.leerMensaje(request, response), engine);

        Spark.get("/operacion",(request,response) -> operacionesController.getFormularioOperaciones(request,response),engine);
        Spark.get("/operaciones/:id/detalle",(request,response) -> operacionesController.vistaOperacion(request,response),engine);
        Spark.get("/operaciones/:idOperacion/presupuestos/:idPresupuesto/detalle",(request,response) -> operacionesController.detallePresupuesto(request,response),engine);
        Spark.get("/operaciones/:idOperacion/presupuestos/:idPresupuesto/seleccionar",(request,response) -> operacionesController.seleccionPresupuesto(request,response),engine);
        Spark.get("/operaciones/:id/presupuestos",(request,response) -> operacionesController.vistaPresupuestos(request,response),engine);

        Spark.get("/operaciones/:id/presupuesto",(request,response) -> operacionesController.getFormularioPresupuesto(request,response),engine);
        Spark.post("/operaciones/:id/presupuesto",(request,response) -> operacionesController.cargarPresupuesto(request,response),engine);
        Spark.post("/operacion",(request,response) -> operacionesController.cargarOperacion(request,response));
        Spark.get("/operaciones", (request, response) -> operacionesController.vistaOperaciones(request, response), engine);

        Spark.get("/entidades", (request, response) -> entidadesController.getVistaEntidades(request, response), engine);
        Spark.get("/entidad/:id/categoria", (request, response) -> entidadesController.getFormularioSeleccionCategoria(request, response), engine);
        Spark.post("/entidad/:id/categoria", (request, response) -> entidadesController.asignarCategoria(request, response));
        Spark.get("/entidadBase", (request, response) -> entidadesBaseController.getFormularioNewEntidadBase(request, response),engine);
        Spark.post("entidadBase", (request, response) -> entidadesBaseController.postNewEntidadBase(request, response));
        Spark.get("entidadBase/:id", (request, response) -> entidadController.getEditEntidad(request, response), engine);
        Spark.post("entidadBase/:id", (request, response) -> entidadController.getEditEntidad(request, response));
        Spark.get("/entidadJuridica", (request, response) -> entidadesJuridicasController.getFormularioNewEntidadJuridica(request, response),engine);
        Spark.post("entidadJuridica", (request, response) -> entidadesJuridicasController.postNewEntidadJuridica(request, response));
        Spark.get("entidadJuridica/:id", (request, response) -> entidadController.getEditEntidad(request, response), engine);
        Spark.post("entidadJuridica/:id", (request, response) -> entidadController.getEditEntidad(request, response));

        Spark.get("/categorias", (request, response) -> categoriasController.getVistaCategorias(request, response), engine);
        Spark.get("/categoria/:id", (request, response) -> categoriasController.getFormularioEdicionCategoria(request, response), engine);
        Spark.get("/categoria", (request, response) -> categoriasController.getFormularioCategoria(request, response), engine);
        Spark.post("/categoria", (request, response) -> categoriasController.altaCategoria(request, response));
        Spark.post("/categoria/:id", (request, response) -> categoriasController.editarCategoria(request, response));
        Spark.get("/categoria/:id/regla", (request, response) -> categoriasController.getFormAgrearReglaACategoria(request, response), engine);
        Spark.post("/categoria/:id/regla", (request, response) -> categoriasController.agregarReglaACategoria(request, response));
        
        Spark.get("ubicacion/provincias", "aplicacion/json", (request, response) -> {
        	return ubicacionController.getProvinciasFromAPI(request.queryParams("pais"));        	
        }, new JsonTransformer());
        
        Spark.get("ubicacion/ciudades", "aplicacion/json", (request, response) -> {
        	return ubicacionController.getCiudadesFromAPI(request.queryParams("pais"),request.queryParams("provincia"));        	
        }, new JsonTransformer());
    }
}