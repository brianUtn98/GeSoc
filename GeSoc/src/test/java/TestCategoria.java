import Dominio.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCategoria {
    @Test
    public void testEsEmpresa() {
    	assertTrue(CategoriaDeEntidad.MedianaTramo2.esEmpresa());
    }
    
    @Test
    public void testNoEsEmpresa() {
    	assertFalse(CategoriaDeEntidad.OSC.esEmpresa());
    }
}