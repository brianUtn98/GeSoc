import Dominio.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCategoria {
    @Test
    public void testCategoria() {
    	assertFalse(CategoriaDeEntidad.OSC.esEmpresa());
    	assertTrue(CategoriaDeEntidad.Micro.esEmpresa());
    	assertTrue(CategoriaDeEntidad.MedianaTramo2.esEmpresa());
    }
}