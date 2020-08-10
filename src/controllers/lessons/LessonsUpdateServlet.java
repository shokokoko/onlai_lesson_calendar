package controllers.lessons;

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

import models.Lesson;
import models.validators.LessonValidator;
import utils.DBUtil;

/**
 * Servlet implementation class LessonsUpdateServlet
 */
@WebServlet("/lessons/update")
public class LessonsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonsUpdateServlet() {
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

            Lesson l = em.find(Lesson.class, (Integer)(request.getSession().getAttribute("lesson_id")));

            l.setTitle(request.getParameter("title"));
            l.setContent(request.getParameter("content"));
            l.setTarget(request.getParameter("target"));
            l.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = LessonValidator.validate(l);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("report", l);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/lessons/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("lesson_id");

                response.sendRedirect(request.getContextPath() + "/lessons/index");
            }
        }
    }

}