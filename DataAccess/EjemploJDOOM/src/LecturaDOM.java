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

public class LecturaDOM {

	

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		Document document = builder.parse(new File("productos.xml"));
		
		NodeList nodes = document.getElementsByTagName("productos");
		
		for (int i = 0; i<nodes.getLength(); i++) {
			Node node = nodes.item(i);
			
			Element element = (Element) node;
			
			System.out.println(element.getAttribute("codigo"));
			System.out.println(element.getElementsByTagName("nombre").item(0).getTextContent());
		}

	}

}
