
package controlador;

import AbstractFactory.Torneo;
import FactoryMethod.TorneoFactory;
import controlador.correo.Correo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PartidoDTO;
import modelo.TorneoDTO;
import persistencia.EquiposDelTorneoDAO;
import persistencia.PartidoDAO;
import utilidades.MiExcepcion;

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
            int tipotorneo = 3;//en bd tres es una eliminatoria
            TorneoDTO elidto = new TorneoDTO();
            
            elidto.setIdTorneo(0);
            elidto.setCapacidadEquipos(Integer.parseInt(request.getParameter("capacidad")));
            elidto.setFechaFin(request.getParameter("fin"));
            elidto.setFechaInicio(request.getParameter("inicio"));
            elidto.setGenero(request.getParameter("tipo"));
            elidto.setTipo(tipotorneo);
            elidto.setIdaVuelta(true);
            elidto.setNombre(request.getParameter("nombreTorneo"));
            TorneoFactory fabrica = new TorneoFactory();
            Torneo cup = fabrica.crearTorneo(elidto);
            String crearelim = cup.crear(elidto);
               response.sendRedirect("paginas/torneos/crear_torneo.jsp?eliminatoria="+crearelim+"#ftorneos");
        }
        //
        //inicio a actualizar fechas de una eli de 16
        //
        else if(request.getParameter("asignarfechas")!=null && request.getParameter("foctavos")!=null){
            int ronda = 1;
            int idTorneo= Integer.parseInt(request.getParameter("idTorneo"));
            String asunto = "Notificacion horarios de partidos";
            PartidoDAO pdao = new PartidoDAO();
            EquiposDelTorneoDAO edt = new EquiposDelTorneoDAO();
            try{
            //comienzo con el primer partido
            PartidoDTO p1 = new PartidoDTO();   
            p1.setRonda(ronda);
            p1.setEquipo1(Integer.parseInt(request.getParameter("0equipo1")));
            p1.setEquipo2(Integer.parseInt(request.getParameter("0equipo2")));
            p1.setFecha(request.getParameter("fecha0"));
            p1.setHora(request.getParameter("hora0"));
            p1.setCancha(Integer.parseInt(request.getParameter("cp0")));
            p1.setIdTorneo(idTorneo);
            pdao.actualizar(p1);
            StringBuilder emailsp1 = new StringBuilder("");
            ArrayList<String> correosp1eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp1eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo1")));
            correosp1eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo2"))));
            for (int i = 0; i < correosp1eq.size(); i++) {
                emailsp1.append(correosp1eq.get(i));
                if (i != correosp1eq.size() -1 && correosp1eq.size() > 0) {
                    emailsp1.append(" ,");
                }
            }
            //envio los correos
            String nequipo1 = request.getParameter("0nequipo1");
            String nequipo2 = request.getParameter("0nequipo2");
            String cuerpop1 = "El partido <strong>"+nequipo1+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo2
                    +"<br/>"
                    +"Sera el "+p1.getFecha()+" a las "+p1.getHora();
            Correo.sendMail(asunto, cuerpop1, emailsp1.toString());
            //fin del primer partido
            
            //comienzo con el segundo
            PartidoDTO p2 = new PartidoDTO();   
            p2.setRonda(ronda);
            p2.setEquipo1(Integer.parseInt(request.getParameter("1equipo1")));
            p2.setEquipo2(Integer.parseInt(request.getParameter("1equipo2")));
            p2.setFecha(request.getParameter("fecha1"));
            p2.setHora(request.getParameter("hora1"));
            p2.setCancha(Integer.parseInt(request.getParameter("cp1")));
            p2.setIdTorneo(idTorneo);
            pdao.actualizar(p2);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp2 = new StringBuilder("");//emails 
            ArrayList<String> correosp2eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp2eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("1equipo1")));
            correosp2eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("1equipo2"))));
            for (int i = 0; i < correosp2eq.size(); i++) {
                emailsp2.append(correosp2eq.get(i));
                if (i != correosp2eq.size() -1 && correosp2eq.size() > 0) {
                    emailsp2.append(" ,");
                }
            }
            //envio los correos
            String nequipo3 = request.getParameter("1nequipo1");
            String nequipo4 = request.getParameter("1nequipo2");
            String cuerpop2 = "El partido <strong>"+nequipo3+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo4
                    +"<br/>"
                    +"Sera el "+p2.getFecha()+" a las "+p2.getHora();
            Correo.sendMail(asunto, cuerpop2, emailsp2.toString());
            //fin del segundo
            
            //comienzo con el tercero
            PartidoDTO p3 = new PartidoDTO();   
            p3.setRonda(ronda);
            p3.setEquipo1(Integer.parseInt(request.getParameter("2equipo1")));
            p3.setEquipo2(Integer.parseInt(request.getParameter("2equipo2")));
            p3.setFecha(request.getParameter("fecha2"));
            p3.setHora(request.getParameter("hora2"));
            p3.setCancha(Integer.parseInt(request.getParameter("cp2")));
            p3.setIdTorneo(idTorneo);
            pdao.actualizar(p2);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp3 = new StringBuilder("");//emails 
            ArrayList<String> correosp3eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp3eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("2equipo1")));
            correosp3eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("2equipo2"))));
            for (int i = 0; i < correosp3eq.size(); i++) {
                emailsp3.append(correosp3eq.get(i));
                if (i != correosp3eq.size() -1 && correosp3eq.size() > 0) {
                    emailsp3.append(" ,");
                }
            }
            //envio los correos
            String nequipo5 = request.getParameter("2nequipo1");
            String nequipo6 = request.getParameter("2nequipo2");
            String cuerpop3 = "El partido <strong>"+nequipo5+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo6
                    +"<br/>"
                    +"Sera el "+p3.getFecha()+" a las "+p3.getHora();
            Correo.sendMail(asunto, cuerpop3, emailsp3.toString());
            //fin del tercero
            
            //comienzo con el cuarto
            PartidoDTO p4 = new PartidoDTO();   
            p4.setRonda(ronda);
            p4.setEquipo1(Integer.parseInt(request.getParameter("3equipo1")));
            p4.setEquipo2(Integer.parseInt(request.getParameter("3equipo2")));
            p4.setFecha(request.getParameter("fecha3"));
            p4.setHora(request.getParameter("hora3"));
            p4.setCancha(Integer.parseInt(request.getParameter("cp3")));
            p4.setIdTorneo(idTorneo);
            pdao.actualizar(p4);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp4 = new StringBuilder("");//emails 
            ArrayList<String> correosp4eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp4eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("3equipo1")));
            correosp4eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("3equipo2"))));
            for (int i = 0; i < correosp4eq.size(); i++) {
                emailsp3.append(correosp4eq.get(i));
                if (i != correosp4eq.size() -1 && correosp4eq.size() > 0) {
                    emailsp4.append(" ,");
                }
            }
            //envio los correos
            String nequipo7 = request.getParameter("3nequipo1");
            String nequipo8 = request.getParameter("3nequipo2");
            String cuerpop4 = "El partido <strong>"+nequipo7+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo8
                    +"<br/>"
                    +"Sera el "+p4.getFecha()+" a las "+p4.getHora();
            Correo.sendMail(asunto, cuerpop4, emailsp4.toString());
            //fin del cuarto
            
            //comienzo con el quinto
            PartidoDTO p5 = new PartidoDTO();   
            p5.setRonda(ronda);
            p5.setEquipo1(Integer.parseInt(request.getParameter("4equipo1")));
            p5.setEquipo2(Integer.parseInt(request.getParameter("4equipo2")));
            p5.setFecha(request.getParameter("fecha4"));
            p5.setHora(request.getParameter("hora4"));
            p5.setCancha(Integer.parseInt(request.getParameter("cp4")));
            p5.setIdTorneo(idTorneo);
            pdao.actualizar(p2);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp5 = new StringBuilder("");//emails 
            ArrayList<String> correosp5eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp5eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("4equipo1")));
            correosp5eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("4equipo2"))));
            for (int i = 0; i < correosp5eq.size(); i++) {
                emailsp3.append(correosp5eq.get(i));
                if (i != correosp5eq.size() -1 && correosp5eq.size() > 0) {
                    emailsp5.append(" ,");
                }
            }
            //envio los correos
            String nequipo9 = request.getParameter("4nequipo1");
            String nequipo10 = request.getParameter("4nequipo2");
            String cuerpop5 = "El partido <strong>"+nequipo9+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo10
                    +"<br/>"
                    +"Sera el "+p5.getFecha()+" a las "+p5.getHora();
            Correo.sendMail(asunto, cuerpop5, emailsp5.toString());
            //fin del quinto
            
            //comienzo con el sexto
            PartidoDTO p6 = new PartidoDTO();   
            p6.setRonda(ronda);
            p6.setEquipo1(Integer.parseInt(request.getParameter("5equipo1")));
            p6.setEquipo2(Integer.parseInt(request.getParameter("5equipo2")));
            p6.setFecha(request.getParameter("fecha5"));
            p6.setHora(request.getParameter("hora5"));
            p6.setCancha(Integer.parseInt(request.getParameter("cp5")));
            p6.setIdTorneo(idTorneo);
            pdao.actualizar(p6);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp6 = new StringBuilder("");//emails 
            ArrayList<String> correosp6eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp6eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("5equipo1")));
            correosp6eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("5equipo2"))));
            for (int i = 0; i < correosp6eq.size(); i++) {
                emailsp3.append(correosp6eq.get(i));
                if (i != correosp6eq.size() -1 && correosp6eq.size() > 0) {
                    emailsp6.append(" ,");
                }
            }
            //envio los correos
            String nequipo11 = request.getParameter("5nequipo1");
            String nequipo12 = request.getParameter("5nequipo2");
            String cuerpop6 = "El partido <strong>"+nequipo11+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo12
                    +"<br/>"
                    +"Sera el "+p6.getFecha()+" a las "+p6.getHora();
            Correo.sendMail(asunto, cuerpop6, emailsp6.toString());
            //fin del sexto
            
             //comienzo con el septimo
            PartidoDTO p7 = new PartidoDTO();   
            p7.setRonda(ronda);
            p7.setEquipo1(Integer.parseInt(request.getParameter("6equipo1")));
            p7.setEquipo2(Integer.parseInt(request.getParameter("6equipo2")));
            p7.setFecha(request.getParameter("fecha6"));
            p7.setHora(request.getParameter("hora6"));
            p7.setCancha(Integer.parseInt(request.getParameter("cp6")));
            p7.setIdTorneo(idTorneo);
            pdao.actualizar(p7);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp7 = new StringBuilder("");//emails 
            ArrayList<String> correosp7eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp7eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("6equipo1")));
            correosp7eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("6equipo2"))));
            for (int i = 0; i < correosp7eq.size(); i++) {
                emailsp3.append(correosp7eq.get(i));
                if (i != correosp7eq.size() -1 && correosp7eq.size() > 0) {
                    emailsp6.append(" ,");
                }
            }
            //envio los correos
            String nequipo13 = request.getParameter("6nequipo1");
            String nequipo14 = request.getParameter("6nequipo2");
            String cuerpop7 = "El partido <strong>"+nequipo13+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo14
                    +"<br/>"
                    +"Sera el "+p7.getFecha()+" a las "+p7.getHora();
            Correo.sendMail(asunto, cuerpop7, emailsp7.toString());
            //fin del septimo
            
            //comienzo con el octavo
            PartidoDTO p8 = new PartidoDTO();   
            p8.setRonda(ronda);
            p8.setEquipo1(Integer.parseInt(request.getParameter("7equipo1")));
            p8.setEquipo2(Integer.parseInt(request.getParameter("7equipo2")));
            p8.setFecha(request.getParameter("fecha7"));
            p8.setHora(request.getParameter("hora7"));
            p8.setCancha(Integer.parseInt(request.getParameter("cp7")));
            p8.setIdTorneo(idTorneo);
            pdao.actualizar(p8);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp8 = new StringBuilder("");//emails 
            ArrayList<String> correosp8eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp8eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("7equipo1")));
            correosp8eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("7equipo2"))));
            for (int i = 0; i < correosp8eq.size(); i++) {
                emailsp3.append(correosp8eq.get(i));
                if (i != correosp8eq.size() -1 && correosp8eq.size() > 0) {
                    emailsp6.append(" ,");
                }
            }
            //envio los correos
            String nequipo15 = request.getParameter("7nequipo1");
            String nequipo16 = request.getParameter("7nequipo2");
            String cuerpop8 = "El partido <strong>"+nequipo15+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo16
                    +"<br/>"
                    +"Sera el "+p8.getFecha()+" a las "+p8.getHora();
            Correo.sendMail(asunto, cuerpop8, emailsp8.toString());
            //fin del octavo
            }catch(MiExcepcion mi){
                response.sendError(500, mi.toString());
            }
            response.sendRedirect("resultados.jsp?idTorneo="+idTorneo);
        }else if(request.getParameter("asignarfechas")!=null && request.getParameter("fcuartos")!=null){
            int ronda = 2;
            int idTorneo= Integer.parseInt(request.getParameter("idTorneo"));
            String asunto = "Notificacion horarios de partidos";
            PartidoDAO pdao = new PartidoDAO();
            EquiposDelTorneoDAO edt = new EquiposDelTorneoDAO();
            try{
            //comienzo con el primer partido
            PartidoDTO p1 = new PartidoDTO();   
            p1.setRonda(ronda);
            p1.setEquipo1(Integer.parseInt(request.getParameter("0equipo1")));
            p1.setEquipo2(Integer.parseInt(request.getParameter("0equipo2")));
            p1.setFecha(request.getParameter("fecha0"));
            p1.setHora(request.getParameter("hora0"));
            p1.setCancha(Integer.parseInt(request.getParameter("cp0")));
            p1.setIdTorneo(idTorneo);
            pdao.actualizar(p1);
            StringBuilder emailsp1 = new StringBuilder("");
            ArrayList<String> correosp1eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp1eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo1")));
            correosp1eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo2"))));
            for (int i = 0; i < correosp1eq.size(); i++) {
                emailsp1.append(correosp1eq.get(i));
                if (i != correosp1eq.size() -1 && correosp1eq.size() > 0) {
                    emailsp1.append(" ,");
                }
            }
            //envio los correos
            String nequipo1 = request.getParameter("0nequipo1");
            String nequipo2 = request.getParameter("0nequipo2");
            String cuerpop1 = "El partido <strong>"+nequipo1+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo2
                    +"<br/>"
                    +"Sera el "+p1.getFecha()+" a las "+p1.getHora();
            Correo.sendMail(asunto, cuerpop1, emailsp1.toString());
            //fin del primer partido
            
            //comienzo con el segundo
            PartidoDTO p2 = new PartidoDTO();   
            p2.setRonda(ronda);
            p2.setEquipo1(Integer.parseInt(request.getParameter("1equipo1")));
            p2.setEquipo2(Integer.parseInt(request.getParameter("1equipo2")));
            p2.setFecha(request.getParameter("fecha1"));
            p2.setHora(request.getParameter("hora1"));
            p2.setCancha(Integer.parseInt(request.getParameter("cp1")));
            p2.setIdTorneo(idTorneo);
            pdao.actualizar(p2);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp2 = new StringBuilder("");//emails 
            ArrayList<String> correosp2eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp2eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("1equipo1")));
            correosp2eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("1equipo2"))));
            for (int i = 0; i < correosp2eq.size(); i++) {
                emailsp2.append(correosp2eq.get(i));
                if (i != correosp2eq.size() -1 && correosp2eq.size() > 0) {
                    emailsp2.append(" ,");
                }
            }
            //envio los correos
            String nequipo3 = request.getParameter("1nequipo1");
            String nequipo4 = request.getParameter("1nequipo2");
            String cuerpop2 = "El partido <strong>"+nequipo3+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo4
                    +"<br/>"
                    +"Sera el "+p2.getFecha()+" a las "+p2.getHora();
            Correo.sendMail(asunto, cuerpop2, emailsp2.toString());
            //fin del segundo
            
            //comienzo con el tercero
            PartidoDTO p3 = new PartidoDTO();   
            p3.setRonda(ronda);
            p3.setEquipo1(Integer.parseInt(request.getParameter("2equipo1")));
            p3.setEquipo2(Integer.parseInt(request.getParameter("2equipo2")));
            p3.setFecha(request.getParameter("fecha2"));
            p3.setHora(request.getParameter("hora2"));
            p3.setCancha(Integer.parseInt(request.getParameter("cp2")));
            p3.setIdTorneo(idTorneo);
            pdao.actualizar(p2);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp3 = new StringBuilder("");//emails 
            ArrayList<String> correosp3eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp3eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("2equipo1")));
            correosp3eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("2equipo2"))));
            for (int i = 0; i < correosp3eq.size(); i++) {
                emailsp3.append(correosp3eq.get(i));
                if (i != correosp3eq.size() -1 && correosp3eq.size() > 0) {
                    emailsp3.append(" ,");
                }
            }
            //envio los correos
            String nequipo5 = request.getParameter("2nequipo1");
            String nequipo6 = request.getParameter("2nequipo2");
            String cuerpop3 = "El partido <strong>"+nequipo5+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo6
                    +"<br/>"
                    +"Sera el "+p3.getFecha()+" a las "+p3.getHora();
            Correo.sendMail(asunto, cuerpop3, emailsp3.toString());
            //fin del tercero
            
            //comienzo con el cuarto
            PartidoDTO p4 = new PartidoDTO();   
            p4.setRonda(ronda);
            p4.setEquipo1(Integer.parseInt(request.getParameter("3equipo1")));
            p4.setEquipo2(Integer.parseInt(request.getParameter("3equipo2")));
            p4.setFecha(request.getParameter("fecha3"));
            p4.setHora(request.getParameter("hora3"));
            p4.setCancha(Integer.parseInt(request.getParameter("cp3")));
            p4.setIdTorneo(idTorneo);
            pdao.actualizar(p4);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp4 = new StringBuilder("");//emails 
            ArrayList<String> correosp4eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp4eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("3equipo1")));
            correosp4eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("3equipo2"))));
            for (int i = 0; i < correosp4eq.size(); i++) {
                emailsp3.append(correosp4eq.get(i));
                if (i != correosp4eq.size() -1 && correosp4eq.size() > 0) {
                    emailsp4.append(" ,");
                }
            }
            //envio los correos
            String nequipo7 = request.getParameter("3nequipo1");
            String nequipo8 = request.getParameter("3nequipo2");
            String cuerpop4 = "El partido <strong>"+nequipo7+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo8
                    +"<br/>"
                    +"Sera el "+p4.getFecha()+" a las "+p4.getHora();
            Correo.sendMail(asunto, cuerpop4, emailsp4.toString());
            //fin del cuarto
            }catch(MiExcepcion mi){
                response.sendError(500, mi.toString());
            }
            response.sendRedirect("resultados.jsp?idTorneo="+idTorneo);
        }else if(request.getParameter("asignarfechas")!=null && request.getParameter("fsemi")!=null){
            int ronda = 3;
            int idTorneo= Integer.parseInt(request.getParameter("idTorneo"));
            String asunto = "Notificacion horarios de partidos";
            PartidoDAO pdao = new PartidoDAO();
            EquiposDelTorneoDAO edt = new EquiposDelTorneoDAO();
            try{
            //comienzo con el primer partido
            PartidoDTO p1 = new PartidoDTO();   
            p1.setRonda(ronda);
            p1.setEquipo1(Integer.parseInt(request.getParameter("0equipo1")));
            p1.setEquipo2(Integer.parseInt(request.getParameter("0equipo2")));
            p1.setFecha(request.getParameter("fecha0"));
            p1.setHora(request.getParameter("hora0"));
            p1.setCancha(Integer.parseInt(request.getParameter("cp0")));
            p1.setIdTorneo(idTorneo);
            pdao.actualizar(p1);
            StringBuilder emailsp1 = new StringBuilder("");
            ArrayList<String> correosp1eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp1eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo1")));
            correosp1eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo2"))));
            for (int i = 0; i < correosp1eq.size(); i++) {
                emailsp1.append(correosp1eq.get(i));
                if (i != correosp1eq.size() -1 && correosp1eq.size() > 0) {
                    emailsp1.append(" ,");
                }
            }
            //envio los correos
            String nequipo1 = request.getParameter("0nequipo1");
            String nequipo2 = request.getParameter("0nequipo2");
            String cuerpop1 = "El partido <strong>"+nequipo1+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo2
                    +"<br/>"
                    +"Sera el "+p1.getFecha()+" a las "+p1.getHora();
            Correo.sendMail(asunto, cuerpop1, emailsp1.toString());
            //fin del primer partido
            
            //comienzo con el segundo
            PartidoDTO p2 = new PartidoDTO();   
            p2.setRonda(ronda);
            p2.setEquipo1(Integer.parseInt(request.getParameter("1equipo1")));
            p2.setEquipo2(Integer.parseInt(request.getParameter("1equipo2")));
            p2.setFecha(request.getParameter("fecha1"));
            p2.setHora(request.getParameter("hora1"));
            p2.setCancha(Integer.parseInt(request.getParameter("cp1")));
            p2.setIdTorneo(idTorneo);
            pdao.actualizar(p2);//pilas aca se envia el dto con el partido no repetir
            StringBuilder emailsp2 = new StringBuilder("");//emails 
            ArrayList<String> correosp2eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp2eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("1equipo1")));
            correosp2eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("1equipo2"))));
            for (int i = 0; i < correosp2eq.size(); i++) {
                emailsp2.append(correosp2eq.get(i));
                if (i != correosp2eq.size() -1 && correosp2eq.size() > 0) {
                    emailsp2.append(" ,");
                }
            }
            //envio los correos
            String nequipo3 = request.getParameter("1nequipo1");
            String nequipo4 = request.getParameter("1nequipo2");
            String cuerpop2 = "El partido <strong>"+nequipo3+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo4
                    +"<br/>"
                    +"Sera el "+p2.getFecha()+" a las "+p2.getHora();
            Correo.sendMail(asunto, cuerpop2, emailsp2.toString());
            //fin del segundo
            
            }catch(MiExcepcion mi){
                response.sendError(500, mi.toString());
            }
            response.sendRedirect("resultados.jsp?idTorneo="+idTorneo);
        }else if(request.getParameter("asignarfechas")!=null && request.getParameter("ffinal")!=null){
            int ronda = 4;
            int idTorneo= Integer.parseInt(request.getParameter("idTorneo"));
            String asunto = "Notificacion horarios de partidos";
            PartidoDAO pdao = new PartidoDAO();
            EquiposDelTorneoDAO edt = new EquiposDelTorneoDAO();
            try{
            //comienzo con el primer partido
            PartidoDTO p1 = new PartidoDTO();   
            p1.setRonda(ronda);
            p1.setEquipo1(Integer.parseInt(request.getParameter("0equipo1")));
            p1.setEquipo2(Integer.parseInt(request.getParameter("0equipo2")));
            p1.setFecha(request.getParameter("fecha0"));
            p1.setHora(request.getParameter("hora0"));
            p1.setCancha(Integer.parseInt(request.getParameter("cp0")));
            p1.setIdTorneo(idTorneo);
            pdao.actualizar(p1);
            StringBuilder emailsp1 = new StringBuilder("");
            ArrayList<String> correosp1eq = new ArrayList();
            //array list con los correos de los jugadores de estos dos equipos
            correosp1eq = (ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo1")));
            correosp1eq.addAll((ArrayList) edt.correosJugadoresEquipo(idTorneo,Integer.parseInt(request.getParameter("0equipo2"))));
            for (int i = 0; i < correosp1eq.size(); i++) {
                emailsp1.append(correosp1eq.get(i));
                if (i != correosp1eq.size() -1 && correosp1eq.size() > 0) {
                    emailsp1.append(" ,");
                }
            }
            //envio los correos
            String nequipo1 = request.getParameter("0nequipo1");
            String nequipo2 = request.getParameter("0nequipo2");
            String cuerpop1 = "El partido de la final <strong>"+nequipo1+"</strong>"
                    +" <span>vs</span> <br/> "
                    + nequipo2
                    +"<br/>"
                    +"Sera el "+p1.getFecha()+" a las "+p1.getHora();
            Correo.sendMail(asunto, cuerpop1, emailsp1.toString());
            //fin del primer partido
            
            
            }catch(MiExcepcion mi){
                response.sendError(500, mi.toString());
            }
            response.sendRedirect("resultados.jsp?idTorneo="+idTorneo);
        }
        //
        //fin de fechas para eli de 16
        //
          else {
            response.sendRedirect("pages/inicio.jsp");
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
