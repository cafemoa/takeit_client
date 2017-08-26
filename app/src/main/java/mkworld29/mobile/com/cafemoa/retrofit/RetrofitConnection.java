package mkworld29.mobile.com.cafemoa.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    public class Recent_payment {
        public String cafe_name;
        public int price;
        public String order_time;
        public String menu_name;
        public String image_url;
        public int pk;
        public String orderer_username;
        public int beverage_pk;
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
        @GET("reservation_page/")
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
        @GET("recent_payment_list_by_id/")
        Call<List<Recent_payment>> repoContributors();
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
                @Field("birth_year") int birth_year,
                @Field("birth_month") int birth_month,
                @Field("birth_day") int birth_day,
                @Field("gender") boolean gender
        );
    }
}
