package controllers;

import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Mensajes.Mensaje;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class MensajesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public ModelAndView getVistaMensajes(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        modelo.put("usuario", usuarioLogueado.get());

        List<Mensaje> bandeja = usuarioLogueado.map(Usuario::verBandejaMensajes).get();
        bandeja.sort(Comparator.comparing(Mensaje::isLeido));

        modelo.put("cantidad", bandeja.size());
        modelo.put("unread", bandeja.stream().filter(msg -> !msg.isLeido()).count());
        modelo.put("mensajes", bandeja);

        return new ModelAndView(modelo, "mensajes.html.hbs");
    }

    public ModelAndView leerMensaje(Request request, Response response) {
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        List<Mensaje> bandeja = usuarioLogueado.map(Usuario::verBandejaMensajes).get();

        long id = Long.parseLong(request.params(":id"));
        Optional<Mensaje> mensaje = bandeja.stream().filter(msg -> msg.getId() == id).findFirst();

        if(!mensaje.isPresent()) {
            response.redirect("/mensajes");
            return null;
        }

        withTransaction(() -> {
            mensaje.get().marcarLeido();
        });

        response.redirect("/operaciones/"+mensaje.get().getOperacion().getId()+"/detalle");
        return null;
    }
}
