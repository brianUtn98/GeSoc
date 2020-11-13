package controllers;

import Dominio.Entidad.*;
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

public class EntidadController implements WithGlobalEntityManager, TransactionalOps {



    public ModelAndView mostrarEntidadCategoria(Request request, Response response){
        String categoriaBuscada = request.queryParams("filtro");

        Map<String, Object> parametros = new HashMap<String, Object>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        parametros.put("usuario", usuarioLogueado.get());

        List<EntidadOrganizacional> entidadesOrganizacionales =
                categoriaBuscada != null ?
                        RepositorioEntidadOrganizacional.instancia.buscarPorCategoria(categoriaBuscada):
                        RepositorioEntidadOrganizacional.instancia.listar();;

        parametros.put("entidades", entidadesOrganizacionales);
        /*"entidadesCategoria.html.hbs"*//*"categorias.html.hbs"*/
        return new ModelAndView(parametros,"categoriasBuscar.html.hbs");
    }

}
