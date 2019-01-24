package function;
import java.sql.*;
import java.util.Vector;
import dbconnect.*;
import java.util.Vector;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class Formulaire {
    Class lequel;
    Champ[] composant;
    String Action;
    public Formulaire(Object o) {
        lequel=o.getClass();
        this.setcomposant();
        String fantarina=lequel.getName().substring(6);
        Action="Insert"+fantarina+".jsp";
    }
    public void setcomposant() {
        Field[] iza=lequel.getDeclaredFields();
        System.out.println(iza.length);
        int firy=iza.length;
        this.composant=new Champ[firy];
        for(int i=0;i<firy;i++) {
            this.composant[i]=new Champ();
            this.composant[i].setvisible(true);
            this.composant[i].settype("text");
            this.composant[i].setattrib(iza[i]);
            String fantarina=lequel.getName().substring(6);
            String tsyAtaohita="id"+fantarina;
            if(iza[i].getName().compareToIgnoreCase(tsyAtaohita)==0) {
                this.composant[i].setvisible(false);
            }
        }
    }
    public Class getlequel() {
        return this.lequel;
    }
    public Champ[] getcomposant() {
        return this.composant;
    }
    public String getAction() {
        return this.Action;
    }
    public String getHTML() {
        String html="<form action="+this.Action+" method=\"POST\">";
                for(int i=0;i<this.composant.length;i++) { 
                if(this.composant[i].getvisible()==true) {
                    html+="<label>"+this.composant[i].getattrib().getName()+"</label>";
                    if(this.composant[i].gettype().equals("text")) {
                        html+="<input type="+this.composant[i].gettype()+" name="+this.composant[i].getattrib().getName()+"> </br>";
                    }
                    if(this.composant[i].gettype().equals("select")) {
                        System.out.println("niditra");
                        html+="<select name="+this.composant[i].getattrib().getName()+">";
                        String[] value=this.composant[i].getdefaut();
                        System.out.println(value.length);
                        for(int f=0;f<value.length;f++) {
                            System.out.println(value[f]);
                            html+="<option value="+value[f]+">"+value[f]+"</option>";
                        }
                        html+="</select></br>";
                    }
                    if(this.composant[i].gettype().equals("checkbox")) {
                        String[] value1=this.composant[i].getdefaut();
                        System.out.println(value1.length);
                        for(int l=0;l<value1.length;l++) {
                            html+="<input type=\"radio\" name="+this.composant[i].getattrib().getName()+" value="+value1[l]+" id="+value1[l]+">";
                            html+="<label for="+value1[l]+">"+value1[l]+"</label>";
                        }
                        html+="</br>";
                    }
                }
             }   
        html+="<input type=\"submit\" value=\"okey\">";
    html+="</form>";
    return html;
    }
    public Champ getChamp(String nom) {
        for(int i=0;i<composant.length;i++) {
            if(composant[i].getattrib().getName().compareToIgnoreCase(nom)==0) {
                return composant[i];
            }
        }
        return null;
    }
    public void setChamp(String nom,Champ newC) {
        for(int i=0;i<composant.length;i++) {
            if(composant[i].getattrib().getName().compareToIgnoreCase(nom)==0) {
                composant[i]=newC;
            }
        }
    }
}