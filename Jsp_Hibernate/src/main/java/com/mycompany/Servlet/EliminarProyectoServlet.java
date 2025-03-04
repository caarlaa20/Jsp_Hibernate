package com.mycompany.Servlet;

import com.mycompany.DAO.ProyectoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/eliminarProyecto")
public class EliminarProyectoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProyectoParam = request.getParameter("idProyecto");

        if (idProyectoParam != null && !idProyectoParam.isEmpty()) {
            try {
                int idProyecto = Integer.parseInt(idProyectoParam);
                ProyectoDAO proyectoDAO = new ProyectoDAO();
                boolean eliminado = proyectoDAO.eliminarProyecto(idProyecto);

                if (eliminado) {
                    request.setAttribute("mensaje", "Proyecto eliminado correctamente.");
                } else {
                    request.setAttribute("error", "No se pudo eliminar el proyecto.");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de proyecto inválido.");
            }
        } else {
            request.setAttribute("error", "ID de proyecto no proporcionado.");
        }

        response.sendRedirect("listarProyectos");
    }
}
