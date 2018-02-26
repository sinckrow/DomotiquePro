package l3pro20162017.domotiquepro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class ActivityUsePswd extends AppCompatActivity {

    PatternLockView mPatternLockView;
    String password;
    int pageVolet;

    @Override
    protected void onCreate ( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_use_pswd );

        SharedPreferences preferences = getSharedPreferences( "PREFS", 0 );
        password = preferences.getString( "password", "0");
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if ( password.equals( PatternLockUtils.patternToString(mPatternLockView, pattern) ) ) {
                    Handler handler = new Handler();

                    boolean b = handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ActivityListActions ala = new ActivityListActions();
                            Intent intent = getIntent();
                            String numeroValue = intent.getStringExtra("numeroValue");
                            int compteurValue = intent.getIntExtra("compteurValue", 1);
                            String code = intent.getStringExtra("code");
                            String cheminCle = intent.getStringExtra("cheminCle");
                            pageVolet = intent.getIntExtra("pageVolet",0);
                            System.out.println("page numéro :"+pageVolet);
                            if ((numeroValue.length() > 0) && (compteurValue != -1)) {

                                ala.sendSms(numeroValue, code, compteurValue, cheminCle);

                                mPatternLockView.clearPattern();
                            } else {
                                Toast.makeText(ActivityUsePswd.this, "Erreur tel ou compteur not init", Toast.LENGTH_SHORT).show();
                            }
                            Intent intent2;
                            System.out.println("page numéro dans if :"+pageVolet);
                            if(pageVolet==1) {
                                intent2 = new Intent( ActivityUsePswd.this, ActivityShutters.class);

                            }else{
                                intent2 = new Intent( ActivityUsePswd.this, ActivityListActions.class);

                            }
                            startActivity(intent2);
                            finish();

                        }
                    }, 100);


                }else {
                    Handler handler = new Handler();
                    handler.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText( ActivityUsePswd.this, "Schéma incorrect !", Toast.LENGTH_LONG).show();
                            mPatternLockView.clearPattern();
                        }
                    }, 100 );

                }
            }

            @Override
            public void onCleared() {

            }

        });
    }


}