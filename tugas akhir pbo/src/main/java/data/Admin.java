package data;

import com.main.LibrarySystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.iMenu;

import java.util.Random;

public class Admin extends User implements iMenu {

    public static String adminusername = "1";
    public static String adminpassword = "1";

    @Override
    public void menu() {
        Stage adminMenuStage = new Stage();
        adminMenuStage.setTitle("UMM Library - Admin Menu");

        // Label
        Label sceneTitle = new Label("Menu Admin");

        // Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        // Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        // Buttons
        Button addStudentButton = new Button("Tambah Mahasiswa");
        Button displayStudentButton = new Button("Daftar Mahasiswa");
        Button addBookButton = new Button("Tambah Buku");
        Button logoutButton = new Button("Logout");

        // Button Styles
        addStudentButton.setStyle("-fx-background-color: #A91D3A; -fx-text-fill: white; -fx-font-size: 14px;");
        displayStudentButton.setStyle("-fx-background-color: #A91D3A; -fx-text-fill: white; -fx-font-size: 14px;");
        addBookButton.setStyle("-fx-background-color: #A91D3A; -fx-text-fill: white; -fx-font-size: 14px;");
        logoutButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black; -fx-font-size: 14px;");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(6);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(sceneTitle, 0, 0);
        grid.add(addStudentButton, 0, 1);
        grid.add(displayStudentButton, 0, 2);
        grid.add(addBookButton, 0, 3);
        grid.add(logoutButton, 0, 4);

        Scene scene = new Scene(grid, 1360, 720);

        // Set background image
        try {
            Image background = new Image("file:src/main/java/img/admin.jpg");
            BackgroundImage backgroundImage = new BackgroundImage(
                    background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            grid.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            e.printStackTrace();
            grid.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        adminMenuStage.setScene(scene);
        adminMenuStage.show();

        // Button Actions
        addStudentButton.setOnAction(event -> {
            addStudent();
            adminMenuStage.close();
        });

        displayStudentButton.setOnAction(event -> {
            displayStudent();
            adminMenuStage.close();
        });

        addBookButton.setOnAction(event -> {
            inputBook();
            adminMenuStage.close();
        });

        logoutButton.setOnAction(event -> {
            LibrarySystem librarySystemObj = new LibrarySystem();
            librarySystemObj.start(new Stage());
            adminMenuStage.close();
        });
    }

    public void addStudent() {
        // Create new form
        Stage addStudentStage = new Stage();
        addStudentStage.setTitle("Tambah Mahasiswa");

        // Labels
        Label sceneTitle = new Label("Tambah Mahasiswa");
        Label nameLabel = new Label("Nama");
        Label nimLabel = new Label("NIM");
        Label fakultasLabel = new Label("Fakultas");
        Label jurusanLabel = new Label("Jurusan");

        // Notification Label
        Label submitFailed = new Label("NIM harus 15 digit!");
        submitFailed.setVisible(false);

        // Fields
        TextField nameField = new TextField();
        TextField nimField = new TextField();
        TextField fakultasField = new TextField();
        TextField jurusanField = new TextField();

        // Font Styles
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        nameLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        nimLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        fakultasLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        jurusanLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));

        // Font Colors
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");
        submitFailed.setStyle("-fx-text-fill: #FF1E1E;");

        // Buttons
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        // Button Styles
        submitButton.setStyle("-fx-background-color: #A91D3A; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black; -fx-font-size: 14px;");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.add(nameLabel, 0, 1);
        grid.add(nimLabel, 0, 2);
        grid.add(fakultasLabel, 0, 3);
        grid.add(jurusanLabel, 0, 4);
        grid.add(nameField, 1, 1);
        grid.add(nimField, 1, 2);
        grid.add(fakultasField, 1, 3);
        grid.add(jurusanField, 1, 4);
        grid.add(submitButton, 1, 5);
        grid.add(backButton, 0, 6);
        grid.add(submitFailed, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 1360, 720);
        addStudentStage.setScene(scene);
        addStudentStage.show();

        // Button Actions
        submitButton.setOnAction(event -> {
            if (nimField.getText().length() == 15) {
                Student.arr_userStudent.add(new Student.UserStudent(nameField.getText(), nimField.getText(), fakultasField.getText(), jurusanField.getText()));
                menu();
                addStudentStage.close();
            } else {
                submitFailed.setVisible(true);
            }
        });

        backButton.setOnAction(event -> {
            menu();
            addStudentStage.close();
        });
    }

    public void displayStudent() {
        // Create new stage
        Stage displayStudentStage = new Stage();
        displayStudentStage.setTitle("UMM Library - Daftar Mahasiswa");

        // Label
        Label sceneTitle = new Label("Daftar Mahasiswa");

        // Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        // Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        // ListView for displaying students
        ListView<String> listView = new ListView<>();
        for (Student.UserStudent i : Student.arr_userStudent) {
            String studentInfo = "Nama     : " + i.nama + "\n" +
                    "NIM      : " + i.nim + "\n" +
                    "Fakultas : " + i.fakultas + "\n" +
                    "Prodi    : " + i.prodi + "\n" +
                    "===========================";
            listView.getItems().add(studentInfo);
        }

        // Button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black; -fx-font-size: 14px;");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(sceneTitle, 0, 0);
        grid.add(listView, 0, 1);
        grid.add(backButton, 0, 2);

        Scene scene = new Scene(grid, 1360, 720);
        displayStudentStage.setScene(scene);
        displayStudentStage.show();

        // Button Action
        backButton.setOnAction(event -> {
            menu();
            displayStudentStage.close();
        });
    }

    public void inputBook() {
        super.inputBook();
    }

    public String generateId() {
        Random random = new Random();
        StringBuilder idTengah = new StringBuilder();
        StringBuilder idAkhir = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            idTengah.append(random.nextInt(10));
            idAkhir.append(random.nextInt(10));
        }
        return "UMM-" + idTengah + "-" + idAkhir;
    }
}