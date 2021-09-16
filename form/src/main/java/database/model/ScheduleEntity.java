package database.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "schedule", schema = "public", catalog = "veterinary_clinic")
public class ScheduleEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "start_hour", nullable = false)
    private LocalTime startHour;
    @Basic
    @Column(name = "end_hour", nullable = false)
    private LocalTime endHour;
    @Basic
    @Column(name = "id_medic", nullable = false)
    private long idMedic;
    @Basic
    @Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public long getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(long idMedic) {
        this.idMedic = idMedic;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", idMedic=" + idMedic +
                ", day=" + day +
                '}';
    }
}
