package scripts.sftanner;

import java.util.ArrayList;
import java.util.HashMap;
import scripts.sftanner.actions.ClickTanningInterface;
import scripts.sftanner.actions.DepositEmptyVial;
import scripts.sftanner.actions.DepositHides;
import scripts.sftanner.actions.DrinkEnergyPotion;
import scripts.sftanner.actions.OpenBank;
import scripts.sftanner.actions.OpenTanningInterface;
import scripts.sftanner.actions.TurnRunOn;
import scripts.sftanner.actions.WalkToBank;
import scripts.sftanner.actions.WalkToTanner;
import scripts.sftanner.actions.WithdrawEnergyPotion;
import scripts.sftanner.actions.WithdrawHides;
import scripts.framework.Action;
import scripts.framework.RSCondition;
import scripts.framework.RSFrame;
import scripts.utl.DynamicVariables;

/**
 * @author Starfox
 * @version 11/20/2013
 */
public class SFTannerGui extends RSFrame {

    public SFTannerGui(final Object message) {
        super(message);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionPanel = new javax.swing.JPanel();
        hideTypeLabel = new javax.swing.JLabel();
        hideTypeComboBox = new javax.swing.JComboBox();
        locationLabel = new javax.swing.JLabel();
        locationComboBox = new javax.swing.JComboBox();
        extrasPanel = new javax.swing.JPanel();
        energyPotionLabel = new javax.swing.JLabel();
        energyPotionComboBox = new javax.swing.JComboBox();
        profitPerHideLabel = new javax.swing.JLabel();
        profitPerHideTextField = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SF Tanner");

        optionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tanning Options"));

        hideTypeLabel.setText("Hide type:");

        hideTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Leather", "Hard leather", "Green d-leather", "Blue d-leather", "Red  d-leather", "Black  d-leather" }));

        locationLabel.setText("Location:");

        locationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Al-Kharid", "Canafis" }));

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(hideTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hideTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(locationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hideTypeLabel)
                    .addComponent(hideTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabel)
                    .addComponent(locationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        extrasPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Extras"));

        energyPotionLabel.setText("Energy potion:");

        energyPotionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Regular", "Super" }));

        profitPerHideLabel.setText("Profit per hide:");

        profitPerHideTextField.setText("0");

        javax.swing.GroupLayout extrasPanelLayout = new javax.swing.GroupLayout(extrasPanel);
        extrasPanel.setLayout(extrasPanelLayout);
        extrasPanelLayout.setHorizontalGroup(
            extrasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extrasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(extrasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(extrasPanelLayout.createSequentialGroup()
                        .addComponent(energyPotionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(energyPotionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(extrasPanelLayout.createSequentialGroup()
                        .addComponent(profitPerHideLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profitPerHideTextField)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        extrasPanelLayout.setVerticalGroup(
            extrasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extrasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(extrasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(energyPotionLabel)
                    .addComponent(energyPotionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(extrasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profitPerHideLabel)
                    .addComponent(profitPerHideTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(optionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(extrasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(optionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(extrasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(startButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startButtonPressed(evt);
    }//GEN-LAST:event_startButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox energyPotionComboBox;
    private javax.swing.JLabel energyPotionLabel;
    private javax.swing.JPanel extrasPanel;
    private javax.swing.JComboBox hideTypeComboBox;
    private javax.swing.JLabel hideTypeLabel;
    private javax.swing.JComboBox locationComboBox;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JLabel profitPerHideLabel;
    private javax.swing.JTextField profitPerHideTextField;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public HashMap<String, String> processOptions() throws NumberFormatException {
        HashMap<String, String> ops = new HashMap<>();
        final String UNTANNED = "Untanned hide type";
        ops.put("Hide type", hideTypeComboBox.getSelectedItem().toString());
        ops.put("Location", locationComboBox.getSelectedItem().toString());
        ops.put("Energy potion", energyPotionComboBox.getSelectedItem().toString());
        ops.put("Profit per hide", profitPerHideTextField.getText());
        switch(hideTypeComboBox.getSelectedItem().toString()) {
            case "Leather":
                ops.put(UNTANNED, "Cowhide");
                ops.put("Child interface id", "100");
                ops.put("Hide cost", "1");
                break;
            case "Hard leather":
                ops.put(UNTANNED, "Cowhide");
                ops.put("Child interface id", "101");
                ops.put("Hide cost", "3");
                break;
            case "Green d-leather":
                ops.put(UNTANNED, "Green dragonhide");
                ops.put("Child interface id", "104");
                ops.put("Hide cost", "20");
                break;
            case "Blue d-leather":
                ops.put(UNTANNED, "Blue dragonhide");
                ops.put("Child interface id", "105");
                ops.put("Hide cost", "20");
                break;
            case "Red d-leather":
                ops.put(UNTANNED, "Red dragonhide");
                ops.put("Child interface id", "106");
                ops.put("Hide cost", "20");
                break;
            case "Black d-leather":
                ops.put(UNTANNED, "Black dragonhide");
                ops.put("Child interface id", "107");
                ops.put("Hide cost", "20");
                break;
        }
        if(ops.get("Location").equalsIgnoreCase("Al-Kharid")) {
            ops.put("Bank x", "3270");
            ops.put("Bank y", "3167");
            ops.put("Tanning x", "3274");
            ops.put("Tanning y", "3191");
            ops.put("Door x", "3277");
            ops.put("Door y", "3191");
            ops.put("Open door x", "3278");
            ops.put("Open door y", "3191");
            ops.put("Tanner name", "Ellis");
        } else {
            ops.put("Bank x", "3511");
            ops.put("Bank y", "3480");
            ops.put("Tanning x", "3491");
            ops.put("Tanning y", "3502");
            ops.put("Door x", "3488");
            ops.put("Door y", "3497");
            ops.put("Open door x", "3488");
            ops.put("Open door y", "3498");
            ops.put("Tanner name", "Sbott");
        }
        DynamicVariables.costPerHide = Integer.parseInt(ops.get("Hide cost"));
        DynamicVariables.profitPerHide = Integer.parseInt(ops.get("Profit per hide"));
        return ops;
    }

    @Override
    public ArrayList<RSCondition> processTerminateConditions(final HashMap<String, String> options) {
        ArrayList<RSCondition> conditions = new ArrayList<>();
        /*conditions.add(new RSCondition() {
            @Override
            public boolean isConditionMet() {
                if(Banking.isBankScreenOpen()) {
                    return Banking.find(options.get("Untanned hide type")).length <= 0;
                }
                return Inventory.getCount(new String[]{"Coins"}) < 500;
            }
        });*/
        return conditions;
    }

    @Override
    public ArrayList<Action> processActions() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new TurnRunOn());
        actions.add(new ClickTanningInterface());
        actions.add(new OpenTanningInterface());
        actions.add(new WalkToTanner());
        actions.add(new WalkToBank());
        actions.add(new OpenBank());
        actions.add(new DepositHides());
        actions.add(new WithdrawHides());
        if(!energyPotionComboBox.getSelectedItem().toString().equalsIgnoreCase("None")) {
            actions.add(new DepositEmptyVial());
            actions.add(new WithdrawEnergyPotion());
            actions.add(new DrinkEnergyPotion());
        }
        return actions;
    }
}
