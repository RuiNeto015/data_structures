package Locals;

/**
 *
 * @author Rui Neto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        IMarket market = new Market("Mercado1");
        market.addClient(25);
        market.addClient(30);
        market.addClient(50);
        //market.printObject();
        //System.out.println(market.localToJson());
        market.HowMuchTheClientNeeds();
        market.serveClient(10);
        market.serveClient(50);
        System.out.println(market.getNumberOfClients());
        System.out.println("breakpoint");
    }
    
}
