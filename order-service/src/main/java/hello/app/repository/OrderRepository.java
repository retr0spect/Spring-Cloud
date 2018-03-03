package hello.app.repository;

import hello.app.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    public Order findByOrderId(long orderId);

    public List<Order> findByUserName(String userName);

}
