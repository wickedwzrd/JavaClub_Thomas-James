/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclub;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author milnejam
 */
public class MainPage extends HttpServlet {

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
        // get the session *if* it exists.
        HttpSession session = request.getSession(false);

        String body;

        if (session != null) {

            String user = (String) session.getAttribute("user");
            if (user != null) {
                body = "<h1>Hi " + request.getParameter("user") + ". Loged in succefully to JavaClub</h1> ";
            } else {
                body = "<h3>User not in session.</h3>\n";
            }
        } else {
            body = "<h3>Session not set up.</h3>\n";
        }

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