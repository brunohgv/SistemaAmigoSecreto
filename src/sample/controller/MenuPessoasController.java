package sample.controller;

import beans.Pessoa;
import beans.Presente;
import data.RepositorioPessoa;
import data.RepositorioPresente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import servicos.Servicos;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuPessoasController implements Initializable {

    RepositorioPessoa rp = new RepositorioPessoa();
    RepositorioPresente repositorioPresente = new RepositorioPresente();

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtApelido;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfSenha;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSalvar;

    @FXML
    private ListView<Presente> listaTodosOsPresentes;

    ObservableList<Presente> observableListPresentes = FXCollections.observableArrayList(repositorioPresente.ler());

    @FXML
    private ListView<Presente> listaPresentesDaPessoa;

    ObservableList<Presente> observableListPresentePessoa = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Pessoa> comboBox;

    ObservableList<Pessoa> observableListPessoa = FXCollections.observableArrayList(rp.lerPessoa());

    @FXML
    private Label nomePessoa;


    @FXML
    void limpar(ActionEvent event) {
        txtNome.setText("");
        txtApelido.setText("");
        txtSenha.setText("");
        txtConfSenha.setText("");
    }

    @FXML
    void salvar(ActionEvent event) {
        if (!txtNome.getText().equals("")
                || !txtApelido.getText().equals("")
                || !txtSenha.getText().equals("")
                || !txtConfSenha.getText().equals("")){
            if (txtSenha.getText().equals(txtConfSenha.getText())){
                boolean existeIgual = false;

                String nome = txtNome.getText();
                String apelido = txtApelido.getText();
                String senha = txtSenha.getText();

                for (int i = 0 ; i < rp.lerPessoa().size() ; i ++){
                    if (rp.lerPessoa().get(i).getApelido().equals(apelido)){
                        existeIgual = true;
                    }
                }

                if (!existeIgual){
                    //Salvar
                    Pessoa pessoa = new Pessoa(nome, apelido, senha);
                    rp.salvarPessoa(pessoa);

                    observableListPessoa = FXCollections.observableArrayList(rp.lerPessoa());
                    atualizar();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmação");
                    alert.setHeaderText("Sucesso!");
                    alert.setContentText(nome + " foi registrado com sucesso e possui o apelido " + apelido);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerta");
                    alert.setHeaderText("Apelido já registrado");
                    alert.setContentText("Já existe uma pessoa registrada com esse apelido");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerta");
                alert.setHeaderText("Senha Não confere");
                alert.setContentText("Senha digitada no campo 'Senha' é diferente do campo 'Confirmar Senha'");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Algum campo não foi preenchido");
            alert.showAndWait();
        }
    }


    @FXML
    void removerPresente(ActionEvent event) {
        Pessoa pessoa = comboBox.getSelectionModel().getSelectedItem();
        Presente presente = listaPresentesDaPessoa.getSelectionModel().getSelectedItem();

        pessoa.removerPresente(presente);

        atualizar();
    }

    @FXML
    void adicionarPresente(ActionEvent event) {
        Pessoa pessoa = comboBox.getSelectionModel().getSelectedItem();
        Presente presente = listaTodosOsPresentes.getSelectionModel().getSelectedItem();

        if (presente != null && pessoa != null){

            pessoa.adicionarPresente(presente);

            atualizar();

        }
        else if (pessoa == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione uma pessoa");
            alert.setContentText("Nenhuma pessoa foi selecionada");
            alert.showAndWait();
        }
        else if (presente == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione um presente");
            alert.setContentText("Nenhuma presente foi selecionado");
            alert.showAndWait();
        }
    }

    @FXML
    void selecionarComboBox(ActionEvent event) {
        atualizar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizar();
    }

    public void atualizar(){
        listaTodosOsPresentes.setItems(observableListPresentes);

        comboBox.setItems(observableListPessoa);

        observableListPresentePessoa = FXCollections.observableArrayList();

        for (int i = 0 ; i < rp.lerPessoa().size() ; i++){
            if (rp.lerPessoa().get(i).equals(comboBox.getSelectionModel().getSelectedItem())){
                observableListPresentePessoa = FXCollections.observableArrayList(rp.lerPessoa().get(i).getPresentes());
            }
        }

        listaPresentesDaPessoa.setItems(observableListPresentePessoa);

        if (comboBox.getSelectionModel().getSelectedItem() != null){
            nomePessoa.setText("Presentes de " + comboBox.getSelectionModel().getSelectedItem().getApelido());
        } else {
            nomePessoa.setText("Selecione uma pessoa");
        }

    }
}
