package System.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";

    private static final String USUARIO = "root";

    // Em vez de colocar a senha direto, mandamos o Java buscar do ambiente
    private static final String SENHA = System.getenv("DB_PASSWORD");

    public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Não foi possível Conectar ao bando de dados. ");
        }
    }

}
