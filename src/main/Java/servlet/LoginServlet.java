package servlet;

import servlet.LogoutServlet;
import web.app.db.UserDBAccess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dan on 13.08.2017.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read user and password introduced by the user in the UI
        String user = request.getParameter("u");
        String passwd = request.getParameter("p");

        UserDBAccess udb = new UserDBAccess();
        int gasit;
        gasit = udb.isUserPwdOK(user, passwd);

        if (gasit != -1) {
            System.out.println("LOginServlet: user found");
            // userul exista in db, il autentific
            HttpSession session = request.getSession(true);
            session.setAttribute(LogoutServlet.USERNAME, user);
            session.setAttribute(LogoutServlet.USERNAMEID, gasit);

            response.sendRedirect("todolist.html");
        } else {
            System.out.println("LoginServlet: user/pwd NOT FOUND in db, retry a new one on the UI ");
            String back = "/login.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }


    }
}

