package dataContainers;

import java.util.ArrayList;

/**
 * Created by peter on 1/27/16.
 */
public class CompanyInfoContainer {

    private ArrayList<CompanyInfo> companies;

    private CompanyInfo tempObject;
    private String tempValue;

    public CompanyInfoContainer(){
        companies = new ArrayList<CompanyInfo>();
    }
    public void writeTempObject(){
        if (tempObject != null)
            companies.add(tempObject);
    }
    public void createTemoObject(){
        tempObject = new CompanyInfo();
    }
    public ArrayList<CompanyInfo> getCompanies(){
        return companies;
    }

    public void setTempValue(String tempValue){
        this.tempValue = tempValue;
    }
    public String getTempValue(){
        return tempValue;
    }
    public void setTempCompanyName(String name){
        tempObject.setCompanyName(name);
    }
    public void setTempOrgNumber(String number){
        tempObject.setOrgNumber(number);
    }
    public void setTempAddress(String address){
        tempObject.setAddress(address);
    }
    public void setTempCity(String city){
        tempObject.setCity(city);
    }
    public void setTempCountry(String country){
        tempObject.setCountry(country);
    }

}
