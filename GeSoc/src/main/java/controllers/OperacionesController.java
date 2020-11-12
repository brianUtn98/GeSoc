package controllers;

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


}


