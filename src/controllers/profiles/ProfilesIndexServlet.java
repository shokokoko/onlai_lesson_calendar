package controllers.profiles;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Profile;
import utils.DBUtil;

/**
 * Servlet implementation class ProfilesIndexServlet
 */
@WebServlet("/profiles/index")
public class ProfilesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception i) {
            page = 1;
        }
        List<Profile> profiles = em.createNamedQuery("getAllProfiles", Profile.class)
                                  .setFirstResult(10 * (page - 1))
                                  .setMaxResults(10)
                                  .getResultList();

        long profiles_count = (long)em.createNamedQuery("getProfilesCount", Long.class)
                                     .getSingleResult();

        em.close();

        request.setAttribute("profiles", profiles);
        request.setAttribute("profiles_count", profiles_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/index.jsp");
        rd.forward(request, response);
    }

}