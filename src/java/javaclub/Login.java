/*
 * Names: Thomas Nevers/James Milne
 */
package javaclub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javaclub.db.JdbcHelper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Object> params = new ArrayList<>();
        JdbcHelper helper = new JdbcHelper();
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String sql = "select id, password from User where id = ? and password = ?;";
        
        boolean connected = helper.connect("jdbc:mysql://localhost/test", "root", "");
        
        try {
            params.add(user);
            params.add(pass);
            ResultSet rs = helper.query(sql, params);
            
            while (rs.next()) {
                if (user.equals(rs.getString("id")) && pass.equals(rs.getString("password"))) {
                    // create a new session and set the "user" attribute
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);

                    request.setAttribute("connected", connected);
                    // send the request to the welcome servlet
                    RequestDispatcher rd = request.getRequestDispatcher("MainPage.do");
                    rd.forward(request, response);
                    return; // get out; we're done
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if we made it here. the credentials didn't match up.  
        // re-generate the login page
        String body = "You entered the wrong username and/or password";

        String css = "<style> .red { color:#f00; } </style>";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHeader(out, "Login", "");
        out.println(body);
        printFooter(out);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Helper method to print out the header HTML
     *
     * @param out the PrintWriter to print out to
     * @param title the title of the page
     * @param css any css that should be added
     */
    private void printHeader(PrintWriter out, String title, String css) {
        String header = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "<meta charset=\"utf-8\">\n"
                + "<title>"
                + title
                + "</title>\n"
                + css
                + "</head>\n"
                + "<body>\n";
        out.println(header);
    }

    /**
     * Helper method to print out the footer HTML
     *
     * @param out the PrintWriter to print out to
     */
    private void printFooter(PrintWriter out) {
        out.println("\n</body>\n</html>\n");
    }

}
