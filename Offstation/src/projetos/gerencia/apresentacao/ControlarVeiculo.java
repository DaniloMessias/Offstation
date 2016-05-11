package projetos.gerencia.apresentacao;

import java.util.Map;
import projetos.gerencia.negocio.cliente.ICliente;
import projetos.gerencia.negocio.veiculo.IVeiculo;
import projetos.gerencia.negocio.veiculo.Veiculo;
import projetos.gerencia.persistencia.veiculo.PersistirVeiculo;

public class ControlarVeiculo {

    public boolean salvar(ICliente dono, int id, String placa, String descricao, String entrada, String saida) {
        return this.salvar(new Veiculo(dono, id, placa, descricao, entrada, saida));
    }

    public boolean salvar(IVeiculo veiculo) {
        return PersistirVeiculo.getInstancia().salvar(veiculo);
    }

    public IVeiculo recuperar(int id) {
        return PersistirVeiculo.getInstancia().recuperar(id);
    }

    public Map<Integer, IVeiculo> recuperarPeloDono(int idDono) {
        return PersistirVeiculo.getInstancia().recuperarPeloDono(idDono);
    }

    public Map<Integer, IVeiculo> recuperarPeloDono(ICliente dono) {
        return PersistirVeiculo.getInstancia().recuperarPeloDono(dono);
    }

}
