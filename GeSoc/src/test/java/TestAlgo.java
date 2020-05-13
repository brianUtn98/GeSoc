import org.junit.Test;
import Dominio.*;

import static org.junit.Assert.*;

public class TestAlgo {
    @Test
    public void testPrenda() {
        assertEquals(5, 5);
    }
    
    @Test
    public void testCategoria() {
    	assertFalse(CategoriaDeEntidad.OSC.esEmpresa());
    	assertTrue(CategoriaDeEntidad.Micro.esEmpresa());
    	assertTrue(CategoriaDeEntidad.MedianaTramo2.esEmpresa());
    }
}