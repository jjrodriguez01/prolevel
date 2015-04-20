/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import facade.FachadaTorneos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CanchaDTO;

/**
 *
 * @author jeisson
 */
public class Canchas extends HttpServlet {

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
        CanchaDTO cdto = null;
        FachadaTorneos facadetorneos = null; 
        if (request.getParameter("ecancha")!=null) {
            facadetorneos = new FachadaTorneos();
            int numeroCancha = Integer.parseInt(request.getParameter("numero"));
            String eliminada = facadetorneos.eliminarCancha(numeroCancha);
            response.sendRedirect("paginas/admin.jsp?eliminada="+eliminada);
        }else if (request.getParameter("icancha")!=null) {
            cdto = new CanchaDTO();
            facadetorneos = new FachadaTorneos();
            cdto.setNumeroCancha(Integer.parseInt(request.getParameter("numero")));
            cdto.setDescripcion(request.getParameter("des"));
            String ins = facadetorneos.insertarCancha(cdto);
            response.sendRedirect("paginas/admin.jsp?inscancha="+ins);
        }else if (request.getParameter("ac")!=null & request.getParameter("confirmac")!=null){
            cdto = new CanchaDTO();
            facadetorneos = new FachadaTorneos();
            cdto.setNumeroCancha(Integer.parseInt(request.getParameter("num")));
            cdto.setDescripcion(request.getParameter("descripcion"));
            String ac = facadetorneos.actualizarCancha(cdto);
            response.sendRedirect("paginas/admin.jsp?ac="+ac);
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
