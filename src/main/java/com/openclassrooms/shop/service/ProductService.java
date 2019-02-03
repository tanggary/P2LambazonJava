package com.openclassrooms.shop.service;

import com.openclassrooms.shop.domain.Cart;
import com.openclassrooms.shop.domain.CartLine;
import com.openclassrooms.shop.repository.OrderRepository;
import com.openclassrooms.shop.domain.Product;
import com.openclassrooms.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private ProductRepository productRepository;
	private OrderRepository orderRepository;

	@Autowired
	public ProductService(ProductRepository repository, OrderRepository orderRepository) {
		this.productRepository = repository;
		this.orderRepository = orderRepository;
	}

	/**
	 * @return all products from the inventory
	 */
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	/**
	 *
	 * @param productId Id of the product
	 * @return a product form the inventory
	 */
	public Product getProductById(Long productId)
	{
		//Returns the product that matches with productID arguement provided.
		//Searches through the list, and only returning the product with that matching ID.
		for (Product item : productRepository.findAll())
		{
			if (item.getId().equals(productId))
			{
				return item;
			}
		}
		return null;
	}

	/**
	 * Update the quantities left for each product in the inventory depending of ordered the quantities
	 * @param productId ID of the product to be updated
	 */
	public void updateProductQuantities(Long productId, int quantity)
	{
		//Updated product in the productrepository if only it matches the productID of the arguement.
		for (Product item : productRepository.findAll())
			if (item.getId().equals(productId))
			{
				item.setStock(item.getStock() - quantity);
				//System.out.println("Item: " + item + " stock is now: " + item.getStock());
			}
	}
}
