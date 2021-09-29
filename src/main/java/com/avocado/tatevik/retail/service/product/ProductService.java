package com.avocado.tatevik.retail.service.product;

import com.avocado.tatevik.retail.service.product.model.ProductCreationModel;
import com.avocado.tatevik.retail.service.product.model.ProductModel;
import com.avocado.tatevik.retail.service.product.model.ProductUpdateModel;

public interface ProductService {

    ProductModel get(Long id);

    Boolean delete(Long id);

    ProductModel create(ProductCreationModel creationModel);

    ProductModel update(ProductUpdateModel updateModel);
}
