package controllers;

import Dominio.Usuario.GeneradorDeHash;
import Dominio.Usuario.RepositorioUsuarios;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsuariosController implements WithGlobalEntityManager, TransactionalOps {

    public Void loginUsuario(Request request, Response response) {
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        Optional<Usuario> usuario = RepositorioUsuarios.instancia.listar().stream().filter(unUsuario -> unUsuario.getNombre().equals(username)).findFirst();

        if(!usuario.isPresent() || !new GeneradorDeHash().getHash(password).equals(usuario.get().getHashPassword()))
            response.redirect("/login?error");
        else  {
            // nos logueamos
            request.session().attribute("idUsuario", usuario.get().getId());
            response.redirect("/");
        }

        return null;
    }

    public ModelAndView getFormularioLogin(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        modelo.put("error", request.queryParams().contains("error"));

        return new ModelAndView(modelo, "login.html.hbs");
    }

    public static Optional<Usuario> getUsuarioLogueado(Request request) {
        Long idUsuario = request.session().attribute("idUsuario");

        Optional<Usuario> usuario = Optional.empty();

        if(idUsuario != null){
            usuario = Optional.ofNullable(RepositorioUsuarios.instancia.getById(idUsuario));
        }

        return usuario;
    }
}
