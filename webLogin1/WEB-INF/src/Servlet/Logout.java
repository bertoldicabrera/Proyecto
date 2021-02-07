package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class Logout extends HttpServlet {
 
    //Ya sea que el método sea por GET o POST, cerraremos la sesion.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
            {
    	response.setContentType("text/html;charset=UTF-8");
    	HttpSession session = request.getSession(true);
    	session.setAttribute("sessionNombre", null);
    	session.setAttribute("sessionEmail", null);
    	session.invalidate();
	    response.sendRedirect("index.jsp");
   
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}