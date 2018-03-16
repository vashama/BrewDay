package ser422.sneha.web.credentials;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpManager {

    //method to check if user exists


    //validate credentials (userId, pass)

    //validation for signup form

    public static void validateSignup(String name, String email, String password, String userRole,
                               HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {


        if(userRole == null){
            httpServletResponse.setHeader("invalid", " Select either reporter or subscriber");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }


        if(name.trim().isEmpty()){
            httpServletResponse.setHeader("invalid", "Name cannot be non empty");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            httpServletResponse.setHeader("invalid", "Name should contain only alphabets");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(email.trim().isEmpty()){
            httpServletResponse.setHeader("invalid", "Email cannot be non empty");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (!(email.length() >= 3 && email.length() <= 255 && email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
            httpServletResponse.setHeader("invalid", "email should be in a specified format eg: acb@yahoo.com");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(password.trim().isEmpty()){
            httpServletResponse.setHeader("invalid", "Password cannot be non empty ");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        /*if (!(password.matches("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})"))) {
            httpServletResponse.setHeader("invalid", "password should contain atleast 1 lowercase alphabet,1 uppercase alphabet, " +
                    "1 number 1 special character and length should be atleast 8 characters long");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }*/

        if(userRole.isEmpty()){
            httpServletResponse.setHeader("invalid", " Select either reporter or subscriber");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(userRole == null){
            httpServletResponse.setHeader("invalid", " Select either reporter or subscriber");
            httpServletRequest.getRequestDispatcher("SignUpPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }
    }

}
