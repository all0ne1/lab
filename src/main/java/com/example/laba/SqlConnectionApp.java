package com.example.laba;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.laba.SqlView;

public class SqlConnectionApp {
    @FXML
    public Button BackToMainMenu;
    @FXML
    public Button goToDataBase;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public TextField DataBaseAddress;

    @FXML
    private Button DataBaseConnectButton;

    @FXML
    public  TextField DataBaseLogin;

    @FXML
    public  TextField DataBaseName;

    @FXML
    private TextArea DataBaseOutPutArea;

    @FXML
    public  PasswordField DataBasePassword;

    @FXML
    void initialize() {
        DataBaseConnectButton.setOnAction(event -> {
            connectToDatabase();
        });
        BackToMainMenu.setOnAction(event -> {
            BackToMainMenu.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Main menu");
            stage.show();
        });
        goToDataBase.setOnAction(event -> {
            String serverAddress = getDataBaseAddress();
            String dbName = getDataBaseName();
            String username = getDataBaseLogin();
            String password = getDataBasePassword();
            String url = "jdbc:sqlserver://" + serverAddress + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
            goToDataBase.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SqlView.fxml"));
            try {
                loader.load();
                Connection connection = connectedDataBase(url, username, password);
                SqlView control = loader.getController();
                control.setCon(connection);
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("DataBase");
                stage.show();
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    private void connectToDatabase() {
        String serverAddress = getDataBaseAddress();
        String dbName = getDataBaseName();
        String username = getDataBaseLogin();
        String password = getDataBasePassword();
        String url = "jdbc:sqlserver://" + serverAddress + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
        try {
            Connection connection = connectedDataBase(url,username,password);
            DataBaseOutPutArea.appendText("Connected to the database successfully!\n");
            // You can use the 'connection' object to perform database operations.

        } catch (SQLException e) {
            DataBaseOutPutArea.appendText("Connection error: " + e.getMessage() + "\n");
        }

    }
    public static Connection connectedDataBase(String url, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }

    public  String getDataBaseAddress() {
        return DataBaseAddress.getText();
    }
    public String getDataBaseName() {
        return DataBaseName.getText();
    }
    public String getDataBaseLogin() {
        return DataBaseLogin.getText();
    }
    public String getDataBasePassword() {
        return DataBasePassword.getText();
    }
    public  String getUrl(){
        String url = "jdbc:sqlserver://" + getDataBaseAddress() + ";databaseName=" + getDataBaseName() + ";encrypt=true;trustServerCertificate=true;";
        return url;
    }
}