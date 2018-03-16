package ser422.sneha.web.pipelines;

import ser422.sneha.web.dao.IPersonDAO;
import ser422.sneha.web.factory.PersonDAOFactory;
import ser422.sneha.web.model.Person;

public class FavouriteManager {

    public static boolean addFavouriteArticle(Person person, String articleID) {
        //check if the article ID is already in fav list
        if(person.getFavArticleIDs().contains(articleID)){
            return false;
        }
        person.getFavArticleIDs().add(articleID);
        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
        personDAO.updatePerson(person);
        return true;
    }



    public static boolean deleteFavouriteArticle(Person person, String articleID) {
        //check if the article ID is already in fav list
        if(person.getFavArticleIDs().contains(articleID)){
            person.getFavArticleIDs().remove(articleID);
            IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
            personDAO.updatePerson(person);
            return true;
        }
        return false;
    }
}
