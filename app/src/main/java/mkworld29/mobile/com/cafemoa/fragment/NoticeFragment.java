package mkworld29.mobile.com.cafemoa.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import mkworld29.mobile.com.cafemoa.R;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class NoticeFragment extends Fragment{

    private static final String TAG_RESULTS="posts";
    private static final String TAG_WRITER = "writer";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DATE = "regist_day";
    private static final String TAG_CONTENT = "content";

    private RecyclerView rv;
    private ArrayList<HashMap<String,String>> cardList;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        cardList = new ArrayList<HashMap<String, String>>();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLinearLayoutManager);


        return view;
    }

    /** JSON 파싱 메소드 **/
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String,Void,String> {
            @Override
            protected String doInBackground(String... params) {
                //JSON 받아온다.
                String uri = params[0];
                BufferedReader br = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String json;
                    while((json = br.readLine()) != null) {
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e) {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String myJSON) {
                makeList(myJSON); //리스트를 보여줌
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
    /** JSON -> LIST 가공 메소드 **/
    public void makeList(String myJSON) {
        for (int i = 0; i < 3; i++) {
            //JSON에서 각각의 요소를 뽑아옴
            String title = "testest";

            //HashMap에 붙이기
            HashMap<String, String> posts = new HashMap<String, String>();
            posts.put(TAG_TITLE, title);
            posts.put(TAG_WRITER, title);
            posts.put(TAG_DATE, title);
            posts.put(TAG_CONTENT, title);

            //ArrayList에 HashMap 붙이기
            cardList.add(posts);
        }
        //카드 리스트뷰 어댑터에 연결
        //CardAdapter adapter = new CardAdapter(getActivity(), cardList);
        //Log.e("onCreate[noticeList]", "" + cardList.size());
        //rv.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }


}
