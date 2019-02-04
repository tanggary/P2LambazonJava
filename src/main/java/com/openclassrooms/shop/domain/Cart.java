package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	
	//Create a class variable for Cart. An arraylist that holds the cart items.
		private List<CartLine> cartLineList = new ArrayList<>();
		
		//Creating a Constructor for Cart
		public Cart()
		{
		}

    /**
     *
     * @return the actual cartline list
     */
	public List<CartLine> getCartLineList() 
    {
        return cartLineList;
    }

    /**
     * Adds a getProductById in the cart or increment its quantity in the cart if already added
     * @param product getProductById to be added
     * @param quantity the quantity
     */
    public void addItem(Product product, int quantity) {
        // Searches list of the cartlinelist object and updates the quantity to the product in arguement.
       	for (CartLine item : getCartLineList())
    	{
    		if (item.getProduct().equals(product))
    		{
    			item.setQuantity(quantity + item.getQuantity());
    			return;
    		}
    	}
    	//add new cartline object if the item is currently not in cart.
    	getCartLineList().add(new CartLine(product,quantity));
    }

    /**
     * Removes a getProductById form the cart
     * @param product the getProductById to be removed
     */
    public void removeLine(Product product) {
        getCartLineList().removeIf(l -> l.getProduct().getId().equals(product.getId()));
    }


    /**
     * @return total value of a cart
     */
    public double getTotalValue()
    {
        //Gets the subtotal of all cartline objects in getcartlinelist, then sums them for total value.
   	double totalvalue = 0.0;
   	for (CartLine item : getCartLineList())
   	{
   		totalvalue += item.getSubtotal();
   	}
   	return totalvalue;

   }

    /**
     * @return Get average value of a cart
     */
    public double getAverageValue()
    {

    	return getTotalValue() / (getCartLineList().size() + 1);
    }

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
    public Product findProductInCartLines(Long productId)
    {
        // Searches the cartLineList object for that product that matches the productID in arguement. 
    	for (CartLine item : getCartLineList())
    	{
    		if (item.getProduct().getId().equals(productId))
    		{
    	    	return item.getProduct();
    		}
    	}
		return null;
    }

    /**
     *
     * @param index index of the cartLine
     * @return CartLine in that index
     */
    public CartLine getCartLineByIndex(int index)
    {
        return getCartLineList().get(index);
    }

    /**
     * Clears a the cart of all added products
     */
    public void clear()
    {
        List<CartLine> cartLines = getCartLineList();
        cartLines.clear();
    }
}
