package Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

//TODO ahora que persistimos, podríamos pensar en tener el top10k en la bd para no tener una lista de 10k pass en memoria todo el tiempo.

public class ValidadorTop10000 implements ValidadorPassword{
    //¿Podría ser un singleton?
    List<String> top10000=new ArrayList<String>();
    @Override
    public boolean esPasswordValida(String password) {

        return !(this.estaEnTop(password));
    }

    public boolean estaEnTop(String password){

    return top10000.stream().anyMatch(pass -> pass==password);
    }

    public void agregarAlTop(String password){
        top10000.add(password);
    }
}
