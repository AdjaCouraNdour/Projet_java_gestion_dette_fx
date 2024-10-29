package com.ism.data.entities;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="detail")
// @ToString(exclude = "dette")

public class Details extends AbstractEntity implements Identifiable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double qteDette;

    @ManyToOne
    @JoinColumn(name = "dette_id", referencedColumnName = "id")
    private Dette dette;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id + // Ajoute l'ID depuis AbstractEntity
                ", qteDette=" + qteDette +
                ", detteId=" + (dette != null ? dette.getId() : "null") + // Affiche l'ID de la dette si elle n'est pas null
                ", article=" + (article != null ? article.getLibelle() : "null") + // Affiche l'ID de l'article si elle n'est pas null
                '}';
    }
}
