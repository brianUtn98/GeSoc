package controllers;

import Dominio.Entidad.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadController implements WithGlobalEntityManager, TransactionalOps {



    public ModelAndView mostrarEntidadCategoria(Request request, Response response){
        String categoriaBuscada = request.queryParams("filtro");

        Map<String, Object> parametros = new HashMap<String, Object>();

        List<EntidadOrganizacional> entidadesOrganizacionales =
                categoriaBuscada != null ?
                        RepositorioEntidadOrganizacional.instancia.buscarPorCategoria(categoriaBuscada):
                        RepositorioEntidadOrganizacional.instancia.listar();;

        parametros.put("entidades", entidadesOrganizacionales);
        /*"entidadesCategoria.html.hbs"*//*"categorias.html.hbs"*/
        return new ModelAndView(parametros,"categoriasBuscar.html.hbs");
    }

}
