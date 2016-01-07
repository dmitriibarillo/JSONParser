/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.exception;

/**
 *
 * @author darthvader
 */

/*
* Выбрасывается, если парсер не может распознать элемент документа 
* (например FALSE вместо false)
*/
public class JsonUnexpectedTokenException extends JsonParseException {
    
    public JsonUnexpectedTokenException(int line, String s) {
        super(line, s);
        System.err.println("Unexpected symbol: \"" + s + "\"\n");
    }
    
}
