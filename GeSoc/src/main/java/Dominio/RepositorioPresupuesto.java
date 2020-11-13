package Dominio;

import java.util.List;

import Dominio.Presupuesto.Presupuesto;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioPresupuesto implements WithGlobalEntityManager{
    public static RepositorioPresupuesto instancia = new RepositorioPresupuesto();

    public List<Presupuesto> listar() {
        return entityManager()//
                .createQuery("from Presupuesto", Presupuesto.class) //
                .getResultList();
    }

    public Presupuesto getById(Long id){
        return entityManager().find(Presupuesto.class, id);
    }


    public void agregar(Presupuesto presupuesto) {
        entityManager().persist(presupuesto);
    }
}
