package at.ac.fhcampuswien;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.naming.ldap.Control;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWriter {
    private static String dirPath = System.getProperty("user.dir");
    public static int AnzahlFragenEinfach, AnzahlFragenMittel, AnzahlFragenSchwer;

    public static void JsonWriter(){

        try {
            File Fragen = new File(dirPath + "//src//rsrc//json//Frage1.json");

            String content = new String(Files.readAllBytes(Paths.get(Fragen.toURI())), "UTF-8");
            //System.out.println(content);
            JSONObject json = new JSONObject(content);
            JSONArray arraySchwierigkeit = json.getJSONArray("Difficulty");

            JSONObject schwierigkeitEinfach = arraySchwierigkeit.getJSONObject(0);
            AnzahlFragenEinfach = schwierigkeitEinfach.getInt("AnzahlFragenEinfach");
            System.out.println(AnzahlFragenEinfach);

            JSONObject schwierigkeitMittel = arraySchwierigkeit.getJSONObject(1);
            AnzahlFragenMittel = schwierigkeitMittel.getInt("AnzahlFragenMittel");
            System.out.println(AnzahlFragenMittel);

            JSONObject schwierigkeitSchwer = arraySchwierigkeit.getJSONObject(2);
            AnzahlFragenSchwer = schwierigkeitSchwer.getInt("AnzahlFragenSchwer");
            System.out.println(AnzahlFragenSchwer);

            setAnzahlFragenEinfach(AnzahlFragenEinfach);
            setAnzahlFragenMittel(AnzahlFragenMittel);
            setAnzahlFragenSchwer(AnzahlFragenSchwer);




            JSONObject objektSchwierigkeit = arraySchwierigkeit.getJSONObject(ControllerMgmt.getSchwierigkeit());
            System.out.println(objektSchwierigkeit);
            JSONObject objektSchwierigkeitEinfach = arraySchwierigkeit.getJSONObject(0);
            System.out.println(objektSchwierigkeitEinfach);
            JSONObject objektSchwierigkeitMittel = arraySchwierigkeit.getJSONObject(1);
            System.out.println(objektSchwierigkeitMittel);
            JSONObject objektSchwierigkeitSchwer = arraySchwierigkeit.getJSONObject(2);
            System.out.println(objektSchwierigkeitSchwer);
            JSONArray arrayFragen = objektSchwierigkeit.getJSONArray("Fragen");
            //System.out.println(arrayFragen);
            FileWriter fw = new FileWriter(dirPath + "//src//rsrc//json//Frage1.json");
            BufferedWriter bw = new BufferedWriter(fw);

            if(ControllerMgmt.getIsSendPressed() == true && ControllerMgmt.getSchwierigkeit()==0){
                String alleFragen ="";
                for(int i=0; i<AnzahlFragenEinfach; i++){
                    JSONObject ObjektFrage = arrayFragen.getJSONObject(i);
                    String Frage = ObjektFrage.toString()+",";
                    alleFragen = alleFragen + Frage;
                }
                System.out.println(alleFragen);

                JSONObject ObjektFrage = arrayFragen.getJSONObject(AnzahlFragenEinfach-1);
                String Frage = ObjektFrage.toString();
                AnzahlFragenEinfach++;
                String fragenzusatz = "\t\t\t{\n" +
                        "\t\t\t\"F"+AnzahlFragenEinfach + "\":\n" +
                        "\t\t\t\t[\n" +
                        "\t\t\t\t\t{\"Frage"+AnzahlFragenEinfach+"\":\""+ ControllerMgmt.getFrage() +"\"},\n" +
                        "\t\t\t\t\t{\"A"+AnzahlFragenEinfach+"\": [\n" +
                        "\t\t\t\t\t\t{\"Antwort0\":\""+ControllerMgmt.getRichtigeAntwort()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort1\":\""+ControllerMgmt.getFalscheAntwort1()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort2\":\""+ControllerMgmt.getFalscheAntwort2()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort3\":\""+ControllerMgmt.getFalscheAntwort3()+"\"}\n" +
                        "\t\t\t\t\t\t]\n" +
                        "\t\t\t\t\t}\n" +
                        "\t\t\t\t]\n" +
                        "\t\t\t}";

                String fragenperSchwierigkeit = "{ \"Difficulty\": [ \n { \"AnzahlFragenEinfach\":\""+AnzahlFragenEinfach+"\", \"Fragen\": ["+alleFragen+fragenzusatz+"]}, \n"+objektSchwierigkeitMittel + ", \n" + objektSchwierigkeitSchwer + "]}";

                bw.write(fragenperSchwierigkeit);
                bw.close();

            }else if(ControllerMgmt.getIsSendPressed() == true && ControllerMgmt.getSchwierigkeit()==1){
                String alleFragen ="";
                for(int i=0; i<AnzahlFragenMittel; i++){
                    JSONObject ObjektFrage = arrayFragen.getJSONObject(i);
                    String Frage = ObjektFrage.toString()+",";
                    alleFragen = alleFragen + Frage;
                }
                System.out.println(alleFragen);

                JSONObject ObjektFrage = arrayFragen.getJSONObject(AnzahlFragenMittel-1);
                String Frage = ObjektFrage.toString();
                AnzahlFragenMittel++;
                String fragenzusatz = "\t\t\t{\n" +
                        "\t\t\t\"F"+AnzahlFragenMittel + "\":\n" +
                        "\t\t\t\t[\n" +
                        "\t\t\t\t\t{\"Frage"+AnzahlFragenMittel+"\":\""+ ControllerMgmt.getFrage() +"\"},\n" +
                        "\t\t\t\t\t{\"A"+AnzahlFragenMittel+"\": [\n" +
                        "\t\t\t\t\t\t{\"Antwort0\":\""+ControllerMgmt.getRichtigeAntwort()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort1\":\""+ControllerMgmt.getFalscheAntwort1()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort2\":\""+ControllerMgmt.getFalscheAntwort2()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort3\":\""+ControllerMgmt.getFalscheAntwort3()+"\"}\n" +
                        "\t\t\t\t\t\t]\n" +
                        "\t\t\t\t\t}\n" +
                        "\t\t\t\t]\n" +
                        "\t\t\t}";

                String fragenperSchwierigkeit = "{ \"Difficulty\": [ \n "+ objektSchwierigkeitEinfach +", \n { \"AnzahlFragenMittel\":\""+AnzahlFragenMittel+"\", \"Fragen\": ["+alleFragen+fragenzusatz+"]}, \n" + objektSchwierigkeitSchwer + "]}";

                bw.write(fragenperSchwierigkeit);
                bw.close();

            }
            else if(ControllerMgmt.getIsSendPressed() == true && ControllerMgmt.getSchwierigkeit()==2){
                String alleFragen ="";
                for(int i=0; i<AnzahlFragenSchwer; i++){
                    JSONObject ObjektFrage = arrayFragen.getJSONObject(i);
                    String Frage = ObjektFrage.toString()+",";
                    alleFragen = alleFragen + Frage;
                }
                System.out.println(alleFragen);

                JSONObject ObjektFrage = arrayFragen.getJSONObject(AnzahlFragenSchwer-1);
                String Frage = ObjektFrage.toString();
                AnzahlFragenSchwer++;
                String fragenzusatz = "\t\t\t{\n" +
                        "\t\t\t\"F"+AnzahlFragenSchwer + "\":\n" +
                        "\t\t\t\t[\n" +
                        "\t\t\t\t\t{\"Frage"+AnzahlFragenSchwer+"\":\""+ ControllerMgmt.getFrage() +"\"},\n" +
                        "\t\t\t\t\t{\"A"+AnzahlFragenSchwer+"\": [\n" +
                        "\t\t\t\t\t\t{\"Antwort0\":\""+ControllerMgmt.getRichtigeAntwort()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort1\":\""+ControllerMgmt.getFalscheAntwort1()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort2\":\""+ControllerMgmt.getFalscheAntwort2()+"\"},\n" +
                        "\t\t\t\t\t\t{\"Antwort3\":\""+ControllerMgmt.getFalscheAntwort3()+"\"}\n" +
                        "\t\t\t\t\t\t]\n" +
                        "\t\t\t\t\t}\n" +
                        "\t\t\t\t]\n" +
                        "\t\t\t}";

                String fragenperSchwierigkeit = "{ \"Difficulty\": [ \n  \n"+objektSchwierigkeitEinfach + ", \n" + objektSchwierigkeitMittel+ ", \n " + "{\"AnzahlFragenSchwer\":\""+AnzahlFragenSchwer+"\", \"Fragen\": ["+alleFragen+fragenzusatz+"]}]}";
                bw.write(fragenperSchwierigkeit);
                bw.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setAnzahlFragenEinfach(int anzahlFragenEinfach) {
        AnzahlFragenEinfach = anzahlFragenEinfach;
    }

    public static void setAnzahlFragenMittel(int anzahlFragenMittel) {
        AnzahlFragenMittel = anzahlFragenMittel;
    }

    public static void setAnzahlFragenSchwer(int anzahlFragenSchwer) {
        AnzahlFragenSchwer = anzahlFragenSchwer;
    }

    public static int getAnzahlFragenEinfach() throws IOException {
        File Fragen = new File(dirPath + "//src//rsrc//json//Frage1.json");

        String content = new String(Files.readAllBytes(Paths.get(Fragen.toURI())), "UTF-8");
        //System.out.println(content);
        JSONObject json = new JSONObject(content);
        JSONArray arraySchwierigkeit = json.getJSONArray("Difficulty");

        JSONObject schwierigkeitEinfach = arraySchwierigkeit.getJSONObject(0);
        AnzahlFragenEinfach = schwierigkeitEinfach.getInt("AnzahlFragenEinfach");

        return AnzahlFragenEinfach;
    }
    public static int getAnzahlFragenMittel() throws IOException {
        File Fragen = new File(dirPath + "//src//rsrc//json//Frage1.json");

        String content = new String(Files.readAllBytes(Paths.get(Fragen.toURI())), "UTF-8");
        //System.out.println(content);
        JSONObject json = new JSONObject(content);
        JSONArray arraySchwierigkeit = json.getJSONArray("Difficulty");
        JSONObject schwierigkeitMittel = arraySchwierigkeit.getJSONObject(1);
        AnzahlFragenMittel = schwierigkeitMittel.getInt("AnzahlFragenMittel");

        return AnzahlFragenMittel;
    }
    public static int getAnzahlFragenSchwer() throws IOException {
        File Fragen = new File(dirPath + "//src//rsrc//json//Frage1.json");

        String content = new String(Files.readAllBytes(Paths.get(Fragen.toURI())), "UTF-8");
        //System.out.println(content);
        JSONObject json = new JSONObject(content);
        JSONArray arraySchwierigkeit = json.getJSONArray("Difficulty");
        JSONObject schwierigkeitSchwer = arraySchwierigkeit.getJSONObject(2);
        AnzahlFragenSchwer = schwierigkeitSchwer.getInt("AnzahlFragenSchwer");
        return AnzahlFragenSchwer;
    }

}
