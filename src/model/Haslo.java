package model;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Haslo {
    private String iD;
    private String haslo;
    private String opis;
    private int dlugosc;
    public static int ID = 1;

    public Haslo(){
    }

    public Haslo(String haslo, String opis) {
        this.iD = ""+ID;
        this.haslo = haslo;
        this.opis = opis;
        this.dlugosc = haslo.length();
        ID++;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public boolean isPasswordGood(String haslo){
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(haslo);
        return matcher.matches();
    }

    public Map<String,Haslo> init(){
        Map<String, Haslo> hasla = new HashMap<>();
        try (BufferedReader locFile = new BufferedReader(new FileReader("src/Resources/EncyclopedicEntries.txt"))) {
            String input;
            while ((input = locFile.readLine()) != null) {
                String[] data = input.split(",");
                hasla.put(data[0],new Haslo(data[0],data[1]));
                System.out.println(data[0] + ": " + data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hasla;
    }
}
