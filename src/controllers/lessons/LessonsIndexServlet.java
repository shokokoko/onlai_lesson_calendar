package controllers.lessons;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class LessonsIndexServlet
 */
@WebServlet("/lessons/index")
public class LessonsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Lesson> lessons = null;
        long lessons_count = 0;

        Instructor login_instructor = (Instructor)request.getSession().getAttribute("login_instructor");

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception i) {
            page = 1;
        }

        if(login_instructor.getAdmin_flag() == 1){
            lessons = em.createNamedQuery("getAllLessons", Lesson.class)
                    .setFirstResult(10 * (page - 1))
                    .setMaxResults(10)
                    .getResultList();

            lessons_count = (long)em.createNamedQuery("getLessonsCount", Long.class)
                       .getSingleResult();

        }else if(login_instructor.getAdmin_flag() == 0) {
            lessons = em.createNamedQuery("getMyAllLessons", Lesson.class)
                    .setParameter("instructor", login_instructor)
                    .setFirstResult(10 * (page - 1))
                    .setMaxResults(10)
                    .getResultList();

            lessons_count = (long)em.createNamedQuery("getMyLessonsCount", Long.class)
                       .setParameter("instructor", login_instructor)
                       .getSingleResult();
        }

        em.close();

        request.setAttribute("lessons", lessons);
        request.setAttribute("lessons_count", lessons_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/lessons/index.jsp");
        rd.forward(request, response);
    }

}
