package login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import forms.LoginForm;


/**
 * Servlet implementation class Login
 */
@WebServlet({"/login", "/logout"})
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private static final String VUE_CONNEXION = "/WEB-INF/login.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(request.getServletPath()) {
		
		case "/login":
			getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
			break;
			
		case "/logout":
			request.getSession().invalidate();
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		LoginForm form= new LoginForm(request);
		
		if(form.login()) {
			
			HttpSession session = request.getSession();
			session.setAttribute("isConnected", true);
			response.sendRedirect("list");
		}
		else {
			request.setAttribute("connexionFailed", form.getStatusMessage());
			getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
		}
	}


}
