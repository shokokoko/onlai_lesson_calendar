package controllers.profiles;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Instructor;
import models.Profile;
import utils.DBUtil;

/**
 * Servlet implementation class ProfilesEditServlet
 */
@WebServlet("/profiles/edit")
public class ProfilesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Profile p = em.find(Profile.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Instructor login_instructor = (Instructor)request.getSession().getAttribute("login_instructor");
        if(p != null && login_instructor.getId() == p.getInstructor().getId()) {
            request.setAttribute("profile", p);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("profile_id", p.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/edit.jsp");
        rd.forward(request, response);
    }

}