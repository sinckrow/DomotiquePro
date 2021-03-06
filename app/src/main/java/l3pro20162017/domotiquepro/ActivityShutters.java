package l3pro20162017.domotiquepro;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ActivityShutters extends AppCompatActivity {
    SeekBar s,s2,s3,s4;
    int progress_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shutters);
        getSupportActionBar().hide();
        s = (SeekBar) findViewById(R.id.seekBar);
        s2 = (SeekBar) findViewById(R.id.seekBar2);
        s3 = (SeekBar) findViewById(R.id.seekBar3);
        s4 = (SeekBar) findViewById(R.id.seekBar4);

        Button btn_liste = (Button) findViewById(R.id.btn_liste);

        btn_liste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityShutters.this, ActivityListActions.class);
                startActivityForResult(intent, 2);
                finish();
            }
        });
        s.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        progress_value = seekBar.getProgress();
                        if (progress_value == 100) {
                            seekBar.setProgress(50);
                            System.out.println("etatvolet : ferme volet");
                            final String libelle = "Tout fermer";
                            final String code = "ALL DOWN";
                            int i_captcha = 1;
                            actionSeekBar(libelle,code,i_captcha,"ferme tout les volets");
                        }
                        if (progress_value == 0) {
                            seekBar.setProgress(50);
                            //int _id = 0;
                            final String libelle = "Tout ouvrir";
                            final String code = "ALL UP";
                            int i_captcha = 1;
                            actionSeekBar(libelle,code,i_captcha,"ouvre tout les volets ");
                        }

                    }
                });


    s2.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            progress_value = seekBar.getProgress();
            if (progress_value == 100) {
                seekBar.setProgress(50);
                final String libelle = "fermer Ouest";
                final String code = "OUEST DOWN";
                int i_captcha = 0;
                actionSeekBar(libelle,code,i_captcha,"ferme les volets coté Ouest");
            }
            if (progress_value == 0) {
                seekBar.setProgress(50);
                //int _id = 0;
                final String libelle = "ouvrir Ouest";
                final String code = "OUEST UP";
                int i_captcha = 0;
                actionSeekBar(libelle,code,i_captcha,"ouvre les volets coté Ouest");
            }

        }
    });

        s3.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        progress_value = seekBar.getProgress();
                        if (progress_value == 100) {
                            seekBar.setProgress(50);
                            final String libelle = "fermer Est";
                            final String code = "EST DOWN";
                            int i_captcha = 0;
                            actionSeekBar(libelle,code,i_captcha,"ferme les volets coté Est");
                        }
                        if (progress_value == 0) {
                            seekBar.setProgress(50);
                            //int _id = 0;
                            final String libelle = "ouvrir Est";
                            final String code = "EST UP";
                            int i_captcha = 0;
                            actionSeekBar(libelle,code,i_captcha,"ouvre les volets coté Est");
                        }

                    }
                });
        s4.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        progress_value = seekBar.getProgress();
                        if (progress_value == 100) {
                            seekBar.setProgress(50);
                            final String libelle = "fermer Sud";
                            final String code = "SUD DOWN";
                            int i_captcha = 0;
                            actionSeekBar(libelle,code,i_captcha,"ferme les volets coté Sud");
                        }
                        if (progress_value == 0) {
                            seekBar.setProgress(50);
                            final String libelle = "ouvrir Sud";
                            final String code = "SUD UP";
                            int i_captcha = 0;
                            actionSeekBar(libelle,code,i_captcha,"ouvre les volets coté Sud");
                        }

                    }
                });
}



    public void actionSeekBar(String libelle,final String code, int i_captcha,String message) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String numeroValue = preferences.getString(KeyWords.NUMERO_TELEPHONE, "");
        final int compteurValue = preferences.getInt(KeyWords.COMPTEUR, -1);
        final String cheminCle = preferences.getString(KeyWords.cheminCle, "");

        boolean captcha;
        if (i_captcha == 1)
            captcha = true;
        else
            captcha = false;

        if (captcha) {
            Intent intent = new Intent(getApplicationContext(), ActivityUsePswd.class);
            intent.putExtra("numeroValue", numeroValue);
            intent.putExtra("compteurValue", compteurValue);
            intent.putExtra("code", code);
            intent.putExtra("cheminCle", cheminCle);
            intent.putExtra("pageVolet", 1);
            incrementationCompteur();
            startActivity(intent);
            finish();
            Toast.makeText(ActivityShutters.this, message, Toast.LENGTH_SHORT).show();
        } else {


                    if ((numeroValue.length() > 0) && (compteurValue != -1) && !cheminCle.isEmpty()) {
                        sendSms(numeroValue, code, compteurValue, cheminCle);

                    } else {
                        Toast.makeText(ActivityShutters.this, "Erreur tel ou compteur not init", Toast.LENGTH_SHORT).show();
                    }
            Toast.makeText(ActivityShutters.this, message, Toast.LENGTH_SHORT).show();
                }



    }

    public void sendSms(String phoneNo, String actionCode, int counter,String cheminCle){

        long timestamp = System.currentTimeMillis()/1000;
        //String chemin = getFilesDir().getPath();
        String msg = actionCode+";"+timestamp+";"+counter;
        PublicKey clePublique = GestionCleRSA.lectureClePublique(cheminCle);
        String lemsg = "";
        byte[] bytes = null;
        try {
            Cipher chiffreur = Cipher.getInstance("RSA/NONE/PKCS1Padding");
            chiffreur.init(Cipher.ENCRYPT_MODE, clePublique);
            bytes = chiffreur.doFinal(msg.getBytes("ISO-8859-2"));
            lemsg = Base64.encodeToString(bytes,Base64.DEFAULT);

        } catch(NoSuchAlgorithmException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(NoSuchPaddingException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(InvalidKeyException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(IllegalBlockSizeException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch(BadPaddingException e) {
            System.err.println("Erreur lors du chiffrement : " + e);
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, lemsg, null, null);
            //Toast.makeText(ActivityListActions.this, "Sms send "+lemsg ,Toast.LENGTH_SHORT).show();
            incrementationCompteur();
        }
        catch (Exception e){
            //Toast.makeText(ActivityListActions.this, "Sms fail "+lemsg ,Toast.LENGTH_SHORT).show();
            System.out.println("-------------------------------"+e);
        }
    }

    private void incrementationCompteur() {
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println("-------------------OK");
        int compteur = preferences.getInt(KeyWords.COMPTEUR, -1);
        compteur++;
        System.out.println("-------------------"+compteur);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KeyWords.COMPTEUR, compteur);
        editor.commit();
    }
}



