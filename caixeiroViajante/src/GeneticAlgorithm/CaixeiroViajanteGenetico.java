/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GeneticAlgorithm;

import caixeiroviajante.AdjMatrix;
import caixeiroviajante.FileManager;
import caixeiroviajante.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author pedro
 */
public class CaixeiroViajanteGenetico {

    private static final int NUM_CITIES = 200;
    private static int[][] population;
    private static final int POPULATION_SIZE = 1000;
    private static final double MUTATION_PROBABILITY = .2;
    private static final int NUM_GENERATIONS = 50;
    private static final int TOURNAMENT_SIZE = 50;

    static Graph graph = null;

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("src/GeneticAlgorithm/avaliacao.txt");

        int nVertex = 0;

        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0) {
                nVertex = Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
            } else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits) {
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    graph.setEdge(oriVertex, targetVertex, weight);
                }
            }
        }
        //int initialNode = 99;
        /* for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0) {
                nVertex = Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
            } else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits) {
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    graph.setEdge(oriVertex, targetVertex, weight);
                }
            }
        }*/
        criaPopulacao();
        for (int i = 0; i < NUM_GENERATIONS; i++) {
            int[][] parents = selectParents();

            int[][] children = crossover(parents);

            mutate(children);

            population = selectNextGeneration(population, children);
        }

        printBestSolution();
    }

    public static void criaPopulacao() {
        population = new int[POPULATION_SIZE][NUM_CITIES];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < NUM_CITIES; j++) {
                population[i][j] = j;
            }
            shuffle(population[i]);

        }
    }

    public static void shuffle(int[] pop) {
        Random random = new Random();
        for (int i = pop.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = pop[i];
            pop[i] = pop[j];
            pop[j] = temp;
        }
    }

// Seleciona os pais para crossover usando seleção por ranking
    private static int[][] selectParents() {
        int[][] parents = new int[POPULATION_SIZE][NUM_CITIES];

        // Criar uma lista ordenada dos índices da população
        Integer[] sortedIndices = new Integer[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            sortedIndices[i] = i;
        }

        // Ordenar a lista com base no fitness, do menor para o maior
        Arrays.sort(sortedIndices, (a, b) -> Double.compare(getFitness(population[a]), getFitness(population[b])));

        // Selecionar pais com base na posição na lista ordenada (menor fitness tem maior probabilidade)
        for (int i = 0; i < POPULATION_SIZE; i++) {
            int parentIndex = sortedIndices[i % TOURNAMENT_SIZE];
            parents[i] = population[parentIndex];
        }

        return parents;
    }

    // Seleciona um subconjunto aleatório da população fornecida
    private static int[][] selectRandomSubset(int[][] population, int subsetSize) {
        Random random = new Random();

        //int subsetSize = (int) (POPULATION_SIZE * 0.2);
        int[][] subset = new int[subsetSize][NUM_CITIES];
        for (int i = 0; i < subsetSize; i++) {
            int index = random.nextInt(POPULATION_SIZE);
            subset[i] = population[index];
        }
        return subset;
    }

    // Retorna o melhor indivíduo na população fornecida
    private static int[] getBestIndividual(int[][] population) {
        int bestIndividualIndex = 0;
        int bestFitness = getFitness(population[bestIndividualIndex]);
        for (int i = 1; i < population.length; i++) {
            int fitness = getFitness(population[i]);
            if (fitness < bestFitness) {
                bestIndividualIndex = i;
                bestFitness = fitness;
            }
        }
        return population[bestIndividualIndex];
    }

    // Calcula o fitness do indivíduo fornecido
    private static int getFitness(int[] individual) {
        int fitness = 0;
        for (int i = 0; i < NUM_CITIES; i++) {
            int city1 = individual[i];
            int city2 = individual[(i + 1) % NUM_CITIES];
            fitness += graph.getAdjacencyMatrix()[city1][city2];
        }
        return fitness;
    }

    public static boolean isInSubsequence(int city, int[] child, int startPos, int endPos) {
        for (int i = startPos; i <= endPos; i++) {
            if (child[i] == city) {
                return true;
            }
        }
        return false;
    }

    // Realiza o crossover nos pais fornecidos para produzir os filhos
    public static int[][] crossover(int[][] parents) {
        int[][] children = new int[2][NUM_CITIES];

        int crossoverPoint = new Random().nextInt(NUM_CITIES);

        for (int i = 0; i < crossoverPoint; i++) {
            children[0][i] = parents[0][i];
        }

        for (int i = crossoverPoint; i < NUM_CITIES; i++) {
            children[0][i] = parents[1][i];
        }

        for (int i = 0; i < crossoverPoint; i++) {
            children[1][i] = parents[1][i];
        }

        for (int i = crossoverPoint; i < NUM_CITIES; i++) {
            children[1][i] = parents[0][i];
        }

        return children;
    }

    // Realiza a mutação nos filhos fornecidos
    private static void mutate(int[][] children) {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            if (random.nextDouble() < MUTATION_PROBABILITY) {
                int city1 = random.nextInt(NUM_CITIES);
                int city2 = random.nextInt(NUM_CITIES);
                int temp = children[i][city1];
                children[i][city1] = children[i][city2];
                children[i][city2] = temp;
            }
        }
    }

    // Seleciona os melhores indivíduos da população atual e dos filhos
    // para formar a nova população
    public static int[][] selectNextGeneration(int[][] currentPopulation, int[][] children) {
        int[][] nextGeneration = new int[currentPopulation.length][NUM_CITIES];

        for (int i = 0; i < currentPopulation.length; i++) {
            nextGeneration[i] = currentPopulation[i];
        }

        for (int i = 0; i < children.length; i++) {
            nextGeneration[currentPopulation.length - children.length + i] = children[i];
        }

        Arrays.sort(nextGeneration, (a, b) -> -Double.compare(getFitness(a), getFitness(b)));

        return Arrays.copyOfRange(nextGeneration, 0, currentPopulation.length);
    }

    private static void printBestSolution() {
        int[] bestIndividual = getBestIndividual(population);
        int fitness = getFitness(bestIndividual);
        System.out.print("Caminho: ");

        for (int cidade : bestIndividual) {
            System.out.printf("-" + cidade + "-");
        }
        System.out.println("Fitness: " + fitness);

    }

}
