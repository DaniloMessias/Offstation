package projetos.gerencia.apresentacao;

import java.util.Map;
import projetos.gerencia.Principal;
import projetos.gerencia.negocio.cliente.Cliente;
import projetos.gerencia.negocio.cliente.ICliente;
import projetos.gerencia.persistencia.cliente.PersistirCliente;

public final class ControlarCliente {

    public void salvar(String nome, String sobrenome, String email) {
        this.salvar(new Cliente(nome, sobrenome, email));
    }

    public boolean salvar(ICliente cliente) {
        boolean salvo = false;
        if ((PersistirCliente.getInstancia().salvar(cliente))) {
            Principal.getInstancia().log(new StringBuilder().append("Cliente '").append(cliente.getNomeCompleto()).append("' salvo com sucesso.").toString());
            salvo = true;
        } else if ((cliente != null)) {
            Principal.getInstancia().log(new StringBuilder().append("Cliente '").append(cliente.getNomeCompleto()).append("' não pode ser salvo.").toString());
        }
        return salvo;
    }

    public boolean remover(ICliente produto) {
        return PersistirCliente.getInstancia().remover(produto);
    }

    public boolean remover(int id) {
        return PersistirCliente.getInstancia().remover(id);
    }

    public ICliente recuperar(int id) {
        return PersistirCliente.getInstancia().recuperar(id);
    }

    public ICliente recuperar(String nome) {
        return PersistirCliente.getInstancia().recuperar(nome);
    }

    public Map<Integer, ICliente> recuperarTodos(String nome) {
        return PersistirCliente.getInstancia().recuperarTodos(nome);
    }

    public Map<Integer, ICliente> recuperarTodos() {
        return PersistirCliente.getInstancia().recuperarTodos();
    }

}
