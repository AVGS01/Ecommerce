import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Interface - André
        SwingUtilities.invokeLater(() -> {
            ClienteGUI frame = new ClienteGUI();
            frame.setVisible(true);
        });

        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();

        while (true) {
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por ID");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Deletar Cliente");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    // Cadastrar Cliente
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Data de Cadastro (YYYY-MM-DD): ");
                    String dataCadastro = scanner.nextLine();

                    Cliente novoCliente = new Cliente(0, nome, email, telefone, dataCadastro);
                    clienteDAO.addCliente(novoCliente);
                    break;

                case 2:
                    // Listar Clientes
                    List<Cliente> clientes = clienteDAO.getAllClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() +
                                    ", Email: " + cliente.getEmail() + ", Telefone: " +
                                    cliente.getTelefone() + ", Data de Cadastro: " + cliente.getDataCadastro());
                        }
                    }
                    break;

                case 3:
                    // Buscar Cliente por ID
                    System.out.print("Digite o ID do cliente: ");
                    int clienteId = scanner.nextInt();
                    Cliente cliente = clienteDAO.getClienteById(clienteId);
                    if (cliente != null) {
                        System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() +
                                ", Email: " + cliente.getEmail() + ", Telefone: " +
                                cliente.getTelefone() + ", Data de Cadastro: " + cliente.getDataCadastro());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 4:
                    // Atualizar Cliente
                    System.out.print("Digite o ID do cliente a ser atualizado: ");
                    clienteId = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    Cliente clienteParaAtualizar = clienteDAO.getClienteById(clienteId);
                    if (clienteParaAtualizar != null) {
                        System.out.println("Dados atuais: ");
                        System.out.println("ID: " + clienteParaAtualizar.getId() + ", Nome: " + clienteParaAtualizar.getNome() +
                                ", Email: " + clienteParaAtualizar.getEmail() + ", Telefone: " +
                                clienteParaAtualizar.getTelefone() + ", Data de Cadastro: " + clienteParaAtualizar.getDataCadastro());

                        System.out.print("Novo Nome (deixe em branco para não alterar): ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo Email (deixe em branco para não alterar): ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Novo Telefone (deixe em branco para não alterar): ");
                        String novoTelefone = scanner.nextLine();
                        System.out.print("Nova Data de Cadastro (YYYY-MM-DD, deixe em branco para não alterar): ");
                        String novaDataCadastro = scanner.nextLine();

                        if (!novoNome.isEmpty()) {
                            clienteParaAtualizar.setNome(novoNome);
                        }
                        if (!novoEmail.isEmpty()) {
                            clienteParaAtualizar.setEmail(novoEmail);
                        }
                        if (!novoTelefone.isEmpty()) {
                            clienteParaAtualizar.setTelefone(novoTelefone);
                        }
                        if (!novaDataCadastro.isEmpty()) {
                            clienteParaAtualizar.setDataCadastro(novaDataCadastro);
                        }

                        clienteDAO.updateCliente(clienteParaAtualizar);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 5:
                    // Deletar Cliente
                    System.out.print("Digite o ID do cliente a ser deletado: ");
                    clienteId = scanner.nextInt();
                    clienteDAO.deleteCliente(clienteId);
                    break;

                case 6:
                    // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
