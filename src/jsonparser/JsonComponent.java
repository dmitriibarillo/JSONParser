/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author darthvader
 */
public interface JsonComponent {
    
    /*
    * Возвращает потомка по ключу. Если такого ключа нет
    * или объект не поддерживает получение потомков по ключу,
    * возвращает null
    */
    JsonComponent getComponentByKey(String key); 
    
    /*
    * Возвращает потомка по индексу. Если такого индекса нет
    * или объект не поддерживает получение потомков по индексу,
    * возвращает null
    */
    JsonComponent getComponentByIndex(int index);
    
    
    /*
    * Колисество потомков в составном объекте
    */
    int getSize();
    
    /*
    * Добавление компонента 
    */
    boolean add(JsonComponent component);
    
    /*
    * Добавление компонента по ключу.
    * Заменяет уже существующее значение и не возвращает его   
    */
    boolean put(String key, JsonComponent component);
    
    /*
    * Удаление объекта по ключу 
    */
    JsonComponent removeByKey(String key);
    
    /*
    * Удаление объекта по индексу
    */
    JsonComponent removeByIndex(int index);
    
    /*
    * Удаление объекта по значению (первого)
    */
    JsonComponent removeByValue(JsonComponent value);
    
    /*
    * Проверяет, является ли компонент листовым
    */
    boolean isLeaf();
    
    /*
    * Проверяет, является ли компонент объектом JSON
    */
    boolean isJsonObject();
    
    /*
    * Проверяет, является ли компонент массивом JSON
    */
    boolean isArray();
    
    /*
    * Представляет элемент как неизменяемую карту.
    * Возвращает null, если объект не может быть представлен в
    * виде карты
    */
    Map<String, JsonComponent> asMap();
    
    /*
    * Представляет элемент как неизменяемый список.
    * Возвращает null, если объект не может быть представлен в
    * виде списка 
    */
    List<JsonComponent> asList();
    
    /*
    * Представляет элемент как неизменяемый объект Java.
    * Возвращает карту или список для композитов, объект соот.
    * типа для листовых и null для null
    */
    Object asJavaObject();
    
    /*
    * Сохранение элемента в поток. Функция выводит данные рекурсивно.
    * Каждый объект не делает ведущих отступов, предполагая, что 
    * их за него формирует его предок.
    * Точно также формируются хвостовые отступы и переносы
    * строк - это тоже задача предка.
    * @param out поток, в который следует сохранить элемент 
    * @param deep глубина вложенности. Определяет число символов 
    *       табуляции, которые будут предварять данный элемент.
    */
    void save(Writer out, int deep);
   
    String toJSONString();
    
}
