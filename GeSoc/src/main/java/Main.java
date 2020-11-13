import Dominio.*;
import Dominio.Entidad.CategoriaDeEntidad;
import Dominio.Pago.Efectivo;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.Presupuesto;
import Dominio.Ubicacion.Moneda;
import Dominio.Usuario.*;
import controllers.CategoriaController;
import controllers.EntidadController;
import controllers.CategoriasController;
import controllers.EntidadesController;
import controllers.MensajesController;
import controllers.UsuariosController;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDate;
import java.util.*;

import static spark.Spark.after;


public class Main{
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

        new Bootstrap().run(); // Deberia estar comemntado en el final

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
        
        CategoriasController categoriasController = new CategoriasController();

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

        Spark.get("/categoriasBuscar",(request, response) -> entidadController.mostrarEntidadCategoria(request,response),engine);
        Spark.get("/mensajes", (request, response) -> mensajesController.getVistaMensajes(request, response), engine);
        Spark.get("/mensajes/leer/:id", (request, response) -> mensajesController.leerMensaje(request, response), engine);
        Spark.get("/entidades", (request, response) -> entidadesController.getVistaEntidades(request, response), engine);
        Spark.get("/entidad/:id/categoria", (request, response) -> entidadesController.getFormularioSeleccionCategoria(request, response), engine);
        Spark.post("/entidad/:id/categoria", (request, response) -> entidadesController.asignarCategoria(request, response));
        
        Spark.get("/categorias", (request, response) -> categoriasController.getVistaCategorias(request, response), engine);
        Spark.get("/categoria/:id", (request, response) -> categoriasController.getFormularioEdicionCategoria(request, response), engine);
        Spark.get("/categoria", (request, response) -> categoriasController.getFormularioCategoria(request, response), engine);
        Spark.post("/categoria", (request, response) -> categoriasController.altaCategoria(request, response));
        Spark.post("/categoria/editar/:id", (request, response) -> categoriasController.editarCategoria(request, response));
    }
}