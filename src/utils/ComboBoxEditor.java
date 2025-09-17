package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellEditor;
import modelo.Evento;
import modelo.Responsavel;

public class ComboBoxEditor extends AbstractCellEditor implements TableCellEditor
{
    private JComboBox<String> comboBox;
    private boolean isComboBoxClicked = false;
    private Evento evento;

    public ComboBoxEditor(Evento evento, JTable tabela) 
    {
       
        this.comboBox = new JComboBox<>();
        this.evento = evento;
        
        comboBox.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                for (Responsavel responsavel : evento.getListaResponsavel()) 
                {
                    comboBox.addItem(responsavel.getParticipante().getNome());
                }
                
                comboBox.setSelectedItem(1);
                
                isComboBoxClicked = false;
                
            }
        });
        
//        comboBox.addMouseListener(new MouseAdapter() {
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int row = tabela.rowAtPoint(tabela.convertPoint(comboBox, e.getPoint()));
//        if (row >= 0) {
//            // Aqui você tem o índice da linha
//            System.out.println("Linha selecionada: " + row);
//        }
//    }
//});
        
         
    }

    @Override
    public Object getCellEditorValue() 
    {
        return comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
       
        isComboBoxClicked = true;
        comboBox.setSelectedItem(value);  
        
        return comboBox;
    }
}