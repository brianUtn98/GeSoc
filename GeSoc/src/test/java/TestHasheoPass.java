import Dominio.Usuario.GeneradorDeHash;
import org.junit.Assert;
import org.junit.Test;

public class TestHasheoPass {
    @Test
    public void hasheoHolaMundo(){
        GeneradorDeHash unGenerador = new GeneradorDeHash();
        Assert.assertEquals("f88ee7ba5dbb9c3206a0bfcef9b80f78",unGenerador.getHash("holaMundo"));
    }
    @Test
    public void hasheo1234(){
        GeneradorDeHash unGenerador = new GeneradorDeHash();
        Assert.assertEquals("81dc9bdb52d04dc20036dbd8313ed055",unGenerador.getHash("1234"));
    }
}
