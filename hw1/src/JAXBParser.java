import com.applicationschema.Application;

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

    FileInputStream fis;

    public JAXBParser(String path){
        try{
            fis = new FileInputStream(path);
        }catch(FileNotFoundException e){
            System.out.print("FileNotFoundException: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        JAXBParser jaxbp = new JAXBParser("src/xml/shortcv.xml");
        jaxbp.start(new Application());
    }

    public String start(Application ap){
        String ssn = "null";
        try{
            final JAXBContext jaxbContext = JAXBContext.newInstance(com.shortcv.ObjectFactory.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<Cv> jaxbel =  (JAXBElement<Cv>)jaxbUnmarshaller.unmarshal(fis);

            for (CvInfo cv : jaxbel.getValue().getCv()){
                ap.setFirstname(cv.getFirstname());
                ap.setLastname(cv.getLastname());
                ap.setLetter(cv.getLetter());
                ssn = cv.getSsn();
            }

        }catch(JAXBException e) {
            System.out.print("JAXBException: " + e.getMessage());
        }
        return ssn;
    }
}
