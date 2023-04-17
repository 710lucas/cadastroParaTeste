
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CadastroTest {
    private static final int NUM_TESTES = 10;
    Cadastro cadastro;

    @BeforeEach
    void setUp() throws Exception {
        cadastro = new Cadastro();
    }

    @Test
    void testAdiciona() {
        assertEquals(0, cadastro.getNumeroDeItens());
        for (int i = 0; i < NUM_TESTES; i++) {
            assertEquals(i, cadastro.getNumeroDeItens());
            assertTrue(cadastro.adiciona("Item" + i));
            assertEquals(i + 1, cadastro.getNumeroDeItens());
        }
        // nao aceita null como item do cadastro
        assertFalse(cadastro.adiciona(null));

        // adiciona item repetido
        assertTrue(cadastro.adiciona("Item0"));
        assertEquals(11, cadastro.getNumeroDeItens());
    }

    @Test
    void testRemoveString() {
        assertEquals(0, cadastro.getNumeroDeItens());
        assertFalse(cadastro.remove("Item0"));
        assertEquals(0, cadastro.getNumeroDeItens());

        povoaCadastro();

        int qtdeDeItens = 10;
        for (int i = 1; i < NUM_TESTES; i += 2) {
            assertEquals(qtdeDeItens, cadastro.getNumeroDeItens());
            assertTrue(cadastro.remove("Item" + i));
            assertEquals(--qtdeDeItens, cadastro.getNumeroDeItens());
        }

        assertTrue(cadastro.remove("Item0"));
        assertEquals(--qtdeDeItens, cadastro.getNumeroDeItens());

        // adiciona e remove elementos iguais
        assertTrue(cadastro.adiciona("Item0"));
        assertTrue(cadastro.adiciona("Item0"));
        assertTrue(cadastro.remove("Item0"));
        assertTrue(cadastro.remove("Item0"));

        assertEquals(qtdeDeItens, cadastro.getNumeroDeItens());

    }

    private void povoaCadastro() {
        for (int i = 0; i < NUM_TESTES; i++) {
            cadastro.adiciona("Item" + i);

        }
    }

    @Test
    void testRemoveInt() {
        povoaCadastro();

        int qtdeDeItens = 10;
        for (int i = 0; i < NUM_TESTES / 2; i += 2) {
            assertEquals(qtdeDeItens, cadastro.getNumeroDeItens());
            cadastro.remove(i);
            assertEquals(--qtdeDeItens, cadastro.getNumeroDeItens());
        }

        cadastro.remove(qtdeDeItens - 1);
        assertEquals(--qtdeDeItens, cadastro.getNumeroDeItens());

        try { // nem precisava fazer isso, pq foi testado em ArrayList
            // só pra relembrar que existem exceções que podem ser lançadas
            // pelos métodos e algumas são silenciosas, nao precisamos
            // indicar com throws (falaremos disso mais adiante no curso)
            cadastro.remove(NUM_TESTES);
            fail("Deveria lançar IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException iobe) {
            assertEquals(qtdeDeItens, cadastro.getNumeroDeItens());
        }

    }

    @Test
    void testContem() {
        assertFalse(cadastro.contem(null));
        povoaCadastro();

        for (int i = 0; i < NUM_TESTES; i++) {
            assertTrue(cadastro.contem("Item" + i));
        }
    }

    @Test
    void testRecupera() {

        povoaCadastro();

        try { // nem precisava fazer isso, pq foi testado em ArrayList
            // só pra relembrar que existem exceções que podem ser lançadas
            // pelos métodos e algumas são silenciosas, nao precisamos
            // indicar com throws (falaremos disso mais adiante no curso)
            cadastro.recupera(NUM_TESTES);
            fail("Deveria lançar IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException iobe) {
            assertEquals(10, cadastro.getNumeroDeItens());
        }
        for (int i = 0; i < NUM_TESTES; i++) {
            assertEquals("Item" + i, cadastro.recupera(i));
        }
    }

    @Test
    void testRecuperaIndice() {
        assertEquals(-1, cadastro.recuperaIndice("qualquer coisa"));
        povoaCadastro();
        for (int i = 0; i < NUM_TESTES; i++) {
            assertEquals(i, cadastro.recuperaIndice("Item" + i));
        }
    }

    private void povoaAleatorio() {
        // povoa de 1 a 9 - só impares
        for (int i = 1; i < NUM_TESTES; i += 2) {
            cadastro.adiciona("Item" + i);
        }

        // povoa de 0 a 8 - só pares
        for (int i = 0; i < NUM_TESTES; i += 2) {
            cadastro.adiciona("Item" + i);
        }

        // podemos sempre usar prints nos testes pra entender o que está acontecendo
        // se for necessario. vai rodar como um main e mostrar na console da IDE
        // pra gente
        // System.out.println(cadastro);
    }

    @Test
    void testToString() {
        assertEquals("", cadastro.toString());
        String resultado = "";
        for (int i = 0; i < NUM_TESTES; i++) {
            cadastro.adiciona("Item" + i);
            resultado += ("Item" + i);
            resultado += "\n";
            assertEquals(resultado, cadastro.toString());
        }
    }

//	****IMPORTANTE****
//	Só descomente o código abaixo depois que todos os testes anteriores
//	estiverem passando com barra verde

	@Test
	void testOrdena() {
		povoaAleatorio();
		for (int i = 0; i < NUM_TESTES; i++) {
			assertNotEquals(i, cadastro.recuperaIndice("Item" + i));
		}
		cadastro.ordena();
		for (int i = 0; i < NUM_TESTES; i++) {
			assertEquals(i, cadastro.recuperaIndice("Item" + i));
		}
	}

}