package main.view;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {

    private JTextArea logArea;

    public LogPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Log della simulazione"));

        logArea = new JTextArea(10, 40); // 10 righe visibili, 40 colonne
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // auto-scroll
    }
}

