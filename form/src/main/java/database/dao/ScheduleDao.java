package database.dao;

import database.DatabaseConnection;
import database.model.ScheduleEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class ScheduleDao implements DaoI<ScheduleEntity> {
    DatabaseConnection connection = new DatabaseConnection();

    @Override
    public ScheduleEntity get(Long id) {
        /*EMPTY*/
        return null;
    }

    @Override
    public List<ScheduleEntity> getAll() {
        return null;
    }

    public List<ScheduleEntity> getMedicSchedules(Long id) {
        /* positional parameter */
        TypedQuery<ScheduleEntity> query = connection.getEntityManager().createQuery("SELECT s FROM ScheduleEntity AS s WHERE s.idMedic= ?1", ScheduleEntity.class)
                .setParameter(1, id);
        return query.getResultList();
    }

    public void create(ScheduleEntity scheduleEntity) {
        connection.executeTransaction(entityManager -> entityManager.persist(scheduleEntity));
    }
}
