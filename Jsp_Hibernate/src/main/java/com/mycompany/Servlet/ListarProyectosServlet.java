package com.mycompany.Servlet;

import com.mycompany.DAO.ProyectoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.model.Proyecto;
import java.util.ArrayList;
import java.util.logging.Logger;

@WebServlet("/listarProyectos")
public class ListarProyectosServlet extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        List<Proyecto> proyectos = proyectoDAO.listarProyectos();

        // Asegúrate de que proyectos nunca sea null
        if (proyectos == null) {
            proyectos = new ArrayList<>();
        }

        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("/listarProyectos.jsp").forward(request, response);
    }
}