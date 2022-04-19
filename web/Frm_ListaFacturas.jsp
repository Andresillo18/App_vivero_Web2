<%-- 
    Document   : Frm_ListaFacturas
    Created on : 16 abr. 2022, 11:50:16
    Author     : Andrés Villalobos Y Redwin
--%>

<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.Empleado"%>
<%@page import="LogicaNegocio.LNEmpleado"%>
<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="Entidades.Factura"%>
<%@page import="java.util.List"%>
<%@page import="LogicaNegocio.LNFactura"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Facturas</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <header>            
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.html">Sistema Facturación del Vivero <i class="fas fa-leaf"></i></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="index.html">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Frm_ListaFacturas.jsp"><i class="fas fa-file-invoice-dollar"></i> Facturar</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Frm_ListaProductos.jsp">Productos</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    </header>
    <body>
        <div class="container">
            <div class="card-header">
                <h1>Listado de Facturas</h1>
            </div>
            <br/>
            <!--No habrá cuadro de busqueda ya que solo es para crear e informar la base de una factura***-->

            </hr>
            <table class="table">
                <thead>
                    <tr>
                        <th>Num. Factura</th>
                        <th>Cod Empleado</th>
                        <th>Cod Cliente</th>
                        <th>Opciones</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        LNFactura logFact = new LNFactura();
                        List<Factura> datos;
                        datos = logFact.ListaRegistros(""); // Se le envía vacío ya que no se ocupa en este caso
                        for (Factura registro : datos) {
                    %>
                    <tr>
                        <%int num = registro.getCod_factura();%>
                        <td><%= num%></td>
                        <td><%= registro.getNombre_empleado()%></td>
                        <td><%= registro.getNombre_cliente()%></td>
                        <td>
                            <a href="Frm_unaFactura.jsp?txtnumFactura=<%= num%>">
                                <i class="fas fa-cart-plus"></i></a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <br/>
            <a href="Frm_unaFactura.jsp?txtnumFactura=-1" class="btn btn-primary">Nueva Factura</a>
        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>

