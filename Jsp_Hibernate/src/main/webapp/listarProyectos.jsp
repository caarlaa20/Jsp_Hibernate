<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.model.Proyecto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Proyectos</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Lista de Proyectos</h1>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Estado</th>
                    <th>Acciones</th> <!-- Nueva columna para eliminar -->
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Proyecto> proyectos = (List<Proyecto>) request.getAttribute("proyectos");
                    if (proyectos != null && !proyectos.isEmpty()) {
                        for (Proyecto proyecto : proyectos) {
                %>
                <tr>
                    <td><%= proyecto.getId() %></td>
                    <td><%= proyecto.getNombreProyecto() %></td>
                    <td><%= proyecto.getEstado() %></td>
                    <td>
                        <!-- Formulario para eliminar proyecto -->
                        <form action="eliminarProyecto" method="post" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este proyecto?');">
                            <input type="hidden" name="idProyecto" value="<%= proyecto.getId() %>">
                            <button type="submit" class="btn-eliminar">X</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" style="text-align: center; color: red;">
                        No hay proyectos disponibles.
                    </td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
