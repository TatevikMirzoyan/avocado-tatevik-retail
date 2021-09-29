package com.avocado.tatevik.retail.common.enums;

public enum OrderQuerySortField {

    CUSTOMER_NAME("customerName", "c.name"),
    SHOP_NAME("shopName", "s.name"),
    PRODUCT_NAME("productName", "p.name"),
    PAYMENT_TYPE("paymentType", "o.payment_type"),
    COUNTRY("country", "a.country"),
    CITY("city", "a.city"),
    DISTRICT("district", "a.district");

    private String apiFieldName;

    private String dbFieldName;

    OrderQuerySortField(String apiFieldName, String dbFieldName) {
        this.apiFieldName = apiFieldName;
        this.dbFieldName = dbFieldName;
    }

    public String getApiFieldName() {
        return apiFieldName;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }}
