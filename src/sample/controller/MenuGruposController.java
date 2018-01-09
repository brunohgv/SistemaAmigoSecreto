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
import java.util.ResourceBundle;

public class MenuGruposController implements Initializable{

    RepositorioGrupo rg = new RepositorioGrupo();
    RepositorioPessoa rp = new RepositorioPessoa();

    @FXML
    private TextField txtNomeGrupo;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<Pessoa> listaTodasAsPessoas;

    private ObservableList<Pessoa> observableListTodas = FXCollections.observableArrayList(rp.lerPessoa());


    @FXML
    private ListView<Pessoa> listaPessoasNoGrupo;

    private ObservableList<Pessoa> observableListPessoaGrupo;


    @FXML
    private ComboBox<Grupo> comboBox;

    private ObservableList<Grupo> observableListGrupo = FXCollections.observableArrayList(rg.lerGrupo());


    @FXML
    void adicionar(ActionEvent event) {
        Pessoa p = listaTodasAsPessoas.getSelectionModel().getSelectedItem();
        Grupo g = comboBox.getSelectionModel().getSelectedItem();

        if(p != null && g != null && !g.getPessoas().contains(p)){

            g.adicionarPessoa(p);
            g.setSorteado(false);

            atualizarListas();
        }
        else if (g.getPessoas().contains(p)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Adição inválida");
            alert.setContentText("Essa pessoa já foi adicionada ao grupo");
            alert.showAndWait();
        }
        else if (p == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione uma pessoa");
            alert.setContentText("Nenhuma pessoa foi selecionada");
            alert.showAndWait();
        }
        else if (g == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione um grupo");
            alert.setContentText("Nenhum grupo foi selecionado");
            alert.showAndWait();
        }

    }

    @FXML
    void remover(ActionEvent event) {
        Pessoa p = listaPessoasNoGrupo.getSelectionModel().getSelectedItem();
        Grupo g = comboBox.getSelectionModel().getSelectedItem();

        g.removerPessoa(p);

        atualizarListas();
    }

    @FXML
    void limpar(ActionEvent event) {
        txtNomeGrupo.setText("");
        datePicker.setValue(null);
    }

    @FXML
    void salvar(ActionEvent event) {
        if (txtNomeGrupo.getText().equals("") && datePicker.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Nenhum campo foi preenchido");
            alert.showAndWait();
        }
        else if (datePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Data não foi selecionada");
            alert.showAndWait();
        }
        else if (txtNomeGrupo.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo Nome não foi preenchido");
            alert.showAndWait();
        } else {
            boolean existe = false;
            for (int i = 0 ; i < rg.lerGrupo().size() ; i ++){
                if (rg.lerGrupo().get(i).getNome().equals(txtNomeGrupo.getText())){
                    existe = true;
                }
            }
            if (existe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Grupo já existe");
                alert.setContentText("Já foi registrado um grupo com esse nome");
                alert.showAndWait();
            }
            else {
                Grupo grupo = new Grupo(txtNomeGrupo.getText(), datePicker.getValue());
                rg.salvarGrupo(grupo);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmação");
                alert.setHeaderText("Sucesso!");
                alert.setContentText(txtNomeGrupo.getText() + " foi registrado com sucesso com a data " + datePicker.getValue());
                alert.showAndWait();
            }

            observableListGrupo = FXCollections.observableArrayList(rg.lerGrupo());
            atualizarListas();
        }

    }

    @FXML
    void selecionarComboBox(ActionEvent event) {
        atualizarListas();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarListas();
    }

    public void atualizarListas(){

        //Listview
        listaTodasAsPessoas.setItems(observableListTodas);

        //comboBox
        comboBox.setItems(observableListGrupo);

        observableListPessoaGrupo = FXCollections.observableArrayList();

        for (int i = 0 ; i < rg.lerGrupo().size() ; i++){
            if (rg.lerGrupo().get(i).equals(comboBox.getSelectionModel().getSelectedItem())){
                observableListPessoaGrupo = FXCollections.observableArrayList(rg.lerGrupo().get(i).getPessoas());
            }
        }

        listaPessoasNoGrupo.setItems(observableListPessoaGrupo);
    }
}
