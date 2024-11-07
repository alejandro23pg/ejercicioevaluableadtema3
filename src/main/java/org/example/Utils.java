package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private SessionFactory sessionFactory;

    public Utils(SessionFactory sessionFactory) {
        this .sessionFactory = sessionFactory;
    }

    // Método para guardar una nueva película (Historia 1)
    public void save(Pelicula pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(pelicula);
            session.getTransaction().commit();
        }
    }

    // Método para obtener todas las opiniones de un usuario (Historia 2)
    public java.util.List<Opinion> getOpinionesUsuario(String usuario){
        List<Opinion> listaOpiniones;
        try (Session session = sessionFactory.openSession()) {
            Query<Opinion> q = session.createQuery("from Opinion o where o.usuario =: usuario",Opinion.class);
            q.setParameter("usuario", usuario);
            listaOpiniones = q.list();
        }catch (Exception e){
            listaOpiniones = new ArrayList<>(0);
        }
        return listaOpiniones;
    };

    // Método para obtener todas las películas con baja puntuación (Historia 4)
    public java.util.List<Pelicula> getPeliculasBajaPuntuacion(Integer puntuacion) {
        List<Pelicula> listaPeliculas;
        try (Session session = sessionFactory.openSession()) {
            String hql = "select p from Pelicula p join p.opiniones o where o.puntuacion <= :puntuacion";
            Query<Pelicula> q = session.createQuery(hql, Pelicula.class);
            q.setParameter("puntuacion", puntuacion);
            listaPeliculas = q.list();
        } catch (Exception e) {
            listaPeliculas = new ArrayList<>(0);
        }
        return listaPeliculas;
    }
}
