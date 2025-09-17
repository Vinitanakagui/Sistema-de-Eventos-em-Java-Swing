package utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class CelulaEditorData extends DefaultCellEditor
{
    private JFormattedTextField textField;

    public CelulaEditorData() {
        // Cria um JFormattedTextField com a máscara de data
        super(new JFormattedTextField(createFormatter("##/##/####")));
        textField = (JFormattedTextField) getComponent();
        textField.setFocusLostBehavior(JFormattedTextField.PERSIST); // Comportamento ao perder o foco

        // Configuração do KeyListener para restrição de caracteres
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caractere = e.getKeyChar();
                // Permitir apenas dígitos e backspace
                if (!Character.isDigit(caractere) && caractere != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Bloquear caracteres não permitidos
                }
            }
        });
    }

    // Método para criar o MaskFormatter
    private static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setPlaceholderCharacter('_'); // Caractere de preenchimento
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    @Override
    public Object getCellEditorValue() {
        String text = textField.getText();
        if (text.trim().isEmpty()) {
            return null; // Permitir que o campo de data seja vazio
        }
        try {
            // Parse a data e formatar para dd/MM/yyyy
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return format.format(format.parse(text)); // Retornar a data formatada como String
        } catch (ParseException e) {
            return null; // Retornar null se o formato for inválido
        }
    }
}
