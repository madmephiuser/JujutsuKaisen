
package com.mycompany.jujutsukaisen;

import com.mycompany.parser.*;
import java.io.UnsupportedEncodingException;
import javax.swing.JFileChooser;

public class JujutsuKaisen {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        JFileChooser chooser = new JFileChooser();
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();

            MissionParser parser = FactoryParser.getParser(path);
            Mission mission = parser.parse(path);
            
            if (mission != null) {
                MissionDisplay renderer = new MissionRenderer();
                renderer.display(mission);
            }
        }
    }
}