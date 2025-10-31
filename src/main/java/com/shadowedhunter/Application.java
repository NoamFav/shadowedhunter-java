package com.shadowedhunter;

import com.shadowedhunter.ui.MainMenu;

import javax.swing.SwingUtilities;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
