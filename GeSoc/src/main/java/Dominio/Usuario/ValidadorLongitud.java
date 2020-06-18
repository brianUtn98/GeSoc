package Dominio.Usuario;

public class ValidadorLongitud implements ValidadorPassword {
    final int LONGITUD_MIN = 8;

    @Override
    public boolean esPasswordValida(String password) {
        return this.tieneLongitudValida(password);
    }

    public boolean tieneLongitudValida(String password){
        return password.length() >= LONGITUD_MIN;
    }
}
