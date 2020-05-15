import org.junit.Test;
import Dominio.*;

import static org.junit.Assert.*;

public class TestAlgo {
    @Test
    public void testCategoria() {

    	assertFalse(CategoriaDeEntidad.OSC.esEmpresa());
    	assertTrue(CategoriaDeEntidad.Micro.esEmpresa());
    	assertTrue(CategoriaDeEntidad.MedianaTramo2.esEmpresa());
    }
    @Test
    public void esPassInvalida(){
        ValidacionTop10000 validador=new ValidacionTop10000();
        validador.agregarAlTop("holaMundo");
        validador.agregarAlTop("contrasenia");
        assertFalse(validador.esPasswordValida("holaMundo"));
        assertFalse(validador.esPasswordValida("contrasenia"));
        assertTrue(validador.esPasswordValida("FranquitoCrack"));
    }

    @Test
    public void passwordTieneCaracteresConsecutivosRepetidos(){
        ValidadorSecuencial validador=new ValidadorSecuencial();
        assertTrue(validador.esPasswordValida("hola"));
        assertFalse(validador.esPasswordValida("aa"));
        assertFalse(validador.esPasswordValida("aaa"));
        assertFalse(validador.esPasswordValida("foooo"));
        assertFalse(validador.esPasswordValida("lalalaaallaaa"));
        assertFalse(validador.esPasswordValida("chau$$"));
    }

    @Test
    public void passwordLongitudMinma(){
        ValidadorPassword validador = new ValidadorLongitud();
        assertFalse(validador.esPasswordValida("hola"));
        assertTrue(validador.esPasswordValida("abcdefgh"));
    }
}