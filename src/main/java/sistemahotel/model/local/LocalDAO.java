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

    public void AlterarHabitacao(String preco, String info, String camasSolteiro, String camasCasal){

    }

    public void AlterarSalao(String preco, String info, String maxPessoas){

    }

    public void DeletarLocal(Local local){
        persist.deletar(local);
    }

}
