package database.dao;

import database.DatabaseConnection;
import database.model.MedicEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class MedicDao implements DaoI<MedicEntity> {
    DatabaseConnection connection = new DatabaseConnection();

    @Override
    public MedicEntity get(Long id) {
        /* EMPTY */
        return null;
    }

    @Override
    public List<MedicEntity> getAll() {
        TypedQuery<MedicEntity> query = connection.getEntityManager().createQuery("SELECT m FROM MedicEntity m", MedicEntity.class);
        return query.getResultList();
    }

    @Override
    public void create(MedicEntity medicEntity) {
        /* EMPTY */
    }
}
