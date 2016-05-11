package projetos.gerencia.negocio.veiculo;

import projetos.gerencia.negocio.cliente.ICliente;

public interface IVeiculo {

    public ICliente getDono();

    public int getId();

    public String getPlaca();

    public String getDescricao();

    public String getEntrada();

    public String getSaida();

}
