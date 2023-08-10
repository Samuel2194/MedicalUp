/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DataBase;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "BuscarUsuario", urlPatterns = {"/BuscarUsuario"})
public class BuscarUsuario extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        String usuario, contrasena;
        String[] user=new String[5];
        DataBase db = new DataBase();
        //out.println("<h2>entra</h2>");
        HttpSession sesion=request.getSession();
        usuario = request.getParameter("usuario");
        contrasena = DatatypeConverter.printHexBinary( request.getParameter("contrasena").getBytes(StandardCharsets.UTF_8));
        //out.println("<h2>"+contrasena+"</h2>");
        user=db.BuscarUsuario(usuario, contrasena);
        if(user[1]!=null){
            sesion.setAttribute("idUsuario",user[0]);
            sesion.setAttribute("nombre",user[1]);
            sesion.setAttribute("edad",user[2]);
            sesion.setAttribute("sexo",user[3]);
            sesion.setAttribute("estatura",user[4]);
            request.getRequestDispatcher("/SesionUsuario").forward(request, response); 
        }else{
            sesion.setAttribute("advertencia","Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/IniciarSesion").forward(request, response);
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
            Logger.getLogger(BuscarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BuscarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
