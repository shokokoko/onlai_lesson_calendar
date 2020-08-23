package controllers.photo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


/**
 * Servlet implementation class PhotoUploadServlet
 */
@WebServlet("/upload")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class PhotoUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ServletRequestContext context = new ServletRequestContext(request);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            try {
                String saveDir = request.getParameter("login_instructor");
                List items = fileUpload.parseRequest(context);
                for (Object item : items) {
                    FileItem fitem= (FileItem)item;
                    String fileName = (new File(fitem.getName())).getName();
                    File file = new File(saveDir + "/" + fileName);
                    fitem.write(file);
                }
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/lessons/new.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
