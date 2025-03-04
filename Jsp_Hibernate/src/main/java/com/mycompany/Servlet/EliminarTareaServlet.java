package com.mycompany.Servlet;

import com.mycompany.DAO.TareaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/eliminarTarea")
public class EliminarTareaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTareaParam = request.getParameter("idTarea");

        if (idTareaParam != null && !idTareaParam.isEmpty()) {
            try {
                int idTarea = Integer.parseInt(idTareaParam);
                TareaDAO tareaDAO = new TareaDAO();
                boolean eliminado = tareaDAO.eliminarTarea(idTarea);

                if (eliminado) {
                    request.setAttribute("mensaje", "Tarea eliminada correctamente.");
                } else {
                    request.setAttribute("error", "No se pudo eliminar la tarea.");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de tarea inválido.");
            }
        } else {
            request.setAttribute("error", "ID de tarea no proporcionado.");
        }

        response.sendRedirect("listarTareas");
    }
}
