package trabalho1.com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import trabalho1.com.example.CentroDistribuicao.SITUACAO;
import trabalho1.com.example.CentroDistribuicao.TIPOPOSTO;

public class EncomendaCombustivelTest {
    CentroDistribuicao c = null;

    @BeforeEach
    void setup() {
        c = new CentroDistribuicao(0, 0, 0, 0);
    }

    @ParameterizedTest
    @CsvSource({
        "249,4000,2500,ESTRATEGICO"
    })
    void faltaAditivo(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(5000, tipoPosto);
        final int resultadoEsperado = -21;
        final int resultadoObtido = resultado[0];
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @ParameterizedTest
    @CsvSource({
        "250,3499,2500,ESTRATEGICO"
    })
    void faltaGasolina(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(5000, tipoPosto);
        final int resultadoEsperado = -21;
        final int resultadoObtido = resultado[0];
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @ParameterizedTest
    @CsvSource({
        "250,4000,1249,ESTRATEGICO"
    })
    void faltaAlcool(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(5000, tipoPosto);
        final int resultadoEsperado = -21;
        final int resultadoObtido = resultado[0];
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @ParameterizedTest
    @CsvSource({
        "250,4000,2499,ESTRATEGICO"
    })
    void qtdadeInvalida(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(-1, tipoPosto);
        final int resultadoEsperado = -7;
        final int resultadoObtido = resultado[0];
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @ParameterizedTest
    @CsvSource({
        "350,4000,2500,ESTRATEGICO"
    })
    void encomendaValida(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(5000, tipoPosto);
        final boolean qtdAditivo = resultado[1] == 100;
        final boolean qtdGasolina = resultado[2] == 500;
        final boolean qtdAlcool = (resultado[3] + resultado[4]) == 1250;
        Assertions.assertTrue((qtdAditivo && qtdGasolina && qtdAlcool));
    }
    
    @ParameterizedTest
    @CsvSource({
        "350,4000,2500,ESTRATEGICO",
        "350,4000,2500,COMUM"
    })
    void encomendaComSituacaoSobraviso(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(5000, tipoPosto);
        final boolean qtdAditivo = tipoPosto == TIPOPOSTO.COMUM ? resultado[1] == 225 : resultado[1] == 100; 
        final boolean qtdGasolina = tipoPosto == TIPOPOSTO.COMUM ? resultado[2] == 2250 : resultado[2] == 500;
        final boolean qtdAlcool = tipoPosto == TIPOPOSTO.COMUM ? (resultado[3] + resultado[4]) == 1876 : (resultado[3] + resultado[4]) == 1250;
        Assertions.assertTrue((qtdAditivo && qtdGasolina && qtdAlcool));
    }

    @ParameterizedTest
    @CsvSource({
        "350,2400,2500,ESTRATEGICO",
    })
    void encomendaComSituacaoEmergencia(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(3000, tipoPosto);
        final boolean qtdAditivo = resultado[1] == 275; 
        final boolean qtdGasolina =resultado[2] == 1350;
        final boolean qtdAlcool = (resultado[3] + resultado[4]) == 2126;
        Assertions.assertTrue((qtdAditivo && qtdGasolina && qtdAlcool));
    }

    @ParameterizedTest
    @CsvSource({
        "350,2400,2500,COMUM",
    })
    void encomendaComSituacaoInvalida(int aditivo, int gasolina, int alcool, TIPOPOSTO tipoPosto) {
        c.recebeAditivo(aditivo);
        c.recebeGasolina(gasolina);
        c.recebeAlcool(alcool);

        final int resultado[] = c.encomendaCombustivel(3000, tipoPosto);
        final int resultadoEsperado = -14;
        final int resultadoObtido = resultado[0];
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }
}
