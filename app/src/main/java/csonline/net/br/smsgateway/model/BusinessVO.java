package csonline.net.br.smsgateway.model;

import java.io.Serializable;
import java.util.List;

public class BusinessVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

	private Integer id;
	
    private String role;
    
    private String name;
    
    private LoginVO login;
    
    private String email;
    
    private String email2;
    
    private String address;
    
    private String mobile;
    
    private List<CustomerVO> customers;
    
    private List<CourierVO> couriers;
    
    private List<DeliveryVO> deliveries;

    private List<PriceListVO> priceList;

    public BusinessVO() {
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public String getRole() {
            return role;
    }

    public void setRole(String role) {
            this.role = role;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public LoginVO getLogin() {
            return login;
    }

    public void setLogin(LoginVO login) {
            this.login = login;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getEmail2() {
            return email2;
    }

    public void setEmail2(String email2) {
            this.email2 = email2;
    }

    public String getAddress() {
            return address;
    }

    public void setAddress(String address) {
            this.address = address;
    }

    public String getMobile() {
            return mobile;
    }

    public void setMobile(String mobile) {
            this.mobile = mobile;
    } 

    public List<CustomerVO> getCustomers() {
            return customers;
    }

    public void setCustomers(List<CustomerVO> customers) {
            this.customers = customers;
    }

    public List<CourierVO> getCouriers() {
            return couriers;
    }

    public void setCouriers(List<CourierVO> couriers) {
            this.couriers = couriers;
    }

    public List<DeliveryVO> getDeliveries() {
            return deliveries;
    }

    public void setDeliveries(List<DeliveryVO> deliveries) {
            this.deliveries = deliveries;
    }

    public List<PriceListVO> getPriceList() {
            return priceList;
    }

    public void setPriceList(List<PriceListVO> priceList) {
            this.priceList = priceList;
    }
}