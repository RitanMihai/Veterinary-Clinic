package database.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment", schema = "public", catalog = "veterinary_clinic")
public class AppointmentEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Basic
    @Column(name = "hour", nullable = false)
    private LocalTime hour;
    @Basic
    @Column(name = "notes", nullable = false, length = -1)
    private String notes;
    @Basic
    @Column(name = "id_surgery", nullable = false)
    private long idSurgery;
    @Basic
    @Column(name = "id_medic", nullable = false)
    private long idMedic;
    @Basic
    @Column(name = "id_client", nullable = false)
    private long idClient;
    @Basic
    @Column(name = "id_animal", nullable = false)
    private long idAnimal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getIdSurgery() {
        return idSurgery;
    }

    public void setIdSurgery(long idSurgery) {
        this.idSurgery = idSurgery;
    }

    public long getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(long idMedic) {
        this.idMedic = idMedic;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "date=" + date +
                ", hour=" + hour +
                ", notes='" + notes + '\'' +
                ", idSurgery=" + idSurgery +
                ", idMedic=" + idMedic +
                ", idClient=" + idClient +
                ", idAnimal=" + idAnimal +
                '}';
    }
}
