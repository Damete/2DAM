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

public class GeneradorDOM {

	private Document document;
	
	public GeneradorDOM() throws ParserConfigurationException {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		document = builder.newDocument();
	}
	
	public void generarDocument( ) {
		Element productos = document.createElement("productos");
		document.appendChild(productos);
		
		Element producto = document.createElement("producto");
		productos.appendChild(producto);
		producto.setAttribute("codigo", "001");
		
		Element nombre = document.createElement("nombre");
		nombre.appendChild(document.createTextNode("Televisión"));
		producto.appendChild(nombre);
		
		Element precio = document.createElement("precio");
		precio.appendChild(document.createTextNode("100.000.000.000.000.000.000.000.000,99 €"));
		producto.appendChild(precio);
	}
	
	public void generarXML() throws IOException, TransformerException {
		
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = factoria.newTransformer();
		
		Source source = new DOMSource(document);
		File file = new File("productos.xml");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		Result result = new StreamResult(pw);
		
		transformer.transform(source, result);
	}
}
