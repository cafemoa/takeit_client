package mkworld29.mobile.com.cafemoa.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */
import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit=null;
    private static final String API_URL = "http://rest.takeitnow.kr/";
    //private static final String API_URL = "http://192.168.10.104/";
    static public  Retrofit getInstance(Context context){
        if(retrofit==null){
            final SharedPreference preference=SharedPreference.getInstance(context);
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    String token=preference.get("Authorization");
                    //Log.d("TAG", token);
                    Request.Builder  requestBuilder = chain.request().newBuilder();

                    if(!token.equals("")){
                        //requestBuilder.addHeader("Authorization", "JWT "+token);
                    }

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            OkHttpClient client = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL) // 통신 url
                    .addConverterFactory(GsonConverterFactory.create()) // json통신 여부
                    .client(client)
                    .build();
        }
        return retrofit;
    }
    static public String getApiUrl(){ return API_URL; }
}