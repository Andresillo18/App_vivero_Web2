package Servlets;

import Entidades.Factura;
import LogicaNegocio.LNFactura;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 21-4-22
 *
 * @author Redwin & Andrés
 */
@WebServlet(name = "RealizarFactura", urlPatterns = {"/RealizarFactura"})
public class RealizarFactura extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            LNFactura Logica = new LNFactura();
            int idFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
            Factura EntidadFactura = Logica.ObtenerRegistro("COD_FACTURA=" + idFactura);
            
            int codEmpleado = Integer.parseInt(request.getParameter("txtCodEmpleado"));
            EntidadFactura.setCod_empleado(codEmpleado);
            
            int codCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
            EntidadFactura.setCod_cliente(codCliente);
            
            EntidadFactura.setEstado("Pagada");
            
            Logica.Modificar(EntidadFactura);
            response.sendRedirect("Frm_ListaFacturas.jsp");
        } catch (Exception e) {
            out.print(e.getMessage());
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
