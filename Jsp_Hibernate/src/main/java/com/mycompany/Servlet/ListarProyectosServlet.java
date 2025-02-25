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

@WebServlet("/listarProyectos")
public class ListarProyectosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = request.getParameter("estado");
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        List<Proyecto> proyectos = proyectoDAO.getProyectosPorEstado(estado);

        request.setAttribute("proyectos", proyectos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarProyectos.jsp");
        dispatcher.forward(request, response);
    }
}
