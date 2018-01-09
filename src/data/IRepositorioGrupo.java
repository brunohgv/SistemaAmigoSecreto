package data;

import beans.Grupo;

import java.util.ArrayList;
import java.util.List;

public interface IRepositorioGrupo {

    boolean salvarGrupo(Grupo grupo);
    boolean deletarGrupoPorNome(String nome);
    List<Grupo> lerGrupo();
    boolean alterarGrupo(Grupo grupo);

}
