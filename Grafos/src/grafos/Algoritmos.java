/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grafos;

import java.util.*;

/**
 *
 * @author pedro
 */
public class Algoritmos implements AlgoritmosEmGrafos {

    public int tempo = 0;

    @Override
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception {
        return null;
    }

    @Override
    public Collection<Aresta> buscaEmLargura(Grafo g) {
        return null;
    }

    public String buscaEmLargura(Grafo g, Vertice inicial) throws Exception {
        inicial.setCor("cinza");
        Queue<Vertice> q = new LinkedList<>();
        q.add(inicial);
        String tempos = "";
        while (!(q.isEmpty())) {
            Vertice u = q.remove();
            ArrayList<Vertice> adj = (ArrayList<Vertice>) g.adjacentesDe(u);

            for (Vertice v : adj) {
                if (v.getCor().equals("branco")) {
                    v.setCor("cinza");
                    v.setTempoInicial(tempo);
                    tempo++;
                    q.add(v);
                }
            }
            u.setCor("preto");
            // tempos += "(" + u.getTempo() + "),";
            tempos += "(" + u.id() + "), ";
        }

        return tempos;
    }

    public void dfsVisit(Vertice u, Grafo g) throws Exception {
        u.setCor("cinza");
        tempo++;
        u.setTempoInicial(tempo);
        ArrayList<Vertice> adjacentes = (ArrayList<Vertice>) g.adjacentesDe(u);
        for (Vertice adj : adjacentes) {
            if (adj.getCor().equals("branco")) {
                dfsVisit(adj, g);
            } else if (adj.getTempoFinal() == 0) {
                Aresta a = g.encontraAresta(u.id(), adj.id());
                a.setTipoAresta("retorno");
            } else if (u.getTempo() < adj.getTempo()) {
                Aresta a = g.encontraAresta(u.id(), adj.id());
                a.setTipoAresta("avanco");
            } else {
                Aresta a = g.encontraAresta(u.id(), adj.id());
                a.setTipoAresta("cruzamento");

            }
        }
        u.setCor("Preto");
        tempo++;
        u.setTempoFinal(tempo);
    }

    @Override
    public String buscaEmProfundidade(Grafo g) throws Exception {
        ArrayList<Aresta> visitou = new ArrayList<>();
        String tempos = "";
        for (Vertice u : g.vertices()) {
            if (u.getCor().equals("branco")) {
                dfsVisit(u, g);
            }
        }
        for (Vertice v : g.vertices()) {
            tempos += "(" + v.getTempo() + ", " + v.getTempoFinal() + ") \n";
        }
        return tempos;
    }

    public void relaxa(Vertice u, Vertice v, Aresta w, Queue<Vertice> fila) {
        if (v.getDistancia() > (u.getDistancia() + w.peso())) {
            v.setDistancia((int) (u.getDistancia() + w.peso()));
            v.setPai(u);
            fila.remove(u);
            fila.add(v);
        }
    }

    // menor caminho usando busca em largura
    @Override
    public String menorCaminho(Grafo g, Vertice origem, Vertice destino) throws Exception {
        ArrayList<Vertice> caminho = new ArrayList<>();
        String caminhoFinal = "";
        origem.setDistancia(0);
        Queue<Vertice> fila = new LinkedList<>();
        fila.add(origem);

        while (!fila.isEmpty()) {
            Vertice u = fila.poll();
            ArrayList<Vertice> adj = (ArrayList<Vertice>) g.adjacentesDe(u);
            for (Vertice v : adj) {
                Aresta w = g.arestaEntre(u, v);
                relaxa(u, v, w, fila);
            }
        }
        Vertice pai = destino.getPai();
        caminho.add(pai);
        while (pai != null) {
            pai = pai.getPai();
            caminho.add(pai);
        }
        for (Vertice v : caminho) {
            /*
             * if (v != null) {
             * caminhoFinal += "(" + v.id() + ", " + v.getDistancia() + ")" + " \n";
             * }
             */
            if (v != null && v.id() != origem.id()) {
                caminhoFinal += "(" + v.id() + ", " + v.getDistancia() + ")" + " \n";
            }

        }
        return caminhoFinal;
    }

