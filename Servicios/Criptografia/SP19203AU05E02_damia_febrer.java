package Servicios.Criptografia;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SP19203AU05E02_damia_febrer {
    public static String pass;
    public static SecretKeySpec key;
    public static String fileContent;
    public static Cipher cipher;

    public static boolean cypherOrDecipher(String fileName) {
        boolean chyper = true;

        String[] partes = fileName.split("\\.");

        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equals("aes")) {
                chyper = false;
            }
        }
        return chyper;
    }

    public static void generateKey() {
        try {
            byte[] deTexto = pass.getBytes("UTF-8");

            key = new SecretKeySpec(deTexto, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFileContent(String filePath) {
        try {
            fileContent = new String(Files.readAllBytes(new File(filePath).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeEncripted(String filePath) {
        try {
            File antiguo = new File(filePath);
            String fileName = antiguo.getName();

            File fichero = new File(fileName + ".aes");
            String path = fichero.getPath();

            System.out.println("\nCifrando contenido del fichero");

            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] missatge = fileContent.getBytes();
            byte[] missatgeXifrat = cipher.doFinal(missatge);

            Files.write(new File(path).toPath(), missatgeXifrat);

            System.out.println("\nContenido cifrado con exito");
            System.out.println("\nEliminando el fichero " + fileName);
            antiguo.delete();
            System.out.println("\n" + fileName + "eliminado con exito");

        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void dechiperEncrypted(String filePath){
        File fichero = new File("descifrado.txt");
        String path = fichero.getPath();

        try{
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decipheredText = cipher.doFinal(fileContent.getBytes());
            System.out.println(decipheredText);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean iterate = true;

        while(iterate){
            System.out.println("Hola, estas son las opciones \n 1- Cifrar/descifrar un fichero\n 2- Salir");
            String respuesta = sc.nextLine();

            if(respuesta.equals("1")){
                System.out.println("\nIntroduzca la ruta del fichero que quiere cifrar/descifrar");
                String file = sc.nextLine();

                System.out.println("Introduzca la contraseña");
                pass = sc.nextLine();

                boolean cypher = cypherOrDecipher(file);

                if(cypher){
                    System.out.println("\nToca cifrar");
                    generateKey();
                    getFileContent(file);                    
                    writeEncripted(file);
                }
                else{
                    //Descifrar el fichero y mandar a la puta el .aes y convertirlo a un fichero normal
                    System.out.println("\nToca descifrar");
                    getFileContent(file);
                    dechiperEncrypted(file);
                }   

            }
            else if(respuesta.equals("2")){
                iterate = false;
            }
            else{
                System.out.println("La opción introducida es incorrecta, por favor vuelva a intentarlo");
            }
        }
    }
}