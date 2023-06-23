package AP;

import java.util.*;

public class AP {
    private Stack<String> pilha;
    private String estadoAtual;

    public AP() {
        pilha = new Stack<>();
        pilha.push("$");
        pilha.push("q0");
        estadoAtual = "q0";
    }

    public String verificaPalavra(String palavra) {
        List<String> tokens = token(palavra);

        for (String token : tokens) {
            String transicao = obterTransicao(estadoAtual, token);

            if (transicao == null) {
                return "palavra rejeitada";
            }

            atualizarPilha(transicao);
        }

        if (estadoAtual.equals("qf") && pilha.isEmpty()) {
            return "palavra aceita";
        } else {
            return "palavra rejeitada";
        }
    }

    private List<String> token(String palavra) {
        palavra = palavra.replaceAll("\\s+", "");
        List<String> tokens = new ArrayList<>(Arrays.asList(palavra.split("(;|\\(|\\)|\\{|\\})", -1)));
        tokens.removeIf(String::isEmpty);
        return tokens;
    }

    private String obterTransicao(String estado, String token) {
        Map<String, Map<String, String>> transicoes = new HashMap<>();
        transicoes.put("q0", Map.of("programa", "q1"));
        transicoes.put("q1", Map.of("id", "q2"));
        transicoes.put("q2", Map.of("(", "q3"));
        transicoes.put("q3", Map.of("id", "q3", "int", "q4", ")", "q5"));
        transicoes.put("q4", Map.of(":", "q6"));
        transicoes.put("q6", Map.of("int", "q7", ";", "q8"));
        transicoes.put("q8", Map.of("id", "q9", "if", "q10", "return", "q11", "}", "q13"));
        transicoes.put("q9", Map.of("id", "q9", ";", "q12"));
        transicoes.put("q10", Map.of("(", "q14"));
        transicoes.put("q11", Map.of("id", "q15"));
        transicoes.put("q13", Map.of("}", "q13"));
        transicoes.put("q14", Map.of("id", "q14", ">", "q14", "<", "q14", ">=", "q14", "<=", "q14", "==", "q14", "numero", "q14", ")", "q16"));
        transicoes.put("q15", Map.of(";", "q17"));
        transicoes.put("q17", Map.of("}", "q13"));
        transicoes.put("qf", new HashMap<>());

        if (transicoes.containsKey(estado) && transicoes.get(estado).containsKey(token)) {
            return transicoes.get(estado).get(token);
        }

        return null;
    }

    private void atualizarPilha(String transicao) {
        pilha.pop();

        if (!transicao.equals("ε")) {
            String[] simbolos = new StringBuilder(transicao).reverse().toString().split(":");
            Collections.addAll(pilha, simbolos);
        }

        estadoAtual = pilha.peek();
    }

    public static void main(String[] args) {
        AP automato = new AP();
        String palavra = "programa id ( id : int ) { id : int;if(id>0){id=id/2;}else{id=0;}returnid;}";

        String resultado = automato.verificaPalavra(palavra);
        System.out.println(resultado);
    }
}