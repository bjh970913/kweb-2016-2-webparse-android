package com.showdownstudio.kweb_11streetapi_study;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.showdownstudio.kweb_11streetapi_study.Adapter.ProductAdapter;
import com.showdownstudio.kweb_11streetapi_study.Model.Product;
import com.showdownstudio.kweb_11streetapi_study.Model.ProductSearchResponse;
import com.showdownstudio.kweb_11streetapi_study.Model.ProductSearchResponseRoot;
import com.showdownstudio.kweb_11streetapi_study.Model.Products;
import com.showdownstudio.kweb_11streetapi_study.Service.ServiceSearch;
import com.showdownstudio.kweb_11streetapi_study.Thread.ProductSearchThread;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText keywordEdt;
    private Button searchBtn;
    private ListView listView;

    private List<Product> productList;

    private ProductAdapter adapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (msg.arg1 == 10) {
                    ProductSearchResponseRoot prr = (ProductSearchResponseRoot) msg.obj;
                    ProductSearchResponse ps = prr.getProductSearchResponse();
                    Products pss = ps.getProducts();
                    productList = pss.getProduct();

                    adapter.updateData(productList);
                }
                if (msg.arg1 == -1) {
                    Log.i("Search handler", "Error");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keywordEdt = (EditText) findViewById(R.id.main_keyword_edt);
        searchBtn = (Button) findViewById(R.id.main_search_btn);
        listView = (ListView) findViewById(R.id.main_listView);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, R.layout.list_product_item, productList);
        listView.setAdapter(adapter);

        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String keyword = keywordEdt.getText().toString();
        ServiceSearch service = new ServiceSearch();
        service.setKeyword(keyword);

        ProductSearchThread thread = new ProductSearchThread(service, handler);
        thread.start();
    }
}
