import java.util.concurrent.CopyOnWriteArrayList;

public class Configuration {
    private static Configuration o = null;



    private Configuration() {}

     static Object instance() {
        if(o == null){
            o = new Configuration();
        }
        return o;
     }

    


}
