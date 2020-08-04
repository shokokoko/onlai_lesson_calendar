package controllers.instructors;

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

import models.Instructor;
import models.validators.InstructorValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class InstructorsCreateServlet
 */
@WebServlet("/instructors/create")
public class InstructorsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorsCreateServlet() {
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

            Instructor i = new Instructor();

            i.setCode(request.getParameter("code"));
            i.setName(request.getParameter("name"));
            i.setTname(request.getParameter("tname"));
            i.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
            i.setMailAdress(request.getParameter("mailAdress"));
            i.setOfficialHP(request.getParameter("officialHP"));
            i.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            i.setCreated_at(currentTime);
            i.setUpdated_at(currentTime);
            i.setDelete_flag(0);

            List<String> errors = InstructorValidator.validate(i, true, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("instructor", i);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/instructors/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(i);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/instructors/index");
            }
        }
    }

}