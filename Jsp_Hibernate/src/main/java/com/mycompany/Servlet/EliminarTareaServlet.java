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
@WebServlet("/eliminarTarea")
public class EliminarTareaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTarea = Integer.parseInt(request.getParameter("idTarea"));

        TareaDAO tareaDAO = new TareaDAO();
        tareaDAO.deleteTarea(idTarea);

        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
        response.sendRedirect("listarTareas?idProyecto=" + idProyecto);
    }
}
