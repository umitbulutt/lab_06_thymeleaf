package com.cydeo.service.impl;

import com.cydeo.model.Cart;
import com.cydeo.model.CartItem;
import com.cydeo.model.Product;
import com.cydeo.service.CartService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    public static Cart CART = new Cart(BigDecimal.ZERO, new ArrayList<>());

    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Cart addToCart(UUID productId, Integer quantity) {
        //todo find product based on productId
        Product product = productService.findProductById(productId);
        //todo initialise cart item using the found product
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

        //todo calculate cart total amount
        CART.setCartTotalAmount(CART.getCartTotalAmount().add(cartItem.getTotalAmount()));
        //todo add to cart
        CART.getCartItemList().add(cartItem);
        return CART;
    }

    @Override
    public boolean deleteFromCart(UUID productId) {
        //todo delete product object from cart using stream

        CartItem cartItemToDelete = CART.getCartItemList().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findAny().orElseThrow();
        //update cartTotal amount
        CART.setCartTotalAmount(CART.getCartTotalAmount().subtract(cartItemToDelete.getTotalAmount()));
        //remove cartItem from the CART
        return CART.getCartItemList().remove(cartItemToDelete);
    }
}