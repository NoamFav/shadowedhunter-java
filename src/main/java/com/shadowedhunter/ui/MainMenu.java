package com.shadowedhunter.ui;

import com.shadowedhunter.audio.AudioManager;
import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.persistence.SaveSystem;
import com.shadowedhunter.util.FontLoader;
import com.shadowedhunter.util.ResourceLoader;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class MainMenu extends JFrame {
    private static final Color BUTTON_COLOR = new Color(71, 18, 18);
    private static final int ROUNDNESS = 25;

    private final Image backgroundImage;
    private final Image titleImage;
    private final Font customFont;

    public MainMenu() {
        customFont = FontLoader.loadCustomFont();
        backgroundImage = ResourceLoader.loadImage("/ground.jpg");
        titleImage = ResourceLoader.loadImage("/Title.png");

        setTitle("Shadowed Hunter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Fullscreen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        ImagePanel imagePanel = new ImagePanel();
        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.CENTER);

        // Play menu music
        AudioManager.getInstance().playMenuMusic("VoidBgMusic.mp3", 20f);

        setVisible(true);
    }

    private JButton createStyledButton(String text, boolean isFixedSize) {
        JButton button =
                new JButton(text) {
                    @Override
                    public Dimension getPreferredSize() {
                        if (!isFixedSize) {
                            int width = (int) (MainMenu.this.getWidth() * 0.5 * 0.7);
                            int height = (int) (MainMenu.this.getHeight() / 6 * 0.7);
                            return new Dimension(width, height);
                        }
                        return super.getPreferredSize();
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setColor(getBackground());
                        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ROUNDNESS, ROUNDNESS);
                        super.paintComponent(g);
                    }

                    @Override
                    protected void paintBorder(Graphics g) {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setColor(Color.WHITE);
                        g2d.drawRoundRect(
                                0, 0, getWidth() - 1, getHeight() - 1, ROUNDNESS, ROUNDNESS);
                    }

                    @Override
                    public boolean contains(int x, int y) {
                        return new RoundRectangle2D.Float(
                                        0, 0, getWidth(), getHeight(), ROUNDNESS, ROUNDNESS)
                                .contains(x, y);
                    }
                };

        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(customFont);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        return button;
    }

    private class ImagePanel extends JPanel {
        private final JButton newGameButton;
        private final JButton loadGameButton;
        private final JButton closeButton;

        public ImagePanel() {
            setLayout(null);

            newGameButton = createStyledButton("NEW GAME", false);
            loadGameButton = createStyledButton("LOAD GAME", false);
            closeButton = createStyledButton("CLOSE", true);

            newGameButton.setFont(new Font(customFont.getFontName(), customFont.getStyle(), 80));
            loadGameButton.setFont(new Font(customFont.getFontName(), customFont.getStyle(), 80));

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int closeButtonWidth = (int) (screenSize.width * 0.1);
            int closeButtonHeight = (int) (0.2 * closeButtonWidth);
            closeButton.setPreferredSize(new Dimension(closeButtonWidth, closeButtonHeight));

            // Button actions
            newGameButton.addActionListener(
                    _ -> {
                        AudioManager.getInstance().stopMenuMusic();
                        GameWindow.getInstance();
                        MainMenu.this.dispose();
                    });

            loadGameButton.addActionListener(
                    _ -> {
                        AudioManager.getInstance().stopMenuMusic();
                        var _ = GameWindow.getInstance();
                        SaveSystem.loadGame(GameEngine.getInstance(), "Save.txt");
                        MainMenu.this.dispose();
                    });

            closeButton.addActionListener(_ -> System.exit(0));

            add(newGameButton);
            add(loadGameButton);
            add(closeButton);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            if (titleImage != null) {
                double titleScaleFactor = (double) getWidth() / titleImage.getWidth(this);
                int scaledTitleHeight = (int) (titleImage.getHeight(this) * titleScaleFactor);
                g.drawImage(titleImage, 0, 80, getWidth(), scaledTitleHeight + 30, this);
            }

            // Position buttons
            int leftButtonX = (getWidth() / 4) - (newGameButton.getPreferredSize().width / 2);
            int rightButtonX = (3 * getWidth() / 4) - (loadGameButton.getPreferredSize().width / 2);
            int buttonY = (5 * getHeight() / 6) - (newGameButton.getPreferredSize().height / 2);

            newGameButton.setBounds(
                    leftButtonX,
                    buttonY,
                    newGameButton.getPreferredSize().width,
                    newGameButton.getPreferredSize().height);
            loadGameButton.setBounds(
                    rightButtonX,
                    buttonY,
                    loadGameButton.getPreferredSize().width,
                    loadGameButton.getPreferredSize().height);

            int closeButtonX = getWidth() - closeButton.getPreferredSize().width - 10;
            int closeButtonY = 10;
            closeButton.setBounds(
                    closeButtonX,
                    closeButtonY,
                    closeButton.getPreferredSize().width,
                    closeButton.getPreferredSize().height);
        }
    }
}
