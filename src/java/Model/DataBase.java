/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Samuel
 */
public class DataBase {
    String db ="medicalup";
    String url="jdbc:mysql://localhost:3306/";
    String user ="root";
    String pass = "";
    String driver ="com.mysql.cj.jdbc.Driver";
    Connection connection;
    public DataBase(){
        
    }
    public boolean Conectar(){
        boolean result= false;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url+db,user,pass);
            result = true;
        }catch(Exception e){
            result=false;
        }
        
        return result;
    }
    
    public void Desconectar(){
        try{
            connection.close();
        }catch(Exception e){        
        }
    }
    
    public int NuevoUsuario(String nombre, int edad, String sexo, float estatura, String usuario, String contrasena) throws SQLException{
        int result = 0;
        String sql = "INSERT INTO `usuarios`( `NombreCompleto`, `Edad`, `Sexo`, `Estatura`, `NombreUsuario`, `Contrasena`) VALUES ('"+nombre+"',"+edad+",'"+sexo+"',"+estatura+",'"+usuario+"','"+contrasena+"')";
        Conectar();
        PreparedStatement st =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            result = rs.getInt(1);
        }
        Desconectar();
        
        return result;
    }
    
    public String[] BuscarUsuario(String usuario, String contrasena) throws SQLException{
        String[] user = new String[5];
        String sql="SELECT * FROM usuarios WHERE NombreUsuario ='"+usuario+"' and Contrasena ='"+contrasena+"'";
        Conectar();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            user[0] = rs.getString("PkUsuarioID");
            user[1] = rs.getString("NombreCompleto");
            user[2] = rs.getString("Edad");
            user[3] = rs.getString("Sexo");
            user[4] = rs.getString("Estatura");
        }
        Desconectar();
        
        return user;
    }
    
     public String ActualizarUsuario(int idUser, String nombre, int edad, String sexo, float estatura) throws SQLException{
        int result = 0;
        String sql = "UPDATE `usuarios` SET `NombreCompleto`='"+nombre+"',`Edad`="+edad+",`Sexo`='"+sexo+"',`Estatura`="+estatura+" WHERE PkUsuarioID="+idUser;
        Conectar();
        PreparedStatement st =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        st.execute();
        Desconectar();
        
        return sql;
    }
     
    public int NuevoPeso(int idUser, float peso, float estatura) throws SQLException{
        int result = 0;
        String sql = "INSERT INTO `peso`( `FKUsuarioID`, `Peso`, `Estatura`) VALUES ("+idUser+","+peso+","+estatura+")";
        Conectar();
        PreparedStatement st =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            result = rs.getInt(1);
        }
        Desconectar();
        
        return result;
    }
    
    public String VerPesos(int idUser) throws SQLException{
        String pesos="";
        float imc=0;
        String sql="SELECT `Peso`, `Estatura`, `Fecha` FROM `peso` WHERE FKUsuarioID="+idUser;
        Conectar();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
                imc =rs.getFloat("Peso")/rs.getFloat("Estatura");
                pesos+="<tr><td>" + rs.getString("Peso") + "</td>"
        + "<td>" + rs.getString("Estatura") + "</td>"+
                 "<td>" + imc + "</td>"+
                          "<td>"+ rs.getString("Fecha") + "</td></tr>";
            }
        Desconectar();
        
        return pesos;
    }
}
