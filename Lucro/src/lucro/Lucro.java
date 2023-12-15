/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lucro;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Lucro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int k = 0;
        int n = 0;
        int m = 0;
        ArrayList<Integer> v = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();

        try {
            FileManager fileManager = new FileManager();
            ArrayList<String> arg = fileManager.stringReader("src/lucro/avaliacao.txt");

            for (String val : arg) {
                if ((val.split(" ").length == 3) && !(val.equals("0 0 0"))) {
                    v.clear();
                    c.clear();
                    String[] comando = val.split(" ");
                    k = Integer.parseInt(comando[0]);
                    n = Integer.parseInt(comando[1]);
                    m = Integer.parseInt(comando[2]);
                }

                if (val.split(" ").length == 2) {
                    String[] valores = val.split(" ");
                    c.add(Integer.valueOf(valores[0]));
                    v.add(Integer.valueOf(valores[1]));

                    if (v.size() == n) {
                        Resultado.calcularResultado(c, v, n, k, m);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
