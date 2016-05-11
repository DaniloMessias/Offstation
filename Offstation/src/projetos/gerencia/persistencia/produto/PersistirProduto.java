package projetos.gerencia.persistencia.produto;

import java.util.HashMap;
import java.util.Map;
import jdbchelper.QueryResult;
import projetos.gerencia.Principal;
import projetos.gerencia.negocio.produto.IProduto;
import projetos.gerencia.negocio.produto.Peca;
import projetos.gerencia.negocio.produto.Servico;
import projetos.gerencia.persistencia.Conectar;

public class PersistirProduto {

    private static PersistirProduto INSTANCIA = null;

    private PersistirProduto() {
    }

    public static PersistirProduto getInstancia() {
        if ((PersistirProduto.INSTANCIA == null)) {
            Principal.getInstancia().log("Criando instancia de persistencia do produto...");
            PersistirProduto.INSTANCIA = new PersistirProduto();
        }
        return PersistirProduto.INSTANCIA;
    }

    public boolean salvar(IProduto produto) {
        if ((produto != null)) {
            if ((produto.getId() > 0)) {
                return this.atualizar(produto);
            }
            return this.inserir(produto);
        }

        Principal.getInstancia().log("Nao é possível salvar uma instancia nula...");
        return false;
    }

    private boolean inserir(IProduto produto) {
        int tipo = (produto instanceof Peca) ? 1 : 2;
        return (Conectar.getInstancia().getJdbc().execute("INSERT INTO `produtos` (`id`, `nome`, `preco`, `tipo`, `marca`, `estoque`) VALUES ( NULL, ?, ?, ?, ?, ? )",
                new Object[]{produto.getNome(), produto.getPreco(), tipo, produto.getMarca(), produto.getEstoque()}) == 1);
    }

    private boolean atualizar(IProduto produto) {
        int tipo = (produto instanceof Peca) ? 1 : 2;
        return (Conectar.getInstancia().getJdbc().execute("UPDATE `produtos` SET `nome` = ?, `preco` = ?, `tipo` = ?, `marca` = ?, `estoque` = ? WHERE ( `id` = ? )",
                new Object[]{produto.getNome(), produto.getPreco(), tipo, produto.getMarca(), produto.getEstoque()}) == 1);
    }

    public IProduto construir(QueryResult resultado) {
        IProduto produto = null;
        if ((resultado != null)) {
            if ((resultado.getInt("tipo") == 1)) {
                produto = new Peca(resultado.getInt("id"), resultado.getInt("estoque"), resultado.getDouble("preco"), resultado.getString("nome"), resultado.getString("marca"));
            } else if ((resultado.getInt("tipo") == 2)) {
                produto = new Servico(resultado.getInt("id"), resultado.getInt("estoque"), resultado.getDouble("preco"), resultado.getString("nome"), resultado.getString("marca"));
            }
        }
        return produto;
    }

    public boolean remover(IProduto produto) {
        if ((produto != null) && (produto.getId() > 0)) {
            return (this.remover(produto.getId()));
        }
        return false;
    }

    public boolean remover(int id) {
        return (Conectar.getInstancia().getJdbc().execute("DELETE FROM `produtos` WHERE ( `id` = ? )", new Object[]{id}) == 1);
    }

    private IProduto recuperar(QueryResult resultado, boolean fechar) {
        IProduto produto = null;
        if ((resultado != null)) {
            produto = this.construir(resultado);
            if ((fechar)) {
                resultado.close();
            }
        }
        return produto;
    }

    public IProduto recuperar(int id) {
        QueryResult resultado = Conectar.getInstancia().getJdbc().query("SELECT * FROM `produtos` WHERE ( `id` = ? )", new Object[]{id});
        IProduto produto = null;
        if ((resultado.next())) {
            produto = this.recuperar(resultado, true);
        }

        return produto;
    }

    public IProduto recuperar(String nome) {
        QueryResult resultado = Conectar.getInstancia().getJdbc().query("SELECT * FROM `produtos` WHERE ( `nome` = ? )", new Object[]{nome});
        IProduto produto = null;
        if ((resultado.next())) {
            produto = this.recuperar(resultado, true);
        }

        return produto;
    }

    private Map<Integer, IProduto> recuperarTodos(QueryResult resultados) {
        Map<Integer, IProduto> produtos = new HashMap();
        while (resultados.next()) {
            IProduto produto = this.recuperar(resultados, false);
            produtos.put(produto.getId(), produto);
        }
        return produtos;
    }

    public Map<Integer, IProduto> recuperarTodos(String nome) {
        QueryResult resultados = Conectar.getInstancia().getJdbc().query("SELECT * FROM `produtos` WHERE ( `nome` = ? ) OR ( `nome` LIKE ? )", new Object[]{nome, (new StringBuilder().append("%").append(nome).append("%").toString())});
        Map<Integer, IProduto> produtos = this.recuperarTodos(resultados);
        resultados.close();
        return produtos;
    }

    public Map<Integer, IProduto> recuperarTodos() {
        QueryResult resultados = Conectar.getInstancia().getJdbc().query("SELECT * FROM `produtos`");
        Map<Integer, IProduto> produtos = this.recuperarTodos(resultados);
        resultados.close();
        return produtos;
    }

}
