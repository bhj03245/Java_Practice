import java.util.*;


public class Lotto_ver2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Integer> rand = new HashSet<Integer>();
		while(rand.size()!=6) {
			rand.add((int)(Math.random()*45+1));
		}
		
		List l = new ArrayList(rand);
		Collections.sort(l);
		
		Iterator i = l.iterator();
		
		System.out.println("�ζǹ�ȣ �ڵ� �Է� �ý���");
		System.out.println("--------------------------����-------------------------");
		System.out.println("1\t2\t3\t4\t5\t6");
		System.out.println("-------------------------�ζǹ�ȣ-----------------------");
		
		while(i.hasNext()) {
			System.out.print(i.next() + "\t");
		}
	}
}
