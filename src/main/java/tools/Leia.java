package tools;

import entity.Bloco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Leia {

    private List<Bloco> blocosRespostaUsuario;


    public Leia() {

        blocosRespostaUsuario = new ArrayList<Bloco>();

    }

    public List<Bloco> getBlocosRespostaUsuario() {
        return blocosRespostaUsuario;
    }

    public void setBlocosRespostaUsuario(List<Bloco> blocosRespostaUsuario) {
        this.blocosRespostaUsuario = blocosRespostaUsuario;
    }

    public Integer leiaRespostaUsuario(){
        Scanner input= new Scanner(System.in);

        Integer resposta = input.nextInt();

        return resposta;
    }


    public void view(){

        for (int i = 1; i <= 7; i++) {

            System.out.println("digite as coordenadas do bloco: " +  i);

            System.out.println("digite o valor de x: ");
            Integer x = leiaRespostaUsuario();

            System.out.println("digite o valor de y: ");
            Integer y = leiaRespostaUsuario();

            Bloco bloco = new Bloco(x, y, false);

            if(bloco.blocoExistente()){
                if(!blocosRespostaUsuario.contains(bloco)) {
                    Bloco blocoX2 = new Bloco(bloco.getX() + 2, bloco.getY(), false);

                    if (blocoX2.blocoExistente()) {

                        Bloco blocoX1 = new Bloco(bloco.getX() + 1, bloco.getY(), false);

                        blocosRespostaUsuario.add(bloco);
                        blocosRespostaUsuario.add(blocoX1);
                        blocosRespostaUsuario.add(blocoX2);
                    }
                }else {
                    System.out.println("Bloco inválido ... encerrando programa. ");
                    System.exit(1);
                }


            }else{

                System.out.println("Bloco inválido ... encerrando programa. ");
                System.exit(1);
            }

        }

    }


}
