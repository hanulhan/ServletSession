package hanulhan.session.ServletHttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String userID = "admin";
	private final String password = "password";
        private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

            try {
                // get request parameters for userID and password
                String user = request.getParameter("user");
                String pwd = request.getParameter("pwd");
                
                if (userID.equals(user) && password.equals(pwd)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", "Pankaj");
                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("user", user);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    response.sendRedirect("LoginSuccess.jsp");
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>Either user name or password is wrong.</font>");
                    rd.include(request, response);
                }
            } catch (IOException iOException) {
                LOGGER.log(Level.ERROR, iOException);
            } catch (ServletException servletException) {
                LOGGER.log(Level.ERROR, servletException);
            }

	}

}
