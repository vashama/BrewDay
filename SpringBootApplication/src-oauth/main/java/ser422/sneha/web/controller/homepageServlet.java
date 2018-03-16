package ser422.sneha.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ser422.sneha.web.dao.IPersonDAO;
import ser422.sneha.web.factory.PersonDAOFactory;
import ser422.sneha.web.model.Person;
import ser422.sneha.web.model.UserType;
import ser422.sneha.web.pipelines.ArticleSelector;
import ser422.sneha.web.pipelines.LoginProcessor;


public class homepageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException{

        Map<String, String> uriMap = new HashMap<>();
        String email = (String) httpServletRequest.getSession().getAttribute("email");
        if(email != null){
            IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
            Person person = personDAO.getPerson(email);
            if(person == null){
                httpServletResponse.setHeader("invalid", "Session is invalid");
                httpServletRequest.getSession().invalidate();
                httpServletRequest.getRequestDispatcher("/SignUpServlet");
                return;
            }
            if(person.getUserType().equals(UserType.REPORTER)){
                LoginProcessor.redirectReporter(person, httpServletRequest, httpServletResponse);
                return;
            }
            if(person.getUserType().equals(UserType.SUBSCRIBER)){
                LoginProcessor.redirectSubscriber(person, httpServletRequest, httpServletResponse);
                return;
            }
            httpServletResponse.sendError(500, "Unknown Server Exception Occured");

        }else {
            ArticleSelector.loadPublicArticleURIs(uriMap, httpServletRequest.getContextPath());
            httpServletRequest.setAttribute("articleURIMap", uriMap);
            httpServletRequest.getRequestDispatcher("homepage.jsp").forward(httpServletRequest, httpServletResponse);
        }

    }
    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException{
        doGet(httpServletRequest, httpServletResponse);

    }

}
