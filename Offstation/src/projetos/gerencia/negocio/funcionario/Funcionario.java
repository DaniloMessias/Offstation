package projetos.gerencia.negocio.funcionario;

public class Funcionario implements IFuncionario {

    private int cpf;
    private int tipo;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    public Funcionario(int cpf, int tipo, String nome, String sobrenome, String email, String senha) {
        this.setCpf(cpf);
        this.setTipo(tipo);
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
    }

    @Override
    public int getCpf() {
        return this.cpf;
    }

    private void setCpf(int cpf) {
        this.cpf = cpf;
    }

    @Override
    public int getTipo() {
        return this.tipo;
    }

    private void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getSobrenome() {
        return this.sobrenome;
    }

    private void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String getNomeCompleto() {
        String nomeCompleto = new StringBuffer().append(this.getNome()).append(" ").append(this.getSobrenome()).toString();
        return nomeCompleto;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

}
