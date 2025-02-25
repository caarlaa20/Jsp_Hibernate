<%-- 
    Document   : listarProyectos
    Created on : 21 feb 2025, 19:45:10
    Author     : alumno
--%>

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
        <form method="get" action="listarProyectos">
            <label for="estado">Filtrar por estado:</label>
            <select name="estado" id="estado">
                <option value="en curso">En curso</option>
                <option value="completado">Completado</option>
            </select>
            <button type="submit" class="button">Filtrar</button>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Fecha de Inicio</th>
                    <th>Fecha de Fin</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí deberías iterar sobre los proyectos en tu backend -->
                <tr>
                    <td>Proyecto A</td>
                    <td>Descripción del proyecto A</td>
                    <td>2025-01-01</td>
                    <td>2025-12-31</td>
                    <td>En curso</td>
                </tr>
                <tr>
                    <td>Proyecto B</td>
                    <td>Descripción del proyecto B</td>
                    <td>2025-02-01</td>
                    <td>2025-06-30</td>
                    <td>Completado</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
