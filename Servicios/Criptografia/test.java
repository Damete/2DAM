package Servicios.Criptografia;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

public class test {
    public static void main(String[] args) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // keygen.init Opcional
            //keygen.init(128);
            SecretKey key = keygen.generateKey();
            
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, key);
   
            byte[] missatge = "Missatge de prova ques es xifrarà amb AES".getBytes();
            System.out.println("Cifrando mensaje");
            byte[] missatgeXifrat = aesCipher.doFinal(missatge);
            System.out.println(new String(missatgeXifrat, "UTF8"));
   
            aesCipher.init(aesCipher.DECRYPT_MODE, key);
            System.out.println("\nDescifrando mensaje");
            byte[] decipheredText = aesCipher.doFinal(missatgeXifrat);
            System.out.println(new String(decipheredText));
         } catch (Exception e) {
            e.printStackTrace();
         }
    }
}