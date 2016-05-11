package projetos.gerencia.apresentacao;

import projetos.gerencia.negocio.funcionario.IFuncionario;
import projetos.gerencia.persistencia.funcionario.PersistirFuncionario;

public class ControlarFuncionario {

    public IFuncionario recuperar(int cpf) {
        return PersistirFuncionario.getInstancia().recuperar(cpf);
    }

}
