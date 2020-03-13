package ru.aikam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aikam.entity.Cart;
import ru.aikam.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
