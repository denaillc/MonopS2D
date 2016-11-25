/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import java.util.*;

/**
 *
 * @author albertar
 */
public class Utilitaire {
    private static final Random RANDOM = new Random();
    
    public static int De3() {
        return RANDOM.nextInt(6)+1;
    }
}
