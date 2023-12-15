# Problema do Caixeiro Viajante

## 1 - Problema 

O Problema do Caixeiro Viajante (PCV, ou Travelling Salesman Problem) é um
problema que tenta determinar a menor rota para percorrer uma série de cidades
(visitando uma única vez cada uma delas), retornando à cidade de origem. Ele é um
problema de otimização NP-Completo, inspirado na necessidade dos vendedores em
realizar entregas em diversos locais (as cidades) percorrendo o menor caminho
possível, reduzindo o tempo necessário para a viagem e os possíveis custos com
transporte e combustível. (Fonte: Wikipédia). Em outras palavras, consiste em
encontrar um ciclo Hamiltoniano de menor custo em um grafo ponderado não
orientado.
Neste trabalho, você resolverá o PCV de duas formas diferentes: Algoritmo
Ótimo (Tentativa e Erro) e Heurística (Algoritmo Genético).

## Entrada 

A entrada deverá ser lida de arquivo de texto, contendo as seguintes
informações: número n de vértices e; n-ésima linha deverá conter informações do
vértice de origem, vértices de destino e pesos das arestas, conforme descrição abaixo:

5
0 0-40; 1-50;
1 1-45; 2-4;
2 3-1; 0-99;
3 1-42; 4-7;
4 3-78; 0-32;

## Saída 

Para cada caso, imprima o custo mínimo necessário para percorrer o ciclo que
passa por cada vértice uma única vez, juntamente com o caminho a ser percorrido.

94
0 - 1 - 2 - 3 – 4 - 0
