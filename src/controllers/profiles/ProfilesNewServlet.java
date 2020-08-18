package controllers.profiles;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Profile;

/**
 * Servlet implementation class ProfilesNewServlet
 */
@WebServlet("/profiles/new")
public class ProfilesNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilesNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Profile p = new Profile();
        request.setAttribute("profile", p);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/new.jsp");
        rd.forward(request, response);
    }

}