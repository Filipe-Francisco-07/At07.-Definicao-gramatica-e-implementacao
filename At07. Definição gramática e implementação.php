<?php 

$sigma = array('a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
$num = array(0,1,2,3,4,5,6,7,8,9);

$delta = array('p'=>array('a'=>'q1', 'b'=>'q1', 'c'=>'q1', 'd'=>'q1', 'e'=>'q1','f'=> 'q5', 'g'=>'q1', 'h'=>'q1','i'=>'q2', 'j'=>'q1', 'k'=>'q1', 'l'=>'q1', 'm'=>'q1', 'n'=>'q1', 'o'=>'q1', 'p'=>'q1', 'q'=>'q1', 'r'=>'q1', 's'=>'q1', 't'=>'q1', 'u'=>'q1', 'v'=>'q1', 'w'=>'q8', 'x'=>'q1', 'y'=>'q1', 'z'=>'q1', 0 => 'q4', 1 => 'q4', 2 => 'q4', 3 => 'q4', 4 => 'q4', 5 => 'q4', 6 => 'q4', 7 => 'q4', 8 => 'q4', 9 => 'q4'),
               'q'=>array('a'=>'q1', 'b'=>'q1','i'=> 'q3', 'c'=>'q1', 'd'=>'q1', 'e'=>'q1', 'g'=>'q1', 'h'=>'q1','f'=>'q3', 'j'=>'q1', 'k'=>'q1', 'l'=>'q1', 'm'=>'q1', 'n'=>'q1', 'o'=>'q1', 'p'=>'q1', 'q'=>'q1', 'r'=>'q1', 's'=>'q1', 't'=>'q1', 'u'=>'q1', 'v'=>'q1', 'w'=>'q1', 'x'=>'q1', 'y'=>'q1', 'z'=>'q1'),
               'f'=>array('a'=>'q1', 'b'=>'q1', 'c'=>'q1', 'd'=>'q1', 'e'=>'q1','f'=> 'q1', 'g'=>'q1', 'h'=>'q1','i'=>'q1', 'j'=>'q1', 'k'=>'q1', 'l'=>'q1', 'm'=>'q1', 'n'=>'q1', 'o'=>'q1', 'p'=>'q1', 'q'=>'q1', 'r'=>'q1', 's'=>'q1', 't'=>'q1', 'u'=>'q1', 'v'=>'q1', 'w'=>'q1', 'x'=>'q1', 'y'=>'q1', 'z'=>'q1'),
);


$Q = array('p', 'q', 'f');

$q0 = 'p';

$finais = array('f');

$palavra = (isset($_POST['input'])?$_POST['input']:""); 

$estado = $q0;

for($i = 0; $i < strlen($palavra); $i++){
    $estado = $delta[$estado][$palavra[$i]];
}

if(in_array($estado,$finais)){
    echo "Cadeia aceita"."<br>estado final: ".$estado."<br>A palavra é: ".$palavra;
    if($estado == 'q1')
        echo "<br>A cadeia é um: ID.";
        else if($estado == 'q3')
            echo "<br>A cadeia é reservada: IF." ;
            else if($estado == 'q4')
                echo "<br>A cadeia é uma: Constante.";
                else if($estado == 'q7')
                    echo "<br>A cadeia é reservada: FOR.";
                    else if($estado == 'q12')
                        echo "<br>A cadeia é reservada: WHILE.";
                        else if($estado == 'q13')
                        echo "<br>Cadeia rejeitada.";
                    }else{
    echo "Cadeia rejeitada"."<br>Estado atual: ".$estado;
}
echo '<br><a href="input.html">digitar novamente</a>';


?>