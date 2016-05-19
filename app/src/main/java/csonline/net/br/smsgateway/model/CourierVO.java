package csonline.net.br.smsgateway.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CourierVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
	
    private String role;
    
    private String name;
    
    private LoginVO login;
    
    private String email;
    
    private String email2;
    
    private String address;
    
    private String mobile;

    private BigDecimal factor_courier;

    private BusinessVO business;
    
    private List<DeliveryVO> deliveries;

    public CourierVO() {
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
    
    public BigDecimal getFactor_courier() {
        return factor_courier;
    }

    public void setFactor_courier(BigDecimal factor_courier) {
        this.factor_courier = factor_courier;
    }
    
    public BusinessVO getBusiness() {
        return business;
    }

    public void setBusiness(BusinessVO business) {
        this.business = business;
    }

    public List<DeliveryVO> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryVO> deliveries) {
        this.deliveries = deliveries;
    }
}
