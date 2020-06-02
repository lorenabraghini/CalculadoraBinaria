package calculadora.operacoes;

public class Flutuante {

    public static String SomaFlutuante(String sinal, Double mantissa, int expoente, String sinal2, Double mantissa2, int expoente2){

        //corrige os numeros
        String [] numero = ArrumaNumero(mantissa, expoente);
        String [] numero2 = ArrumaNumero(mantissa2, expoente2);

        //checa se ocorreu overflow ou underflow
        if(numero[0].equals("Underflow") || numero2[0].equals("Underflow")) return "Underflow";
        if(numero[0].equals("Overflow") || numero2[0].equals("Overflow")) return "Overflow";

        int expoente0 = Integer.parseInt(numero[1]);
        int expoente1 = Integer.parseInt(numero2[1]);
        int expoentefinal;
        int nbits = 0;

        //os proximos dois ifs servem para igualar o expoente dos numeros
        if(expoente0 < expoente1) {

            for(int i = 0; i < expoente1-expoente0; i++) numero2[0]+= "0";
            expoentefinal = expoente0;
        }

        if(expoente1 < expoente0) {

            for(int i = 0; i < expoente0-expoente1; i++) numero[0]+= "0";
            expoentefinal = expoente1;
        }

        else expoentefinal = expoente1;

        if(expoentefinal < -127) return "Underflow";
        if(expoentefinal > 128) return "Overflow";

        //checa qual numero possui mais bits para usar a soma inteira
        if(numero[0].length() > numero2[0].length()) nbits = numero[0].length();
        else nbits = numero2[0].length();

        String somainteira = Inteiros.SomaInteiro(nbits, sinal, Long.parseLong(numero[0]), sinal2, Long.parseLong(numero2[0]));
        String resultado = "";

        //coloca a virgula no local esperado
        if(expoentefinal != 0) {
            
            expoentefinal = -expoentefinal;            
            char [] flutu = somainteira.toCharArray();
    
            for(int l = 0; l < flutu.length-expoentefinal; l++) resultado += flutu[l];
            resultado += ",";
            for(int g = flutu.length-expoentefinal; g < flutu.length; g++) resultado += flutu[g];
            if(resultado.charAt(0) == ',') resultado = "0" + resultado;
        }

        else resultado = somainteira;

        return resultado;
    }

    public static String SubtracaoFlutuante(String sinal, Double mantissa, int expoente, String sinal2, Double mantissa2, int expoente2){
        
        //corrige os numeros
        String [] numero = ArrumaNumero(mantissa, expoente);
        String [] numero2 = ArrumaNumero(mantissa2, expoente2);

        //checa se ocorreu overflow ou underflow
        if(numero[0].equals("Underflow") || numero2[0].equals("Underflow")) return "Underflow";
        if(numero[0].equals("Overflow") || numero2[0].equals("Overflow")) return "Overflow";

        int expoente0 = Integer.parseInt(numero[1]);
        int expoente1 = Integer.parseInt(numero2[1]);
        int expoentefinal;
        int nbits = 0;

        //os proximos dois ifs servem para igualar o expoente dos numeros
        if(expoente0 < expoente1) {

            for(int i = 0; i < expoente1-expoente0; i++) numero2[0]+= "0";
            expoentefinal = expoente0;
        }

        if(expoente1 < expoente0) {

            for(int i = 0; i < expoente0-expoente1; i++) numero[0]+= "0";
            expoentefinal = expoente1;
        }

        else expoentefinal = expoente1;

        if(expoentefinal < -127) return "Underflow";
        if(expoentefinal > 128) return "Overflow";

        //checa qual numero possui mais bits para usar a soma inteira
        if(numero[0].length() > numero2[0].length()) nbits = numero[0].length();
        else nbits = numero2[0].length();

        String subinteira = Inteiros.SubtracaoInteiro(nbits, sinal, Long.parseLong(numero[0]), sinal2, Long.parseLong(numero2[0]));
        String resultado = "";

        //coloca a virgula no local esperado
        if(expoentefinal != 0) {

            expoentefinal = -expoentefinal;
            char [] flutu = subinteira.toCharArray();

            for(int l = 0; l < flutu.length-expoentefinal; l++) resultado += flutu[l];
            resultado += ",";
            for(int g = flutu.length-expoentefinal; g < flutu.length; g++) resultado += flutu[g];
            if(resultado.charAt(0) == ',') resultado = "0" + resultado;
        }

        else resultado = subinteira;

        return resultado;
    }

