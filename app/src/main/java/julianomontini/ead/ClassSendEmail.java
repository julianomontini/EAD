package julianomontini.ead;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ClassSendEmail {
    private final static String ip = "http://192.168.1.36:3000";

    public void sendConf(Context context, String email, int codigo){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url =ip + "/conf/" + email + "/" +codigo;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    public void resetPass(final Context context, String email, int senha){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url =ip + "/reset/" + email + "/" +senha;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}
