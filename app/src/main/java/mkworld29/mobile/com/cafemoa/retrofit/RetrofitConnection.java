package mkworld29.mobile.com.cafemoa.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by eunseo on 2016-12-09.
 */
public class RetrofitConnection {

    public class Cafe{
        public String tag;
        public String cafe_image;
        public String name;
        public String locationString;
        public int pk;

        public List<Beverage> beverages;
    }

    public class Beverage{
        public String image;
        public String name;
        public String price;
        public int pk;
    }

    public class Coupon_info{
        public Cafe cafe;
        public int coupon_progress;
        public int pk;
    }
    public class Alert{
        public boolean is_event;
        public String content;
        public String cafe_name;
    }

    public class Recent_payment {
        public String cafe_name;
        public String amount_price;
        public String order_time;
        public String menu_name;
        public String cafe_location;
        public String image_url;
        public int pk;
        public String orderer_username;
        public int beverage_pk;
    }

    public static class Order_option{
        int beverage;
        boolean whipping_cream;
        boolean is_ice;
        int size;
        int shot_num;

        public Order_option(int beverage, boolean whipping_cream,boolean is_ice,int size, int shot_num) {
            this.beverage = beverage;
            this.whipping_cream = whipping_cream;
            this.is_ice = is_ice;
            this.size = size;
            this.shot_num = shot_num;
        }
    }

    public static class Order_Info{
        int payment_type;
        Order_option[] options;
        public Order_Info(int payment_type, Order_option[] options){
            this.payment_type=payment_type;
            this.options=options;
        }
    }

    public class Token{
        public String token;
    }

    public interface login {
        @FormUrlEncoded
        @POST("api-auth/")
        Call<Token> repoContributors(
                @Field("username") String id,
                @Field("password") String password
        );
    }
    public interface reservation {
        @GET("get_cafes/")
        Call<List<Cafe>> repoContributors();
    }
    public interface get_alerts {
        @GET("get_alerts/")
        Call<List<Alert>> repoContributors();
    }

    public interface get_cafes {
        @GET("get_cafes/")
        Call<List<Cafe>> repoContributors();
    }

    public interface one_coupon{
        @GET
        Call<RetrofitConnection.Coupon_info> repoContributors(@Url String url);
    }

    public interface all_coupon {
        @GET("get_all_coupons/")
        Call<List<Coupon_info>> repoContributors();
    }

    public interface recent_payments{
        @GET
        Call<List<Recent_payment>> repoContributors(@Url String url);
    }

    public interface recent_payment_list_by_id{
        @GET
        Call<List<Recent_payment>> repoContributors(@Url String url);
    }
    public interface signup{
        @FormUrlEncoded
        @POST("user-manage/")
        Call<ResponseBody> repoContributors(
                @Field("username") String username,
                @Field("password") String password,
                @Field("name") String name,
                @Field("phone_number") String phone_number,
                @Field("email") String email,
                @Field("birth") String birth,
                @Field("gender") boolean gender
        );
    }
    public interface social_auth {
        @FormUrlEncoded
        @POST("social-api-auth/")
        Call<Token> repoContributors(
                @Field("access_token") String access_token
        );
    }

    public interface payment_beverages{
        @POST("order_beverage/{PK}")
        Call<ResponseBody> repoContributors(
                @Path("PK") int pk,
                @Body RequestBody body
        );
    }
    public interface get_cafe_beverage{
        @GET("get_cafe_beverage/{PK}")
        Call<List<Beverage>> repoContributors(
                @Path("PK") int pk
        );
    }

    public interface fcm_register{
        @FormUrlEncoded
        @POST("fcm/devices/")
        Call<ResponseBody> repoContributors(
                @Field("dev_id") String device_id,
                @Field("reg_id") String token,
                @Field("is_active") boolean is_active
        );
    }
}