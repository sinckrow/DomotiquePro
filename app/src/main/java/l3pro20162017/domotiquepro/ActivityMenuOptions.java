package l3pro20162017.domotiquepro;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.rustamg.filedialogs.FileDialog;
import com.rustamg.filedialogs.OpenFileDialog;

import android.support.v7.app.AppCompatActivity;

import java.io.File;


public class ActivityMenuOptions extends AppCompatActivity implements FileDialog.OnFileSelectedListener {
    String numeroValue;
    int compteurValue;
    String cheminCle;
    EditText editNumero;
    EditText editCompteur;
    Button BtnClefPub;
    CheckBox cbxPageDefaut;
    Boolean defaut;

    SharedPreferences preferencesContext;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().hide();

        editNumero = (EditText)findViewById(R.id.activity_options_input_numero);
        editCompteur = (EditText)findViewById(R.id.activity_options_input_compteur);
        BtnClefPub=(Button)findViewById(R.id. activity_option_btn_clefp);
        cbxPageDefaut = (CheckBox)findViewById(R.id.cbxPageDefaut);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        numeroValue = preferences.getString(KeyWords.NUMERO_TELEPHONE, "");
        compteurValue = preferences.getInt(KeyWords.COMPTEUR, -1);
        System.out.println("--------Compteur value base : "+compteurValue);
        cheminCle = preferences.getString(KeyWords.cheminCle, "");
        defaut = preferences.getBoolean(String.valueOf(KeyWords.defaut),false);


        if (ContextCompat.checkSelfPermission(ActivityMenuOptions.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityMenuOptions.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(ActivityMenuOptions.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                ActivityCompat.requestPermissions(ActivityMenuOptions.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            //ne rien faire
        }

        if (!numeroValue.isEmpty()){
            editNumero.setText(numeroValue, TextView.BufferType.EDITABLE);
        }

        if (compteurValue != -1){
            editCompteur.setText(String.valueOf(compteurValue), TextView.BufferType.EDITABLE);
            System.out.println("----------EDIT TEXT-----------------:"+String.valueOf(compteurValue));
        }
        if(cheminCle.isEmpty()){
            BtnClefPub.setText("ouvrir");
        }else{
            BtnClefPub.setText("modifier");
        }
        if(defaut==true){
            cbxPageDefaut.setChecked(true);
        }
        else
            cbxPageDefaut.setChecked(false);

        BtnClefPub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // You can use either OpenFileDialog or SaveFileDialog depending on your needs
                FileDialog dialog = new OpenFileDialog();
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
                dialog.show(getSupportFragmentManager(), OpenFileDialog.class.getName());


            }
        });


        Button btnValider = (Button)findViewById(R.id.activity_options_btn_valider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroValue = editNumero.getText().toString();
                compteurValue=Integer.parseInt(editCompteur.getText().toString());
                if(cbxPageDefaut.isChecked()){
                    defaut=true;
                }
                else{
                    defaut=false;
                }
                preferencesContext = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                editor = preferencesContext.edit();
                editor.putString(KeyWords.NUMERO_TELEPHONE, numeroValue);
                editor.putInt(KeyWords.COMPTEUR, compteurValue);
                System.out.println("--------Compteur value modif : "+compteurValue);
                editor.putString(KeyWords.cheminCle, cheminCle);
                editor.putBoolean(String.valueOf(KeyWords.defaut), defaut);

                boolean isok = editor.commit();
                if(isok)
                    Toast.makeText(v.getContext(), "Modifications enregistrés !", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(v.getContext(), "erreur !", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onFileSelected(FileDialog dialog, File file) {
        System.out.println("nom fichier : "+file.getAbsolutePath());
        cheminCle = file.getAbsolutePath();
        BtnClefPub.setText("modifier");
           }


}