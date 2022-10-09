package trabalho1.com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecebeCombustivelTest {
    CentroDistribuicao c = null;

    @BeforeEach
    void setup() {
        c = new CentroDistribuicao(0, 0, 0, 0);
    }

    @Test
    void adicionaAditivo() {
        final int qtdade = c.recebeAditivo(250);
        final int resultadoEsperado = 250;
        Assertions.assertEquals(qtdade, resultadoEsperado);
    }

    @Test
    void adicionaNegativoAditivo() {
        final int qtdadeNegativa = c.recebeAditivo(-5);
        final int resultadoEsperado = -1;
        Assertions.assertEquals(qtdadeNegativa, resultadoEsperado);
    }

    @Test
    void adicionaMaiorQLimiteAditivo() {
        final int qtdade = c.recebeAditivo(501);
        final int resultadoEsperado = CentroDistribuicao.MAX_ADITIVO;
        Assertions.assertEquals(qtdade, resultadoEsperado);
    }

    @Test
    void adicionaGasolina() {
        final int qtdade = c.recebeGasolina(6500);
        final int resultadoEsperado = 6500;
        Assertions.assertEquals(qtdade, resultadoEsperado);
    }

    @Test
    void adicionaNegativoGasolina() {
        final int qtdadeNegativa = c.recebeGasolina(-5);
        final int resultadoEsperado = -1;
        Assertions.assertEquals(qtdadeNegativa, resultadoEsperado);
    }

    @Test
    void adicionaMaiorQLimiteGasolina() {
        final int qtdade = c.recebeGasolina(10001);
        final int resultadoEsperado = CentroDistribuicao.MAX_GASOLINA;
        Assertions.assertEquals(qtdade, resultadoEsperado);
    }

    @Test
    void adicionaAlcool() {
        final int qtdade = c.recebeAlcool(2000);
        final int resultadoEsperado = 1000;
        Assertions.assertEquals(qtdade, resultadoEsperado);
    }

    @Test
    void adicionaNegativoAlcool() {
        final int qtdadeNegativa = c.recebeAlcool(-5);
        final int resultadoEsperado = -1;
        Assertions.assertEquals(qtdadeNegativa, resultadoEsperado);
    }

    @Test
    void adicionaMaiorQLimiteAlcool() {
        final int qtdade = c.recebeAlcool(2501);
        final int resultadoEsperado = CentroDistribuicao.MAX_ALCOOL / 2;
        Assertions.assertEquals(qtdade, resultadoEsperado);
    }
}
