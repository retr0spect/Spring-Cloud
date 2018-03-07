package com.retrospect.user.clients;

import com.retrospect.user.model.Order;
import com.retrospect.user.repository.OrderRedisRepository;
import com.retrospect.user.repository.OrderRedisRepositoryImpl;
import com.retrospect.user.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRestTemplateClient {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Autowired
    OrderRedisRepositoryImpl orderRedisRepo;

    private static final Logger logger = LoggerFactory.getLogger(OrderRestTemplateClient.class);

    private Order checkRedisCache(String orderId) {
        try {
            return orderRedisRepo.findOrder(orderId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", orderId, ex);
            return null;
        }
    }

    private void cacheOrderObject(Order order) {
        try {
            orderRedisRepo.saveOrder(order);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}", order.getOrderId(), ex);
        }
    }

    public Order getOrder(String orderId){
        logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());


        Order org = checkRedisCache(orderId);
        if (org!=null){
            System.out.println("I have successfully retrieved an organization {} from the redis cache: {} " +  orderId + org);
            return org;
        }

        System.out.println("Unable to locate organization from the redis cache: {}. " + orderId);

        ResponseEntity<Order> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/api/order/v1/orders/{orderId}",
                        HttpMethod.GET,
                        null, Order.class, orderId);

        org = restExchange.getBody();
        System.out.println(restExchange.toString());

        if (org!=null) {
            cacheOrderObject(org);
        }

        return org;
    }


    public List<Order> getOrders(String userName){
        ResponseEntity<List<Order>> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/api/order/v1/orders/user/{userName}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Order>>() {}, userName);

        return restExchange.getBody();
    }
}
