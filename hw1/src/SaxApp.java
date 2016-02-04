import dataContainers.CompanyInfo;
import dataContainers.CompanyInfoContainer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by peter on 1/25/16.
 */
public class SaxApp {


    public ArrayList<CompanyInfo> companyInfoParser(String fileName) {
        ArrayList<CompanyInfo> companyInfos = new ArrayList<CompanyInfo>();

        try {
            File inputFile = new File(fileName);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CompanyInfoContainer container = new CompanyInfoContainer();
            CompanyInfoHandler companyInfoHandler = new CompanyInfoHandler(container);
            saxParser.parse(inputFile, companyInfoHandler);
            companyInfos = container.getCompanies();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return companyInfos;
    }
}
