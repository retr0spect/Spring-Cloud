package com.retrospect.user.events.models;

public class OrderChangeModel {
    private String type;
    private String action;
    private String orderId;
    private String correlationId;

    public OrderChangeModel(){
        super();
    }

    public OrderChangeModel(String type, String action, String organizationId, String correlationId) {
        super();
        this.type   = type;
        this.action = action;
        this.orderId = organizationId;
        this.correlationId = correlationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "OrderChangeModel [type=" + type +
                ", action=" + action +
                ", orgId="  + orderId +
                ", correlationId=" + correlationId + "]";
    }
}