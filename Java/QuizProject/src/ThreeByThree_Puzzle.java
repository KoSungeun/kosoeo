import java.util.Random;
import java.util.Scanner;

public class ThreeByThree_Puzzle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[][] puzzle = new String[3][3];
		int num = 0;
		Random r = new Random();
		String input;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				num++;
				if (num == puzzle.length * puzzle.length) {
					puzzle[i][j] = "x";
				} else {
					puzzle[i][j] = num + "";
				}
			}
		}
		
		int[] index = shuffle(puzzle, r);
		
		while (true) {
			for (int i = 0; i < puzzle.length; i++) {
				for (int j = 0; j < puzzle.length; j++) {
					System.out.print(puzzle[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("[ Move ] a:Left s:Right w:Up z:Down");
			System.out.println("[ Exit ] k:Exit");
			System.out.println("이동키를 입력하세요 :");
			input = sc.next();
			if (input.equalsIgnoreCase("k")) {
				System.out.println("게임을 종료합니다");
				break;
			}
			if (input.equalsIgnoreCase("a")) {
				index = move(puzzle, index, index[0] + 1, index[1]);
			} else if (input.equalsIgnoreCase("s")) {
				index = move(puzzle, index, index[0] - 1, index[1]);
			} else if (input.equalsIgnoreCase("w")) {
				index = move(puzzle, index, index[0], index[1] + 1);
			} else if (input.equalsIgnoreCase("z")) {
				index = move(puzzle, index, index[0], index[1] - 1);
			}
			if (checker(puzzle)) {
				System.out.println("정답입니다. 게임을 종료합니다.");
				sc.close();
				break;
			}
		}
	}

	public static int[] find(String[][] puzzle) {
		int[] index = new int[2];
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				if (puzzle[i][j].equals("x")) {
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return index;
	}

	public static int[] shuffle(String[][] puzzle, Random r) {
		int seed;
		int[] index = find(puzzle);
		for (int i = 0; i < 10000; i++) {
			seed = r.nextInt(4);
			switch (seed) {
			case 0:
				index = move(puzzle, index, index[0] + 1, index[1]);				
				break;
			case 1:
				index = move(puzzle, index, index[0] - 1, index[1]);
				break;
			case 2:
				index = move(puzzle, index, index[0], index[1] + 1);
				break;
			case 3:
				index = move(puzzle, index, index[0], index[1] - 1);
				break;
			}
		}
		return index;
	}

	public static int[] move(String[][] puzzle, int[] index, int mx, int my) {
		String temp;
		if ((mx < puzzle.length && mx >= 0) && (my < puzzle.length && my >= 0)) {
			temp = puzzle[index[1]][index[0]];
			puzzle[index[1]][index[0]] = puzzle[my][mx];
			puzzle[my][mx] = temp;
			index[0] = mx;
			index[1] = my;
			return index;
		}
		return index;
	}


	public static boolean checker(String[][] puzzle) {
		int answer = 1;
		boolean flag = true;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				if (!puzzle[i][j].equals(Integer.toString(answer))) {
					return false;
				}
				answer++;
				if (answer == puzzle.length * puzzle.length) {
					return flag;
				}
			}
		}
		return flag;
	}
}
