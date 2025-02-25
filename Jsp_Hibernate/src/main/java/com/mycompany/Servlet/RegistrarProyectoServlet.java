package com.mycompany.Servlet;

import com.mycompany.DAO.ProyectoDAO;
import com.mycompany.model.Proyecto;
import java.io.IOException;
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
@WebServlet("/registrarProyecto")
public class RegistrarProyectoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");

            // Parseo de las fechas con manejo de excepciones
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = null;
            Date fechaFin = null;

            // Verificar que las fechas no estén vacías y hacer el parseo
            if (request.getParameter("fechaInicio") != null && !request.getParameter("fechaInicio").isEmpty()) {
                fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            }
            if (request.getParameter("fechaFin") != null && !request.getParameter("fechaFin").isEmpty()) {
                fechaFin = sdf.parse(request.getParameter("fechaFin"));
            }

            String estado = request.getParameter("estado");

            // Crear un objeto de tipo Proyecto y asignar los valores
            Proyecto proyecto = new Proyecto();
            proyecto.setNombreProyecto(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.setFechaInicio(fechaInicio);
            proyecto.setFechaFin(fechaFin);
            proyecto.setEstado(estado);

            // Guardar el proyecto en la base de datos
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            proyectoDAO.saveProyecto(proyecto);

            // Redirigir al usuario a la lista de proyectos
            response.sendRedirect("listarProyectos");
        } catch (Exception e) {
            // Manejar cualquier excepción (incluido ParseException)
            e.printStackTrace();
            response.getWriter().write("Error al registrar el proyecto. Verifica los campos de fecha.");
        }
    }
}
