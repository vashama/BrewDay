package ser422.sneha.web.controller;

import ser422.sneha.web.dao.IPersonDAO;
import ser422.sneha.web.factory.PersonDAOFactory;
import ser422.sneha.web.model.Person;
import ser422.sneha.web.pipelines.FavouriteManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FavouriteServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

        String articleID = httpServletRequest.getParameter("AddasFavourite");

        System.out.println("Now u added this to ur fav list " + articleID);

        if (articleID == null) {
            httpServletResponse.sendError(418, "Missing Article ID");
            return;
        }

        String email = (String) httpServletRequest.getSession().getAttribute("email");
        if (email == null) {
            httpServletResponse.sendError(419, "No Authentication Present");
            return;
        }

        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
        try {
            Person person = personDAO.getPerson(email);
            boolean flag = FavouriteManager.addFavouriteArticle(person, articleID);

            if (!flag) {
                httpServletResponse.setHeader("valid", "It is already in your fav list");
                httpServletRequest.getRequestDispatcher("success.jsp").forward(httpServletRequest, httpServletResponse);
            }

            System.out.println("added to favourites list");
            httpServletResponse.setHeader("valid", "Added to your fav list");
            httpServletRequest.getRequestDispatcher("success.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(420, "Account not Found");
        }
    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        String articleIDAdd = httpServletRequest.getParameter("AddasFavourite");

        if (articleIDAdd == null ){
            String articleIDDelete = httpServletRequest.getParameter("DeleteFavourite");
            if (!articleIDDelete.isEmpty())
                doDelete(httpServletRequest, httpServletResponse);
        }

        if(articleIDAdd != null )
            doPost(httpServletRequest, httpServletResponse);

    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        String articleIDDelete = httpServletRequest.getParameter("DeleteFavourite");
        String email = (String) httpServletRequest.getSession().getAttribute("email");
        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
        Person person = personDAO.getPerson(email);

        boolean flag = FavouriteManager.deleteFavouriteArticle(person, articleIDDelete);

        if (flag) {
            httpServletResponse.setHeader("valid", "It is deleted from your fav list");
            httpServletRequest.getRequestDispatcher("success.jsp").forward(httpServletRequest, httpServletResponse);
        }
        else{
            httpServletResponse.setHeader("valid", "It is not present in your fav list");
            httpServletRequest.getRequestDispatcher("success.jsp").forward(httpServletRequest, httpServletResponse);

        }
    }
}