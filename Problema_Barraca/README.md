## 1 - Problema
Os organizadores de uma Conferência Nacional de Computação promoveram um
encontro de pessoas que trabalham na área relacionada à tecnologia da informação. Nesta
conferência, cada pessoa deveria levar sua barraca para que fosse montado um
acampamento para alojamento, já que a conferência dura 17 semanas, afim de aproximar
mais as pessoas e extrair o máximo da relação interpessoal, fazendo com que as mesmas
mantenham contato constante. Antes do início da conferência, no momento que as pessoas
montavam suas barracas na área reservada, foi descoberto que os organizadores não
atentaram para o importante fato de que existiriam homens e mulheres compartilhando a
área do acampamento. Sendo conhecida a popularidade dos homens da computação e a
fama de sempre serem muito ativos e conquistadores, tanto os homens quanto as mulheres
reclamaram que não queriam compartilhar da mesma área para acampar. Para minimizar
a chance de conflito, foi requisitado que fosse inserida uma parede separando homens e
mulheres. Felizmente os organizadores têm material suficiente para a construção de tal
parede fazendo uso de painéis de paredes portáteis. Existe a restrição de que tais painéis
só podem formar uma única parede reta de qualquer dimensão. Com este problema em
mãos, os organizadores requisitaram que você, bastião da sabedoria computacional,
baluarte dos problemas de geometria e complexidade de algoritmos, determine se é
possível separar os homens das mulheres usando apenas uma parede reta. A parede não
pode tocar em nenhuma barraca, embora o material da mesma seja fino e suficiente para
ficar arbitrariamente perto das barracas. A restrição de não tocar em nenhuma barraca é
para que as pessoas não derrubem a parede por acidente.

## 2 - Entrada
A entrada consiste de casos distintos para serem analisados. Cada caso é definido
por 2 inteiros: H e M, onde H é o número de homens e M é o número de mulheres,
respectivamente (1 ≤ H,M ≤ 500). Nas próximas (H+M) linhas, cada uma com quatro
inteiros positivos x1, y1, x2, y2, há a especificação de dois pontos distintos. (x1, y1)
especifica o canto inferior esquerdo de uma barraca, enquanto (x2, y2) especifica o canto
superior direito. De outra forma, podemos dizer que x1 < x2 e y1 < y2 sempre. A área do
acampamento tem como canto inferior esquerdo as coordenadas (0, 0), enquanto o
superior direito é (15.000, 15.000). Você pode assumir que as barracas estão 100% inseridas
na área determinada, isto é, nenhuma barraca toca as paredes da mesma. Pode-se assumir
também que, de forma alguma, uma barraca toca na outra (motivos óbvios). Assim como
as paredes, as barracas são feitas de um material finíssimo. O final da entrada é
especificado H = M = 0.

A entrada pode ser lida de qualquer arquivo ou através da entrada padrão (teclado).

## 3 - Saída
Para cada caso você deve imprimir o número do mesmo (iniciando de 1), seguido
por dois pontos ':' e um espaço. Após isso imprima: "É possível separar os dois grupos!", se
for possível separá-los. Caso contrário, imprima: “Não é possível separar os dois grupos!".
Entre os casos imprima uma linha em branco.

A saída não deve ser escrita em nenhum arquivo. Ela deve ser escrita na saída padrão.

## 4 - Exemplo
Entrada:
7 2
377 41 417 96
311 65 349 112
258 103 290 138
220 144 246 184
188 196 223 233
165 252 197 288
148 312 174 340
460 14 491 51
339 308 375 342


![image](https://github.com/pedrogalhardi/Projeto_Analise_Algoritmo/assets/61712871/6b01f825-84b0-45e4-8401-c190e938fdc9)

7 3
377 41 417 96
311 65 349 112
258 103 290 138
220 144 246 184
188 196 223 233
165 252 197 288
148 312 174 340
460 14 491 32
339 308 375 342
114 18 150 50


![image](https://github.com/pedrogalhardi/Projeto_Analise_Algoritmo/assets/61712871/ae7586bd-a90a-4ff2-ab4c-8058c4682ad1)

Saída:
Caso 1: É possível separar os dois grupos!
Caso 2: Não é possível separar os dois grupos!
 
