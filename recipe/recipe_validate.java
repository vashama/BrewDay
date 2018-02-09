package credentials;

import javax.lang.model.type.NullType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class recipe_validate {

    public static void validateForm(String ID, String createdDate, String batchSize, String ABV, String instructions, String IBU, String boiltime,
                                      String brewtype,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws ServletException, IOException {

        if(ID == null){
            httpServletResponse.setHeader("invalid", " ID cannot be empty");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(createdDate == null){
            httpServletResponse.setHeader("invalid", " Please enter today's date");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(ABV==null){
            httpServletResponse.setHeader("invalid", " Please select ABV");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(batchSize==null){
            httpServletResponse.setHeader("invalid", " Please enter batch-size");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(instructions==null){
            httpServletResponse.setHeader("invalid", " Please give instructions about recipe");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(boiltime==null){
            httpServletResponse.setHeader("invalid", " Please mention boil time for brewing");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(brewtype==null){
            httpServletResponse.setHeader("invalid", " Please mention brewing type");
            httpServletRequest.getRequestDispatcher("recipeform.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }
        
    }
