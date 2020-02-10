package Global;

import Structures.SequenceList;

import javax.sound.midi.Sequence;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Configuration {
    private static Properties o = null;



    private Configuration() {}

     static public Object instance(FileInputStream f) throws IOException {
        if(o == null){
            o = new Properties();
            o.load(f);
        }
        return o;
     }

     public String lis (String param){
        String value;

        if(!(o.containsKey(param))){
            throw new NoSuchElementException("Le paramètre demandé n'existe pas");
        }else {
            value = o.getProperty(param);
        }
        return value;
     }

     public Sequence nouvelleSequence(){

        if(lis("sequence") == "tableau"){
            return new SequenceTableau()
        } else {
            return new SequenceList();
        }
     }

    


}
