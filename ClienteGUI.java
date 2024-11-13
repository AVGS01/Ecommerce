import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ClienteGUI extends JFrame {

    private ClienteDAO clienteDAO;
    private JTextField txtId, txtNome, txtEmail, txtTelefone, txtDataCadastro;
    private JTable table;
    private DefaultTableModel tableModel;

    public ClienteGUI() {
        clienteDAO = new ClienteDAO();
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciamento de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /** Painel visual do formulário
         * @author Andre
         * @version 1.0
         * */
        JPanel panelForm = new JPanel(new GridLayout(5, 2));
        panelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);

        panelForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelForm.add(txtEmail);

        panelForm.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        panelForm.add(txtTelefone);

        panelForm.add(new JLabel("Data de Cadastro (AAAA-MM-DD):"));
        txtDataCadastro = new JTextField();
        panelForm.add(txtDataCadastro);

        add(panelForm, BorderLayout.NORTH);

        /** Painel de Botões
         * @author Gabriel Viana
         * @version 1.0
         * */
        JPanel panelButtons = new JPanel();
        JButton btnCreate = new JButton("Cadastrar");
        JButton btnRead = new JButton("Listar Todos");
        JButton btnUpdate = new JButton("Atualizar");
        JButton btnDelete = new JButton("Excluir");

        panelButtons.add(btnCreate);
        panelButtons.add(btnRead);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);

        add(panelButtons, BorderLayout.CENTER);

        /** Tabela para exibir dados
         * @author Frederico Augusto
         * @version 1.0
         * */
        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Email", "Telefone", "Data Cadastro"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        /** Ações dos Botões
         * @author Andre
         * @version 1.0
         * */
        btnCreate.addActionListener(e -> createCliente());
        btnRead.addActionListener(e -> readClientes());
        btnUpdate.addActionListener(e -> updateCliente());
        btnDelete.addActionListener(e -> deleteCliente());
    }

    private void createCliente() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();
        String dataCadastro = txtDataCadastro.getText();

        /** Cria o objeto Cliente e insere no banco de dados
         * @author Gabriel Viana
         * @version 1.0
         * */
        Cliente cliente = new Cliente(0, nome, email, telefone, dataCadastro);
        clienteDAO.addCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

        clearFields();
    }

    private void readClientes() {
        /** Limpa a tabela antes de listar todos os clientes
         * @author Andre
         * @version 1.0
         * */
        tableModel.setRowCount(0);

        List<Cliente> clientes = clienteDAO.getAllClientes();

        /** Adiciona os dados dos clientes à tabela
         * @author Gabriel Viana
         * @version 1.0
         * */
        for (Cliente cliente : clientes) {
            Object[] rowData = {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getTelefone(),
                    cliente.getDataCadastro()
            };
            tableModel.addRow(rowData);
        }
    }

    private void updateCliente() {
        int clienteId;
        try {
            clienteId = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido!");
            return;
        }

        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();
        String dataCadastro = txtDataCadastro.getText();

        Cliente cliente = new Cliente(clienteId, nome, email, telefone, dataCadastro);
        clienteDAO.updateCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");

        clearFields();
        readClientes(); /** Atualiza a lista após atualização @author Andre*/
    }

    private void deleteCliente() {
        int clienteId;
        try {
            clienteId = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido!");
            return;
        }

        clienteDAO.deleteCliente(clienteId);
        JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");

        clearFields();
        readClientes(); /** Atualiza a lista após exclusão @author Andre*/
    }

    private void clearFields() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtDataCadastro.setText("");
    }
}
