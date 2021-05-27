package StableMatch;
import java.util.Scanner;
public class Match {
	Man[] man;
	Woman[] woman;
	public Match(int n){
		woman=new Woman[n];
		man=new Man[n];
		System.out.println("女生的喜好表：");

		//新建n个女生并输出其喜好表
		for(int i=0;i<n;i++){
			woman[i]=new Woman(n);
			System.out.print("woman_"+i+"：\t");
			for(int j=0;j<n;j++){
				System.out.print("man_"+woman[i].prefer(j)+"\t");
			}
			System.out.println();
		}
		System.out.println("\n男生的喜好表：");

		//新建n个男生并输出其喜好表
		for(int i=0;i<n;i++){
			man[i]=new Man(n);
			System.out.print("man_"+i+"：\t");
			for(int j=0;j<n;j++){
				System.out.print("woman_"+man[i].prefer(j)+"\t");
			}
			System.out.println("");
		}
		System.out.println("");

		int[] prefer_loc= new int[10] ;		//标记男生即将表白的位置
		for(int i=0; i<n; i++)prefer_loc[i]=0;
		int count_man_single = n;	//记录单身的男生个数
		while (count_man_single>0)
		{
			for(int i=0; i<n;){
				int w=man[i].prefer(prefer_loc[i]);		//男人i第prefer_loc[i]喜欢的女生

				// 男生i非单身
				if(man[i].getState()!=-1){
					i++;
				}
				else {
					if(woman[w].getState()==-1) {
						System.out.printf("%d号男生向%d号女生表白\n", i, w);
						woman[w].State(i);            //女生的状态变为男生编号
						man[i].State(w);            //男生状态变为女生编号
						System.out.printf("%d号女生单身因此接受了%d号男生\n", w, i);
						System.out.println();
						i++;        //换下一个男生
						count_man_single--;
					}
					//女生现男友在偏好表中的位置偏后与当前表白的男生，则女生甩掉前男友跟当前表白的人在一起
					else if(woman[w].index(woman[w].getState())>woman[w].index(i)){
						System.out.printf("%d号男生向%d号女生表白\n",i,w);
						int state_old = woman[w].getState();
						woman[w].State(i);			//女生的状态变为男生编号
						man[i].State(w);			//男生状态变为女生编号
						System.out.printf("%d号女生更喜欢%d号男生，因此甩了%d号男生，并接受了%d号男生\n",w,i,state_old,i);
						System.out.println();
						man[state_old].State(-1);		//前男友状态变为-1，即单身
						prefer_loc[state_old]++;		//man_i从此女生之后一个人开始表白
					}
					//女生现男友在偏好表中的位置在当前表白的男生之前，则女生拒绝当前表白的人
					else if(woman[w].index(woman[w].getState())<woman[w].index(i)){
						System.out.printf("%d号男生向%d号女生表白\n",i,w);
						System.out.printf("%d号女生心有所属因此拒绝了%d号男生\n",w,i);
						System.out.println();
						prefer_loc[i]++;		//男生继续表白自己偏好表中下一个女生
					}
				}
			}

		}

		//打印最终配对情况
		System.out.println("最终配对情况：");
		for(int i=0;i<n;i++) {
			System.out.printf("man_%d and woman_%d\n", i, man[i].getState());
		}
	}

}
