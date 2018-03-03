package hello.app.clients;

import hello.app.model.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("order-service")
public interface OrdersFeignClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/orders/user/{userName}",
            consumes = "application/json")
    List<Order> getOrders(@PathVariable("userName") String userName);

}