    @Override
    public boolean existeCiclo(Grafo g) {
        return false;
    }

    @Override
    public Collection<Aresta> agmUsandoKruskall(Grafo g) {
        return null;
    }

    public int busca(int i, int[] pai) {
        while (pai[i] != i) {
            i = pai[i];

        }
        return i;
    }

    public void union(int i, int j, int[] pai) {
        int a = busca(i, pai);
        int b = busca(j, pai);
        pai[a] = b;
    }

    public String agmUsandoKruskall(Grafo g, Vertice inicial) {
        ArrayList<Aresta> agm = new ArrayList<>();
        String arestasFinais = "";
        ArrayList<Vertice> vertice = g.vertices();
        String caminho = "";
        int[] pai = new int[g.numeroDeVertices()];
        int contador = 0;
        for (int i = 0; i < g.numeroDeVertices(); i++) {
            pai[i] = i;
        }
        while (contador < g.numeroDeVertices() - 1) {
            int min = Integer.MAX_VALUE;
            int a = 0, b = 0;

            for (int i = 0; i < g.numeroDeVertices(); i++) {
                for (int j = 0; j < g.numeroDeVertices(); j++) {
                    if (busca(i, pai) != busca(j, pai) && g.getValue(i, j) < min) {
                        min = g.getValue(i, j);
                        a = i;
                        b = j;
                    }
                }
            }
            union(a, b, pai);
            arestasFinais += "(" + a + ", " + b + ") , \n";
            contador++;
        }

        return arestasFinais;
    }

    public int fluxoMaximo(Grafo g, int origem, int destino) {
        Map<Integer, Integer> idToIndex = new HashMap<>();
        int index = 0;

        // Mapeia os identificadores dos vértices para índices na matriz
        for (Vertice v : g.vertices()) {
            idToIndex.put(v.id(), index++);
        }

        int[][] graph = new int[g.numeroDeVertices()][g.numeroDeVertices()];
        // Chama o algoritmo de Fluxo Máximo
        int maxFluxo = fordFulkerson(graph, idToIndex.get(origem), idToIndex.get(destino));
        return maxFluxo;
    }

// Implementação do algoritmo Ford-Fulkerson
    private int fordFulkerson(int graph[][], int s, int t) {
        int u, v, rGraph[][] = new int[graph.length][graph.length];
        for (u = 0; u < graph.length; u++) {
            for (v = 0; v < graph.length; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        int parent[] = new int[graph.length];
        int maxFlow = 0;

        // Enquanto houver caminho aumentante
        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Atualiza as capacidades residuais
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Adiciona o fluxo do caminho ao fluxo máximo
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[rGraph.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < rGraph.length; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // Retorna verdadeiro se alcançar o destino na BFS
        return visited[t];
    }

    @Override
    public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception {
        return 0;
    }

    @Override
    public boolean ehArvoreGeradora(Grafo g, Collection<Aresta> arestas) {
        return false;
    }

    @Override
    public String caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {
        return null;
    }

    @Override
    public double custoDoCaminho(Grafo g, ArrayList<Aresta> arestas, Vertice origem, Vertice destino) throws Exception {
        return 0;
    }

    @Override
    public boolean ehCaminho(ArrayList<Aresta> arestas, Vertice origem, Vertice destino) {
        return false;
    }

    @Override
    public Collection<Aresta> arestasDeArvore(Grafo g) {
        return null;
    }

    @Override
    public Collection<Aresta> arestasDeRetorno(Grafo g) {
        return null;
    }

    @Override
    public Collection<Aresta> arestasDeAvanco(Grafo g) {
        return null;
    }

    @Override
    public Collection<Aresta> arestasDeCruzamento(Grafo g) {
        return null;
    }
}
