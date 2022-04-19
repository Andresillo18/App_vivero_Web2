<%-- 
    Document   : Frm_UnCliente
    Created on : 19 abr. 2022, 13:51:26
    Author     : Redwin & Andrés
--%>

<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="Entidades.Detalle_Factura"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar un Cliente</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/>
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
                                <a class="nav-link" href="index.html">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaFacturas.jsp"><i class="fas fa-file-invoice-dollar"></i> Facturar</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaProductos.jsp">Producto</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaClientes.jsp">Cliente</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="">Empleado</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

        </header>
        <div class="container">
            <div class="row">
                <!--Se estaría mostrando en 6 columnas y centrado-->
                <div class="col-md-6 mx-auto">               

                    <div class="card-header">
                        <h1 class="titulos">Crear o Modificar Clientes</h1>
                    </div>          

                    <%
                        // Se puede conseguir el parámetro enviado por java o un servlet
                        String id = request.getParameter("idCrearModificar");
                        int codigo = Integer.parseInt(id);
                        Cliente cliente1;
                        LNCliente logica = new LNCliente();

                        if (codigo > 0) {
                            cliente1 = logica.ObtenerRegistro("COD_CLIENTE=" + id);
                        } else {
                            cliente1 = new Cliente();
                        }
                    %>

                    <form action="" method="post" id="form_AgregarModificar">                    

                        <!-- contenedor para el ID -->
                        <div class="form-group">
                            <%if (codigo > 0) {%>
                            <!-- Si el cliente existe, mostrará la etiqueta y el ID
                            -->

                            <label for="txtCodigo" class="control-label">Código</label>
                            <input type="number" id="txtCodigo" name="txtCodigo" value="<%=cliente1.getCod_cliente()%>" readonly class="form-control"/>
                            <%} else {%>
                            <!-- Sino, el registro no existe-->
                            <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/>
                            <%}%>
                        </div>

                        <!-- form-group para la identificación-->
                        <div class="form-group">
                            <label for="txtID" class="control-label">ID</label>
                            <input type="text" id="txtID" name="txtID" value="<%=cliente1.getId()%>" class="form-control"/>
                        </div>

                        <!-- form-group para los controles de Nombre -->
                        <div class="form-group">
                            <label for="txtNombre" class="control-label">Nombre</label>
                            <input type="txt" id="txtNombre" name="txtNombre" value="<%=cliente1.getNombre()%>" class="form-control"/>
                        </div>

                        <!-- para el apellido -->
                        <div class="form-group">
                            <label for="txtApellido" class="control-label">Apellido</label>
                            <input type="txt" id="txtApellido" name="txtApellido" value="<%=cliente1.getApellido1()%>" class="form-control"/>
                        </div>

                        <!-- para el telefono -->
                        <div class="form-group">
                            <label for="txtTelefono" class="control-label">Teléfono</label>
                            <input type="number" id="txtTelefono" name="txtTelefono" value="<%=cliente1.getTelefono()%>" class="form-control"/>
                        </div>

                        <!-- para el estado -->
<!--                        <div class="form-group">
                            <label for="txtActivo" class="control-label">Estado</label>
                            <input type="number" id="txtCantidadDisponible" name="txtCantidadDisponible" value="<%%>" class="form-control"/>
                            <input type="radio" id="txtActivo" name="txtActivo" value="<%=%>">
                            <label for="html">HTML</label><br>
                            <input type="radio" id="css" name="fav_language" value="CSS">
                        </div>       -->

                        <!-- form-group para los BOTONES de guardar y regresar  -->
                        <div class="form-group">
                            <div class="input-group">
                                <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'Frm_Lista_ProdHerra.jsp'" class="btn btn-secondary"/>
                                <!-- No estamos haciendo un RESPONSE porque no esta enviando ningún parámetro -->
                            </div>
                        </div>

                    </form>

                </div> <!-- clase que crea las 6 columnas -->

            </div> <!-- class row, div de la fila -->

        </div> <!-- class container -->

        <!-- Agregamos las referencias a Bootstrap, jquery y jquery-validation -->
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>    

        <script>
                                    // Cuando el documento está listo, el $ es de JQuery
                                    $(document).ready(function () {

                                        $("#form_AgregarModificar").validate({
                                            // Con estas reglas se personaliza, se usa el atributo name para verificar
                                            rules: {

                                                txtNombre: {required: true, maxlength: 30},
                                                txtDescripcion: {required: true, maxlength: 60},
                                                txtPrecio: {required: true},
                                                txtCantidadDisponible: {required: true}
                                            },
                                            // Mensajes que deseamos personalizar: 
                                            messages: {
                                                txtNombre: "El campo de Nombre es obligatorio (max 30 caracteres)",
                                                txtDescripcion: "El campo de Descripción es obligatorio (max 60 caracteres)",
                                                txtPrecio: "El campo de Precio es obligatorio",
                                                txtCantidadDisponible: "El campo de Cantidad Disponible es obligatorio",
                                                txttxtCantidadDisponible: "El campo de la Cantidad Disponible es obligatorio"
                                            },
                                            errorElement: 'span'

                                        });
                                    });
        </script>

    </body>   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
