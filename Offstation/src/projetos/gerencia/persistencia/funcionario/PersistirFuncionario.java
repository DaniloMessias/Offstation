package projetos.gerencia.persistencia.funcionario;

import jdbchelper.QueryResult;
import projetos.gerencia.Principal;
import projetos.gerencia.negocio.funcionario.Atendente;
import projetos.gerencia.negocio.funcionario.Funcionario;
import projetos.gerencia.negocio.funcionario.Gerente;
import projetos.gerencia.negocio.funcionario.IFuncionario;
import projetos.gerencia.negocio.funcionario.Mecanico;
import projetos.gerencia.persistencia.Conectar;

public class PersistirFuncionario {
    
    private static PersistirFuncionario INSTANCIA = null;
    
    private PersistirFuncionario() {
    }

    public static PersistirFuncionario getInstancia() {
        if ((PersistirFuncionario.INSTANCIA == null)) {
            Principal.getInstancia().log("Criando instancia de persistencia do funcionario...");
            PersistirFuncionario.INSTANCIA = new PersistirFuncionario();
        }
        return PersistirFuncionario.INSTANCIA;
    }
    
    public boolean salvar(IFuncionario funcionario) {
        if ((funcionario != null)) {
            
        }
        return false;
    }

    private boolean inserir(IFuncionario cliente) {
        return false;
    }

    private boolean atualizar(IFuncionario cliente) {
        return false;
    }

    private IFuncionario construir(QueryResult resultado) {
        IFuncionario funcionario = null;
        if ((resultado != null)) {
            if ((resultado.getInt("tipo") == 1)) {
                funcionario = new Gerente(resultado.getInt("cpf"), resultado.getInt("tipo"), resultado.getString("nome"), resultado.getString("sobrenome"), resultado.getString("email"), resultado.getString("senha"));
            } else if ((resultado.getInt("tipo") == 2)) {
                funcionario = new Atendente(resultado.getInt("cpf"), resultado.getInt("tipo"), resultado.getString("nome"), resultado.getString("sobrenome"), resultado.getString("email"), resultado.getString("senha"));
            } else if ((resultado.getInt("tipo") == 3)) {
                funcionario = new Mecanico(resultado.getInt("cpf"), resultado.getInt("tipo"), resultado.getString("nome"), resultado.getString("sobrenome"), resultado.getString("email"), resultado.getString("senha"));
            } else {
                funcionario = new Funcionario(resultado.getInt("cpf"), resultado.getInt("tipo"), resultado.getString("nome"), resultado.getString("sobrenome"), resultado.getString("email"), resultado.getString("senha"));
            }
        }

        return funcionario;
    }
    
    public IFuncionario recuperar(int cpf) {
        QueryResult resultado = Conectar.getInstancia().getJdbc().query("SELECT * FROM `funcionarios` WHERE ( `cpf` = ? )", new Object[]{cpf});
        IFuncionario funcionario = null;
        if ((resultado.next())) {
            funcionario = this.construir(resultado);
        }
        
        resultado.close();
        return funcionario;
    }
    
}
