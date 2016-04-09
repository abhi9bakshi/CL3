import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BoothServlet
 */
@WebServlet("/BoothServlet")
public class BoothServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Booth booth;
    private HttpSession session;

    public BoothServlet() {
        booth = new Booth();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        String result = booth.multiply(Integer.parseInt(num1),
                Integer.parseInt(num2));
        session.setAttribute("multiplyResponse", String.valueOf(result));

        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
