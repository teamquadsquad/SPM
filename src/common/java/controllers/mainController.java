package common.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import savindu.java.service.ctcServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    public ListView list_files;
    public Button btn_choose_single;
    public Button btn_next;
    public Button btn_exit;
    public Label lbl_connection;

    private Connection conn = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = db.SqliteConnection.Connector();

        try {
            checkDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void findComplexity( String selectedFilePath) throws FileNotFoundException {

        ctcServiceImpl ctcService = new ctcServiceImpl();

        int Ctc = ctcService.findCtc( selectedFilePath );


        //Cs Ctc Cnc Ci
        //Total calculation

        System.out.println( "Ctc= " + Ctc );
    }

    public void handleChooseFileSingle(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Java Files", "*.java"),
                new FileChooser.ExtensionFilter("C++ Files", "*.cpp"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null )
            list_files.getItems().add(selectedFile.getAbsolutePath());
        else
            System.out.println("File is not valid");
    }

    public void handleNext(ActionEvent actionEvent) throws FileNotFoundException {

        Object selectedFilePath;
        selectedFilePath = list_files.getSelectionModel().getSelectedItem();

        if( selectedFilePath != null ){
            findComplexity( selectedFilePath.toString() );
        }

    }

    @FXML
    private void changeScreenNextButtonPushed(ActionEvent event) throws IOException {
        Parent CustomersParent = FXMLLoader.load(getClass().getResource("output.fxml"));
        Scene CustomersScene = new Scene(CustomersParent);

        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(CustomersScene);
        window.show();
    }

    public void handleExit(ActionEvent actionEvent) {
        //TODO
    }

    public void handleRemoveFile(ActionEvent actionEvent) {
        //TODO
    }

    public void checkDbConnection() throws SQLException {
        boolean isconneted = !conn.isClosed();

        if(isconneted){
            lbl_connection.setText("Connected");
        }
        else
            lbl_connection.setText("Not Connected");
    }
}
