package database.dao;

import database.DatabaseConnection;
import database.model.AppointmentEntity;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class AppointmentDao implements DaoI<AppointmentEntity> {
    DatabaseConnection connection = new DatabaseConnection();

    @Override
    public AppointmentEntity get(Long id) {
        return null;
    }

    @Override
    public List<AppointmentEntity> getAll() {
        return null;
    }

    public List<AppointmentEntity> getByDate(LocalDate date) {
        TypedQuery<AppointmentEntity> query =  connection
                .getEntityManager()
                .createQuery("SELECT a FROM AppointmentEntity AS a WHERE a.date= ?1 ORDER BY a.hour ASC", AppointmentEntity.class)
                .setParameter(1, date);

        return query.getResultList();
    }

    public void create(AppointmentEntity appointmentEntity) {
        connection.executeTransaction(entityManager -> entityManager.persist(appointmentEntity));
    }
}
