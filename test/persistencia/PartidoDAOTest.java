/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.PartidoDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeisson
 */
public class PartidoDAOTest {
    
    public PartidoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertar method, of class PartidoDAO.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        int ronda = 1;
        PartidoDTO cal = new PartidoDTO();
        cal.setRonda(1);
        cal.setEquipo1(23);
        cal.setEquipo2(23);
        cal.setIdTorneo(11);
        cal.setCancha(1);
        PartidoDAO instance = new PartidoDAO();
        String expResult = "Se insertaron los partidos";
        String result = instance.insertar(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class PartidoDAO.
     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        PartidoDTO cal = null;
//        PartidoDAO instance = new PartidoDAO();
//        String expResult = "";
//        String result = instance.eliminar(cal);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarTodo method, of class PartidoDAO.
//     */
//    @Test
//    public void testListarTodo() throws Exception {
//        System.out.println("listarTodo");
//        PartidoDAO instance = new PartidoDAO();
//        List<PartidoDTO> expResult = null;
//        List<PartidoDTO> result = instance.listarTodo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarUno method, of class PartidoDAO.
//     */
//    @Test
//    public void testListarUno() throws Exception {
//        System.out.println("listarUno");
//        int ronda = 0;
//        int idTorneo = 0;
//        PartidoDAO instance = new PartidoDAO();
//        List<PartidoDTO> expResult = null;
//        List<PartidoDTO> result = instance.listarUno(ronda, idTorneo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
