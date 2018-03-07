package com.retrospect.user.events.handler;

import com.retrospect.user.events.CustomChannels;
import com.retrospect.user.events.models.OrderChangeModel;
import com.retrospect.user.repository.OrderRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
public class OrderChangeHandler {

    @Autowired
    private OrderRedisRepository orderRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderChangeHandler.class);

    @StreamListener("inboundOrgChanges")
    public void loggerSink(OrderChangeModel orgChange) {
        logger.debug("Received a message of type " + orgChange.getType());
        switch(orgChange.getAction()){
            case "GET":
                logger.debug("Received a GET event from the organization service for organization id {}", orgChange.getOrderId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the organization service for organization id {}", orgChange.getOrderId());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the organization service for organization id {}", orgChange.getOrderId());
                orderRedisRepository.deleteOrder(orgChange.getOrderId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", orgChange.getType());
                break;

        }
    }

}
