import Dominio.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCrearUsuario {
    @Test(expected = PasswordInvalidaException.class)
    public void contraseñaCorta () throws Exception{
        CreadorDeUsuario builder = new CreadorDeUsuario("Pepito Alcachofa");
        builder.setearTipoUsuario(new TipoEstandar());
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();
        ValidadorSecuencial validadorSecuencial = new ValidadorSecuencial();
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        validadores.add(validadorLongitud);
        validadores.add(validadorSecuencial);
        builder.crearPassword("corta",validadores);

    }

    @Test(expected = PasswordInvalidaException.class)
    public void contraseñaRepetitiva () throws Exception{
        CreadorDeUsuario builder = new CreadorDeUsuario("Pepito Alcachofa");
        builder.setearTipoUsuario(new TipoEstandar());
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();
        ValidadorSecuencial validadorSecuencial = new ValidadorSecuencial();
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        validadores.add(validadorLongitud);
        validadores.add(validadorSecuencial);
        builder.crearPassword("fooo",validadores);

    }

    @Test
    public void crearUsuarioCorrectamenteNombre(){
        CreadorDeUsuario builder = new CreadorDeUsuario("Esteban Quito");
        builder.setearTipoUsuario(new TipoAdministrador());
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();
        ValidadorSecuencial validadorSecuencial = new ValidadorSecuencial();
        validadores.add(validadorLongitud);
        validadores.add(validadorSecuencial);
        builder.crearPassword("ajbh#1jvk",validadores);
        builder.setearTipoUsuario(new TipoAdministrador());

        Usuario nuevoUsuario1 = builder.crearUsuario();

        Assert.assertEquals(nuevoUsuario1.getNombre(),"Esteban Quito");
    }

    @Test
    public void crearUsuarioCorrectamentePassWord(){
        CreadorDeUsuario builder = new CreadorDeUsuario("Esteban Quito");
        builder.setearTipoUsuario(new TipoAdministrador());
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();
        ValidadorSecuencial validadorSecuencial = new ValidadorSecuencial();
        validadores.add(validadorLongitud);
        validadores.add(validadorSecuencial);
        builder.crearPassword("ajbh#1jvk",validadores);
        builder.setearTipoUsuario(new TipoAdministrador());

        Usuario nuevoUsuario2 = builder.crearUsuario();

        Assert.assertEquals(nuevoUsuario2.getHashPassword(),"7d228e9a594b6250e1cd9ae42ab9d6df");
    }
}
