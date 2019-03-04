package usmb.nc.cgi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.*;

@Entity
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String last_name;
    private String first_name;
    private String birthday;
    private String adress;
    private String city;
    private String postcode;
    private String phone;
    private String email;
    private String city_birthday;
    private String no_assurance;
    private String category;
    private String position;
    private String last_name_other;
    private String first_name_other;
    private String phone_other;
    private String cote = "left";
    private String sexe = "woman";
    private String pathPdf;

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCity_birthday() {
        return city_birthday;
    }

    public void setCity_birthday(String city_birthday) {
        this.city_birthday = city_birthday;
    }

    public String getNo_assurance() {
        return no_assurance;
    }

    public void setNo_assurance(String no_assurance) {
        this.no_assurance = no_assurance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLast_name_other() {
        return last_name_other;
    }

    public void setLast_name_other(String last_name_other) {
        this.last_name_other = last_name_other;
    }

    public String getFirst_name_other() {
        return first_name_other;
    }

    public void setFirst_name_other(String first_name_other) {
        this.first_name_other = first_name_other;
    }

    public String getPhone_other() {
        return phone_other;
    }

    public void setPhone_other(String phone_other) {
        this.phone_other = phone_other;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathPdf() {
        return "/pdf/" + id + ".pdf";
    }

    public void setPathPdf(String pathPdf) {
        this.pathPdf = pathPdf;
    }

    public Form(){}

    public HashMap<String, String> getHashMap(){
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("last_name" , last_name);
        result.put("first_name" , first_name);
        result.put("birthday" , birthday);
        result.put("adress" , adress);
        result.put("city" , city);
        result.put("postcode" , postcode);
        result.put("phone" , phone);
        result.put("email" , email);
        result.put("city_birthday" , city_birthday);
        result.put("no_assurance" , no_assurance);
        result.put("category" , category);
        result.put("position" , position);
        result.put("last_name_other" , last_name_other);
        result.put("first_name_other" , first_name_other);
        result.put("phone_other" , phone_other);
        result.put("cote", cote);
        result.put("sexe", sexe);

        SimpleDateFormat formater = new java.text.SimpleDateFormat( "dd/MM/yy" );
        result.put("todays_date" , formater.format( new Date() ));
        return result;
    }
}
