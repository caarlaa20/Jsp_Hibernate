package com.mycompany.DAO;

import com.mycompany.model.Proyecto;
import com.mycompany.model.Tarea;
import com.mycompany.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ProyectoDAO {

       public boolean eliminarProyecto(int idProyecto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Proyecto proyecto = session.get(Proyecto.class, idProyecto);
            
            if (proyecto != null) {
                session.delete(proyecto);
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

    
     public Proyecto getProyectoById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        Proyecto proyecto = null;

        try {
            transaction = session.beginTransaction();
            proyecto = session.get(Proyecto.class, id); // Recuperar el proyecto por ID
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return proyecto;
    }

    // Guardar un proyecto
    public void saveProyecto(Proyecto proyecto) {
        // Obtener la sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Guardar el proyecto
            session.saveOrUpdate(proyecto);

            // Commit de la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }

    public List<Proyecto> listarProyectos() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    Query<Proyecto> query = session.createQuery("FROM Proyecto", Proyecto.class);
    List<Proyecto> proyectos = query.getResultList();

    session.getTransaction().commit();
    session.close();

    // Si la lista es null, devolvemos una lista vacía
    if (proyectos == null) {
        proyectos = new ArrayList<>();
    }

    return proyectos;
}

    // Obtener proyectos por estado
    public List<Proyecto> getProyectosPorEstado(String estado) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Proyecto> proyectos = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Crear una consulta HQL para obtener proyectos por estado
            String hql = "FROM Proyecto p WHERE p.estado = :estado";
            Query<Proyecto> query = session.createQuery(hql, Proyecto.class);
            query.setParameter("estado", estado);

            // Obtener los proyectos
            proyectos = query.list();

            // Commit de la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }

        return proyectos;
    }
}
