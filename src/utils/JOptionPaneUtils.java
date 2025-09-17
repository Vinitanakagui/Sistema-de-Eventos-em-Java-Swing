package utils;

import javax.swing.JOptionPane;


public class JOptionPaneUtils 
{
    public static void atencao (String mensagem, String titulo)
    {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }
    
    public static int confirmacao(String mensagem, String titulo)
    {
        return JOptionPane.showConfirmDialog(null, mensagem, titulo,JOptionPane.YES_NO_OPTION);
    }
    
    public static void erro(String mensagem, String titulo)
    {
       JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public static  void sucesso(String mensagem, String titulo)
    {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
