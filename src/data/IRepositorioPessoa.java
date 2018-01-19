package data;

import beans.Pessoa;
import beans.Presente;

import java.util.List;

public interface IRepositorioPessoa {

    boolean salvarPessoa(Pessoa pessoa);
    boolean deletarPessoaPorApelido(String apelido);
    List<Pessoa> lerPessoa();
    boolean alterarPessoa(Pessoa pessoa);
    String lerApelidoPessoa(Pessoa pessoa);
    String lerNomeCompletoPessoa(Pessoa pessoa);
    boolean removerPresente(Pessoa pessoa, Presente presente);
    boolean adicionarPresente(Pessoa pessoa, Presente presente);

}
