/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TarjetasDTO;
import persistencia.TarjetasDAO;

/**
 *
 * @author jeisson
 */
public class Tarjetas extends HttpServlet {

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
       
            if (request.getParameter("asigtarjetas")!=null && request.getParameter("tarjetas")!=null) {
                TarjetasDTO tar = new TarjetasDTO();
                tar.setIdJugador(Integer.parseInt(request.getParameter("jugadorunico")));
                tar.setIdtorneo(Integer.parseInt(request.getParameter("idTorneo")));
                tar.setTarjetaAzul(Integer.parseInt(request.getParameter("azules")));
                tar.setTarjetaRoja(Integer.parseInt(request.getParameter("rojas")));
                TarjetasDAO tardao = new TarjetasDAO();
                LinkedList<TarjetasDTO> listar = new LinkedList();
                listar = tardao.listarUno(tar.getIdtorneo(), tar.getIdJugador());
                if (listar.isEmpty()) {
                    String itarjetas = tardao.insertarPrimer(tar);
                    response.sendRedirect("misTorneos.jsp?tarjetas="+itarjetas+"&idTorneo="+tar.getIdtorneo());
                }else{
                    String itarjetas = tardao.insertar(tar);
                    response.sendRedirect("misTorneos.jsp?tarjetas="+itarjetas+"&idTorneo="+tar.getIdtorneo());
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
