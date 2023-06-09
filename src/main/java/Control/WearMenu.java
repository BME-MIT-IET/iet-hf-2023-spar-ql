package Control;

import Model.Axe;
import Model.Item;
import Model.Virologist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WearMenu {
    private JFrame wearmenu;
    private JOptionPane jPopup;
    private JLabel lProtectiveGears;
    private JPanel pProtectiveGears;
    private JTable protectiveGearTable;

    private Virologist virologist;

    private GameMenu gameMenu;

    private BagMenuData protectiveGears;

    public WearMenu(Virologist v, GameMenu gameMenu){
        virologist = v;
        this.gameMenu = gameMenu;
        init();
    }

    /**
     * Létrehozza az ablakot és az előugró ablakokat, ha kell
     */
    public void init(){
        wearmenu = new JFrame("Wear");
        wearmenu.setSize(new Dimension(300, 300));
        final JPanel jPanel = new JPanel(new BorderLayout());
        final JPanel jEmptyLeft = new JPanel();
        final JPanel jEmptyRight = new JPanel();
        final JPanel jEmptyBottom = new JPanel();
        pProtectiveGears = new JPanel();
        pProtectiveGears.setName("pProtectiveGears");
        lProtectiveGears = new JLabel("Protective gears");

        /**
         * a tábla adatai
         */
        protectiveGears = new BagMenuData(new ArrayList<Item>(virologist.getWear()));
        pProtectiveGears.setLayout(new BorderLayout());
        /**
         * a táblázat beállítása
         */
        protectiveGearTable = new JTable(protectiveGears);
        protectiveGearTable.setFillsViewportHeight(true);
        protectiveGearTable.setTableHeader(null);
        protectiveGearTable.setRowHeight(20);
        protectiveGearTable.setName("protectiveGearTable");
        protectiveGearTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        protectiveGearTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        protectiveGearTable.setShowGrid(false);
        pProtectiveGears.add(new JScrollPane(protectiveGearTable), BorderLayout.CENTER);

        protectiveGearTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean axe = false;
                int row = protectiveGearTable.rowAtPoint(evt.getPoint());
                int col = protectiveGearTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    JFrame jFrame = new JFrame();
                    Object[] options = {"Unwear!", "Discard!"};
                    int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the protective gear?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                    if (result == 0){
                        protectiveGears.removeItem(row);
                        virologist.getWear().get(row).takeAway(virologist);
                        gameMenu.updateStats();
                    }
                    else if (result == 1){
                        protectiveGears.removeItem(row);
                        virologist.getBag().Discard(virologist.getWear().get(row));
                        gameMenu.updateStats();
                    }
                }
            }
        });

        wearmenu.add(jPanel);
        jPanel.add(lProtectiveGears, BorderLayout.PAGE_START);
        jPanel.add(pProtectiveGears, BorderLayout.CENTER);
        jPanel.add(jEmptyLeft, BorderLayout.LINE_START);
        jPanel.add(jEmptyRight, BorderLayout.LINE_END);
        jPanel.add(jEmptyBottom, BorderLayout.PAGE_END);

        wearmenu.setVisible(true);
    }

    public JFrame getFrame() {
        return wearmenu;
    }
}
