import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ModifyXML{
    private Document document;
	
	public GeneradorDOM() throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse("new_stock.xml");
	}
	
	public void generarDocument( ) {
        Node company = doc.getFirstChild();
        Node staff = doc.getElementsByTagName("staff").item(0);

		Element productos = document.createElement("productos");
		document.appendChild(productos);
		
		Element juego = document.createElement("videogame");
		productos.appendChild(videogame);
		juego.setAttribute("cod", "0001");
		
		Element title = document.createElement("title");
		title.appendChild(document.createTextNode("New Super Mario Bros"));
		juego.appendChild(title);
		
		Element distributor = document.createElement("distributor");
		distributor.appendChild(document.createTextNode("Nintendo"));
        juego.appendChild(distributor);
        
        Element url = document.createElement("url");
        url.appendChild(document.createTextNode("http://www.mariobros.com"));
        juego.appendChild(url);
        
	}
}