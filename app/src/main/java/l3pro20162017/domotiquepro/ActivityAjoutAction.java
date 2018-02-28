package l3pro20162017.domotiquepro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class ActivityAjoutAction extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions_ajout);

        Button btn_valider = (Button)findViewById(R.id.activity_actions_ajout_btn_valider);
        final EditText editTextNumber = (EditText)findViewById(R.id.editTextNumber);
        final RadioButton rb_number = (RadioButton)findViewById((R.id.rb_nombre));
        final RadioButton rb_chaine = (RadioButton)findViewById((R.id.rbChaine));
        final RadioButton rb_heure = (RadioButton)findViewById((R.id.rb_heure));
        final EditText editTextChaine = (EditText)findViewById(R.id.activity_actions_ajout_input_code_action2);
        final TimePicker timepicker = (TimePicker)findViewById(R.id.timePicker);


        btn_valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final RadioGroup radioGroup  = (RadioGroup)findViewById(R.id.activity_actions_ajout_RadioGroup);

       // check_option.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {

               // if(radioGroup.getVisibility()== (View.GONE)) {
                //    radioGroup.setVisibility((View.VISIBLE));
                //    }else {
                //    radioGroup.setVisibility((View.GONE));
                // }
         //   }
       // });

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radiobuttonId = radioGroup.getCheckedRadioButtonId();
                System.out.println("id : "+radiobuttonId);
                RadioButton rb = (RadioButton) findViewById(radiobuttonId);
                System.out.println("rbText : "+rb.getText());
                if( rb.getText().equals("rb_nombre")) {
                    editTextChaine.setVisibility((View.INVISIBLE));
                    timepicker.setVisibility((View.INVISIBLE));
                    editTextNumber.setVisibility((View.VISIBLE));

                } else if(rb.getText().equals("rbChaine")){
                    editTextNumber.setVisibility((View.INVISIBLE));
                    timepicker.setVisibility((View.INVISIBLE));
                    editTextChaine.setVisibility((View.VISIBLE));
                }
                else if (rb.getText().equals("rb_heure")) {
                    editTextNumber.setVisibility((View.INVISIBLE));
                    editTextChaine.setVisibility((View.INVISIBLE));
                    timepicker.setVisibility((View.VISIBLE));
                }else {
                    editTextNumber.setVisibility((View.INVISIBLE));
                    editTextChaine.setVisibility((View.INVISIBLE));
                    timepicker.setVisibility((View.INVISIBLE));
                }
            }
        }
        );



    }


    @Override
    public void finish(){
        EditText edit_libelle = (EditText)findViewById(R.id.activity_actions_ajout_input_libelle);
        EditText edit_code = (EditText)findViewById(R.id.activity_actions_ajout_input_code_action);
        CheckBox edit_captcha = (CheckBox)findViewById(R.id.activity_actions_ajout_input_check_verif);
        CheckBox check_option = (CheckBox) findViewById(R.id.activity_actions_ajout_input_check_option);


        String libelle = edit_libelle.getText().toString();
        String code = edit_code.getText().toString();
        boolean captcha = edit_captcha.isChecked();
        boolean option = check_option.isChecked();

        if (libelle.trim().isEmpty() || code.trim().isEmpty()){
            Intent data = new Intent();
            setResult(RESULT_CANCELED, data);
            super.finish();
        }
        else {
            Intent data = new Intent();
            data.putExtra("libelle",libelle);
            data.putExtra("code",code);
            data.putExtra("captcha",captcha);
            data.putExtra("option",option);
            setResult(RESULT_OK, data);
            super.finish();
        }
    }
}
