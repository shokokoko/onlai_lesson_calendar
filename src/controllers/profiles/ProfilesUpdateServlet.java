package controllers.profiles;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Profile;
import models.validators.ProfileValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ProfilesUpdateServlet
 */
@WebServlet("/profiles/update")
public class ProfilesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Profile p = em.find(Profile.class, (Integer)(request.getSession().getAttribute("profile_id")));

            p.setContent(request.getParameter("content"));
            p.setMainprogram(request.getParameter("mainprogram"));
            p.setLanguage(request.getParameter("language"));
            p.setQualifications(request.getParameter("qualifications"));
            p.setSnsblog(request.getParameter("snsblog"));
            p.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = ProfileValidator.validate(p);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("profile", p);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("profile_id");

                response.sendRedirect(request.getContextPath() + "/profiles/index");
            }
        }
    }

}