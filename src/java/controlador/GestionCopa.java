
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CopaDTO;
import modelo.EliminatoriaDTO;
import modelo.LigaDTO;
import persistencia.CopaDAO;
import persistencia.EliminatoriaDAO;
import persistencia.LigaDAO;

/**
 *
 * @author Sena
 */
@WebServlet(name = "GestionTorneos", urlPatterns = {"/GestionTorneos"})
public class GestionCopa extends HttpServlet {

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
        
        
        if ( request.getParameter("copa")!=null && request.getParameter("enviarcopa")!=null) {
            
            CopaDTO copa = new CopaDTO();
           
            
            copa.setIdTorneo(0);
            copa.setNombre(request.getParameter("nombreTorneo"));
            copa.setFechaInicio(request.getParameter("inicio"));
            copa.setFechaFin(request.getParameter("fin"));
            copa.setGenero(request.getParameter("tipo"));
            copa.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
            copa.setEquiposGrupos(Integer.parseInt(request.getParameter("numgrupos")));
            copa.setOctavosCuartosSemifinalFinalIdaVuelta(Boolean.parseBoolean(request.getParameter("idavueltaeli")));
            copa.setFinalidavuelta(Boolean.parseBoolean(request.getParameter("finalIdaVuelta")));
            copa.setTercerPuesto(Boolean.parseBoolean(request.getParameter("tercer")));
            
            CopaDAO cup = new CopaDAO();
            cup.insertar(copa);
            response.sendRedirect("copa.jsp?mensaje=ok");
        }
    
     else {
        response.sendRedirect("copa.html");
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
