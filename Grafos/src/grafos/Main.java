/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grafos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bom dia, boa tarde ou boa noite, escolha o tipo de armazenamento das estruturas: ");
        System.out.println("1. Matriz de Incidencia");
        System.out.println("2. Matriz de Adjacencia");
        System.out.println("3. Lista de Adjacencia");

        int tipoGrafo = scanner.nextInt();
        System.out.println("Agora, escolha o tipo de algoritmo: ");
        System.out.println("1. Busca em Profundidade");
        System.out.println("2. Busca em Largura");
        System.out.println("3. Menor Caminho");
        System.out.println("4. Arvore Geradora Minima");
        System.out.println("5. Fluxo Maximo");

        int opcao = scanner.nextInt();

        Algoritmos algo = new Algoritmos();
        FileManager fileM = new FileManager();
        String path = "src\\grafos\\avaliacao.txt";
        ArrayList<String> vetorGrafo = fileM.stringReader(path);
        int qtdGrafo = Integer.parseInt(vetorGrafo.get(0));

        if (tipoGrafo == 1) {
            InciMatrix grap3 = new InciMatrix(qtdGrafo);
            // Resto do código para criar o grafo InciMatrix
            for (int i = 1; i <= qtdGrafo; i++) {
                int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
                grap3.addVertice(id);
            }
            for (int i = 1; i <= qtdGrafo; i++) {
                String[] vetorSplit = vetorGrafo.get(i).split(" ");
                int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
                for (int j = 1; j < vetorSplit.length; j++) {
                    if (vetorSplit[j].equals(";")) {
                        continue;
                    }
                    String[] arestas = vetorSplit[j].split("-");
                    int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                    double peso = Integer.parseInt(arestas[1].replace(";", "")); // 1 é o peso
                    int cmp = i - 1;
                    grap3.adicionarAresta(id, pos, peso);
                }
            }
            if (opcao == 1) {
                // Resultado Busca em Profundidade
                String tempos = algo.buscaEmProfundidade(grap3);
                System.out.println("Resultado da Busca em Profundidade:");
                System.out.println("Tempo Inicial / Tempo Finalizacao");
                System.out.println(tempos);
            } else if (opcao == 2) {
                // Lógica para Busca em Largura
                System.out.println("Por favor, insira o numero do Vertice de partida:");
                int posVertice = scanner.nextInt();
                ArrayList<Vertice> vertices = grap3.vertices();
                if (posVertice >= 0 && posVertice < vertices.size()) {
                    String tempos = algo.buscaEmLargura(grap3, vertices.get(posVertice));
                    System.out.println("Resultado da Busca em Largura a partir do Vertice " + posVertice + ":");
                    System.out.println(tempos);
                } else {
                    System.out.println("Vertice de partida invalido.");
                }
            } else if (opcao == 3) {
                // Lógica para Menor Caminho
                System.out.println("Por favor, insira o numero do Vertice de origem:");
                int origem = scanner.nextInt();

                System.out.println("Por favor, insira o numero do Vertice de destino:");
                int destino = scanner.nextInt();

                ArrayList<Vertice> vertices = grap3.vertices();
                if (origem >= 0 && origem < vertices.size() && destino >= 0 && destino < vertices.size()) {
                    String caminho = algo.menorCaminho(grap3, vertices.get(origem), vertices.get(destino));
                    System.out.println("Menor caminho entre Vertice " + origem + " e Vertice " + destino + ":");
                    System.out.println(caminho);
                } else {
                    System.out.println("Vertices de origem ou destino invalidos.");
                }
            } else if (opcao == 4) {
                // Lógica para AGM usando Kruskal
                int posVertice = 0;
                ArrayList<Vertice> vertices = grap3.vertices();
                if (posVertice >= 0 && posVertice < vertices.size()) {
                    String arestas = algo.agmUsandoKruskall(grap3, vertices.get(posVertice));
                    System.out.println("Resultado do AGM usando Kruskall a partir do Vertice "
                            + posVertice + ":");
                    System.out.println(arestas);
                } else {
                    System.out.println("Vertice de partida invalido.");
                }

            } else if (opcao == 5) {
                // Lógica para Fluxo Maximo
                System.out.println("Por favor, insira o numero do Vertice de origem:");
                int origem = scanner.nextInt();

                System.out.println("Por favor, insira o numero do Vertice de destino:");
                int destino = scanner.nextInt();

                ArrayList<Vertice> vertices = grap3.vertices();
                if (origem >= 0 && origem < vertices.size() && destino >= 0 && destino < vertices.size()) {
                    int fluxoMaximo = algo.fluxoMaximo(grap3, origem, destino);
                    System.out.println("Fluxo Maximo entre Vertice " + origem + " e Vertice " + destino + ":");
                    System.out.println(fluxoMaximo);
                } else {
                    System.out.println("Vertices de origem ou destino invalidos.");
                }
            }
        } else if (tipoGrafo == 2) {
            // Lógica para GrafoMatrixAdj
            GrafoMatrixAdj grap3 = new GrafoMatrixAdj(qtdGrafo);

            for (int i = 1; i <= qtdGrafo; i++) {
                int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
                grap3.addVertice(id);
            }
            for (int i = 1; i <= qtdGrafo; i++) {
                String[] vetorSplit = vetorGrafo.get(i).split(" ");
                int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
                for (int j = 1; j < vetorSplit.length; j++) {
                    String[] arestas = vetorSplit[j].split("-");

                    int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                    double peso = Integer.parseInt(arestas[1].replace(";", "")); // 1 é o peso
                    int cmp = i - 1;

                    grap3.adicionarAresta(id, pos, peso);

                }
            }

            if (opcao == 1) {
                // Resultado da busca em profundidade
                String tempos = algo.buscaEmProfundidade(grap3);
                System.out.println("Resultado da Busca em Profundidade:");
                System.out.println("Tempo Inicial / Tempo Finalizacao");
                System.out.println(tempos);
            } else if (opcao == 2) {
                // Lógica para Busca em Largura
                System.out.println("Por favor, insira o numero do Vertice de partida:");
                int posVertice = scanner.nextInt();
                ArrayList<Vertice> vertices = grap3.vertices();
                if (posVertice >= 0 && posVertice < vertices.size()) {
                    String tempos = algo.buscaEmLargura(grap3, vertices.get(posVertice));
                    System.out.println("Resultado da Busca em Largura a partir do Vertice " + posVertice + ":");
                    System.out.println(tempos);
                } else {
                    System.out.println("Vertice de partida invalido.");
                }
            } else if (opcao == 3) {
                // Lógica para Menor Caminho
                System.out.println("Por favor, insira o numero do Vertice de origem:");
                int origem = scanner.nextInt();

                System.out.println("Por favor, insira o numero do Vertice de destino:");
                int destino = scanner.nextInt();

                ArrayList<Vertice> vertices = grap3.vertices();
                if (origem >= 0 && origem < vertices.size() && destino >= 0 && destino < vertices.size()) {
                    String caminho = algo.menorCaminho(grap3, vertices.get(origem), vertices.get(destino));
                    System.out.println("Menor caminho entre Vertice " + origem + " e Vertice " + destino + ":");
                    System.out.println(caminho);
                } else {
                    System.out.println("Vertices de origem ou destino invalidos.");
                }
            } else if (opcao == 4) {
                // Lógica para AGM usando Kruskal

                int posVertice = 0;
                ArrayList<Vertice> vertices = grap3.vertices();
                if (posVertice >= 0 && posVertice < vertices.size()) {
                    String arestas = algo.agmUsandoKruskall(grap3, vertices.get(posVertice));
                    System.out.println("Resultado do AGM usando Kruskall apartir do Vertice " + posVertice + ":");
                    System.out.println(arestas);
                } else {
                    System.out.println("Vertice de partida invalido.");
                }
            } else if (opcao == 5) {
                // Lógica para Fluxo Maximo
                System.out.println("Por favor, insira o numero do Vertice de origem:");
                int origem = scanner.nextInt();

                System.out.println("Por favor, insira o numero do Vertice de destino:");
                int destino = scanner.nextInt();

                ArrayList<Vertice> vertices = grap3.vertices();
                if (origem >= 0 && origem < vertices.size() && destino >= 0 && destino < vertices.size()) {
                    int fluxoMaximo = algo.fluxoMaximo(grap3, origem, destino);
                    System.out.println("Fluxo Maximo entre Vertice " + origem + " e Vertice " + destino + ":");
                    System.out.println(fluxoMaximo);
                } else {
                    System.out.println("Vertices de origem ou destino invalidos.");
                }
            }

        } else if (tipoGrafo == 3) {
            // Lógica para ListAdj
            ListAdj graph2 = new ListAdj(qtdGrafo);
            for (int i = 1; i <= qtdGrafo; i++) {
                int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
                graph2.addVertice(id);
            }
            for (int i = 1; i <= qtdGrafo; i++) {
                String[] vetorSplit = vetorGrafo.get(i).split(" ");
                int id = Integer.parseInt(vetorGrafo.get(i).split(" ")[0]);
                for (int j = 1; j < vetorSplit.length; j++) {
                    String[] arestas = vetorSplit[j].split("-");

                    int pos = Integer.parseInt(arestas[0]); // 0 é a posição
                    double peso = Integer.parseInt(arestas[1].replace(";", "")); // 1 é o peso
                    int cmp = i - 1;
                    graph2.adicionarAresta(id, pos, peso);

                }
            }

            if (opcao == 1) {
                // Resultado da busca em profundidade
                String tempos = algo.buscaEmProfundidade(graph2);
                System.out.println("Resultado da Busca em Profundidade:");
                System.out.println("Tempo Inicial / Tempo Finalizacao");
                System.out.println(tempos);
            } else if (opcao == 2) {
                // Lógica para Busca em Largura
                System.out.println("Por favor, insira o numero do Vertice de partida:");
                int posVertice = scanner.nextInt();
                ArrayList<Vertice> vertices = graph2.vertices();
                if (posVertice >= 0 && posVertice < vertices.size()) {
                    String tempos = algo.buscaEmLargura(graph2, vertices.get(posVertice));
                    System.out.println("Resultado da Busca em Largura a partir do Vertice " + posVertice + ":");
                    System.out.println(tempos);
                } else {
                    System.out.println("Vertice de partida invalido.");
                }
            } else if (opcao == 3) {
                // Lógica para Menor Caminho
                System.out.println("Por favor, insira o numero do Vertice de origem:");
                int origem = scanner.nextInt();

                System.out.println("Por favor, insira o numero do Vertice de destino:");
                int destino = scanner.nextInt();

                ArrayList<Vertice> vertices = graph2.vertices();
                if (origem >= 0 && origem < vertices.size() && destino >= 0 && destino < vertices.size()) {
                    String caminho = algo.menorCaminho(graph2, vertices.get(origem), vertices.get(destino));
                    System.out.println("Menor caminho entre Vertice " + origem + " e Vertice " + destino + ":");
                    System.out.println(caminho);
                } else {
                    System.out.println("Vertices de origem ou destino invalidos.");
                }

            } else if (opcao == 4) {
                // Lógica para AGM usando Kruskal

                int posVertice = 0;
                ArrayList<Vertice> vertices = graph2.vertices();
                if (posVertice >= 0 && posVertice < vertices.size()) {
                    String arestas = algo.agmUsandoKruskall(graph2, vertices.get(posVertice));
                    System.out.println("Resultado do AGM usando Kruskall apartir do Vertice "
                            + posVertice + ":");
                    System.out.println(arestas);

                } else {
                    System.out.println("Vertice de partida invalido.");
                }

            } else if (opcao == 5) {
                // Lógica para Fluxo Maximo
                System.out.println("Por favor, insira o numero do Vertice de origem:");
                int origem = scanner.nextInt();

                System.out.println("Por favor, insira o numero do Vertice de destino:");
                int destino = scanner.nextInt();

                ArrayList<Vertice> vertices = graph2.vertices();
                if (origem >= 0 && origem < vertices.size() && destino >= 0 && destino < vertices.size()) {
                    int fluxoMaximo = algo.fluxoMaximo(graph2, origem, destino);
                    System.out.println("Fluxo Maximo entre Vertice " + origem + " e Vertice " + destino + ":");
                    System.out.println(fluxoMaximo);
                } else {
                    System.out.println("Vertices de origem ou destino invalidos.");
                }
            }
        }

        scanner.close();
    }
}
