package ser422.sneha.web.datastore;


import ser422.sneha.web.model.Person;
import java.util.LinkedHashMap;
import java.util.Map;


public class UserData {
    public static Map<String, Person> credentialMap = new LinkedHashMap<>();

    public static Map<String, Person> getCredentialMap() {
        return credentialMap;
    }

    public static void setCredentialMap(Map<String, Person> credentialMap) {
        UserData.credentialMap = credentialMap;
    }

}


