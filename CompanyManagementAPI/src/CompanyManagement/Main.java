package CompanyManagement;

import Locals.Market;
import Locals.Warehouse;
import SellersManagement.Seller;

/**
 *
 * @author Rui Neto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManageCompany manageCompany = new ManageCompany("Casfil");
        manageCompany.addLocal(new Market("Market1"));
        manageCompany.addLocal(new Market("Market2"));
        manageCompany.addLocal(new Warehouse("Warehouse1", 50));
        manageCompany.addLocal(new Warehouse("Warehouse2", 40));
        manageCompany.loadWarehouseStock("Warehouse1", 50);
        manageCompany.loadWarehouseStock("Warehouse2", 10);

        manageCompany.addMarketClient("Market1", 50);
        manageCompany.addMarketClient("Market2", 10);

        manageCompany.addSeller(new Seller("1", "Dwight", 10));
        manageCompany.loadGoodsToSeller(10, "1");
        manageCompany.addMarketToSeller("Market2", "1");
        manageCompany.addMarketToSeller("Market1", "1");

        manageCompany.addRoad("Casfil", "Warehouse1", 15);
        manageCompany.addRoad("Warehouse1", "Market1", 20);
        manageCompany.addRoad("Casfil", "Market1", 5);
        manageCompany.addRoad("Market1", "Market2", 20);
        manageCompany.addRoad("Warehouse2", "Market1", 4);
        manageCompany.setRoadDistance("Casfil", "Warehouse1", 10);

        manageCompany.printLocals();
        manageCompany.printSellers();
        manageCompany.printRoads();
        manageCompany.printRouteForSeller(manageCompany.getCompany(),
                manageCompany.getSeller("1"));

        manageCompany.companyToJson();
    }

}
