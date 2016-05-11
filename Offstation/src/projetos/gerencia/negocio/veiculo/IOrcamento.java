package projetos.gerencia.negocio.veiculo;

import projetos.gerencia.negocio.produto.IProduto;

public interface IOrcamento {

    public int getId();

    public IVeiculo getVeiculo();

    public IProduto getProduto();

    public int getQuantidade();

    public double getValor();

    public String getData();

}
