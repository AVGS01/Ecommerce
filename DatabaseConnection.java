import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Ecomerce";
    private static final String USER = "root";
    private static final String PASSWORD = "@Av326800";

    /** Método que visa estabelecer conexão com banco de dados
     * @author Gabriel Viana
     * @since 1.0
     * */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            /** Classe responsável por registrar o driver JDBC
             * @author Gabriel Viana
             * @since 1.0
             */
            Class.forName("com.mysql.cj.jdbc.Driver");
            /** Estabelece a conexão
             * @author Gabriel Viana
             * @since 1.0
             * */
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        }
        return connection;
    }
}
