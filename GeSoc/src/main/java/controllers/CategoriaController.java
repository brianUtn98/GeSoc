package controllers;

import Dominio.Entidad.CategoriaDeEntidad;
import Dominio.Entidad.EntidadOrganizacional;
import Dominio.Entidad.RepositorioCategoriaDeEntidad;
import Dominio.Entidad.RepositorioEntidadOrganizacional;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaController implements WithGlobalEntityManager, TransactionalOps {



    public ModelAndView mostrarCategoria(Request request, Response response){

        Map<String, Object> parametros = new HashMap<String, Object>();
        List<CategoriaDeEntidad> categoriaDeEntidads = RepositorioCategoriaDeEntidad.instancia.listar();

        parametros.put("categorias", categoriaDeEntidads);
        return new ModelAndView(parametros, /*"entidadesCategoria.html.hbs"*/"categoriasBuscar.html.hbs");
    }

}
