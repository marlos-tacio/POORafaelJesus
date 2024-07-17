package principal;

import java.time.LocalDate;

public class Produto {
	
	private String nome;
	private String descricao;
	private int quantidade;
	private double preco;
	private LocalDate validade;
	
	public Produto(String nome, String descricao, int quantidade, double preco, LocalDate validade) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.validade = validade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	private void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	
	public void adicionarProduto(int quantidade) {
		if (quantidade > 0) {
			setQuantidade(getQuantidade() + quantidade);
		} else {
			throw new RuntimeException("Valor inválido!");
		}
		 
	}
	
	public void removerProduto(int quantidade) {
		if (quantidade > 0) {
			setQuantidade(getQuantidade() - quantidade);
		} else {
			throw new RuntimeException("Valor inválido!");
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof Produto) {
			Produto p = (Produto) obj;
			retorno = getNome() == p.getNome();
		}
		return retorno;
	}
	
}
