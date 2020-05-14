package Dominio;
import java.util.regex.*;
public class ValidadorSecuencial implements ValidadorPassword{
    @Override
    public boolean esPasswordValida(String password) {
        return this.NoTieneSecuencias(password);
    }

    public boolean NoTieneSecuencias(String password){
        int longitud=password.length();
        String regex="";
        Pattern patron=Pattern.compile(regex);
        Matcher mach=patron.matcher(password);
        return mach.matches();
    }
}
