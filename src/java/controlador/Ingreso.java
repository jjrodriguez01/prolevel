/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.UsuariosDTO;
import persistencia.RolUsuarioDAO;
import persistencia.UsuariosDAO;

/**
 *
 * @author jeisson
 */
public class Ingreso extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("ingresar") != null) {
                String email = request.getParameter("email");
                String contraseña = request.getParameter("pass");
                UsuariosDAO usu = new UsuariosDAO();
                UsuariosDTO datosUsuario = new UsuariosDTO();
                RolUsuarioDAO rol = new RolUsuarioDAO();
                
                datosUsuario = usu.validarUsuario(email, contraseña);
                int numerorol = rol.getRol(datosUsuario);
                if(datosUsuario!=null && numerorol!=0){   
                    HttpSession miSesion = request.getSession(true);
                    miSesion.setAttribute("usr", datosUsuario);
                    miSesion.setAttribute("rol", numerorol);
                    response.sendRedirect("paginas/inicio.jsp");               
                }
                else if(datosUsuario == null){
                response.sendRedirect("index.jsp?auto=No_Ingreso");
                }
            }else if(request.getParameter("logout")!=null){
                    request.getSession().invalidate();
                    response.sendRedirect("index.jsp?sesion=cerrada");
                }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Ingreso</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ingreso at " + request.getContextPath() + "</h1>");
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
