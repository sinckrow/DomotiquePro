package l3pro20162017.domotiquepro;


import android.widget.Adapter;

import java.net.PortUnreachableException;
import java.security.PublicKey;

public class Action{
    private int     id;
    private String  libelle;
    private String  code;
    private boolean confirm;
    private boolean option;

    public Action(){}

    public Action(String libelle, String code){
        this.id = -1;
        this.libelle = libelle;
        this.code = code;
        this.confirm = false;
        this.option =false;
    }

    public Action(String libelle, String code, boolean confirm, boolean option){
        this.id = -1;
        this.libelle = libelle;
        this.code = code;
        this.confirm = confirm;
        this.option=option;
    }

    public Action(int id, String libelle, String code, boolean confirm,boolean option){
        this.id = id;
        this.libelle = libelle;
        this.code = code;
        this.confirm = confirm;
        this.option=option;
    }

    public String getLibelle(){
        return libelle;
    }

    public  void setLibelle(String libelle){
        this.libelle = libelle;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public  boolean getConfirm(){
        return confirm;
    }

    public  void setConfirm(boolean confirm){
        this.confirm = confirm;
    }

    public  boolean getOption(){
        return confirm;
    }
    public  void setOption(boolean option){
        this.option = option;
    }

}