    public static String MultiplicacaoFlutuante(String sinal, Double mantissa, int expoente, String sinal2, Double mantissa2, int expoente2){

        //corrige os numeros
        String [] numero = ArrumaNumero(mantissa, expoente);
        String [] numero2 = ArrumaNumero(mantissa2, expoente2);


        //checa se ocorreu overflow ou underflow
        if(numero[0].equals("Underflow") || numero2[0].equals("Underflow")) return "Underflow";
        if(numero[0].equals("Overflow") || numero2[0].equals("Overflow")) return "Overflow";

        int nbits = 0;
        
        //checa qual numero possui mais bits para usar a soma inteira
        if(numero[0].length() > numero2[0].length()) nbits = numero[0].length();
        else nbits = numero2[0].length();


        String multiplicacaointeira = Inteiros.MultiplicacaoInteiro(nbits, sinal, Long.parseLong(numero[0]), sinal2, Long.parseLong(numero2[0]));

        String resultado = "";
        int expoentefinal = Integer.parseInt(numero[1]) + Integer.parseInt(numero2[1]);
 

        if(expoentefinal < -127) return "Underflow";
        if(expoentefinal > 128) return "Overflow";

        //coloca a virgula no local esperado
        if(expoentefinal != 0) {

            expoentefinal = -expoentefinal; 
            char [] flutu = multiplicacaointeira.toCharArray();

            for(int l = 0; l < flutu.length-expoentefinal; l++) resultado += flutu[l];
            resultado += ",";
            for(int g = flutu.length-expoentefinal; g < flutu.length; g++) resultado += flutu[g];
            if(resultado.charAt(0) == ',') resultado = "0" + resultado;
        }

        else resultado = multiplicacaointeira;

        return resultado;
    }

    public static String DivisaoFlutuante(String sinal, Double mantissa, int expoente, String sinal2, Double mantissa2, int expoente2){
        
        //corrige os numeros
        String [] numero = ArrumaNumero(mantissa, expoente);
        String [] numero2 = ArrumaNumero(mantissa2, expoente2);

        //checa se ocorreu overflow ou underflow
        if(numero[0].equals("Underflow") || numero2[0].equals("Underflow")) return "Underflow";
        if(numero[0].equals("Overflow") || numero2[0].equals("Overflow")) return "Overflow";

        int nbits = 0;
        
        //checa qual numero possui mais bits para usar a soma inteira
        if(numero[0].length() > numero2[0].length()) nbits = numero[0].length();
        else nbits = numero2[0].length();


        String divisaointeira = Inteiros.DivisaoInteiro(nbits, sinal, Long.parseLong(numero[0]), sinal2, Long.parseLong(numero2[0]));

        String resultado = "";
        int expoentefinal = Integer.parseInt(numero[1]) - Integer.parseInt(numero2[1]);

        if(expoentefinal < -127) return "Underflow";
        if(expoentefinal > 128) return "Overflow";

        //coloca a virgula no local esperado
        if(expoentefinal != 0) {

            expoentefinal = -expoentefinal; 
            char [] flutu = divisaointeira.toCharArray();

            for(int l = 0; l < flutu.length-expoentefinal; l++) resultado += flutu[l];
            resultado += ",";
            for(int g = flutu.length-expoentefinal; g < flutu.length; g++) resultado += flutu[g];
            if(resultado.charAt(0) == ',') resultado = "0" + resultado;
        }

        else resultado = divisaointeira;

        return resultado;
    }

    //função que transforma os numeros de ponto flutuante em inteiros
    public static String[] ArrumaNumero(Double mantissa, int expoente) {

        String [] resultado = new String[2];

        //subtrai o BIAS do expoente
        expoente = Integer.parseInt(String.valueOf(expoente), 2) - 127;
        int expoenteaux = expoente;

        //se o expoente for menor que -127 ocorre um Underflow
        if(expoenteaux < -127){
            
            resultado[0] = "Underflow";
            resultado[1] = String.valueOf(expoente);
            return resultado;
        }

        //se o expoente for maior que 128 ocorre um Overflow
        if (expoenteaux > 128) {

            resultado[0] = "Overflow";
            resultado[1] = String.valueOf(expoente);
            return resultado;
        }

        char [] mant = String.valueOf(mantissa).toCharArray();
        String numero = "1";
        
        //tira a virgula do numero
        for(int i = 2; i < mant.length; i++) {

            numero += mant[i];
            expoente--;
        }

        /*caso o numero de "casas" a andar com a virgula seja maior que o 
        numero de bits existente, completa o numero com zeros até o expoente chegar em zero*/
        if(mant.length-2 < expoenteaux) {

            for(int k = mant.length-2; k < expoenteaux; k++) { 
                numero += "0";
                expoente--;
            }
        }

        expoente--;

        //devolve um array com o numero inteiro e o expoente final
        resultado[0] = numero;
        resultado[1] = String.valueOf(expoente);

        return resultado; 
    }
}