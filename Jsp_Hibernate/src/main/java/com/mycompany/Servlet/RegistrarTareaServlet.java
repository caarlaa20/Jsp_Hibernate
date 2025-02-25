package com.mycompany.Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.mycompany.DAO.ProyectoDAO;
import com.mycompany.DAO.TareaDAO;
import com.mycompany.model.Proyecto;
import com.mycompany.model.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet("/registrarTarea")
public class RegistrarTareaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            String responsable = request.getParameter("responsable");

            // Conversión de las fechas
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
            String estado = request.getParameter("estado");

            // Verificar que los valores no sean nulos
            if (descripcion == null || responsable == null || fechaInicio == null || fechaFin == null || estado == null) {
                throw new IllegalArgumentException("Todos los campos deben ser llenados");
            }

            // Obtener el proyecto relacionado
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            Proyecto proyecto = proyectoDAO.getProyectoById(idProyecto);

            // Crear una nueva tarea
            Tarea tarea = new Tarea();
            tarea.setDescripcionTarea(descripcion);
            tarea.setResponsable(responsable);
            tarea.setFechaInicio(fechaInicio);
            tarea.setFechaFin(fechaFin);
            tarea.setEstado(estado);
            tarea.setProyecto(proyecto);

            // Guardar la tarea
            TareaDAO tareaDAO = new TareaDAO();
            tareaDAO.saveTarea(tarea);

            // Redirigir al usuario para ver las tareas del proyecto
            response.sendRedirect("listarTareas?idProyecto=" + idProyecto);
        } catch (ParseException e) {
            e.printStackTrace();
            response.getWriter().write("Error en el formato de las fechas. Por favor, usa el formato yyyy-MM-dd.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Ha ocurrido un error inesperado.");
        }
    }
}

