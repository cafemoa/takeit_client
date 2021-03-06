package mkworld29.mobile.com.cafemoa.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zoyi.channel.plugin.android.model.rest.User;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.item.OptionItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
        public boolean is_open;
        public int pk;
        public int min_time;
        public int coupon_num;
        public int coupon_price;

        public List<Beverage> beverages;
    }

    public class Beverage{
        public String image;
        public String name;
        public String price;
        public int pk;
        public int type;
        public boolean is_best;
        public ArrayList<Option> options;
        public class Option{
            public String content;
            public int pk;
            ArrayList<Integer> selections;
        }
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
        public int order_num;
    }

    public class Payment_Complete{
        public int order_num;
        public String order_time;
        public int remain_order;
        public int amount_price;
        public String menu_name;
    }

    public class Payment_ready{
        public String order_num;
        public String user_name;
        public String cafe_name;
        public String user_phonenumber;
        public String cafe_phonenumber;
        public String pay_closetime;
        public int amount_price;
        public String menu_name;

    }

    public class Profile{
        public String name;
        public String profile_picture;
        public String username;
    }

    public static class Order_option{
        int beverage_id;
        int size;
        int shot_num;
        int amount;
        ArrayList<Integer> selections;

        public Order_option(int beverage_id,int size, int shot_num,int amount, ArrayList<Integer> selections){
            this.beverage_id = beverage_id;
            this.selections=selections;
            this.size = size;
            this.shot_num = shot_num;
            this.amount=amount;
        }
    }

    public static class Order_Info{
        int use_coupon;
        Order_option[] beverages;
        public Order_Info(int use_coupon,Order_option[] beverages){
            this.use_coupon=use_coupon;
            this.beverages=beverages;
        }
    }

    public class Token{
        public String token;
    }
/*
    @Root(strict = false)
    static public class Pay_request {
        @Element(name="reshead")
        public ResHead reshead;
        public class ResHead{
            @Attribute(name="error")
            public int error;
            @Attribute(name="error_msg")
            public String error_msg;
        }

        @Element(name="resbody")
        public ResBody resbody;

        public class ResBody{
            @Element(name="response")
            public _Response response;

            public class _Response{
                @Element(name="data")
                public Data data;

                public class Data{
                    @Attribute(name="error")
                    public int error;

                    @Attribute(name="msg")
                    public String msg;

                    @Attribute(name="orderno")
                    public String orderno;

                    @Attribute(name="payno")
                    public String payno;

                    @Attribute(name="paytype")
                    public String paytype;

                    @Attribute(name="payurl")
                    public String payurl;

                    @Attribute(name="useretc1")
                    public String useretc1;

                    @Attribute(name="useretc2")
                    public String useretc2;

                    @Attribute(name="useretc3")
                    public String useretc3;
                }

                @Attribute(name="error")
                public int error;
                @Attribute(name="error_msg")
                public String error_msg;
                @Attribute(name="method")
                public String method;

            }

        }
        //@Attribute(name="payurl")
        //public String pay_url;
    }*/

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

    public interface get_profile{
        @GET("user-manage/")
        Call<Profile> repoContributors();
    }

    public interface save_profile{
        @FormUrlEncoded
        @PUT("user-manage/")
        Call<ResponseBody> repoContributors(
                @Field("now_password") String now_password,
                @Field("password") String password
        );
    }

    public interface delete_user{
        @DELETE("user-manage/")
        Call<ResponseBody> repoContributors();
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
                @Field("birth") String birth,
                @Field("gender") boolean gender
        );
    }

    public interface social_signup{
        @FormUrlEncoded
        @POST("social-api-signup/")
        Call<ResponseBody> repoContributors(
                @Field("name") String name,
                @Field("phone_number") String phone_number,
                @Field("birth") String birth,
                @Field("gender") boolean gender,
                @Field("access_token") String access_token
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
        Call<Payment_Complete> repoContributors(
                @Path("PK") int pk,
                @Body RequestBody body
        );
    }

    public interface thePay_pay_request{
        @POST("http://www.thepay.pro/thepay_if/ProcRequest.action")
        Call<ResponseBody> repoContributors(
                @Body RequestBody body
        );
    }

    public interface ready_payment{
        @POST("ready_payment/{PK}")
        Call<Payment_ready> repoContributors(
                @Path("PK") int pk,
                @Body RequestBody body
        );
    }

    public interface get_cafe_beverage{
        @GET("get_cafe_beverage/{PK}")
        Call<ArrayList<OrderListItem>> repoContributors(
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

    public interface fcm_setActive{
        @FormUrlEncoded
        @PUT("fcm/set_active")
        Call<ResponseBody> repoContributors(
                @Field("is_active") boolean is_active
        );
    }
    public interface email_check{
        @FormUrlEncoded
        @POST("email_check/")
        Call<ResponseBody> repoContributors(
                @Field("email") String email
        );
    }

    public interface get_beverage_option{
        @GET("get_beverage_option/{PK}")
        Call<List<OptionItem>> repoContributors(
                @Path("PK") int pk
        );
    }


}