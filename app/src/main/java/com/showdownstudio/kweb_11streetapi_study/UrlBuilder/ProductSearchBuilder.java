package com.showdownstudio.kweb_11streetapi_study.UrlBuilder;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by bjh970913 on 06/11/2016 - 20:59
 */

public class ProductSearchBuilder {
    private final String url = "http://apis.skplanetx.com/11st/common/products";
    private int count = 50;
    private int page = 1;
    private String searchKeyword;
    private String sortCode = "N";
    private int version = 1;

    public ProductSearchBuilder(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public URL build() throws IOException {
        String URLs = url;

        URLs = URLs.concat("?");

        URLs = URLs.concat("version=");
        URLs = URLs.concat(Integer.toString(version));
        URLs = URLs.concat("&");

        URLs = URLs.concat("count=");
        URLs = URLs.concat(Integer.toString(count));
        URLs = URLs.concat("&");

        URLs = URLs.concat("page=");
        URLs = URLs.concat(Integer.toString(page));
        URLs = URLs.concat("&");

        URLs = URLs.concat("searchKeyword=");
        URLs = URLs.concat(URLEncoder.encode(searchKeyword, "UTF-8"));
        URLs = URLs.concat("&");

        URLs = URLs.concat("sortCode=");
        URLs = URLs.concat(sortCode);

        return new URL(URLs);
    }
}
