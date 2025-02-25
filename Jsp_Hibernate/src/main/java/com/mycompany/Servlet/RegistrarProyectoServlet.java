package com.mycompany.Servlet;

import com.mycompany.DAO.ProyectoDAO;
import com.mycompany.model.Proyecto;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrarProyecto")
public class RegistrarProyectoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");
            String estado = request.getParameter("estado");

            // Verificar que los campos no sean nulos ni vacíos
            if (nombre == null || nombre.isEmpty() || descripcion == null || descripcion.isEmpty() || 
                fechaInicioStr == null || fechaFinStr == null || estado == null || estado.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben ser llenados.");
            }

            // Imprimir fechas recibidas para depuración
            System.out.println("Fecha de inicio recibida: " + fechaInicioStr);
            System.out.println("Fecha de fin recibida: " + fechaFinStr);

            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr);
            

            // Imprimir fechas convertidas para verificar
            System.out.println("Fecha de inicio convertida (LocalDate): " + fechaInicio);
            System.out.println("Fecha de fin convertida (LocalDate): " + fechaFin);

            // Crear el proyecto
            Proyecto proyecto = new Proyecto();
            proyecto.setNombreProyecto(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.setFechaInicio(fechaInicio); // Usar LocalDate directamente
            proyecto.setFechaFin(fechaFin);       // Usar LocalDate directamente
            proyecto.setEstado(estado);

            // Guardar el proyecto
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            proyectoDAO.saveProyecto(proyecto);

            // Redirigir a la lista de proyectos
            response.sendRedirect("listarProyectos");

        } catch (IllegalArgumentException e) {
            // Capturar el error si los campos son incorrectos
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        } catch (Exception e) {
            // Capturar cualquier otro tipo de error
            e.printStackTrace();
            response.getWriter().write("Error al registrar el proyecto. Verifica los campos de fecha.");
        }
    }

    // Método auxiliar para parsear la fecha de String a LocalDate
    private LocalDate parseFecha(String fechaStr, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("El formato de fecha no es válido. Usa el formato yyyy-MM-dd.");
        }
    }
}
