/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author darthvader
 */
public class JsonArray implements JsonComponent{
    List<JsonComponent> arrayComponents = new ArrayList<JsonComponent>();
    
    @Override
    public JsonComponent getComponentByKey(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonComponent getComponentByIndex(int index) {
        return arrayComponents.get(index);
    }

    @Override
    public int getSize() {
        return arrayComponents.size();
    }

    @Override
    public boolean add(JsonComponent component) {
        return arrayComponents.add(component);
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
        return arrayComponents.remove(index);
    }

    @Override
    public JsonComponent removeByValue(JsonComponent value) {
        int index = arrayComponents.indexOf(value);
        JsonComponent component = arrayComponents.get(index);
        arrayComponents.remove(value);
        return component;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isJsonObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public Map<String, JsonComponent> asMap() {
        return null;
    }

    @Override
    public List<JsonComponent> asList() {
        return arrayComponents;
    }

    @Override
    public Object asJavaObject() {
        return this;
    }

    @Override
    public void save(Writer out, int deep) {
        try {
            if (arrayComponents == null)
                out.write("null");
            else if (arrayComponents.size() == 0) {
                 out.write("[]");
            }
            else {
                out.write("[\n");
                deep++;
                for (int i = 0; i < deep; i++)
                    out.write("  ");
                arrayComponents.get(0).save(out, deep);
                for(int i = 1; i < arrayComponents.size(); i++){
                    out.write(",");
                    out.write("\n");
                    for (int j = 0; j < deep; j++)
                        out.write("  ");
                    arrayComponents.get(i).save(out, deep);
                }
            }
            deep--;
            String s = "";
            for (int i = 0; i < deep; i++)
                s += "  ";
            out.write("\n" + s + "]");
         } catch (Exception e) {
        }
    }
    
    @Override
    public String toJSONString() { 
        String string;
        if (arrayComponents == null)
            return  "null";
        else if (arrayComponents.size() == 0) {
            return "[]";
        }
        else {
            string = "[";
            string += arrayComponents.get(0).toJSONString();
            for(int i = 1; i < arrayComponents.size(); i++){
                string += ",";
                string += arrayComponents.get(i).toJSONString();
            }
        }
        string += "]";
        return string;
    }

    
}
