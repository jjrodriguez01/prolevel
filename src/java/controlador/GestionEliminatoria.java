
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.EliminatoriaDTO;
import persistencia.EliminatoriaDAO;

/**
 *
 * @author jeisson
 */
public class GestionEliminatoria extends HttpServlet {

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
        if ( request.getParameter("eliminatoria")!=null && request.getParameter("enviareli")!=null) {
            
            
            EliminatoriaDTO elidto = new EliminatoriaDTO();
            EliminatoriaDAO elidao = new EliminatoriaDAO();
            
            elidto.setIdEliminatoria(0);
            elidto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
            elidto.setFechaFin(request.getParameter("fin"));
            elidto.setFechaInicio(request.getParameter("inicio"));
            elidto.setGenero(request.getParameter("tipo"));
            elidto.setIdaVuelta(Boolean.parseBoolean(request.getParameter("idaVuelta")));
            elidto.setNombre(request.getParameter("nombreTorneo"));
          
            elidao.insertar(elidto);
               response.sendRedirect("index.html? mensaje=ok");
        }
          else {
            response.sendRedirect("eliminatoria.html");
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
