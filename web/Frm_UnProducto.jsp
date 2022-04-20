    <%-- 
    Document   : Frm_Prod_Herra
    Created on : 13 abr. 2022, 19:35:54
    Author     : Andrés Villalobos Y Redwin
--%>

<%@page import="LogicaNegocio.LNProducto"%>
<%@page import="Entidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir o Modificar Herramientas - Productos</title>
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
                                <a class="nav-link" href="index.html"><i class="fas fa-home"></i> Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Frm_ListaFacturas.jsp"><i class="fas fa-file-invoice-dollar"></i> Facturar</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="Frm_ListaProductos.jsp"><i class="fas fa-tree"></i> Producto</a>
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
            <div class="row">
                <!--Se estaría mostrando en 6 columnas y centrado-->
                <div class="col-md-6 mx-auto">               

                    <div class="card-header">
                        <h1 class="titulos">Crear o Modificar Datos</h1>
                    </div>          

                    <%
                        Producto entidad;
                        LNProducto logica = new LNProducto();

                        int codProducto;

                        if (request.getParameter("codProd") != null
                                && !request.getParameter("codProd").equals("")) {
                            codProducto = Integer.parseInt(request.getParameter("codProd"));
                            entidad = logica.ObtenerRegistro("codProducto=" + codProducto);
                        } else {
                            entidad = new Producto();
                            entidad.setCodProducto(-1);
                        }

                    %>

                    <form action="CrearModificarProd" method="post" id="form_AgregarModificar">                    

                        <!-- contenedor para el cod -->
                        <div class="form-group">
                            <%if (entidad.getCodProducto() > 0) {%>
                            <!-- Si el producto existe, mostrará la etiqueta y el ID
                            -->

                            <label for="txtCodigo" class="control-label">Código</label>
                            <input type="txt" id="txtCodigo" name="txtCodigo" value="<%=entidad.getCodProducto()%>" readonly class="form-control"/>
                            <%} else {%>
                            <!-- Sino, el registro no existe-->
                            <input type="hidden" type="number" id="txtCodigo" name="txtCodigo" value="-1"/>
                            <%}%>
                        </div>

                        <!-- form-group para los controles de Tipo -->
                        <div class="form-group">
                            <label for="txtTipo" class="control-label">Tipo</label>
                            <br>
                            <select name="txtTipo" id="txtTipo" onchange="cambioTxtTipo(this.value)">
                                
                                
                                <%if (!entidad.getTipoProducto().equals("")) {
                                        %><option value="<%=entidad.getTipoProducto()%>"><%=entidad.getTipoProducto()%></option>
                                   <% } %>
                                
                                
                                <option value="Otro">Otro</option>
                                <option value="Artículo">Artículo</option>
                                <option value="Planta">Planta</option>
                                <option value="Químico">Químico</option>
                            </select>
                        </div>
                                   
                                   <button id="" class="btn btn-success" style="display: none" href="Frm_ListarCategorias.jsp">Categorías</button>

                        <!-- form-group para los controles de Nombre -->
                        <div class="form-group">
                            <label for="txtNombre" class="control-label">Nombre</label>
                            <input type="txt" id="txtNombre" name="txtNombre" value="<%=entidad.getNombre()%>" class="form-control"/>
                        </div>

                        <!-- para la descripción -->
                        <div class="form-group">
                            <label for="txtDescripcion" class="control-label">Descripción</label>
                            <input type="txt" id="txtDescripcion" name="txtDescripcion" value="<%=entidad.getDescripcion()%>" class="form-control"/>
                        </div>

                        <!-- para el precio -->
                        <div class="form-group">
                            <label for="txtPrecio" class="control-label">Precio</label>
                            <input type="number" min="0.0" id="txtPrecio" name="txtPrecio" value="<%=entidad.getPrecio()%>" class="form-control"/>
                        </div>

                        <!-- para la cantidad disponible-->
                        <div class="form-group">
                            <label for="txtCantidadDisponible" class="control-label">Cantidad disponible</label>
                            <input type="number" min="0" id="txtCantidadDisponible" name="txtCantidadDisponible" value="<%=entidad.getCantDisponible()%>" class="form-control"/>
                        </div>



                        <!-- form-group para los BOTONES de guardar y regresar  -->
                        <div class="form-group">
                            <div class="input-group">
                                <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'Frm_ListaProductos.jsp'" class="btn btn-secondary"/>
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
            function cambioTxtTipo(contenido){
                
                //alert(contenido);
                if(contenido == "Planta"){
                    alert(contenido);
                    document.getElementById("txtTipo").style.display = "inline";
                    alert(document.getElementById("txtTipo").value);
                }
            };
                                    // Cuando el documento está listo, el $ es de JQuery
                                    $(document).ready(function () {

                                        $("#form_AgregarModificar").validate({
                                            // Con estas reglas se personaliza, se usa el atributo name para verificar
                                            rules: {
                                                
                                                txtNombre: {required: true, maxlength: 30},
                                                txtDescripcion: {required: true, maxlength: 30},
                                                txtPrecio: {required: true},
                                                txtCantidadDisponible: {required: true}
                                            },
                                            // Mensajes que deseamos personalizar: 
                                            messages: {
                                                txtNombre: "El campo de Nombre es obligatorio (max 30 caracteres)",
                                                txtDescripcion: "El campo de Descripción es obligatorio (max 30 caracteres)",
                                                txtPrecio: "El campo de Precio es obligatorio",
                                                txtCantidadDisponible: "El campo de Cantidad Disponible es obligatorio"
                                            },
                                            errorElement: 'span'

                                        });
                                    });
        </script>

    </body>   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
