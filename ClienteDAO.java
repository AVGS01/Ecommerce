import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;


    /** Construtor responsável por inicializar a conexão com o banco de dados
     * @author Frederico Augusto
     * @version 1.0
     * */
    public ClienteDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    /** Método responsável por adicionar os dados dos clientes ao banco de dados
     * @author Frederico Augusto
     * @version 1.0
     * */
    public void addCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, telefone, data_cadastro) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getDataCadastro());
            stmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    /** Método responsável por obter e listar os dados dos clientes
     * @author Frederico Augusto
     * @version 1.0
     * */
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("data_cadastro")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    /** Método responsável por selecionar o cliente pelo ID -
     * @author Gabriel Viana
     * @version 1.0
     * */
    public Cliente getClienteById(int clienteId) {
        String sql = "SELECT * FROM cliente WHERE cliente_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("data_cadastro")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    /** Método responsável por atualizar os dados dos clientes
     * @author Frederico Augusto
     * @version 1.0
     * */
    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ?, data_cadastro = ? WHERE cliente_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getDataCadastro());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    /** Método responsável por deletar os dados do cliente pelo ID
     * @author Frederico Augusto
     * @version 1.0
     * */
    public void deleteCliente(int clienteId) {
        String sql = "DELETE FROM cliente WHERE cliente_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.executeUpdate();
            System.out.println("Cliente deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
