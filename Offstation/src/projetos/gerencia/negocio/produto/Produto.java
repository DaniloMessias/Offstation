package projetos.gerencia.negocio.produto;

public abstract class Produto implements IProduto {

    private int id;
    private int estoque;
    private double preco;
    private String nome;
    private String marca;

    public Produto(int id, int estoque, double preco, String nome, String marca) {
        this.setId(id);
        this.setEstoque(estoque);
        this.setPreco(preco);
        this.setNome(nome);
        this.setMarca(marca);
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public int getEstoque() {
        return this.estoque;
    }

    private void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public double getPreco() {
        return this.preco;
    }

    private void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getMarca() {
        return this.marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }
    
}
