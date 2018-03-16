package ser422.sneha.web.pipelines;

import ser422.sneha.web.dao.IPersonDAO;
import ser422.sneha.web.datastore.UserData;
import ser422.sneha.web.factory.PersonDAOFactory;
import ser422.sneha.web.model.Article;
import ser422.sneha.web.model.Person;
import ser422.sneha.web.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static ser422.sneha.web.pipelines.ArticleSelector.*;

public class LoginProcessor {

    public static void addUser(String name, String email, String password, boolean isReporter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        PrintWriter out = httpServletResponse.getWriter();

        email = email.trim().toUpperCase();
        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
        Person person = personDAO.getPerson(email);
        if (person != null) {
            httpServletResponse.setHeader("invalid", "User already exists with this emailID ! \n Click on Login button");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.setContentType("text/html");
            if (isReporter) {
                person = new Person(name, email, password, UserType.REPORTER);
            } else {
                person = new Person(name, email, password, UserType.SUBSCRIBER);
            }
            personDAO.addPerson(person);
            out.println("<body><center>");
            out.println("<br>");
            out.println("<br>");

            out.println("new user added");
            out.println("<br>");
            out.println("<br>");

            out.println("Please Login now!!");
            out.println("<br>");
            out.println("<br>");

            out.println("<a href=\"" + httpServletRequest.getContextPath() + "\">Landing Page</a>");
            out.println("</center></body>");
        }
    }


    public static void checkUser(String email, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        email = email.trim().toUpperCase();
        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();
        Person person = personDAO.getPerson(email);

        if (person == null) {
            httpServletResponse.setHeader("invalid", "Email does not exist ! Please sign up..");
            httpServletRequest.getRequestDispatcher("/SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (!person.getPassword().equals(password)) {
            httpServletResponse.setHeader("invalid", "Incorrect Password..Please try again!");
            httpServletRequest.getRequestDispatcher("/LoginPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (person.getUserType().equals(UserType.SUBSCRIBER)) {
            redirectSubscriber(person, httpServletRequest, httpServletResponse);
            return;
        }

        if (person.getUserType().equals(UserType.REPORTER)) {
            redirectReporter(person, httpServletRequest, httpServletResponse);
            return;
        }
        httpServletResponse.sendError(500, "Unknown Server exception");

    }

    public static void redirectSubscriber(Person person, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("name", person.getName());
        session.setAttribute("email", person.getEmail());
        session.setAttribute("role", person.getUserType().toString());

        String emailid = (String) session.getAttribute("email");

        System.out.println("Email id is(before entering check user) " + emailid);

        Map<String, Article> articleMap = new LinkedHashMap<>();
        //ArticleSelector.loadAllArticleURI(articleMap, httpServletRequest.getContextPath());
        ArticleSelector.loadAllFavAndOtherArticleURI(articleMap, httpServletRequest.getContextPath(), emailid);

        httpServletRequest.setAttribute("articleMap", articleMap);
        httpServletRequest.getRequestDispatcher("SubscriberNewsPage.jsp").forward(httpServletRequest, httpServletResponse);
    }

    public static void redirectReporter(Person person, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("email", person.getEmail());
        session.setAttribute("name", person.getName());
        session.setAttribute("role", person.getUserType().toString());

        Map<String, Article> reporterArticleMap = new LinkedHashMap<>();
        loadReporterArticleURI(reporterArticleMap, httpServletRequest.getContextPath(), person.getEmail());

        Map<String, String> uriMap = new LinkedHashMap<>();
        ArticleSelector.loadPublicArticleURIs(uriMap, httpServletRequest.getContextPath());
        for(String reporterArticleKey : reporterArticleMap.keySet()){
            if(uriMap.containsKey(reporterArticleKey)){
                uriMap.remove(reporterArticleKey);
            }
        }

        httpServletRequest.setAttribute("publicArticleMap", uriMap);
        httpServletRequest.setAttribute("reporterArticleMap", reporterArticleMap);

        httpServletRequest.getRequestDispatcher("Reporterpage.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
