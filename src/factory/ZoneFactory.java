package factory;

import zone.AdminZone;
import zone.BuyerZone;
import zone.SellerZone;
import zone.Zone;

public class ZoneFactory {
	
	public static Zone getZone(int choice) {
		
		switch(choice) {
			case 1:
				return new BuyerZone();
			case 2:
				return new SellerZone();
			case 3:
				return new AdminZone();
			default:
				return new BuyerZone();
		}
	}
}
