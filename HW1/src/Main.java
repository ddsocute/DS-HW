
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter your weight(kg) and height(cm): ");
		String text = buf.readLine();
		String[] data = text.split(" ");
		double res = getBMI(data);
		String dia = getDiagnosis(res);
		System.out.println(dia + " BMI: " + res);
	}

	public static double getBMI(String[] data) {
		double weight = Double.parseDouble(data[0]);
		double height = Double.parseDouble(data[1]);
		double heightm = height/100;
		double BMI = weight / (heightm * heightm);
		return BMI;
	}

	public static String getDiagnosis(double BMI) {
		if (BMI >= 30) {
			return "You are not in shape. Actually, you are not even close.";
		}else if(30 > BMI && BMI >= 26){
			return "To be honest, you are not in shape.";
		}else if(26 > BMI && BMI >= 20){
			return "You are in shape";
		}else {
			return "You are under shape";
		}
	}
}
