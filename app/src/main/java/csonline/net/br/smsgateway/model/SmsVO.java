package csonline.net.br.smsgateway.model;

import java.util.Date;

public class SmsVO {

    private Integer id;

    private Integer idDelivery;

    private DeliveryVO delivery;

    private String to;

    private String from;

    private short piece;

    private char type;
    
    private Date datetime;
	
    private String message;
	
    public SmsVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Integer idDelivery) {
        this.idDelivery = idDelivery;
    }

    public DeliveryVO getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryVO delivery) {
        this.delivery = delivery;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public short getPiece() {
        return piece;
    }

    public void setPiece(short piece) {
        this.piece = piece;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}