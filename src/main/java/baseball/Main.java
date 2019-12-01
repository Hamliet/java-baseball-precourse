package baseball;

import java.util.Scanner;

public class Main {
	private static final int MAX_ARR_SIZE = 10; // 10���� (0~9)
	private static final int MAX_ANSWER_SIZE = 3; // ���� �迭�� ũ��
	private static final int USED = 1; // �̻�� = 0, ��� = 1
	private static final Scanner scanner = new Scanner(System.in);
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
		Answer nowAnswer;
		int userInput;
		do {
			nowAnswer = new Answer();
			System.out.print("���ڸ� �Է����ּ��� : ");
			userInput = scanner.nextInt();
			checkAnswer(nowAnswer, userInput);
			printResult(nowAnswer);
		} while (checkRoundEnd(nowAnswer.Strike));
	}

	private static void checkAnswer(Answer nowAnswer, int userInput) {
		int numberIndexDivider = 1;
		answerNumberCount = 0;
		while (answerNumberCount < MAX_ANSWER_SIZE) {
			countStrikeAndBall(nowAnswer, userInput, numberIndexDivider);
			numberIndexDivider *= 10;
			answerNumberCount++;
		}
	}

	private static void countStrikeAndBall(Answer nowAnswer, int userInput, int numberIndexDivider) {
		if ((numberUsed[(userInput % (numberIndexDivider * 10)) / numberIndexDivider] == USED)
				&& (answer[MAX_ANSWER_SIZE - 1 - answerNumberCount] == (userInput % (numberIndexDivider * 10))
						/ numberIndexDivider)) {
			nowAnswer.Strike++;
		} else if (numberUsed[(userInput % (numberIndexDivider * 10)) / numberIndexDivider] == USED) {
			nowAnswer.Ball++;
		}
	}

	private static void printResult(Answer nowAnswer) {
		if (nowAnswer.Strike > 0) {
			System.out.print(nowAnswer.Strike + " ��Ʈ����ũ ");
		}
		if (nowAnswer.Ball > 0) {
			System.out.print(nowAnswer.Ball + " ��");
		}
		if (!(nowAnswer.Strike > 0 || nowAnswer.Ball > 0)) {
			System.out.print("����");
		}
		System.out.println();
	}

	private static boolean checkRoundEnd(int nowStrike) {
		return nowStrike != MAX_ANSWER_SIZE;
	}

	private static boolean checkRestart() {
		return false;
	}
}