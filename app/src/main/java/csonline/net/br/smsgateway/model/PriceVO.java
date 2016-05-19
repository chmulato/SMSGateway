package csonline.net.br.smsgateway.model;

import java.math.BigDecimal;

public class PriceVO {

    private Integer id;

    private BusinessVO business;

    private String table;

    private String vehicle;

    private String local;

    private BigDecimal price;

    public PriceVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BusinessVO getBusiness() {
        return business;
    }

    public void setBusiness(BusinessVO business) {
        this.business = business;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}