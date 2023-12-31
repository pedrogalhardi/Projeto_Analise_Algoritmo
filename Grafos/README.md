# Trabalho Pratico II

## 1 - Entrada
O programa deverá carregar um grafo a partir de um arquivo texto (que o usuário
irá especificar), como exemplificado no arquivo Teste.txt disponível em anexo a este
documento. O arquivo Teste - Grafo.jpg apresenta uma visualização gráfica do grafo
contido no arquivo Teste.txt;
• O grafo deverá ser armazenado em uma das três possíveis estruturas
computacionais vistas em aula (matriz de adjacência, matriz de incidência ou lista
de adjacência). A estrutura também deverá ser informada pelo usuário;
• Ao carregar o grafo, o programa deverá solicitar ao usuário qual algoritmo em
grafos que ele quer executar. Seu programa deverá rodar os seguintes algoritmos:

## - Busca em Profundidade (DFS)
O algoritmo de busca em profundidade no grafo. O usuário deverá informar o vértice inicial da busca. 
Os resultados deste algoritmo serão os tempos de descoberta (d) e finalização (f) de cada
vértice. Além disso, o algoritmo deverá classificar as arestas de acordo com a
busca (arestas de árvores, aresta de retorno, arestas de avanço e arestas de
cruzamento);


## - Busca em Largura (BFS)
O algoritmo de busca em largura. O usuário deverá
informar o vértice inicial da busca. O resultado deste algoritmo serão os
tempos de descoberta de cada vértice (d) e o pai de cada vértice (π);

## - Árvore Geradora Mínima (AGM)
O algoritmo de árvore geradora mínima. O
usuário deverá informar o vértice inicial da busca. O resultado deste
algoritmo será as arestas que compõem a AGM do grafo.

## - Caminho Mínimo
O algoritmo de caminho mínimo entre dois vértices. O
usuário deverá informar o vértice inicial (s) e o vértice final (t). Os resultados
deste algoritmo serão as arestas que compõem o caminho mínimo entres os
vértices s e t;

## - Fluxo Máximo
O algoritmo que identifica o fluxo máximo em grafo ponderado
orientado.

## 4 - Exemplo
### Entrada:
5
0 0-40; 1-50;
1 1-45; 2-4;
2 3-1; 0-99;
3 1-42; 4-7;
4 3-78; 0-32;


### Saída:

![image](https://github.com/pedrogalhardi/Projeto_Analise_Algoritmo/assets/61712871/a29924dc-6f63-45a4-9460-9fbe67668a53)
