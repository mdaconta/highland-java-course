import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleProgram implements Runnable {

	public static void main(String[] args) {
		ConsoleProgram cp = new ConsoleProgram();
		cp.run();
	}
	
	public void run() { }

	public void print(String s)
	{
		System.out.print(s);
	}
	
	public void println(String s)
	{
		System.out.println(s);
	}
	
	public int readInt()
	{
		int num=0;
		
		String line = readLine();
		num = Integer.parseInt(line);
		
		return num;
	}

	public float readFloat()
	{
		float num = 0.0f;

		String line = readLine();
		num = Float.parseFloat(line);
		return num;
	}
	
	public String readLine()
	{
		String str = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return str;		
	}
}
