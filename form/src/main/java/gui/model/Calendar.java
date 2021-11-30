package gui.model;

import database.dao.AppointmentDao;
import database.dao.ScheduleDao;
import database.dao.SurgeryDao;
import database.model.AppointmentEntity;
import database.model.MedicEntity;
import database.model.ScheduleEntity;
import database.model.SurgeryEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calendar {
    private ScheduleDao scheduleDao;
    private AppointmentDao appointmentDao;
    private SurgeryDao surgeryDao;

    private MedicEntity medic = new MedicEntity();
    private List<ScheduleEntity> medicSchedules = new ArrayList<>();
    private SurgeryEntity surgery;
    private Long surgeryTime;

    public Calendar() {
        scheduleDao = new ScheduleDao();
        appointmentDao = new AppointmentDao();
        surgeryDao = new SurgeryDao();
    }

    public Calendar(MedicEntity medic, SurgeryEntity surgery) {
        /* Call default constructor */
        this();
        this.setMedic(medic);
        this.setSurgery(surgery);
    }

    public void setMedic(MedicEntity medic) {
        this.medic = medic;
        setMedicSchedules();
    }

    public void setSurgery(SurgeryEntity surgery) {
        this.surgery = surgery;
        this.surgeryTime = surgery.getTime().getLong(ChronoField.HOUR_OF_DAY);
    }

    private void setMedicSchedules() {
        medicSchedules = scheduleDao.getMedicSchedules(medic.getId());
    }

    public boolean isDayAvailable(LocalDate item) {
        /* Must select a surgery time */
        if (surgeryTime == null)
            return false;

        List<ScheduleEntity> medicSchedulesCurrentDay = getSchedulesByDay(item);

        if (medicSchedulesCurrentDay.isEmpty())
            return false;

        /* Subtract appointments from schedule  */
        subtractAppointmentsFromSchedules(medicSchedulesCurrentDay, item);

        /* Check if surgery time fits */
        for (ScheduleEntity medicSchedule : medicSchedulesCurrentDay) {
            LocalTime minimumHour = medicSchedule.getEndHour().minusHours(surgeryTime);
            if (medicSchedule.getStartHour().equals(minimumHour) || medicSchedule.getStartHour().isBefore(minimumHour))
                return true;
        }
        return false;
    }

    public List<LocalTime> getFreeHours(LocalDate item) {
        List<ScheduleEntity> medicSchedulesCurrentDay = getSchedulesByDay(item);

        /* Subtract appointments from schedule  */
        subtractAppointmentsFromSchedules(medicSchedulesCurrentDay, item);

        List<LocalTime> freeHours = new ArrayList<>();

        /* Check if surgery time fits */
        for (ScheduleEntity medicSchedule : medicSchedulesCurrentDay) {
            LocalTime minimumHour = medicSchedule.getEndHour().minusHours(surgeryTime);
            if (medicSchedule.getStartHour().equals(minimumHour) || medicSchedule.getStartHour().isBefore(minimumHour)) {
                for (LocalTime hour = medicSchedule.getStartHour();
                     !hour.equals(medicSchedule.getEndHour()) && hour.isBefore(medicSchedule.getEndHour());
                     hour = hour.plusHours(surgeryTime)) {
                    freeHours.add(hour);
                }
            }
        }

        return freeHours;
    }

    public List<ScheduleEntity> getSchedulesByDay(LocalDate date) {
        List<ScheduleEntity> medicSchedulesCurrentDay = new ArrayList<>();

        /* Deep copy of the list */
        for (ScheduleEntity medicSchedule : medicSchedules) {
            ScheduleEntity copy = new ScheduleEntity();
            copy.setStartHour(medicSchedule.getStartHour());
            copy.setEndHour(medicSchedule.getEndHour());
            copy.setDay(medicSchedule.getDay());
            medicSchedulesCurrentDay.add(copy);
        }

        /* Get only schedule entities that are in the same day as the parameter (date) */
        medicSchedulesCurrentDay = medicSchedulesCurrentDay
                .stream()
                .filter(scheduleEntity -> scheduleEntity.getDay().toString().equals(date.getDayOfWeek().toString()))
                .collect(Collectors.toList());

        return medicSchedulesCurrentDay;
    }

    public void subtractAppointmentsFromSchedules(List<ScheduleEntity> medicSchedulesCurrentDay, LocalDate item) {
        /* All appointments for current day */
        List<AppointmentEntity> appointmentEntities = appointmentDao.getByDate(item);

        for (AppointmentEntity appointmentEntity : appointmentEntities) {
            LocalTime appointmentHour = appointmentEntity.getHour();

            Long currentSurgeryTime = surgeryDao
                    .get(appointmentEntity.getIdSurgery()).getTime().getLong(ChronoField.HOUR_OF_DAY);

            for (int i = 0; i < medicSchedulesCurrentDay.size(); i++) {
                LocalTime scheduleStartHour = medicSchedulesCurrentDay.get(i).getStartHour();
                LocalTime scheduleEndHour = medicSchedulesCurrentDay.get(i).getEndHour();

                boolean isLeftEdge = appointmentHour.equals(scheduleStartHour);
                boolean isRightEdge = appointmentHour.equals(scheduleEndHour);
                boolean isInside = (appointmentHour.isAfter(scheduleStartHour) && appointmentHour.isBefore(scheduleEndHour));

                if (isLeftEdge) {
                    medicSchedulesCurrentDay.get(i).setStartHour(appointmentHour.plusHours(currentSurgeryTime));
                } else if (isInside) {
                    /* CODE TO SPLIT */
                    ScheduleEntity left = new ScheduleEntity();
                    ScheduleEntity right = new ScheduleEntity();

                    left.setStartHour(scheduleStartHour);
                    left.setEndHour(appointmentHour);

                    right.setStartHour(appointmentHour.plusHours(currentSurgeryTime));
                    right.setEndHour(scheduleEndHour);

                    medicSchedulesCurrentDay.remove(medicSchedulesCurrentDay.get(i));

                    medicSchedulesCurrentDay.add(left);
                    medicSchedulesCurrentDay.add(right);
                }
            }
        }
    }
}
