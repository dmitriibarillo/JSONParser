/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import jsonparser.exception.*;

/**
 *
 * @author darthvader
 */
public class LineColumnNumberReader extends LineNumberReader {

    public LineColumnNumberReader(Reader reader) {
        super(reader);
    }
    
    
    /*
    * Пропускает все пробельные символы пока не    
    * наткнется что-то иное (в т.ч. конец файла)
    */
    public void skipWhiteSpaces() throws IOException {
        int c = peek();
        while (c == ' ' || c == '\n' || c == '\t' || c == '\r' ) {
            read();
            c = peek();            
        }        
    }
    
    /*
    * Выполняет skipWhiteSpaces, а затем читает и возвращает 
    * символ 
    */
    public int skipWhiteSpacesAndRead() throws IOException {
        skipWhiteSpaces();
        return read();
    }

    /*
    * Читает следующий символ, но не извлекает его из потока
    */
    public int peek() throws IOException {
        mark(1);
        int c = read();
        reset();
        return c;        
    }

    /*
    * Проверяет следующий символ и кидает исключение, 
    * если что-то пошло не так
    */
    protected void testCharacter(int character, char expected) throws JsonParseException {
        if (character != expected)
            throw new JsonUnexpectedTokenException(getLineNumber(),String.valueOf(character));
    }

    /*
    * Парсит корень Json-документа ({} или [])
    */
    protected JsonComponent parseJsonRoot() throws IOException, JsonParseException {
        skipWhiteSpaces();
        JsonComponent result = parseJsonComponent();
        skipWhiteSpaces();
        int c = peek();
        if (c != -1) {
            throw new JsonUnexpectedEndException(getLineNumber(), String.valueOf((char)c));
        }
        return result;
    }

    /*
    * Парсит произвольный компонент Json. Анализирует тип компонента и
    * вызывает соответствующий метод  
    */
    protected JsonComponent parseJsonComponent() throws IOException, JsonParseException {
        int c = peek();
        switch (c) {
            case 'n':
                return parseJsonNull(); 
            case 't':
                return parseJsonBoolean();
            case 'f':
                return parseJsonBoolean();
            case '"':
                return parseJsonString();
            case '[':
                return parseJsonArray();
            case '{':
                return parseJsonObject();
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return parseJsonNumber();
            default:
                throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
        }
       
    }

    /*
    * Парсит json-объект ({})
    */
    protected JsonObject parseJsonObject() throws IOException, JsonParseException {
        read();
        JsonObject object = new JsonObject();
        skipWhiteSpaces();
        int c = peek();
        if (c == '}') {
            return object;
        }
        
        do {
          c = skipWhiteSpacesAndRead();
          String name = readString();
          skipWhiteSpaces();
          c = read();
          if (c != ':') {
            throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
          }
          skipWhiteSpaces();
          object.put(name, parseJsonComponent());
          skipWhiteSpaces();
          c = read();
        } while (c == ',');
        
        if (c != '}') {
          throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
        }
        
        return object;
    }

    /*
    * Парсит json-массив ([])
    */
    protected JsonArray parseJsonArray() throws IOException {
        read(); 
        JsonArray array = new JsonArray();
        skipWhiteSpaces();
        int c = peek();
        if (c == ']') {
          return array;
        }
        do {
          skipWhiteSpaces();
          array.add(parseJsonComponent());
          skipWhiteSpaces();
          c = read();
        } while (c == ',');
        
        if (c != ']') {
          throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
        }
        
        return array;
    }

    /*
    * Парсит json-строку 
    */
    protected JsonString parseJsonString() throws IOException {
        read();
        String string = readString();
        return new JsonString(string);
    }
    
    String readString() throws IOException {
        int c = read();
        String string = "";
        while (c != '"') {
            string += (char) c;
            c = read();
        }
        return string;
    }

    /*
    * Парсит null-значение
    */
    protected JsonNull parseJsonNull() throws IOException {
        int c = read();
        if (c == 'n') {
            testCharacter(read(), 'u');
            testCharacter(read(), 'l');
            testCharacter(read(), 'l');
            return new JsonNull();
        }
        else
            throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
    }

    /*
    * Парсит json-число
    */
    protected JsonComponent parseJsonNumber() throws IOException {
        String string = "";
        int c = read();
        if (c == '-' || Character.isDigit(c)) {
            string += (char)c;
        }
        else {
            throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
        }
        
        c = peek();
        if (c == ',') {
            return new JsonNumber(string);
        }
        else if (!Character.isDigit(c) && c != '.') {
          throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
        }
        
        string += readNumber();
        
        return new JsonNumber(string);
    }    
    
    String readNumber() throws IOException {
        int c = peek();
        String string = "";
        while (Character.isDigit(c) == true || c == '.') {
            string += (char) read();
            c = peek();
        }
        
        return string;
    }
    
    /*
    * Парсит логическое значение
    */
    protected JsonBoolean parseJsonBoolean() throws IOException, JsonParseException {
        int c = peek();
        if (c == 't') 
            return parseJsonTrue();
        else if (c == 'f')
            return parseJsonFalse();
        else 
            throw new JsonUnexpectedTokenException(getLineNumber(), String.valueOf((char)c));
    }
    
    protected JsonBoolean parseJsonTrue() throws IOException, JsonParseException {
        testCharacter(read(), 't');
        testCharacter(read(), 'r');
        testCharacter(read(), 'u');
        testCharacter(read(), 'e');
        return new JsonBoolean(true);
    }
    
    protected JsonBoolean parseJsonFalse() throws IOException, JsonParseException {
        testCharacter(read(), 'f');
        testCharacter(read(), 'a');
        testCharacter(read(), 'l');
        testCharacter(read(), 's');
        testCharacter(read(), 'e');
        return new JsonBoolean(false);
    }

}