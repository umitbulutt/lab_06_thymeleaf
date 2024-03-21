package com.cydeo.service;


import com.cydeo.model.Cart;

import java.util.UUID;

public interface CartService {

    Cart addToCart(UUID productId, Integer quantity);

    boolean deleteFromCart(UUID productId);
}
