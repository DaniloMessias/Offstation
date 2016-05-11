package projetos.gerencia.persistencia.veiculo;

import java.util.HashMap;
import java.util.Map;
import jdbchelper.QueryResult;
import projetos.gerencia.Principal;
import projetos.gerencia.apresentacao.ControlarCliente;
import projetos.gerencia.negocio.cliente.ICliente;
import projetos.gerencia.negocio.veiculo.IVeiculo;
import projetos.gerencia.negocio.veiculo.Veiculo;
import projetos.gerencia.persistencia.Conectar;

public class PersistirVeiculo {

    private static PersistirVeiculo INSTANCIA = null;

    private PersistirVeiculo() {
    }

    public static PersistirVeiculo getInstancia() {
        if ((PersistirVeiculo.INSTANCIA == null)) {
            Principal.getInstancia().log("Criando instancia de persistencia do produto...");
            PersistirVeiculo.INSTANCIA = new PersistirVeiculo();
        }
        return PersistirVeiculo.INSTANCIA;
    }

    public boolean salvar(IVeiculo veiculo) {
        if ((veiculo != null)) {
            if ((veiculo.getId() > 0)) {
                return this.atualizar(veiculo);
            }
            return this.inserir(veiculo);
        }

        Principal.getInstancia().log("Nao é possível salvar uma instancia nula...");
        return false;
    }

    private boolean inserir(IVeiculo cliente) {
        return true;
    }

    private boolean atualizar(IVeiculo cliente) {
        return true;
    }

    private IVeiculo construir(QueryResult resultado, ICliente dono, boolean fechar) {
        IVeiculo veiculo = null;
        if ((resultado != null)) {
            if ((dono != null) && (dono.getId() > 0)) {
                veiculo = new Veiculo(dono, resultado.getInt("id"), resultado.getString("placa"), resultado.getString("descricao"), resultado.getString("entrada"), resultado.getString("saida"));
            } else {
                Principal.getInstancia().log("O objeto 'dono' ainda não foi persistido no banco de dados.");
            }

            if ((fechar)) {
                resultado.close();
            }
        }

        return veiculo;
    }

    public IVeiculo recuperar(int id) {
        QueryResult resultado = Conectar.getInstancia().getJdbc().query("SELECT * FROM `veiculos` WHERE ( `id` = ? )", new Object[]{id});
        IVeiculo veiculo = null;
        if ((resultado.next())) {
            ControlarCliente controle = new ControlarCliente();
            ICliente dono = controle.recuperar(resultado.getInt("clienteID"));
            veiculo = this.construir(resultado, dono, true);
        }

        return veiculo;
    }

    public Map<Integer, IVeiculo> recuperarPeloDono(int idDono) {
        Map<Integer, IVeiculo> veiculos = new HashMap();
        if ((idDono > 0)) {
            ControlarCliente controle = new ControlarCliente();
            ICliente dono = controle.recuperar(idDono);
            veiculos = this.recuperarPeloDono(dono);
        }

        return veiculos;
    }

    public Map<Integer, IVeiculo> recuperarPeloDono(ICliente dono) {
        Map<Integer, IVeiculo> veiculos = new HashMap();
        if ((dono != null)) {
            QueryResult resultados = Conectar.getInstancia().getJdbc().query("SELECT * FROM `veiculos` WHERE ( `clienteID` = ? )", new Object[]{dono.getId()});
            while (resultados.next()) {
                IVeiculo veiculo = this.construir(resultados, dono, false);
                if ((veiculo != null)) {
                    veiculos.put(veiculo.getId(), veiculo);
                }
            }

            resultados.close();
        }

        return veiculos;
    }

}
