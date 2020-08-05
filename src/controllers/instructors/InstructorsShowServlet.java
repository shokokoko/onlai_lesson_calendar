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
 * Servlet implementation class InstructorsShowServlet
 */
@WebServlet("/instructors/show")
public class InstructorsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorsShowServlet() {
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

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/instructors/show.jsp");
        rd.forward(request, response);
    }

}