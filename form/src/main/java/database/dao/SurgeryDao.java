package database.dao;

import database.DatabaseConnection;
import database.model.SurgeryEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class SurgeryDao implements DaoI<SurgeryEntity> {
    DatabaseConnection connection = new DatabaseConnection();

    @Override
    public SurgeryEntity get(Long id) {
        return connection.getEntityManager().find(SurgeryEntity.class, id);
    }

    @Override
    public List<SurgeryEntity> getAll() {
        TypedQuery<SurgeryEntity> query = connection.getEntityManager().createQuery("SELECT s FROM SurgeryEntity s", SurgeryEntity.class);
        return query.getResultList();
    }

    @Override
    public void create(SurgeryEntity surgeryEntity) {
        /* EMPTY */
    }
}
