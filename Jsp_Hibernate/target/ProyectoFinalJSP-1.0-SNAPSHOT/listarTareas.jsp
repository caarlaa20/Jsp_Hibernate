<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.model.Tarea" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Tareas</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Lista de Tareas</h1>

        <table class="table">
            <thead>
                <tr>
                    <th>Descripción</th>
                    <th>Responsable</th>
                    <th>Fecha de Inicio</th>
                    <th>Fecha de Fin</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Obtener la lista de tareas desde el request
                    List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas");
                    if (tareas != null && !tareas.isEmpty()) { 
                        // Si hay tareas, las iteramos
                        for (Tarea tarea : tareas) { 
                %>
                    <tr>
                        <td><%= tarea.getDescripcion() %></td>
                        <td><%= tarea.getResponsable() %></td>
                        <td><%= tarea.getFechaInicio() %></td>
                        <td><%= tarea.getFechaFin() %></td>
                        <td><%= tarea.getEstado() %></td>
                    </tr>
                <% 
                        } 
                    } else { 
                %>
                    <!-- Si no hay tareas, mostramos un mensaje -->
                    <tr>
                        <td colspan="5" style="text-align: center; color: red;">
                            No hay tareas registradas.
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
