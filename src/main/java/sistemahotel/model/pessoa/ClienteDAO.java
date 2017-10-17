package sistemahotel.model.pessoa;

import sistemahotel.model.infraestrutura.Persistencia;

import java.time.LocalDate;

public class ClienteDAO {
    Persistencia persistencia = Persistencia.getInstancia();
    Cliente cliente;

    private static ClienteDAO instancia = null;

    private ClienteDAO() {}

    public static ClienteDAO getInstancia() {
        if (instancia == null){
            instancia = new ClienteDAO();
        }
        return instancia;
    }

    public void Novo(String nome, String endereco, String email, String cidade,
                     String nacionalidade, String placaDoCarro, String informacoesAdicionais,
                     String cpf, String rg, LocalDate dataDeNascimento) {
        cliente = new Cliente();
        cliente.tipo = "Cliente";
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setCidade(cidade);
        cliente.setNacionalidade(nacionalidade);
        cliente.setPlacaDoCarro(placaDoCarro);
        cliente.setInformacoesAdicionais(informacoesAdicionais);
        cliente.setCPF(cpf);
        cliente.setRG(rg);
        cliente.setDataDeNascimento(dataDeNascimento);
        persistencia.persistir(cliente);
    }

    public Cliente Alterar(String nome, String endereco, String email, String cidade,
                           String nacionalidade, String placaDoCarro, String informacoesAdicionais,
                           String cpf, String rg, LocalDate dataDeNascimento, Long id) {
        cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setCidade(cidade);
        cliente.setNacionalidade(nacionalidade);
        cliente.setPlacaDoCarro(placaDoCarro);
        cliente.setInformacoesAdicionais(informacoesAdicionais);
        cliente.setCPF(cpf);
        cliente.setRG(rg);
        cliente.setDataDeNascimento(dataDeNascimento);
        cliente.setId(id);
        persistencia.alterar(cliente);
        return cliente;
    }

    public void Deletar(Cliente cliente) {
        persistencia.deletar(cliente);
    }
}
