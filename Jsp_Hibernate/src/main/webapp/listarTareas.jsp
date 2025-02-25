<%-- 
    Document   : listarTareas
    Created on : 21 feb 2025, 19:45:38
    Author     : alumno
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.model.Tarea" %>
<%@ page import="com.mycompany.model.Proyecto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Tareas</title>
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
                <!-- Aquí deberías iterar sobre las tareas del proyecto -->
                <tr>
                    <td>Tarea A</td>
                    <td>Juan Pérez</td>
                    <td>2025-01-01</td>
                    <td>2025-03-31</td>
                    <td>En progreso</td>
                </tr>
                <tr>
                    <td>Tarea B</td>
                    <td>Ana García</td>
                    <td>2025-02-01</td>
                    <td>2025-06-30</td>
                    <td>Pendiente</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
