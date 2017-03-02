package ca.campbell.simplecustomlv;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        String name;
        int id;

        TextView tv = (TextView) findViewById(R.id.tv);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        if ( getIntent().hasExtra("dog_name") ) {
            tv.setText(getIntent().getExtras().getString("dog_name"));
        } else {
            tv.setText("no name");
            tv.setTextColor(Color.CYAN);
        }

        if ( getIntent().hasExtra("dog_image") ) {
            iv.setImageResource(getIntent().getExtras().getInt("dog_image"));
        } else {
            iv.setImageResource(R.drawable.ic_launcher);

        }
    }
}
