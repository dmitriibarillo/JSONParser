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
* Базовый класс для всех исключений, связанных с парсингом
*/
public abstract class JsonParseException extends IOException {

    public JsonParseException(int line, String s) {
        System.err.println("Error in line: " + line + " ");
    }
    
}
