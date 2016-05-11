package projetos.gerencia;

import jdbchelper.JdbcException;
import projetos.gerencia.apresentacao.ControlarFuncionario;
import projetos.gerencia.exceptions.LoginException;
import projetos.gerencia.negocio.funcionario.IFuncionario;

public final class Principal {

    private static Principal INSTANCIA;

    private IFuncionario funcionario;
    private boolean logs;

    private Principal() {
        this.setLogs(true);
    }

    public static Principal getInstancia() {
        if ((Principal.INSTANCIA == null)) {
            Principal.INSTANCIA = new Principal();
        }
        return Principal.INSTANCIA;
    }

    public void fazerLogin(String cpf, String senha) throws LoginException {
        if ((this.getFuncionario() == null)) {
            try {
                ControlarFuncionario controlar = new ControlarFuncionario();
                IFuncionario conectar = controlar.recuperar(Integer.parseInt(cpf));

                if ((conectar != null) && (conectar.getSenha().equals(senha))) {
                    if ((conectar.getTipo() <= 0)) {
                        this.log(LoginException.SEM_PERMISSAO);
                        throw new LoginException(LoginException.SEM_PERMISSAO, LoginException.SEM_PERMISSAO_ID);
                    } else {
                        this.log(new StringBuilder().append("Conectado com sucesso como: '").append(conectar.getNomeCompleto()).append("'").toString());
                        this.setFuncionario(conectar);
                    }
                } else {
                    this.log(LoginException.NAO_ENCONTRADO);
                    throw new LoginException(LoginException.NAO_ENCONTRADO, LoginException.NAO_ENCONTRADO_ID);
                }
            } catch (JdbcException error) {
                this.log(LoginException.SEM_CONEXOES);
                throw new LoginException(LoginException.SEM_CONEXOES, LoginException.SEM_CONEXOES_ID);
            } catch (NumberFormatException error) {
                this.log(LoginException.CPF_INVALIDO);
                throw new LoginException(LoginException.CPF_INVALIDO, LoginException.CPF_INVALIDO_ID);
            }
        } else {
            this.log(LoginException.SESSAO_ATIVA);
            throw new LoginException(LoginException.SESSAO_ATIVA, LoginException.SESSAO_ATIVA_ID);
        }
    }

    public boolean fazerLogout() {
        boolean sair = false;
        IFuncionario conectado = this.getFuncionario();

        if ((conectado == null)) {
            this.log("Você não está conectado.");
        } else {
            sair = true;
            this.log("Usuário desconectado... sessão finalizada.");
            this.setFuncionario(null);
        }

        return sair;
    }

    public void log(String mensagem) {
        this.log(mensagem, "SISTEMA");
    }

    public void log(String mensagem, String tipo) {
        if ((this.isLogs())) {
            tipo = tipo.toUpperCase();
            System.out.println(new StringBuilder().append(tipo).append(" > ").append(mensagem));
        }
    }

    public boolean isLogs() {
        return this.getLogs();
    }

    private boolean getLogs() {
        return this.logs;
    }

    private void setLogs(boolean logs) {
        this.logs = logs;
    }

    public IFuncionario getFuncionario() {
        return this.funcionario;
    }

    private void setFuncionario(IFuncionario funcionario) {
        this.funcionario = funcionario;
    }

}
