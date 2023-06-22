<?php
class AutomatoPilha {
    private $pilha;
    private $estadoAtual;

    public function __construct() {
        $this->pilha = array('$', 'q0');
        $this->estadoAtual = 'q0';
    }

    public function verificaPalavra($palavra) {
        $tokens = $this->token($palavra);
        
        foreach ($tokens as $token) {
            $transicao = $this->obterTransicao($this->estadoAtual, $token);
            
            if ($transicao == null) {
                return "palavra rejeitada";
            }
            
            $this->atualizarPilha($transicao);
        }
        
        if ($this->estadoAtual == 'qf' && empty($this->pilha)) {
            return "palavra aceita";
        } else {
            return "palavra rejeitada";
        }
    }
    
    private function token($palavra) {
        $palavra = preg_replace('/\s+/', '', $palavra);
        $tokens = preg_split('/(;|\(|\)|\{|\})/', $palavra, -1, PREG_SPLIT_DELIM_CAPTURE | PREG_SPLIT_NO_EMPTY);
        return $tokens;
    }
    
    private function obterTransicao($estado, $token) {
        $transicoes = array(
            'q0' => array(
                'programa' => 'q1',
            ),
            'q1' => array(
                'id' => 'q2',
            ),
            'q2' => array(
                '(' => 'q3',
            ),
            'q3' => array(
                'id' => 'q3',
                'int' => 'q4',
                ')' => 'q5',
            ),
            'q4' => array(
                ':' => 'q6',
            ),
            'q6' => array(
                'int' => 'q7',
                ';' => 'q8',
            ),
            'q8' => array(
                'id' => 'q9',
                'if' => 'q10',
                'return' => 'q11',
                '}' => 'q13',
            ),
            'q9' => array(
                'id' => 'q9',
                ';' => 'q12',
            ),
            'q10' => array(
                '(' => 'q14',
            ),
            'q11' => array(
                'id' => 'q15',
            ),
            'q13' => array(
                '}' => 'q13',
            ),
            'q14' => array(
                'id' => 'q14',
                '>' => 'q14',
                '<' => 'q14',
                '>=' => 'q14',
                '<=' => 'q14',
                '==' => 'q14',
                'numero' => 'q14',
                ')' => 'q16',
            ),
            'q15' => array(
                ';' => 'q17',
            ),
            'q17' => array(
                '}' => 'q13',
            ),
            'qf' => array(),
        );
        
        if (isset($transicoes[$estado][$token])) {
            return $transicoes[$estado][$token];
        }
        
        return null;
    }
    
    private function atualizarPilha($transicao) {
        // Implemente a função de atualização da pilha aqui
        array_pop($this->pilha);
        
        if ($transicao !== 'ε') {
            $simbolos = array_reverse(explode(':', $transicao));
            $this->pilha = array_merge($this->pilha, $simbolos);
        }
        
        $this->estadoAtual = end($this->pilha);
    }
}

$automato = new AutomatoPilha();
$palavra = 'programaid(id:int){id:int;if(id>0){id=id/2;}else{id=0;}returnid;}';

$resultado = $automato->verificaPalavra($palavra);
echo $resultado;

?>