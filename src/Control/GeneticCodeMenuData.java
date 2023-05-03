package Control;

import Model.Agent;
import Model.GeneticCode;
import Model.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneticCodeMenuData extends AbstractTableModel {

    List<Agent> data;
    List<ImageIcon> icons;

    /**
     * Beolvassa a kapot adatokat és a megfelelő listába teszi
     * @param i
     */
    public GeneticCodeMenuData(ArrayList<GeneticCode> i){
       ArrayList<Agent> agents = new ArrayList<>();
        icons = new ArrayList<ImageIcon>();
        for(GeneticCode codes : i){
            agents.add(codes.getAgent());
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("images/code.png")));
                icons.add(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        data = agents;
    }

    /**
     * Megadja, hogy a tábla hány sort fog tartalmazni
     * @return
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Megadja, hogy a táblla hány oszlopot fog tartalmazni
     * @return
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * Visszaadja, hogy egy sornak egy oszlopában milyen adat található
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 1){
            Item item = data.get(rowIndex);
            return item.getName();
        }
        else {
            ImageIcon icon = icons.get(rowIndex);
            return icon;
        }
    }

    /**
     * Az oszlopok osztályát adja meg
     * @param columnIndex
     * @return
     */
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return ImageIcon.class;
            default: return String.class;
        }
    }

    /**
     * Megmondja, hogy szerkeszthetőek-e a sorok
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * Kivesz egy elemet a listából
     * @param row
     */
    public void removeItem(int row) {
        data.remove(row);
        if(data.size() == 0)
            fireTableRowsDeleted(0, 0);
        else
            fireTableRowsDeleted(0, data.size() - 1);
    }
}