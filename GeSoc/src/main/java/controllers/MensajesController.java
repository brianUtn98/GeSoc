package controllers;

import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Mensajes.Mensaje;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MensajesController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView getVistaMensajes(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        List<Mensaje> bandeja = usuarioLogueado.get().verBandejaMensajes();

        modelo.put("cantidad", bandeja.size());
        modelo.put("mensajes", bandeja);

        return new ModelAndView(modelo, "mensajes.html.hbs");
    }
}
