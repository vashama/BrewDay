package basicpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public myServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("Hello from myServlet GET method!!"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		foodTruck ft = new foodTruck(); 
		
		//response.getWriter().println("Hello from myServlet POST method!!");
		String ID = request.getParameter("id");
		//response.getWriter().println("ID: " + ID);
		
		String pd = request.getParameter("lastPurchasedDate");
		//response.getWriter().println("Last Purchase Date: " + pd);
		
		String amount = request.getParameter("amount");
		//response.getWriter().println("Amount: " + amount);
		
		String in = request.getParameter("ingredientName");
		//response.getWriter().println("Ingredient Name: " + in);
		
		String it = request.getParameter("ingredientType");
		//response.getWriter().println("Ingredient Type: " + it);
		
		response.getWriter().println(ft.getData(ID, pd, amount, it, in));
	}
}