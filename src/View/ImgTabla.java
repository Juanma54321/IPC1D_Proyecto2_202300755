
package View;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
    

public class ImgTabla  extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                    boolean isSelected, boolean hasFocus,
                                                    int row, int column) {

        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
            setText(""); // No texto
            setHorizontalAlignment(CENTER); // Centrado horizontal
            setVerticalAlignment(CENTER);   // Centrado vertical
        } else {
            setIcon(null);
            setText((value != null) ? value.toString() : "");
        }

        return this;
    }
}

