import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class L3A2_Main_Damia{
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, ParserConfigurationException, SAXException{
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        ModifyXML modificar = new ModifyXML();
        LecturaXML leer = new LecturaXML();
        createXML crear = new createXML();

        System.out.println("Elige la opción \n"
        + "1- Leer para guardar en BBDD \n" 
        + "2- Añadir elemento al fichero XML \n"
        + "3- Crear fichero XML \n"
        + "4- Salir \n");   
        String respuesta = sc.nextLine();

        while(loop){
            switch(respuesta){
                case "1":
                    leer.leerDocumento();
                    break;

                case "2":
                    break;

                case "3":
                    crear.generarDocument();
                    crear.generarXML();
                    break;

                case"4":
                    loop = false;
                    break;

                default:
                    System.out.println("El valor introducido es incorrecto, por favor vuelve a intentarlo");
                    break;
            }
        }
    }
}