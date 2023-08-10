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
@WebServlet(name = "AdministrarUsuario", urlPatterns = {"/AdministrarUsuario"})
public class AdministrarUsuario extends HttpServlet {

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
        String error="",advertencia="",informacion="";
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
            if(sesion.getAttribute("informacion")!=null){
                informacion =sesion.getAttribute("informacion").toString();
                sesion.removeAttribute("informacion");
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
            if (!informacion.isEmpty()) {
                out.println("<div class=\"informacion\">"+ informacion +"</div>");
            }
            out.println("<h2>Administrar Usuario</h2>\n" +
"                <form action=\"ActualizarUsuario\" method=\"Post\">\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Nombre:</div>\n" +
"                        <input class=\"txtInput\" type=\"text\" name=\"nombre\" id=\"nombre\" value=\""+sesion.getAttribute("nombre").toString()+"\"/>\n" +
"                    </div>\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Edad:</div>\n" +
"                        <input class=\"txtInput\" type=\"text\" name=\"edad\" id=\"edad\" value=\""+sesion.getAttribute("edad").toString()+"\"/>\n" +
"                    </div>\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Sexo:</div>\n" +
"                        <input class=\"txtInput\" type=\"text\" name=\"sexo\" id=\"sexo\" value=\""+sesion.getAttribute("sexo").toString()+"\"/>\n" +
"                    </div>\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Estatura:</div>\n" +
"                        <input class=\"txtInput\" type=\"text\" name=\"estatura\" id=\"estatura\" value=\""+sesion.getAttribute("estatura").toString()+"\"/>\n" +
"                    </div>\n" +
"                    <div class=\"btnContent2\">\n" +
"                        <button class=\"btn2\" type=\"sudmit\">Actualizar</button>\n" +
"                    </div>\n" +
"                </form>\n" +
"               <div class=\"btnContent2\">\n" +
"                   <button class=\"btn2\" type=\"button\" onclick=\"location.href ='SesionUsuario'\">Atras</button>\n" +
"               </div>"+
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
