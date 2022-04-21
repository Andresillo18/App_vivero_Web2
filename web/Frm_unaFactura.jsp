<%-- 
    Document   : Frm_unaFactura
    Created on : 16 abr. 2022, 14:06:12
    Author     : Andrés Villalobos Y Redwin
--%>

<%@page import="Entidades.Empleado"%>
<%@page import="LogicaNegocio.LNEmpleado"%>
<%@page import="Entidades.Producto"%>
<%@page import="LogicaNegocio.LNProducto"%>
<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="Entidades.Cliente"%>
<%@page import="java.util.Date"%>
<%@page import="Entidades.Detalle_Factura"%>
<%@page import="java.util.List"%>
<%@page import="LogicaNegocio.LNDetalle_Factura"%>
<%@page import="LogicaNegocio.LNFactura"%>
<%@page import="Entidades.Factura"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facturación</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/><link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
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
                                <a class="nav-link" href="index.html"><i class="fas fa-home"></i> Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="Frm_ListaFacturas.jsp"><i class="fas fa-file-invoice-dollar"></i> Facturar</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaProductos.jsp"><i class="fas fa-tree"></i> Producto</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaClientes.jsp"><i class="fas fa-child"></i> Cliente</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaEmpleados.jsp"><i class="fas fa-user"></i> Empleado</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

        </header>
        <div class="container">

            <div class="card-header ">
                <h1 class="titulos">Crear o Modificar Datos</h1>
            </div>  


            <%
                int numFactura = -1;
                double total = 0;
                Factura EntidadFactura;
                Detalle_Factura EntidadDF; // Se crea una entidad para usar sus atributos
                LNFactura logicaFactura = new LNFactura();
                LNDetalle_Factura logicaDetalle = new LNDetalle_Factura();
                List<Detalle_Factura> DatosDetalles = null;

                //Esto se ejecuta si se envia algún parámetro
                if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    EntidadFactura = logicaFactura.ObtenerRegistro("DF.COD_FACTURA=" + numFactura);

                    EntidadDF = logicaDetalle.ObtenerRegistro("DF.COD_FACTURA=" + numFactura);

                    DatosDetalles = logicaDetalle.ListaRegistros("DF.COD_FACTURA=" + numFactura);
                } else {
                    EntidadFactura = new Factura();
                    EntidadFactura.setCod_factura(-1);

                    EntidadDF = new Detalle_Factura();

                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    EntidadDF.setFecha(fechasql);
                }
            %>
            <br/>
            <form action="RealizarFactura" method="post">
                <div class="form-group float-right">

                    <div class="input-group">
                        <label for="txtnumFactura" class="form-control">Num. Factura</label>
                        <input type="text" id="txtnumFactura" name="txtnumFactura" value="<%=EntidadFactura.getCod_factura()%>" 
                               readonly class="form-control"/>
                    </div>

                    <div class="input-group">
                        <label for="txtFechaFactura" class="form-control">Fecha</label>
                        <input type="text" id="txtFechaFactura" name="txtFechaFactura" value="<%=EntidadDF.getFecha()%>"
                               required class="datepicker form-control"/>
                    </div>

                </div>
                <br/>
                <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdCliente" name="txtIdCliente" value="<%=EntidadFactura.getCod_cliente()%>"
                               readonly="" class="form-control"/>
                        <input type="text" id="txtNombreCliente" name="txtNombreCliente" 
                               value="<%=EntidadFactura.getNombre_cliente()%>" readonly="" class="form-control"
                               placeholder="Seleccione un cliente"/>
                        &nbsp;&nbsp;<a id="btnbuscar" class="btn btn-success" data-toggle="modal"
                                       data-target="#buscarCliente"><i class="fas fa-search"></i></a>&nbsp;&nbsp; 
                        <!--Al darle al enlace de HTML llama un código del #, y dice que sea un modal para que solo se manipule esa-->

                        <input type="hidden" id="txtCodEmpleado" name="txtCodEmpleado" value="<%=EntidadFactura.getCod_empleado()%>"
                               readonly="" class="form-control"/>
                        <input type="text" id="txtNombreEmpleado" name="txtNombreEmpleado" 
                               value="<%=EntidadFactura.getNombre_empleado()%>" readonly="" class="form-control"
                               placeholder="Seleccione un Empleado"/>
                        &nbsp;&nbsp;<a id="btnbuscar" class="btn btn-success" data-toggle="modal"
                                       data-target="#buscarEmpleado"><i class="fas fa-search"></i></a> 
                    </div>
                </div>
                <hr/> <!-- Inicia el detalle de factura -->
                <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdProducto" name="txtIdProducto" value="" readonly="" class="form-control"/>
                        <input type="text" id="txtNombre" name="txtNombre" value="" class="form-control" readonly
                               placeholder="Seleccione un producto"/> &nbsp;&nbsp;
                        <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                            <i class="fas fa-search"></i></a>&nbsp;&nbsp;
                        <input type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control" 
                               placeholder="Cantidad"/> &nbsp;&nbsp;
                        <input type="number" id="txtprecio" name="txtprecio" value="" class="form-control" 
                               placeholder="Precio"/> 
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <input type="submit" name="Guardar" id="BtnGuardar" value="Agregar y Guardar" class="btn btn-primary"/>
                </div>
            </form>
            <hr/>
            <!-- Mostrar detalle de factura -->
            <h5>Detalle de Factura</h5>
            <table id="DetalleFactura" class="table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre del Prodcuto</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (DatosDetalles != null) {

                            for (Detalle_Factura registroDetalle : DatosDetalles) {
                    %>
                    <tr>
                        <%
                            int numfactura = registroDetalle.getCod_factura();
                            int codigop = registroDetalle.getCodProducto();

                            String nombre = registroDetalle.getNombreProducto();

                            int cantidad = registroDetalle.getCantDetalle();
                            double precioV = registroDetalle.getPrecio();
                            total += (cantidad * precioV);
                        %>
                        <td><%= codigop%></td>
                        <td><%= nombre%></td>
                        <td><%= cantidad%></td>
                        <td><%= precioV%></td>
                        <td><%= cantidad * precioV%></td>



                        <td>
                            <a href="EliminarDetalle?idproducto=<%=codigop%>&idfactura=<%=numfactura%>">Eliminar</a>
                        </td>
                    </tr>
                    <%
                            }// cierre de for
                        } // cierre del if
