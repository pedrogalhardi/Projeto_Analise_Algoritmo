/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lucro;

/**
 *
 * @author pedro
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author pedro
 */
public class Resultado {

    static class ParLucroValor {

        float indiceLucro;
        int valorDoPrato;

        public ParLucroValor(float indiceLucro, int valorDoPrato) {
            this.indiceLucro = indiceLucro;
            this.valorDoPrato = valorDoPrato;
        }
    }

    static void calcularResultado(ArrayList<Integer> vendas, ArrayList<Integer> v, int n, int k, int m) {

        ArrayList<ParLucroValor> lucroEValor = new ArrayList<>();
        ArrayList<Float> valorPLucroOrdenados = new ArrayList<>();
        ArrayList<Integer> lucroOrdenado = new ArrayList<>();
        ArrayList<Float> indicesLucro = new ArrayList<>();
        int[] pratosSelecionados = new int[k];
        float melhorLucro = 0;

        for (int valorDoPrato : vendas) {
            int lucroDoPrato = v.get(vendas.indexOf(valorDoPrato));
            lucroOrdenado.add(lucroDoPrato);
            float indiceLucro = (float) lucroDoPrato / valorDoPrato;
            indicesLucro.add(indiceLucro);
            lucroEValor.add(new ParLucroValor(indiceLucro, valorDoPrato));
            valorPLucroOrdenados.add(indiceLucro);
        }

        Collections.sort(valorPLucroOrdenados, Collections.reverseOrder());
        Collections.sort(lucroOrdenado, Collections.reverseOrder());

        backtrack(0, pratosSelecionados, 0,  & melhorLucro, lucroEValor, vendas, v, n, k, m);

    static void backtrack(int dia, int[] pratosSelecionados, float lucroAtual, float * melhorLucro, ArrayList<ParLucroValor> lucroEValor, ArrayList<Integer> vendas, ArrayList<Integer> v, int n, int k, int m ) {
        if (dia == k) {
            if (lucroAtual >  * melhorLucro && lucroAtual <= m) {
                 * melhorLucro = lucroAtual;
                System.arraycopy(pratosSelecionados, 0, melhorCardapio, 0, k);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (vendas.get(i) <= m) {
                pratosSelecionados[dia] = i + 1;
                float lucroDoPrato = v.get(i);
                if (dia > 0 && pratosSelecionados[dia - 1] == pratosSelecionados[dia]) {
                    lucroDoPrato *= 1.5;
                }
                backtrack(dia + 1, pratosSelecionados, lucroAtual + lucroDoPrato, melhorLucro, lucroEValor, vendas, v, n, k, m);
            }
        }
    }

    if (k% 2 == 0) {
            int[] aux = pratosSelecionados;
        int index = pratosSelecionados[pratosSelecionados.length - 1];

        if (index - 1 > 0) {
            for (int lucroC : lucroOrdenado) {
                float somatorio = (melhorLucro - v.get(index - 1)) + lucroC;
                aux[pratosSelecionados.length - 1] = lucroOrdenado.indexOf(lucroC) + 1;
                int somaVenda = 0;
                for (int valor : aux) {
                    somaVenda += vendas.get(valor - 1);
                }
                if (v.get(index - 2) != lucroC && somatorio > melhorLucro && somaVenda <= m) {
                    pratosSelecionados[pratosSelecionados.length - 1] = lucroOrdenado.indexOf(lucroC) + 1;
                    melhorLucro = somatorio;
                }
            }
        }
    }

    System.out.println (melhorLucro);

    if (melhorLucro!= 0) {
            for (int numPrato : pratosSelecionados) {
            System.out.print(" " + numPrato + " ");
        }
        System.out.println("\n");
        System.out.println("----------------");
    }
}
}
