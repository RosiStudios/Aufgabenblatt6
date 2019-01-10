/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Mini-Paint
*/

import sun.java2d.loops.DrawLine;

import java.awt.*;
import java.util.ArrayList;

public class Aufgabe1 {

    private static void floodFill(int[][] picArray, int sx, int sy) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int stupidSy = picArray.length - 1 - sy;
        if (picArray[stupidSy][sx] == 0) {
            StdDraw.point(sx, sy);
            picArray[stupidSy][sx] = 1;
            if(stupidSy!=0)floodFill(picArray,sx,sy+1);
            if(stupidSy!=picArray.length-1)floodFill(picArray,sx,sy-1);
            if(sx!=0)floodFill(picArray,sx-1,sy);
            if(sx!=picArray[0].length)floodFill(picArray,sx+1,sy);
        }
    }


    private static void paintLine(int[][] picArray, int[] xClick, int[] yClick) {
        int x0 = xClick[0];
        int x1 = xClick[1];
        int y0 = yClick[0];
        int y1 = yClick[1];

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int xd = x0 < x1 ? 1 : -1;
        int yd = y0 < y1 ? 1 : -1;

        int e = dx - dy;
        int et;

        picArray[picArray.length - 1 - y0][x0] = 1;
        StdDraw.point(x0, y0);
        System.out.println("Point: x:" + x0 + " y:" + y0);

        while (x0 != x1 || y0 != y1) {
            et = 2 * e;
            if (et > -dy) {
                e -= dy;
                x0 += xd;
            }
            if (et < dx) {
                e += dx;
                y0 += yd;
            }
            picArray[picArray.length - 1 - y0][x0] = 1;
            StdDraw.point(x0, y0);
            System.out.println("Point: x:" + x0 + " y:" + y0);
        }
    }

    public static void main(String[] args) {
        int width = 300;
        int height = 250;
        int squareSize = 50;
        StdDraw.setCanvasSize(width - 1, height - 1);
        StdDraw.setXscale(0, width - 1);
        StdDraw.setYscale(0, height - 1);
        StdDraw.setPenRadius(0.001);

        //draw color buttons
        Color[] colors = new Color[]{StdDraw.RED, StdDraw.GREEN, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.CYAN};

        for (int i = 0; i < colors.length; i++) {
            StdDraw.setPenColor(colors[i]);
            StdDraw.filledSquare(width - squareSize / 2 - 1, squareSize / 2 + i * squareSize, squareSize / 2);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.0082);
        StdDraw.line(width - squareSize + 1, 0, width - squareSize + 1, height - 1);
        for (int i = 1; i < 5; i++) {
            StdDraw.line(width - squareSize + 1, i * squareSize - 1, width - 1, i * squareSize - 1);
        }
        StdDraw.setPenRadius(0.001);

        int[] xClick = new int[2];
        int[] yClick = new int[2];
        int[][] picArray = new int[height][width - squareSize];
        boolean isRunning = true;
        boolean isDrawing = true;
        StdDraw.enableDoubleBuffering();
        xClick[0] = 0;
        yClick[0] = 0;

        while (isRunning) {
            if (StdDraw.isMousePressed()) {
                // TODO: Implementieren Sie hier die fehlende Funktionalität für das Mini-Paint
                if (StdDraw.mouseX() <= 250 && isDrawing) {
                    xClick[1] = (int) StdDraw.mouseX();
                    yClick[1] = (int) StdDraw.mouseY();
                    paintLine(picArray, xClick, yClick);
                    xClick[0] = xClick[1];
                    yClick[0] = yClick[1];
                    StdDraw.show();
                    StdDraw.pause(100);


                } else if (StdDraw.mouseX() <= 250) {
                    floodFill(picArray, (int) StdDraw.mouseX(), (int) StdDraw.mouseY());
                    StdDraw.show();
                    StdDraw.pause(1000);
                    isDrawing = true;
                    StdDraw.setPenColor(Color.black);
                } else {
                    StdDraw.setPenColor(calcPenColor(StdDraw.mouseY()));
                    isDrawing = false;
                }

            }
        }

    }

    private static Color calcPenColor(double mouseY) {
        if (isBetween(mouseY, 0, 50)) return Color.RED;
        if (isBetween(mouseY, 50, 100)) return Color.GREEN;
        if (isBetween(mouseY, 100, 150)) return Color.BLUE;
        if (isBetween(mouseY, 150, 200)) return Color.YELLOW;
        if (isBetween(mouseY, 200, 250)) return Color.CYAN;
        return Color.BLACK;
    }

    private static boolean isBetween(double value, double lowerBound, double upperBound) {
        return lowerBound <= value && value <= upperBound;
    }
}


