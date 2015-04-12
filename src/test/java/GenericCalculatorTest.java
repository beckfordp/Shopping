
import org.junit.Test;
import rx.observables.BlockingObservable;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GenericCalculatorTest {

    @Test
    public void canCalculateThePriceOfItemsNotOnOffer() {
        List<BasketItem> basketOfApples = Arrays.asList(BasketItem.Apple, BasketItem.Apple, BasketItem.Apple);
        GenericCalculator calc = new GenericCalculator(basketOfApples);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(105));
    }

    @Test
    public void canCalculateThePriceOfItemsOnOfferBuyOneGetOneFree() {
        List<BasketItem> basketOfMelons = Arrays.asList(BasketItem.Melon, BasketItem.Melon, BasketItem.Melon);
        GenericCalculator calc = new GenericCalculator(basketOfMelons);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(100));
    }

    @Test
    public void canCalculateThePriceOfItemsOnOfferBuyThreeForThePriceOfTwo() {
        List<BasketItem> basketOfLimes = Arrays.asList(BasketItem.Lime, BasketItem.Lime, BasketItem.Lime, BasketItem.Lime);
        GenericCalculator calc = new GenericCalculator(basketOfLimes);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(45));
    }

    @Test
    public void canCalculateThePriceOfAMixedBasketOfItems() {
        List<BasketItem> basket = Arrays.asList(BasketItem.Banana, BasketItem.Banana, BasketItem.Banana, // 60
                                            BasketItem.Apple, BasketItem.Apple, BasketItem.Apple,  // 105
                                            BasketItem.Melon, BasketItem.Melon, BasketItem.Melon,  // 100
                                            BasketItem.Lime, BasketItem.Lime, BasketItem.Lime, BasketItem.Lime); // 45
        GenericCalculator calc = new GenericCalculator(basket);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(310));
    }

    @Test
    public void canCalculateThePriceOfAnEmptyBasket() {
        List<BasketItem> basket = Arrays.asList();
        GenericCalculator calc = new GenericCalculator(basket);
        assertThat(BlockingObservable.from(calc.totalPrice()).last(), is(0));
    }

}