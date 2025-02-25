<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar Tarea</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Eliminar Tarea</h1>
        <form action="EliminarTarea" method="post" class="formulario">
            <label for="tareaId">ID de la tarea a eliminar:</label>
            <input type="text" id="tareaId" name="tareaId" required class="input-field"/>
            <input type="submit" value="Eliminar" class="submit-btn"/>
        </form>
    </div>
</body>
</html>
