import dataContainers.CompanyInfo;
import dataContainers.CompanyInfoContainer;
import dataContainers.Person;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by peter on 1/25/16.
 */
public class CompanyInfoHandler extends DefaultHandler {


    private CompanyInfoContainer companies;



    public CompanyInfoHandler(CompanyInfoContainer companies){
        this.companies = companies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);


        if (qName.equals("company:company")){
            //System.out.println("create");
            companies.createTemoObject();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        companies.setTempValue(new String(ch, start, length));


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (qName.equals("company:company")){
            companies.writeTempObject();
        }
        else if(qName.equals("company:companyName")){
            //System.out.println(companies.getTempValue());
            companies.setTempCompanyName(companies.getTempValue());
        }
        else if(qName.equals("company:orgNumber")){
            companies.setTempOrgNumber(companies.getTempValue());
        }
        else if(qName.equals("company:address")){
            companies.setTempAddress(companies.getTempValue());
        }
        else if(qName.equals("company:city")){
            companies.setTempCity(companies.getTempValue());
        }
        else if(qName.equals("company:country")){
            companies.setTempCountry(companies.getTempValue());
        }
        //System.out.println("end "+qName);
    }
}
