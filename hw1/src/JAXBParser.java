import com.shortcv.Cv;
import com.shortcv.CvInfo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Pontus on 2016-02-03.
 */
public class JAXBParser {
    public static void main(String[] args){
        JAXBParser jaxbp = new JAXBParser();
        jaxbp.start();
    }

    public void start(){

        try{
            final JAXBContext jaxbContext = JAXBContext.newInstance(com.shortcv.ObjectFactory.class);
            FileInputStream fis = new FileInputStream("src/xml/shortcv.xml");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<Cv> jaxbel =  (JAXBElement<Cv>)jaxbUnmarshaller.unmarshal(fis);

            for (CvInfo cv : jaxbel.getValue().getCv()){
                System.out.println(cv.getFirstname());
                System.out.println(cv.getLastname());
                System.out.println(cv.getSsn());
                System.out.println(cv.getGender());
            }

        }catch(JAXBException e){
            System.out.print("JAXBException: " + e.getMessage());
        }catch(FileNotFoundException e){
            System.out.print("FileNotFoundException: " + e.getMessage());
        }

    }
}
