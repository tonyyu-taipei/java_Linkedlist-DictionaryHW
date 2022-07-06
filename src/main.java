import java.util.*;

class MyClass{
	String className;
	Dictionary <Integer, String[]> Data = new Hashtable<Integer, String[]>();
	//id, {姓名,分數}>
	Scanner sc = new Scanner(System.in);
	MyClass(String cN){
		className = cN;
	}
	MyClass next;
	public int getSize() {
		return Data.size();
	}
	public String[][] getData() {
		Enumeration<Integer> num = Data.keys();
		String[][] toSort = new String[Data.size()][3];
		int index= 0;
		while(num.hasMoreElements()) {
			// 學號
			toSort[index][0] = ""+(int)num.nextElement();
			int ID = Integer.valueOf(toSort[index][0]);
			//姓名
			toSort[index][1] = Data.get(ID)[0];
		
			//分數
			toSort[index][2] = Data.get(ID)[1];
			index++;
			
		}
		return toSort;
	}
	public void inputNum() {
		while(true) {
			System.out.println("請輸入第"+(Data.size()+1)+"位同學學號");
			String[] stuInfo = new String[2];
			int stuId = sc.nextInt();
			System.out.println("請輸入第"+(Data.size()+1)+"位同學姓名");
			stuInfo[0] = sc.next();
			System.out.println("請輸入分數");
			stuInfo[1] = sc.next();
			Data.put(stuId, stuInfo);
			
			System.out.println("繼續？Y/N");
			if(!sc.next().equals("Y")) {
				System.out.println("是否要輸入不同班的資料？Y/N");
				if(sc.next().equals("Y")) {
					System.out.println("請輸入班級名稱");
					next = new MyClass(sc.next());
					next.inputNum();
					break;
				}
				break;
			}
		}
	}
	public void print() {
		System.out.printf("%s的成績排序\r\n",className);

		String[][] toSort = getData();
		for(int i = 0 ; i < (toSort.length-1); i++) {
			for(int j=toSort.length; j>i;j--) {
				if(Integer.valueOf(toSort[i][2])<Integer.valueOf(toSort[i+1][2])) {
					String[] temp = new String[2];
					temp = toSort[i];
					toSort[i] = toSort[i+1];
					toSort[i+1] = temp;
				}
			}
		}
		System.out.println("學號\t\t姓名\t成績");
		for(String[] sorted : toSort) {
			System.out.printf("%s\t%s\t%s\r\n",sorted[0],sorted[1],sorted[2]);
		}
		System.out.println();
		
		if(next!=null)
		next.print();
	}
}
public class MyWork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("請輸入班級名稱");
		MyClass mcu = new MyClass(sc.next());
		while(true) {
			System.out.println("成績登錄查詢系統");
			System.out.println("1.輸入成績及學生資料");
			System.out.println("2.印出成績（由大到小排列）");
			System.out.println("9.離開");
			System.out.println("請輸入您的選項：");
			int choice = sc.nextInt();
			
			if(choice == 1)
				mcu.inputNum();
			
			if(choice == 2) {
				mcu.print();
			}
			if(choice == 9) {
				System.out.println("掰掰");
				break;
			}
		}
	}
}
