package Servlets;

import Entidades.Detalle_Factura;
import Entidades.Factura;
import LogicaNegocio.LNDetalle_Factura;
import LogicaNegocio.LNFactura;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 20-4-22
 *
 * @author Redwin & Andrés
 */
@WebServlet(name = "Facturar", urlPatterns = {"/Facturar"})
public class Facturar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            LNFactura LogicaFactura = new LNFactura();
            LNDetalle_Factura LogicaDetalle_Factura = new LNDetalle_Factura();
            Factura EntidadFactura = new Factura();
            Detalle_Factura EntidadDetalle = new Detalle_Factura();
            int resultado = 0;
            int resultadoFacturaCod = 0;

            //crear entidad de factura
            if (request.getParameter("txtNombreCliente") != null
                    && !request.getParameter("txtNombreCliente").equals("")) {
                //Se guarda el cod del cliente en la entidad
                EntidadFactura.setCod_cliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                //Se guarda el cod de la factura en la entidad
                EntidadFactura.setCod_factura(Integer.parseInt(request.getParameter("txtnumFactura")));
                //Se guarda la fecha en la entidad
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString = request.getParameter("txtFechaFactura");
                Date fecha = formato.parse(fechaString);
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                EntidadFactura.setFecha(fechasql);
                //Se guarda el cod del empleado en la entidad
                EntidadFactura.setCod_empleado(Integer.parseInt(request.getParameter("txtCodEmpleado")));

//                EntidadFactura.setEstado("Pendiente");
                // si es diferente a -1
                if (Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    
                    
                    //LogicaFactura.Modificar(EntidadFactura);
                    
                    EntidadDetalle.setCod_factura(Integer.parseInt(request.getParameter("txtnumFactura")));
                    
                    EntidadDetalle.setCodProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
                    EntidadDetalle.setCantDetalle(Integer.parseInt(request.getParameter("txtcantidad")));
                    EntidadDetalle.setTotal_pagar(0);
                    
                    LogicaDetalle_Factura.Insertar(EntidadDetalle);
                    
                    resultadoFacturaCod = EntidadDetalle.getCod_factura();

                // si es igual a -1
                } else {
                    //crear entidad de detalle
                    //Obtiene los valores de los campos de texto para crear la entidad
                    EntidadDetalle.setCod_detalle(-1);

                    EntidadDetalle.setCodProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
                    EntidadDetalle.setCantDetalle(Integer.parseInt(request.getParameter("txtcantidad")));
                    EntidadDetalle.setTotal_pagar(0);
                    EntidadDetalle.setPrecio(Float.parseFloat(request.getParameter("txtprecio")));
                    EntidadDetalle.setObservaciones("");
                    //Inserta o Modifica
                    resultadoFacturaCod = LogicaFactura.Insertar(EntidadFactura);
                    EntidadDetalle.setCod_factura(resultadoFacturaCod);
                    resultado = LogicaDetalle_Factura.Insertar(EntidadDetalle);

                }
                response.sendRedirect("Frm_unaFactura.jsp?txtnumFactura=" + resultadoFacturaCod);
            } else {
                response.sendRedirect("Frm_unaFactura.jsp?txtnumFactura="
                        + request.getParameter("txtnumFactura"));
            }
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
