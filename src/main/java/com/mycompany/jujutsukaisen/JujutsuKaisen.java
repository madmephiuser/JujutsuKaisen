
package com.mycompany.jujutsukaisen;

import com.mycompany.parser.*;
import java.io.UnsupportedEncodingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import validate.ControlValidator;
import validate.ValidationException;

public class JujutsuKaisen {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        JFileChooser chooser = new JFileChooser();
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            MissionParser parser = FactoryParser.getParser(); 
            Mission mission = parser.parse(path);
            
            if (mission != null) {
                try {
                    ControlValidator validator = new ControlValidator();
                    validator.validate(mission);
                    MissionDisplay renderer = new MissionRenderer();
                    renderer.display(mission);                                        
                } catch (ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка валидации", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Не удалось прочитать файл или формат не поддерживается.", "Ошибка парсинга", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}