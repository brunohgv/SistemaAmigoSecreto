package data;

import beans.Presente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPresente implements IRepositorioPresente {

    private static List<Presente> listaPresentes = new ArrayList<Presente>();

    @Override
    public boolean salvar(Presente presente) {
        try {
            listaPresentes.add(presente);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(String descricao) {
        for(int i = 0; i < listaPresentes.size() ; i ++){
            if (listaPresentes.get(i).getDescricao().equals(descricao)){
                listaPresentes.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Presente> ler() {
        return listaPresentes;
    }

    @Override
    public boolean alterar(Presente presente) {
        return false;
    }
}
