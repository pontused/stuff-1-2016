import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Created by peter on 1/25/16.
 */
public class SaxApp {




    private void educationParser(){
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            //saxParser.parse(new File(XMLFileName), handler);
        }catch (ParserConfigurationException e){

        }catch (SAXException e){

        }


    }
}
