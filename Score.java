package testProject;

public class Score {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int score[][] = new int[3][3];
		String name[] = {"홍길동", "이순신", "유관순"};
		score[0] = new int [] {80,90,77};
		score[1] = new int [] {78,97,86};
		score[2] = new int [] {71,68,88};
		int sum[] = new int[3];
		double avg[] = new double[3];
		int total_k=0, total_en=0, total_math=0, total=0;
		double total_avg=0.0;
		
		System.out.println("================= A반 성적표 ==================");
		System.out.println("이름	국어	영어	수학	합계	평균");
		System.out.println("===========================================");
		
		for(int i=0;i<score.length;i++) {
			System.out.print(name[i] + "\t");
			for(int j=0;j<score[i].length;j++) {
				sum[i] += score[i][j];
				avg[i] = (double)sum[i]/3;
				System.out.print(score[i][j]+"\t");
			}
			System.out.print(sum[i] + "\t");
			System.out.printf("%.1f",avg[i]);
			System.out.println();
		}

		
		for(int i=0;i<3;i++) {
			total_k += score[i][0];
			total_en += score[i][1];
			total_math += score[i][2];
			total += sum[i];
			total_avg = total/9;
		}
		
		System.out.println("===========================================");
		System.out.println("합계\t" + total_k + "\t" + total_en + "\t" + total_math + "\t" + total + "\t" + total_avg);
	}

}


