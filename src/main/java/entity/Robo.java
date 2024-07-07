package entity;

import java.util.ArrayList;
import java.util.List;

public class Robo {

    private Bloco blocoAtual;
    private List<Bloco> aoRedor;
    private List<Bloco>  historicoTotal;
    private List<Bloco> blocosBloqueados;


    public Bloco getBlocoAtual() {
        return blocoAtual;
    }

    public void setBlocoAtual(Bloco blocoAtual) {
        this.blocoAtual = blocoAtual;
    }

    public List<Bloco> getBlocosBloqueados() {
        return blocosBloqueados;
    }

    public Bloco getblocoAtual() {
        return blocoAtual;
    }

    public List<Bloco> getHistoricoTotal() {
        return historicoTotal;
    }

    public Robo() {

        historicoTotal = new ArrayList<Bloco>();
        blocosBloqueados = new ArrayList<Bloco>();
        aoRedor = new ArrayList<Bloco>();

        blocoAtual = new Bloco(1, 1, this.historicoTotal, this.blocosBloqueados);

        historicoTotal.add(blocoAtual);
    }


    public Boolean chegou(){
        if(blocoAtual.getX() == 15 &&
                blocoAtual.getY() == 15){
            return true;
        }
        return false;
    }


    public Bloco decidaPassadoComSaida(List<Bloco> blocosPassadosComSaida){

        Bloco blocoMaisPertoDestino = null;

        for (Bloco bloco : blocosPassadosComSaida){

            if(blocoMaisPertoDestino == null){
                if(bloco.getDisponivel()){
                    blocoMaisPertoDestino = bloco;
                }
            }else if(bloco.getDistanciaDestino() <= blocoMaisPertoDestino.getDistanciaDestino()){

                if(bloco.getDisponivel()) {
                    blocoMaisPertoDestino = bloco;
                }
            }
        }

        return blocoMaisPertoDestino;
    }

    public Bloco lembreDeOndeEsteve(){

        List<Bloco> blocosPassadosComSaida = new ArrayList<Bloco>();

        for (Bloco bloco : historicoTotal){

            Robo robo = new Robo();
            robo.setBlocoAtual(bloco);
            robo.getHistoricoTotal().addAll(this.historicoTotal);
            robo.getBlocosBloqueados().addAll(this.blocosBloqueados);

            robo.observeAoRedor();

            for (Bloco blocoAoRedor : robo.aoRedor){

                if(blocoAoRedor.getDisponivel()){

                    blocosPassadosComSaida.add(robo.getBlocoAtual());
                    break;
                }

            }

        }

        if(!blocosPassadosComSaida.isEmpty()){

            return decidaPassadoComSaida(blocosPassadosComSaida);
        }

        return null;
    }

    public Boolean ande(){

        observeAoRedor();

        Bloco bloco = decida();

        if(bloco == null){
            System.out.println("não a bloco disponível ao redor");
            return false;

        }

        historicoTotal.add(bloco);
        blocoAtual = bloco;
        System.out.println(blocoAtual);
        return true;
    }

    public Bloco decida(){

        Bloco blocoMaisPertoDestino = null;

        for (Bloco bloco : aoRedor){

            if(blocoMaisPertoDestino == null){
                if(bloco.getDisponivel()){
                    blocoMaisPertoDestino = bloco;
                }
                continue;
            }

            if(bloco.getDisponivel()) {
                if (bloco.getDistanciaDestino() <= blocoMaisPertoDestino.getDistanciaDestino()) {
                    blocoMaisPertoDestino = bloco;
                }
            }
        }
        
        return blocoMaisPertoDestino;
    }


    public void observeAoRedor(){

        //observe ao redor e pegue o que é disponível e tem a distancia do destino menor

        aoRedor = new ArrayList<Bloco>();

        aoRedor.add(direita());
        aoRedor.add(baixo());
        aoRedor.add(cima());
        aoRedor.add(esquerda());
        aoRedor.add(esquerdaSuperior());
        aoRedor.add(esquerdaInferior());
        aoRedor.add(direitaSuperior());
        aoRedor.add(direitaInferior());

    }

    private Bloco direita(){

        return new Bloco(blocoAtual.getX() , (blocoAtual.getY() + 1), this.blocosBloqueados, this.historicoTotal);

    }

    private Bloco baixo(){

        return new Bloco((blocoAtual.getX() + 1) , blocoAtual.getY(), this.blocosBloqueados, this.historicoTotal);

    }

    private Bloco cima(){

        return new Bloco((blocoAtual.getX() -1), blocoAtual.getY(), this.blocosBloqueados, this. historicoTotal);

    }

    private Bloco esquerda(){

        return new Bloco(blocoAtual.getX(), (blocoAtual.getY() -1), this.blocosBloqueados, this.historicoTotal);
    }


    private Bloco esquerdaSuperior(){

        return new Bloco((blocoAtual.getX() - 1), (blocoAtual.getY() -1), this.blocosBloqueados, this.historicoTotal);

    }


    private Bloco esquerdaInferior(){

        return new Bloco((blocoAtual.getX() + 1), (blocoAtual.getY() -1), this.blocosBloqueados, this.historicoTotal);

    }


    private Bloco direitaSuperior(){

        return new Bloco((blocoAtual.getX() - 1), (blocoAtual.getY() + 1), this.blocosBloqueados, this.historicoTotal);

    }

    private Bloco direitaInferior(){

        return new Bloco((blocoAtual.getX() + 1), (blocoAtual.getY() + 1), this.blocosBloqueados, this.historicoTotal);

    }

}
