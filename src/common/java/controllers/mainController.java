package common.java.controllers;

import hashini.java.service.Inheritance;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import savindu.java.service.ctcServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    public ListView list_files;
    public Button btn_choose_single;
    public Button btn_next;
    public Button btn_exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void findComplexity(String selectedFilePath) throws FileNotFoundException {

        ctcServiceImpl ctcService = new ctcServiceImpl();

        int Ctc = ctcService.findCtc( selectedFilePath );

        Inheritance inheritance = new Inheritance();
        try {
            inheritance.checkCi(selectedFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Cs Ctc Cnc Ci
        //Total calculation

        System.out.println( "Ctc= " + Ctc );

//        Parent ContractParent = FXMLLoader.load(getClass().getResource("ContractManagement.fxml"));
//        Scene ContractScene = new Scene(ContractParent);
//
//        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
//        window.setScene(ContractScene);
//        window.show();
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

    public void handleExit(ActionEvent actionEvent) {
        //TODO
    }

    public void handleRemoveFile(ActionEvent actionEvent) {
        //TODO
    }
}
