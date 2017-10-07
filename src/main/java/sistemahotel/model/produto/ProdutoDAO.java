package sistemahotel.model.produto;

import sistemahotel.model.infraestrutura.Persistencia;

/**
 * Created by Tatsunori on 05/10/2017.
 */
public class ProdutoDAO {
    Persistencia persistencia = Persistencia.getInstancia();
    Produto produto;

    private static ProdutoDAO instancia = null;

    private ProdutoDAO(){
    }
    public static ProdutoDAO getInstancia(){
        if(instancia == null){
            instancia = new ProdutoDAO();
        }
        return instancia;
    }

    public void NovoProduto(String nome,String quantidade,String preco,String alertaEstoque){
        produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setAlertaEstoque(alertaEstoque);
        persistencia.persistir(produto);
    }

    public void AlterarProduto(String nome,String quantidade,String preco,String alertaEstoque, Long id){
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setAlertaEstoque(alertaEstoque);
        produto.setId(id);
        persistencia.persistir(produto);

    }

    public void DeleterProduto(Produto produto){
        persistencia.deletar(produto);
    }

}
