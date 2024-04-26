package cn.edu.xcu.servlet.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/test")
public class HelloServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("servlet init ...");
        String test = config.getInitParameter("test");
        System.out.println(test);
    }
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("servlet destroy ...");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        if (req.getRequestURI().indexOf("second.do")>0){
            req.setAttribute("username","zhangsan");
            req.getRequestDispatcher("/WEB-INF/test.jsp").forward(req,resp);
            return;
        }
        resp.getWriter().println(req.getRequestURI());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        super.doPost(req, resp);
    }
}
