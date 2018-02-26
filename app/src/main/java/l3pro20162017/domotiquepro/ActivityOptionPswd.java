package l3pro20162017.domotiquepro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class ActivityOptionPswd extends Activity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ){

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_option_pswd );

        Button btn_create = (Button) findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), ActivityCreatePswd.class);
                startActivity(intent);
                finish();
            }
        });
    }

}