
public class BasketItem {
    private String name;
    private OfferEnum offer;
    private Integer unitPrice;

    public static BasketItem Apple = new BasketItem("Apple", OfferEnum.NONE, 35);
    public static BasketItem Banana = new BasketItem("Banana", OfferEnum.NONE, 20);
    public static BasketItem Melon = new BasketItem("Melon", OfferEnum.BUY_ONE_GET_ONE_FREE, 50);
    public static BasketItem Lime = new BasketItem("Lime", OfferEnum.BUY_THREE_FOR_THE_PRICE_OF_TWO, 15);

    public BasketItem(String name, OfferEnum offer, Integer unitPrice) {
         this.name = name; this.offer = offer; this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }


    public boolean onOffer(OfferEnum offer) {
        return this.offer == offer;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

}
