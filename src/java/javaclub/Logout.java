/*
 * Names: Thomas Nevers/James Milne
 */
package javaclub;

import java.io.IOException;
import java.io.PrintWriter;
import javaclub.db.JdbcHelper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet {
   
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JdbcHelper helper = new JdbcHelper();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHeader(out, "Logout", "");
        out.println("<p>Goodbye.</p>");
        printFooter(out);
        
        helper.disconnect();
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