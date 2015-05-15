
package controlador;

import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ReservasDTO;
import persistencia.ReservasDAO;
import utilidades.MiExcepcion;

/**
 *
 * @author Astrid viviana ruiz
 */
public class GestionReservas extends HttpServlet {

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
        
        if (request.getParameter("reservar")!=null) {
            
            ReservasDAO rdao = new ReservasDAO();
            ReservasDTO rdto = new ReservasDTO();
            
            //rdto.setCodigo(0);
            // obteniendo datos de formulario y asignarlos a reservasDTO
            rdto.setFecha(request.getParameter("fecha"));
            rdto.setHora(request.getParameter("hora"));
            rdto.setNumeroCancha( Integer.parseInt( request.getParameter("cancha")));
        
  rdto.setUsuarioIdUsuario(1);
         rdto.setEstado(true);
            // insertamos la reserva
            String msj = rdao.insertar(rdto);
            response.sendRedirect("listarReservas.jsp?mensaje="+msj);
        }else if(request.getParameter("eliminareserva")!=null){
            
                ReservasDAO rdao = new ReservasDAO();
                int codigo = Integer.parseInt(request.getParameter("eliminareserva"));
                String reliminada = rdao.eliminar(codigo);
                
            if (reliminada.equals("Se elimino la reserva")) {
                out.println("rtyuiop");
//                response.sendRedirect("listarReservas.jsp?rdelete="+reliminada);
            }else{
                response.sendRedirect("listarReservas?rnodelete="+reliminada);
            }
            
        }else if(request.getParameter("cmodificar")!=null){
            ReservasDAO rdao = new ReservasDAO();
            ReservasDTO rdto = new ReservasDTO();
            
            rdto.setCodigo(Integer.parseInt(request.getParameter("cod")));
            // obteniendo datos de formulario y asignarlos a reservasDTO
            rdto.setFecha(request.getParameter("mfechar"));
            rdto.setHora(request.getParameter("mhorar"));
            rdto.setNumeroCancha( Integer.parseInt( request.getParameter("mcanchar")));
            rdto.setEstado(false);
            String modify = rdao.actualizar(rdto);
             if (modify.equals("La reserva se ha modificado")) {
            response.sendRedirect("listarReservas.jsp?rmodificada="+modify);
             }else{
                 response.sendRedirect("listarReservas.jsp?rnomodificada="+modify);
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
        try {
            processRequest(request, response);
        } catch (MiExcepcion ex) {
            Logger.getLogger(GestionReservas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestionReservas.class.getName()).log(Level.SEVERE, null, ex);
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
