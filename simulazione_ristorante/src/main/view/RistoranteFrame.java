package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import main.balducci.interfaces.GruppoClienti;
import main.control.Controller;
import main.palazzetti.interfaces.Ordine;
import main.palazzetti.interfaces.Tavolo;

/**
 *
 * @author milena
 */
public class RistoranteFrame extends javax.swing.JFrame {

    private boolean simulazioneAttiva;
    private final Controller controller;
    private final View view;
    private final Map<Tavolo, JPanel> tavoliMap;
    private String defaultPath = Paths.get(System.getProperty("user.dir")).resolve("simulazione_ristorante/Prodotti.json").toAbsolutePath().toString();

    private Color sfondoPrincipale = new Color(245, 245, 245); // grigio molto chiaro
    private Color sfondoSecondario = new Color(230, 230, 230); // leggermente più scuro
    private Color bordoColore = new Color(100, 100, 100); // grigio scuro
    private Color verdeTavoli = new Color(144, 238, 144); // verde chiaro
    private Color rossoTavoli = new Color(255, 105, 97); // rosso chiaro
    private Color bluBottoni = new Color(100, 149, 237); // blu moderato
    
    /**
     * Creates new form RistoranteFrame
     */
    public RistoranteFrame(Controller controller, View view) {
        this.simulazioneAttiva = false;
        this.controller = controller;
		this.view = view;
        this.tavoliMap = new HashMap<>();

        System.out.println("Path completo: " + defaultPath);

        setTitle("Simulazione Ristorante");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setVisible(true);

        initComponents();


    }
    public void inizializzaTavoli() {
        for (Tavolo t : controller.getTavoli()) {
            JPanel tavoloPanel = new JPanel();
            tavoloPanel.setPreferredSize(new Dimension(120, 80));
            tavoloPanel.setBackground(t.isOccupato() ? rossoTavoli : verdeTavoli);
            tavoloPanel.setBorder(new LineBorder(Color.BLACK, 2));
            tavoloPanel.setLayout(new GridLayout(3, 1));

            tavoloPanel.add(new JLabel("Tavolo: " + t.getNumero()));
            tavoloPanel.add(new JLabel("Posti: " + t.getNumeroPosti()));

            JLabel label = new JLabel("Gruppo: nessuno");
            label.setName("gruppoLabel");
            tavoloPanel.add(label);

            tavoliMap.put(t, tavoloPanel);
            innerTavoliPanel.add(tavoloPanel);
        }

        innerTavoliPanel.revalidate();
        innerTavoliPanel.repaint();

    }

