package Servlets;

import Entidades.Producto;
import LogicaNegocio.LNProducto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CrearModificarProd", urlPatterns = {"/CrearModificarProd"})
public class CrearModificarProd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Siempre se debe crear el objeto out para dar un respuesta
        PrintWriter out = response.getWriter();

        try {
            LNProducto Logica = new LNProducto();
            Producto prod = new Producto();
            int resultado;

            prod.setCodProducto(Integer.parseInt(request.getParameter("txtCodigo")));

            prod.setTipoProducto(new String(request.getParameter("txtTipo").getBytes("ISO-8859-1"), "UTF-8"));

            prod.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));

            prod.setDescripcion(new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8"));

            prod.setPrecio(Float.parseFloat((new String(request.getParameter("txtPrecio").getBytes("ISO-8859-1"), "UTF-8"))));

            prod.setCantDisponible(Integer.parseInt(new String(request.getParameter("txtCantidadDisponible").getBytes("ISO-8859-1"), "UTF-8")));

            if (prod.getCodProducto() > 0) {
                resultado = Logica.Modificar(prod);
            } else {
                resultado = Logica.Insertar(prod);
            }

            response.sendRedirect("Frm_ListaProductos.jsp");

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
