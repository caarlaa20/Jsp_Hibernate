package com.mycompany.DAO;

import java.util.List;
import com.mycompany.model.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.query.Query;

public class TareaDAO {

  public List<Tarea> getTareasPorProyecto(int idProyecto) {
    List<Tarea> tareas = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        tareas = session.createQuery("FROM Tarea WHERE proyecto.id = :idProyecto", Tarea.class)
                        .setParameter("idProyecto", idProyecto)
                        .list();
        System.out.println("Consulta ejecutada, tareas obtenidas: " + tareas);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tareas;
}

  public List<Tarea> obtenerTodasLasTareas() {
    List<Tarea> tareas = new ArrayList<>();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        session.beginTransaction();
        Query query = session.createQuery("FROM Tarea");
        tareas = query.list(); // Aquí obtienes todas las tareas
        session.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }
    return tareas;
}



    // Método para guardar una tarea
    public void saveTarea(Tarea tarea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tarea);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // En caso de error, hacemos rollback
            }
            e.printStackTrace();
        } finally {
            session.close();  // Asegurarse de cerrar la sesión
        }
    }

    public boolean eliminarTarea(int idTarea) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tarea tarea = session.get(Tarea.class, idTarea);
            
            if (tarea != null) {
                session.delete(tarea);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
