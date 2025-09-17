package utils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CelulaEditorInteiro extends DefaultCellEditor {
    private JTextField textField;

    public CelulaEditorInteiro()
    {
        super(new JTextField());
        textField = (JTextField) getComponent();

        // KeyListener para permitir apenas números e backspace
        textField.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyTyped(KeyEvent e) 
            {
                char caracter = e.getKeyChar();
                // Permitir apenas dígitos e backspace
                if (!Character.isDigit(caracter) && caracter != KeyEvent.VK_BACK_SPACE) 
                {
                    e.consume(); // Bloquear caracteres não permitidos
                }
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        String texto = textField.getText();
        if (texto.trim().isEmpty()) {
            return null; // Permitir que o campo de número seja vazio
        }
        try {
            return Integer.parseInt(texto); // Retornar o valor inteiro
        } catch (NumberFormatException ex) {
            return 1; // Retornar valor padrão se o número for inválido
        }
    }
}
