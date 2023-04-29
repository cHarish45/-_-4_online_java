import java.util.*;

public class OnlineExamSystem {
	private String username;
	private String password;
	private boolean isLoggedIn;
	private int timeRemaining;
	private int questionCount;
	private int[] userAnswers;
	private int[] correctAnswers;

	public OnlineExamSystem(String username, String password) {
		this.username = username;
		this.password = password;
		System.out.println("SUCESSFULLY REGISTERED...");
		this.isLoggedIn = false;
		this.timeRemaining = 10;
		this.questionCount = 10;
		this.userAnswers = new int[questionCount];
		this.correctAnswers = new int[questionCount];
		for (int i = 0; i < questionCount; i++) {
			correctAnswers[i] = (int) Math.round(Math.random());
		}
	}

	public void login() {
		System.out.println("PLEASE LOG IN TO PROCEED YOUR EXAM... ");
		Scanner scanner = new Scanner(System.in);
		System.out.print("USERNAME:-");
		String inputUsername = scanner.nextLine();
		System.out.print("PASSWORD:- ");
		String inputPassword = scanner.nextLine();
		if (inputUsername.equals(username) && inputPassword.equals(password)) {
			isLoggedIn = true;
			System.out.println("LOG IN SUCESSFULL..ALL THE BEST FOR YOUR EXAMINATION");
		} else {
			System.out.println("LOGIN FAILED..INVALID CREDENTIALS");
		}
	}

	public void logout() {
		isLoggedIn = false;
		System.out.println("LOGOUT DONE SUCESSFULLY...");
	}

	public void startExam() {
		if (!isLoggedIn) {
			System.out.println("YOU NEED TO LOG IN FIRST...");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("YOU HAVE " + timeRemaining + " MINUTES TO COMPLETE THE EXAM");
		for (int i = 0; i < questionCount; i++) {
			System.out.println("Question " + (i + 1) + ":");
			System.out.println("1. Option 1");
			System.out.println("2. Option 2");
			System.out.print("Your answer (1 or 2): ");
			int answer = scanner.nextInt();
			userAnswers[i] = answer;
		}

		System.out.println("WOULD YOU LIKE TO SUBMIT YOUR ANSWERS? \n1:Yes \n2:NO ");
		int n = scanner.nextInt();
		if (n == 1) {
			submitExam();
		} else {
			try {
				Thread.sleep(timeRemaining * 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				submitExam();
			}

		}

	}

	public void submitExam() {
		if (!isLoggedIn) {
			System.out.println("PLEASE LOG IN...");
			return;
		}
		int score = 0;
		for (int i = 0; i < questionCount; i++) {
			if (userAnswers[i] == correctAnswers[i]) {
				score++;
			}
		}
		System.out.println("YOU HAVE SCORED " + score + " OUT OFF " + questionCount + ".");
		System.out.println("THANK YOU !");
		logout();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("PLEASE ENTER YOUR USERNAME AND PASSWORD:-");
		String uName = sc.nextLine();
		String pWord = sc.nextLine();
		OnlineExamSystem examSystem = new OnlineExamSystem(uName, pWord);
		examSystem.login();
		examSystem.startExam();
	}
}