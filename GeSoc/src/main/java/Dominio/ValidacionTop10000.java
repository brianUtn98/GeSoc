package Dominio;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class ValidacionTop10000 implements ValidadorPassword{

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
