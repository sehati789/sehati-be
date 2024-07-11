package id.sehatibe.service;

import id.sehatibe.model.Order;
import id.sehatibe.model.User;
import id.sehatibe.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public void addOrder( String id, Order order) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Hibernate.initialize(user.getOrders());
        user.getOrders().add(order);
        order.setUser(user);
        userRepository.save(user);
    }
}
