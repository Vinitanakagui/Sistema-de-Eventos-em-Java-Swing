package visao.semana;

import controle.EventoDAO;
import controle.SemanaDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;
import modelo.Evento;
import modelo.Participante;
import modelo.Semana;
import modelo.enuns.Ativo;
import utils.DateUtils;
import utils.JOptionPaneUtils;

public class TelaVisualizarSemana extends javax.swing.JDialog {

    private Boolean menuAtivo;
    private Administrador administrador;
    private Participante participante;
    private SemanaDAO semanaDAO;
    private Semana semana;
    private List<Evento> listaEvento;
    private EventoDAO eventoDAO;
    private Evento evento;
    private static DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public TelaVisualizarSemana(java.awt.Frame parent, boolean modal, Participante participante, Administrador administrador, Semana semana) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.menuAtivo = false;
        this.administrador = administrador;
        this.participante = participante;
        this.semanaDAO = new SemanaDAO();
        this.semana = semana;
        this.listaEvento = new ArrayList<>();
        this.eventoDAO = new EventoDAO();
        this.botaoCancelar.setVisible(false);
        this.evento = new Evento();
        
        if(participante == null)
        {
            ajustarComponentesAdmin();
        }
        
        ajustarCorPanelEvento();
        ajustarCamposEventos();
        preencherTabelaCurso();
        menuInicializar();
        
