package com.mycompany.Servlet;

import com.mycompany.DAO.ProyectoDAO;
import com.mycompany.DAO.TareaDAO;
import com.mycompany.model.Proyecto;
import com.mycompany.model.Tarea;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrarTarea")
public class RegistrarTareaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recuperar los parámetros del formulario
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            String responsable = request.getParameter("responsable");

            // Verificar que los parámetros no sean nulos o vacíos
            if (descripcion == null || descripcion.trim().isEmpty() ||
                responsable == null || responsable.trim().isEmpty() ||
                request.getParameter("fechaInicio") == null || request.getParameter("fechaFin") == null ||
                request.getParameter("estado") == null) {
                throw new IllegalArgumentException("Todos los campos deben ser llenados correctamente.");
            }

            // Conversión de las fechas
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");
            LocalDate fechaInicio = parseFecha(fechaInicioStr);
            LocalDate fechaFin = parseFecha(fechaFinStr);
            String estado = request.getParameter("estado");

            // Convertir LocalDate a Date
            Date fechaInicioDate = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fechaFinDate = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Obtener el proyecto relacionado
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            Proyecto proyecto = proyectoDAO.getProyectoById(idProyecto);

            // Verificar que el proyecto exista
            if (proyecto == null) {
                throw new IllegalArgumentException("El proyecto con el ID proporcionado no existe.");
            }

            // Crear una nueva tarea
            Tarea tarea = new Tarea();
            tarea.setDescripcion(descripcion);
            tarea.setResponsable(responsable);
            tarea.setFechaInicio(fechaInicioDate);
            tarea.setFechaFin(fechaFinDate);
            tarea.setEstado(estado);
            tarea.setProyecto(proyecto);

            // Guardar la tarea
            TareaDAO tareaDAO = new TareaDAO();
            tareaDAO.saveTarea(tarea);

            // Redirigir al usuario a la lista de tareas del proyecto
            response.sendRedirect("listarTareas?idProyecto=" + idProyecto);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Ha ocurrido un error inesperado. Detalles: " + e.getMessage());
        }
    }

    // Método auxiliar para parsear la fecha de String a LocalDate
    private LocalDate parseFecha(String fechaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("El formato de fecha no es válido. Usa el formato yyyy-MM-dd.");
        }
    }
}
