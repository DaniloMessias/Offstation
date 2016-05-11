package projetos.gerencia.persistencia.cliente;

import java.util.HashMap;
import java.util.Map;
import jdbchelper.QueryResult;
import projetos.gerencia.Principal;
import projetos.gerencia.negocio.cliente.Cliente;
import projetos.gerencia.negocio.cliente.ICliente;
import projetos.gerencia.persistencia.Conectar;

public class PersistirCliente {

    private static PersistirCliente INSTANCIA = null;

    private PersistirCliente() {
    }

    public static PersistirCliente getInstancia() {
        if ((PersistirCliente.INSTANCIA == null)) {
            Principal.getInstancia().log("Criando instancia de persistencia do cliente...");
            PersistirCliente.INSTANCIA = new PersistirCliente();
        }

        return PersistirCliente.INSTANCIA;
    }

    public boolean salvar(ICliente cliente) {
        if ((cliente != null)) {
            if ((cliente.getId() > 0)) {
                return this.atualizar(cliente);
            }
            return this.inserir(cliente);
        }

        Principal.getInstancia().log("Nao é possível salvar uma instancia nula...");
        return false;
    }

    private boolean inserir(ICliente cliente) {
        return (Conectar.getInstancia().getJdbc().execute("INSERT INTO `clientes` ( `id`, `nome`, `sobrenome`, `email` ) VALUES ( NULL, ?, ?, ? )",
                new Object[]{cliente.getNome(), cliente.getSobrenome(), cliente.getEmail()}) == 1);

    }

    private boolean atualizar(ICliente cliente) {
        return (Conectar.getInstancia().getJdbc().execute("UPDATE `clientes` SET ( `nome` = ? ), ( `sobrenome` = ? ), ( `email` = ? ) WHERE ( `id` = ? )",
                new Object[]{cliente.getNome(), cliente.getSobrenome(), cliente.getEmail()}) == 1);
    }

    public boolean remover(ICliente cliente) {
        if ((cliente != null) && (cliente.getId() > 0)) {
            return (this.remover(cliente.getId()));
        }
        return false;
    }

    public boolean remover(int id) {
        return (Conectar.getInstancia().getJdbc().execute("DELETE FROM `clientes` WHERE ( `id` = ? )", new Object[]{id}) == 1);
    }

    private ICliente construir(QueryResult resultado, boolean fechar) {
        ICliente cliente = null;
        if ((resultado != null)) {
            cliente = new Cliente(resultado.getInt("id"), resultado.getString("nome"), resultado.getString("sobrenome"), resultado.getString("email"));
            if ((fechar)) {
                resultado.close();
            }
        }
        return cliente;
    }

    public ICliente recuperar(int id) {
        QueryResult resultado = Conectar.getInstancia().getJdbc().query("SELECT * FROM `clientes` WHERE ( `id` = ? )", new Object[]{id});
        ICliente cliente = null;
        if ((resultado.next())) {
            cliente = this.construir(resultado, true);
        }

        return cliente;
    }

    public ICliente recuperar(String nome) {
        QueryResult resultado = Conectar.getInstancia().getJdbc().query("SELECT * FROM `clientes` WHERE ( `nome` = ? )", new Object[]{nome});
        ICliente cliente = null;
        if ((resultado.next())) {
            cliente = this.construir(resultado, true);
        }

        return cliente;
    }

    private Map<Integer, ICliente> recuperarTodos(QueryResult resultados) {
        Map<Integer, ICliente> clientes = new HashMap();
        while (resultados.next()) {
            ICliente cliente = this.construir(resultados, false);
            clientes.put(cliente.getId(), cliente);
        }
        return clientes;
    }

    public Map<Integer, ICliente> recuperarTodos(String nome) {
        QueryResult resultados = Conectar.getInstancia().getJdbc().query("SELECT * FROM `clientes` WHERE ( `nome` = ? ) OR ( `nome` LIKE ? )", new Object[]{nome, (new StringBuilder().append("%").append(nome).append("%").toString())});
        Map<Integer, ICliente> clientes = this.recuperarTodos(resultados);
        resultados.close();
        return clientes;
    }

    public Map<Integer, ICliente> recuperarTodos() {
        QueryResult resultados = Conectar.getInstancia().getJdbc().query("SELECT * FROM `clientes`");
        Map<Integer, ICliente> clientes = this.recuperarTodos(resultados);
        resultados.close();
        return clientes;
    }

}
