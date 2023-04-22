package src;

import com.sun.security.jgss.GSSUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadastroPessoaTest {
    private static final int NUM_TESTES = 10;
    CadastroPessoas cadastro;

    @BeforeEach
    void setUp() throws Exception {
        cadastro = new CadastroPessoas();
    }

    @Test
    void testeGeral() throws Exception{
        Pessoa p = new Pessoa("teste", "123.123.123-12");
        assertTrue(cadastro.adiciona(p));
        assertEquals(0, cadastro.recuperaIndice("teste", "123.123.123-12"));
        cadastro = new CadastroPessoas();
        povoaCadastro();
        assertEquals(1, cadastro.recuperaIndice("teste", "000.000.000-01"));
    }

    @Test
    void testAdiciona() throws Exception {
        assertEquals(0, cadastro.getNumeroDeItens());
        for (int i = 0; i < NUM_TESTES; i++) {
            assertEquals(i, cadastro.getNumeroDeItens());
            assertTrue(cadastro.adiciona("teste", "000.000.000-0"+i));
            assertEquals(i + 1, cadastro.getNumeroDeItens());
        }
        // nao aceita null como item do cadastro
        assertFalse(cadastro.adiciona(null, (String) null));

        // adiciona item repetido
        assertTrue(cadastro.adiciona("teste", "000.000.000-00"));
        assertEquals(11, cadastro.getNumeroDeItens());
    }

    @Test
    void testRemoveString() throws Exception {
        assertEquals(0, cadastro.getNumeroDeItens());
        assertFalse(cadastro.remove("teste", "000.000.000-00"));
        assertEquals(0, cadastro.getNumeroDeItens());

        povoaCadastro();

        int qtdeDeItens = 10;
        for (int i = 1; i < NUM_TESTES; i += 2) {
            assertEquals(qtdeDeItens, cadastro.getNumeroDeItens());
            assertTrue(cadastro.remove("teste", "000.000.000-0"+i));
            assertEquals(--qtdeDeItens, cadastro.getNumeroDeItens());
        }

        assertTrue(cadastro.remove("teste", "000.000.000-00"));
        assertEquals(--qtdeDeItens, cadastro.getNumeroDeItens());

        // adiciona e remove elementos iguais
        assertTrue(cadastro.adiciona("teste", "000.000.000-00"));
        assertTrue(cadastro.adiciona("teste", "000.000.000-00"));
        assertTrue(cadastro.remove("teste", "000.000.000-00"));
        assertTrue(cadastro.remove("teste", "000.000.000-00"));

        assertEquals(qtdeDeItens, cadastro.getNumeroDeItens());

    }

    private void povoaCadastro() throws Exception {
        for (int i = 0; i < NUM_TESTES; i++) {
            cadastro.adiciona("teste", "000.000.000-0"+i);
        }
    }

    @Test
    void testRemoveInt() throws Exception {
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
    void testContem() throws Exception {
        assertFalse(cadastro.contem(null, (String)null));
        povoaCadastro();

        for (int i = 0; i < NUM_TESTES; i++) {
            assertTrue(cadastro.contem("teste", "000.000.000-0"+i));
        }
    }

    @Test
    void testRecupera() throws Exception {

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
            //Colocando ToString ja que fica dificil de comparar objetos
            assertEquals(new Pessoa("teste", "000.000.000-0"+i).toString(), cadastro.recupera(i).toString());
        }
    }

    @Test
    void testRecuperaIndice() throws Exception {
        assertEquals(-1, cadastro.recuperaIndice("qualquerPessoa", "000.000.000-00"));
        povoaCadastro();
        for (int i = 0; i < NUM_TESTES; i++) {
            assertEquals(i, cadastro.recuperaIndice("teste", "000.000.000-0"+ i));
        }
    }

    private void povoaAleatorio() throws Exception {
        // povoa de 1 a 9 - só impares
        for (int i = 1; i < NUM_TESTES; i += 2) {
            cadastro.adiciona("teste", "000.000.000-0"+i);
        }

        // povoa de 0 a 8 - só pares
        for (int i = 0; i < NUM_TESTES; i += 2) {
            cadastro.adiciona("teste", "000.000.000-0"+i);
        }

        // podemos sempre usar prints nos testes pra entender o que está acontecendo
        // se for necessario. vai rodar como um main e mostrar na console da IDE
        // pra gente
        // System.out.println(cadastro);
    }

    @Test
    void testToString() throws Exception {
        assertEquals("", cadastro.toString());
        String resultado = "";
        for (int i = 0; i < NUM_TESTES; i++) {
            cadastro.adiciona("teste", "000.000.000-0"+i);
            resultado += ("teste " + "000.000.000-0"+i);
            resultado += "\n";
            assertEquals(resultado, cadastro.toString());
        }
    }

//	****IMPORTANTE****
//	Só descomente o código abaixo depois que todos os testes anteriores
//	estiverem passando com barra verde

    @Test
    void testOrdena() throws Exception {
        povoaAleatorio();
        for (int i = 0; i < NUM_TESTES; i++) {
            assertNotEquals(i, cadastro.recuperaIndice("teste", "000.000.000-0"+i));
        }
        cadastro.ordena();
        for (int i = 0; i < NUM_TESTES; i++) {
            assertEquals(i, cadastro.recuperaIndice("teste", "000.000.000-0"+i));
        }
    }

}