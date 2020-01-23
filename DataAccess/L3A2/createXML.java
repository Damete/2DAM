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

public class createXML{
    private Document document;
	
	public GeneradorDOM() throws ParserConfigurationException {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		document = builder.newDocument();
	}
	
	public void generarDocument() {
		Element new_stock = document.createElement("new_stock");
        document.appendChild(new_stock);
        
        Element date = document.createElement("date");
        new_stock.appendChild(date);
        date.appendChild(document.createTextNode("test"));        

        Element book = document.createElement("book");
        new_stock.appendChild(book);

        Element titleB = document.createElement("title");
        titleB.appendChild(document.createTextNode("Java Tutorial"));
        book.appendChild(titleB);

        Element Author = document.createElement("author");
        Author.appendChild(document.createTextNode("John Sealmont"));
        book.appendChild(Author);

        Element urlB = document.createElement("url");
        urlB.appendChild(document.createTextNode("www.javalearning.org"));
        book.appendChild(urlB);

        Element music = document.createElement("music");
        new_stock.appendChild(music);

        Element genre = document.createElement("genre");
        genre.appendChild(document.createTextNode("pop"));
        music.appendChild(genre);

        Element title = document.createElement("title");
        title.appendChild(document.createTextNode("Lover"));
        music.appendChild(title);

        Element artist = document.createElement("artist");
        artist.appendChild(document.createTextNode("Taylor Swift"));
        music.appendChild(artist);

        Element url = document.createElement("url");
        url.appendChild(document.createTextNode("www.taylorswift.com"));
        music.appendChild(url);
	}
	
	public void generarXML() throws IOException, TransformerException {
		
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = factoria.newTransformer();
		
		Source source = new DOMSource(document);
		File file = new File("new_stock2.xml");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		Result result = new StreamResult(pw);
		
		transformer.transform(source, result);
	}
}