package org.simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.simplewebapp.beans.UserAccount;
import org.simplewebapp.utils.DBUtils;
import org.simplewebapp.utils.MyUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = { "/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");

dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String userName = request.getParameter("userName");
	        String password = request.getParameter("password");
	        String gender = request.getParameter("gender");
	 
	        UserAccount user = null;
	        boolean hasError = false;
	        String errorString = null;
	 
	        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
	            hasError = true;
	            errorString = "Required username and password!";
	        } else {
	            Connection conn = MyUtils.getStoredConnection(request);
	            try {
	                // Insert the user in the DB.
	                DBUtils.addUser(conn, userName, password, gender);
	 
	            } catch (SQLException e) {
	                e.printStackTrace();
	                hasError = true;
	                errorString = e.getMessage();
	            }
	        }
	        // If error, forward to /WEB-INF/views/login.jsp
	        if (hasError) {
	            
	            // Store information in request attribute, before forward.
	            request.setAttribute("errorString", errorString);
	 
	            // Forward to /WEB-INF/views/login.jsp
	            RequestDispatcher dispatcher //
	                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
	 
	            dispatcher.forward(request, response);
	        }
	        
	}

}
