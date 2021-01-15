
package at.ac.fhcampuswien;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import org.json.*;

public class JsonReader {

    private static String dirPath = System.getProperty("user.dir");
    static Random random = new Random();
    public static int fragennummer, fragennummerEinfach, fragennummerMittel, fragennummerSchwer;
    public static String fragestellung;
    public static String[] antwort = new String[4];
    private int anzahlFragenEinfach;
    private int anzahlFragenMittel;
    private int anzahlFragenSchwer;
    private static ArrayList einfach = new ArrayList();
    private static ArrayList mittel = new ArrayList();
    private static ArrayList schwer = new ArrayList();



    public static String frageAuslesen(int rundennummer) throws IOException {

        // System.out.println(System.getProperty("user.dir")); --> Test für relativen Pfad.

        int AnzahlEinfach = JsonWriter.getAnzahlFragenEinfach();
        int AnzahlMittel = JsonWriter.getAnzahlFragenMittel();
        int AnzahlSchwer = JsonWriter.getAnzahlFragenSchwer();








        try {
            File Fragen = new File(dirPath + "//src//rsrc//json//Frage1.json");

            String content = new String(Files.readAllBytes(Paths.get(Fragen.toURI())), "UTF-8");
            //System.out.println(content);
            JSONObject json = new JSONObject(content);
            JSONArray arraySchwierigkeit = json.getJSONArray("Difficulty");
            JSONObject objektSchwierigkeit = null;
            if(rundennummer<=5){
                objektSchwierigkeit = arraySchwierigkeit.getJSONObject(0);
                fragennummerEinfach = random.nextInt(AnzahlEinfach)+1;
                while (einfach.contains(fragennummerEinfach)){
                    fragennummerEinfach = random.nextInt(AnzahlEinfach)+1;
                }
                einfach.add(fragennummerEinfach);
                fragennummer = fragennummerEinfach;
            }
            else if(rundennummer>5 && rundennummer <= 10){
                objektSchwierigkeit = arraySchwierigkeit.getJSONObject(1);
                fragennummerMittel = random.nextInt(AnzahlMittel);
                while (mittel.contains(fragennummerMittel)){
                    fragennummerMittel = random.nextInt(AnzahlMittel);
                }
                mittel.add(fragennummerMittel);
                fragennummer = fragennummerMittel;
            }else if(rundennummer>10 && rundennummer <= 15){
                objektSchwierigkeit = arraySchwierigkeit.getJSONObject(2);
                fragennummerSchwer = random.nextInt(AnzahlSchwer);
                while (schwer.contains(fragennummerSchwer)){
                    fragennummerSchwer = random.nextInt(AnzahlSchwer);
                }
                schwer.add(fragennummerSchwer);
                fragennummer = fragennummerSchwer;
            }

            JSONArray arrayFragen = objektSchwierigkeit.getJSONArray("Fragen");
            JSONObject objektFragenNummer = arrayFragen.getJSONObject(fragennummer-1);
            //System.out.println(objektFragenNummer);
            JSONArray arrayFragenInhalt = objektFragenNummer.getJSONArray("F"+fragennummer);
            JSONObject objektFrage = arrayFragenInhalt.getJSONObject(0);





            fragestellung = objektFrage.getString("Frage"+fragennummer);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragestellung;
    }



    public static String antwortenAuslesen(int antwortmöglichkeit, int rundennummer){


        try {
            File Fragen = new File(dirPath + "//src//rsrc//json//Frage1.json");
            //System.out.println(fragennummer);
            String content = new String(Files.readAllBytes(Paths.get(Fragen.toURI())), "UTF-8"); // bekomme den Inhalt von Fragen.json
            //System.out.println(content);
            JSONObject json = new JSONObject(content);

            JSONObject objektSchwierigkeit = null;

            JSONArray arraySchwierigkeit = json.getJSONArray("Difficulty");

            if(rundennummer<=5){
                objektSchwierigkeit = arraySchwierigkeit.getJSONObject(0);
            }
            else if(rundennummer>5 && rundennummer <= 10){
                objektSchwierigkeit = arraySchwierigkeit.getJSONObject(1);
            }else if(rundennummer>10 && rundennummer <= 15){
                objektSchwierigkeit = arraySchwierigkeit.getJSONObject(2);

            }


            JSONArray arrayFragen = objektSchwierigkeit.getJSONArray("Fragen");
            JSONObject objektFragenNummer = arrayFragen.getJSONObject(fragennummer-1);
            //System.out.println(objektFragenNummer);
            JSONArray arrayFragenInhalt = objektFragenNummer.getJSONArray("F"+fragennummer);
            JSONObject objektAntworten = arrayFragenInhalt.getJSONObject(1);
            JSONArray arrayAntwortenInhalt = objektAntworten.getJSONArray("A"+fragennummer);

            for(int i =0;i<4;i++){
                JSONObject objektInhaltAntwortenMittel = arrayAntwortenInhalt.getJSONObject(i);
                String antwortnr = "Antwort" + i;
                antwort[i]= objektInhaltAntwortenMittel.getString(antwortnr);
                //die richtige Antwort ist immer antwort[0], angezeigt im Spielfeld kann sie immer noch random werden, aber antwort[0] ist immer die richtige
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return antwort[antwortmöglichkeit];

    }


}