import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeAverage4 extends ConsoleProgram {
	int numberOfStudents = 0;
	long sum = 0;
	String[] names = null;
	int[] grades = null;
	boolean done = false;

	public static void main(String args[]) {
		GradeAverage4 average = new GradeAverage4();
		average.run();
	}

	public int showMenu() {
		System.out.println("   Grade Book 1.0 ");
		System.out.println("1) Load data file.");
		System.out.println("2) Enter names and grades.");
		System.out.println("3) Calculate Averages, highest and lowest grade.");
		System.out.println("4) Save data file.");
		System.out.println("5) Exit.");
		System.out.print("Your choice is: ");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		answer = answer.trim();
		int intAnswer = Integer.parseInt(answer);
		return intAnswer;
	}

	public void run() {
		while (!done) {
			int response = showMenu();
			switch (response) {
			case 1:
				loadDataFile();
				break;
			case 2:
				enterNamesAndGrades();
				break;
			case 3:
				calculateStats();
				break;
			case 4:
				saveDataFile();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Error: please choose between 1 and 5.");
			}
		}
	}
	
	public void loadDataFile() {
		String filename = "grades.txt";
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			String line = null;
			int count = 0;
			while ((line = br.readLine()) != null) {
				System.out
						.println("line # " + count++ + " is: " + line);
			}
			numberOfStudents = count;
			fr.close();

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			names = new String[numberOfStudents];
			grades = new int[numberOfStudents];

			line = null;
			int count2 = 0;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				if (tokens != null && tokens.length == 2) {
					names[count2] = tokens[0];
					grades[count2] = Integer.parseInt(tokens[1]);
					sum += grades[count2];
					count2++;
				}
			}

			fr.close();
		} catch (Exception e1) {
			System.out.println("The file " + filename
					+ " does not exist! Type in data.");
		}
	}
	
	public void enterNamesAndGrades() {
		print("Enter # of students in the class:");
		numberOfStudents = readInt();

		names = new String[numberOfStudents];
		grades = new int[numberOfStudents];

		for (int i = 0; i < numberOfStudents; i++) {
			print("Student name: ");
			String name = readLine();
			names[i] = name;

			print("Enter a grade:");
			int grade = readInt();
			grades[i] = grade;
			// sum = sum + grade;
			sum += grade;
		}
	}
	
	public void calculateStats() {
		println("The sum of grades is: " + sum);

		long average = sum / numberOfStudents;

		println("Average class grade is: " + average);

		long highestGrade = grades[0];
		int highestGradeIndex = 0;

		long lowestGrade = grades[0];
		int lowestGradeIndex = 0;

		for (int i = 0; i < numberOfStudents; i++) {
			if (grades[i] > highestGrade) {
				highestGrade = grades[i];
				highestGradeIndex = i;
			}

			if (grades[i] < lowestGrade) {
				lowestGrade = grades[i];
				lowestGradeIndex = i;
			}
		}

		println("Highest Grade is: " + highestGrade);
		println("Student with highest grade is:"
				+ names[highestGradeIndex]);

		println("Lowest Grade is: " + lowestGrade);
		println("Student with Lowest grade is: "
				+ names[lowestGradeIndex]);		
	}
	
	public void saveDataFile() {
		// Save to a file
		try {
			FileWriter fw = new FileWriter("grades.txt");
			PrintWriter pw = new PrintWriter(fw);

			for (int i = 0; i < numberOfStudents; i++) {
				pw.println(names[i] + "," + grades[i]);
			}

			System.out.println("Saving data to file: grades.txt");
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
