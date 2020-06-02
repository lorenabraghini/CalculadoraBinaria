package calculadora.operacoes;

public class Inteiros {

    public static String SomaInteiro(int nbit, String sinal, long numero, String sinal2, long numero2) {

        //caso algum dos numeros for negativo, realiza a subtracao
        if(sinal.equals("-")) return SubtracaoInteiro(nbit, sinal2, numero2, "+", numero);
        if(sinal2.equals("-")) return SubtracaoInteiro(nbit, sinal, numero, "+", numero2);
        
        //caso algum dos numeros for igual a zero, retorna o outro numero
        if(numero == 0) return SetBits(nbit, numero2);   
        if(numero2 == 0) return SetBits(nbit, numero);
     
        //conversao dos numeros para array de char ja com o numero de bits certo
        char [] a = SetBits(nbit, numero).toCharArray();
        char [] b = SetBits(nbit, numero2).toCharArray();
        char [] c = new char [nbit + 1];
    
        char aux = '0'; //variavel que 'sobe'
        
        c[0] = '0';
        //for que realiza a soma em si
        for(int m = (c.length - 1); m > 0; m--){
    
            //1+1 == 0  e sobe 1
            if(a[m-1] == b[m-1] && a[m-1] == '1') {
    
                c[m] = '0';
                aux = '1';
            }
    
            //0+0 == 0
            else if(a[m-1] == b[m-1] && a[m-1] == '0') c[m] = '0';
    
            //1+0 == 0+1 == 1
            else if((a[m-1] == '1' && b[m-1] == '0') || (b[m-1] == '1' && a[m-1] == '0')) c[m] = '1';
        
            //se tiver subido 1
            if(aux == '1') {
    
                if(m == 1) c[0] = '1';
    
                //se o numero a esquerda for 1, o ele passa a ser 0 e sobe 1 novamente
                else if(a[m-2] == '1') a[m-2] = '0'; 
    
                //caso contrario, ele passa a ser 1 e nao sobe nenhum valor
                else {
                    a[m-2] = '1';
                    aux = '0';
                }
            }    
        }
    
        String resultado = String.copyValueOf(c);
        long soma = Long.parseLong(resultado);      
    
        return SetBits(nbit,soma);
    }

    public static String SubtracaoInteiro(int nbit, String sinal, long numero, String sinal2, long numero2) {
        
        //caso algum dos numeros for igual a zero, retorna o outro numero
        if(numero == 0) return SetBits(nbit, numero2);   
        if(numero2 == 0) return SetBits(nbit, numero);

        //arruma o número de bits
        String a = (SetBits(nbit, numero));
        String b = (SetBits(nbit, numero2));

        //checa onde irá usar o complemento de dois
        if(sinal.equals("-")) numero = Long.parseLong(CompDois(a));
        else numero = Long.parseLong(a);

        if(sinal2.equals("+")) numero2 = Long.parseLong(CompDois(b));
        else numero2 = Long.parseLong(b);

        //chama a soma 
        String resultado = SomaInteiro(nbit, "+", numero, "+", numero2);
            
        return resultado;
    }

