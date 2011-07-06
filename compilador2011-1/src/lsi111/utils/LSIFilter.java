/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi111.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author brodock
 */
public class LSIFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory())
            return true;
        
        String extension = getExtension(file);
        if (extension != null) {
            if (extension.equals("lsi")) {
                    return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Código Fonte LSI111";
    }
    
    /**
     * Pega extensão do arquivo
     * @param file
     * @return 
     */
    private static String getExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
}
