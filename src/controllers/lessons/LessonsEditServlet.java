package controllers.lessons;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Instructor;
import models.Lesson;
import utils.DBUtil;

/**
 * Servlet implementation class LessonsEditServlet
 */
@WebServlet("/lessons/edit")
public class LessonsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Lesson l = em.find(Lesson.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Instructor login_instructor = (Instructor)request.getSession().getAttribute("login_instructor");
        if(l != null && login_instructor.getId() == l.getInstructor().getId()) {
            request.setAttribute("lesson", l);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("lesson_id", l.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/lessons/edit.jsp");
        rd.forward(request, response);
    }

}
