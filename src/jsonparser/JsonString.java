/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darthvader
 */
public class JsonString extends JsonLeaf{
    private String value;

    public JsonString(String value) {
        this.value = value;
    }
    
    public String get() {
        return value;
    }
    
    public String toString() {
        return value;
    }
    
    public String toJSONString() { 
        return "\"" + toString() + "\"";
    }

}
