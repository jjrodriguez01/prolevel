/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import facade.FachadaTorneos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */
public class RegistroEquipos extends HttpServlet {

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
        if (request.getParameter("crearEquipo")!=null) {
            int idTorneo = Integer.parseInt(request.getParameter("idTorneo"));//id del torneo
            String nombre = request.getParameter("nombre");//nombre equipo
            FachadaTorneos facadetorneos = new FachadaTorneos();                       
  
            int codigo = facadetorneos.existeEquipo(nombre);
            //si el codigo no es cero significa que este equipo ya esta registrado solo lo insertamos al torneo 
            if (codigo!=0) {
                String  nuevoequipo = facadetorneos.inscribirEquipos(codigo, idTorneo);//insertamos en la tabla equipos del torneo
                if (nuevoequipo.equals("Se inserto el equipo al torneo")) {
    //ahora borro los jugadores de ese equipo anteriormente
                    facadetorneos.eliminarJugadoresEquipo(codigo);
    //ahora inscribo los nuevos jugadores
    long juno = Long.parseLong(request.getParameter("juno"));
    long jdos = Long.parseLong(request.getParameter("jdos"));
    long jtres = Long.parseLong(request.getParameter("jtres"));
    long jcuatro = Long.parseLong(request.getParameter("jcuatro"));
    long jcinco = Long.parseLong(request.getParameter("jcinco"));
    long jseis = Long.parseLong(request.getParameter("jseis"));
    long jsiete = Long.parseLong(request.getParameter("jsiete"));
    long jocho = Long.parseLong(request.getParameter("jocho"));
    //creo un array con los documentos
    long[] docs = {juno,jdos,jtres,jcuatro,jcinco,jseis,jsiete,jocho};
        for (int i = 0; i < docs.length; i++) {
            facadetorneos.inscribirJugadorAEquipo(codigo, docs[i]);
        }
        response.sendRedirect("paginas/torneos/inscribirEquipos.jsp?idTorneo="+idTorneo+"&registro=Se registraron el equipo y los jugadores");
                }//si el codigo de equipo es cero es porq no esta registrado
                //hay que registrarlo y repetir el proceso
            }else{
                String registroequipo = facadetorneos.insertarEquipo(nombre);
                if (registroequipo.equals("Se inserto el equipo")) {//si se registro correctamente
                    //hay q buscar el codigo con el que se inserto el equipo
                    int nuevoequipo = facadetorneos.existeEquipo(nombre);
                    //ahora lo insertamos al torneo
                    String equipoatorneo = facadetorneos.inscribirEquipos(nuevoequipo, idTorneo);
                    if (equipoatorneo.equals("Se inserto el equipo al torneo")) {
    //ahora a inscribir los jugadores
    long juno = Long.parseLong(request.getParameter("juno"));
    long jdos = Long.parseLong(request.getParameter("jdos"));
    long jtres = Long.parseLong(request.getParameter("jtres"));
    long jcuatro = Long.parseLong(request.getParameter("jcuatro"));
    long jcinco = Long.parseLong(request.getParameter("jcinco"));
    long jseis = Long.parseLong(request.getParameter("jseis"));
    long jsiete = Long.parseLong(request.getParameter("jsiete"));
    long jocho = Long.parseLong(request.getParameter("jocho"));
    //creo un array con los documentos
    long[] docs = {juno,jdos,jtres,jcuatro,jcinco,jseis,jsiete,jocho};
        for (int i = 0; i < docs.length; i++) {
            facadetorneos.inscribirJugadorAEquipo(nuevoequipo, docs[i]);//insertamos en la tabla jugadorespor equipo 
        }
        response.sendRedirect("paginas/torneos/inscribirEquipos.jsp?idTorneo="+idTorneo+"&registro=Se registraron el equipo y los jugadores");
                    }else{
                        response.sendError(500, equipoatorneo);
                    }
                }else{//si hubo problemas en el registro
                    response.sendError(500, registroequipo);
                }
            }
            
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroEquipos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroEquipos at " + request.getContextPath() + "</h1>");
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
