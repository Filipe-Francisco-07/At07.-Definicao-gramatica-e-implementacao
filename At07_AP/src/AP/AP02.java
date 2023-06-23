package AP;

import java.util.Stack;

public class AP02 {
    public static void main(String[] args) {
    	
        String entrada = "programa id ( id : int ) { id : int ; if (id > 0) { id = id / 2 ; } else { id = 0 ; } return id ; }";
        String [] vet = new String [entrada.length()];
        
        for(int i = 0; i < entrada.length();i++) {
        	vet = entrada.split(" ");
        }
        
        int estadoAtual = 0;
        
        Pilha pilha = new Pilha();
        
        pilha.empilhar("Z0");
        
        String[][] delta = {
        		//parte1
                {"0", "programa", "Z0", "1", ""},
                {"1", "id", "Z0", "2", ""},
                {"2", "(", "Z0", "3", "("},
                {"3", "id", "(", "4", ""},
                {"4", ":", "(", "5", ""},
                {"5", "int", "(", "6", ""},
                {"6", ")", "(", "7", "desempilha"},
                
                //parte2
                {"7", "{", "Z0", "8", "{"},
                
                {"8", "id", "{", "9", ""},
                {"9", ":", "{", "8", ""},
                {"9", "int", "{", "8", ""},
                {"9", ";", "{", "10", ""},                         
                {"10", "}", "{", "7", "desempilha"},
                
                {"7", "if", "Z0", "11", ""},
                {"11", "(", "Z0", "12", "("},
                {"12", "id", "(", "12", ""},
                {"12", ">", "(", "12", ""},
                {"12", "0", "(", "12", ""},
                {"12", ")", "(", "13", "desempilha"},
                {"13", "{", "Z0", "8", "{"},
                
                {"7", "else", "Z0", "14", ""},
                {"14", "{", "Z0", "8", "{"},
                
                {"7", "return", "Z0", "15", ""},
                {"15", "id", "Z0", "15", ""},
                {"15", ";", "Z0", "7", ""},
                
                {"7", "}", "Z0", "16", ""},                         
                
                {"16", "", "Z0", "16", ""},
        };
        
        int i = 0;
        while (i < entrada.length()) {
            String aux = null;
            for (String[] delt : delta) {
                if (delt[0].equals(Integer.toString(estadoAtual)) && delt[1].equals(vet[i]) && delt[2].equals(pilha.getTopo())) {
                	aux = delt[3];
                	
                	if(delt[1] == "(" || delt[1] == "{") {
                		pilha.empilhar(delt[1]);
                	}
                	if(delt[4] == "desempilha") {
                		pilha.desempilhar();
                	}
                }

            }
            if (aux == null) {
                break;
            }
            estadoAtual = Integer.parseInt(aux);
              
            i++;
        }
        if (estadoAtual == 16 && pilha.estaVazio()) {
            System.out.println("Linguagem Não Aceita");
        } else {
            System.out.println("Linguagem Aceita");
        }
    }

    
}