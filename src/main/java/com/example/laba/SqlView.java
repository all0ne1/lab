package com.example.laba;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SqlView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Students, Integer> AgeCol;

    @FXML
    private TableView<Students> DataBaseTablet;

    @FXML
    private Button GetDataBaseData;

    @FXML
    private TableColumn<Students, Integer> MarkCol;

    @FXML
    private TableColumn<Students, String> NameCol;

    @FXML
    private TableColumn<Students,String> SurCol;
    @FXML
    private TableColumn<Students, Integer> idCol;
    @FXML
    ObservableList<Students> listM;
    String password;
    String user;
    String url;
    Connection con;

    public void setCon(Connection con) {
        this.con = con;
    }

    @FXML
    public ObservableList<Students> getDataStudents(Connection c) throws SQLException {
        Connection connection = c;
        ObservableList<Students> list = FXCollections.observableArrayList();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Academic_Performance");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new Students(Integer.parseInt(rs.getString("id")),rs.getString("name"),rs.getString("surname"),
                    Integer.parseInt(rs.getString("age")),Integer.parseInt(rs.getString("mark"))));
        }
        return list;
    }
    @FXML
    void initialize() throws SQLException {
        idCol.setCellValueFactory(new PropertyValueFactory<Students,Integer>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<Students,String>("name"));
        SurCol.setCellValueFactory(new PropertyValueFactory<Students,String>("surname"));
        AgeCol.setCellValueFactory(new PropertyValueFactory<Students,Integer>("age"));
        MarkCol.setCellValueFactory(new PropertyValueFactory<Students,Integer>("mark"));
        try {
            listM = getDataStudents(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DataBaseTablet.setItems(listM);

        GetDataBaseData.setOnAction(event -> {
            // Your event handler code...
        });
    }
}

