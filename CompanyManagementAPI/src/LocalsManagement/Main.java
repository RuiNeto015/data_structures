package LocalsManagement;

import AdaptedDataStructures.AdaptedNetwork;
import Locals.ILocal;
import Locals.IMarket;
import Locals.IWarehouse;
import Locals.Market;
import Locals.Warehouse;

/**
 *
 * @author Simão
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdaptedNetwork<ILocal> network = new AdaptedNetwork<>();
        IMarket market1 = new Market("Market1");
        IMarket market2 = new Market("Market2");
        IWarehouse warehouse1 = new Warehouse("Warehouse1", 15);
        IWarehouse warehouse2 = new Warehouse("Warehouse2", 30);
        
        IManageMarkets manageMarkets = new ManageMarkets(network);
        IManageWarehouses manageWarehouses = new ManageWarehouses(network);
        //----------------------------------------------------------------------
        manageMarkets.addLocal(market1);
        manageMarkets.addLocal(market2);
        manageMarkets.addMarketClient("Market1", 15);
        manageMarkets.addMarketClient("Market1", 23);
        manageMarkets.addMarketClient("Market2", 10);
        manageMarkets.addMarketClient("Market2", 25);
        manageMarkets.serveMarketClient("Market2", 5);
        manageMarkets.howMuchTheMarketClientNeeds("Market2");
        manageMarkets.getNumberOfMarketClients("Market1");
        manageMarkets.printLocals();
        //----------------------------------------------------------------------
        manageWarehouses.addLocal(warehouse1);
        manageWarehouses.addLocal(warehouse2);
        manageWarehouses.setWarehouseCapacity("Warehouse2", 45);
        manageWarehouses.getWarehouseCapacity("Warehouse2");
        manageWarehouses.loadWarehouseStock("Warehouse1", 30);
        manageWarehouses.loadWarehouseStock("Warehouse2", 20);
        manageWarehouses.unloadWarehouseStock("Warehouse2", 15);
        manageWarehouses.printLocals();
        //----------------------------------------------------------------------
        
        System.out.println("breakpoint");
    }
    
}
