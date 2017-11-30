package sistemahotel.model.produto;

import sistemahotel.model.infraestrutura.Persistencia;


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

    public void novo(String nome, String quantidade, String preco, String alertaEstoque){
        produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setAlertaEstoque(alertaEstoque);
        persistencia.persistir(produto);
    }

    public Produto alterar(String nome, String quantidade, String preco, String alertaEstoque, Long id){
        produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setAlertaEstoque(alertaEstoque);
        produto.setId(id);
        persistencia.alterar(produto);
        return produto;
    }

    public void movimentarEstoque(String valor, Produto produto){
        int num1 = Integer.parseInt(produto.getQuantidade());
        int num2 = Integer.parseInt(valor);
        String resultado = Integer.toString(num1 + num2);
        produto.setQuantidade(resultado);
        persistencia.alterar(produto);
    }

    public void deletar(Produto produto){
        produto.setAtivo(false);
        persistencia.alterar(produto);
    }

}
