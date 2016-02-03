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
        jaxbp.start();
    }

    public void start(){

        try{
            final JAXBContext jaxbContext = JAXBContext.newInstance(com.shortcv.ObjectFactory.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<Cv> jaxbel =  (JAXBElement<Cv>)jaxbUnmarshaller.unmarshal(fis);

            for (CvInfo cv : jaxbel.getValue().getCv()){
                System.out.println(cv.getFirstname());
                System.out.println(cv.getLastname());
                System.out.println(cv.getSsn());
                System.out.println(cv.getGender());
            }

        }catch(JAXBException e) {
            System.out.print("JAXBException: " + e.getMessage());
        }

    }
}
