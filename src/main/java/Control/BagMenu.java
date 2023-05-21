package Control;

import Model.*;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BagMenu {
    private JOptionPane jPopup;
    private JLabel lAgent;
    private JLabel lProtectiveGears;
    private JLabel lMaterials;
    private JPanel pAgent;
    private JPanel pProtectiveGears;
    private JPanel pMaterials;
    private Virologist virologist;
    private Bag virologistBag;
    private JTable agentTable;
    private JTable protectiveGearTable;
    private JTable materialTable;
    private GameMenu gameMenu;

    private BagMenuData agents;
    private BagMenuData protectiveGears;
    private BagMenuData materials;

    public BagMenu(Virologist v, GameMenu gameMenu){
        virologist = v;
        virologistBag = virologist.getBag();
        this.gameMenu = gameMenu;
        init();
    }

    /**
     * Létrehozza az ablakot és az előugró ablakokat, ha kell
     */
    public void init(){
        JFrame bag = new JFrame("Bag");
        bag.setSize(new Dimension(400, 500));
        final JPanel jPanel = new JPanel(new BorderLayout());
        final JPanel grid = new JPanel(new GridLayout(0,1));
        final JPanel jEmptyLeft = new JPanel();
        final JPanel jEmptyRight = new JPanel();
        final JPanel jEmptyBottom = new JPanel();
        final JPanel jEmptyTop = new JPanel();
        pAgent = new JPanel();
        JPanel agent = new JPanel(new BorderLayout());
        pProtectiveGears = new JPanel();
        JPanel gear = new JPanel(new BorderLayout());
        pMaterials = new JPanel();
        JPanel material = new JPanel(new BorderLayout());

        lAgent = new JLabel("Agents");
        lProtectiveGears = new JLabel("Protecive gears");
        lMaterials = new JLabel("Materials");

        /**
         * a táblák adatai
         */
        agents = new BagMenuData(new ArrayList<Item>(virologistBag.getAgents()));
        protectiveGears = new BagMenuData(new ArrayList<Item>(virologistBag.getProtectiveGears()));
        materials = new BagMenuData(new ArrayList<Item>(virologistBag.getMaterials()));

        pAgent.setLayout(new BorderLayout());
        pProtectiveGears.setLayout(new BorderLayout());
        pMaterials.setLayout(new BorderLayout());

        /**
         * a táblázatok beállítása
         */
        agentTable = new JTable(agents);
        protectiveGearTable = new JTable(protectiveGears);
        materialTable = new JTable(materials);
        agentTable.setFillsViewportHeight(true);
        protectiveGearTable.setFillsViewportHeight(true);
        materialTable.setFillsViewportHeight(true);
        agentTable.setTableHeader(null);
        protectiveGearTable.setTableHeader(null);
        materialTable.setTableHeader(null);
        agentTable.setRowHeight(20);
        protectiveGearTable.setRowHeight(20);
        materialTable.setRowHeight(20);
        agentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        agentTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        protectiveGearTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        protectiveGearTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        materialTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        materialTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        agentTable.setShowGrid(false);
        protectiveGearTable.setShowGrid(false);
        materialTable.setShowGrid(false);

        pAgent.add(new JScrollPane(agentTable), BorderLayout.CENTER);
        pProtectiveGears.add(new JScrollPane(protectiveGearTable), BorderLayout.CENTER);
        pMaterials.add(new JScrollPane(materialTable), BorderLayout.CENTER);

        agentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = agentTable.rowAtPoint(evt.getPoint());
                int col = agentTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    JFrame jFrame = new JFrame();
                    Object[] options = {"Use on self!", "Use on other virologist!", "Discard!"};
                    int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the agent?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                    if (result == 0){
                        virologist.UseAgent(virologist, virologistBag.getAgents().get(row));
                        agents.removeItem(row);
                        gameMenu.updateStats();
                    }
                    else if (result == 1){
                        if(virologist.getTile().GetOtherVirologist(virologist).isThrowBackAvailable()){
                            JFrame throwback = new JFrame();
                            Object[] options2 = {"Yes!", "No!"};
                            int result2 = jPopup.showOptionDialog(throwback, "Do you want throwback the agent?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[1]);
                            if(result2 == 0){
                                virologist.UseAgent(virologist.getTile().GetOtherVirologist(virologist), virologistBag.getAgents().get(row), true);
                            }
                            else if(result2 == 1){
                                virologist.UseAgent(virologist.getTile().GetOtherVirologist(virologist), virologistBag.getAgents().get(row), false);
                            }
                        }
                        else {
                            virologist.UseAgent(virologist.getTile().GetOtherVirologist(virologist), virologistBag.getAgents().get(row));
                        }
                        agents.removeItem(row);
                        gameMenu.updateStats();
                    }
                    else if (result == 2){
                        agents.removeItem(row);
                        virologistBag.Discard(virologistBag.getAgents().get(row));
                        gameMenu.updateStats();
                    }
                }
            }
        });

        protectiveGearTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean axe = false;
                int row = protectiveGearTable.rowAtPoint(evt.getPoint());
                int col = protectiveGearTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    if(virologistBag.getProtectiveGears().get(row) instanceof Axe) axe = true;
                    JFrame jFrame = new JFrame();
                    if(axe){
                        Object[] options = {"Use!", "Discard!"};
                        int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the protective gear?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (result == 0 && ((Axe) virologistBag.getProtectiveGears().get(row)).getUsable()){
                            Virologist other = virologist.getTile().GetOtherVirologist(virologist);
                            virologistBag.getProtectiveGears().get(row).Use(other, null);
                            gameMenu.updateStats();
                            gameMenu.getGamePanel().virologistDie(other);
                            gameMenu.getGamePanel().draw();
                        }
                        else if (result == 1){
                            protectiveGears.removeItem(row);
                            virologistBag.Discard(virologistBag.getProtectiveGears().get(row));
                            gameMenu.updateStats();
                        }
                    }
                    else{
                        Object[] options = {"Wear!", "Discard!"};
                        int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the protective gear?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (result == 0){
                            virologist.Wear(virologistBag.getProtectiveGears().get(row));
                            gameMenu.updateStats();
                        }
                        else if (result == 1){
                            protectiveGears.removeItem(row);
                            virologistBag.Discard(virologistBag.getProtectiveGears().get(row));
                            gameMenu.updateStats();
                        }
                    }
                }
            }
        });

        materialTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = materialTable.rowAtPoint(evt.getPoint());
                int col = materialTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    JFrame jFrame = new JFrame();
                    Object[] options = {"Discard!"};
                    int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the material?","Options", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (result == 0){
                        materials.removeItem(row);
                        virologistBag.Discard(virologistBag.getMaterials().get(row));
                        gameMenu.updateStats();
                    }
                }
            }
        });

        bag.add(jPanel);
        jPanel.add(grid);
        grid.add(agent);
        grid.add(gear);
        grid.add(material);
        agent.add(lAgent, BorderLayout.PAGE_START);
        agent.add(pAgent, BorderLayout.CENTER);
        gear.add(lProtectiveGears, BorderLayout.PAGE_START);
        gear.add(pProtectiveGears, BorderLayout.CENTER);
        material.add(lMaterials, BorderLayout.PAGE_START);
        material.add(pMaterials, BorderLayout.CENTER);
        jPanel.add(jEmptyLeft, BorderLayout.LINE_START);
        jPanel.add(jEmptyRight, BorderLayout.LINE_END);
        jPanel.add(jEmptyBottom, BorderLayout.PAGE_END);
        jPanel.add(jEmptyTop, BorderLayout.PAGE_START);

        bag.setVisible(true);
    }
}
