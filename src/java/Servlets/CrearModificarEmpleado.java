package Servlets;

import Entidades.Empleado;
import LogicaNegocio.LNEmpleado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 19-4-22
 *
 * @author Red & Andrés
 */
@WebServlet(name = "CrearModificarEmpleado", urlPatterns = {"/CrearModificarEmpleado"})
public class CrearModificarEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Siempre se debe crear el objeto out para dar un respuesta
        PrintWriter out = response.getWriter();

        try {
            LNEmpleado Logica = new LNEmpleado();
            Empleado empleado1 = new Empleado();
            int resultado;

            empleado1.setCod_empleado(Integer.parseInt(request.getParameter("txtCodigo")));

            empleado1.setId(Integer.parseInt(new String(request.getParameter("txtID").getBytes("ISO-8859-1"), "UTF-8")));

            empleado1.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));

            empleado1.setApellido1(new String(request.getParameter("txtApellido").getBytes("ISO-8859-1"), "UTF-8"));

            empleado1.setTelefono((new String(request.getParameter("txtTelefono").getBytes("ISO-8859-1"), "UTF-8")));

            empleado1.setVentas_realizadas(Integer.parseInt((new String(request.getParameter("txtVentasRealizadas").getBytes("ISO-8859-1"), "UTF-8"))));

            empleado1.setBono(Float.parseFloat(new String(request.getParameter("txtBono").getBytes("ISO-8859-1"), "UTF-8")));

            empleado1.setEstado(true);

            if (empleado1.getCod_empleado() > 0) {
                resultado = Logica.Modificar(empleado1);
            } else {
                resultado = Logica.Insertar(empleado1);
            }

            response.sendRedirect("Frm_ListaEmpleados.jsp");

        } catch (Exception ex) {
            out.print(ex.getMessage());
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
