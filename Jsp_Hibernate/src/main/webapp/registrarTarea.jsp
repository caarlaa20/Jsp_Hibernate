<%-- 
    Document   : registrarTarea
    Created on : 21 feb 2025, 19:47:31
    Author     : alumno
--%>

<%@ page import="com.mycompany.model.Proyecto" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Tarea</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Registrar Nueva Tarea</h1>
        <form method="post" action="registrarTarea">
            <label for="descripcion">Descripción de la Tarea:</label>
            <input type="text" name="descripcion" id="descripcion" required>

            <label for="responsable">Responsable:</label>
            <input type="text" name="responsable" id="responsable" required>

            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" name="fechaInicio" id="fechaInicio" required>

            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" name="fechaFin" id="fechaFin" required>

            <label for="estado">Estado:</label>
            <select name="estado" id="estado" required>
                <option value="pendiente">Pendiente</option>
                <option value="en progreso">En progreso</option>
                <option value="completada">Completada</option>
            </select>

            <label for="idProyecto">Proyecto:</label>
            <input type="number" name="idProyecto" id="idProyecto" required>

            <button type="submit" class="button">Registrar Tarea</button>
        </form>
    </div>
</body>
</html>
