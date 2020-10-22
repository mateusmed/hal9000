package entity;

import java.util.ArrayList;
import java.util.List;

public class Regra {

    private List<String> direcoes;

    public Regra() {
        direcoes = new ArrayList<String>();
    }

    public Regra(List<String> direcoes) {
        this.direcoes = direcoes;
    }

    public List<String> getDirecoes() {
        return direcoes;
    }

    public void setDirecoes(List<String> direcoes) {
        this.direcoes = direcoes;
    }

    @Override
    public String toString() {
        return "Regra{" +
                "direcoes=" + direcoes +
                '}';
    }
}
