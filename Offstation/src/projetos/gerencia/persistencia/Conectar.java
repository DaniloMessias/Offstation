package projetos.gerencia.persistencia;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import jdbchelper.ConnectionPool;
import jdbchelper.JdbcHelper;
import jdbchelper.PooledDataSource;

public class Conectar {

    private static Conectar INSTANCIA = null;
    private JdbcHelper jdbc;

    private Conectar() {
        this.conectar("localhost", "gp", "123456", "gp_offstation", 3306);
    }

    public static Conectar getInstancia() {
        if ((Conectar.INSTANCIA == null)) {
            Conectar.INSTANCIA = new Conectar();
        }
        return Conectar.INSTANCIA;
    }

    private void conectar(String host, String usuario, String senha, String db, int porta) {
        MysqlConnectionPoolDataSource source = new MysqlConnectionPoolDataSource();
        source.setServerName(host);
        source.setUser(usuario);
        source.setPassword(senha);
        source.setDatabaseName(db);
        source.setPort(porta);

        ConnectionPool pool = new ConnectionPool(source, 10);
        this.setJdbc(new JdbcHelper(new PooledDataSource(pool)));
    }

    public JdbcHelper getJdbc() {
        return this.jdbc;
    }

    private void setJdbc(JdbcHelper jdbc) {
        this.jdbc = jdbc;
    }

}
