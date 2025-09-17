package utils;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class ValidarCampo 
{
    public static boolean validarTextField(JTextField textField)
    {
        if(textField.getText() == null || textField.getText().isEmpty())  
        {
            textField.setBorder(new LineBorder(Color.RED, 2));  
                     
            return false;
        }
        
        return true;
    }
    
    public static boolean validarDataChooser(JDateChooser jDateChooser)
    { 
        if(jDateChooser.getDate() == null)
        {
            jDateChooser.setBorder(new LineBorder(Color.RED, 2));
           
            return false;
        }
        
        return true;
    }
    
    public static boolean validarComboBox(JComboBox jComboBox)
    {
       if(jComboBox.getSelectedIndex() == 0)
       {
           jComboBox.setBorder(new LineBorder(Color.RED, 2));
           
           return false;
       }
       
       return true;
    }
}
