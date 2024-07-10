package hw.q4;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

public class HWQ4 extends Application {

    private TextField idField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField phoneField;
    private TextArea addressField;
    private ChoiceBox<String> citySelector;
    private ToggleGroup genderGroup;
    private DatePicker dobField;
    private Label errorsViewer;
    private EmployeeManager employeeManager = new EmployeeManager();
    private Employee employee = new Employee();

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = createMainLayout();
        Scene scene = new Scene(mainLayout, 720, 640);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("نظام إدارة الموظفين");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMainLayout() {
        
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getStyleClass().add("grid-background");

        HBox introLayout = createIntroLayout();
        mainLayout.getChildren().add(introLayout);
        
        errorsViewer = new Label();  // Initialize the errorsViewer label
        errorsViewer.getStyleClass().add("errorViewer");
        mainLayout.getChildren().add(errorsViewer);
        
        HBox continerLayout = createFetchContiner();
        mainLayout.getChildren().add(continerLayout);
        
       

        GridPane formLayout = createFormLayout();
        mainLayout.getChildren().add(formLayout);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), mainLayout);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        return mainLayout;
    }

    private HBox createIntroLayout() {
        HBox introLayout = new HBox(10);
        introLayout.setAlignment(Pos.CENTER);

        Label introLabel = new Label("نظام إدارة الموظفين");
        introLabel.getStyleClass().add("intro-label");
        introLayout.getChildren().add(introLabel);

        return introLayout;
    }
    
    private HBox createFetchContiner() {
        HBox continerLayout = new HBox(10);
        continerLayout.setAlignment(Pos.CENTER);
        
        ChoiceBox<Integer> idChoiceBox = new ChoiceBox<>();
        idChoiceBox.getStyleClass().add("id-choice-box");
        continerLayout.getChildren().add(idChoiceBox);
        
        Button fetchIdsButton = new Button("معرفات المتاحة");
        fetchIdsButton.getStyleClass().add("button");
        continerLayout.getChildren().add(fetchIdsButton);
       
        
        fetchIdsButton.setOnAction(e -> fetchAvailableIds(idChoiceBox));
        idChoiceBox.setOnAction(e -> fetchEmployeeData(idChoiceBox.getValue()));
        return continerLayout;
    }
    

    private GridPane createFormLayout() {
        GridPane formLayout = new GridPane();
        formLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.setPadding(new Insets(5));
        formLayout.setHgap(20);
        formLayout.setVgap(20);

        addFormControls(formLayout);
        addFormButtons(formLayout);

        return formLayout;
    }

    private void addFormControls(GridPane formLayout) {
        // ID
        formLayout.add(new Label("أدخل معرفك:"), 2, 0);
        idField = new TextField();
        formLayout.add(idField, 2, 1);

        // First Name
        formLayout.add(new Label("أدخل الاسم الأول:"), 1, 0);
        firstNameField = new TextField();
        formLayout.add(firstNameField, 1, 1);

        // Last Name
        formLayout.add(new Label("أدخل اسم العائلة:"), 0, 0);
        lastNameField = new TextField();
        formLayout.add(lastNameField, 0, 1);

        // Phone
        formLayout.add(new Label("أدخل رقم الهاتف:"), 2, 2);
        phoneField = new TextField();
        formLayout.add(phoneField, 2, 3);

        // Address
        formLayout.add(new Label("أدخل عنوانك:"), 0, 2, 2, 1);
        addressField = new TextArea();
        addressField.setPrefRowCount(1);
        formLayout.add(addressField, 0, 3, 2, 1);

        // Gender
        formLayout.add(new Label("الجنس:"), 2, 4);
        genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("ذكر");
        maleRadio.setToggleGroup(genderGroup);
        formLayout.add(maleRadio, 1, 4);
        RadioButton femaleRadio = new RadioButton("أنثى");
        femaleRadio.setToggleGroup(genderGroup);
        formLayout.add(femaleRadio, 0, 4);

        // City
        formLayout.add(new Label("اختر مدينتك:"), 2, 5);
        citySelector = new ChoiceBox<>();
        citySelector.getItems().addAll("طرابلس", "الزاوية", "بنغازي", "تونس", "القاهرة");
        formLayout.add(citySelector, 2, 6);

        // Date of Birth
        formLayout.add(new Label("أدخل تاريخ الميلاد:"), 0, 5, 2, 1);
        dobField = new DatePicker();
        formLayout.add(dobField, 0, 6, 2, 1);
    }

    private void addFormButtons(GridPane formLayout) {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button insertButton = new Button("إدخال");
        insertButton.getStyleClass().add("button");
        insertButton.setOnAction(e -> handleInsert());
        buttonBox.getChildren().add(insertButton);

        Button updateButton = new Button("تحديث");
        updateButton.getStyleClass().add("button");
        updateButton.setOnAction(e -> handleUpdate());
        buttonBox.getChildren().add(updateButton);

        Button deleteButton = new Button("حذف");
        deleteButton.getStyleClass().add("button");
        deleteButton.setOnAction(e -> handleDelete());
        buttonBox.getChildren().add(deleteButton);

        Button showButton = new Button("عرض");
        showButton.getStyleClass().add("button");
        buttonBox.getChildren().add(showButton);

        formLayout.add(buttonBox, 2, 7);

        Button exitButton = new Button("خروج");
        exitButton.getStyleClass().add("button-exit");
        exitButton.setOnAction(e -> Platform.exit());
        HBox exitBox = new HBox(exitButton);
        exitBox.setAlignment(Pos.CENTER);
        formLayout.add(exitBox, 0, 7, 2, 1);
    }

    private void handleInsert() {
        populateEmployeeFromFields();
        try{
            employeeManager.insertEmployee(employee);
        } catch (SQLException ex) {
            errorsViewer.setText("خطأ في المعلومات، لم يتم إدراح الموضف بالمنظومة");
        }
        
    }

    private void handleUpdate() {
        populateEmployeeFromFields();
        try {
            employeeManager.updateEmployee(employee);

        } catch (SQLException ex) {
            errorsViewer.setText("خطأ في المعلومات، لم يتم تحديث الموضف بالمنظومة");
        }
    }

    private void handleDelete() {
        int employeeId = Integer.parseInt(idField.getText());
        try {
            employeeManager.deleteEmployee(employeeId);
        } catch (SQLException ex) {
            errorsViewer.setText("خطأ في المعلومات، لم يتم حذف الموضف بالمنظومة");
        }
    }

    private void populateEmployeeFromFields() {
        employee.setEid(Integer.parseInt(idField.getText()));
        employee.setFirstName(firstNameField.getText());
        employee.setLastName(lastNameField.getText());
        employee.setAddress(addressField.getText());
        employee.setCity(citySelector.getValue());
        employee.setPhoneNum(phoneField.getText());
        employee.setSex(genderGroup.getSelectedToggle().equals("ذكر"));
        employee.setDob(dobField.getValue());
    }
    
    private void fetchAvailableIds(ChoiceBox<Integer> idChoiceBox) {
        try {
            List<Employee> employees = employeeManager.listEmployees();
            idChoiceBox.getItems().clear();
            for (Employee employee : employees) {
                idChoiceBox.getItems().add(employee.getEid());
            }
        } catch (SQLException ex) {
             errorsViewer.setText("حدث خطأ!");
        }
    }
    
    private void fetchEmployeeData(int id) {
        try {
            employee = employeeManager.getEmployee(id);
            idField.setText(String.valueOf(employee.getEid()));
            firstNameField.setText(employee.getFirstName());
            lastNameField.setText(employee.getLastName());
            phoneField.setText(employee.getPhoneNum());
            addressField.setText(employee.getAddress());
            citySelector.setValue(employee.getCity());
            dobField.setValue(employee.getDob().toLocalDate());
        } catch (SQLException ex) {
            errorsViewer.setText("حدث خطأ!");
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
