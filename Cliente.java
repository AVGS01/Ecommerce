public class Cliente {
    private int id; // ID do cliente
    private String nome; // Nome do cliente
    private String email; // Email do cliente
    private String telefone; // Telefone do cliente
    private String dataCadastro; // Data de cadastro

    // Construtor - Resposável por inicializar os objetos - André
    public Cliente(int id, String nome, String email, String telefone, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    // Métodos getters - Obtém o valor da variável - André
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    // Métodos setters - Altera valor da variável - André
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
