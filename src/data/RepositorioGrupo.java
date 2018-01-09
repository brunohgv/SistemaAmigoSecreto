package data;

import beans.Grupo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioGrupo implements IRepositorioGrupo {

    private static List<Grupo> listaGrupos = new ArrayList<Grupo>();

    @Override
    public boolean salvarGrupo(Grupo grupo) {
        try {
            listaGrupos.add(grupo);
        } catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public boolean deletarGrupoPorNome(String nome) {
        for (Grupo grupo : listaGrupos){
            if (grupo.getNome().equals(nome)){
                listaGrupos.remove(grupo);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Grupo> lerGrupo() {
        return listaGrupos;
    }

    @Override
    public boolean alterarGrupo(Grupo grupo) {
        for (Grupo grupo1 : listaGrupos){
            if (grupo1.getNome().equals(grupo.getNome())){
                listaGrupos.remove(grupo1);
                listaGrupos.add(grupo);
                return true;
            }
        }
        return false;
    }

}
