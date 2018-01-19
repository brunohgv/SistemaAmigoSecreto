package controller;

import beans.Grupo;
import beans.Pessoa;
import beans.Presente;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class Fachada {

    private static Fachada instance;
    Negocios negocios;

    private Fachada () {
        negocios = new Negocios();
    }

    public static Fachada getInstance(){
        if (instance == null){
            instance = new Fachada();
        }
        return instance;
    }


    //listar
    public List<Pessoa> listarPessoas(){
        return negocios.listarPessoas();
    }

    public List<Presente> listarPresentes(){
        return negocios.listarPresentes();
    }

    public List<Grupo> listarGrupos(){
        return negocios.listarGrupos();
    }

    //Pessoas
    public int salvarPessoa(String nome, String apelido, String senha, String confSenha){
        int codigoMensagem = negocios.salvarPessoa(nome, apelido, senha, confSenha);
        return codigoMensagem;
    }

    public int adicionarPessoaGrupo(Pessoa pessoa, Grupo grupo){
        int codigoMensagem = negocios.adicionarPessoaGrupo(pessoa, grupo);
        return codigoMensagem;
    }

    public boolean removerPessoaGrupo(Pessoa pessoa, Grupo grupo){
        boolean codigoMensagem = negocios.removerPessoaGrupo(pessoa, grupo);
        return codigoMensagem;
    }

    public ObservableList<Pessoa> listarPessoasGrupo(Grupo grupo){
        return negocios.listarPessoasGrupo(grupo);
    }

    //grupos
    public int salvarGrupo(String nome, LocalDate data){
        return negocios.salvarGrupo(nome, data);
    }

    //presentes
    public void adicionarPresentePessoa(Presente presente, Pessoa pessoa){
        negocios.adicionarPresentePessoa(presente, pessoa);
    }

    public void removerPresentePessoa(Presente presente, Pessoa pessoa){
        negocios.removerPresentePessoa(presente, pessoa);
    }

    public ObservableList<Presente> listarPresentesPessoa(Pessoa pessoa){
        return negocios.lerPresentesPessoa(pessoa);
    }

    public int salvarPresente(String descricao, String categoria, String preco){
        return negocios.salvarPresente(descricao, categoria, preco);
    }

    //Sorteio

    public Pessoa consultarAmigoSecreto(Grupo grupo, Pessoa pessoa, String senha) {
        return consultarAmigoSecreto (grupo, pessoa, senha);
    }

    public int sortear(Grupo grupo){
        return negocios.sortear(grupo);
    }
}
