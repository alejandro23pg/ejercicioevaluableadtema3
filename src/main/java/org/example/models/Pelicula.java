package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Opinion> opiniones = new ArrayList<>(0);

    public Pelicula(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Pelicula() {

    }

    // Método para añadir una opinión a una película ya existente (Historia 3)
    public void addOpinion(Opinion opinion) {
        opinion.setPelicula(this);
        opiniones.add(opinion);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", opiniones=" + opiniones +
                '}';
    }
}
