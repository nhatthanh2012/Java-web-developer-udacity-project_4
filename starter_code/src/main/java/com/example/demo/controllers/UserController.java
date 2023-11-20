package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	// 20231117 ThanhTLN - update start
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	// 20231117 ThanhTLN - update end

	@GetMapping("/id/{id}")
	// comment out by ThanhTLN
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null) {
			log.info("Error 404 - user not found.");
			return ResponseEntity.notFound().build();
		}
		log.info("User found.");
		return ResponseEntity.ok(user.get());
	}
	
	@GetMapping("/{username}")
	// comment out by ThanhTLN
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			log.info("User not found!");
			return ResponseEntity.notFound().build();
		}
		log.info("User found");
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	// comment out by ThanhTLN
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		// 20231117 ThanhTLN - update start
		String username = createUserRequest.getUsername();
		String password = createUserRequest.getPassword();
		String confirmPassword = createUserRequest.getConfirmPassword();
		// check validate before create new user
		// check case null
		if (username == null || password == null || confirmPassword == null) {
			log.info("Failed to create user - null parameter");
			return ResponseEntity.badRequest().build();
		}
		if (userRepository.findByUsername(username) != null) {
			log.info("Username exist");
			return ResponseEntity.badRequest().build();
		}
		// check length pass
		if (password.length() < 7) {
			log.info("Username must be at least 7 characters in length");
			return ResponseEntity.badRequest().build();
		}
		// check confirm pass
		if (!password.equals(confirmPassword)) {
			log.info("Please confirm correctly your password");
			return ResponseEntity.badRequest().build();
		}
		// 20231117 ThanhTLN - update end
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		log.info("User created ok");
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}
	
}
