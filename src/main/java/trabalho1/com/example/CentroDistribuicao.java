package trabalho1.com.example;

public class CentroDistribuicao {

    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private SITUACAO situacao;
    private int tGasolina;
    private int tAditivo;
    private int tAlcool1;
    private int tAlcool2;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        tGasolina = tGasolina < 0 ? 0 : tGasolina;
        tAditivo = tAditivo < 0 ? 0 : tAditivo;
        tAlcool1 = tAlcool1 < 0 ? 0 : tAlcool1;
        tAlcool2 = tAlcool2 < 0 ? 0 : tAlcool2;

        this.tGasolina = tGasolina > MAX_GASOLINA ? MAX_GASOLINA : tGasolina;
        this.tAditivo = tAditivo > MAX_ADITIVO ? MAX_ADITIVO : tAditivo;
        this.tAlcool1 = tAlcool1 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2 : tAlcool1;
        this.tAlcool2 = tAlcool2 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2 : tAlcool2;

    }

    public void defineSituacao() {
        int tGasolinaAtual = (MAX_GASOLINA - this.tGasolina) / MAX_GASOLINA;
        int tGasolinaPorcentagem = 100 - (tGasolinaAtual * 100);
        int tAlcoolAtual = (MAX_ALCOOL - this.tAlcool1) / MAX_ALCOOL;
        int tAlcoolPorcentagem = 100 - (tAlcoolAtual * 100);
        int tAditivoAtual = (MAX_ADITIVO - this.tAditivo) / MAX_ADITIVO;
        int tAditivoPorcentagem = 100 - (tAditivoAtual * 100); 
        
        if(tGasolinaPorcentagem >= 50 && tAlcoolPorcentagem >= 50 && tAditivoPorcentagem >= 50) {
            this.situacao = SITUACAO.NORMAL;
        } else if(tGasolinaPorcentagem >= 25 && tAlcoolPorcentagem >= 25 && tAditivoPorcentagem >= 25) {
            this.situacao = SITUACAO.SOBRAVISO;
        } else {
            this.situacao =  SITUACAO.EMERGENCIA;
        }
    }

    public SITUACAO getSituacao() {
        return this.situacao;
    }

    public int gettGasolina() {
        return tGasolina;
    }

    public int gettAditivo() {
        return tAditivo;
    }

    public int gettAlcool1() {
        return tAlcool1;
    }

    public int gettAlcool2() {
        return tAlcool2;
    }

    public int recebeAditivo(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int totalAditivo = tAditivo + qtdade;
        totalAditivo = totalAditivo > MAX_ADITIVO ? MAX_ADITIVO : totalAditivo;

        return totalAditivo;
    }

    public int recebeGasolina(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int totalGasolina = tGasolina + qtdade;
        totalGasolina = totalGasolina > MAX_GASOLINA ? MAX_GASOLINA : totalGasolina;

        return totalGasolina;
    }

    public int recebeAlcool(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int totalAlcool = tAlcool1 + tAlcool2 + qtdade;
        totalAlcool = totalAlcool > MAX_ALCOOL ? MAX_ALCOOL : totalAlcool;

        return totalAlcool;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        return null;
    }
}
