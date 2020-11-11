package controllers;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.Entidad.EntidadOrganizacional;
import Dominio.Entidad.RepositorioEntidades;
import Dominio.Mensajes.Mensaje;
import Dominio.Usuario.RepositorioUsuarios;
import Dominio.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class EntidadesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	
	public ModelAndView getVistaEntidades(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        List<EntidadOrganizacional> entidades =  RepositorioEntidades.instancia.listar();
        
        /*modelo.put("cantidad", bandeja.size());
        modelo.put("unread", bandeja.stream().filter(msg -> !msg.isLeido()).count());
        modelo.put("mensajes", bandeja);*/
        modelo.put("entidades", entidades);

        return new ModelAndView(modelo, "entidades.html.hbs");
    }
}