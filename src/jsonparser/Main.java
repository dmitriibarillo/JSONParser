/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author darthvader
 */



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fullFileLocation = String.format("jsondata/%s.json", "01");
        FileInputStream f = new FileInputStream(fullFileLocation);;      
        JsonParser parser = new JsonParser();
        JsonComponent obj = parser.parse(f);
        Map<String, JsonComponent>  jsonObj = obj.asMap();
        Set<Entry<String, JsonComponent>> entrySet = jsonObj.entrySet();
        for (Entry<String, JsonComponent> entry : entrySet) {
                JsonComponent value = entry.getValue();
        }
        f.close();
        
    }
    
}
