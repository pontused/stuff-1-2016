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
    Source xslSource;
    Source ss;
    FileOutputStream fos;


    public XslTransformer(String transformation, String input, String output){
        xslSource = new StreamSource(transformation);
        try {
            ss = new StreamSource(input);
            fos = new FileOutputStream(output);

        }catch(FileNotFoundException fnfe){

        }
    }

    public void start(){
        TransformerFactory tFactory = TransformerFactory.newInstance();

        try{
            Transformer transformer = tFactory.newTransformer(xslSource);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(ss, new StreamResult(fos));
        }catch(TransformerConfigurationException tce){

        }catch(TransformerException te){

        }


    }

}