Using any language you like, write a simple program that calculates the price of a basket of shopping.
 Items are presented one at a time, in a list, identified by name - for example "Apple" or "Banana".
 Multiple items are present multiple times in the list, so for example ["Apple", "Apple", "Banana"]
 is a basket with two apples and one banana.

Items are priced as follows:
 - Apples are 35p each
 - Bananas are 20p each
 - Melons are 50p each, but are available as ëbuy one get one freeí
 - Limes are 15p each, but are available in a ëthree for the price twoí offer

Given a list of shopping, calculate the total cost of those items.

Two solutions are presented:

ShoppingCalculator
 - Uses observables, but otherwise is the simplest thing that can possibly work.

 GenericCalculator
 - Uses observables, but also abstracts away the offer associated with each item in a BasketItem object.

 My submission is the ShoppingCalculator, just wanted to try something more challenging.
 I'm new to Functional Reactive Programming (FRP). My real expertise is Object Orientation.
 I like the idea of being able to mix programming styles.




