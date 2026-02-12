// Let's play a game on an array! You're standing at index  of an -element array named . From some index  (where ), you can perform one of the following moves:

// Move Backward: If cell  exists and contains a , you can walk back to cell .
// Move Forward:
// If cell  contains a zero, you can walk to cell .
// If cell  contains a zero, you can jump to cell .
// If you're standing in cell  or the value of , you can walk or jump off the end of the array and win the game.
// In other words, you can move from index  to index , , or  as long as the destination index is a cell containing a . If the destination index is greater than , you win the game.

// Function Description

// Complete the canWin function in the editor below.

// canWin has the following parameters:

// int leap: the size of the leap
// int game[n]: the array to traverse
// Returns

// boolean: true if the game can be won, otherwise false

import java.util.*;

public class Java1dArrayP2 {

    public static boolean canWin(int leap, int[] game) {
        boolean[] visited = new boolean[game.length];
        return canWinFrom(0, leap, game, visited);
    }

    private static boolean canWinFrom(int i, int leap, int[] game, boolean[] visited) {
        if (i >= game.length) return true;
        if (i < 0 || game[i] == 1 || visited[i]) return false;

        visited[i] = true;

        return canWinFrom(i + leap, leap, game, visited) ||
               canWinFrom(i + 1, leap, game, visited) ||
               canWinFrom(i - 1, leap, game, visited);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println((canWin(leap, game)) ? "YES" : "NO");
        }
        scan.close();
    }
}
