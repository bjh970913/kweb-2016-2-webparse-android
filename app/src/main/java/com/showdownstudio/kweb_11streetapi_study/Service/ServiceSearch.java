package com.showdownstudio.kweb_11streetapi_study.Service;

import com.google.gson.Gson;

import com.showdownstudio.kweb_11streetapi_study.API.API_SETTING;
import com.showdownstudio.kweb_11streetapi_study.Model.ProductSearchResponseRoot;
import com.showdownstudio.kweb_11streetapi_study.UrlBuilder.ProductSearchBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bjh970913 on 06/11/2016 - 20:56
 */

public class ServiceSearch {
    private Gson gson;
    private String keyword;

    public ServiceSearch() {
        gson = new Gson();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ProductSearchResponseRoot productSearch() throws IOException {
        ProductSearchBuilder query = new ProductSearchBuilder(keyword);

        URL req = query.build();
        String result = getHttpContent(req);

        return gson.fromJson(result, ProductSearchResponseRoot.class);
    }

    private String getHttpContent(URL req) throws IOException {
        HttpURLConnection conn;
        conn = (HttpURLConnection) req.openConnection();

        conn.setRequestMethod("GET");

        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("appKey", API_SETTING.APP_KEY);

        InputStream in;

        int status = conn.getResponseCode();

        if (status >= 400)
            in = conn.getErrorStream();
        else
            in = conn.getInputStream();

        String content = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line;

        while ((line = reader.readLine()) != null) {
            content = content.concat(line + "\n");
        }

        return content;
    }
}
