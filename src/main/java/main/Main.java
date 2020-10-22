package main;

import entity.Bloco;
import entity.Robo;
import tools.Leia;
import tools.MakeSpreadsheet;
import java.util.List;

public class Main {


    public void mapaCaio(Robo robo){

        for (int i = 1; i <= 12; i++) {
            robo.getBlocosBloqueados().add(new Bloco(i, 2, false));
        }
       for (int i = 10; i <= 15; i++) {
           robo.getBlocosBloqueados().add(new Bloco(i, 4, false));
        }
    }

    public void mapaDois(Robo robo){
        robo.getBlocosBloqueados().add(new Bloco(2, 15, false));
        robo.getBlocosBloqueados().add(new Bloco(3, 15, false));
        robo.getBlocosBloqueados().add(new Bloco(4, 15, false));

        robo.getBlocosBloqueados().add(new Bloco(4, 14, false));
        robo.getBlocosBloqueados().add(new Bloco(5, 14, false));
        robo.getBlocosBloqueados().add(new Bloco(6, 14, false));

        robo.getBlocosBloqueados().add(new Bloco(6, 13, false));
        robo.getBlocosBloqueados().add(new Bloco(7, 13, false));
        robo.getBlocosBloqueados().add(new Bloco(8, 14, false));

        robo.getBlocosBloqueados().add(new Bloco(8, 12, false));
        robo.getBlocosBloqueados().add(new Bloco(9, 12, false));
        robo.getBlocosBloqueados().add(new Bloco(10, 12, false));

        robo.getBlocosBloqueados().add(new Bloco(10, 11, false));
        robo.getBlocosBloqueados().add(new Bloco(11, 11, false));
        robo.getBlocosBloqueados().add(new Bloco(12, 11, false));

        robo.getBlocosBloqueados().add(new Bloco(5, 11, false));
        robo.getBlocosBloqueados().add(new Bloco(6, 11, false));
        robo.getBlocosBloqueados().add(new Bloco(7, 11, false));

        robo.getBlocosBloqueados().add(new Bloco(12, 10, false));
        robo.getBlocosBloqueados().add(new Bloco(13, 10, false));
        robo.getBlocosBloqueados().add(new Bloco(14, 10, false));
    }


    public void loadBlocos(Robo robo){

        mapaCaio(robo);
        //System.out.println(robo.getBlocosBloqueados());

    }


    public void start(List<Bloco> blocosBloqueados){

            StringBuilder stringBuilder = new StringBuilder(".");
            MakeSpreadsheet makeSpreadsheet = new MakeSpreadsheet();

            Robo robo = new Robo();
            robo.getBlocosBloqueados().addAll(blocosBloqueados);

            int cansado = 0;

            while (!robo.chegou() && cansado != 1000){

                stringBuilder.append(".");
                System.out.println(stringBuilder);

                if(!robo.ande()){
                    System.out.println("final");

                    Bloco blocoLembranca = robo.lembreDeOndeEsteve();
                    if(blocoLembranca != null){
                        robo.setBlocoAtual(blocoLembranca);
                    }


                }

                cansado++;
            }

            System.out.println("================================================");
            System.out.println("historico total: " + robo.getHistoricoTotal());
            System.out.println("================================================");
            System.out.println("blocos bloqueados: " + robo.getBlocosBloqueados());

            makeSpreadsheet.buildMap(robo.getHistoricoTotal(), robo.getBlocosBloqueados());



    }


    public static void main(String[] args) {

        Main m = new Main();

        Leia leia = new Leia();
        leia.view();


        System.out.println(leia.getBlocosRespostaUsuario());

        System.out.println("===================================");
        m.start(leia.getBlocosRespostaUsuario());

    }


}
