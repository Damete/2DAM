package Servicios.Criptografia;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class KeyFromText {
    public static void main(String[] args) {
        try {
           // Clau de xifrat a partir d'un text nostre
           byte[] clau_text = "patata".getBytes("UTF-8");
           
           
           SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
           SecretKey key2 = factory.generateSecret(new DESKeySpec(clau_text));
           System.out.println(key2);
           
           Cipher cipher = Cipher.getInstance("DES");
           cipher.init(Cipher.ENCRYPT_MODE, key2);
           
           byte[] missatge = "Missatge de prova".getBytes();
           byte[] missatgeXifrat = cipher.doFinal(missatge);
           System.out.println(new String(missatgeXifrat, "UTF8"));
           
           
           cipher.init(Cipher.DECRYPT_MODE, key2);
           
           //Desxifrat
           byte[] decipheredText = cipher.doFinal(missatgeXifrat);
           System.out.println(new String(decipheredText));
        } catch (Exception e) {
           e.printStackTrace();
        }
     }
}