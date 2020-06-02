package calculadora;

import java.util.Scanner;
import calculadora.operacoes.Inteiros;
import calculadora.operacoes.Flutuante;

class CalculadoraBinaria {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        Inicio();
    }

    private static void Inicio(){

        //imprime a mensagem com as opcoes do menu principal
        Mensagem();

        //le a opcao escolhida pelo usuario e chama o metodo correspondente
        int x = sc.nextInt();
        if(x == 1) NumeroInteiro();
        if(x == 2) PontoFlutuante();
        if(x == 3) System.exit(0);

        System.out.println("\n \n");

        //apos realizada a operacao escolhida, retorna para o menu principal
        Inicio();

    }

    private static void Mensagem(){

        System.out.println("-----------------Calculadora de Numeros Binarios-----------------");
        System.out.println("Digite:");
        System.out.println("1 para realizar uma operacao com numeros inteiros;");
        System.out.println("2 para realizar uma operacao com numeros de ponto flutuante (padrao IEEE 754 32 bits); ou");
        System.out.println("3 para sair do programa");
        System.out.println("------------------------------------------------------------------");
    }

    private static void NumeroInteiro(){

        //le todos os dados necessarios para realizar operacao com inteiro
        System.out.println("Digite o sinal da operacao (+ - / ou *)");
        String operacao = sc.next();

        //manda uma mensagem de erro caso seja inserido um sinal de operacao invalido
        if(!operacao.equals("+") && !operacao.equals("-") && !operacao.equals("*") && !operacao.equals("/")){ 

            System.out.println("Ocorreu um erro. Insira uma operacao valida. \n");
            NumeroInteiro();
        }

        else {

            System.out.println("Digite a quantidade de bits da operacao desejada");
            int nbit = sc.nextInt();
            System.out.println("Digite o sinal do primeiro numero (+ ou -)");
            String sinal = sc.next();
            System.out.println("Digite o valor em binario do primeiro numero (Ex 1001)");
            Long numero = sc.nextLong();
            System.out.println("Digite o sinal do segundo numero (+ ou -)");
            String sinal2 = sc.next();
            System.out.println("Digite o valor em binario do segundo numero (Ex 1001)");
            Long numero2 = sc.nextLong();

            //checa a operacao escolhida pelo usuario e chama o metodo correspondente
            if(operacao.equals("+")) System.out.println("Resultado: " + Inteiros.SomaInteiro(nbit, sinal, numero, sinal2, numero2));
            else if(operacao.equals("-")) System.out.println("Resultado: " + Inteiros.SubtracaoInteiro(nbit, sinal, numero, sinal2, numero2));
            else if(operacao.equals("*")) System.out.println("Resultado: " + Inteiros.MultiplicacaoInteiro(nbit, sinal, numero, sinal2, numero2));
            else if(operacao.equals("/")) System.out.println("Resultado: " + Inteiros.DivisaoInteiro(nbit, sinal, numero, sinal2, numero2));
        }
    }
    
    private static void PontoFlutuante() {

        //le todos os dados necessarios para realizar operacao com ponto flutuante
        System.out.println("Digite o sinal da operacao (+ - / ou *)");
        String operacao = sc.next();

        //manda uma mensagem de erro caso seja inserido um sinal de operacao invalido
        if(!operacao.equals("+") && !operacao.equals("-") && !operacao.equals("*") && !operacao.equals("/")){ 

            System.out.println("Ocorreu um erro. Insira uma operacao valida. \n");
            PontoFlutuante();
        }

        else {

            System.out.println("Digite o sinal do primeiro numero (+ ou -)");
            String sinal = sc.next();
            System.out.println("Digite a mantissa do primeiro numero (Ex 1,010011)");
            Double mantissa = sc.nextDouble();
            System.out.println("Digite o expoente do primeiro numero em binario");
            int expoente = sc.nextInt();
            System.out.println("Digite o sinal do segundo numero (+ ou -)");
            String sinal2 = sc.next();
            System.out.println("Digite a mantissa do segundo numero (Ex 1,010011)");
            Double mantissa2 = sc.nextDouble();
            System.out.println("Digite o expoente do segundo numero em binario");
            int expoente2 = sc.nextInt();
            
            
            //checa a operacao escolhida pelo usuario e chama o metodo correspondente
            if(operacao.equals("+")) System.out.println("Resultado: " + Flutuante.SomaFlutuante(sinal, mantissa , expoente, sinal2, mantissa2, expoente2));
            else if(operacao.equals("-")) System.out.println("Resultado: " + Flutuante.SubtracaoFlutuante(sinal, mantissa , expoente, sinal2, mantissa2, expoente2));
            else if(operacao.equals("*")) System.out.println("Resultado: " + Flutuante.MultiplicacaoFlutuante(sinal, mantissa , expoente, sinal2, mantissa2, expoente2));
            else if(operacao.equals("/")) System.out.println("Resultado: " + Flutuante.DivisaoFlutuante(sinal, mantissa , expoente, sinal2, mantissa2, expoente2));
        }
    }
}   
