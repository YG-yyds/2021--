package StableMatch;

import java.util.Random;

public class Man {
	Random r=new Random();
	int state;
	int[] prefer;
	public Man(int n){
		prefer=new int[n];
		this.state=-1;			//��ʼ��״̬Ϊ-1������
		//���ɸ���ƫ�ñ�
		for(int i=0;i<n;i++){
			prefer[i]=i;
		}
		for(int i=0;i<n;i++){
			int j=r.nextInt(n-1);
			int e=prefer[i];
			prefer[i]=prefer[j];
			prefer[j]=e;
		}
	}
	//������������i,����i�ڴ�Ů��ƫ�ñ��е�����
	public int index(int i){
		int index;
		for(index=0;prefer[index]!=i;index++){	}
		return index;
	}
	//��������i���������
	public int prefer(int i){
		return prefer[i];
	}
	//�ı�״̬
	public void State(int n){
		this.state=n;
	}
	//���ص�ǰ״̬
	public int getState(){
		return state;
	}

}
