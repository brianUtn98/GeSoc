import Dominio.CorredorValidaciones;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class TareaValidar implements Job {
    public void execute(JobExecutionContext contexto) {
        System.out.println("Iniciando tarea de validacion"); //Esto no se va a quedar bajo ningun termino, solo nos sirve para probar por ahora!!!
        CorredorValidaciones corredorValidaciones = CorredorValidaciones.getInstance();
        System.out.println(String.format("Corriendo %d validaciones", corredorValidaciones.getOperacionesPendientes().size()));
        corredorValidaciones.validarPendientes();
    }
}
