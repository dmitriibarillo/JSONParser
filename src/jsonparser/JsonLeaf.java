/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darthvader
 */
public abstract class JsonLeaf implements JsonComponent {
    
    @Override
    public JsonComponent getComponentByKey(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public JsonComponent getComponentByIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSize() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(JsonComponent component) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean put(String key, JsonComponent component) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonComponent removeByKey(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonComponent removeByIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonComponent removeByValue(JsonComponent value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public boolean isJsonObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public Map<String, JsonComponent> asMap() {
        return null;
    }

    @Override
    public List<JsonComponent> asList() {
        return null;
    }

    @Override
    public Object asJavaObject() {
        return this;
    }
    
    @Override
    public void save(Writer out, int deep) {
         try {
            out.write(toJSONString());
        } catch (IOException ex) {
            Logger.getLogger(JsonString.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toJSONString() {
        return toString();
    }
    
}
