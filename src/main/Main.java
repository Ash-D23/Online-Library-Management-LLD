package main;

import java.util.Scanner;

import factory.ZoneFactory;
import zone.Zone;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Library Management App");
		System.out.println("---------------------------------");
		
		System.out.println("Select your Zone:");
		System.out.println("1. Buyer Zone");
		System.out.println("2. Seller Zone");
		System.out.println("3. Admin Zone");
		
		Scanner input = new Scanner(System.in);
		
		int zoneChoice = input.nextInt();
		
		Zone zone = ZoneFactory.getZone(zoneChoice);
		zone.view();
		
	}

}
