package controllers.instructors;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Instructor;
import utils.DBUtil;

/**
 * Servlet implementation class InstructorsEditServlet
 */
@WebServlet("/instructors/edit")
public class InstructorsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Instructor i = em.find(Instructor.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("instructor", i);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("instructor_id", i.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/instructors/edit.jsp");
        rd.forward(request, response);
    }

}
