/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package View;

import Model.DataBase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "VerIMC", urlPatterns = {"/VerIMC"})
public class VerIMC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DataBase db = new DataBase();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String error="",advertencia="",informacion="";
        float imc=0;
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
        response.setContentType("text/html;charset=UTF-8");
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
            String rs = db.VerPesos(Integer.parseInt(sesion.getAttribute("idUsuario").toString()));
            out.println("<h2>Mi IMC</h2>\n" +
"                <form action=\"AgregarPeso\" method=\"Post\">\n" +
"                    <div class=\"formControl\">\n" +
"                        <div class=\"lb\">Peso:</div>\n" +
"                        <input class=\"txtInput\" type=\"text\" name=\"peso\" id=\"peso\"\"/>\n" +
"                    </div>\n" +
"                    <div class=\"btnContent2\">\n" +
"                        <button class=\"btn2\" type=\"sudmit\">Agregar Peso</button>\n" +
"                    </div>\n" +
"                </form>\n" +
"               <div class=\"btnContent2\">\n" +
"                   <button class=\"btn2\" type=\"button\" onclick=\"location.href ='SesionUsuario'\">Atras</button>\n" +
"               </div>"+
"              <table>"
                    + "<tr>"
                        + "<th>Peso</th>"
                        + "<th>Estatura</th>"
                        + "<th>IMC</th>"
                        + "<th>Fecha</th>"
                    + "</tr>");
//            while(rs.next()){
////                imc =rs.getFloat("Peso")/rs.getFloat("Estatura");
//                out.println("<tr>\n" +
////"                               <td>" + rs.getString("Peso") + "</td></tr>"
////        + "<td>" + rs.getString("Estatura") + "</td></tr>"+
////                 "<td>" + imc + "</td>"+
//                          "<td>sss</td></tr>");
//            }
            out.println("\""+rs+"</table>"+
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerIMC.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerIMC.class.getName()).log(Level.SEVERE, null, ex);
        }
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
