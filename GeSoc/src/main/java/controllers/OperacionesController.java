package controllers;

import Dominio.Provedor;
import Dominio.RepositorioProveedor;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class OperacionesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView cargarEntidad(){
        return null;
    }


    public ModelAndView getFormularioOperaciones(Request request, Response response) {
        Map <String,Object> modelo = new HashMap<>();
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);
//        if(!usuarioLogueado.isPresent()){
//            response.redirect("/login");
//            return null;
//        }


        List<Provedor> proveedores = RepositorioProveedor.instancia.listar();

        modelo.put("proveedores", proveedores);



        return new ModelAndView(modelo,"operaciones.html.hbs");
    }
}


