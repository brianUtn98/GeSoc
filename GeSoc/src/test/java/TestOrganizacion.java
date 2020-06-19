import Dominio.Entidad.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOrganizacion {
	
	
	
    @Test
    public void testTieneNombreFicticio() {
    	CategoriaDeEntidad categoria = new Empresa(TipoEmpresa.MedianaTramo1);
    	EntidadOrganizacional entidad = new EntidadJuridica("Pato feliz", "Patito S.A.", "30701258651", "calle falsa 123", categoria, "jih5524");
    	
    	assertTrue(entidad.getNombreFicticio().equals("Pato feliz"));
    }
}
