package Control;

import Model.GeneticCode;
import Model.Virologist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GeneticCodesMenu {
    private JOptionPane jPopup;
    private JLabel lCodes;
    private JPanel pCodes;
    private Virologist virologist;
    private GameMenu gameMenu;
    private JTable geneticCodeTable;

    private GeneticCodeMenuData geneticcodes;

    public GeneticCodesMenu(Virologist v, GameMenu gameMenu) {
        virologist = v;
        this.gameMenu = gameMenu;
        init();
    }
    /**
     * Létrehozza az ablakot és az előugró ablakokat, ha kell
     */
    public void init(){
        JFrame geneteicCodes = new JFrame("Genteic Codes");
        geneteicCodes.setSize(new Dimension(300, 500));
        final JPanel jPanel = new JPanel(new BorderLayout());
        final JPanel jEmptyLeft = new JPanel();
        final JPanel jEmptyRight = new JPanel();
        final JPanel jEmptyBottom = new JPanel();
        pCodes = new JPanel();
        lCodes = new JLabel("Codes");

        /**
         * a tábla adatai
         */
        geneticcodes = new GeneticCodeMenuData(new ArrayList<GeneticCode>(virologist.getGeneticCodes()));
        pCodes.setLayout(new BorderLayout());
        /**
         * a táblázat beállítása
         */
        geneticCodeTable = new JTable(geneticcodes);
        geneticCodeTable.setFillsViewportHeight(true);
        geneticCodeTable.setTableHeader(null);
        geneticCodeTable.setRowHeight(20);
        geneticCodeTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        geneticCodeTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        geneticCodeTable.setShowGrid(false);
        pCodes.add(new JScrollPane(geneticCodeTable), BorderLayout.CENTER);

        geneticCodeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean axe = false;
                int row = geneticCodeTable.rowAtPoint(evt.getPoint());
                int col = geneticCodeTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    JFrame jFrame = new JFrame();
                    Object[] options = {"Create Agent!"};
                    int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the genetic code?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (result == 0){
                        virologist.getGeneticCodes().get(row).CreateAgent(virologist);
                        gameMenu.updateStats();
                    }
                }
            }
        });

        geneteicCodes.add(jPanel);
        jPanel.add(lCodes, BorderLayout.PAGE_START);
        jPanel.add(pCodes, BorderLayout.CENTER);
        jPanel.add(jEmptyLeft, BorderLayout.LINE_START);
        jPanel.add(jEmptyRight, BorderLayout.LINE_END);
        jPanel.add(jEmptyBottom, BorderLayout.PAGE_END);

        geneteicCodes.setVisible(true);
    }
}
