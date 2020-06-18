package Dominio.Usuario;

public class PasswordInvalidaException extends RuntimeException{
    public PasswordInvalidaException(TipoErrorPassword tipo){
        super(tipo.getError());
    }
}
