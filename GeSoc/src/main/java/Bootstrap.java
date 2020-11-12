import Dominio.DireccionPostal;
import Dominio.Entidad.*;
import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Usuario.GeneradorDeHash;
import Dominio.Usuario.TipoAdministrador;
import Dominio.Usuario.TipoDeUsuario;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.Arrays;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public static void main(String[] args) {
        new Bootstrap().run();
    }

    public void run() {
        TipoDeUsuario tipoAdmin = new TipoAdministrador();
        withTransaction(() -> {
            persist(tipoAdmin);
            persist(new Usuario("jose55", new GeneradorDeHash().getHash("123456"), tipoAdmin, new BandejaDeMensajes()));
        });

        //Entidades persistidas para vizualizarlas por categoria
        TipoDeEntidad tipoEntidad = new Empresa(TipoEmpresa.MedianaTramo1);
        DireccionPostal direccion = new DireccionPostal("Calle 12","Argentina","Buenos Aires", "La Plata");
        CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG", Arrays.asList(new BloquearNuevosEgresos(50)));
        EntidadOrganizacional entidad = new EntidadJuridica("Cruz Roja", "Cruz Roja.", "30701258651", direccion, tipoEntidad, "jih5524", categoria);
        withTransaction(()->{
            persist(direccion);
            persist(categoria);
            persist(entidad);
        });

        DireccionPostal direccion1 = new DireccionPostal("Macacha Güemes 515","Argentina","Buenos Aires", "Ciudad de Buenos Aires");
        CategoriaDeEntidad categoria1 = new CategoriaDeEntidad("SA", Arrays.asList(new BloquearNuevosEgresos(50)));
        EntidadOrganizacional entidad1 = new EntidadJuridica("YPF", "YPF S.A.", "25865130701", direccion1, tipoEntidad, "5524jih", categoria);
        withTransaction(()-> {
            persist(direccion1);
            persist(categoria1);
            persist(entidad1);
        });

        EntidadOrganizacional entidad2 = new EntidadBase("Panaderia Luna","Elaboracion de panificados", categoria1);
        withTransaction(()->{
            persist(entidad2);
        });

        DireccionPostal direccion3 = new DireccionPostal("Calle 12","Argentina","Buenos Aires", "Ciudad de Buenos Aires");
        CategoriaDeEntidad categoria3 = new CategoriaDeEntidad("ONG", Arrays.asList(new BloquearNuevosEgresos(50)));
        EntidadOrganizacional entidad3 = new EntidadJuridica("Medicos Sin Fronteras", "Medicos Sin Fronteras", "123456789", direccion3, tipoEntidad, "jih5524", categoria3);
        withTransaction(()-> {
            persist(direccion3);
            persist(categoria3);
            persist(entidad3);
        });

        CategoriaDeEntidad categoria4 = new CategoriaDeEntidad("Industria Agropecuaria", Arrays.asList(new BloquearNuevosEgresos(50)));
        EntidadOrganizacional entidad4 = new EntidadBase("La huerta", "La huerta", categoria4);
        withTransaction(()-> {
            persist(categoria4);
            persist(entidad4);
        });
    }

}