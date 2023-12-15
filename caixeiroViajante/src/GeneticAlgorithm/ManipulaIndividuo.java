/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneticAlgorithm;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class ManipulaIndividuo {

    private Random aleatorio;

    public ManipulaIndividuo() {
        this.aleatorio = new Random(System.nanoTime());
    }

    public void criarIndividuoAleatorio(Individuo individuo) {
        for (int i = 0; i < individuo.getTamanho(); i++) {
            int posicao = 0;
            do {
                posicao = aleatorio.nextInt(individuo.getTamanho());
            } while (individuo.getGenes()[posicao] != -1);
            individuo.setGene(posicao, i);
        }
    }

}
