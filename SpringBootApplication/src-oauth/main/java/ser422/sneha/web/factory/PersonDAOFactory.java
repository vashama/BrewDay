package ser422.sneha.web.factory;

import ser422.sneha.web.dao.IPersonDAO;
import ser422.sneha.web.dao.PersonMemoryDAO;
import ser422.sneha.web.dao.PersonMongoDAO;

public class PersonDAOFactory {

    private static boolean inMemory = true;
    private static String host;
    private static String port;

    public static void initialize(boolean inMemory, String host, String port) {
        if(!inMemory){
            PersonDAOFactory.inMemory = false;
            PersonDAOFactory.host = host;
            PersonDAOFactory.port = port;
        }
    }
    public static IPersonDAO getPersonDAO(){
        if(inMemory)
            return new PersonMemoryDAO();
        return new PersonMongoDAO(host,port);
    }
}
