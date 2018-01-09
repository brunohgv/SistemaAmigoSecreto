package data;

import beans.Presente;

import java.util.List;

public interface IRepositorioPresente {

    boolean salvar(Presente presente);
    boolean deletar(String descricao);
    List<Presente> ler();
    boolean alterar(Presente presente);

}
