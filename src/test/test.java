package test;

import java.util.Arrays;

import wudeng.entity.Permission;
import wudeng.entity.Role;
import wudeng.util.MenuFactory;
import wudeng.util.PermissionFactory;

public class test {
	public static void main(String[] args) {
		/*char[] c = "11101".toCharArray();
		System.out.println(Arrays.toString(c));
		for(char c1 :c){
		System.out.println(c1);
		}
		System.out.println(Integer.parseInt("11101", 2));*/
		
		 int num = 29;
	        String binaryString = Integer.toBinaryString(num);
	        System.out.println(binaryString);
	        for (int i = 0; i < 5; i++)
	        {
	            System.out.print(get(num, i) + "\t");
	        }
		
	}
	
	   public static int get(int num, int index)
	    {
	        return (num & (0x1 << index)) >> index;
	    }
}
