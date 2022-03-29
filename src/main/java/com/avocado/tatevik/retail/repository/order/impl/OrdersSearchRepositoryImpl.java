package com.avocado.tatevik.retail.repository.order.impl;

import com.avocado.tatevik.retail.common.enums.Country;
import com.avocado.tatevik.retail.common.enums.OrdersSearchSortField;
import com.avocado.tatevik.retail.common.enums.PaymentType;
import com.avocado.tatevik.retail.controller.order.dto.OrderSearchQueryParams;
import com.avocado.tatevik.retail.repository.order.OrdersSearchRepository;
import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Repository
public class OrdersSearchRepositoryImpl implements OrdersSearchRepository {

    @Autowired
    private EntityManager entityManager;

    private final Set<OrdersSearchSortField> sortOptions = new HashSet<>();

    @Override
    public HashSet<OrderEntity> search(OrderSearchQueryParams request) {
        String queryString = "select o from OrderEntity o " +
                "inner join o.customer c " +
                "inner join o.shop s " +
                "inner join o.address a " +
                "inner join o.orderProducts op " +
                "join ProductEntity p on op.productId = p.id " +
                "where (:totalPrice is null or o.totalPrice = :totalPrice) ";
        queryString = queryString + createFilteredQuery(request) + addSortOrder(request);
        log.info("GENERATED QUERY:'{}'", queryString);
        TypedQuery<OrderEntity> query = entityManager.createQuery(queryString, OrderEntity.class);
        query.setParameter("totalPrice", request.getTotalPrice());
        setFilteredQueryParams(query, request);
        query.setFirstResult(getFirstPage(request));
        query.setMaxResults(request.getMaxPageSize() == null ? 20 : request.getMaxPageSize());
        return new HashSet<>(query.getResultList());
    }

    private String createFilteredQuery(OrderSearchQueryParams request) {
        return addCustomerFilter(request) +
                addShopFilter(request) +
                addProductFilter(request) +
                addPaymentTypeFilter(request) +
                addCountryFilter(request) +
                addCityFilter(request) +
                addDistrictFilter(request);
    }

    private void setFilteredQueryParams(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        setCustomerFilterParam(query, request);
        setShopFilterParam(query, request);
        setProductFilterParam(query, request);
        setPaymentTypeFilterParam(query, request);
        setCountryFilterParam(query, request);
        setCityFilterParam(query, request);
        setDistrictFilterParam(query, request);
    }

    private void setDistrictFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final String district = request.getDistrict();
        if (district == null) {
            return;
        }
        query.setParameter("district", district);
    }

    private void setCityFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final String city = request.getCity();
        if (city == null) {
            return;
        }
        query.setParameter("city", city);
    }

    private void setCountryFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final Country country = request.getCountry();
        if (country == null) {
            return;
        }
        query.setParameter("country", country.name());
    }

    private void setPaymentTypeFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final PaymentType paymentType = request.getPaymentType();
        if (paymentType == null) {
            return;
        }
        query.setParameter("paymentType", paymentType);
    }

    private void setProductFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final String product = request.getProductName();
        if (product == null) {
            return;
        }
        query.setParameter("productName", product);
    }

    private void setShopFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final String shop = request.getShopName();
        if (shop == null) {
            return;
        }
        query.setParameter("shopName", shop);
    }

    private void setCustomerFilterParam(TypedQuery<OrderEntity> query, OrderSearchQueryParams request) {
        final String customer = request.getCustomerName();
        if (customer == null) {
            return;
        }
        query.setParameter("customerName", customer);
    }

    private String addCustomerFilter(OrderSearchQueryParams request) {
        final String customer = request.getCustomerName();
        if (customer == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.CUSTOMER_NAME);
        return " and (:customerName is null or c.name like concat('%', :customerName, '%')) ";
    }

    private String addShopFilter(OrderSearchQueryParams request) {
        final String shop = request.getShopName();
        if (shop == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.SHOP_NAME);
        return " and (:shopName is null or s.name like concat('%', :shopName, '%')) ";
    }

    private String addCountryFilter(OrderSearchQueryParams request) {
        final Country country = request.getCountry();
        if (country == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.COUNTRY);
        return " and (:country is null or a.country like concat('%', :country, '%')) ";
    }

    private String addCityFilter(OrderSearchQueryParams request) {
        final String city = request.getCity();
        if (city == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.CITY);
        return " and (:city is null or a.city like concat('%', :city, '%')) ";
    }

    private String addDistrictFilter(OrderSearchQueryParams request) {
        final String district = request.getDistrict();
        if (district == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.DISTRICT);
        return " and (:district is null or a.district like concat('%', :district, '%')) ";
    }

    private String addPaymentTypeFilter(OrderSearchQueryParams request) {
        final PaymentType paymentType = request.getPaymentType();
        if (paymentType == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.PAYMENT_TYPE);
        return " and (:paymentType is null or o.paymentType = :paymentType) ";
    }

    private String addProductFilter(OrderSearchQueryParams request) {
        final String product = request.getProductName();
        if (product == null) {
            return "";
        }
        creatingSortOptions(OrdersSearchSortField.PRODUCT_NAME);
        return " and (:productName is null or p.name like concat('%', :productName, '%')) ";
    }

    private String addSortOrder(OrderSearchQueryParams request) {
        final OrdersSearchSortField sortField = request.getSort();
        if (sortField == null) {
            return "";
        }
        // TODO: 9/16/21 this part is wrong
        return " order by " + getSortFieldName(sortField) + " " + (request.getSortDirection() == null ? Sort.Direction.DESC : request.getSortDirection()).name();
    }

    private void creatingSortOptions(OrdersSearchSortField sortField) {
        sortOptions.add(sortField);
    }

    private String getSortFieldName(OrdersSearchSortField sortField) {
        OrdersSearchSortField field = this.sortOptions
                .stream()
                .filter(sf -> sf.getApiFieldName().equalsIgnoreCase(sortField.getApiFieldName()))
                .findAny().orElse(null);
        return field == null ? "" : field.getDbFieldName();
    }

    private Integer getFirstPage(OrderSearchQueryParams request) {
        if (request.getOffset() == null) {
            return 0;
        }
        return request.getMaxPageSize() == null ? request.getOffset() : request.getOffset() * request.getMaxPageSize();
    }
}
