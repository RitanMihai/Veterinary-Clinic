package gui.controllers;

import database.dao.*;
import database.model.*;
import gui.model.Calendar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    /* FXML members */
    @FXML
    private ComboBox<String> speciesList = new ComboBox<>();
    @FXML
    private ComboBox<AnimalEntity> breedList = new ComboBox<>();
    @FXML
    private ComboBox<MedicEntity> medicList = new ComboBox<>();
    @FXML
    private ComboBox<SurgeryEntity> surgeryList = new ComboBox<>();
    @FXML
    private ComboBox<LocalTime> hourList = new ComboBox<>();
    @FXML
    private DatePicker datePicker = new DatePicker();
    @FXML
    private Text surgeryTime = new Text();
    @FXML
    private TextField firstName = new TextField();
    @FXML
    private TextField lastName = new TextField();
    @FXML
    private TextField tin = new TextField();
    @FXML
    private TextField phoneNumber = new TextField();
    @FXML
    private TextField email = new TextField();
    @FXML
    private TextArea notes = new TextArea();

    @FXML
    private Text firstNameWarning = new Text();
    @FXML
    private Text lastNameWarning = new Text();
    @FXML
    private Text tinWarning = new Text();
    @FXML
    private Text phoneNumberWarning = new Text();
    @FXML
    private Text emailWarning = new Text();

    /* Dao members */
    AnimalDao animalDao = new AnimalDao();
    MedicDao medicDao = new MedicDao();
    SurgeryDao surgeryDao = new SurgeryDao();
    ClientDao clientDao = new ClientDao();
    AppointmentDao appointmentDao = new AppointmentDao();

    final List<AnimalEntity> BREED_LIST = new ArrayList<>();
    Calendar calendar = new Calendar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> allSpecies = animalDao.getAllSpecies();
        List<AnimalEntity> allAnimals = animalDao.getAll();

        BREED_LIST.addAll(allAnimals);
        speciesList.getItems().addAll(allSpecies);

        breedList.getItems().addAll(allAnimals);

        List<MedicEntity> allMedics = medicDao.getAll();
        medicList.getItems().addAll(allMedics);

        List<SurgeryEntity> allSurgeries = surgeryDao.getAll();
        surgeryList.getItems().addAll(allSurgeries);

        // Factory to create Cell of DatePicker
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        boolean isInSchendule = true;

                        /* The days when the doctor works */
                        /* Check if have a schedule in that day */
                        isInSchendule = !calendar.isDayAvailable(item);

                        /* The period in which you can make an appointment */
                        boolean isOneMonthPeriod = item.isBefore(LocalDate.now())
                                || item.isAfter(LocalDate.now().plusMonths(3L));

                        if (isOneMonthPeriod || isInSchendule) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    @FXML
    public void onMedicSelected(ActionEvent actionEvent) {
        calendar.setMedic(medicList
                .getSelectionModel()
                .getSelectedItem());

        clearAppointmentDate();
    }

    @FXML
    public void onDateSelect(ActionEvent actionEvent) {
        if (datePicker.getValue() == null)
            return;

        hourList.getItems().clear();
        hourList.getItems().addAll(calendar.getFreeHours(datePicker.getValue()));
    }

    public void onSelectSurgery(ActionEvent actionEvent) {
        surgeryTime.setText("Estimated time: "
                + surgeryList.getSelectionModel().getSelectedItem().getTime().getHour()
                + " hour/s");

        calendar.setSurgery(surgeryList.getValue());
        clearAppointmentDate();
    }

    public void clearAppointmentDate() {
        datePicker.setValue(null);
        hourList.setValue(null);
        hourList.getItems().clear();
    }

    public void onSelectSpecies(ActionEvent actionEvent) {
        /* MEME THREADUURI VALURI */
        Platform.runLater(() -> {
                    if (speciesList.getValue() == null)
                        return;

                    breedList.getItems().clear();

                    for (AnimalEntity animalEntity : BREED_LIST) {
                        breedList.getItems().add(animalEntity);
                    }

                    List<AnimalEntity> collect = breedList.getItems()
                            .stream()
                            .filter(animalEntity -> animalEntity.getSpecies().equals(speciesList.getValue()))
                            .collect(Collectors.toList());

                    breedList.getItems().clear();
                    breedList.getItems().addAll(collect);
                }
        );
    }

    public void onSelectBreed(ActionEvent actionEvent) {
        if (breedList.getValue() == null)
            return;
        final AnimalEntity selectedSpecies = breedList.getValue();
        speciesList.setValue(selectedSpecies.getSpecies());

        Platform.runLater(() -> breedList.setValue(selectedSpecies));
    }

    public void onSubmitClicked(MouseEvent mouseEvent) {
        markInvalidFields();

        if (!areAllFieldsValid())
            return;

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setFirstName(firstName.getText());
        clientEntity.setLastName(lastName.getText());
        clientEntity.setTin(tin.getText());
        clientEntity.setPhoneNumber(phoneNumber.getText());
        clientEntity.setEmail(email.getText());

        clientDao.create(clientEntity);

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDate(datePicker.getValue());
        appointmentEntity.setHour(hourList.getValue());
        appointmentEntity.setNotes(notes.getText());
        appointmentEntity.setIdSurgery(surgeryList.getValue().getId());
        appointmentEntity.setIdMedic(medicList.getValue().getId());
        appointmentEntity.setIdClient(clientEntity.getId());
        appointmentEntity.setIdAnimal(breedList.getValue().getId());

        appointmentDao.create(appointmentEntity);

        System.out.println(
                firstName.getText() + " " + lastName.getText()
        );

        /* Call parent */
        Window window = ((Node) mouseEvent.getTarget()).getScene().getWindow();
        try {
            loadSubmittedPage(window);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void loadSubmittedPage(Window window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/gui/submitted.fxml"));
        Parent submitted = (Parent) fxmlLoader.load();

        /* get the dimension and position of previous window */
        double height = window.getHeight();
        double width = window.getWidth();
        double x = window.getX();
        double y = window.getY();
        boolean isFullscreen = ((Stage) window).isFullScreen();

        Scene scene = new Scene(submitted);
        Stage appStage = (Stage) window;
        appStage.setScene(scene);

        appStage.setHeight(height);
        appStage.setWidth(width);
        appStage.setX(x);
        appStage.setY(y);
        appStage.setFullScreen(isFullscreen);

        appStage.show();
    }

    private boolean areAllFieldsValid() {
        if (firstName.getText().isEmpty())
            return false;

        if (lastName.getText().isEmpty())
            return false;

        if (tin.getText().isEmpty())
            return false;

        if (phoneNumber.getText().isEmpty())
            return false;

        if (email.getText().isEmpty())
            return false;

        if (speciesList.getValue() == null)
            return false;

        if (breedList.getValue() == null)
            return false;

        if (medicList.getValue() == null)
            return false;

        if (surgeryList.getValue() == null)
            return false;

        if (datePicker.getValue() == null)
            return false;

        if (hourList.getValue() == null)
            return false;

        return true;
    }

    private void markInvalidFields() {
        if (firstName.getText().isEmpty())
            firstNameWarning.setVisible(true);

        if (lastName.getText().isEmpty())
            lastNameWarning.setVisible(true);

        if (tin.getText().isEmpty())
            tinWarning.setVisible(true);

        if (phoneNumber.getText().isEmpty())
            phoneNumberWarning.setVisible(true);

        if (email.getText().isEmpty())
            emailWarning.setVisible(true);

        if (speciesList.getValue() == null)
            speciesList.setPromptText("* Required");

        if (breedList.getValue() == null)
            breedList.setPromptText("* Required");


        if (medicList.getValue() == null)
            medicList.setPromptText("* Required");


        if (surgeryList.getValue() == null)
            surgeryList.setPromptText("* Required");


        if (datePicker.getValue() == null)
            datePicker.setPromptText("* Required");

        if (hourList.getValue() == null)
            hourList.setPromptText("* Required");
    }

    public void onFirstNameKeyPressed(KeyEvent keyEvent) {
        firstNameWarning.setVisible(false);
    }

    public void onLastNameKeyPressed(KeyEvent keyEvent) {
        lastNameWarning.setVisible(false);
    }

    public void onTinKeyPressed(KeyEvent keyEvent) {
        tinWarning.setVisible(false);
    }

    public void onPhoneNumberKeyPressed(KeyEvent keyEvent) {
        phoneNumberWarning.setVisible(false);
    }

    public void onEmailKeyPressed(KeyEvent keyEvent) {
        emailWarning.setVisible(false);
    }
}
