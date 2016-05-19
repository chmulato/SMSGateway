package csonline.net.br.smsgateway.model;

import java.util.List;

public class PriceListVO {

    private String table;

    private List<PriceVO> list;

    public PriceListVO() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<PriceVO> getList() {
        return list;
    }

    public void setList(List<PriceVO> list) {
        this.list = list;
    }
}