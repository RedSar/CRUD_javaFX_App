package ma.rsmi.crud_javafx_app.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ma.rsmi.crud_javafx_app.dao.StudentManager;
import ma.rsmi.crud_javafx_app.models.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    private int selectItemId=0;

    @FXML
    public TextField tfFirstname;
    @FXML
    public TextField tfLastname;
    @FXML
    public TextField tfCourse;
    private StudentManager manager = new StudentManager();

    @FXML
    private Button btnSaveStudent;
    @FXML
    private Button btnUpdateStudent;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private Button btnClearFields;
    @FXML
    private TableView<Student> tblStudents;
    @FXML
    private TableColumn<Student,Integer> colId;
    @FXML
    private TableColumn<Student,String> colFirstname;
    @FXML
    public TableColumn<Student,String>  colLastname;
    @FXML
    public TableColumn<Student,String>  colCourse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTableData();
    }

    private void showTableData() {
        ObservableList<Student> students = manager.findAll();
        tblStudents.setItems(students);

        colId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        colCourse.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
    }

    public void saveStudent(ActionEvent event) {

        manager.saveStudent(new Student(
                tfFirstname.getText(),
                tfLastname.getText(),
                tfCourse.getText())
        );

        clearFields();

        showTableData();
    }



    public  void updateStudent(ActionEvent event) {
        if (selectItemId != 0){
            manager.updateStudent(new Student(
                    selectItemId,
                    tfFirstname.getText(),
                    tfLastname.getText(),
                    tfCourse.getText())
            );
            showTableData();
            clearFields();
            switchButtonsState();
        }
    }

    public void deleteStudent(ActionEvent event) {
        if (selectItemId != 0){
            manager.deleteStudent(selectItemId);
            showTableData();
            clearFields();
            switchButtonsState();
        }

    }

    public void clearFields() {
        tfFirstname.clear();
        tfLastname.clear();
        tfCourse.clear();
    }


    public void getDataAndPopulateForm(MouseEvent clickEvent) {
        Student student = tblStudents.getSelectionModel().getSelectedItem();
        selectItemId = student.getId();

        tfFirstname.setText(student.getFirstname());
        tfLastname.setText(student.getLastname());
        tfCourse.setText(student.getCourse());

        switchButtonsState();
    }

    private void switchButtonsState() {
        btnUpdateStudent.setDisable(!btnUpdateStudent.isDisable());
        btnDeleteStudent.setDisable(!btnDeleteStudent.isDisable());
        btnSaveStudent.setDisable(!btnSaveStudent.isDisable());
    }
}