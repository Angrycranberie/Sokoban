package Main;

import java.io.*;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws IOException {

        Properties p = new Properties();

        String s = System.getProperty("user.home");
        File cfg = new File("C:\\Users\\mathd\\IdeaProjects\\src\\APNEE\\defaut.cfg");
        File soko = new File(s + "\\.sokoban\\.sokoban");
        boolean exist = cfg.exists();
        boolean sokoExist = soko.exists();


        if(sokoExist){
            FileInputStream f = new FileInputStream(soko);
            FileOutputStream cor = new FileOutputStream(soko);
            p.load(f);
            p.putIfAbsent("Sequence", "Tableau");
            System.out.println(p.get("Sequence"));
            p.store(cor, "Type sequence");


        } else if (exist) {

            FileInputStream f = new FileInputStream(cfg);
            p.load(f);
            f.close();

        }
        if(!exist){

            FileOutputStream f = new FileOutputStream(cfg);
            p.put("Sequence", "Tableau");
            p.store(f,"Type Sequence");
            f.close();
        }
        System.out.println(System.getProperty("user.home"));
        System.out.println(p.get("Sequence"));

    }

}
