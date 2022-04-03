import java.util.Scanner;

public class Main {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean finished = false;
        boolean playing = true;
        String player = "X";
        int row = -1;
        int col = -1;
        final int movesForWin = 5;
        final int movesForTie = 7;
        int counter = 0;


        do {
            player = "X";
            playing = false;
            counter = 0;
            clearBoard();
            do {
                do {
                    display();
                    System.out.println("Enter a move for " + player);
                    row = SafeInput.getRangedInt(in, "Enter row [1-3] ", 1, 3);
                    col = SafeInput.getRangedInt(in, "Enter column [1-3] ", 1, 3);
                    row--; col--;
                }
                while (!isValidMove(row, col));
                board[row][col] = player;
                counter++;

                if (counter <= movesForWin) {
                    if (isWin(player)) {
                        display();
                        System.out.println("Player " + player + " wins!");
                        playing = true;
                    }
                }
                if (counter >= movesForTie) {
                    if (isTie())
                    {
                        display();
                        System.out.println("It's a tie!");
                        playing = true;
                    }
                }
                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }
            }
            while (!playing);
            finished = SafeInput.getYNConfirm(in, "Done playing?");
        }while (!finished);
    }
    /**
     * clears all spaces on the board
     */
    private static  void clearBoard()
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }
    private static void display()
    {
        for (int row = 0; row < ROW; row++)
        {
            System.out.print("| ");
            for (int col = 0; col < COL; col++)
            {
                System.out.print(board[row][col] + "| ");
            }
            System.out.println();
        }
    }
    private static boolean isValidMove(int row, int col)
    {
        boolean validMove = false;
        if(board[row][col].equalsIgnoreCase(" "))
        {
            validMove = true;
        }
        return validMove;
    }
    /**
     *
     * @param player
     * @return
     */
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }
    private static  boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) &&
                board[1][col].equals(player) &&
                board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) &&
                board[row][1].equals(player) &&
                board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static  boolean isDiagonalWin(String player)
    {
        if (board[0][0].equals(player) &&
            board[1][1].equals(player) &&
            board[2][2].equals(player))
        {
            return true;
        }
        if (board[0][2].equals(player) &&
            board[1][1].equals(player) &&
            board[2][0].equals(player))
        {
            return true;
        }
        return false;
    }
    private static boolean isTie()
    {
        boolean playerX = false;
        boolean playerO = false;

        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X"))
            {
                playerX = true;
            }
            if (board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O"))
            {
                playerO = true;
            }
            if (! (playerX && playerO))
            {
                return false;
            }
            playerX = playerO = false;

            for (int col = 0; col < COL; col++)
            {
                if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X"))
                {
                    playerX = true;
                }
                if (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O"))
                {
                    playerO = true;
                }
                if (! (playerX && playerO))
                {
                    return false;
                }
            }
            playerX = playerO = false;

            if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X"))
            {
                playerX = true;
            }
            if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O"))
            {
                playerO = true;
            }
            if (! (playerX && playerO))
            {
                return false;
            }
            playerX = playerO = false;

            if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X"))
            {
                playerX = true;
            }
            if (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O"))
            {
                playerO = true;
            }
            if (! (playerX && playerO))
            {
                return false;
            }
        }
        return true;
    }
}