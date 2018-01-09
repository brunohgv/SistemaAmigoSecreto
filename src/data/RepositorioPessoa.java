package data;

import beans.Pessoa;
import com.sun.jdi.event.ExceptionEvent;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPessoa implements IRepositorioPessoa {

    private static List<Pessoa> listaPessoas = new ArrayList<Pessoa>();

    @Override
    public boolean salvarPessoa(Pessoa pessoa) {
        try {
            listaPessoas.add(pessoa);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deletarPessoaPorApelido(String apelido) {
        for (Pessoa pessoa : listaPessoas){
            if (pessoa.getApelido().equals(apelido)){
                listaPessoas.remove(pessoa);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Pessoa> lerPessoa() {
        return listaPessoas;
    }

    @Override
    public boolean alterarPessoa(Pessoa pessoa) {
        for (Pessoa pessoa1 : listaPessoas){
            if (pessoa.getApelido().equals(pessoa1.getApelido())){
                listaPessoas.remove(pessoa1);
                listaPessoas.add(pessoa);
                return true;
            }
        }
        return false;
    }

    @Override
    public String lerApelidoPessoa(Pessoa pessoa){
        return pessoa.getApelido();
    }

    @Override
    public String lerNomeCompletoPessoa(Pessoa pessoa){
        return pessoa.getNomeCompleto();
    }

}
