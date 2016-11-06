package com.showdownstudio.kweb_11streetapi_study.Model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bjh970913 on 06/11/2016 - 20:45
 */

public class Products {
    private int TotalCount = 0;
    private Product Product[];

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public List<Product> getProduct() {
        return Arrays.asList(Product);
    }

    public void setProduct(Product[] product) {
        Product = product;
    }
}
