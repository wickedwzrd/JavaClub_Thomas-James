/*
 * Names: Thomas Nevers/James Milne
 */
package javaclub;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javaclub.db.UserDb;

public class Signup extends HttpServlet {
    UserDb db = new UserDb();
    User u;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String passwd = request.getParameter("pwd");
        String passwd1 = request.getParameter("pwd_conf");
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String email = request.getParameter("email");
        
        PrintWriter out = response.getWriter();
        printHeader(out, "Signup", "");
        if (!passwd.equals(passwd1)) {
            out.println("<p>Passwords do not match!</p>");
        } else {
            u = new User(user, passwd, fName, lName, email);
            
            if (u.getId().equals(db.getUser(user))) {
                out.println("<p>User with id " + user +" already exsists</p>");
            } else {
                db.addUser(u);
                out.println("<p>Signed up succesfully. Thank you for joining JavaClub</p>");
                out.println("<br><p><a href=\"index.html\">Go to Home Page</a</p>");
            }
        }
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