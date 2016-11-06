package com.showdownstudio.kweb_11streetapi_study.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.showdownstudio.kweb_11streetapi_study.Model.Product;
import com.showdownstudio.kweb_11streetapi_study.R;
import com.showdownstudio.kweb_11streetapi_study.Service.ImageLoader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by bjh970913 on 06/11/2016 - 22:08
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;
    private ImageLoader imageLoader;

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        productList = objects;

        imageLoader= new ImageLoader(context);
    }

    public void updateData(List<Product> objects) {
        productList.clear();
        productList.addAll(objects);
        Log.i("Adapter", productList.size()+" of data sets updated");
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Log.i("Adapter", "create view for "+position);
        ProductViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_product_item, parent, false);
            holder = new ProductViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.product_img);
            holder.productName = (TextView) convertView.findViewById(R.id.product_productName);
            holder.productPrice = (TextView) convertView.findViewById(R.id.product_price);
            holder.productSeller = (TextView) convertView.findViewById(R.id.product_seller);

            convertView.setTag(holder);
        } else {
            holder = (ProductViewHolder) convertView.getTag();
        }

        Product p = productList.get(position);

        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.productName.setText(p.getProductName());
        holder.productPrice.setText("가격: " + p.getProductPrice());
        holder.productSeller.setText("판매자: " + p.getSeller());

        imageLoader.DisplayImage(p.getProductImage(), holder.imageView);

        return convertView;
    }

    private static class ProductViewHolder {
         ImageView imageView;
         TextView productName;
         TextView productPrice;
         TextView productSeller;
    }

    class ImageDownLoader extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public ImageDownLoader(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                BufferedInputStream bi = new BufferedInputStream(url.openStream());
                bitmap = BitmapFactory.decodeStream(bi);
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null)
                imageView.setImageBitmap(result);
        }
    }
}
