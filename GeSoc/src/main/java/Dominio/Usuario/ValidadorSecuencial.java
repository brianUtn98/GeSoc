package Dominio.Usuario;
import java.util.regex.*;
public class ValidadorSecuencial implements ValidadorPassword{
    
	@Override
    public boolean esPasswordValida(String password) {
        return this.NoTieneSecuencias(password);
    }

    public boolean NoTieneSecuencias(String password){
    	String regex = "(.)\\1";
        Pattern patron=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher mach=patron.matcher(password);
        return !mach.find();
    }
}
