package StableMatch;

import java.util.Random;

public class Man {
	Random r=new Random();
	int state;
	int[] prefer;
	public Man(int n){
		prefer=new int[n];
		this.state=-1;			//初始化状态为-1（单身）
		//生成个人偏好表
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
	//输入男生代号i,返回i在此女生偏好表中的排名
	public int index(int i){
		int index;
		for(index=0;prefer[index]!=i;index++){	}
		return index;
	}
	//返回排名i的男生编号
	public int prefer(int i){
		return prefer[i];
	}
	//改变状态
	public void State(int n){
		this.state=n;
	}
	//返回当前状态
	public int getState(){
		return state;
	}

}
