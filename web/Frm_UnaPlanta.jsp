<%-- 
    Document   : añadirModificarPlanta
    Created on : 13 abr. 2022, 15:19:48
    Author     : Andrés Villalobos
--%>

<%@page import="LogicaNegocio.LNPlanta"%>
<%@page import="Entidades.Planta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir o Modificar Plantas</title>        
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
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="index.html" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Inventario
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="Frm_ListaPlantas.jsp"><i class="fas fa-tree"></i> Plantas</a></li>
                                <li><a class="dropdown-item" href="Frm_Lista_ProdHerra.jsp"><i class="fas fa-toolbox"></i> Productos o Herramientas</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="fas fa-file-invoice-dollar"></i> Facturar</a>
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
                    // Se puede conseguir el parámetro enviado por java o un servlet
                    String id = request.getParameter("idCrearModificar");
                    int codigo = Integer.parseInt(id);
                    Planta plant;
                    LNPlanta logica = new LNPlanta();

                    if (codigo > 0) {
                        plant = logica.ObtenerRegistro("COD_PLANTA=" + id);
                    } else {
                        plant = new Planta();
                    }
                %>

                <form action="CrearModificarPlanta" method="post" id="form_AgregarModificar">                    

                    <!-- contenedor para el ID -->
                    <div class="form-group">
                        <%if (codigo > 0) {%>
                        <!-- Si el cliente existe, mostrará la etiqueta y el ID
                        -->

                        <label for="txtCodigo" class="control-label">Código</label>
                        <input type="number" id="txtCodigo" name="txtCodigo" value="<%=plant.getCod_planta()%>" readonly class="form-control"/>
                        <%} else {%>
                        <!-- Sino, el registro no existe-->
                        <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/>
                        <%}%>
                    </div>

                    <!-- form-group para los controles de Nombre -->
                    <div class="form-group">
                        <label for="txtNombre" class="control-label">Nombre</label>
                        <input type="txt" id="txtNombre" name="txtNombre" value="<%=plant.getNombre()%>" class="form-control"/>
                    </div>

                    <!-- para la descripción -->
                    <div class="form-group">
                        <label for="txtDescripcion" class="control-label">Descripción</label>
                        <input type="txt" id="txtDescripcion" name="txtDescripcion" value="<%=plant.getDescripcion()%>" class="form-control"/>
                    </div>

                    <!-- para el precio -->
                    <div class="form-group">
                        <label for="txtPrecio" class="control-label">Precio</label>
                        <input type="number" id="txtPrecio" name="txtPrecio" value="<%=plant.getPrecio()%>" class="form-control"/>
                    </div>

                    <!-- para la cantidad disponible-->
                    <div class="form-group">
                        <label for="txtCantidadDisponible" class="control-label">Cantidad disponible</label>
                        <input type="number" id="txtCantidadDisponible" name="txtCantidadDisponible" value="<%=plant.getCantidad_disponible()%>" class="form-control"/>
                    </div>

                    <!-- para el cantidad para regar -->
                    <div class="form-group">
                        <label for="txtCantidadRegado" class="control-label">Cantidad de Regado</label>
                        <input type="number" id="txtCantidadRegado" name="txtCantidadRegado" value="<%=plant.getCantidad_Regado()%>" class="form-control"/>
                    </div>

                    <!-- para el tiempo luz-->
                    <div class="form-group">
                        <label for="txtCantidadLuz" class="control-label">Tiempo de luz solar</label>
                        <input type="number" id="txtCantidadLuz" name="txtCantidadLuz" value="<%=plant.getTiempo_luz_solar()%>" class="form-control"/>
                    </div>

                    <!-- para extras caracteristicas -->
                    <div class="form-group">
                        <label for="txtOtrasCaract" class="control-label">Otras características</label>
                        <input type="text" id="txtOtrasCaract" name="txtOtrasCaract" value="<%=plant.getExtras_caracteristicas()%>" class="form-control"/>
                    </div>

                    <!-- form-group para los BOTONES de guardar y regresar  -->
                    <div class="form-group">
                        <div class="input-group">
                            <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                            <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'Frm_ListaPlantas.jsp'" class="btn btn-secondary"/>
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
                                            txtCantidadDisponible: {required: true},
                                            txtCantidadRegado: {required: true},
                                            txtCantidadLuz: {required: true},
                                            txtOtrasCaract: {maxlength: 100}
                                        },
                                        // Mensajes que deseamos personalizar: 
                                        messages: {
                                            txtNombre: "El campo de Nombre es obligatorio (max 30 caracteres)",
                                            txtDescripcion: "El campo de Descripción es obligatorio (max 60 caracteres)",
                                            txtPrecio: "El campo de Precio es obligatorio",
                                            txtCantidadDisponible: "El campo de Cantidad Disponible es obligatorio",                        
                                            txttxtCantidadDisponible: "El campo de la Cantidad Disponible es obligatorio",
                                            txtCantidadRegado: "El campo de la Cantidad de Regado es obligatorio",
                                            txtCantidadLuz: "El campo de la Cantidad de Luz es obligatorio",
                                            txtOtrasCaract: "El campo de Otra Características es como máximo 100"
                                        },
                                        errorElement: 'span'

                                    });
                                });
    </script>

</body>   
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
