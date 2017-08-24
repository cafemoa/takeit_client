package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

public class CoffeeOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_option);

        Intent intent = getIntent();

        String menu_name = null;
        String price;
        String image;

        // 옵션 추가를 위해 온 경우
        if(intent != null)
        {
            menu_name = intent.getStringExtra("menu_name");
            price = intent.getStringExtra("menu_price");
            image = intent.getStringExtra("image_url");
        }

        if(menu_name != null)
            Toast.makeText(this, menu_name,Toast.LENGTH_SHORT).show();


        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.shots_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shots_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btn_submit.getId())
        {
            // 여기서 서버처리
        }
    }
}
