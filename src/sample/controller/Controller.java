package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button btnPessoas;

    @FXML
    private Button btnGrupos;

    @FXML
    private Button btnPresentes;

    @FXML
    private Button btnSorteio;

    @FXML
    void openMenuPessoas(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/menuPessoas.fxml"));
        contentPane.getChildren().setAll(pane);
    }

    @FXML
    void openMenuGrupos(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/menuGrupos.fxml"));
        contentPane.getChildren().setAll(pane);
    }

    @FXML
    void openMenuPresentes(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/menuPresentes.fxml"));
        contentPane.getChildren().setAll(pane);
    }

    @FXML
    void openMenuSorteio(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/menuSorteio.fxml"));
        contentPane.getChildren().setAll(pane);
    }


}
