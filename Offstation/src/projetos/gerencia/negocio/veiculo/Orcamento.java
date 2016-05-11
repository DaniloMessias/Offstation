package projetos.gerencia.negocio.veiculo;

import projetos.gerencia.negocio.produto.IProduto;

public class Orcamento implements IOrcamento {

    private IVeiculo veiculo;
    private IProduto produto;
    private int id;
    private int quantidade;
    private String data;

    public Orcamento(IVeiculo veiculo, IProduto produto, int id, int quantidade, String data) {
        this.setVeiculo(veiculo);
        this.setProduto(produto);
        this.setId(id);
        this.setQuantidade(quantidade);
        this.setData(data);
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public IVeiculo getVeiculo() {
        return this.veiculo;
    }

    private void setVeiculo(IVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public IProduto getProduto() {
        return this.produto;
    }

    private void setProduto(IProduto produto) {
        this.produto = produto;
    }

    @Override
    public int getQuantidade() {
        return this.quantidade;
    }

    private void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public double getValor() {
        return (this.getQuantidade() * this.getProduto().getPreco());
    }

    @Override
    public String getData() {
        return this.data;
    }

    private void setData(String data) {
        this.data = data;
    }

}
