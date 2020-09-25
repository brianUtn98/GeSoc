package Dominio.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TS")
public class TipoEstandar extends TipoDeUsuario{
}