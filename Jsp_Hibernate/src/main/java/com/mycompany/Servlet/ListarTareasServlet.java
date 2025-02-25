package com.mycompany.Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.mycompany.DAO.TareaDAO;
import com.mycompany.model.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet("/listarTareas")
public class ListarTareasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProyectoParam = request.getParameter("idProyecto");

        // Verificar si el parámetro es nulo o vacío
        if (idProyectoParam == null || idProyectoParam.isEmpty()) {
            request.setAttribute("error", "ID de proyecto no válido.");
            request.getRequestDispatcher("listarTareas.jsp").forward(request, response);
            return;
        }

        try {
            int idProyecto = Integer.parseInt(idProyectoParam);
            TareaDAO tareaDAO = new TareaDAO();
            List<Tarea> tareas = tareaDAO.getTareasPorProyecto(idProyecto);

            request.setAttribute("tareas", tareas);
            request.setAttribute("idProyecto", idProyecto);
            request.getRequestDispatcher("listarTareas.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Formato de ID inválido.");
            request.getRequestDispatcher("listarTareas.jsp").forward(request, response);
        }
    }
}
