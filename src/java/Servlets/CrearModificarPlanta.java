package Servlets;

import Entidades.Planta;
import LogicaNegocio.LNPlanta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 13-4-22
 *
 * @author Andrés Villalobos
 */
@WebServlet("/CrearModificarPlanta")
public class CrearModificarPlanta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            LNPlanta Logica = new LNPlanta();
            Planta plant = new Planta();
            int resultado;

            plant.setCod_planta(Integer.parseInt(request.getParameter("txtCodigo")));

            plant.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));

            plant.setDescripcion(new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8"));

            plant.setPrecio(Float.parseFloat((new String(request.getParameter("txtPrecio").getBytes("ISO-8859-1"), "UTF-8"))));

            plant.setCantidad_disponible(Integer.parseInt(new String(request.getParameter("txtCantidadDisponible").getBytes("ISO-8859-1"), "UTF-8")));

            plant.setCantidad_Regado(Integer.parseInt(new String(request.getParameter("txtCantidadRegado").getBytes("ISO-8859-1"), "UTF-8")));

            plant.setTiempo_luz_solar(Float.parseFloat(new String(request.getParameter("txtCantidadLuz").getBytes("ISO-8859-1"), "UTF-8")));

            plant.setExtras_caracteristicas(new String(request.getParameter("txtOtrasCaract").getBytes("ISO-8859-1"), "UTF-8"));

            if (plant.getCod_planta() > 0) {
                resultado = Logica.Modificar(plant);
            } else {
                resultado = Logica.Insertar(plant);
            }

            response.sendRedirect("Frm_ListaPlantas.jsp");

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
