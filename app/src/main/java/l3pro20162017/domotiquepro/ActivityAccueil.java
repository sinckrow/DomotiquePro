package l3pro20162017.domotiquepro;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityAccueil extends AppCompatActivity {

    private static int CODE_RESULT_OPTIONS = 1;
    private static int CODE_RESULT_ACTIONS = 2;
    private static int CODE_RESULT_LOG = 3;
    private static int CODE_RESULT_PSW = 4;
    String numero;
    int compteur;
    Boolean defaut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);



        //Demande et ajout des permissions (ici pour lecture sms)
        if (ContextCompat.checkSelfPermission(ActivityAccueil.this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityAccueil.this,
                    Manifest.permission.READ_SMS)) {
                ActivityCompat.requestPermissions(ActivityAccueil.this,
                        new String[]{Manifest.permission.READ_SMS}, 2);
            } else {
                ActivityCompat.requestPermissions(ActivityAccueil.this,
                        new String[]{Manifest.permission.READ_SMS}, 2);
            }
        }

        //Demande et ajout des permissions (ici pour l'envoie sms)
        if (ContextCompat.checkSelfPermission(ActivityAccueil.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityAccueil.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(ActivityAccueil.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(ActivityAccueil.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        numero = preferences.getString(KeyWords.NUMERO_TELEPHONE,"");
        compteur = preferences.getInt(KeyWords.COMPTEUR,-1);
        defaut = preferences.getBoolean(String.valueOf(KeyWords.defaut), false);

        Button btn_action = (Button) findViewById(R.id.activity_accueil_btn_action);
        Button btn_options = (Button) findViewById(R.id.activity_accueil_btn_options);
        Button btn_pswd = (Button) findViewById(R.id.activity_accueil_btn_pswd);
        Button btn_log = (Button) findViewById(R.id.activity_accueil_btn_historique);

        btn_action.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!(numero.length()>0) || compteur == -1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Application non configurée, veuillez la configurer");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ActivityAccueil.this, ActivityMenuOptions.class);
                            startActivityForResult(intent, CODE_RESULT_OPTIONS);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
                else{

                    //Intent intent = new Intent(ActivityAccueil.this, ActivityListActions.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    defaut = preferences.getBoolean(String.valueOf(KeyWords.defaut), false);
                    if(defaut==true){
                        Intent intent = new Intent(ActivityAccueil.this, ActivityListActions.class);
                        startActivityForResult(intent, CODE_RESULT_ACTIONS);
                    }
                    else
                    {
                        Intent intent = new Intent(ActivityAccueil.this, ActivityShutters.class);
                        startActivityForResult(intent, 15);
                    }
                }
            }
        });

        btn_options.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAccueil.this, ActivityMenuOptions.class);
                startActivityForResult(intent, CODE_RESULT_OPTIONS);
            }
        });

        btn_pswd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAccueil.this, ActivityOptionPswd.class);
                startActivityForResult(intent, CODE_RESULT_PSW);
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAccueil.this, ActivityListLog.class);
                startActivityForResult(intent, CODE_RESULT_LOG);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //pet l'importe d'ou l'on reviens on maj le num et le compteur
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        numero = preferences.getString(KeyWords.NUMERO_TELEPHONE,"");
        compteur = preferences.getInt(KeyWords.COMPTEUR,-1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(ActivityAccueil.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permissions envoie sms ok", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Pas de permissions envoie sms", Toast.LENGTH_SHORT).show();
                }
                break;

            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(ActivityAccueil.this,
                            Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permissions lecture sms ok", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Pas de permissions lecture sms", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
