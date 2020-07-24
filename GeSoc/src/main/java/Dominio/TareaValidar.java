package Dominio;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
public class TareaValidar implements Job {

    public void execute(JobExecutionContext contexto) throws JobExecutionException{
        System.out.println("Iniciando tarea de validacion"); //Esto no se va a quedar bajo ningun termino, solo nos sirve para probar por ahora!!!
        CorredorValidaciones corredorValidaciones = CorredorValidaciones.getInstance();
        corredorValidaciones.validarPendientes();
    }
}
