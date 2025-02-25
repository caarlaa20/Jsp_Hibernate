package com.mycompany.Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.mycompany.DAO.TareaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet("/EliminarTarea")
public class EliminarTareaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Método para eliminar tarea
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tareaId = Integer.parseInt(request.getParameter("tareaId"));
        
        // Llamada al DAO para eliminar la tarea
        TareaDAO tareaDAO = new TareaDAO();
        boolean tareaEliminada = tareaDAO.eliminarTarea(tareaId);
        
        if (tareaEliminada) {
            response.sendRedirect("listarTareas.jsp");  // Redirige a la lista de tareas si fue eliminada
        } else {
            request.setAttribute("error", "No se pudo eliminar la tarea.");
            request.getRequestDispatcher("error.jsp").forward(request, response);  // Redirige a una página de error
        }
    }
}