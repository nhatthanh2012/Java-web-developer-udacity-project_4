package com.example.demo;

import java.lang.reflect.Field;

import com.example.demo.controller.UserControllerTest;
import com.example.demo.controllers.CartController;
import com.example.demo.controllers.ItemController;
import com.example.demo.controllers.OrderController;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.mock;

/**
 * Create by ThanhTLN - 2023/11/17
 * Description: Sareeta Application
 */
@Component
public class TestUtils {
    private UserController userController;
    private final UserRepository userRepository = mock(UserRepository.class);
    private final CartRepository cartRepository = mock(CartRepository.class);
    private final BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    private ItemController itemController;
    private final ItemRepository itemRepository = mock(ItemRepository.class);

    private OrderController orderController;
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private CartController cartController;

    public static void injectObjects(Object target, String fieldName, Object fieldValue) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);

            if (!field.isAccessible()) {
                field.setAccessible(true);
                field.set(target, fieldValue);
                field.setAccessible(false);
            }
            else
                field.set(target, fieldValue);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
