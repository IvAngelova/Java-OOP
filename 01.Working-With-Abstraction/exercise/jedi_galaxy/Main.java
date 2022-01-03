package T01_WorkingWithAbstraction.exercise.jedi_galaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        fillMatrix(rows, cols, matrix);

        String command = scanner.nextLine();
        long sumStars = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] playerPosition = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilPosition = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int evilRow = evilPosition[0];
            int evilCol = evilPosition[1];

            while (evilRow >= 0 && evilCol >= 0) {
                if (isInMatrix(matrix, evilRow, evilCol)) {
                    matrix[evilRow][evilCol] = 0;
                }
                evilRow--;
                evilCol--;
            }

            int playerRow = playerPosition[0];
            int playerCol = playerPosition[1];

            while (playerRow >= 0 && playerCol < matrix[1].length) {
                if (isInMatrix(matrix, playerRow, playerCol)) {
                    sumStars += matrix[playerRow][playerCol];
                }
                playerCol++;
                playerRow--;
            }

            command = scanner.nextLine();
        }

        System.out.println(sumStars);
    }

    private static boolean isInMatrix(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    private static void fillMatrix(int rows, int cols, int[][] matrix) {
        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
    }
}
