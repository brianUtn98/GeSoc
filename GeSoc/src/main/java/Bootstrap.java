import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Usuario.GeneradorDeHash;
import Dominio.Usuario.TipoAdministrador;
import Dominio.Usuario.TipoDeUsuario;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

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
    }

}