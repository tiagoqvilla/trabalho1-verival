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
        if(tAlcool1 > tAlcool2) {
            this.tAlcool1 = tAlcool2 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2 : tAlcool2;
            this.tAlcool2 = tAlcool2 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2 : tAlcool2;  
        } else {
            this.tAlcool1 = tAlcool1 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2 : tAlcool1;
            this.tAlcool2 = tAlcool1 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2 : tAlcool1;
        }

        defineSituacao();
    }

    public void defineSituacao() {
        int situacao = getSituacaoTanques()[0];
        for (int i : getSituacaoTanques()) {
            situacao = i > situacao ? i : situacao;
        }
        this.situacao = SITUACAO.values()[situacao];
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
        this.tAditivo = totalAditivo > MAX_ADITIVO ? MAX_ADITIVO : totalAditivo;

        return tAditivo;
    }

    public int recebeGasolina(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int totalGasolina = tGasolina + qtdade;
        this.tGasolina = totalGasolina > MAX_GASOLINA ? MAX_GASOLINA : totalGasolina;

        return this.tGasolina;
    }

    public int recebeAlcool(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int totalAlcool = tAlcool1 + tAlcool2 + qtdade;
        this.tAlcool1 = totalAlcool / 2 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2: totalAlcool / 2;
        this.tAlcool2 = totalAlcool / 2 > MAX_ALCOOL / 2 ? MAX_ALCOOL / 2: totalAlcool / 2;
        return tAlcool1;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        
        if(qtdade <= 0) {
            return new int[] { -7, 0, 0, 0, 0 };
        }

        if(getSituacao() == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.COMUM) {
            return new int[] { -14, 0, 0, 0, 0 };
        }


        int qtAditivo = (int) (qtdade * 0.05);
        int qtGasolina = (int) (qtdade * 0.7);
        int qtAlcool = (int) (qtdade * 0.25);
        
        if(!canRemoveAditivo(qtAditivo)) {
            return new int[] { -21, 0, 0, 0, 0};
        }

        if(!canRemoveGasolina(qtGasolina)) {
            return new int[] { -21, 0, 0, 0, 0};
        }

        if(!canRemoveAlcool(qtAlcool)) {
            return new int[] { -21, 0, 0, 0, 0};
        }

        if(getSituacao() == SITUACAO.SOBRAVISO && tipoPosto == TIPOPOSTO.COMUM) {
            this.tAditivo -= qtAditivo / 2;
            this.tGasolina -= qtGasolina / 2;
            this.tAlcool1 -= qtAlcool / 4;
            this.tAlcool2 -= qtAlcool / 4;
        } else if(getSituacao() == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.ESTRATEGICO) {
            this.tAditivo -= qtAditivo / 2;
            this.tGasolina -= qtGasolina / 2;
            this.tAlcool1 -= qtAlcool / 4;
            this.tAlcool2 -= qtAlcool / 4;
        } else {
            this.tAditivo -= qtAditivo;
            this.tGasolina -= qtGasolina;
            this.tAlcool1 -= qtAlcool / 2;
            this.tAlcool2 -= qtAlcool / 2;
        }


        return new int[] { 0, tAditivo, tGasolina, tAlcool1, tAlcool2};
    }

    private int[] getSituacaoTanques() {
        return new int[]{getSituacaoCombustivel(this.tGasolina, MAX_GASOLINA), 
            getSituacaoCombustivel(this.tAlcool1, MAX_ALCOOL / 2), getSituacaoCombustivel(this.tAlcool2, MAX_ALCOOL / 2),
            getSituacaoCombustivel(this.tAditivo, MAX_ADITIVO)};
    }

    private int getSituacaoCombustivel(int combustivel, int limiteMaximo) {
        if(combustivel >= limiteMaximo * 0.5) {
            return 0;
        } else if(combustivel >= limiteMaximo * 0.25) {
            return 1;
        } else {
            return 2;
        }
    }

    private boolean canRemoveAditivo(int qtdade) {
        return this.tAditivo - qtdade >= 0 ? true : false;
    }

    private boolean canRemoveGasolina(int qtdade) {
        return this.tGasolina - qtdade >= 0 ? true : false;
    }

    private boolean canRemoveAlcool(int qtdade) {
        return (this.tAlcool1 + this.tAlcool2) - qtdade >= 0 ? true : false;
    }


    public static void main(String[] args) {
        CentroDistribuicao c = new CentroDistribuicao(249, 4999, 1200, 1200);
        System.out.println(c.getSituacao());
    }


}
