package com.skillingpetchance;

import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SkillingPetPanel extends PluginPanel {

    public SkillingPetPanel (Client client, SkillingPetChanceConfig config, ConfigManager configManager){
        super();

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        final JComboBox<String> dropdown = new JComboBox<>();
        dropdown.setFocusable(false); // To prevent an annoying "focus paint" effect
        dropdown.setForeground(Color.WHITE);
        dropdown.setMaximumRowCount(8);

        dropdown.addItem("Beaver");
        dropdown.addItem("Baby Chinchompa");
        dropdown.addItem("Giant Squirrel");
        dropdown.addItem("Heron");
        dropdown.addItem("Rift Guardian");
        dropdown.addItem("Rock Golem");
        dropdown.addItem("Rocky");
        dropdown.addItem("Tangleroot");

        dropdown.addItemListener(e ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                final String source = (String) e.getItem();
                System.out.println(source);
            }
        });


        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;

        add(dropdown, c);
        c.gridy++;
    }
}
