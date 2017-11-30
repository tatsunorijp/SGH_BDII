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
                     String nacionalidade, String placaDoCarro, String telefone,
                     String informacoesAdicionais, String cpf, String rg, LocalDate dataDeNascimento,
                     String bairro, String CEP, String estado) {
        cliente = new Cliente();
        cliente.tipo = "Cliente";
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCidade(cidade);
        cliente.setNacionalidade(nacionalidade);
        cliente.setPlacaDoCarro(placaDoCarro);
        cliente.setInformacoesAdicionais(informacoesAdicionais);
        cliente.setCPF(cpf);
        cliente.setRG(rg);
        cliente.setDataDeNascimento(dataDeNascimento);
        cliente.setBairro(bairro);
        cliente.setCEP(CEP);
        cliente.setEstado(estado);
        persistencia.persistir(cliente);
    }

    public Cliente Alterar(String nome, String endereco, String email, String cidade,
                           String nacionalidade, String placaDoCarro, String telefone,
                           String informacoesAdicionais, String cpf, String rg, LocalDate dataDeNascimento,
                           String bairro, String CEP, String estado, Long id) {
        cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setCidade(cidade);
        cliente.setNacionalidade(nacionalidade);
        cliente.setPlacaDoCarro(placaDoCarro);
        cliente.setTelefone(telefone);
        cliente.setInformacoesAdicionais(informacoesAdicionais);
        cliente.setCPF(cpf);
        cliente.setRG(rg);
        cliente.setDataDeNascimento(dataDeNascimento);
        cliente.setBairro(bairro);
        cliente.setCEP(CEP);
        cliente.setEstado(estado);
        cliente.setId(id);
        persistencia.alterar(cliente);
        return cliente;
    }

    public void Deletar(Cliente cliente) {
        cliente.setAtivo(false);
        persistencia.alterar(cliente);
    }

    public static boolean verificaCPF(String strCpf) {
        int     d1 = 0, d2 = 0;
        int     digito1 = 0, digito2 = 0, resto = 0;
        int     digitoCPF;
        String  nDigResult;

        for (int nCount = 1; nCount < strCpf.length() -1; nCount++) {
            digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();
            d1 = d1 + ( 11 - nCount ) * digitoCPF;
            d2 = d2 + ( 12 - nCount ) * digitoCPF;
        };
        resto = (d1 % 11);
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;
        d2 += 2 * digito1;
        resto = (d2 % 11);
        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;
        String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        return nDigVerific.equals(nDigResult);
    }

}
