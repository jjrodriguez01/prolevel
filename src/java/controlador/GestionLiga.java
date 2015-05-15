
package controlador;

import AbstractFactory.FabricaTorneo;
import AbstractFactory.Liga;
import AbstractFactory.Torneo;
import FactoryMethod.TorneoFactory;
import facade.FachadaTorneos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.EquiposdeltorneoDTO;
import modelo.TorneoDTO;
import utilidades.MiExcepcion;

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
            throws ServletException, IOException, MiExcepcion {
        response.setContentType("text/html;charset=UTF-8");
        if ( request.getParameter("liga")!=null && request.getParameter("enviarliga")!=null) {
           TorneoDTO ligdto = new TorneoDTO();
            int tipotorneo = 2;//en bd dos es una liga
            ligdto.setIdTorneo(0);
            ligdto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
            ligdto.setFechaFin(request.getParameter("fin"));
            ligdto.setFechaInicio(request.getParameter("inicio"));
            ligdto.setGenero(request.getParameter("tipo"));
            ligdto.setTipo(tipotorneo);
            ligdto.setIdaVuelta(true);
            ligdto.setNombre(request.getParameter("nombreTorneo"));
            TorneoFactory fabrica = new TorneoFactory();
            Torneo liga = fabrica.crearTorneo(ligdto);//la fabrica toma el tipo de torneo y como es dos me crea una liga
            String crearliga = liga.crear(ligdto);         
            response.sendRedirect("paginas/torneos/crear_torneo.jsp?liga="+crearliga+"#ftorneos");
        }
        //
        //si van a iniciar la liga
        //
        else if(request.getParameter("iniciar")!=null && request.getParameter("liga")!=null){
             TorneoDTO ligdto = new TorneoDTO();
             FachadaTorneos facadeTorneos = new FachadaTorneos();
            int tipotorneo = 2;//en bd dos es una liga
            ligdto.setIdTorneo(Integer.parseInt(request.getParameter("idTorneo")));
            ligdto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidadEquipos")));
            ligdto.setFechaFin(request.getParameter("fechaFin"));
            ligdto.setFechaInicio(request.getParameter("fechaInicio"));
            ligdto.setGenero(request.getParameter("genero"));
            ligdto.setTipo(tipotorneo);
            ligdto.setIdaVuelta(true);
            ligdto.setNombre(request.getParameter("nombre"));
            FabricaTorneo fabrica = new FabricaTorneo();
            Liga liga = fabrica.creaLiga(ligdto);
            List<EquiposdeltorneoDTO> edt = new ArrayList();//arrayList con los equipos de este torneo
            //ese metodo me devuelve un List con todos los equiposn de este torneo
            edt = facadeTorneos.listarEquiposInscritos(ligdto.getIdTorneo());
            liga.ligaSeis(edt);
            response.sendRedirect("paginas/torneos/calendario.jsp?idTorneo="+ligdto.getIdTorneo());
        }
          else {
            response.sendRedirect("paginas/torneos/crear_torneo.jsp");
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
        try {
            processRequest(request, response);
        } catch (MiExcepcion ex) {
            response.sendError(500, ex.getMessage());
        }
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
        try {
            processRequest(request, response);
        } catch (MiExcepcion ex) {
            response.sendError(500, ex.getMessage());
        }
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
