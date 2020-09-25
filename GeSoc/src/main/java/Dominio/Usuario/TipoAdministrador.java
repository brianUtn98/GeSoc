package Dominio.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TA")
public class TipoAdministrador extends TipoDeUsuario{

}
