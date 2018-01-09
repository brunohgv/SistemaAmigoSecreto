package sample.controller;

import beans.Grupo;
import beans.Pessoa;
import data.RepositorioGrupo;
import data.RepositorioPessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.ResourceBundle;

public class MenuSorteioController implements Initializable{

    RepositorioGrupo rg = new RepositorioGrupo();
    RepositorioPessoa rp = new RepositorioPessoa();

    @FXML
    private ComboBox<Grupo> comboBox;

    @FXML
    private ComboBox<Grupo> comboBoxGrupo;

    ObservableList<Grupo> observableListGrupo = FXCollections.observableArrayList(rg.lerGrupo());

    @FXML
    private ComboBox<Pessoa> comboBoxPessoa;

    ObservableList<Pessoa> observableListPessoa = FXCollections.observableArrayList(rp.lerPessoa());

    @FXML
    private PasswordField txtSenha;

    @FXML
    void consultar(ActionEvent event) {
        if (!comboBoxGrupo.getSelectionModel().getSelectedItem().isSorteado()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Grupo não sorteado");
            alert.setContentText("Ainda não foi realizado o sorteio desse grupo");
            alert.showAndWait();
        } else {
            Grupo g = comboBoxGrupo.getSelectionModel().getSelectedItem();
            Pessoa p = comboBoxPessoa.getSelectionModel().getSelectedItem();

            if (!txtSenha.getText().equals(p.getSenha())){
                System.out.println("Senha incorreta");
            } else {
                if (g.getPessoas().indexOf(p) == g.getPessoas().size() - 1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Amigo secreto");
                    alert.setHeaderText("Mensagem");
                    alert.setContentText("O amigo secreto de " + p.getApelido() + " no grupo " + g.getNome() + " é " + g.getPessoas().get(0).getApelido());
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Amigo secreto");
                    alert.setHeaderText("Mensagem");
                    alert.setContentText("O amigo secreto de " + p.getApelido() + " no grupo " + g.getNome() + " é " + g.getPessoas().get(g.getPessoas().indexOf(p) + 1).getApelido());
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    void sortear(ActionEvent event) {
        if (comboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione um grupo");
            alert.setContentText("Nenhum grupo foi selecionado");
            alert.showAndWait();
        }
        else if (comboBox.getSelectionModel().getSelectedItem().isSorteado()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro no sorteio");
            alert.setContentText("O grupo selecionado já foi sorteado");
            alert.showAndWait();
        }
        else {
            if (comboBox.getSelectionModel().getSelectedItem().getDataSorteio().isAfter(LocalDate.now())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro no sorteio");
                alert.setContentText("Só é possível realizar o sorteio na data prevista ou posterior");
                alert.showAndWait();
            }
            else{

                //Embaralha o array de pessoas
                Collections.shuffle(comboBox.getSelectionModel().getSelectedItem().getPessoas());

                comboBox.getSelectionModel().getSelectedItem().setSorteado(true);

                //Requisito 8
                comboBox.getSelectionModel().getSelectedItem().listarPessoas();
                comboBox.getSelectionModel().getSelectedItem().listarAmigoSecreto();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação");
                alert.setHeaderText("Grupo sorteado");
                alert.setContentText("O grupo foi sorteado com sucesso");
                alert.showAndWait();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizar();
    }

    public void atualizar(){
        comboBox.setItems(observableListGrupo);

        comboBoxGrupo.setItems(observableListGrupo);

        comboBoxPessoa.setItems(observableListPessoa);
    }
}
