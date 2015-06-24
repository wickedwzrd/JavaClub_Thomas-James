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

/**
 *
 * @author milnejam
 */
public class Signup extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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