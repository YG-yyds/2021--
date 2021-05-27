package StableMatch;

import java.util.Scanner;

public class Test {

	public static void main(String[]arges){
		Scanner in=new Scanner(System.in);
		System.out.print("请输入配对男（女）生数：");
		int n=in.nextInt();
		Match mach=new Match(n);
	}

}
