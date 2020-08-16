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
 * Servlet implementation class InstructorsUpdateServlet
 */
@WebServlet("/instructor/update")
public class InstructorsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorsUpdateServlet() {
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

            Instructor i = em.find(Instructor.class, (Integer)(request.getSession().getAttribute("instructor_id")));

            // 現在の値と異なるユーザー名が入力されていたら
            // 重複チェックを行う指定をする
            Boolean code_duplicate_check = true;
            if(i.getCode().equals(request.getParameter("code"))) {
                code_duplicate_check = false;
            } else {
                i.setCode(request.getParameter("code"));
            }

            // パスワード欄に入力があったら
            // パスワードの入力値チェックを行う指定をする
            Boolean password_check_flag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                password_check_flag = false;
            } else {
                i.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("salt")
                                )
                        );
            }

            i.setName(request.getParameter("name"));
            i.setTname(request.getParameter("tname"));
            i.setMailAdress(request.getParameter("mailAdress"));
            i.setOfficialHP(request.getParameter("officialHP"));
            i.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
            i.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            i.setDelete_flag(0);

            i.setName(request.getParameter("tname"));

            String password2 = request.getParameter("password2");

            List<String> errors = InstructorValidator.validate(i, code_duplicate_check, password_check_flag, password2);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("instructor", i);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/instructors/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("instructor_id");

                response.sendRedirect(request.getContextPath() + "/instructors/index");
            }
        }
    }

}