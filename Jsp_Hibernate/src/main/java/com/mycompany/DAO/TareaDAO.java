package com.mycompany.DAO;

import java.util.List;
import com.mycompany.model.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.util.HibernateUtil;

public class TareaDAO {

   // Método para obtener tareas por proyecto
   public List<Tarea> getTareasPorProyecto(int idProyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tarea> tareas = null;
        try {
            tareas = session.createQuery("FROM Tarea WHERE proyecto.id = :idProyecto", Tarea.class)
                            .setParameter("idProyecto", idProyecto)
                            .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Asegurarse de cerrar la sesión incluso en caso de error
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

    // Método para eliminar tarea por ID
    public boolean eliminarTarea(int tareaId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Tarea tarea = session.get(Tarea.class, tareaId);  // Obtiene la tarea por ID
            if (tarea != null) {
                session.delete(tarea);  // Elimina la tarea de la base de datos
                tx.commit();  // Confirma la transacción
                return true;
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();  // Revierte si hay un error
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión de Hibernate
        }
        return false;
    }
}