    public static String MultiplicacaoInteiro(int nbit, String sinal, long numero, String sinal2, long numero2) {

        //checa se a multiplicacao e por 0 ou 1
        if(numero2 == 0) return SetBits(nbit, 0);
        if(numero2 == 1) return SetBits(nbit, numero);
        if(numero == 0) return SetBits(nbit, 0);
        if(numero == 1) return SetBits(nbit, numero2);
  
        //se algum dos numeros for negativo, faz o complemento de dois
        if(sinal.equals("-")) numero = Long.parseLong(CompDois(String.valueOf(numero)));
        if(sinal2.equals("-")) numero2 = Long.parseLong(CompDois(String.valueOf(numero2)));
  
        //transforma as variaveis necessarias para arrays de char
        char [] multiplicando = String.valueOf(numero).toCharArray();
        char [] multiplicador = String.valueOf(numero2).toCharArray();
        char [] inverso = CompDois(String.valueOf(numero)).toCharArray();
  
        //variaveis necessarias para determinar a quantidade de colunas da matriz
        int x = inverso.length;
        int y = multiplicador.length;
  
        /*cria a matriz na qual sera realizado o algoritmo de booth,
        onde booth[0] = A, booth[1] = S e booth[2] = P*/
        char [][] booth = new char [3][x+y+1];
  
        //preenche os x primeiros elementos de cada linha
        for(int i = 0; i < x; i++){
  
            booth[0][i] = multiplicando[i];
            booth[1][i] = inverso[i];
            booth[2][i] = '0';       
        }
  
        //preenche os proximos y elementos de cada linha
        for(int i = x; i < x+y; i++){
  
            booth[0][i] = '0';
            booth[1][i] = '0';
            booth[2][i] = multiplicador[i-x];
        }
  
        //coloca zero no ultimo elemento de cada linha
        booth[0][x+y] = '0';
        booth[1][x+y] = '0';
        booth[2][x+y] = '0';
  
        //realiza a operacao
        for(int i = 0; i < y; i++){
  
            //se os ultimos dois bits do produto for 01, P+=A
            if(booth[2][x+y] == '1' && booth[2][x+y-1] == '0'){
  
                long p = Long.parseLong(String.copyValueOf(booth[2]));
                long a = Long.parseLong(String.copyValueOf(booth[0]));
                char[] soma = SomaInteiro(x+y+1, "+", p, "+", a).toCharArray();
        
                if(soma.length == x+y+1) for(int m = 0; m < x+y+1; m++) booth[2][m] = soma[m];
                else for(int m = 0; m < x+y+1; m++) booth[2][m] = soma[m+1];
            }
  
            //se os ultimos dois bits do produto for 10, P+=S
            else if(booth[2][x+y] == '0' && booth[2][x+y-1] == '1'){
  
                long p = Long.parseLong(String.copyValueOf(booth[2]));
                long s = Long.parseLong(String.copyValueOf(booth[1]));
                char[] soma = SomaInteiro(x+y+1, "+", p, "+", s).toCharArray();
            
                if(soma.length == x+y+1) for(int m = 0; m < x+y+1; m++) booth[2][m] = soma[m];
                else for(int m = 0; m < x+y+1; m++) booth[2][m] = soma[m+1];
            }
  
            //desloca P um bit para a direita
            for(int k = x+y; k > 0; k--) booth[2][k] = booth[2][k-1];
        } 
  
        //descarta o ultimo bit e retorna o resultado final com o numero de bits desejados
        String resultado = "";
        for(int i = 1; i < x+y; i++) resultado+= booth[2][i];
  
        return SetBits(nbit, Long.parseLong(resultado));
    }

