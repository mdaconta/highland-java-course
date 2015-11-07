
public class GradeAverage extends ConsoleProgram {
	
	public static void main(String args[]) 
	{
		GradeAverage average = new GradeAverage();
		average.run();
	}
	
	public void run() 
	{
		print("Enter # of students in the class:");
		
		int numberOfStudents = readInt();
		
		long sum = 0;
		
		String [] names = new String[numberOfStudents];
		int [] grades = new int[numberOfStudents];
		
		for (int i=0; i < numberOfStudents; i++) 
		{
			print("Student name: ");
			String name = readLine();
			names[i] = name;
			
			print("Enter a grade:");
			int grade = readInt();
			grades[i] = grade;
			//sum = sum + grade;
			sum += grade;
		}
		

		println("The sum of grades is: " + sum);
		
		long average = sum / numberOfStudents;
		
		println("Average class grade is: " + average);
		
		long highestGrade = grades[0];
		int highestGradeIndex = 0;
		
		long lowestGrade = grades[0];
		int lowestGradeIndex = 0;
		
		for (int i=0; i < numberOfStudents; i++)
		{
			if (grades[i] > highestGrade) 
			{
				highestGrade = grades[i];
				highestGradeIndex = i;
			}
			
			if (grades[i] < lowestGrade)
			{
				lowestGrade = grades[i];
				lowestGradeIndex = i;
			}
		}
		
		println("Highest Grade is: " + highestGrade);
		println("Student with highest grade is:" + names[highestGradeIndex]);
		
		println("Lowest Grade is: " + lowestGrade);
		println("Student with Lowest grade is: " + names[lowestGradeIndex]);
		
		
		
		
	}
}
