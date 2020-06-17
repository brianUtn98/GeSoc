package Dominio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CreadorDeUsuario {
String nombre;
String hashPass;
TipoDeUsuario tipoDeUsuario;
GeneradorDeHash generador = new GeneradorDeHash();

    public CreadorDeUsuario(String _nombre) {
        nombre=_nombre;
    }

    //Usamos inyección de dependencias con los validadores, para que no acoplar esto al CreadorDeUsuario.
    public void crearPassword(String _passWord, List<ValidadorPassword> validadores){
        if(esValido(_passWord,validadores)){
            hashPass=this.generador.getHash(_passWord);
        }
        else
        {
            //tirar una excepción
            //Puse un enum mock que devuelve un mensaje genérico porque todavía no se cómo determinar qué validador me pinchó
            throw new PasswordInvalidaException(TipoErrorPassword.MOCK);

        }

    }

    public void setearTipoUsuario(TipoDeUsuario _tipoDeUsuario){
        this.tipoDeUsuario=_tipoDeUsuario;
    }

    public Usuario crearUsuario(){
        Usuario nuevoUsuario = new Usuario(this.nombre,this.hashPass,this.tipoDeUsuario);
        return nuevoUsuario;
    }

    public boolean esValido(String pass, List<ValidadorPassword> validadores){
        return validadores.stream().allMatch(validadorPassword -> validadorPassword.esPasswordValida(pass));
    }

    //Tuve que usar try - catch si o sí, porque el método getIntance de MessageDigest tira una exepción por si se le pasa un algoritmo inválido.

}
