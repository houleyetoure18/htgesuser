package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.UtilisateurDao;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet( "/update" )
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_UPDATE_UTILISATEUR = "/WEB-INF/modifierUtilisateur.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		if(id != null && id.matches("[0-9]+")) {
			Utilisateur utilisateur = UtilisateurDao.get(Integer.parseInt(id));
			request.setAttribute("utilisateur", utilisateur);
			getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR).forward(request, response);
			return;
		} 
			response.sendRedirect("list");
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Utilisateur utilisateur = new Utilisateur( nom, prenom, login, password);
		UtilisateurDao.modifier(utilisateur);
		response.sendRedirect("list");
	}
	


}
