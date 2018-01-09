package sample.controller;

import beans.Presente;
import data.RepositorioPresente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class MenuPresentesController {

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    @FXML
    void limpar(ActionEvent event) {
        txtCategoria.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
    }

    RepositorioPresente repositorioPresente = new RepositorioPresente();

    @FXML
    void salvar(ActionEvent event) {
        if (txtDescricao.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo Descrição não foi preenchido");
            alert.showAndWait();
        }
        else if (txtPreco.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo Preço não foi preenchido");
            alert.showAndWait();
        }
        else if (txtCategoria.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo categoria não foi preenchido");
            alert.showAndWait();
        }
        else {
            float preco = Float.parseFloat(txtPreco.getText());
            Presente presente = new Presente(txtDescricao.getText(), txtCategoria.getText(), preco);
            repositorioPresente.salvar(presente);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Sucesso!");
            alert.setContentText(txtDescricao.getText() + " foi registrado com sucesso com a categoria " + txtCategoria.getText() + " e preço de R$" + preco);
            alert.showAndWait();
        }

    }
}
