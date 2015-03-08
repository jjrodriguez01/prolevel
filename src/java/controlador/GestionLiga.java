
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.LigaDTO;
import persistencia.LigaDAO;

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
            
            
           LigaDTO ligdto = new LigaDTO();
           LigaDAO ligdao = new LigaDAO();
            
            ligdto.setIdLiga(0);
            ligdto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
            ligdto.setFechaFin(request.getParameter("fin"));
            ligdto.setFechaInicio(request.getParameter("inicio"));
            ligdto.setGenero(request.getParameter("tipo"));
            ligdto.setIdaVuelta(Boolean.parseBoolean(request.getParameter("idaVuelta")));
            ligdto.setNombre(request.getParameter("nombreTorneo"));
           
            ligdao.insertar(ligdto);         
            
               response.sendRedirect("liga.jsp?mensaje=ok");
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
