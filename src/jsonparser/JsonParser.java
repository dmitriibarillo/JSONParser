/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import jsonparser.exception.JsonParseException;
import jsonparser.Parser;

/**
 *
 * @author darthvader
 */
public class JsonParser{
    
    /*
    * Парсит из Reader
    */
    public static JsonComponent parse(Reader in) throws IOException, JsonParseException {
        LineColumnNumberReader reader = new LineColumnNumberReader(in);
        return reader.parseJsonRoot();
    }
    
    /*
    * Парсит из Stream'а
    */
    public static JsonComponent parse(InputStream in) throws IOException, JsonParseException {
        LineColumnNumberReader reader = new LineColumnNumberReader(new InputStreamReader(in));
        return reader.parseJsonRoot();
    }
    
    /*
    * Парсит из файла
    */
    public static JsonComponent parse(File in) throws IOException, JsonParseException {
        LineColumnNumberReader reader = new LineColumnNumberReader(new FileReader(in));
        return reader.parseJsonRoot();
    }
    
    /*
    * Парсит из строки
    */  
    public static JsonComponent parse(String string) throws IOException, JsonParseException {
        LineColumnNumberReader reader = new LineColumnNumberReader(new StringReader(string));
        return reader.parseJsonRoot();
    }
    
    /*
    * Сохраняет в Writer
    */
    public static void save(JsonComponent cmp, Writer out) throws IOException {
       out.flush();
       cmp.save(out, 0);
       out.close();
    }
    
    /*
    * Сохраняет в Stream
    */
    public static void save(JsonComponent cmp, OutputStream out) throws IOException {
       out.flush();
       cmp.save(new OutputStreamWriter(out), 0);
       out.close();
    }
    
    /*
    * Сохраняет в файл
    */
    public static void save(JsonComponent cmp, File out) throws IOException {
        cmp.save(new FileWriter(out), 0);
    }
    
}
