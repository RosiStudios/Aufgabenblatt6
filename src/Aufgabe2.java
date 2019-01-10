/*
    Aufgabe 2) Zweidimensionale Arrays und Methoden - Vier Gewinnt
*/

import com.sun.glass.ui.Size;

import javax.xml.transform.Source;
import java.awt.*;

public class Aufgabe2 {
    
    private static int[][] genGameBoard(int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
       return new int[x][y];
    }
    
    private static void drawGameBoard(int[][] currentGameBoard, int oneSquareSize) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        StdDraw.clear();
        int x,y;
        x=oneSquareSize/2;
        y=oneSquareSize/2;
        for (int i = 0; i < currentGameBoard.length; i++) {
            for (int j = 0; j < currentGameBoard[i].length; j++) {
                StdDraw.setPenColor(Color.blue);
                StdDraw.filledRectangle(x+oneSquareSize*i,y+oneSquareSize*j,oneSquareSize/2,oneSquareSize/2);
                StdDraw.setPenColor(getCurrColor(currentGameBoard[i][j]));
                StdDraw.filledCircle(x+oneSquareSize*i,y+oneSquareSize*j,oneSquareSize/3);
            }            
        }
    }

    private static Color getCurrColor(int i) {
        if(i==0)return Color.gray;
        if(i==1)return Color.red;
        if(i==2)return Color.yellow;
        return Color.magenta;
    }

    private static boolean move(int[][] currentGameBoard, int player, int col) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        for (int i = 0; i < currentGameBoard[col].length; i++) {
            if(currentGameBoard[col][i]==0){
                currentGameBoard[col][i]=player;
                return true;
            }
        }
        return false;
    }
    
    private static boolean checkGameStatus(int[][] currentGameBoard, int player) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        //vertical wincondition
        for (int i = 0; i < currentGameBoard.length; i++) {
            for (int j = 0; j < currentGameBoard[i].length-3; j++) {
                if(currentGameBoard[i][j]==player&&currentGameBoard[i][j+1]==player&&currentGameBoard[i][j+2]==player&&currentGameBoard[i][j+3]==player)return true;
            }
        }
        //horizontal wincondition
        for (int i = 0; i < currentGameBoard.length-3; i++) {
            for (int j = 0; j < currentGameBoard[i].length; j++) {
                if(currentGameBoard[i][j]==player&&currentGameBoard[i+1][j]==player&&currentGameBoard[i+2][j]==player&&currentGameBoard[i+3][j]==player)return true;
            }
        }
        //diagonal right
        for (int i = 0; i < currentGameBoard.length-3; i++) {
            for (int j = 0; j < currentGameBoard[i].length-3; j++) {
                if(currentGameBoard[i][j]==player&&currentGameBoard[i+1][j+1]==player&&currentGameBoard[i+2][j+2]==player&&currentGameBoard[i+3][j+3]==player)return true;
            }
        }
        //diagonal left
        for (int i = 0; i < currentGameBoard.length-3; i++) {
            for (int j = currentGameBoard[i].length-1; j >3; j--) {
                if(currentGameBoard[i][j]==player&&currentGameBoard[i+1][j-1]==player&&currentGameBoard[i+2][j-2]==player&&currentGameBoard[i+3][j-3]==player)return true;
            }
        }
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

        StdDraw.setFont(new Font("Arial",Font.BOLD,30));


        System.out.println("Player " + player + (player == 2 ? " (RED)" : " (YELLOW)") + " has to make a move!");
        while (!fullBoard && !isWon) {
            if (StdDraw.isMousePressed()) {
                // TODO: Implementieren Sie hier die fehlende Spiellogik
                int col = getCurrCol(StdDraw.mouseX(),colsGameBoard,oneSquareSize);
                System.out.println("Player " + player + (player == 2 ? " (RED)" : " (YELLOW)") + " has to make a move!");
                if(col>=0&&move(myGameBoard,player,col)){
                    fieldsUsed++;
                drawGameBoard(myGameBoard,oneSquareSize);

                if(checkGameStatus(myGameBoard,player)){
                    isWon=true;
                    StdDraw.setPenColor(Color.green);
                    StdDraw.text(175,150,""+((player==1)?"RED ":"YELLOW ")+"Won!");
                }
                else { fullBoard=fieldsUsed==(rowsGameBoard*colsGameBoard);
                    if(fullBoard)StdDraw.text(175, 150, "Board is full!");
                }
                player= player==1?2:1;
                }else {
                    StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                    StdDraw.text(175, 150, "Column already full!");
                }

                StdDraw.pause(500);
            }
        }
    }

    private static int getCurrCol(double mouseX,double cols,double squareSize) {
        for (double i = 0; i < cols; i++) {
            if(mouseX<(i+1)*squareSize)return (int)i;
        }
        return -1;
    }
}



