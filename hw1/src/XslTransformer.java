import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Pontus on 2016-02-03.
 */
public class XslTransformer {

    public static void main(String[] args){
        XslTransformer xsltr = new XslTransformer();
        xsltr.start();
    }

    public void start(){
        TransformerFactory tFactory = TransformerFactory.newInstance();

        Source xslSource = new StreamSource("src/TranscriptTransformation.xsl");
        try{
            Transformer transformer = tFactory.newTransformer(xslSource);

            FileOutputStream fos = new FileOutputStream("src/xml/Transcript_output.xml");


            transformer.transform(new StreamSource("src/xml/Transcript.xml"), new StreamResult(fos));
        }catch(TransformerConfigurationException tce){

        }catch(TransformerException te){

        }catch(FileNotFoundException fnfe){

        }


    }

}