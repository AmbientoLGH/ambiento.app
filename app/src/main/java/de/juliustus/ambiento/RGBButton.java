package de.juliustus.ambiento;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RGBButton {

    private LinearLayout linearLayout;
    private String color;
    private String mode = "color";


    public RGBButton(final Context context, LinearLayout linearLayout, final String color, final String mode) {
        this.linearLayout = linearLayout;
        this.color = color;
        this.mode = mode;

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Ausgeführt. "+color,Toast.LENGTH_SHORT).show();
                sendSignal(context);
            }
        });
    }

    public void sendSignal(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        System.out.println(Configuration.host + "update/" + this.color);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configuration.host + "update/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("----> "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("----> "+error.networkResponse+" "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mode", mode);
                params.put("color", color);
                return params;

            }
        };

        queue.add(stringRequest);

    }

    public LinearLayout getView() {
        return linearLayout;
    }

    public static RGBButton createButton(Context context, String color, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout rgbButton = (LinearLayout) inflater.inflate(R.layout.rgb_button,null);
        Drawable drawable = context.getResources().getDrawable(R.drawable.round_button);
        drawable.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);
        rgbButton.setBackground(drawable);

        if (text!=null)
            ((TextView)rgbButton.findViewById(R.id.text)).setText(text);

        String mode  = "color";
        if(text != null) mode = text;

        return new RGBButton(context,rgbButton,color,mode);
    }
}
