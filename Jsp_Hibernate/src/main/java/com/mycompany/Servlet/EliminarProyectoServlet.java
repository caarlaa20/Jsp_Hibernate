package com.mycompany.Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.mycompany.DAO.ProyectoDAO;
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
@WebServlet("/EliminarProyecto")
public class EliminarProyectoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Método para eliminar proyecto
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int proyectoId = Integer.parseInt(request.getParameter("proyectoId"));
        
        // Llamada al DAO para eliminar el proyecto
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        boolean proyectoEliminado = proyectoDAO.eliminarProyecto(proyectoId);
        
        if (proyectoEliminado) {
            response.sendRedirect("listarProyectos.jsp");  // Redirige a la lista de proyectos si fue eliminada
        } else {
            request.setAttribute("error", "No se pudo eliminar el proyecto.");
            request.getRequestDispatcher("error.jsp").forward(request, response);  // Redirige a una página de error
        }
    }
}