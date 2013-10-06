package puzzle;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Read {
	public int[][] iniState=new int[4][4];
	String arg1 = null;
	String arg2 = null;
	public Read(String string){
		//if(string.length==1){
	//	arg1 = string[0];
		//arg2 = args[2];
		//}
	}
	public int[][] convert(String string){
		ArrayList<Integer> list = new ArrayList<Integer>();
		//get the numbers from the string and save them to a matrix
		Pattern p = Pattern.compile("\\D+");
		String[] numbers = p.split(string);
		for (String number:numbers){
			if(null!=number && !"".equals(number))
				list.add(Integer.parseInt(number));
			//	System.out.print(number);
				//System.out.println(list.toString());
		}

		int index=0;
		int len =  list.size();
		for (int i = 0; i<4; i++){
			for (int j =0; j<4;j++){
				iniState[i][j]=  list.get(index);
				index++;
			//	System.out.println(iniState[i][j]);
}
		}
		return iniState;
	}


}