    public void aggiornaTavoli() {
        for(Tavolo t : controller.getTavoli()){
        JPanel panel = tavoliMap.get(t);
        JLabel label = null;
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel l && "gruppoLabel".equals(l.getName())) {
                label = l;
            }
}
        if (t.isOccupato()) {
            panel.setBackground(rossoTavoli);
            label.setText("Gruppo: " + t.getGruppoCorrente().getId());
        } else {
            panel.setBackground(verdeTavoli);
            label.setText("Gruppo: nessuno");
        }
        tavoliMap.put(t, panel);
        }
        
        tavoliPanel.revalidate();
        tavoliPanel.repaint();
    }

    public void printGruppiInAttesa(List<GruppoClienti> gruppi){
        String listaGruppi = gruppi.stream()
            .map(g -> g.getId() + ", " + g.getNumeroClienti() + " clienti")
            .collect(Collectors.joining("\n"));
        this.gruppiPanelTA.setText(listaGruppi);
    }

    public void printListaOrdini(List<Ordine> ordini){
        String risultato = ordini.stream()
            .map(ordine -> {
                StringBuilder sb = new StringBuilder();
                sb.append("Tavolo: ").append(ordine.getTavoloRiferimento().getNumero()).append("\n");
                sb.append(ordine.toString());
                //ordine.getProdotti().keySet().forEach(prodotto -> 
                //    sb.append(prodotto.getNome()).append("\n")
                //);
            
                return sb.toString().trim();
            })
            .collect(Collectors.joining("\n\n"));    
        
        ordiniPanelTA.setText(risultato);
    }

    public void printRichiesteConto(List<GruppoClienti> gruppi){
        String listaGruppi = gruppi.stream()
            .map(GruppoClienti::getId)
            .collect(Collectors.joining("\n"));
        this.rcPanelTA.setText(listaGruppi);
    }

    public void printMessaggi(String messaggi){
        this.logTextArea.setText(messaggi);
    }

    public void printTotali(String totali){
        this.incassiTA.setText(totali);
    }
    
    public int getNumClienti(){
        return (int)clientiSpin.getValue();
    }
    
    public int getDurataInserita(){
        return (int)durataSpin.getValue();
    }
    
    public String getPathInserito(){
        return percorsoField.getText();
    }
    
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */                         
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        logPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logScrollPane = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        tavoliPanel = new javax.swing.JPanel();
        innerTavoliPanel = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        impostazioniLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        clientiSpin = new javax.swing.JSpinner(new SpinnerNumberModel(50, 2, 100, 1));
        jLabel11 = new javax.swing.JLabel();
        durataSpin = new javax.swing.JSpinner(new SpinnerNumberModel(5, controller.getDurataMinimaSimulazione(), controller.getDurataMassimaSimulazione(), 1));
        buttonPanel = new javax.swing.JPanel();
        avviaButton = new javax.swing.JButton();
        fermaButton = new javax.swing.JButton();
        menuLabel = new javax.swing.JLabel();
        percorsoField = new javax.swing.JTextField();
        apriButton = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        gruppiPanel = new javax.swing.JPanel();
        gruppiPanelLabel = new javax.swing.JLabel();
        gruppiPanelScroll = new javax.swing.JScrollPane();
        gruppiPanelTA = new javax.swing.JTextArea();
        ordiniPanel = new javax.swing.JPanel();
        ordiniPanelLabel = new javax.swing.JLabel();
        ordiniPanelScroll = new javax.swing.JScrollPane();
        ordiniPanelTA = new javax.swing.JTextArea();
        richiesteContoPanel = new javax.swing.JPanel();
        rcPanelLabel = new javax.swing.JLabel();
        rcPanelScroll = new javax.swing.JScrollPane();
        rcPanelTA = new javax.swing.JTextArea();
        incassiPanel = new JPanel();
        jLabel5 = new JLabel();
        incassiTA = new JTextArea();
        jScrollPane2 = new JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        avviaButton.setBackground(bluBottoni);
        avviaButton.setForeground(Color.WHITE);
        avviaButton.setFocusPainted(false);
        avviaButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

        fermaButton.setBackground(new Color(220, 53, 69)); 
        fermaButton.setForeground(Color.WHITE);
        fermaButton.setFocusPainted(false);
        fermaButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

        apriButton.setBackground(new Color(40, 167, 69)); 
        apriButton.setForeground(Color.WHITE);
        apriButton.setFocusPainted(false);
        apriButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        mainPanel.setBackground(sfondoPrincipale);
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, bordoColore),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        topPanel.setBackground(new java.awt.Color(51, 255, 51));
        topPanel.setLayout(new java.awt.BorderLayout());

        logPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Simulazione");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        logScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        logTextArea.setColumns(20);
        logTextArea.setFont(new java.awt.Font("Roboto Condensed", 0, 13)); // NOI18N
        logTextArea.setRows(5);
        logTextArea.setBorder(null);
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        logScrollPane.setViewportView(logTextArea);

        javax.swing.GroupLayout logPanelLayout = new javax.swing.GroupLayout(logPanel);
        logPanel.setLayout(logPanelLayout);
        logPanelLayout.setHorizontalGroup(
            logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        logPanelLayout.setVerticalGroup(
            logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        topPanel.add(logPanel, java.awt.BorderLayout.WEST);

        tavoliPanel.setBackground(new java.awt.Color(255, 255, 255));

        innerTavoliPanel.setBackground(new java.awt.Color(228, 228, 228));
        innerTavoliPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));
        innerTavoliPanel.setLayout(new java.awt.GridLayout(4, 5));
        innerTavoliPanel.setPreferredSize(new Dimension(600, 320));


        javax.swing.GroupLayout tavoliPanelLayout = new javax.swing.GroupLayout(tavoliPanel);
        tavoliPanel.setLayout(tavoliPanelLayout);
        tavoliPanelLayout.setHorizontalGroup(
            tavoliPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tavoliPanelLayout.createSequentialGroup()
                .addComponent(innerTavoliPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tavoliPanelLayout.setVerticalGroup(
            tavoliPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tavoliPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(innerTavoliPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        topPanel.add(tavoliPanel, java.awt.BorderLayout.CENTER);

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));

        impostazioniLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        impostazioniLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        impostazioniLabel.setText("Impostazioni");
        impostazioniLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setText("Numero clienti:");

        jLabel11.setText("Durata simulazione:");

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));

        avviaButton.setFont(new java.awt.Font("Roboto Condensed", 1, 12)); // NOI18N
        avviaButton.setText("Avvia");
        avviaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avviaButtonActionPerformed(evt);
            }
        });

        fermaButton.setFont(new java.awt.Font("Roboto Condensed", 1, 12)); // NOI18N
        fermaButton.setText("Ferma");
        fermaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fermaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(avviaButton)
                .addGap(18, 18, 18)
                .addComponent(fermaButton)
                .addGap(35, 35, 35))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(buttonPanelLayout.createSequentialGroup()
                    .addGap(10)
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fermaButton)
                        .addComponent(avviaButton))
                    .addGap(10))
        );

        menuLabel.setText("Menu:");

        clientiSpin.setPreferredSize(new Dimension(64, 25));
        clientiSpin.setMinimumSize(new Dimension(64, 25));
        clientiSpin.setMaximumSize(new Dimension(64, 25));
        durataSpin.setPreferredSize(new Dimension(64, 25));
        durataSpin.setMinimumSize(new Dimension(64, 25));
        durataSpin.setMaximumSize(new Dimension(64, 25));

        percorsoField.setFont(new java.awt.Font("Roboto Condensed", 0, 13)); // NOI18N
        percorsoField.setText(defaultPath);
        percorsoField.setPreferredSize(new Dimension(181, 25));
        percorsoField.setMinimumSize(new Dimension(181, 25));
        percorsoField.setMaximumSize(new Dimension(181, 25));

        apriButton.setFont(new java.awt.Font("Roboto Condensed", 1, 12)); // NOI18N
        apriButton.setText("Apri");
        apriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apriButtonActionPerformed(evt);
            }
        });

        incassiPanel.setBackground(new java.awt.Color(255, 255, 255));
        incassiPanel.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Incassi");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        incassiPanel.add(jLabel5, java.awt.BorderLayout.NORTH);

        incassiTA.setColumns(20);
        incassiTA.setRows(5);
        incassiTA.setFont(new java.awt.Font("Roboto Condensed", 0, 16));
        jScrollPane2.setViewportView(incassiTA);

        incassiPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(impostazioniLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clientiSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(durataSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(incassiPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addComponent(menuLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(percorsoField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(apriButton)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(impostazioniLabel)
                .addGap(26, 26, 26)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clientiSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(durataSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menuLabel)
                    .addComponent(percorsoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apriButton)
                .addGap(28, 28, 28)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(incassiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        topPanel.add(settingsPanel, java.awt.BorderLayout.EAST);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setLayout(new java.awt.GridLayout());

        gruppiPanel.setBackground(sfondoSecondario);
        gruppiPanel.setLayout(new java.awt.BorderLayout());

        gruppiPanelLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        gruppiPanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gruppiPanelLabel.setText("Gruppi in attesa");
        gruppiPanelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gruppiPanel.add(gruppiPanelLabel, java.awt.BorderLayout.NORTH);

        gruppiPanelTA.setColumns(20);
        gruppiPanelTA.setFont(new java.awt.Font("Roboto Condensed", 0, 16)); // NOI18N
        gruppiPanelTA.setRows(5);
        gruppiPanelScroll.setViewportView(gruppiPanelTA);

        gruppiPanel.add(gruppiPanelScroll, java.awt.BorderLayout.CENTER);

        bottomPanel.add(gruppiPanel);

        ordiniPanel.setBackground(sfondoSecondario);
        ordiniPanel.setLayout(new java.awt.BorderLayout());

        ordiniPanelLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        ordiniPanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordiniPanelLabel.setText("Ordini in attesa");
        ordiniPanelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ordiniPanel.add(ordiniPanelLabel, java.awt.BorderLayout.NORTH);

        ordiniPanelTA.setColumns(20);
        ordiniPanelTA.setRows(5);
        ordiniPanelTA.setFont(new java.awt.Font("Roboto Condensed", 0, 16));
        ordiniPanelScroll.setViewportView(ordiniPanelTA);

        ordiniPanel.add(ordiniPanelScroll, java.awt.BorderLayout.CENTER);

        bottomPanel.add(ordiniPanel);

        richiesteContoPanel.setBackground(sfondoSecondario);
        richiesteContoPanel.setLayout(new java.awt.BorderLayout());

        rcPanelLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        rcPanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rcPanelLabel.setText("Richieste conto");
        rcPanelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        richiesteContoPanel.add(rcPanelLabel, java.awt.BorderLayout.NORTH);

        rcPanelTA.setColumns(20);
        rcPanelTA.setRows(5);
        rcPanelTA.setFont(new java.awt.Font("Roboto Condensed", 0, 16));
        rcPanelScroll.setViewportView(rcPanelTA);

        richiesteContoPanel.add(rcPanelScroll, java.awt.BorderLayout.CENTER);

        bottomPanel.add(richiesteContoPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void apriButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        JFileChooser fileChooser = new JFileChooser();
        int risultato = fileChooser.showOpenDialog(this);

        if (risultato == JFileChooser.APPROVE_OPTION) {
            File fileSelezionato = fileChooser.getSelectedFile();
            String percorso = fileSelezionato.getAbsolutePath();
            System.out.println("Hai scelto: " + percorso);

            percorsoField.setText(fileSelezionato.getAbsolutePath());
        }
    }  

    private void avviaButtonActionPerformed(java.awt.event.ActionEvent evt) { 
        if(this.simulazioneAttiva == false){   
            this.incassiTA.setText("");                                     
            view.simula();
            this.simulazioneAttiva = true;
        }
    }                                        

    private void fermaButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        if(this.simulazioneAttiva = true){
            this.fermaSimulazione();                                
            view.notificaSimulazioneFermata();
            this.simulazioneAttiva = false;
        }
    }                        
    
    public void fermaSimulazione(){
        this.tavoliMap.clear();
        innerTavoliPanel.removeAll();
        innerTavoliPanel.revalidate();
        innerTavoliPanel.repaint();
        this.gruppiPanelTA.setText("");  
        this.ordiniPanelTA.setText("");
        this.rcPanelTA.setText("");
        this.logTextArea.setText(""); 
    }

    // Variables declaration - do not modify                     
    private JPanel bottomPanel;
    private JPanel buttonPanel;
    private JPanel gruppiPanel;
    private JLabel gruppiPanelLabel;
    private JScrollPane gruppiPanelScroll;
    private JTextArea gruppiPanelTA;
    private JButton avviaButton;
    private JButton fermaButton;
    private JButton apriButton;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel impostazioniLabel;
    private JLabel menuLabel;
    private JPanel innerTavoliPanel;
    private JScrollPane logScrollPane;
    private JSpinner clientiSpin;
    private JSpinner durataSpin;
    private JTextArea logTextArea;
    private JTextField percorsoField;
    private JPanel logPanel;
    private JPanel mainPanel;
    private JPanel ordiniPanel;
    private JLabel ordiniPanelLabel;
    private JScrollPane ordiniPanelScroll;
    private JTextArea ordiniPanelTA;
    private JLabel rcPanelLabel;
    private JScrollPane rcPanelScroll;
    private JTextArea rcPanelTA;
    private JPanel richiesteContoPanel;
    private JPanel settingsPanel;
    private JPanel tavoliPanel;
    private JPanel topPanel;
    private JPanel incassiPanel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea incassiTA;
    // End of variables declaration                   
}
