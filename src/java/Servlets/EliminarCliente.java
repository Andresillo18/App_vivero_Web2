/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entidades.Cliente;
import LogicaNegocio.LNCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Progra
 */
@WebServlet(name = "EliminarCliente", urlPatterns = {"/EliminarCliente"})
public class EliminarCliente extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        try {
            LNCliente logica = new LNCliente();

            //Se verifica si se envío el parámetro que es el id del producto a eliminar
            if (request.getParameter("idEliminar") != null
                    && !request.getParameter("idEliminar").equals("")) {
                String id = request.getParameter("idEliminar");
                // obtiene el parámetro del QUERY STRING y siempre será un string

                int codigo = Integer.parseInt(id);
                Cliente cliente1 = new Cliente();
                cliente1.setCod_cliente(codigo);

                int resultado = logica.Eliminar(cliente1);

                String mensaje = logica.getMensaje();

                mensaje = URLEncoder.encode(mensaje, "UTF-8");

                //Reenviamos a la página que estaba y se envía con un RESPONSE los parámetros por la URL
                response.sendRedirect("Frm_ListaClientes.jsp?mensajeEliminar=" + mensaje + "&resultado=" + resultado);
            }

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
