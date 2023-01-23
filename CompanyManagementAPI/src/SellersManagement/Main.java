package SellersManagement;

/**
 *
 * @author Simão
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ISeller sellerA = new Seller("1", "Simão", 15);
        ISeller sellerB = new Seller("2", "José", 35);

        IManageSellers manageSellers = new ManageSellers();
        manageSellers.addSeller(sellerA);
        manageSellers.addSeller(sellerB);
        System.out.println(manageSellers.loadGoodsToSeller(1, "2"));
        System.out.println(manageSellers.unloadGoodsFromSeller(8, "2"));
        System.out.println(manageSellers.loadGoodsToSeller(15, "2"));
        System.out.println("breakpoint");
        manageSellers.addMarketToSeller("market1", "1");
    }

}
