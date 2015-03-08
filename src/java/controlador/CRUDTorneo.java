/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TorneoDTO;
import persistencia.TorneoDAO;

/**
 *
 * @author jeisson
 */
public class CRUDTorneo extends HttpServlet {

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
        TorneoDTO tdto = null;
        TorneoDAO tdao = null;    
            if (request.getParameter("at")!=null && request.getParameter("confirmactu")!=null) {
            tdto = new TorneoDTO();    
                
                tdto.setNombre(request.getParameter("nombre"));
                tdto.setFechaInicio(request.getParameter("finicio"));
                tdto.setFechaFin(request.getParameter("ffin"));
                tdto.setGenero(request.getParameter("genero"));
                tdto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
                tdto.setIdTorneo(Integer.parseInt(request.getParameter("idTorneo")));
                
                tdao = new TorneoDAO();
                String msj = tdao.actualizar(tdto);
                response.sendRedirect("perfil_admin.jsp?updatet="+msj);
                
            }else if (request.getParameter("et")!=null && request.getParameter("realizaret")!=null)  {
                tdto = new TorneoDTO();
                int id = (Integer.parseInt(request.getParameter("idTorneo")));
                tdao = new TorneoDAO();
                String elit = tdao.eliminar(id);
                response.sendRedirect("perfil_admin.jsp?elimt="+elit);
            }
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