%>
                </tbody>
            </table>
            <div class="float-right">
                <p class="text-black-50">Total = </p>
                <%= total%>
            </div>
            <br><br>
            <input type="button" id="BtnRealizar" value="Realizar Facturacion"
                   onclick="location.href = 'RealizarFactura?txtnumFactura=' +<%= EntidadFactura.getCod_factura()%>"
                   class="btn btn-success"/>
            &nbsp;&nbsp;
            <a href="Frm_ListaFacturas.jsp" class="btn btn-secondary">Regresar</a>

        </div> <!-- container principal -->


        <!-- Modal de CLIENTE-->
        <div class="modal" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentaja">Buscar Cliente</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- tabla de clientes -->
                        <table id="tablaClientes">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNCliente logicaClientes = new LNCliente();
                                    List<Cliente> datosClientes;
                                    datosClientes = logicaClientes.ListaRegistros("");
                                    for (Cliente registroC : datosClientes) {
                                %>
                                <tr>
                                    <%int codigoCliente = registroC.getCod_cliente();
                                        String nombreCliente = registroC.getNombre();%>
                                    <td><%= codigoCliente%></td>
                                    <td><%= nombreCliente%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarCliente('<%=codigoCliente%>', '<%= nombreCliente%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div> <!-- modal body -->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                            Cancelar
                        </button>
                    </div>
                </div> <!-- modal content -->
            </div> <!-- modal dialog -->
        </div> <!-- modal -->

        <!-- Modal de EMPLEADO-->
        <div class="modal" id="buscarEmpleado" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentaja">Buscar Empleado</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- tabla de empleados -->
                        <table id="tablaEmpleados">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNEmpleado logicaEmpleados = new LNEmpleado();
                                    List<Empleado> datosEmpleado;
                                    datosEmpleado = logicaEmpleados.ListaRegistros("");
                                    for (Empleado registroC : datosEmpleado) {
                                %>
                                <tr>
                                    <%int codigoEmpleado = registroC.getCod_empleado();
                                        String nombreEmpleado = registroC.getNombre();%>
                                    <td><%= codigoEmpleado%></td>
                                    <td><%= nombreEmpleado%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarEmpleado('<%=codigoEmpleado%>', '<%= nombreEmpleado%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div> <!-- modal body -->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                            Cancelar
                        </button>
                    </div>
                </div> <!-- modal content -->
            </div> <!-- modal dialog -->
        </div> <!-- modal -->

        <!-- Modal de PRODUCTO -->
        <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Producto</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- tabla de productos -->
                        <table id="tablaProductos">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Descripción</th>
                                    <th>Precio</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNProducto logicaProductos = new LNProducto();
                                    List<Producto> datosProductos;
                                    datosProductos = logicaProductos.ListaRegistros("");
                                    for (Producto registroP : datosProductos) {
                                %>
                                <tr>
                                    <%int codigoProducto = registroP.getCodProducto();
                                        String nombreProducto = registroP.getNombre();
                                        double precio = registroP.getPrecio();%>
                                    <td><%= codigoProducto%></td>
                                    <td><%= nombreProducto%></td>
                                    <td><%= precio%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarProducto('<%=codigoProducto%>',
                                                           '<%= nombreProducto%>', '<%= precio%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div> <!-- modal body -->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" 
                                onclick="LimpiarProducto()">
                            Cancelar
                        </button>
                    </div>
                </div> <!-- modal content -->
            </div> <!-- mnodal dialog -->
        </div> <!-- modal -->

        <!--  Scripts requeridos -->
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script>

                                    //hacer que la lista de clientes se comporte como un datatable
                                    $(document).ready(function () {
                                        //mostrar calendario
                                        $('.datepicker').datepicker({
                                            format: 'yyyy-mm-dd',
                                            autoclose: true,
                                            language: 'es'
                                        });

                                        $('#tablaClientes').dataTable({
                                            "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                            "language": {
                                                "info": "Página _PAGE_ de _PAGES_",
                                                "infoEmpty": "No existen Registros disponibles",
                                                "zeroRecords": "No se encuentran registros",
                                                "search": "Buscar",
                                                "infoFiltered": "",
                                                "lengthMenu": "Mostrar _MENU_ Registros",
                                                "paginate": {
                                                    "first": "Primero",
                                                    "last": "Último",
                                                    "next": "Siguiente",
                                                    "previous": "Anterior"
                                                }

                                            }
                                        });

                                        //tabla empleados
                                        $('#tablaEmpleados').dataTable({
                                            "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                            "language": {
                                                "info": "Página _PAGE_ de _PAGES_",
                                                "infoEmpty": "No existen Registros disponibles",
                                                "zeroRecords": "No se encuentran registros",
                                                "search": "Buscar",
                                                "infoFiltered": "",
                                                "lengthMenu": "Mostrar _MENU_ Registros",
                                                "paginate": {
                                                    "first": "Primero",
                                                    "last": "Último",
                                                    "next": "Siguiente",
                                                    "previous": "Anterior"
                                                }

                                            }
                                        });
                                        //tabla de productos
                                        $('#tablaProductos').dataTable({
                                            "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                            "language": {
                                                "info": "Página _PAGE_ de _PAGES_",
                                                "infoEmpty": "No existen Registros disponibles",
                                                "zeroRecords": "No se encuentran registros",
                                                "search": "Buscar",
                                                "infoFiltered": "",
                                                "lengthMenu": "Mostrar _MENU_ Registros",
                                                "paginate": {
                                                    "first": "Primero",
                                                    "last": "Último",
                                                    "next": "Siguiente",
                                                    "previous": "Anterior"
                                                }

                                            }
                                        });

                                    });


                                    //seleccionar cliente
                                    function SeleccionarCliente(idCliente, nombreCliente) {
                                        $("#txtIdCliente").val(idCliente);
                                        $("#txtNombreCliente").val(nombreCliente);
                                    }
                                    //seleccionar empleado
                                    function SeleccionarEmpleado(idEmpleado, nombreEmpleado) {
                                        $("#txtCodEmpleado").val(idEmpleado);
                                        $("#txtNombreEmpleado").val(nombreEmpleado);
                                    }

                                    //seleccionar producto
                                    function SeleccionarProducto(idProducto, nombre, Precio) {
                                        $("#txtIdProducto").val(idProducto);
                                        $("#txtNombre").val(nombre);
                                        $("#txtprecio").val(Precio);
                                        $("#txtcantidad").focus();
                                    }

                                    //seleccionar cliente
                                    function Limpiar() {
                                        $("#txtIdCliente").val("");
                                        $("#txtNombreCliente").val("");
                                    }
                                    //seleccionar producto
                                    function LimpiarProducto() {
                                        $("#txtIdProducto").val("");
                                        $("#txtNombre").val("");
                                        $("#txtprecio").val("");
                                    }
        </script>
    </body>
</html>
