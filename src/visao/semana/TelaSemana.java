package visao.semana;

import controle.SemanaDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;
import modelo.Participante;
import modelo.Semana;
import utils.DateUtils;
import utils.JOptionPaneUtils;
import visao.evento.TelaHistoricoEventos;
import visao.dados.TelaDados;
import visao.dados.TelaDadosAdministrador;
import visao.home.TelaMenuPrincipal;

public class TelaSemana extends javax.swing.JDialog {

    private Boolean menuAtivo;
    private Administrador administrador;
    private Participante participante;
    private SemanaDAO semanaDAO;
    private List<Semana> listaSemana;
    private DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Semana semana;
    
    public TelaSemana(java.awt.Frame parent, boolean modal, Participante participante, Administrador administrador) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.menuAtivo = false;
        this.administrador = administrador;
        this.participante = participante;
        this.semanaDAO = new SemanaDAO();
        this.listaSemana = new ArrayList<>();
        this.semana = new Semana();
        
        menuInicializar();
        ajustarCorPanelEvento();
        carregarComponentesAdmin();
        preencherTabela();
        
        tabelaSemana.addMouseListener(new MouseAdapter() 
        {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getClickCount() == 2) 
                    {
                        int linha = tabelaSemana.getSelectedRow(); 
                        
                        if (linha != -1) 
                        {
                        
                            visualizarEvento(linha);
                            
                        }
                    }
                }
        });
        

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
        
        if(participante == null)
        {
            ajustarCampoBemVindo(administrador.getLogin());
        }
        else
        {
            ajustarCampoBemVindo(participante.getNome());
        }
        
        labelSair.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {

                int resposta = JOptionPaneUtils.confirmacao("Deseja se desconectar?", "Atenção");

                if (resposta == JOptionPane.YES_OPTION) {
                    Window currentWindow = SwingUtilities.getWindowAncestor((JLabel) e.getSource());

                    while (currentWindow != null && !(currentWindow instanceof JFrame)) 
                    {
                        currentWindow.dispose(); 
                        currentWindow = SwingUtilities.getWindowAncestor(currentWindow); 
                    }
                }
            }
        });
        
        ajustarColunas();
   }
    
    private void ajustarColunas()
    {
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);

        tabelaSemana.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        tabelaSemana.getColumnModel().getColumn(1).setCellRenderer(esquerda);
        tabelaSemana.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabelaSemana.getColumnModel().getColumn(3).setCellRenderer(centro);
         
    }
    
    private void ajustarCampoBemVindo(String nome)
    {
        labelBemVindo.setText(nome);
    }
    
    
    private void visualizarEvento(Integer linha) 
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         
        if(linha != -1)
        {
                   
            try
            {
                Date inicio = formato.parse((String) tabelaSemana.getValueAt(linha, 2));
                Date fim = formato.parse((String) tabelaSemana.getValueAt(linha, 3));
                String nome = (String) tabelaSemana.getValueAt(linha, 0);
                
                 try
                {
                    semana = semanaDAO.buscarSemana(nome, DateUtils.asLocalDate(inicio), DateUtils.asLocalDate(fim));
                    this.setVisible(false);

                    if(participante == null)             
                    {
                        TelaEditarSemana telaEditarSemana = new TelaEditarSemana((Frame) this.getParent(), true, semana);
                        telaEditarSemana.setVisible(true);
                        
                        preencherTabela();
                    }
                    else
                    {
                        TelaInscreverSemana telaInscreverSemana = new TelaInscreverSemana((Frame) this.getParent(), true, semana, participante);
                        telaInscreverSemana.setVisible(true);
                    }

                    this.setVisible(true);
                }
                catch(PersistenceException e)
                {
                    JOptionPaneUtils.erro("Erro ao buscar Semana", "Atenção");
                }
                }
                catch(ParseException e)
                {
                    System.out.println("Erro ao converter variáveis (String --> Date)");
                }
                 

           
            
            
            
        }
        
    }
    
    private void preencherTabela()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaSemana.getModel();
        modelo.setRowCount(0);
        
        if(administrador != null)
        {
            listaSemana = semanaDAO.buscarSemanaAtiva();   
        }
        else
        {
           listaSemana = semanaDAO.buscarSemanaAtiva();
        }
        
        for (Semana semana : listaSemana) 
        {
            modelo.addRow(new String[]
            {
                semana.getNome(),
                semana.getLocal(),
                String.valueOf(semana.getInicio().format(formatoBR)),
                String.valueOf(semana.getFim().format(formatoBR))
            });
        }
    }
    
    private void pesquisar()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaSemana.getModel();
        modelo.setRowCount(0);
        
        String nome = textPesquisar.getText();
        
        if(checkSituacao.isSelected())
        {
            listaSemana = semanaDAO.pesquisar(nome);
        }
        else
        {
            if(nome.isEmpty())
            {
                listaSemana = semanaDAO.buscarSemanaAtiva();
            }
            else
            {
                listaSemana = semanaDAO.pesquisarSemanaAtiva(nome);
            }
            
        }
        
        for (Semana semana : listaSemana) 
        {
            modelo.addRow(new String[]
            {
                semana.getNome(),
                semana.getLocal(),
                String.valueOf(semana.getInicio().format(formatoBR)),
                String.valueOf(semana.getFim().format(formatoBR))
            });
        }
        
        
    }
    
    private void carregarComponentesAdmin()
    {
        if(this.participante == null)
        {
            labelAdicionar.setVisible(true);
            checkSituacao.setVisible(true);
            labelPesquisar.setVisible(true);
            textPesquisar.setVisible(true);
        } 
    }
    
    private void ajustarCorPanelEvento()
    {
        Color cor = new Color(1, 114, 18);
        panelEventos.setBackground(cor); 
    }
    
    private void menuInicializar()
    {
        
            labelEventos.setVisible(false);
            labelHistorico.setVisible(false);
            labelPerfil.setVisible(false);
            
            panelMenuSuspenso.setPreferredSize(new Dimension(70, 649));
            panelMenuSuspenso.revalidate();
            panelMenuSuspenso.repaint();  
            
            revalidate();
            pack();
            
            
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelMenu = new javax.swing.JLabel();
        labelBemVindo = new javax.swing.JLabel();
        separadorAtividades1 = new javax.swing.JSeparator();
        labelSair = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaSemana = new javax.swing.JTable();
        labelTituloTabela = new javax.swing.JLabel();
        panelMenuSuspenso = new javax.swing.JPanel();
        panelEventos = new javax.swing.JPanel();
        labelIconEventos = new javax.swing.JLabel();
        labelEventos = new javax.swing.JLabel();
        panelPerfil = new javax.swing.JPanel();
        labelIconPerfil = new javax.swing.JLabel();
        labelPerfil = new javax.swing.JLabel();
        panelCertificado = new javax.swing.JPanel();
        labelIconHistorico = new javax.swing.JLabel();
        labelHistorico = new javax.swing.JLabel();
        panelInicio = new javax.swing.JPanel();
        labelInicio = new javax.swing.JLabel();
        labelIconInicio = new javax.swing.JLabel();
        separadorAtividades = new javax.swing.JSeparator();
        textPesquisar = new javax.swing.JTextField();
        labelPesquisar = new javax.swing.JLabel();
        labelAdicionar = new javax.swing.JLabel();
        checkSituacao = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 633));

        jPanel2.setBackground(new java.awt.Color(1, 114, 18));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        labelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/icon__menu.png"))); // NOI18N
        labelMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuMouseClicked(evt);
            }
        });

        labelBemVindo.setBackground(new java.awt.Color(255, 255, 255));
        labelBemVindo.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelBemVindo.setForeground(new java.awt.Color(255, 255, 255));

        separadorAtividades1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        labelSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/sair.png"))); // NOI18N
        labelSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1292, Short.MAX_VALUE)
                .addComponent(labelBemVindo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorAtividades1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSair)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelSair)
                        .addComponent(separadorAtividades1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelMenu))
                .addContainerGap())
        );

        tabelaSemana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título", "Local", "Início", "Fim"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelaSemana);
        if (tabelaSemana.getColumnModel().getColumnCount() > 0) {
            tabelaSemana.getColumnModel().getColumn(1).setMinWidth(300);
            tabelaSemana.getColumnModel().getColumn(1).setMaxWidth(300);
            tabelaSemana.getColumnModel().getColumn(2).setMinWidth(100);
            tabelaSemana.getColumnModel().getColumn(2).setMaxWidth(100);
            tabelaSemana.getColumnModel().getColumn(3).setMinWidth(100);
            tabelaSemana.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        labelTituloTabela.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela.setText("Semanas");

        panelMenuSuspenso.setBackground(new java.awt.Color(1, 155, 45));
        panelMenuSuspenso.setPreferredSize(new java.awt.Dimension(189, 649));

        panelEventos.setBackground(new java.awt.Color(1, 155, 45));

        labelIconEventos.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelIconEventos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconEventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/calendario.png"))); // NOI18N
        labelIconEventos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelEventos.setBackground(new java.awt.Color(255, 255, 255));
        labelEventos.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelEventos.setText("Semanas");
        labelEventos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                .addContainerGap())
        );

        panelPerfil.setBackground(new java.awt.Color(1, 155, 45));
        panelPerfil.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconPerfil.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconPerfil.setForeground(new java.awt.Color(255, 255, 255));
        labelIconPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/usuario-masculino.png"))); // NOI18N
        labelIconPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIconPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconPerfilMouseClicked(evt);
            }
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
        labelPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPerfilMouseClicked(evt);
            }
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

        labelIconHistorico.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconHistorico.setForeground(new java.awt.Color(255, 255, 255));
        labelIconHistorico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/historico.png"))); // NOI18N
        labelIconHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIconHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconHistoricoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconHistoricoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIconHistoricoMouseExited(evt);
            }
        });

        labelHistorico.setBackground(new java.awt.Color(255, 255, 255));
        labelHistorico.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelHistorico.setForeground(new java.awt.Color(255, 255, 255));
        labelHistorico.setText("Histórico");
        labelHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHistoricoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelHistoricoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelHistoricoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelCertificadoLayout = new javax.swing.GroupLayout(panelCertificado);
        panelCertificado.setLayout(panelCertificadoLayout);
        panelCertificadoLayout.setHorizontalGroup(
            panelCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCertificadoLayout.createSequentialGroup()
                .addComponent(labelIconHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCertificadoLayout.setVerticalGroup(
            panelCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelIconHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        panelInicio.setBackground(new java.awt.Color(1, 155, 45));
        panelInicio.setPreferredSize(new java.awt.Dimension(196, 58));

        labelInicio.setBackground(new java.awt.Color(255, 255, 255));
        labelInicio.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelInicio.setForeground(new java.awt.Color(255, 255, 255));
        labelInicio.setText("Início");
        labelInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelInicioMouseExited(evt);
            }
        });

        labelIconInicio.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconInicio.setForeground(new java.awt.Color(255, 255, 255));
        labelIconInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/casa.png"))); // NOI18N
        labelIconInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIconInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIconInicioMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(labelIconInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
            .addComponent(labelIconInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
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
                    .addComponent(panelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                        .addGroup(panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(0, 0, 0)
                .addComponent(panelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textPesquisar.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelPesquisar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelPesquisar.setText("Pesquisar:");

        labelAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/adicionar.png"))); // NOI18N
        labelAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdicionarMouseClicked(evt);
            }
        });

        checkSituacao.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        checkSituacao.setText("Mostrar Inativas");
        checkSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSituacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTituloTabela)
                    .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkSituacao)
                                .addGap(465, 465, 465))
                            .addComponent(labelAdicionar))))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(labelTituloTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPesquisar)
                            .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkSituacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(343, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1409, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuMouseClicked
        
        
        if(menuAtivo)
        {
            menuAtivo = false;
            
            labelEventos.setVisible(true);
            labelHistorico.setVisible(true);
            labelPerfil.setVisible(true);
            
            panelMenuSuspenso.setPreferredSize(new Dimension(195, 649));
            panelMenuSuspenso.revalidate();
            panelMenuSuspenso.repaint(); 
            
           
        }
        else
        {
            menuAtivo = true;
            
            labelEventos.setVisible(false);
            labelHistorico.setVisible(false);
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

    private void labelIconHistoricoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconHistoricoMouseExited
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelIconHistoricoMouseExited

    private void labelHistoricoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHistoricoMouseExited
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelHistoricoMouseExited

    private void labelHistoricoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHistoricoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelHistoricoMouseEntered

    private void labelIconHistoricoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconHistoricoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelIconHistoricoMouseEntered

    private void labelAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdicionarMouseClicked
       
        TelaAdicionarSemana telaAdicionarSemana = new TelaAdicionarSemana((Frame) this.getParent(), true);
        telaAdicionarSemana.setVisible(true); 


        preencherTabela();
    }//GEN-LAST:event_labelAdicionarMouseClicked

    private void checkSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSituacaoActionPerformed
       
        pesquisar();
    }//GEN-LAST:event_checkSituacaoActionPerformed

    private void labelIconInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconInicioMouseEntered
       
        Color cor = new Color(1, 114, 18);
        panelInicio.setBackground(cor);
    }//GEN-LAST:event_labelIconInicioMouseEntered

    private void labelIconInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconInicioMouseExited
        
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
            
    }//GEN-LAST:event_labelIconInicioMouseExited

    private void labelInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInicioMouseEntered
        
        Color cor = new Color(1, 114, 18);
        panelInicio.setBackground(cor);
        
    }//GEN-LAST:event_labelInicioMouseEntered

    private void labelInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInicioMouseExited
        
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
        
    }//GEN-LAST:event_labelInicioMouseExited

    private void labelIconInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconInicioMouseClicked
       
        dispose();
        TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal((Frame) this.getParent(), true, participante, administrador);
        telaMenuPrincipal.setVisible(true);
        
        
    }//GEN-LAST:event_labelIconInicioMouseClicked

    private void labelInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInicioMouseClicked
       
        dispose();
        TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal((Frame) this.getParent(), true, participante, administrador);
        telaMenuPrincipal.setVisible(true);

        
    }//GEN-LAST:event_labelInicioMouseClicked

    private void labelHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHistoricoMouseClicked
       
        dispose();
        TelaHistoricoEventos telaHistoricoEventos = new TelaHistoricoEventos((Frame) this.getParent(), true, participante, administrador);
        telaHistoricoEventos.setVisible(true);
        dispose();
    }//GEN-LAST:event_labelHistoricoMouseClicked

    private void labelIconHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconHistoricoMouseClicked
       
        dispose();
        TelaHistoricoEventos telaHistoricoEventos = new TelaHistoricoEventos((Frame) this.getParent(), true, participante, administrador);
        telaHistoricoEventos.setVisible(true);

    }//GEN-LAST:event_labelIconHistoricoMouseClicked

    private void labelIconPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconPerfilMouseClicked
        
        if(participante == null)
        {
            TelaDadosAdministrador telaDadosAdministrador = new TelaDadosAdministrador((Frame) this.getParent(), true, administrador);
            telaDadosAdministrador.setVisible(true);
        }
        else
        {
            TelaDados telaDados = new TelaDados ((Frame) this.getParent(), true, participante);
            telaDados.setVisible(true);
        }
    }//GEN-LAST:event_labelIconPerfilMouseClicked

    private void labelPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPerfilMouseClicked
        
        if(participante == null)
        {
            TelaDadosAdministrador telaDadosAdministrador = new TelaDadosAdministrador((Frame) this.getParent(), true, administrador);
            telaDadosAdministrador.setVisible(true);
        }
        else
        {
            TelaDados telaDados = new TelaDados ((Frame) this.getParent(), true, participante);
            telaDados.setVisible(true);
        }
    }//GEN-LAST:event_labelPerfilMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkSituacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAdicionar;
    private javax.swing.JLabel labelBemVindo;
    private javax.swing.JLabel labelEventos;
    private javax.swing.JLabel labelHistorico;
    private javax.swing.JLabel labelIconEventos;
    private javax.swing.JLabel labelIconHistorico;
    private javax.swing.JLabel labelIconInicio;
    private javax.swing.JLabel labelIconPerfil;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JLabel labelMenu;
    private javax.swing.JLabel labelPerfil;
    private javax.swing.JLabel labelPesquisar;
    private javax.swing.JLabel labelSair;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JPanel panelCertificado;
    private javax.swing.JPanel panelEventos;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelMenuSuspenso;
    private javax.swing.JPanel panelPerfil;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JSeparator separadorAtividades1;
    private javax.swing.JTable tabelaSemana;
    private javax.swing.JTextField textPesquisar;
    // End of variables declaration//GEN-END:variables
}
