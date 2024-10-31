import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Ecomerce";
    private static final String USER = "root";
    private static final String PASSWORD = "@Av326800";

    //Método que visa estabelecer conexão com banco de dados - Gabriel Viana
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Registra o driver JDBC- Gabriel Viana
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão - Gabriel Viana
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
