/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import sun.org.mozilla.javascript.Token;

/**
 *
 * @author darthvader
 */
public class JsonObject implements JsonComponent{

    LinkedHashMap<String, JsonComponent> objectComponents = new LinkedHashMap<String, JsonComponent>();

    @Override
    public JsonComponent getComponentByKey(String key) {
        return objectComponents.get(key);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonComponent getComponentByIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSize() {
        return objectComponents.size();
    }

    @Override
    public boolean add(JsonComponent component) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean put(String key, JsonComponent component) {
        if (objectComponents.put(key, component) instanceof JsonComponent)
            return true;
        else 
            return false;
    }

    @Override
    public JsonComponent removeByKey(String key) {
        return objectComponents.remove(key);
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
        return false;
    }

    @Override
    public boolean isJsonObject() {
        return true;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public Map<String, JsonComponent> asMap() {
        return (Map<String, JsonComponent>)objectComponents;
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
            String string = "";
            boolean first = true;
            Iterator iter = objectComponents.entrySet().iterator();
            out.write("{\n");
            deep++;
            while (iter.hasNext()) {
                if (first) {
                    first = false;
                }
                else {
                    out.write(",");
                    out.write("\n");
                }
                for (int j = 0; j < deep; j++)
                    out.write("  ");
                Map.Entry<String, JsonComponent> entry = (Map.Entry)iter.next();
                out.write("\"");
                out.write(entry.getKey());
                out.write("\"");
                out.write(": ");
                entry.getValue().save(out, deep); 
            }
            deep--;
            String s = "";
            for (int i = 0; i < deep; i++)
                s += "  ";
            out.write("\n" + s + "}");
        } catch (Exception e) {
        }
    }

    @Override
    public String toJSONString() {
        if (objectComponents == null) {
            return "null";
        }
        String string = "";
        boolean first = true;
        Iterator iter = objectComponents.entrySet().iterator();
        string += "{";
        while (iter.hasNext()) {
            if (first)
                first = false;
            else 
                string += ",";
            Map.Entry<String, JsonComponent> entry = (Map.Entry)iter.next();
            string += "\"";
            string += entry.getKey();
            string += "\"";
            string += ":";
            string += entry.getValue().toJSONString();    
        }
        string += "}";
        return string;
    }
    
}
