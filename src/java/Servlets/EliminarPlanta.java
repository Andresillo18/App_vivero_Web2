package Servlets;

import Entidades.Planta;
import LogicaNegocio.LNPlanta;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
@WebServlet("/EliminarPlanta")
public class EliminarPlanta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter(); // para poder escribir en el HTML

        try {
            LNPlanta logica = new LNPlanta();

            String id = request.getParameter("idEliminar");
            // obtiene el parámetro del QUERY STRING y siempre será un string

            int codigo = Integer.parseInt(id);
            Planta plant = new Planta();
            plant.setCod_planta(codigo);

            int resultado = logica.Eliminar(plant);

            String mensaje = logica.getMensaje();

            mensaje = URLEncoder.encode(mensaje, "UTF-8");

            //Reenviamos a la página que estaba y se envía con un RESPONSE los parámetros por la URL
            response.sendRedirect("Frm_ListaPlantas.jsp?mensajeEliminar=" + mensaje + "&resultado=" + resultado);
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
