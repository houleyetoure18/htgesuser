 package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UtilisateurDao;

/**
 * Servlet implementation class ListUser
 */
@WebServlet({"", "/list"})
public class ListUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_LIST_UTILISATEUR = "/WEB-INF/listerUtilisateur.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("utilisateurs", UtilisateurDao.lister());
		getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response); 
	}

}
