package servlet;

import servlet.LogoutServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dan on 13.08.2017.
 */

@WebServlet("/Login")
public class anLogin extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jsonResponse;
        String username = (String) request.getSession().getAttribute(LogoutServlet.USERNAME);
        System.out.println("/isLogin : username in verificare:" + username);
        if (username != null) {
            jsonResponse = "{\"keyMessage\":\"Hello " + username + ". \"}";
        } else {
            jsonResponse = "{\"keyMessage\":\"You are not logged in. \"}";
        }
        returnJsonResponse(response, jsonResponse);
    }


    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }


}