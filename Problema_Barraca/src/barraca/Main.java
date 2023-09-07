/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package barraca;

/**
 *
 * @author pedro
 */
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;

        while (true) {
            int H = scanner.nextInt();
            int M = scanner.nextInt();

            if (H == 0 && M == 0) {
                break;
            }

            List<Point2D> homem = new ArrayList<>();
            List<Point2D> mulher = new ArrayList<>();

            for (int i = 0; i < H; i++) {
                double x1 = scanner.nextDouble();
                double y1 = scanner.nextDouble();
                double x2 = scanner.nextDouble();
                double y2 = scanner.nextDouble();

                homem.add(new Point2D.Double(x1, y1));
                homem.add(new Point2D.Double(x1, y2));
                homem.add(new Point2D.Double(x2, y1));
                homem.add(new Point2D.Double(x2, y2));
                //System.out.println(homem);
            }

            for (int i = 0; i < M; i++) {
                double x1 = scanner.nextDouble();
                double y1 = scanner.nextDouble();
                double x2 = scanner.nextDouble();
                double y2 = scanner.nextDouble();

                mulher.add(new Point2D.Double(x1, y1));
                mulher.add(new Point2D.Double(x1, y2));
                mulher.add(new Point2D.Double(x2, y1));
                mulher.add(new Point2D.Double(x2, y2));
                //System.out.println(mulher);
            }

            boolean isPossible = possivelSeparacao(homem, mulher);

            System.out.println("Caso " + caseNumber + ": "
                    + (isPossible ? "E possivel separar os dois grupos!" : "Nao e possivel separar os dois grupos!"));
            caseNumber++;
        }
    }

    private static boolean possivelSeparacao(List<Point2D> homem, List<Point2D> mulher) {
        for (Point2D man : homem) {
            for (Point2D woman : mulher) {
                Line2D line = new Line2D.Double(man, woman);
                //System.out.println(man);
                //System.out.println(woman);
                //System.out.println(line);

                boolean allhomemOnOneSide = homem.stream().allMatch(m -> line.relativeCCW(m) <= 0);
                boolean allmulherOnOtherSide = mulher.stream().allMatch(w -> line.relativeCCW(w) >= 0);

                if (allhomemOnOneSide && allmulherOnOtherSide) {
                    return true;

                }
            }
        }
        return false;

    }
}
