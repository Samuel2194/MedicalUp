/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DataBase;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AgregarPeso", urlPatterns = {"/AgregarPeso"})
public class AgregarPeso extends HttpServlet {

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
            throws ServletException, IOException {
        float peso=0;
        HttpSession sesion=request.getSession();
        try{
            peso = Float.parseFloat(request.getParameter("peso"));
        }catch(Exception e){
            sesion.setAttribute("advertencia","Peso invalido");
            request.getRequestDispatcher("/VerIMC").forward(request, response); 
        }
        if(peso>0){
            try{
                db.NuevoPeso(Integer.parseInt(sesion.getAttribute("idUsuario").toString()), peso,Float.parseFloat(sesion.getAttribute("estatura").toString()));
                sesion.setAttribute("informacion","El Peso se agrego");
                request.getRequestDispatcher("/VerIMC").forward(request, response); 
            }catch(Exception e){
                sesion.setAttribute("error","Por el momento no podemos agregar tu peso, intentalo mas tarde");
                request.getRequestDispatcher("/VerIMC").forward(request, response); 
            }
        }else{
            sesion.setAttribute("advertencia","Peso invalido");
            request.getRequestDispatcher("/VerIMC").forward(request, response); 
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarPeso</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarPeso at " + request.getContextPath() + "</h1>");
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
