package ser422.sneha.web.dao;

import ser422.sneha.web.datastore.UserData;
import ser422.sneha.web.model.Person;

import static ser422.sneha.web.datastore.UserData.*;

public class PersonMemoryDAO implements IPersonDAO {

    public Person getPerson(String email) {
        return getCredentialMap().get(email.trim().toUpperCase());
    }

    public void addPerson(Person person) {
        synchronized (UserData.credentialMap) {
            UserData.credentialMap.put(person.getEmail(), person);
        }
    }
    public void updatePerson(Person person) {
        synchronized (UserData.credentialMap) {
            UserData.credentialMap.put(person.getEmail(), person);
        }
    }
}
