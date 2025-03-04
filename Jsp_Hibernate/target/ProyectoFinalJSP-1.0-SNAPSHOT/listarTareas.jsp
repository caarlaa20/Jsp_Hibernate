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
                    <th>Acciones</th> <!-- Nueva columna para eliminar -->
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas");
                    if (tareas != null && !tareas.isEmpty()) { 
                        for (Tarea tarea : tareas) { 
                %>
                    <tr>
                        <td><%= tarea.getDescripcion() %></td>
                        <td><%= tarea.getResponsable() %></td>
                        <td><%= tarea.getFechaInicio() %></td>
                        <td><%= tarea.getFechaFin() %></td>
                        <td><%= tarea.getEstado() %></td>
                        <td>
                            <!-- Formulario para eliminar tarea -->
                            <form action="eliminarTarea" method="post" onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta tarea?');">
                                <input type="hidden" name="idTarea" value="<%= tarea.getId() %>">
                                <button type="submit" class="btn-eliminar">X</button>
                            </form>
                        </td>
                    </tr>
                <% 
                        } 
                    } else { 
                %>
                    <tr>
                        <td colspan="6" style="text-align: center; color: red;">
                            No hay tareas registradas.
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