    public static String DivisaoInteiro(int nbit, String sinal, long numero, String sinal2, long numero2) {

        //checa se um dos números é zero
        if(numero == 0) return SetBits(nbit, 0);
        if(numero2 == 0) return "Operacao impossivel";

        //se o divisor for 1 o resultado é o dividendo
        if(numero2 == 1) return SetBits(nbit, numero);

        //se dividendo e divisor forem iguais, o resultado é 1
        if(numero == numero2) return SetBits(nbit, 1);

        //se algum dos numeros for negativo, faz o complemento de dois
        if(sinal.equals("-")) numero = Long.parseLong(CompDois(String.valueOf(numero)));
        if(sinal2.equals("-")) numero2 = Long.parseLong(CompDois(String.valueOf(numero2)));

        //criando arrays de char 
        char [] a = String.valueOf(numero).toCharArray();
        char [] b = String.valueOf(numero2).toCharArray();
        String c = "";

        String divisor = String.copyValueOf(b); 

        //array auxiliar para guardar chances de divisão
        String j = "";
        int aux = a.length;

        //for que realiza a divisao em si
        for(int i = 0; i < aux; i++) {

            //vai juntando os bits do dividendo ate que seja divisivel
            j += a[i];
            Long k = Long.parseLong((j));

            //caso seja divisivel, entra no if
            if(Compara(j, divisor)){
                
                //adiciona 1 no resultado e subtrai o divisor de j para achar o resto
                c += "1";
                String resto = SubtracaoInteiro(j.length(), sinal, k, sinal2, numero2);

                //apos isso, e feita a manipulacao do novo dividendo
                for(int l = 0; l <= i; l++) {
                    j = "";
                    a[l] = '0';
                }

                //zera os bits que ja foram usados e "joga eles fora"
                String dividendo = String.copyValueOf(a);
                int lolo = Integer.parseInt(dividendo);
                dividendo = String.valueOf(lolo);

                //ignora o overflow no resto
                char [] hp = resto.toCharArray();
                resto = "";
                for(int q = 1; q < hp.length; q++) resto += hp[q];
                
                String aaa = "";
                for(int p = 0; p < resto.length(); p++) aaa += "0";
                //se o resto for zero, o dividendo nao muda, se nao, o dividendo passa a ser o resto seguido do dividendo
                if(aaa.equals(resto)) a = dividendo.toCharArray();
                else a = (resto + dividendo).toCharArray();

                //se o novo dividendo tiver um tamanho maior do que o do anterior, ele fica sendo o novo limite do for
                if(a.length > aux) aux = a.length;

                //se for menor, adiciona bits a esquerda do dividendo para que fique do mesmo tamanho que o atual
                else if(a.length < aux) {

                    String w = "";
                    for(int f = 0; f < aux-a.length; f++) w+="0";
                    w += String.copyValueOf(a);
                    a = w.toCharArray();
                }

                j += a[i];
            }
            //se nao der para dividir, coloca zero no resultado
            else c += "0";
        }

        Long resultado = Long.parseLong(c);
        return SetBits(nbit, resultado);
    }

    //funcao que retorna um numero com a quantidade de bits passadas por parametro
    private static String SetBits(int nbit, long numero){

        //cria um array de char com o numero
        String aux = Long.toString(numero);
        char [] valor = aux.toCharArray();
        
        //cria um array com a diferenca entre o numero de bits desejado e o numero de bits do valor em questao
        int k = nbit - valor.length;
        if(k < 0) return Long.toString(numero);
    
        char [] tmp = new char [k];
    
        //preenche o array com zeros e retorna a string resultante
        for(int i = 0; i < k; i++) tmp[i] = '0';
    
        String zeros = String.copyValueOf(tmp);
    
        return zeros + aux;
    }

    //funcao que retorna o complemento de dois de um numero
    private static String CompDois(String numero) {
        
        char [] n = (String.valueOf(numero)).toCharArray();
  
        //Complemento de 1 (trocando bits de 0 para 1 e vice-versa)
        for(int i = 0; i < n.length; i++){ 
            
            if(n[i] == '0') n[i] = '1';
            else n[i] = '0';
        }
  
        //cria array para guardar o resultado
        char [] res = new char [n.length];
        char aux = '1';
  
        //soma o resultado do complemento de 1 com 1
        for(int i = n.length-1; i > -1; i--){
  
            //1+1 = 0 e sobe 1
            if(n[i] == '1' && aux == '1') res[i] = '0';        
    
            //1+0 = 1
            else if(n[i] == '1' && aux == '0') res[i] = '1';
                
            //1+0 =1
            else if(n[i] == '0' && aux == '1') {
    
                res[i] = '1';
                aux = '0';
            }
    
            //0+0 = 0
            else if(n[i] == '0' && aux == '0') res[i] = '0';
        }        
  
        String resultado = String.copyValueOf(res);
        return resultado;
    }

    //funcao que recebe dois numeros e retorna true se o primeiro for maior ou igual o segundo
    public static boolean Compara(String dividendo, String divisor){

        int dividendo1 = Integer.parseInt(dividendo, 2);
        int divisor1 = Integer.parseInt(divisor, 2);

        if(dividendo1 >= divisor1) return true;
        else return false;
    }
}