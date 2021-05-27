package StableMatch;
import java.util.Scanner;
public class Match {
	Man[] man;
	Woman[] woman;
	public Match(int n){
		woman=new Woman[n];
		man=new Man[n];
		System.out.println("Ů����ϲ�ñ�");

		//�½�n��Ů���������ϲ�ñ�
		for(int i=0;i<n;i++){
			woman[i]=new Woman(n);
			System.out.print("woman_"+i+"��\t");
			for(int j=0;j<n;j++){
				System.out.print("man_"+woman[i].prefer(j)+"\t");
			}
			System.out.println();
		}
		System.out.println("\n������ϲ�ñ�");

		//�½�n�������������ϲ�ñ�
		for(int i=0;i<n;i++){
			man[i]=new Man(n);
			System.out.print("man_"+i+"��\t");
			for(int j=0;j<n;j++){
				System.out.print("woman_"+man[i].prefer(j)+"\t");
			}
			System.out.println("");
		}
		System.out.println("");

		int[] prefer_loc= new int[10] ;		//�������������׵�λ��
		for(int i=0; i<n; i++)prefer_loc[i]=0;
		int count_man_single = n;	//��¼�������������
		while (count_man_single>0)
		{
			for(int i=0; i<n;){
				int w=man[i].prefer(prefer_loc[i]);		//����i��prefer_loc[i]ϲ����Ů��

				// ����i�ǵ���
				if(man[i].getState()!=-1){
					i++;
				}
				else {
					if(woman[w].getState()==-1) {
						System.out.printf("%d��������%d��Ů�����\n", i, w);
						woman[w].State(i);            //Ů����״̬��Ϊ�������
						man[i].State(w);            //����״̬��ΪŮ�����
						System.out.printf("%d��Ů��������˽�����%d������\n", w, i);
						System.out.println();
						i++;        //����һ������
						count_man_single--;
					}
					//Ů����������ƫ�ñ��е�λ��ƫ���뵱ǰ��׵���������Ů��˦��ǰ���Ѹ���ǰ��׵�����һ��
					else if(woman[w].index(woman[w].getState())>woman[w].index(i)){
						System.out.printf("%d��������%d��Ů�����\n",i,w);
						int state_old = woman[w].getState();
						woman[w].State(i);			//Ů����״̬��Ϊ�������
						man[i].State(w);			//����״̬��ΪŮ�����
						System.out.printf("%d��Ů����ϲ��%d�����������˦��%d����������������%d������\n",w,i,state_old,i);
						System.out.println();
						man[state_old].State(-1);		//ǰ����״̬��Ϊ-1��������
						prefer_loc[state_old]++;		//man_i�Ӵ�Ů��֮��һ���˿�ʼ���
					}
					//Ů����������ƫ�ñ��е�λ���ڵ�ǰ��׵�����֮ǰ����Ů���ܾ���ǰ��׵���
					else if(woman[w].index(woman[w].getState())<woman[w].index(i)){
						System.out.printf("%d��������%d��Ů�����\n",i,w);
						System.out.printf("%d��Ů������������˾ܾ���%d������\n",w,i);
						System.out.println();
						prefer_loc[i]++;		//������������Լ�ƫ�ñ�����һ��Ů��
					}
				}
			}

		}

		//��ӡ����������
		System.out.println("������������");
		for(int i=0;i<n;i++) {
			System.out.printf("man_%d and woman_%d\n", i, man[i].getState());
		}
	}

}
