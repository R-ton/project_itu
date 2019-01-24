package function;
import java.lang.reflect.Field;
public class Champ {
    Field attrib;
    Boolean visible;
    String[] defaut;
    String type;
    public Champ() {
        this.visible=true;
        this.type="text";
    }
    public String gettype() {
        return this.type;
    }
    public Field getattrib() {
        return this.attrib;
    }
    public Boolean getvisible() {
        return this.visible;
    }
    public String[] getdefaut() {
        return this.defaut;
    }
    public void settype(String a) {
        this.type=a;
    }
    public void setvisible(Boolean a) {
        this.visible=a;
    }
      public void setdefaut(String[] a) {
        this.defaut=a;
    }
     public void setattrib(Field a) {
        this.attrib=a;
    }
}