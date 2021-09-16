import database.dao.AppointmentDao;
import database.dao.ClientDao;
import database.model.AppointmentEntity;
import database.model.ClientEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        /* CRUD db examples */
        ClientDao clientDao = new ClientDao();
        AppointmentDao appointmentDao = new AppointmentDao();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName("Mock");
        clientEntity.setLastName("Mock");
        clientEntity.setTin("mock");
        clientEntity.setEmail("mock@mock.com");
        clientEntity.setPhoneNumber("+40133333333333");

        clientDao.create(clientEntity);

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDate(LocalDate.now());
        appointmentEntity.setHour(LocalTime.of(2,0));
        appointmentEntity.setNotes("Mock");
        appointmentEntity.setIdAnimal(1);
        appointmentEntity.setIdClient(clientEntity.getId());
        appointmentEntity.setIdMedic(1);
        appointmentEntity.setIdSurgery(1);

        appointmentDao.create(appointmentEntity);
    }
}
