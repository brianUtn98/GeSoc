package Dominio.Usuario;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_usuario")
public abstract class TipoDeUsuario {
//A futuro tendrá métodos, pero actualmente es una interface vacía.
    //Actualmente esto podría ser un enum, pero estamos bastante seguros de que vamos a tener comportamiento que puede ser representado mejor por una clase.
	@Id
	@GeneratedValue
	private long tipousuario_id;	
}
