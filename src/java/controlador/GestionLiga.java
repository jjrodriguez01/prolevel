
package controlador;

import AbstractFactory.Torneo;
import FactoryMethod.TorneoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public class GestionLiga extends HttpServlet {

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
        if ( request.getParameter("liga")!=null && request.getParameter("enviarliga")!=null) {
            
            
           TorneoDTO ligdto = new TorneoDTO();
            
            ligdto.setIdTorneo(0);
            ligdto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
            ligdto.setFechaFin(request.getParameter("fin"));
            ligdto.setFechaInicio(request.getParameter("inicio"));
            ligdto.setGenero(request.getParameter("tipo"));
            ligdto.setIdaVuelta(true);
            ligdto.setNombre(request.getParameter("nombreTorneo"));
            TorneoFactory fabrica = new TorneoFactory();
            Torneo liga = fabrica.crearTorneo(1, ligdto);
            String crearliga = liga.crear(ligdto);         
            response.sendRedirect("paginas/torneos/crear_torneo.jsp?liga="+crearliga+"#ftorneos");
        }
          else {
            response.sendRedirect("liga.html");
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
