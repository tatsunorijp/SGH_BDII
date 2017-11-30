package sistemahotel.model.pessoa;

import javax.persistence.Entity;


@Entity
public class Cliente extends Pessoa {
    private String telefone;
    private String endereco;
    private String email;
    private String cidade;
    private String bairro;
    private String CEP;
    private String estado;
    private String nacionalidade;
    private String placaDoCarro;
    private String informacoesAdicionais;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getPlacaDoCarro() {
        return placaDoCarro;
    }

    public void setPlacaDoCarro(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getBairro() { return bairro; }

    public void setCEP(String cep) { this.CEP = cep; }

    public String getCEP() { return CEP; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getEstado() { return estado; }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }
}
