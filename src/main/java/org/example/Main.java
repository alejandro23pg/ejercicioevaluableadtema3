package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Utils utils = new Utils(sessionFactory);

        // Registro de nueva película
        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo("Nueva_Película");

        // Añadir opiniones a una película:
        Opinion opinion1 = new Opinion();
        opinion1.setDescripcion("Descripcion");
        opinion1.setUsuario("Usuario1");
        opinion1.setPuntuacion(7);
        nuevaPelicula.addOpinion(opinion1);

        utils.save(nuevaPelicula);

        System.out.println("Opiniones de una usuario específico:");
        utils.getOpinionesUsuario("user1@example.com").forEach(System.out::println);

        System.out.println("Películas con baja puntuación:");
        utils.getPeliculasBajaPuntuacion(3).forEach(System.out::println);

    }
}