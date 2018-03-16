package ser422.sneha.web.dao;

import ser422.sneha.web.model.Person;

public interface IPersonDAO {

    Person getPerson(String email);
    void addPerson(Person person);
    void updatePerson(Person person);

}
