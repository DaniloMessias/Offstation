package projetos.gerencia.negocio.veiculo;

import projetos.gerencia.negocio.cliente.ICliente;
import projetos.gerencia.persistencia.veiculo.PersistirOrcamento;

public final class Veiculo implements IVeiculo {

    private ICliente dono;
    private int id;
    private String placa;
    private String descricao;
    private String entrada;
    private String saida;
    private PersistirOrcamento persistencia;

    public Veiculo(ICliente dono, int id, String placa, String descricao, String entrada, String saida) {
        this.setDono(dono);
        this.setId(id);
        this.setPlaca(placa);
        this.setDescricao(descricao);
        this.setEntrada(entrada);
        this.setSaida(saida);
    }

    @Override
    public ICliente getDono() {
        return this.dono;
    }

    private void setDono(ICliente dono) {
        this.dono = dono;
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPlaca() {
        return this.placa;
    }

    private void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getEntrada() {
        return this.entrada;
    }

    private void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    @Override
    public String getSaida() {
        return this.saida;
    }

    private void setSaida(String saida) {
        this.saida = saida;
    }

    private PersistirOrcamento getPersistencia() {
        return this.persistencia;
    }

    private void setPersistencia(PersistirOrcamento persistencia) {
        this.persistencia = persistencia;
    }

}
