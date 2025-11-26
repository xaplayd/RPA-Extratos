package main.java.utils;

import javax.swing.*;
import java.awt.*;

public class LoadingFrame extends JWindow {

	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
    private JLabel statusLabel;

    public LoadingFrame() {
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setFocusableWindowState(false);

        JPanel panel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(50, 50, 50, 200)); 
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        statusLabel = new JLabel("Iniciando...");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(statusLabel, BorderLayout.NORTH);

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 120, 215));
        progressBar.setBackground(new Color(200, 200, 200, 100));
        progressBar.setIndeterminate(true);
        panel.add(progressBar, BorderLayout.CENTER);

        setContentPane(panel);

        setSize(400, 80);

        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

        int x = (screenBounds.width - getWidth()) / 2;
        int y = screenBounds.height - getHeight() - screenInsets.bottom - 10;
        setLocation(x, y);
    }

    public void mostrar() {
        setVisible(true);
    }

    public void fechar() {
        setVisible(false);
        dispose();
    }

    public void setStatus(String texto) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(texto));
    }

    public void setProgress(int valor) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setIndeterminate(false);
            progressBar.setValue(valor);
        });
    }
}
