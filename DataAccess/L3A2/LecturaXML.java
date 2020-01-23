import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.util.ArrayList;

public class LecturaXML {

    ArrayList<String> codigoProductos = new ArrayList<String>();
    ArrayList<String> generoProductos = new ArrayList<String>();
    ArrayList<String> titulo = new ArrayList<String>();
    ArrayList<String> artista = new ArrayList<String>();
    ArrayList<String> autor = new ArrayList<String>();
    ArrayList<String> urlProductos = new ArrayList<String>();
    ArrayList<String> distribuidor = new ArrayList<String>();

    public void leerDocumento() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoria.newDocumentBuilder();
        Document documento = builder.parse(new File("new_stock.xml"));
    
        NodeList nodes = documento.getElementsByTagName("new_stock");
    
        for(int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
    
            Element elemento = (Element) node;

            System.out.println(elemento.getAttribute("music"));

            if(elemento.getElementsByTagName("music")){
                generoProductos.add(elemento.getAttribute("genre"));
                codigoProductos.add(elemento.getAttribute("cod"));
                titulo.add(elemento.getAttribute("title"));
                artista.add(elemento.getAttribute("artista"));
                urlProductos.add(elemento.getAttribute("url"));
            }
            else if(elemento.getElementsByTagName("book")){
                codigoProductos.add(elemento.getAttribute("cod"));
                titulo.add(elemento.getAttribute("title"));
                autor.add(elemento.getAttribute("author"));
                urlProductos.add(elemento.getAttribute("url"));
            }
            else if(elemento.getElementsByTagName("videogame")){
                codigoProductos.add(elemento.getAttribute("cod"));;
                title.add(elemento.getAttribute("title"));
                distribuidor.add(elemento.getAttribute("distributor"));
                urlProductos.add(elemento.getAttribute("url"));
            }
        }
    }    
}