///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controlador;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.JasperRunManager;
//import net.sf.jasperreports.engine.util.JRLoader;
//import utilidades.Conexion;
//import utilidades.MiExcepcion;
//
///**
// *
// * @author jeisson
// */
//public class Reportes extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, MiExcepcion, JRException {
//        response.setContentType("text/html;charset=UTF-8");
//        
//        if (request.getParameter("tabla") !=null && request.getParameter("tabla").equals("posiciones")) {
//            
//            int idTorneo = Integer.parseInt(request.getParameter("idTorneo"));
//            //paso el parametro id del torneo q necesita el reporte en un hashmap
//            Map parametros = new HashMap();
//            parametros.put("idTorneo", idTorneo);
//            
//            Connection conexion = Conexion.getInstance();
//            //cargo el reporte en ruta dinamica
//            JasperReport reporte = (JasperReport) JRLoader.loadObject(request.getContextPath()+"/reportes/posiciones.jasper");
//            @SuppressWarnings("unchecked")
//            byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, conexion);
//            
//            // el atributo attachment hace que el archivo se lance con el nombre reporteventasfechahora.pdf
//            LocalDate fecha = LocalDate.now();
//            response.setHeader("Content-Disposition", "attachment; filename=\"Posiciones "+fecha.toString()+".pdf\"");
//
//            response.setHeader("Cache-Control", "max-age=30");
//            response.setHeader("Pragma", "No-cache");
//            response.setDateHeader("Expires", 0);
//            response.setContentLength(bytes.length);
//            ServletOutputStream ouputStream = response.getOutputStream();
//            ouputStream.write(bytes, 0, bytes.length);
//            ouputStream.flush();
//            ouputStream.close();
//        }else if(request.getParameter("tabla") !=null && request.getParameter("tabla").equals("goleadores")){
//            int idTorneo = Integer.parseInt(request.getParameter("idTorneo"));
//            //paso el parametro id del torneo q necesita el reporte en un hashmap
//            Map parametros = new HashMap();
//            parametros.put("idTorneo", idTorneo);
//            
//            Connection conexion = Conexion.getInstance();
//            //cargo el reporte en ruta dinamica
//            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile((request.getContextPath()+"/reportes/goleadores.jasper"));
//            @SuppressWarnings("unchecked")
//            byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, conexion);
//            
//            // el atributo attachment hace que el archivo se lance con el nombre reporteventasfechahora.pdf
//            LocalDate fecha = LocalDate.now();
//            response.setHeader("Content-Disposition", "attachment; filename=\"Goleadores "+fecha.toString()+".pdf\"");
//
//            response.setHeader("Cache-Control", "max-age=30");
//            response.setHeader("Pragma", "No-cache");
//            response.setDateHeader("Expires", 0);
//            response.setContentLength(bytes.length);
//            ServletOutputStream ouputStream = response.getOutputStream();
//            ouputStream.write(bytes, 0, bytes.length);
//            ouputStream.flush();
//            ouputStream.close();
//        }
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Reportes</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Reportes at " + request.getContextPath() + getServletContext() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (MiExcepcion | JRException ex) {
//            response.sendError(500, ex.getMessage());
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (MiExcepcion | JRException ex) {
//            response.sendError(500, ex.getMessage());
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
