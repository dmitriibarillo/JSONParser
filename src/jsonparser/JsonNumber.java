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
public class JsonNumber<T> extends JsonLeaf{
    private T value;

    public JsonNumber(T value) {
        this.value = value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
    

}
