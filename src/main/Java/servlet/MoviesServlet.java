package servlet;

import org.json.JSONObject;
import web.app.db.DBAccess;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/movies")
public class MoviesServlet extends HttpServlet {

    private static final String ACTION = "action";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String REMOVE_ACTION = "remove";
    private static final String ID_TASK = "id";
    private static final String VALUE_NEWTASK = "value";

    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("list service called now.");

        HttpSession session = request.getSession(true);

        String username = (String) session.getAttribute(LogoutServlet.USERNAME);
        if (username != null) // nu esti logat
        {
            String action = request.getParameter(ACTION);

            if (action != null && action.equals(LIST_ACTION)) {
                listAction(request, response);
            } else if (action != null && action.equals(ADD_ACTION)) {
                addAction(request, response);
            } else if (action != null && action.equals(REMOVE_ACTION)) {
                removeAction(request, response);
            }

        } else {
            System.out.println("ListSdervlet: user not logged, so we do nothing...");
        }

    }


    private void listAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("list action");
        HttpSession session = request.getSession(true);

        int iduser = (Integer) session.getAttribute(LogoutServlet.USERNAMEID);
        // call db

        DBAccess db = new DBAccess();
        List<Movie> l = db.getMovies();

        JSONObject json = new JSONObject();
        json.put("items", l);

        returnJsonResponse(response, json.toString());
        System.out.println("end list action");
    }

    private void removeAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("enter pe done");

        HttpSession session = request.getSession(true);

        String idS = request.getParameter(ID_TASK);
        int id = Integer.parseInt(idS);

        DBAccess atl = new DBAccess();
        atl.markDone(id);


        System.out.println("i am done");
    }

    private void addAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("add action");

        HttpSession session = request.getSession(true);

        int iduser = (Integer) session.getAttribute(LogoutServlet.USERNAMEID);

        String name = request.getParameter("name");
        String year = request.getParameter("year");
        String date = request.getParameter("date");

        DBAccess atl = new DBAccess();
        atl.insertMovie(name, year, date);

        System.out.println("now I am done");

        try {
            response.sendRedirect("todolist.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
