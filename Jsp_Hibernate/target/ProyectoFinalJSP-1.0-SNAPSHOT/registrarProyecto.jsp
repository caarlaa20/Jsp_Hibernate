<%-- 
    Document   : registrarProyecto
    Created on : 21 feb 2025, 19:45:24
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Proyecto</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Registrar Nuevo Proyecto</h1>
        <form method="post" action="registrarProyecto">
            <label for="nombre">Nombre del Proyecto:</label>
            <input type="text" name="nombre" id="nombre" required>

            <label for="descripcion">Descripción:</label>
            <textarea name="descripcion" id="descripcion" required></textarea>

            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" name="fechaInicio" id="fechaInicio" required>

            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" name="fechaFin" id="fechaFin" required>

            <label for="estado">Estado:</label>
            <select name="estado" id="estado" required>
                <option value="en curso">En curso</option>
                <option value="completado">Completado</option>
            </select>

            <button type="submit" class="button">Registrar Proyecto</button>
        </form>
    </div>
</body>
</html>
