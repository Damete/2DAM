import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class EscrituraDOM {

	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
		
		GeneradorDOM g = new GeneradorDOM();

		g.generarDocument();
		g.generarXML();
	}

}
