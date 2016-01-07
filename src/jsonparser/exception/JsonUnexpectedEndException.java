/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.exception;

import java.io.IOException;

/**
 *
 * @author darthvader
 */

/*
* Выбрасывается, если парсер встретил конец документа
* до того, как была считана последняя закрывающая скобка
*/
public class JsonUnexpectedEndException extends JsonParseException {
    
    public JsonUnexpectedEndException(int line, String s) {
        super(line, s);
        System.err.println("Unexpected symbol: \"" + s + "\"\n");
    }
    
    
    
}
