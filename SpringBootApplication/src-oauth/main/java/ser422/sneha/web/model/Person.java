package ser422.sneha.web.model;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private String email;
    private String password;
    private UserType userType;
    private List<String> favArticleIDs = new ArrayList<>();


    public List<String> getFavArticleIDs() {
        return favArticleIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!name.equals(person.name)) return false;
        if (!email.equals(person.email)) return false;
        if (!password.equals(person.password)) return false;
        if (userType != person.userType) return false;
        return favArticleIDs.equals(person.favArticleIDs);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userType.hashCode();
        result = 31 * result + favArticleIDs.hashCode();
        return result;
    }

    public void setFavArticleIDs(List<String> favArticleIDs) {
        this.favArticleIDs = favArticleIDs;
    }


    public Person(String name, String email, String password, UserType userType){
        this.name=name;
        this.email=email;
        this.password=password;
        this.userType=userType;

    }

    public Person(){}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) { this.userType = userType; }
}


