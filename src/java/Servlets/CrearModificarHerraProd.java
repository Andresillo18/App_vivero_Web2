package Servlets;

import Entidades.Producto;
import LogicaNegocio.LNHerram_Prod;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 14-4-22
 *
 * @author Andrés Villalobos Y Redwin
 */
@WebServlet(name = "CrearModificarHerraProd", urlPatterns = {"/CrearModificarHerraProd"})
public class CrearModificarHerraProd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Siempre se debe crear el objeto out para dar un respuesta
        PrintWriter out = response.getWriter();

        try {
            LNHerram_Prod Logica = new LNHerram_Prod();
            Producto HP = new Producto();
            int resultado;

            HP.setCantidad_disponible(Integer.parseInt(request.getParameter("txtCodigo")));

            HP.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));

            HP.setDescripcion(new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8"));

            HP.setPrecio(Float.parseFloat((new String(request.getParameter("txtPrecio").getBytes("ISO-8859-1"), "UTF-8"))));

            HP.setCantidad_disponible(Integer.parseInt(new String(request.getParameter("txtCantidadDisponible").getBytes("ISO-8859-1"), "UTF-8")));

            HP.setMaterial(new String(request.getParameter("txtMaterial").getBytes("ISO-8859-1"), "UTF-8"));

            if (request.getParameter("txtFechaVencimiento") != null) {
                HP.setFechaVencimiento(Date.valueOf(new String(request.getParameter("txtFechaVencimiento").getBytes("ISO-8859-1"), "UTF-8"))); //****
            } else {
                HP.setFechaVencimiento(null); //****
            }

            if (HP.getCod_herramienta_prod() > 0) {
                resultado = Logica.Modificar(HP);
            } else {
                resultado = Logica.Insertar(HP);
            }

            response.sendRedirect("Frm_Lista_ProdHerra.jsp");

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
