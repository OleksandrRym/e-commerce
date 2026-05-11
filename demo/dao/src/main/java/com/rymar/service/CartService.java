package com.rymar.service;

import com.rymar.entity.Cart;
import com.rymar.repo.spring.CartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo;

    public Cart getCartById(UUID id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

//    // Приклад методу для отримання кошика конкретного користувача,
//    // якщо у вас у репозиторії буде метод findByUserId
//    public Cart getCartByUserId(UUID userId) {
//        return cartRepo.findAll().stream()
//                .filter(cart -> cart.getUser().getId().equals(userId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Cart for this user not found"));
//    }
}