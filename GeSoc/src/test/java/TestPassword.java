import Dominio.Usuario.ValidadorLongitud;
import Dominio.Usuario.ValidadorPassword;
import Dominio.Usuario.ValidadorSecuencial;
import Dominio.Usuario.ValidadorTop10000;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPassword {
    @Test
    public void esPassIncluidaEnTop10000Usadas() {
        ValidadorTop10000 validador = new ValidadorTop10000();
        validador.agregarAlTop("holaMundo");
        validador.agregarAlTop("contrasenia");
        assertFalse(validador.esPasswordValida("holaMundo"));
    }
    
    @Test
    public void esPassNoIncluidaEnTop10000Usadas() {
        ValidadorTop10000 validador = new ValidadorTop10000();
        validador.agregarAlTop("holaMundo");
        validador.agregarAlTop("contrasenia");
        assertTrue(validador.esPasswordValida("FranquitoCrack"));
    }

    @Test
    public void passwordTiene2CaracteresConsecutivosRepetidos(){
        ValidadorPassword validador = new ValidadorSecuencial();
        assertFalse(validador.esPasswordValida("aa"));
        assertFalse(validador.esPasswordValida("chau$$"));
    }
    
    @Test
    public void passwordTieneCaracteresConsecutivosRepetidosEnSecuencia(){
        ValidadorPassword validador = new ValidadorSecuencial();
        assertFalse(validador.esPasswordValida("foooo"));
        assertFalse(validador.esPasswordValida("lalalaaallaaa"));
    }

    @Test
    public void passwordTiene3CaracteresConsecutivosRepetidos(){
        ValidadorPassword validador = new ValidadorSecuencial();
        assertFalse(validador.esPasswordValida("foooo"));
    }
    
    @Test
    public void passwordNoTieneCaracteresConsecutivosRepetidos(){
        ValidadorPassword validador = new ValidadorSecuencial();
        assertTrue(validador.esPasswordValida("hola"));
    }

    @Test
    public void passwordCumpleLongitudMinima(){
        ValidadorPassword validador = new ValidadorLongitud();
        assertTrue(validador.esPasswordValida("abcdefgh"));
    }
    
    @Test
    public void passwordNOCumpleLongitudMinima(){
        ValidadorPassword validador = new ValidadorLongitud();
        assertFalse(validador.esPasswordValida("hola"));
    }
}
