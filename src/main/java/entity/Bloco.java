package entity;

import java.util.ArrayList;
import java.util.List;

public class Bloco {

    private int x;
    private int y;
    private Boolean disponivel;
    private Integer distanciaDestino;

    public Bloco() {
    }

    public Bloco(int x, int y, Boolean disponivel) {
        this.x = x;
        this.y = y;
        this.disponivel = disponivel;
    }

    public Bloco(int x, int y, List<Bloco> blocosBloqueados, List<Bloco> historicoTotal) {
        this.x = x;
        this.y = y;
        distanciaDestino = calculaDistancia();

        this.disponivel = false;

        if(!blocosBloqueados.contains(this) && !historicoTotal.contains(this) && blocoExistente()) {
            this.disponivel = true;
        }

    }


    public Integer calculaDistancia(){
        Integer distanciaDeX = (15 - this.x);
        Integer distanciaDeY = (15 - this.y);

        return distanciaDeX + distanciaDeY;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getDistanciaDestino() {
        return distanciaDestino;
    }

    public void setDistanciaDestino(Integer distanciaDestino) {
        this.distanciaDestino = distanciaDestino;
    }

    @Override
    public String toString() {
        return "[" +  x + "-" + y + "]" + "  -  [" + disponivel + ']' + '\n';
    }

    @Override
    public boolean equals(Object obj) {

        if(this.x == ((Bloco)obj).getX() && this.y == ((Bloco)obj).getY()){
            return true;
        }
        return false;
    }


    //não haverá mais limites certo?
    //não precisaremos mais mapear os limites
    public Boolean blocoExistente(){

        if((this.getX() > 15 || this.getY() > 15) ||
                (this.getX() < 1 || this.getY() < 1)){
            return false;
        }

        return true;
    }


    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }
}
