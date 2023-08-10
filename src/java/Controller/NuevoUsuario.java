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
import Model.DataBase;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "NuevoUsuario", urlPatterns = {"/NuevoUsuario"})
public class NuevoUsuario extends HttpServlet {

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
        String nombre="",sexo="",usuario="",contrasena="",rep="";
        int edad, idUser;
        float estatura;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion=request.getSession();
        nombre = request.getParameter("nombre");
        try{
            edad = Integer.parseInt(request.getParameter("edad"));
        }catch(Exception e){
            sesion.setAttribute("advertencia","Edad invalida");
            request.getRequestDispatcher("/Registrate").forward(request, response); 
        }
        sexo = request.getParameter("sexo");
        try{
            estatura = Float.parseFloat(request.getParameter("estatura"));
        }catch(Exception e){
            sesion.setAttribute("advertencia","Estatura invalida");
            request.getRequestDispatcher("/Registrate").forward(request, response); 
        }
        usuario = request.getParameter("usuario");
        contrasena = request.getParameter("contrasena");
        rep = request.getParameter("repContrasena");
        if(!nombre.equals("")&&!sexo.equals("")&& !usuario.equals("")&&!contrasena.equals("")){
            if(edad>15){
                if(estatura>=1 &&estatura<=2.5){
                    if(contrasena.equals(rep)){
                        try{
                            idUser =db.NuevoUsuario(nombre, edad, sexo, estatura, usuario, DatatypeConverter.printHexBinary(contrasena.getBytes(StandardCharsets.UTF_8)));
                            if(idUser!=0){
                                sesion.setAttribute("idUsuario",idUser);
                                sesion.setAttribute("nombre",nombre);
                                sesion.setAttribute("edad",edad);
                                sesion.setAttribute("sexo",sexo);
                                sesion.setAttribute("estatura",estatura);
                                request.getRequestDispatcher("/SesionUsuario").forward(request, response);  
                            }else{
                                sesion.setAttribute("advertencia","Por el momento no podemos darte de alta intentalo mas tarde");
                                request.getRequestDispatcher("/Registrate").forward(request, response); 
                            }
                        }catch(Exception e){
                            sesion.setAttribute("advertencia","Por el momento no podemos darte de alta intentalo mas tarde");
                            request.getRequestDispatcher("/Registrate").forward(request, response);  
                        }
                    }else{
                        sesion.setAttribute("advertencia","Las contraseñas deben de ser iguales");
                        request.getRequestDispatcher("/Registrate").forward(request, response);  
                    }
                }else{
                    sesion.setAttribute("advertencia","La estatura debe de ser mayor a 1m y menor a 2.5m");
                    request.getRequestDispatcher("/Registrate").forward(request, response);  
                }
            }else{
                sesion.setAttribute("advertencia","Debes de ser mayor a 15 años");
                request.getRequestDispatcher("/Registrate").forward(request, response);
            }
        }else{
            sesion.setAttribute("advertencia","Todos los campos son obligatorios");
            request.getRequestDispatcher("/Registrate").forward(request, response);  
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
