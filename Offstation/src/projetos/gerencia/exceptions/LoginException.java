package projetos.gerencia.exceptions;

public class LoginException extends Exception {
    
    public static final String SEM_PERMISSAO = "Você não tem permissão suficiente.";
    public static final int SEM_PERMISSAO_ID = 1;
    
    public static final String NAO_ENCONTRADO = "Nenhum usuário encontrado com as informações inseridas.";
    public static final int NAO_ENCONTRADO_ID = 2;
    
    public static final String SEM_CONEXOES = "Não foi possível conectar ao banco de dados.";
    public static final int SEM_CONEXOES_ID = 3;
    
    public static final String CPF_INVALIDO = "CPF digitado de forma incorreta! Use apenas os números.";
    public static final int CPF_INVALIDO_ID = 4;
    
    public static final String SESSAO_ATIVA = "Oops... Você já está conectado.";
    public static final int SESSAO_ATIVA_ID = 5;
    
    private int tipo;
    
    public LoginException(String mensagem, int tipo) {
        super(mensagem);
        this.setTipo(tipo);
    }

    public int getTipo() {
        return this.tipo;
    }

    private void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
