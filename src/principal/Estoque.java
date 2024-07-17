package principal;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	
	private List<Produto> estoque;

	public Estoque() {
		this.estoque = new ArrayList<>();
	}
	
	public void adicionarProduto(Produto produto, Usuario usuario) {
		if (!usuario.podeAdicionarProduto()) {
			throw new RuntimeException("Usuario nao tem acesso!");
		}
		if (!produtoExiste(produto)) {
			estoque.add(produto);
		} else {
			int posicao = buscarPosicao(produto);
			Produto novo = estoque.get(posicao);
			novo.adicionarProduto(produto.getQuantidade());
		}
	}

	private int buscarPosicao(Produto produto) {
		int pos = -1;
		for(int i = 0; i < estoque.size(); i++) {
			if (estoque.get(i).equals(produto)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	private boolean produtoExiste(Produto produto) {
		return estoque.contains(produto);
	}
	
	public Produto buscarProduto(Produto produto, Usuario usuario) {
		if (!usuario.podeBuscarProduto()) {
			throw new RuntimeException("Usuario nao tem acesso!");
		}
		Produto prod = null;
		int index = buscarPosicao(produto);
		if (index != -1) {
			prod = estoque.get(index);
		}
		return prod;
	}
	
	public void removerProduto(Produto produto, int quantidade, Usuario usuario) {
		if (!usuario.podeRemoverProduto()) {
			throw new RuntimeException("Usuario nao tem acesso!");
		}
		Produto produtoEstoque = buscarProduto(produto, usuario);
		if (produtoEstoque.getQuantidade() >= quantidade) {
			// fazer remocao
			int index = buscarPosicao(produtoEstoque);
			estoque.get(index).removerProduto(quantidade);
			if (estoque.get(index).getQuantidade() == 0) {
				estoque.remove(index);
			}
		} else {
			throw new RuntimeException("Produto nao existe!");
		}
	}
	
	public String relatorio(Produto produto, Usuario usuario) {
		if (!usuario.podeAcessarRelatorio()) {
			throw new RuntimeException("Usuario nao tem acesso!");
		}
		int index = buscarPosicao(produto);
		if (index != -1) {
			String prod = String.format("Produto: %s, Quantidade: %d, Preço: %.2f, Validade: %s", produto.getNome(), produto.getQuantidade(), produto.getPreco(), produto.getValidade().toString());
			return prod;
		} else {
			throw new RuntimeException("Produto nao Encontrado!");
		}
	}
	
	public String relatorioGeral(Usuario usuario) {
		if (!usuario.podeAcessarRelatorio()) {
			throw new RuntimeException("Usuario nao tem acesso!");
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < estoque.size(); i++) {
			String prod = String.format("Produto: %s, Quantidade: %d, Preço: %.2f, Validade: %s", estoque.get(i).getNome(), estoque.get(i).getQuantidade(), estoque.get(i).getPreco(), estoque.get(i).getValidade().toString());
			sb.append(prod);
			if (i < estoque.size()-1) {
				sb.append("; ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
}
