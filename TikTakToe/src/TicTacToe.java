import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// 
//      1 | 2 | 3
//     ---+---+---
//      4 | 5 | 6
//     ---+---+---
//      7 | 8 | 9
//


public class TicTacToe {

	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();

	
	public static void main(String[] args) {

		char[][] gameBoard = {
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-'},
				{ ' ', '|', ' ', '|', ' '},
				{ '-', '+', '-', '+', '-'},
				{ ' ', '|', ' ', '|', ' '}
							};
		displayGameBoard(gameBoard);
		
		
		Scanner scan = new Scanner(System.in);
		while (true) {
		System.out.println("Enter your placement (1-9): ");
		
		int playerPos = scan.nextInt();
		while (playerPositions.contains(playerPos) || 
				cpuPositions.contains(playerPos)) {
			System.out.println("Position taken! Enter a valid position.");
			playerPos = scan.nextInt();
		}
		placePiece(gameBoard, playerPos, "player");
		String result = checkWinner();
		
		if (result != "") {
			displayGameBoard(gameBoard);
			System.out.println(result);
			break;
		}
	
		Random random = new Random();
		int cpuPos = random.nextInt(9) + 1;
		while (playerPositions.contains(cpuPos) || 
				cpuPositions.contains(cpuPos)) {
			cpuPos = random.nextInt(9) + 1;
		}
		
		placePiece(gameBoard, cpuPos, "cpu");
		result = checkWinner();
		
		if (result != "") {
			displayGameBoard(gameBoard);
			System.out.println(result);
			break;
		}
	
		displayGameBoard(gameBoard);
		
		}

	}
	
	public static void displayGameBoard(char[][] gameBoard) {

		for (char[] row : gameBoard) {
			for (char x : row) {
				System.out.print(x);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		char symbol = ' ';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;	
			default:
				break;
	}
		
	}
	
	public static String checkWinner() {
		
		String result = "";
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);

		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				result = "You won! Well done.";
			} else if (cpuPositions.containsAll(l)) {
				result = "cpu won! Sorry, you lost. ";
			} else if (cpuPositions.size() + playerPositions.size() >= 9 &&
					!playerPositions.containsAll(l) && 
					!cpuPositions.containsAll(l) ) {
				result = "It's a tie! Wanna play again?";
			}
		}

		return result;
	}


}
