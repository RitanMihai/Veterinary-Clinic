package database.dao;

import database.DatabaseConnection;
import database.model.AnimalEntity;
import javax.persistence.TypedQuery;
import java.util.List;

public class AnimalDao implements DaoI<AnimalEntity> {
    DatabaseConnection connection = new DatabaseConnection();

    public AnimalEntity get(Long id) {
        return connection.getEntityManager().find(AnimalEntity.class, Long.valueOf(id));
    }

    @Override
    public List<AnimalEntity> getAll() {
        TypedQuery<AnimalEntity> query = connection.getEntityManager().createQuery("SELECT a FROM AnimalEntity a", AnimalEntity.class);
        return query.getResultList();
    }

    public List<AnimalEntity> getAllBySpecies(String species) {
        TypedQuery<AnimalEntity> query = connection
                .getEntityManager()
                .createQuery("SELECT a FROM AnimalEntity a WHERE a.species= :species", AnimalEntity.class)
                .setParameter("species", species);
        return query.getResultList();
    }

    public List<String> getAllSpecies() {
        TypedQuery<String> query = connection.getEntityManager().createQuery("SELECT a.species FROM AnimalEntity a GROUP BY a.species", String.class);
        return query.getResultList();
    }

    public void create(AnimalEntity animal) {
        connection.executeTransaction(entityManager -> entityManager.persist(animal));
    }
}
