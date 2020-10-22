package tools;

import java.util.*;

/**
 * Created by mateus.santos on 11/10/17.
 */
public class LoaderRegras {

    public static void main(String[] args) {

        List<String> direcoes = new ArrayList<String>();
        direcoes.add("cima");
        direcoes.add("baixo");
        direcoes.add("direita");
        direcoes.add("esquerda");

        List<List<String>> allPossibilidades = new ArrayList<List<String>>();

        for (String direcao1: direcoes){
            List<String> temp = new ArrayList<String>();

            if(!temp.contains(direcao1)) {
                temp.add(direcao1);
            }

            for (String direcao2: direcoes){

                if(!temp.contains(direcao2)){

                    temp.add(direcao2);
                }
            }

            allPossibilidades.add(temp);
        }

//        System.out.println(allPossibilidades);

        List<List<String>> allPossibilidades2 = new ArrayList<List<String>>();

        for (List<String> list: allPossibilidades){

            for (String direcao1: list){
                List<String> temp = new ArrayList<String>();

                if(!temp.contains(direcao1)) {
                    temp.add(direcao1);
                }

                for (String direcao2: list){

                    if(!temp.contains(direcao2)){

                        temp.add(direcao2);
                    }
                }

                if(!allPossibilidades2.contains(temp)){
                    allPossibilidades2.add(temp);
                }
            }
        }


        for (List<String> list : allPossibilidades2){
            if(!allPossibilidades.contains(list)){

                allPossibilidades.add(list);
            }
        }


        System.out.println(allPossibilidades);



    }





}
