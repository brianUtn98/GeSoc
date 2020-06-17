package Dominio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneradorDeHash {

    public String getHash(String password) {
        String passHash = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger num = new BigInteger(1,messageDigest);
            passHash = num.toString(16);

            while(passHash.length() < 32){
                passHash = "0" + passHash;
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passHash;

    }

}
