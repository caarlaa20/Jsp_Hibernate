/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import java.util.List;
import com.mycompany.model.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.util.HibernateUtil;

/**
 *
 * @author alumno
 */




public class TareaDAO {
   public List<Tarea> getTareasPorProyecto(int idProyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tarea> tareas = session.createQuery("FROM Tarea WHERE proyecto.id = :idProyecto", Tarea.class)
                                    .setParameter("idProyecto", idProyecto)
                                    .list();
        session.close();
        return tareas;
    }

    public void saveTarea(Tarea tarea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(tarea);
        transaction.commit();
        session.close();
    }

    public void deleteTarea(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Tarea tarea = session.get(Tarea.class, id);
        if (tarea != null) {
            session.delete(tarea);
        }
        transaction.commit();
        session.close();
    }
}