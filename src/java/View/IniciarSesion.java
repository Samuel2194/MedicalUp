/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error="",advertencia="";
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion=request.getSession();
        Enumeration enumeration = sesion.getAttributeNames();
        if(enumeration.hasMoreElements()){
            if(sesion.getAttribute("error")!=null){
                error= sesion.getAttribute("error").toString();
                sesion.removeAttribute("error");
            }
            if(sesion.getAttribute("advertencia")!=null){
                advertencia =sesion.getAttribute("advertencia").toString();
                sesion.removeAttribute("advertencia");
            }
            //session.removeAttribute("nombre");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"css/SiteStyle.css\" rel=\"stylesheet\" type=\"text/css\"/>"
                    + "<title>Medical Up</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"content\">\n" +
"            <h1>Medical UP</h1>\n" +
"            <div class=\"content2\">");
            if (!advertencia.isEmpty()) {
                out.println("<div class=\"advertencia\">"+ advertencia +"</div>");
            }
            if (!error.isEmpty()) {
                out.println("<div class=\"error\">"+ error +"</div>");
            }
            out.println("<h2>Iniciar Sesion</h2>\n" +
"                <form action=\"BuscarUsuario\" method=\"Post\">\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Usuario:</div>\n" +
"                        <input class=\"txtInput\" type=\"text\" name=\"usuario\" id=\"usuario\"/>\n" +
"                    </div>\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Estatura:</div>\n" +
"                        <input class=\"txtInput\" type=\"password\" name=\"contrasena\" id=\"contrasena\" />\n" +
"                    </div>\n" +
"                    <div class=\"btnContent2\">\n" +
"                        <button class=\"btn2\" type=\"sudmit\">Iniciar Sesion</button>\n" +
"                    </div>\n" +
"                </form>\n" +
"            </div>\n" +
"        </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

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
        processRequest(request, response);
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

}
