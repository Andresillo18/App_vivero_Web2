<%-- 
    Document   : Frm_Categorias
    Created on : 20 abr. 2022, 16:16:35
    Author     : Progra
--%>

<%@page import="java.util.List"%>
<%@page import="Entidades.Categoria"%>
<%@page import="LogicaNegocio.LNCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Categorías</title>
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
            <div class="card-header">
                <h1 class="titulos text-center">Lista de Categorías</h1>
            </div>
            <br/>

            <!-- El form hará un postback cuando se le da al botón de buscar, entonces se estará llamando a este mismo pero con lo que tenga el campo de texto para buscar
            -->

            <form action="Frm_ListarCategorias.jsp" method="post">                
                <div class="form-group">
                    <div class="input-group">                      
                        <input type="text" id="txtnombre" name="txtnombre" value="" placeholder="Busqueda por nombre..."
                               class="form-control"/> &nbsp; &nbsp; 
                        <!--Cuando se carga por primera vez no se ha creado el parámetro txtnombre, se crea hasta que se envíe por un form, cuando se hace el postback se crea y se puede saber si tiene algo-->

                        <input type="submit" id="btnbuscar" name="btnBuscar" value="Buscar" class="btn btn-primary"/>
                    </div>
                </div>
            </form>
            <hr/>
            <table class="table">
                <thead>
                    <tr id="titulos">
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Descripción</th>                        
                        <th>Seleccionar</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 
                        Este bloque de código java permite usar sentencias y se hará una consulta a la BD
                    -->
                    <%
                        String nombre = "";
                        String condicion = "";
                        String tipo = "";

                        // Si el contenido del cuadro de texto NO es vacío:
                        //Obtiene el parámetro por el name de las etiquetas y si esta vacío no envía condición
                        if (request.getParameter("txtnombre") != null) {
                            nombre = request.getParameter("txtnombre");
                            condicion = "NOMBRE_CATEGORIA LIKE '%" + nombre + "%'";
                        }

                        LNCategoria logica = new LNCategoria();
                        List<Categoria> datos;
                        //Se usa una lista para tener los registros completos y no un resultset
                        datos = logica.ListaRegistros(condicion);

                        for (Categoria registro : datos) {
                            //El for no cierra aún
                    %>

                    <tr>
                        <%int cod = registro.getCod_categoria();%>

                        <!--Este código insertado de java permite solo mostrar datos del mismo java, son Expresiones-->
                        <td><%= cod%></td> 
                        <td><%= registro.getNombre_categoria()%></td>
                        <td><%= registro.getDescripcion()%></td>

                        <!-- *Columna adicional*-->
                        <td>
                            <!--Es una petición get (se ve en la URL), enviamos un parámetro por Query String -->
                            <a href="Frm_UnProducto.jsp?codProd=<%=cod%>">Seleccionar</a> 
                        </td>
                    </tr>

                    <%}%>
                </tbody>
            </table>
            <br>

            <a href="Frm_UnaCategoria.jsp?idCrearModificar=-1">Agregar Nueva Categoría</a> |
            <a href="Frm_ListarCategorias.jsp">Actualizar</a>
            <br><br>
            <a href="index.html">Regresar al Index</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>            
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>        

    </body>
</html>
