package principal;

public enum NivelDeAcesso {
	ADMIN {

		@Override
		boolean podeAcessarRelatorio() {
			return true;
		}

		@Override
		boolean podeAdicionarProduto() {
			return true;
		}

		@Override
		boolean podeBuscarProduto() {
			return true;
		}

		@Override
		boolean podeRemoverProduto() {
			return true;
		}
		
	},
	USUARIO {

		@Override
		boolean podeAcessarRelatorio() {
			return true;
		}

		@Override
		boolean podeAdicionarProduto() {
			return true;
		}

		@Override
		boolean podeBuscarProduto() {
			return true;
		}

		@Override
		boolean podeRemoverProduto() {
			return false;
		}
		
	},
	VISITANTE {

		@Override
		boolean podeAcessarRelatorio() {
			return true;
		}

		@Override
		boolean podeAdicionarProduto() {
			return false;
		}

		@Override
		boolean podeBuscarProduto() {
			return false;
		}

		@Override
		boolean podeRemoverProduto() {
			return false;
		}
		
	};
	
	abstract boolean podeAcessarRelatorio();
	abstract boolean podeAdicionarProduto();
	abstract boolean podeBuscarProduto();
	abstract boolean podeRemoverProduto();
}
