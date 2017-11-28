package sistemahotel.model.local;

import sistemahotel.model.infraestrutura.Persistencia;

public class LocalDAO {

    Persistencia persist = Persistencia.getInstancia();






    public void NovaHabitacao(String numero, String camasSolteiro, String camasCasal, String preco, String infoAdicionais){
        Habitacao habitacao = new Habitacao();
        habitacao.setNumero(numero);
        habitacao.setCamasDeSolteiro(camasSolteiro);
        habitacao.setCamasDeCasal(camasCasal);
        habitacao.setPreco(preco);
        habitacao.setInformacoesAdicionais(infoAdicionais);
        persist.persistir(habitacao);
    }

    public void NovoSalao(String numero, String maxPessoas, String infoAdicionais, String preco){
        SalaoFestas salao = new SalaoFestas();
        salao.setNumero(numero);
        salao.setMaximoPessoas(maxPessoas);
        salao.setInformacoesAdicionais(infoAdicionais);
        salao.setPreco(preco);
        persist.persistir(salao);
    }

    public Local AlterarHabitacao(Habitacao habitacao, String numero, String preco, String info, String camasSolteiro, String camasCasal){
        habitacao.setNumero(numero);
        habitacao.setPreco(preco);
        habitacao.setInformacoesAdicionais(info);
        habitacao.setCamasDeCasal(camasCasal);
        habitacao.setCamasDeSolteiro(camasSolteiro);
        persist.alterar(habitacao);
        return habitacao;
    }

    public Local AlterarSalao(SalaoFestas salao, String numero, String preco, String info, String maxPessoas){
        salao.setNumero(numero);
        salao.setPreco(preco);
        salao.setInformacoesAdicionais(info);
        salao.setMaximoPessoas(maxPessoas);
        persist.alterar(salao);
        return salao;
    }

    public void DeletarLocal(Local local){
        local.ativo = false;
        persist.alterar(local);
    }

}
