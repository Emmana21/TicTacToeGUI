import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private JLabel statusLabel;

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private String currentPlayer = "X";
    private int moveCount = 0;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
                int finalI = i;
                int finalJ = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isValidMove(finalI, finalJ)) {
                            buttons[finalI][finalJ].setText(currentPlayer);
                            board[finalI][finalJ] = currentPlayer;
                            moveCount++;
                            if (isWin(currentPlayer)) {
                                displayStatus("Player " + currentPlayer + " wins!");
                                disableButtons();
                            } else if (moveCount >= 9) {
                                displayStatus("It's a tie!");
                            } else {
                                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                                displayStatus("Player " + currentPlayer + "'s turn");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
                        }
                    }
                });
                panel.add(button);
                buttons[i][j] = button;
            }
        }

        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void displayStatus(String status) {
        statusLabel.setText(status);
    }

    private void disableButtons() {
        for (JButton[] buttonRow : buttons) {
            for (JButton button : buttonRow) {
                button.setEnabled(false);
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col] == null || board[row][col].trim().isEmpty();
    }

    private static boolean isWin(String player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(player) &&
                    board[i][1] != null && board[i][1].equals(player) &&
                    board[i][2] != null && board[i][2].equals(player)) {
                return true;
            }
            if (board[0][i] != null && board[0][i].equals(player) &&
                    board[1][i] != null && board[1][i].equals(player) &&
                    board[2][i] != null && board[2][i].equals(player)) {
                return true;
            }
        }
        if (board[0][0] != null && board[0][0].equals(player) &&
                board[1][1] != null && board[1][1].equals(player) &&
                board[2][2] != null && board[2][2].equals(player)) {
            return true;
        }
        return board[0][2] != null && board[0][2].equals(player) &&
                board[1][1] != null && board[1][1].equals(player) &&
                board[2][0] != null && board[2][0].equals(player);
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}