        textPesquisar.getDocument().addDocumentListener(new DocumentListener() 
        {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                pesquisar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                pesquisar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                pesquisar();
            }
        });
        
        tabelaEvento.addMouseListener(new MouseAdapter() 
        {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getClickCount() == 2) 
                    {
                        int linha = tabelaEvento.getSelectedRow(); 
                        
                        if (linha != -1) 
                        {
                        
                            visualizarEvento(linha);
                            
                        }
                    }
                }
        });
        
        
   }
    
    private void visualizarEvento(Integer linha)
    {
        String nome = (String) tabelaEvento.getValueAt(linha, 0);
        
        try
        {
            evento = eventoDAO.buscarEventoPorSemana(nome, semana.getIdSemana());
            
        }
        catch(PersistenceException e)
        {
            JOptionPaneUtils.erro("Erro: " + e.getMessage(), "Erro");
        }
    }
    
    private void pesquisar()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEvento.getModel();
        modelo.setRowCount(0);
        
        listaEvento = eventoDAO.pesquisar(textPesquisar.getText());
        
        for (Evento evento : listaEvento) 
        {
            modelo.addRow(new String[] 
            {
            
                evento.getTitulo(),
                String.valueOf(evento.getCargaHoraria()),
                String.valueOf(evento.getNumeroVagas()),
                String.valueOf(evento.getQntdeIncrito()),
                evento.getData().format(formatoBR)
            
            });
        }
    }
    
    private void menuInicializar()
    {
        
            labelEventos.setVisible(false);
            labelCerificado.setVisible(false);
            labelPerfil.setVisible(false);
            
            panelMenuSuspenso.setPreferredSize(new Dimension(70, 649));
            panelMenuSuspenso.revalidate();
            panelMenuSuspenso.repaint();  
            
            revalidate();
            pack();
            
            
    }
   
    private void ajustarComponentesAdmin()
    {
        botaoEditar.setVisible(true);
    }
    private void ajustarCorPanelEvento()
    {
        Color cor = new Color(1, 114, 18);
        panelEventos.setBackground(cor); 
    }
    
    private void preencherTabelaCurso()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEvento.getModel();
        modelo.setRowCount(0);
        
        listaEvento = eventoDAO.buscarListaEventoPorSemana(semana.getIdSemana());
        
        for (Evento evento : listaEvento) 
        {
            modelo.addRow(new String[] 
            {
            
                evento.getTitulo(),
                String.valueOf(evento.getCargaHoraria()),
                String.valueOf(evento.getNumeroVagas()),
                String.valueOf(evento.getQntdeIncrito()),
                evento.getData().format(formatoBR)
            
            });
        }
    }
    
    private void ajustarCamposEventos()
    {
        textNome.setText(semana.getNome());
        textLocal.setText(semana.getLocal());
        textInicio.setDate(DateUtils.asDate(semana.getInicio()));
        textFim.setDate(DateUtils.asDate(semana.getFim()));
        textObservacao.setText(semana.getObservacao());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelMenu = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        panelMenuSuspenso = new javax.swing.JPanel();
        panelEventos = new javax.swing.JPanel();
        labelIconEventos = new javax.swing.JLabel();
        labelEventos = new javax.swing.JLabel();
        panelPerfil = new javax.swing.JPanel();
        labelIconPerfil = new javax.swing.JLabel();
        labelPerfil = new javax.swing.JLabel();
        panelCertificado = new javax.swing.JPanel();
        labelIconCertificado = new javax.swing.JLabel();
        labelCerificado = new javax.swing.JLabel();
        separadorAtividades = new javax.swing.JSeparator();
        labelNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEvento = new javax.swing.JTable();
        labelLocal = new javax.swing.JLabel();
        labelInicio = new javax.swing.JLabel();
        labelFim = new javax.swing.JLabel();
        labelObs = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        separadorAtividades1 = new javax.swing.JSeparator();
        labelTituloTabela1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textPesquisar = new javax.swing.JTextField();
        labelAdicionar = new javax.swing.JLabel();
        botaoEditar = new javax.swing.JButton();
        labelVoltar = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        textLocal = new javax.swing.JTextField();
        textInicio = new com.toedter.calendar.JDateChooser();
        comboSituacao = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        textObservacao = new javax.swing.JTextArea();
        textFim = new com.toedter.calendar.JDateChooser();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(1, 114, 18));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        labelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/icon__menu.png"))); // NOI18N
        labelMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenu)
                .addContainerGap())
        );

        labelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        labelTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTitulo.setText("Dados da Semana");

        panelMenuSuspenso.setBackground(new java.awt.Color(1, 155, 45));
        panelMenuSuspenso.setPreferredSize(new java.awt.Dimension(189, 649));

        panelEventos.setBackground(new java.awt.Color(1, 155, 45));

        labelIconEventos.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelIconEventos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconEventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/calendario.png"))); // NOI18N

        labelEventos.setBackground(new java.awt.Color(255, 255, 255));
        labelEventos.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelEventos.setText("Eventos");

        javax.swing.GroupLayout panelEventosLayout = new javax.swing.GroupLayout(panelEventos);
        panelEventos.setLayout(panelEventosLayout);
        panelEventosLayout.setHorizontalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventosLayout.createSequentialGroup()
                .addComponent(labelIconEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelEventosLayout.setVerticalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEventosLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIconEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        panelPerfil.setBackground(new java.awt.Color(1, 155, 45));
        panelPerfil.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconPerfil.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconPerfil.setForeground(new java.awt.Color(255, 255, 255));
        labelIconPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/usuario-masculino.png"))); // NOI18N
        labelIconPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIconPerfilMouseExited(evt);
            }
        });

        labelPerfil.setBackground(new java.awt.Color(255, 255, 255));
        labelPerfil.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelPerfil.setForeground(new java.awt.Color(255, 255, 255));
        labelPerfil.setText("Perfil");
        labelPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelPerfilMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelPerfilLayout = new javax.swing.GroupLayout(panelPerfil);
        panelPerfil.setLayout(panelPerfilLayout);
        panelPerfilLayout.setHorizontalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPerfilLayout.createSequentialGroup()
                .addComponent(labelIconPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelPerfilLayout.setVerticalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelIconPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        panelCertificado.setBackground(new java.awt.Color(1, 155, 45));
        panelCertificado.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconCertificado.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconCertificado.setForeground(new java.awt.Color(255, 255, 255));
        labelIconCertificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/certificado.png"))); // NOI18N
        labelIconCertificado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconCertificadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIconCertificadoMouseExited(evt);
            }
        });

        labelCerificado.setBackground(new java.awt.Color(255, 255, 255));
        labelCerificado.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelCerificado.setForeground(new java.awt.Color(255, 255, 255));
        labelCerificado.setText("Certificados");
        labelCerificado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCerificadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCerificadoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelCertificadoLayout = new javax.swing.GroupLayout(panelCertificado);
        panelCertificado.setLayout(panelCertificadoLayout);
        panelCertificadoLayout.setHorizontalGroup(
            panelCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCertificadoLayout.createSequentialGroup()
                .addComponent(labelIconCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCerificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCertificadoLayout.setVerticalGroup(
            panelCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCerificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelIconCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMenuSuspensoLayout = new javax.swing.GroupLayout(panelMenuSuspenso);
        panelMenuSuspenso.setLayout(panelMenuSuspensoLayout);
        panelMenuSuspensoLayout.setHorizontalGroup(
            panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                .addGroup(panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                        .addComponent(panelCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                        .addComponent(panelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuSuspensoLayout.setVerticalGroup(
            panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                .addComponent(panelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        labelNome.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelNome.setText("Nome: ");

        tabelaEvento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título", "Carga Horária", "Total Vagas", "Inscritos", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEvento.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tabelaEvento);
        tabelaEvento.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        labelLocal.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelLocal.setText("Local: ");

        labelInicio.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelInicio.setText("Início: ");

        labelFim.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelFim.setText("Fim: ");

        labelObs.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelObs.setText("Observação: ");

        labelSituacao.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelSituacao.setText("Situação: ");

        labelTituloTabela1.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela1.setText("Cursos e Palestras");

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel1.setText("Pesquisar:");

        textPesquisar.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/adicionar.png"))); // NOI18N

        botaoEditar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        labelVoltar.setBackground(new java.awt.Color(255, 255, 255));
        labelVoltar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/voltar.png"))); // NOI18N
        labelVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelVoltarMouseClicked(evt);
            }
        });

        textNome.setEditable(false);
        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        textLocal.setEditable(false);
        textLocal.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        textInicio.setEnabled(false);
        textInicio.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        comboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Andamento", "Encerrado" }));
        comboSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSituacaoActionPerformed(evt);
            }
        });

        textObservacao.setEditable(false);
        textObservacao.setColumns(20);
        textObservacao.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        textObservacao.setLineWrap(true);
        textObservacao.setRows(5);
        textObservacao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textObservacao);

        textFim.setEnabled(false);
        textFim.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        botaoCancelar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelObs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelInicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelFim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(labelSituacao)
                                .addGap(18, 18, 18)
                                .addComponent(comboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(labelLocal)
                                .addGap(18, 18, 18)
                                .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelTituloTabela1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorAtividades1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTitulo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(botaoEditar))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelAdicionar))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(labelTitulo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelVoltar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLocal)
                            .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelFim)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelInicio)
                                    .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(2, 2, 2)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelObs)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(labelTituloTabela1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(separadorAtividades1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelAdicionar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoCancelar)
                            .addComponent(botaoEditar))
                        .addGap(37, 37, 37))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuMouseClicked
        
        
        if(menuAtivo)
        {
            menuAtivo = false;
            
            labelEventos.setVisible(true);
            labelCerificado.setVisible(true);
            labelPerfil.setVisible(true);
            
            panelMenuSuspenso.setPreferredSize(new Dimension(195, 649));
            panelMenuSuspenso.revalidate();
            panelMenuSuspenso.repaint(); 
            
           
        }
        else
        {
            menuAtivo = true;
            
            labelEventos.setVisible(false);
            labelCerificado.setVisible(false);
            labelPerfil.setVisible(false);
           
            panelMenuSuspenso.setPreferredSize(new Dimension(70, 649));
            panelMenuSuspenso.revalidate();
            panelMenuSuspenso.repaint();  
            
            
        }
      
    }//GEN-LAST:event_labelMenuMouseClicked

    private void labelIconPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconPerfilMouseEntered
        Color cor = new Color(1, 114, 18);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelIconPerfilMouseEntered

    private void labelIconPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconPerfilMouseExited
        Color cor = new Color(1, 155, 45);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelIconPerfilMouseExited

    private void labelPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPerfilMouseEntered
        Color cor = new Color(1, 114, 18);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelPerfilMouseEntered

    private void labelPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPerfilMouseExited
        Color cor = new Color(1, 155, 45);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelPerfilMouseExited

    private void labelIconCertificadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconCertificadoMouseExited
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelIconCertificadoMouseExited

    private void labelCerificadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerificadoMouseExited
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelCerificadoMouseExited

    private void labelCerificadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerificadoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelCerificadoMouseEntered

    private void labelIconCertificadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconCertificadoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelIconCertificadoMouseEntered

    private void comboSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSituacaoActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        
        if(botaoEditar.getText().equals("Editar"))
        {
            botaoEditar.setText("Atualizar");
            
            textNome.setEditable(true);
            textLocal.setEditable(true);
            textFim.setEnabled(false);
            textInicio.setEnabled(false);
            textObservacao.setEditable(true);
            comboSituacao.setEditable(true);
            
            labelTitulo.setText("Atualizar Dados");
            botaoCancelar.setVisible(true);
        }
        else
        {
            labelTitulo.setText("Dados da Semana");
            botaoCancelar.setVisible(false);
            
            if(comboSituacao.getSelectedItem().equals("Em Andamento"))
            {
                semana.setAtiva(Ativo.S);
            }
            else
            {
                semana.setAtiva(Ativo.N);
            }
            
            semana.setNome(textNome.getText());
            semana.setLocal(textLocal.getText());
            semana.setInicio(DateUtils.asLocalDate(textInicio.getDate()));
            semana.setFim(DateUtils.asLocalDate(textFim.getDate()));
            semana.setObservacao(textObservacao.getText());
            
            try 
            {
                semanaDAO.alterar(semana);
                JOptionPaneUtils.sucesso("Dados atualizados com sucesso!", null);
            } 
            catch (PersistenceException e) 
            {
                System.out.println("Erro ao alterar os dados no banco: " + e.getMessage());
            }

            ajustarCamposEventos();
            
          
        }
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void labelVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVoltarMouseClicked
        dispose();
    }//GEN-LAST:event_labelVoltarMouseClicked

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JComboBox<String> comboSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAdicionar;
    private javax.swing.JLabel labelCerificado;
    private javax.swing.JLabel labelEventos;
    private javax.swing.JLabel labelFim;
    private javax.swing.JLabel labelIconCertificado;
    private javax.swing.JLabel labelIconEventos;
    private javax.swing.JLabel labelIconPerfil;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JLabel labelLocal;
    private javax.swing.JLabel labelMenu;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelPerfil;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTituloTabela1;
    private javax.swing.JLabel labelVoltar;
    private javax.swing.JPanel panelCertificado;
    private javax.swing.JPanel panelEventos;
    private javax.swing.JPanel panelMenuSuspenso;
    private javax.swing.JPanel panelPerfil;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JSeparator separadorAtividades1;
    private javax.swing.JTable tabelaEvento;
    private com.toedter.calendar.JDateChooser textFim;
    private com.toedter.calendar.JDateChooser textInicio;
    private javax.swing.JTextField textLocal;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextArea textObservacao;
    private javax.swing.JTextField textPesquisar;
    // End of variables declaration//GEN-END:variables
}
