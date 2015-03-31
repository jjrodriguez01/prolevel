/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.UsuariosDTO;
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
public class UsuariosDAOTest {
    
    public UsuariosDAOTest() {
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
     * Test of insertar method, of class UsuariosDAO.
     */
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        UsuariosDTO usu = null;
//        UsuariosDAO instance = new UsuariosDAO();
//        String expResult = "";
//        String result = instance.insertar(usu);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actualizar method, of class UsuariosDAO.
//     */
//    @Test
//    public void testActualizar() {
//        System.out.println("actualizar");
//        UsuariosDTO usu = null;
//        UsuariosDAO instance = new UsuariosDAO();
//        String expResult = "";
//        String result = instance.actualizar(usu);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminar method, of class UsuariosDAO.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        Long id = null;
//        UsuariosDAO instance = new UsuariosDAO();
//        String expResult = "";
//        String result = instance.eliminar(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarTodo method, of class UsuariosDAO.
//     */
//    @Test
//    public void testListarTodo() {
//        System.out.println("listarTodo");
//        UsuariosDAO instance = new UsuariosDAO();
//        List<UsuariosDTO> expResult = null;
//        List<UsuariosDTO> result = instance.listarTodo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarUno method, of class UsuariosDAO.
//     */
//    @Test
//    public void testListarUno() {
//        System.out.println("listarUno");
//        Long id = null;
//        UsuariosDAO instance = new UsuariosDAO();
//        UsuariosDTO expResult = null;
//        UsuariosDTO result = instance.listarUno(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validarUsuario method, of class UsuariosDAO.
//     */
    @Test
    public void testValidarUsuario() {
        System.out.println("validarUsuario");
        String email = "hdyhdbddhb";
        String password = "21545454";
        UsuariosDAO instance = new UsuariosDAO();
        UsuariosDTO expResult = null;
        UsuariosDTO result = instance.validarUsuario(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of ValidarRol method, of class UsuariosDAO.
     */
//    @Test
//    public void testValidarRol() {
//        System.out.println("ValidarRol");
//        UsuariosDTO usu = new UsuariosDTO();
//        usu.setIdUsuario((long)1016036010);
//        UsuariosDAO instance = new UsuariosDAO();
//        boolean expResult = false;
//        boolean result = instance.ValidarRol(usu);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    /**
     * Test of recuperar method, of class UsuariosDAO.
     */
//    @Test
//    public void testRecuperar() {
//        System.out.println("recuperar");
//        String email = "";
//        UsuariosDAO instance = new UsuariosDAO();
//        String expResult = "";
//        String result = instance.recuperar(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
