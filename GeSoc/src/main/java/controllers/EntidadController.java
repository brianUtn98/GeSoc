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
        List<EntidadOrganizacional> entidadesOrganizacionales = RepositorioEntidadOrganizacional.instancia.listar();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("entidades", entidadesOrganizacionales);
        return new ModelAndView(parametros, "entidadesCategoria.html.hbs");
    }

}
