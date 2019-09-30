package card_pizza;

import java.util.spi.LocaleNameProvider;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.xml.transform.Templates;

public class pizza_store {
	
	static String[] pizzas = {"chickenFiesta","farmHouse","margherita","peppyPaneer"};
	static String[] toppings = {"Fresh Tomato", "Paneer", "Jalapeno", "Capsicum", "Barbeque"};
	
	public static void addTopping(String topping) {
		int t_len = toppings.length;
		String[] new_toppings = new String[t_len+1];
		for(int i=0; i<t_len; i++) {
			new_toppings[i]=toppings[i];
		}
		new_toppings[t_len] = topping;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(true) {
			int opt1 = -1;
			Scanner scanner = new Scanner(System.in);
			System.out.println("Plz enter 1 for costomer or 0 for manager");
			try {
				opt1 = scanner.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Please enter numer 1 or 0");
			}
			if(opt1 == 1) {
				System.out.println("welcome dear costomer");
				String copt1 = "";
				String copt2 = "";
				String c_toppings="";
				int valid1 = 0;
				while(valid1 == 0) {
					System.out.println("please chose your pizza. You may choose from: ");
					System.out.println(Arrays.toString(pizzas));
					try {
						copt1 = scanner.next();				
					}catch(InputMismatchException e) {
						System.out.println("plz choose from: "+ Arrays.toString(pizzas));
					}
					
					for (int j=0; j<pizzas.length; j++) {
						if(copt1.equals(pizzas[j])) {
							valid1 = 1;
						}
					}
				}
				System.out.println("pizza chosed: "+copt1);
				
				while(valid1==1) {
					System.out.println("please choose your toppings. you may choose more than one from: ");
					System.out.println(Arrays.toString(toppings));
					System.out.println("enter done to stop adding toppings");
					try {
						copt2 = scanner.next();				
					}catch(InputMismatchException e) {
						System.out.println("plz choose from: "+ Arrays.toString(toppings));
					}
					for (int k=0; k<toppings.length;k++) {
						if(copt2.equals(toppings[k])) {
							c_toppings+=copt2;
							c_toppings+= ";";
						}
					}
					if(copt2.equals("done")) {
						System.out.println("triggered");
						valid1=0;
					}
					System.out.println("toppings choosed: "+ c_toppings);

					
				}
				
				System.out.println("Your Pizza ordered");
				
				
				
			}else {
				if(opt1 == 0) {
					System.out.println("welcome, manager");
					System.out.println("enter the topping you want to add");
					String new_topping="";
					try {
						new_topping = scanner.next();
						addTopping(new_topping);
					}catch(InputMismatchException e) {
						
					}
					System.out.println("new topping: "+new_topping+" added!");
					
				}
			}
			
		}
	}

}
