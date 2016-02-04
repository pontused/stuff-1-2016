import com.applicationschema.Application;
import com.applicationschema.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Pontus on 2016-02-04.
 */
public class JAXBMarshaler {
    public JAXBMarshaler(){

    }

    public void start(Application ap){
        try{
            JAXBContext jaxbC = JAXBContext.newInstance(com.applicationschema.ObjectFactory.class);
            Marshaller jaxbM = jaxbC.createMarshaller();

            jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbM.marshal(ap, System.out);
            jaxbM.marshal(ap, new FileOutputStream("src/xml/application.xml"));
        }catch(JAXBException e){
            e.printStackTrace();
        }catch(FileNotFoundException e){

        }


    }
}
