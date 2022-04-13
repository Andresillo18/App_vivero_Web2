<%-- 
    Document   : Frm_ListaPlanta
    Created on : 12 abr. 2022, 16:17:35
    Author     : Andrés Villalobos
--%>

<%@page import="Entidades.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>

        <title>Listado de Clientes</title>
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
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="index.html" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Inventario
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <li><a class="dropdown-item" href="Frm_ListaPlanta"><i class="fas fa-tree"></i> Plantas</a></li>
                                    <li><a class="dropdown-item" href="#"><i class="fas fa-toolbox"></i> Productos o Herramientas</a></li>
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

        <div class="container">  <!-- container y card-header son clases de BOOTSTRAP -->
            <div class="card-header">
                <h1>Listado de Clientes</h1>
            </div>
            <br/>

            <!-- Este formulario se carga a si mismo (a la misma página), 
                va a cargar una lista con CargarRegistros de la Logica de Negocio 
                y el parámetro que se le va a enviar es lo que esté dentro del 
                cuadro de texto (que contiene el nombre a buscar, o parte 
                del nombre a buscar). 
                Hace PostBack (envío de vuelta), pero cuando hace PostBack 
                lo que hace es llamarse a sí mismo. 
                Originalmente va cargar todos los registros porque el cuadro de 
                texto está vacío.
                Debe llamarse a sí mismo, porque al precionar el botón Buscar 
                realizará PostBack, y lo que debe cargar es la misma página
                pero mostrando solamente los clientes que coincidan con lo que 
                se escribió en el campo de Texto. 
            -->

            <form action="FrmListarClientes.jsp" method="post">
                <!-- 
                    Se utiliza la ACCIÓN y MÉTODOS de HTML (a diferencia de ASP 
                    en c# donde no se utilizaba el Action de HTML sino que se 
                    utilizaba el AspACCION que invocaba al método del controlador)
                    
                    En este ejemplo el ACTION llama al mismo formulario, por ende 
                    se escribe el mismo nombre y el método es
                    POST para que el parámetro vaya ENCAPSULADO en la petición
                -->
                <div class="form-group">
                    <div class="input-group">
                        <!-- al crear controles de Formulario con BOOSTRAP se colocan de forma Vertical, 
                            para que ambos queden en la misma línea se hace utilizando este DIV de input-group, 
                            que lo que hace es colocar todos los controles que estén dentro del 
                            input-group en forma Horizontal.
                            Es decir que el campo de texto y el Botón de buscar, estarán en la misma línea.
                        -->

                        <!-- lo más importante es el atributo NAME, porque por medio del NAME se van a definir los PARÁMETROS
                            de la petición HTTP, cuando se envíe el formulario ese formulario va a llevar un Parámetro 
                            que va a ser el nombre de cliente que digitó el usuario, y ese parámetro se va a llamar: 
                            txtnombre
                        -->
                        <input type="text" id="txtnombre" name="txtnombre" value="" placeholder="Buscar por nombre..." 
                               class="form-control"/>&nbsp; &nbsp;

                        <!-- Cuando tenemos un formulario, la ACCIÓN DEL FORMULARIO 
                        (en este caso action="FrmListarClientes.jsp" method="post") 
                        se EJECUTA al presionar el botón de SUBMIT
                        -->
                        <input type="submit" id="btnbuscar" name="btnBuscar" value="Buscar" class="btn btn-primary"/><br><br>
                    </div>
                </div>
            </form>
            <hr/>
            <table class="table">
                <thead>
                    <tr id="titulos">
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Teléfono</th>
                <a href="../../../../OneDrive/Documentos/Programación- Andrés/Módulo-Java/Lecciones/Lección 23/EjemploWebCapas_Base_Terminado/web/FrmListarFacturas.jsp"></a>
                        <th>Dirección</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 
                        Para poder llenar el cuerpo se necesita hacer una consulta para saber cuáles registros hay
                        y con base en esos registros de la BD cargar la tabla de HTML
                        Para ello hacemos este bloque de código de Java:
                    -->
                    <%
                        String nombre = "";
                        String condicion = "";
                        // verificar si el campo de txtnombre tiene algo

                        /*
                            Para obtener un parámetro se hace uso de los objetos INTRÍNSECOS (ya vienen definidos)
                            que tiene todo JSP:
                                REQUEST - para leer parámetros y otros datos de la solicitud
                                RESPONSE - para tramitar una respuesta 
                                SESSION - para GUARDAR DATOS en Sesion
                        
                            request no se tiene que declarar ya que es un objeto intrínseco de todo JSP, viene definido.
                            Si estamos en un Servlet o en un JSP SIEMPRE VAN A ESTAR DISPONIBLES.
                        
                            Con el método getParameter se lee un parámetro específico de la solicitud, este método recibe 
                            un string que debe contener el NOMBRE DEL PARÁMETRO QUE DESEAMOS ACCEDER, en este caso el único 
                            input que tenemos en el formulario: txtnombre
                            getParameter va a devolver siempre un STRING, por eso nombre y condicion se declararon como String
                            si la variable es numérica igual se obtienen en formato String y luego deben parsearse
                         */
                        // Si el contenido del cuadro de texto NO es vacío:
                        if (request.getParameter("txtnombre") != null) {
                            // tambien se puede agregar en la comprobación  !__.equals(""), en este caso no se ocupa porque 
                            // si es igual a "" retorna todos los registros, lo cual es correcto que retorne todos los registros
                            // cuando el txt está sin escribirle nada.

                            // si tiene algo construimos una CONDICIÓN SQL para enviarla el procedimiento de ListarRegistros
                            nombre = request.getParameter("txtnombre");
                            condicion = "NOMBRE LIKE '%" + nombre + "%'";
                        }
                        BL_Cliente logica = new BL_Cliente();
                        List<Cliente> datos;
                        datos = logica.ListarRegistros(condicion);
                        // Se usa el método que devuelve una LISTA porque si la conexión se cerrara NO PODRÍA UTILIZARSE
                        // el ResultSet, por ende para tener independencia de JDBC. 
                        // Por ello es recomendable mejor utilizar LISTAS, no utilizar ResultSets

                        for (Cliente registro : datos) {
                            // es un FOR MEJORADO, se aplica al Arreglo Cliente
                            // abre llave pero NO CIERRA, porque lo que sige es código HTML
                            // El CONTENIDO DEL FOR van a ser un montón de etiquetas HTML
                            // cada iteración del FOR va a crear el código necesario para 
                            // incluir una FILA (tr) de la tabla en código HTML
                    %>

                    <tr>
                        <%int codigo = registro.getId_cliente();%> <!-- cada línea DEBE terminar con ; porque es un BLOQUE 
                                                                    Si empezara con %@ no ocuparia punto y coma al final-->

                        <td><%= codigo%></td> <!-- estos no terminan con ; porque son Expresiones -->
                        <td><%= registro.getNombre()%></td>
                        <td><%= registro.getTelefono()%></td>
                        <td><%= registro.getDireccion()%></td>

                        <!-- Columna adicional con botones de opciones: -->
                        <td>
                            <a href="Frm_Clientes.jsp?idCrearModificar=<%=codigo%>"><i class="fas fa-user-edit"></i></a> |
                            <!--
                                Este primer botón llamaría a una página donde se pueda EDITAR los clientes
                                Llama a un archivo .JSP ( Frm_Clientes.jsp )
                            
                                Cuando utilizamos un símbolo de pregunta dentro de una URL sirve para pasar un PARÁMETRO.
                                El simbolo de ? se llama QUERY STRING y sirve para pasar parámetros, son peticiones GET 
                                (porque estamos pasando el parámetro por medio de la URL)
                            
                                Sintaxis: 
                                    - símbolo de pregunta ( ? )
                                    - el nombre del la variable
                                    - símbolo de igual que ( = ) 
                                    - valor que asignamos a esa variable 
                                                            
                                si ocupamos pasar VARIOS PARÁMETROS se separan con símbolo &
                                (sólo el primero lleva el ?)
                            -->
                            <a href="EliminarCliente?idEliminar=<%=codigo%>"><i class="fas fa-trash-alt"></i></a>
                            <!-- 
                                Este segundo botón se utilizaría para ELIMINAR un cliente.
                                Llama a un SERVLET (lo sabemos porque NO tiene extension .JSP), ese significa que 
                                ElimianrCliente es un SERVLET (no un JSP)
                                Un servlet es una CLASE JAVA que permite procesar PETICIONES HTTP, ya sea un GET, POST, DELETE, etc
                                Aqui tenemos peticiones GET, entonces en el SERVLET debemos programar peticiones GET
                            
                                el PARÁMETRO ?id=  pudo haberse llamado de cualquier forma que queramos (no necesariamente "id", por 
                                ejemplo "abc")
                                El nombre que le asignemos es con el nombre que debemos llamar a ese parámetro desde el Servlet
                                con la instrucción: String identificacionCliente = request.getParameter("abc");
                            -->
                        </td>
                    </tr>

                    <%}%> <!--Cerrar la llave del FOR de JAVA
                                La llave se cierra donde se termina la fila, la table row tr-->
                </tbody>
            </table>
            <br>

            <%
                // en caso que se hubiera eliminado un registro, muestra el mensaje
                // si no se hizo una eliminación ese mensaje es NULO, entonces este IF no se hace,
                // esta variable "mensaje" se originará en el archivo SERVLET EliminarCliente.java

                // El siguiente código es necesario porque se está haciendo la eliminación directamente en esta página. 
                // Si estuviéramos trabajando otra ventana aparte para eliminar y confimar la eliminación, 
                // no se ocuparía hacer esto en esta página. 
                if (request.getParameter("mensajeServletEliminarCliente") != null) {
                    /*  Únicamente cuando el Servelet EliminarCliente llama a esta página asignándole el parámetro "mensaje01"
                        entonces el mensaje tiene algo (ya sea que indique que si se eliminó o que no se eliminó. 
                        Pero si no se ha dado clic al botón ELIMINAR, el Servet no ha sido ejecutado, por tanto el PARÁMETRO
                        "mensaje01" NO existe porque no se ha creado. Entonces será NULO y este if NO se ejecuta. */

                    out.print("<p class='text-danger'>" + new String(request.getParameter("mensajeServletEliminarCliente").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                    // la variable out también es un objeto INTRÍNSECO, y permite escribir DIRECTAMENTE EN EL HTML
                    // en este caso imprimir en un TAG de párrafo
                    // requiere ese formateo porque si tiene caracteres especiales no se imprime en el código HTML
                }
            %>
            <a href="Frm_Clientes.jsp?idCrearModificar=-1">Agregar Nuevo Cliente</a> |
            <a href="FrmListarClientes.jsp">Actualizar</a>
            <br><br>
            <a href="index.html">Regresar al Index</a>
        </div>

        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <!-- "min" minificado es que esta en una sola linea. 
        El .map igual esta minificado, pero ademas está en la capacidad de decir 
        (en caso de haber un error) en cuál línea está el error. -->

    </body>
</html>
