/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darthvader
 */
public class JsonBoolean extends JsonLeaf{
    private boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }
    
    public boolean get() {
        return value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }

}
