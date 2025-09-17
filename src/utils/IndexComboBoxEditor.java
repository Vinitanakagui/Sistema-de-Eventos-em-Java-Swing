package utils;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class IndexComboBoxEditor extends AbstractCellEditor implements TableCellEditor {
    private Pair<Integer, JComboBox> iComboBox;

    public IndexComboBoxEditor(Integer index, JComboBox comboBox) 
    {
        this.iComboBox = new Pair<>(index, comboBox);
        
        iComboBox.right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("LINHA: " + iComboBox.left);
            }
            
        });
    }

    @Override
    public Object getCellEditorValue() 
    {
        return iComboBox.right.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        iComboBox.right.setSelectedItem(value);  
        
        return iComboBox.right;
    }
}
