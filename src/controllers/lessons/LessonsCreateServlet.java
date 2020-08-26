package controllers.lessons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import models.Instructor;
import models.Lesson;
import models.validators.LessonValidator;
import utils.DBUtil;

/**
 * Servlet implementation class LessonsCreateServlet
 */
@WebServlet("/lessons/create")
public class LessonsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = "";
        EntityManager em = DBUtil.createEntityManager();

        Lesson l = new Lesson();

        String filePath = "";
        ServletRequestContext context = new ServletRequestContext(request);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(context);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();// text1
                    String charge = item.getString();
                    switch (name) {
                    case "_token":
                        _token = item.getString();
                        break;
                    case "title":
                        l.setTitle(item.getString());
                        break;
                    case "content":
                        l.setContent(item.getString());
                        break;
                    case "required_time":
                        l.setRequired_time(Integer.parseInt(item.getString()));
                        break;
                    case "application":
                        l.setApplication(item.getString());
                        break;
                    case "charge":
                        if( charge != null && !(charge.equals("")) ) {
                            l.setCharge(Integer.parseInt(charge));
                        }
                        break;
                    case "target":
                        l.setTarget(item.getString());
                        break;
                    case "detail":
                        l.setDetail(item.getString());
                        break;
                    case "notes":
                        l.setNotes(item.getString());
                        break;
                    }
                } else {
                    String saveDir = "C:/Users/miamf";   //ファイルを保存するフォルダに書き換えてください。
                    FileItem fitem = (FileItem) item;
                    String fileName = (new File(fitem.getName())).getName();
                    filePath = saveDir + "/" + fileName;
                    InputStream in = null;
                    FileOutputStream out = null;

                    byte[] buf = new byte[5120];

                    try {
                        in = item.getInputStream();
                        out = new FileOutputStream(filePath);

                        // ストリームの最後まで読み込む
                        while (in.read(buf) != -1) {
                            out.write(buf);
                        }
                    } finally {
                        if (in != null) { in.close(); }
                        if (out != null) { out.close(); }
                    }
                }
            }
        } catch (Exception e) {
            filePath = "";
            e.printStackTrace();
        }

        l.setInstructor((Instructor) request.getSession().getAttribute("login_instructor"));
        l.setThumbnail(filePath);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        l.setCreated_at(currentTime);
        l.setUpdated_at(currentTime);

        String check = request.getParameter("title");

        if (_token != null && _token.equals(request.getSession().getId())) {
            List<String> errors = LessonValidator.validate(l);
            if (errors.size() > 0) {
                em.close();
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("lesson", l);
                request.setAttribute("errors", errors);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/lessons/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(l);
                //System.out.println("デバック");
                //System.out.println(request.getParameter("title"));
                //System.out.println(l.getTitle());
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                response.sendRedirect(request.getContextPath() + "/lessons/index");
            }
        }
    }
}