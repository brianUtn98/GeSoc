import Dominio.DireccionPostal;
import Dominio.Entidad.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOrganizacion {
	
	
	
    @Test
    public void testTieneNombreFicticio() {
    	TipoDeEntidad categoria = new Empresa(TipoEmpresa.MedianaTramo1);
    	DireccionPostal direccion = new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal");
    	EntidadOrganizacional entidad = new EntidadJuridica("Pato feliz", "Patito S.A.", "30701258651", direccion, categoria, "jih5524");
    	
    	assertTrue(entidad.getNombreFicticio().equals("Pato feliz"));
    }
}
