package baseball;

public class Main {
	private static final int MAX_ARR_SIZE = 10; // 10진법 (0~9)
	private static final int MAX_ANSWER_SIZE = 3; // 정답 배열의 크기
	private static final int USED = 1; // 미사용 = 0, 사용 = 1
	private static int[] numberUsed;
	private static int[] answer;
	private static int answerNumberCount;

	public static void main(String args[]) {
		do {
			makeNewAnswer();
			gameStart();
		} while (checkRestart());
	}

	private static void makeNewAnswer() {
		int newNumber;
		numberUsed = new int[MAX_ARR_SIZE];
		answer = new int[MAX_ANSWER_SIZE];
		answerNumberCount = 0;
		while (answerNumberCount < MAX_ANSWER_SIZE) {
			newNumber = (int) (((Math.random() * 10) % 9) + 1);
			checkNewNumber(newNumber);
		}
	}
	
	private static void checkNewNumber(int newNumber) {
		if (numberUsed[newNumber] != USED) {
			numberUsed[newNumber] = USED;
			answer[answerNumberCount++] = newNumber;
		}
	}

	private static void gameStart() {
	}
	
	private static boolean checkRestart() {
		return false;
	}
}