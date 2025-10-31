package com.shadowedhunter.ui;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.ui.components.*;
import com.shadowedhunter.util.Constants;
import com.shadowedhunter.util.FontLoader;
import com.shadowedhunter.util.ResourceLoader;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class GamePanel extends JPanel {
    private final GameEngine engine;
    private final JTextArea outputArea;
    private final JTextArea inputArea;
    private final MapRenderer mapRenderer;
    private final HealthBar healthBar;
    private final InventoryPanel inventoryPanel;
    private final TimeCounter timeCounter;
    private final FogOfWar fogOfWar;

    private final Image backgroundImage;
    private final Image cheatSheetImage;
    private boolean showCheatSheet = false;

    private final Font customFont;

    public GamePanel(GameEngine engine) {
        this.engine = engine;
        this.customFont = FontLoader.loadCustomFont();

        setLayout(null);
        setOpaque(false);

        // Load images
        backgroundImage = ResourceLoader.loadImage("/images/BackgroundGame.png");
        cheatSheetImage = ResourceLoader.loadImage("/images/CheatSheet.png");

        // Create components
        mapRenderer = new MapRenderer(engine);
        healthBar = new HealthBar(engine);
        inventoryPanel = new InventoryPanel(engine);
        timeCounter = new TimeCounter(engine);
        fogOfWar = new FogOfWar();

        // Create text areas
        outputArea = createRoundedTextArea(false);
        outputArea.setText("Type 'intro' to start");
        outputArea.setFont(new Font(customFont.getFontName(), customFont.getStyle(), 50));
        add(outputArea);

        inputArea = createRoundedTextArea(true);
        inputArea.setFont(new Font(customFont.getFontName(), customFont.getStyle(), 50));
        add(inputArea);

        // Add key listener for input
        inputArea.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            String text = inputArea.getText();
                            inputArea.setText("");
                            e.consume();

                            String command = text.trim();
                            if (command.equalsIgnoreCase("help")) {
                                showCheatSheet = true;
                                repaint();

                                Timer timer =
                                        new Timer(
                                                Constants.CHEAT_SHEET_DISPLAY_MS,
                                                _ -> {
                                                    showCheatSheet = false;
                                                    repaint();
                                                });
                                timer.setRepeats(false);
                                timer.start();
                            }

                            engine.processCommand(command);
                        }
                    }
                });
    }

    private JTextArea createRoundedTextArea(boolean editable) {
        JTextArea textArea =
                new JTextArea() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.GRAY);
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                        super.paintComponent(g2);
                        g2.dispose();
                    }
                };
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(editable);
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        return textArea;
    }

    public void setOutputText(String text) {
        outputArea.setText(text);
    }

    public void showCheatSheet() {
        showCheatSheet = true;
        repaint();

        Timer timer =
                new Timer(
                        Constants.CHEAT_SHEET_DISPLAY_MS,
                        _ -> {
                            showCheatSheet = false;
                            repaint();
                        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw background
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        int fullRectWidth = getWidth() - 20;
        int topRectHeight = (int) (getHeight() * 0.80);

        // Calculate map area
        int mapX = 10;
        int mapY = 10;
        int mapWidth = fullRectWidth;
        int mapHeight = topRectHeight;

        // Draw map
        mapRenderer.render(g2d, mapX, mapY, mapWidth, mapHeight);

        // Draw fog of war
        var _ = engine.getGameState().getPlayer();
        int iconX = mapRenderer.getIconX();
        int iconY = mapRenderer.getIconY();
        fogOfWar.render(g2d, getWidth(), getHeight(), iconX + 4, iconY + 4);

        // Draw UI elements
        healthBar.render(g2d);
        timeCounter.render(g2d);
        inventoryPanel.render(g2d);

        // Position text areas
        int textArea1Height = (int) ((getHeight() - topRectHeight) * 0.57) - 10;
        int textArea1StartY = topRectHeight + 20;

        int textArea2Height = getHeight() - textArea1StartY - textArea1Height - 20;
        int textArea2StartY = textArea1StartY + textArea1Height + 10;

        int halfWidth = (getWidth() / 2) - 10;
        int startXHalf = (getWidth() - halfWidth) / 2;

        int textArea1Width = (int) (fullRectWidth / 1.3);
        int textArea1X = (fullRectWidth - textArea1Width) / 2 + 10;

        outputArea.setBounds(textArea1X, textArea1StartY, textArea1Width, textArea1Height);
        inputArea.setBounds(startXHalf, textArea2StartY, halfWidth, textArea2Height);

        // Draw cheat sheet if visible
        if (showCheatSheet) {
            g2d.setClip(new RoundRectangle2D.Float(mapX, mapY, mapWidth, mapHeight, 20, 20));
            g2d.drawImage(cheatSheetImage, mapX, mapY, mapWidth, mapHeight, this);
            g2d.setClip(null);
        }
    }
}
