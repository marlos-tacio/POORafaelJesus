package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import principal.*;

class ProdutoTeste {
	
	// Escrevendo o teste (red)
	@Test
	void testCadastrarProduto() {
		Produto produto = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		assertEquals("Arroz", produto.getNome());
		assertEquals("Arroz Branco", produto.getDescricao());
		assertEquals(50, produto.getQuantidade());
		assertEquals(7, produto.getPreco());
		assertEquals("2024-12-24", produto.getValidade().toString());
	}
	
	@Test
	void testAdicionarProdutoNoEstoque() {
		Usuario admin = new Usuario("admin", "1234", NivelDeAcesso.ADMIN);
		Produto arroz = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Produto feijao = new Produto("Feijao", "Feijao Preto", 70, 10, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Estoque estoque = new Estoque();
		
		estoque.adicionarProduto(arroz, admin);
		estoque.adicionarProduto(feijao, admin);
		
		assertEquals("Arroz", estoque.buscarProduto(arroz, admin).getNome());
		assertEquals("Arroz Branco", estoque.buscarProduto(arroz, admin).getDescricao());
		assertEquals(50, estoque.buscarProduto(arroz, admin).getQuantidade());
		assertEquals(7, estoque.buscarProduto(arroz, admin).getPreco());
		assertEquals("2024-12-24", estoque.buscarProduto(arroz, admin).getValidade().toString());
		
		assertEquals("Feijao", estoque.buscarProduto(feijao, admin).getNome());
		assertEquals("Feijao Preto", estoque.buscarProduto(feijao, admin).getDescricao());
		assertEquals(70, estoque.buscarProduto(feijao, admin).getQuantidade());
		assertEquals(10, estoque.buscarProduto(feijao, admin).getPreco());
		assertEquals("2024-12-24", estoque.buscarProduto(feijao, admin).getValidade().toString());
	}
	
	@Test
	void testEntradaSaidaProduto() {
		Usuario admin = new Usuario("admin", "1234", NivelDeAcesso.ADMIN);
		Produto arroz = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Produto feijao = new Produto("Feijao", "Feijao Preto", 70, 10, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		Estoque estoque = new Estoque();
		estoque.adicionarProduto(arroz, admin);
		estoque.adicionarProduto(feijao, admin);
		
		Produto novaCaixaDeArroz = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		// Entrada
		estoque.adicionarProduto(novaCaixaDeArroz, admin);
		assertEquals(100, estoque.buscarProduto(arroz, admin).getQuantidade());
		
		// Saida
		estoque.removerProduto(feijao, 20, admin);
		assertEquals(50, estoque.buscarProduto(feijao, admin).getQuantidade());
		
		// Remover completamente o produto
		estoque.removerProduto(feijao, 50, admin);
		assertNull(estoque.buscarProduto(feijao, admin));
	}
	
	@Test
	void testUsuarioAcesso() {
		Usuario admin = new Usuario("admin", "1234", NivelDeAcesso.ADMIN);
		Usuario usuario = new Usuario("user", "12345", NivelDeAcesso.USUARIO);
		Usuario visitante = new Usuario(NivelDeAcesso.VISITANTE);
		
		assertEquals(NivelDeAcesso.ADMIN, admin.getNivelAcesso());
		assertEquals(NivelDeAcesso.USUARIO, usuario.getNivelAcesso());
		assertEquals(NivelDeAcesso.VISITANTE, visitante.getNivelAcesso());
	}
	
	@Test
	void testLogin() {
		Usuario admin = new Usuario("admin", "1234", NivelDeAcesso.ADMIN);
		Usuario usuario = new Usuario("user", "12345", NivelDeAcesso.USUARIO);
		Usuario visitante = new Usuario(NivelDeAcesso.VISITANTE);
		
		Estoque estoque = new Estoque();
		Produto arroz = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Produto feijao = new Produto("Feijao", "Feijao Preto", 70, 10, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		// Verificar acesso do adicionaProduto
		// Visitante
		Exception exception = assertThrows(RuntimeException.class, () -> {
			estoque.adicionarProduto(feijao, visitante);
		});
		
		String msgEsperada = "Usuario nao tem acesso!";
		String msg = exception.getMessage();
		assertTrue(msg.contains(msgEsperada));
		
		estoque.adicionarProduto(feijao, admin);
		estoque.adicionarProduto(arroz, admin);
		
		// Verificar acesso do BuscaProduto
		// Visitante
		exception = assertThrows(RuntimeException.class, () -> {
			estoque.buscarProduto(feijao, visitante);
		});
		
		msg = exception.getMessage();
		assertTrue(msg.contains(msgEsperada));
		
		// Verificar acesso do removerProduto
		// Visitante
		exception = assertThrows(RuntimeException.class, () -> {
			estoque.removerProduto(feijao, 10, visitante);
		});
		
		msg = exception.getMessage();
		assertTrue(msg.contains(msgEsperada));
		// Usuario
		exception = assertThrows(RuntimeException.class, () -> {
			estoque.removerProduto(feijao, 10, usuario);
		});
		
		msg = exception.getMessage();
		assertTrue(msg.contains(msgEsperada));
	}
	
	@Test
	void testRelatorioProduto() {
		Usuario admin = new Usuario("admin", "1234", NivelDeAcesso.ADMIN);
		Produto arroz = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Produto feijao = new Produto("Feijao", "Feijao Preto", 70, 10, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Estoque estoque = new Estoque();
		
		estoque.adicionarProduto(feijao, admin);
		estoque.adicionarProduto(arroz, admin);
		
		String res = estoque.relatorio(feijao, admin);
		String result = estoque.relatorio(arroz, admin);
		
		assertEquals("Produto: Feijao, Quantidade: 70, Preço: 10,00, Validade: 2024-12-24", res);
		assertEquals("Produto: Arroz, Quantidade: 50, Preço: 7,00, Validade: 2024-12-24", result);
	}
	
	@Test
	void testRelatorioGeral() {
		Usuario admin = new Usuario("admin", "1234", NivelDeAcesso.ADMIN);
		Produto arroz = new Produto("Arroz", "Arroz Branco", 50, 7, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Produto feijao = new Produto("Feijao", "Feijao Preto", 70, 10, LocalDate.parse("24/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Estoque estoque = new Estoque();
		
		estoque.adicionarProduto(feijao, admin);
		estoque.adicionarProduto(arroz, admin);
		
		String resultado = estoque.relatorioGeral(admin);
		assertEquals("[Produto: Feijao, Quantidade: 70, Preço: 10,00, Validade: 2024-12-24; Produto: Arroz, Quantidade: 50, Preço: 7,00, Validade: 2024-12-24]", resultado);
		
	}

}
