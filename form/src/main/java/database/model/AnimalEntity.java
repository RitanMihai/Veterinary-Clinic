package database.model;

import javax.persistence.*;

@Entity
@Table(name = "animal", schema = "public", catalog = "veterinary_clinic")
public class AnimalEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "breed", nullable = false, length = -1)
    private String breed;
    @Basic
    @Column(name = "species", nullable = false, length = -1)
    private String species;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return breed;
    }
}
