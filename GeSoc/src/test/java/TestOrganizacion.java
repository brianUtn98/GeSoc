import Dominio.DireccionPostal;
import Dominio.Entidad.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOrganizacion {
	
	
	
    @Test
    public void testTieneNombreFicticio() {
    	TipoDeEntidad tipo = new Empresa(TipoEmpresa.MedianaTramo1);
    	DireccionPostal direccion = new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal");
    	CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG");
    	EntidadOrganizacional entidad = new EntidadJuridica("Pato feliz", "Patito S.A.", "30701258651", direccion, tipo, "jih5524", categoria);
    	
    	assertTrue(entidad.getNombreFicticio().equals("Pato feliz"));
    }
}
