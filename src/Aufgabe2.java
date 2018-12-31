/*
    Aufgabe 2) Zweidimensionale Arrays und Methoden - Vier Gewinnt
*/

import java.awt.*;

public class Aufgabe2 {
    
    private static int[][] genGameBoard(int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return null;
    }
    
    private static void drawGameBoard(int[][] currentGameBoard, int oneSquareSize) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
    }
    
    private static boolean move(int[][] currentGameBoard, int player, int col) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return false;
    }
    
    private static boolean checkGameStatus(int[][] currentGameBoard, int player) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return false;
    }
    
    public static void main(String[] args) {
        
        // canvas settings
        int rowsGameBoard = 6;
        int colsGameBoard = 7;
        int oneSquareSize = 50;
        int width = oneSquareSize * colsGameBoard;
        int height = oneSquareSize * rowsGameBoard;
        
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        
        // game variables
        int[][] myGameBoard = genGameBoard(colsGameBoard, rowsGameBoard);
        boolean fullBoard = false;
        boolean isWon = false;
        int player = 1;
        int fieldsUsed = 0;
        
        // initial draw of the game board
        drawGameBoard(myGameBoard, oneSquareSize);
        
        // game play starts
        System.out.println("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " has to make a move!");
        while (!fullBoard && !isWon) {
            if (StdDraw.isMousePressed()) {
                // TODO: Implementieren Sie hier die fehlende Spiellogik
            }
        }
    }
}



