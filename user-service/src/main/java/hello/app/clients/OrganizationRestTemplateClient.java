package hello.app.clients;

import hello.app.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    OAuth2RestTemplate restTemplate;


    public List<Order> getOrders(String userName){
        ResponseEntity<List<Order>> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/api/order/v1/orders/user/{userName}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Order>>() {}, userName);

        return restExchange.getBody();
    }
}
