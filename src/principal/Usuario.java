package principal;

public class Usuario {
	
	private String usuario;
	private String senha;
	private NivelDeAcesso nivelAcesso;
	
	public Usuario(String usuario, String senha, NivelDeAcesso nivelAcesso) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
	}

	public Usuario(NivelDeAcesso nivelAcesso) {
		this.usuario = "visitante";
		this.nivelAcesso = nivelAcesso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public NivelDeAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelDeAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	public boolean podeAcessarRelatorio() {
		return nivelAcesso.podeAcessarRelatorio();
	}

	public boolean podeAdicionarProduto() {
		return nivelAcesso.podeAdicionarProduto();
	}

	public boolean podeBuscarProduto() {
		return nivelAcesso.podeBuscarProduto();
	}

	public boolean podeRemoverProduto() {
		return nivelAcesso.podeRemoverProduto();
	} 
	
	
}
