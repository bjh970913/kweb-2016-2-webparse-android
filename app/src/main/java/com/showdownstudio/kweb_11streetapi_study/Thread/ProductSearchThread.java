package com.showdownstudio.kweb_11streetapi_study.Thread;

import android.os.Handler;
import android.os.Message;

import com.showdownstudio.kweb_11streetapi_study.Model.ProductSearchResponseRoot;
import com.showdownstudio.kweb_11streetapi_study.Service.ServiceSearch;

/**
 * Created by bjh970913 on 06/11/2016 - 21:57
 */

public class ProductSearchThread extends Thread {
    private ServiceSearch service;
    private Handler handler;

    public ProductSearchThread(ServiceSearch service, Handler handler) {
        this.service = service;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();

        Message msg = handler.obtainMessage();

        try {
            ProductSearchResponseRoot data = service.productSearch();
            msg.what = 1;
            msg.obj = data;

            msg.arg1 = 10;

            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
