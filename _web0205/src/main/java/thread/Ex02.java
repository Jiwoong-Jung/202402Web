package thread;

public class Ex02 {

	public static void main(String[] args) {
		Task task = new Task();
		Thread th = new Thread(task);
		th.start();

	}

}
