package common.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.code_model;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputController implements Initializable {

    public Label lbl_connection;

    //code table
    @FXML
    private TableView<code_model> tbl_code;
    @FXML
    private TableColumn<?, ?> clm_lineNo;
    @FXML
    private TableColumn<?, ?> clm_statement;
    @FXML
    private TableColumn<?, ?> clm_sizeTokens;
    @FXML
    private TableColumn<?, ?> clm_cs;
    @FXML
    private TableColumn<?, ?> clm_ctc;
    @FXML
    private TableColumn<?, ?> clm_cnc;
    @FXML
    private TableColumn<?, ?> clm_ci;
    @FXML
    private TableColumn<?, ?> clm_tw;
    @FXML
    private TableColumn<?, ?> clm_cps;
    @FXML
    private TableColumn<?, ?> clm_cr;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<code_model> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = db.SqliteConnection.Connector();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();

        try {
            checkDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkDbConnection() throws SQLException {
        boolean isconneted = !conn.isClosed();

        if(isconneted){
            lbl_connection.setText("Connected");
        }
        else
            lbl_connection.setText("Not Connected");
    }

    private void setCellTable() {

        clm_lineNo.setCellValueFactory(new PropertyValueFactory<>("lineNo"));
        clm_statement.setCellValueFactory(new PropertyValueFactory<>("statement"));
        clm_sizeTokens.setCellValueFactory(new PropertyValueFactory<>("sizeTokens"));
        clm_cs.setCellValueFactory(new PropertyValueFactory<>("cs"));
        clm_ctc.setCellValueFactory(new PropertyValueFactory<>("ctc"));
        clm_cnc.setCellValueFactory(new PropertyValueFactory<>("cnc"));
        clm_ci.setCellValueFactory(new PropertyValueFactory<>("ci"));
        clm_tw.setCellValueFactory(new PropertyValueFactory<>("tw"));
        clm_cps.setCellValueFactory(new PropertyValueFactory<>("cps"));
        clm_cr.setCellValueFactory(new PropertyValueFactory<>("cr"));
    }

    private void loadDataFromDatabase() {

        //try {
//            pst = conn.prepareStatement("SELECT * FROM code");
//            rs = pst.executeQuery();
            //while (rs.next()) {
                //data.add(new code_model(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10) ));
                data.add(new code_model( 1,"public class FibonacciMain {", "long, fibonacci, long, number", 2,2,8,3,6,8,8 ));
            //}

//        } catch (SQLException ex) {
//            Logger.getLogger(OutputController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        tbl_code.setItems(data);
    }
}
