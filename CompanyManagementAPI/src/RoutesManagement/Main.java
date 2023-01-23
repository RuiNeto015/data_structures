package RoutesManagement;

import AdaptedDataStructures.AdaptedNetwork;
import AdaptedDataStructures.IAdaptedNetwork;
import Locals.ILocal;
import Locals.IMarket;
import Locals.Local;
import Locals.Market;
import Locals.Warehouse;
import LocalsManagement.IManageMarkets;
import LocalsManagement.IManageWarehouses;
import LocalsManagement.ManageMarkets;
import LocalsManagement.ManageWarehouses;
import SellersManagement.ISeller;
import SellersManagement.Seller;

/**
 *
 * @author Simão
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IAdaptedNetwork network = new AdaptedNetwork();
        IManageMarkets manageMarkets = new ManageMarkets(network);
        IManageWarehouses manageWarehouses = new ManageWarehouses(network);
        ManageRoutes manageRoutes = new ManageRoutes(network);

        ILocal company = new Local("Company");
        network.addVertex(company);

        IMarket market1 = new Market("Market1");
        IMarket market2 = new Market("Market2");
        IMarket market3 = new Market("Market3");
        IMarket market4 = new Market("Market4");

        Warehouse warehouse1 = new Warehouse("Warehouse1", 50);
        Warehouse warehouse2 = new Warehouse("Warehouse2", 100);

        manageMarkets.addLocal(market1);
        manageMarkets.addLocal(market2);
        manageMarkets.addLocal(market3);
        manageMarkets.addLocal(market4);

        manageMarkets.addMarketClient("Market1", 5);
        manageMarkets.addMarketClient("Market1", 2);
        manageMarkets.addMarketClient("Market2", 15);
        manageMarkets.addMarketClient("Market2", 3);
        manageMarkets.addMarketClient("Market3", 4);
        manageMarkets.addMarketClient("Market3", 1);

        manageWarehouses.addLocal(warehouse1);
        manageWarehouses.addLocal(warehouse2);

        manageWarehouses.loadWarehouseStock("Warehouse1", 50);
        manageWarehouses.loadWarehouseStock("Warehouse2", 100);

        manageRoutes.addRoad("Company", "Market1", 10);
        manageRoutes.addRoad("Company", "Warehouse1", 15);
        manageRoutes.addRoad("Company", "Warehouse2", 50);
        manageRoutes.addRoad("Market1", "Warehouse1", 5);
        manageRoutes.addRoad("Market1", "Market2", 20);
        manageRoutes.addRoad("Market2", "Market4", 25);
        manageRoutes.addRoad("Market2", "Warehouse1", 5);
        manageRoutes.addRoad("Warehouse1", "Market4", 30);
        manageRoutes.addRoad("Market4", "Warehouse2", 15);
        manageRoutes.addRoad("Warehouse2", "Market3", 5);
        manageRoutes.addRoad("Market3", "Warehouse1", 5);

        ISeller seller = new Seller("1", "José", 10);
        seller.addMarketToVisit("Market1");
        seller.addMarketToVisit("Market2");
        seller.addMarketToVisit("Market3");
        seller.addMarketToVisit("Market4");

//        Iterator<ILocal> it = manageRoutes.generateRouteForSeller(company, 
//                seller);
//        
//        while (it.hasNext()) {
//            ILocal currentLocal = it.next();
//            System.out.println(currentLocal.getName() + ";");
//        }
        manageRoutes.printRoads();
        System.out.println(manageRoutes.roadsToJson());
        manageRoutes.printRouteForSeller(company, seller);
    }

}
