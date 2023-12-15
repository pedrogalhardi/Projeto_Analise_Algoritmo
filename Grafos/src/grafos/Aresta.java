/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

/**
 *
 * @author humberto e douglas
 */

public class Aresta {

    private Vertice origem;
    private Vertice destino;
    private double peso;
    private String tipoAresta = "";

    public Aresta(Vertice origem, Vertice destino) {
        this.origem = origem;
        this.destino = destino;
        this.peso = 1;
    }

    public void setTipoAresta(String tipo) {
        this.tipoAresta = tipo;
    }

    public Aresta(Vertice origem, Vertice destino, double peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice origem() {
        return origem;
    }

    public void setarOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice destino() {
        return destino;
    }

    public void setarDestino(Vertice destino) {
        this.destino = destino;
    }

    public double peso() {
        return peso;
    }

    public void setarPeso(double peso) {
        this.peso = peso;
    }

    Object vertice1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object vertice2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
