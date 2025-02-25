package com.mycompany.DAO;

import java.util.List;
import com.mycompany.model.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.util.HibernateUtil;


public class ProyectoDAO {

    public Proyecto getProyectoById(int id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Proyecto proyecto = session.get(Proyecto.class, id);
    session.close();
    return proyecto;
}

    public List<Proyecto> getProyectosPorEstado(String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Proyecto> proyectos = session.createQuery("FROM Proyecto WHERE estado = :estado", Proyecto.class)
                                           .setParameter("estado", estado)
                                           .list();
        session.close();
        return proyectos;
    }

    public void saveProyecto(Proyecto proyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(proyecto);
        transaction.commit();
        session.close();
    }

    public void deleteProyecto(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Proyecto proyecto = session.get(Proyecto.class, id);
        if (proyecto != null) {
            session.delete(proyecto);
        }
        transaction.commit();
        session.close();
    }
}
