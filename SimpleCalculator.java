// TODO: comment this program

public class SimpleCalculator extends ConsoleProgram {
	public static void main(String [] args) {
		SimpleCalculator calc = new SimpleCalculator();
		calc.run();
	}
	
	public void run() {
		boolean done = false;
		
		while (!done) {
			print("Enter your first number: ");
			int num1 = readInt();
			
			print("Enter your second number: ");
			int num2 = readInt();
			
			long answer = 0;
			
			boolean validInput=false;
			while (!validInput) {
				print("Enter your operation (+, -, /, *): ");
				String operation = readLine();
				switch (operation) {
				case "+": 
					// add
					answer = num1 + num2;
					validInput=true;
					break;
				case "-":
					// subtract
					answer = num1 - num2;
					validInput=true;
					break;
				case "/":
					// divide
					answer = num1 / num2;
					validInput=true;
					break;
				case "*":
					// multiply
					answer = num1 * num2;
					validInput=true;
					break;
					default: 
						println("Invalid input: try again");
				}
			}
			
			System.out.println("Answer is: " + answer);
			
			done = true;
		}
	}
}
