package visao.semana;

import visao.organizador.TelaAdicionarOrganizador;
import controle.CursoDAO;
import controle.EventoDAO;
import controle.MatriculaDAO;
import controle.OrganizadorDAO;
import controle.SemanaDAO;
import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Evento;
import modelo.Organizador;
import modelo.Responsavel;
import modelo.Semana;
import modelo.enuns.Ativo;
import utils.DateUtils;
import utils.JOptionPaneUtils;
import visao.evento.TelaAdicionarEvento;
import visao.evento.TelaVisualizarInscritos;


public class TelaEditarSemana extends javax.swing.JDialog 
{

    private Semana semana;
    private SemanaDAO semanaDAO;
    private List<Evento> listaEventos;
    private List<Curso> listaCurso;
    private CursoDAO cursoDAO;
    private List<Organizador> listaOrganizador;
    private Evento eventoSelecionado;
    
    public TelaEditarSemana(java.awt.Frame parent, boolean modal, Semana semana) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.semana = semana;
        this.semanaDAO = new SemanaDAO();
        this.listaEventos = new ArrayList<>();
        this.listaOrganizador = new ArrayList<>();
        this.listaOrganizador = semana.getListaOrganizadores();
        this.listaEventos = semana.getListaEventos();
        
        carregarInformacoesSemana();
        
       
         tabelaEvento.addMouseListener(new MouseAdapter() 
         {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int row = tabelaEvento.rowAtPoint(e.getPoint());
                
                if (row != -1) 
                { 
                    if (e.getClickCount() == 1) 
                    {
                       preencherTabelaResponsavel();
                        
                    } 
                    else if (e.getClickCount() >= 2) 
                    {
                        editarEvento();
                        preencherTabelaEvento();
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

        tabelaEvento.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        tabelaEvento.getColumnModel().getColumn(1).setCellRenderer(centro);
        tabelaEvento.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabelaEvento.getColumnModel().getColumn(3).setCellRenderer(centro);           
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        separadorAtividades = new javax.swing.JSeparator();
        labelTituloTabela = new javax.swing.JLabel();
        labelVoltar = new javax.swing.JLabel();
        botaoEditar = new javax.swing.JButton();
        panelInformacoes = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        labelNome2 = new javax.swing.JLabel();
        textDataInicio = new com.toedter.calendar.JDateChooser();
        labelNome4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textObservacao = new javax.swing.JTextArea();
        labelNome1 = new javax.swing.JLabel();
        textDataFim = new com.toedter.calendar.JDateChooser();
        checkAtivo = new javax.swing.JCheckBox();
        labelNome5 = new javax.swing.JLabel();
        textLocal = new javax.swing.JTextField();
        panelOrganizador = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaOrganizador = new javax.swing.JTable();
        labelAdicionarOrganizador = new javax.swing.JLabel();
        labelExcluirOrganizador = new javax.swing.JLabel();
        panelEvento = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEvento = new javax.swing.JTable();
        labelAdicionarEvento = new javax.swing.JLabel();
        labelExcluirEvento = new javax.swing.JLabel();
        botaoInscritos = new javax.swing.JButton();
        panelResponsavel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaResponsavel = new javax.swing.JTable();
        labelExcluirResponsavel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        labelTituloTabela.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela.setText("Cadastrar Semana");

        labelVoltar.setBackground(new java.awt.Color(255, 255, 255));
        labelVoltar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/voltar.png"))); // NOI18N
        labelVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelVoltarMouseClicked(evt);
            }
        });

        botaoEditar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        panelInformacoes.setBackground(new java.awt.Color(255, 255, 255));
        panelInformacoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        labelNome.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome.setText("Nome: ");

        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelNome2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome2.setText("Início:");

        textDataInicio.setDateFormatString("d'/'M'/'y");
        textDataInicio.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        textDataInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textDataInicioMouseClicked(evt);
            }
        });

        labelNome4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome4.setText("Observação:");

        textObservacao.setColumns(20);
        textObservacao.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        textObservacao.setLineWrap(true);
        textObservacao.setRows(5);
        textObservacao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textObservacao);

        labelNome1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome1.setText("Fim:");

        textDataFim.setDateFormatString("d'/'M'/'y");
        textDataFim.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        textDataFim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textDataFimMouseClicked(evt);
            }
        });

        checkAtivo.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        checkAtivo.setText("Ativo?");
        checkAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAtivoActionPerformed(evt);
            }
        });

        labelNome5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome5.setText("Local:");

        textLocal.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelInformacoesLayout = new javax.swing.GroupLayout(panelInformacoes);
        panelInformacoes.setLayout(panelInformacoesLayout);
        panelInformacoesLayout.setHorizontalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacoesLayout.createSequentialGroup()
                        .addComponent(labelNome4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformacoesLayout.createSequentialGroup()
                        .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInformacoesLayout.createSequentialGroup()
                                .addComponent(labelNome2)
                                .addGap(18, 18, 18)
                                .addComponent(textDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelNome1)
                                .addGap(18, 18, 18)
                                .addComponent(textDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(checkAtivo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInformacoesLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(labelNome5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInformacoesLayout.setVerticalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNome5)
                    .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacoesLayout.createSequentialGroup()
                        .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInformacoesLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(labelNome2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelInformacoesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))
                    .addGroup(panelInformacoesLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkAtivo)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacoesLayout.createSequentialGroup()
                                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNome1)
                                    .addComponent(textDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNome4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        panelOrganizador.setBackground(new java.awt.Color(255, 255, 255));
        panelOrganizador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Organizadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaOrganizador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelaOrganizador);

        labelAdicionarOrganizador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/adicionar.png"))); // NOI18N
        labelAdicionarOrganizador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelAdicionarOrganizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdicionarOrganizadorMouseClicked(evt);
            }
        });

        labelExcluirOrganizador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirOrganizador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirOrganizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirOrganizadorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelOrganizadorLayout = new javax.swing.GroupLayout(panelOrganizador);
        panelOrganizador.setLayout(panelOrganizadorLayout);
        panelOrganizadorLayout.setHorizontalGroup(
            panelOrganizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrganizadorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelOrganizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelOrganizadorLayout.createSequentialGroup()
                        .addComponent(labelAdicionarOrganizador)
                        .addGap(18, 18, 18)
                        .addComponent(labelExcluirOrganizador))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelOrganizadorLayout.setVerticalGroup(
            panelOrganizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrganizadorLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelOrganizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelExcluirOrganizador)
                    .addComponent(labelAdicionarOrganizador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEvento.setBackground(new java.awt.Color(255, 255, 255));
        panelEvento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Eventos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaEvento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Carga Horária", "Vagas", "Data"
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
        jScrollPane1.setViewportView(tabelaEvento);
        if (tabelaEvento.getColumnModel().getColumnCount() > 0) {
            tabelaEvento.getColumnModel().getColumn(1).setMinWidth(130);
            tabelaEvento.getColumnModel().getColumn(1).setMaxWidth(130);
            tabelaEvento.getColumnModel().getColumn(2).setMinWidth(100);
            tabelaEvento.getColumnModel().getColumn(2).setMaxWidth(100);
            tabelaEvento.getColumnModel().getColumn(3).setMinWidth(100);
            tabelaEvento.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        labelAdicionarEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/adicionar.png"))); // NOI18N
        labelAdicionarEvento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelAdicionarEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdicionarEventoMouseClicked(evt);
            }
        });

        labelExcluirEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirEvento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirEventoMouseClicked(evt);
            }
        });

        botaoInscritos.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoInscritos.setText("Ver Inscritos");
        botaoInscritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInscritosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEventoLayout = new javax.swing.GroupLayout(panelEvento);
        panelEvento.setLayout(panelEventoLayout);
        panelEventoLayout.setHorizontalGroup(
            panelEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoInscritos)
                    .addGroup(panelEventoLayout.createSequentialGroup()
                        .addComponent(labelAdicionarEvento)
                        .addGap(18, 18, 18)
                        .addComponent(labelExcluirEvento))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEventoLayout.setVerticalGroup(
            panelEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelExcluirEvento)
                    .addComponent(labelAdicionarEvento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoInscritos)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelResponsavel.setBackground(new java.awt.Color(255, 255, 255));
        panelResponsavel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responsáveis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaResponsavel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabelaResponsavel);

        labelExcluirResponsavel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirResponsavel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirResponsavel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirResponsavelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelResponsavelLayout = new javax.swing.GroupLayout(panelResponsavel);
        panelResponsavel.setLayout(panelResponsavelLayout);
        panelResponsavelLayout.setHorizontalGroup(
            panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResponsavelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelExcluirResponsavel)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelResponsavelLayout.setVerticalGroup(
            panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResponsavelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelExcluirResponsavel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTituloTabela)
                            .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelOrganizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoEditar))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTituloTabela)
                    .addComponent(labelVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOrganizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(botaoEditar)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void carregarInformacoesSemana()
    {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DefaultTableModel modeloOrganizador = (DefaultTableModel) tabelaOrganizador.getModel();
        DefaultTableModel modeloEvento = (DefaultTableModel) tabelaEvento.getModel();
        
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        
        textNome.setText(semana.getNome());
        textLocal.setText(semana.getLocal());
        textDataFim.setDate(DateUtils.asDate(semana.getFim()));
        textDataInicio.setDate(DateUtils.asDate(semana.getInicio()));
        textObservacao.setText(semana.getObservacao());
        
        if(semana.getAtiva() == Ativo.S)
        {
            checkAtivo.setSelected(true);
        }
        
        
        for (Evento evento : semana.getListaEventos()) 
        {
            
            modeloEvento.addRow(new String[]
            {
                evento.getTitulo(),
                String.valueOf(evento.getCargaHoraria()),
                String.valueOf(evento.getNumeroVagas()),
                evento.getData().format(formato)
           
            });
        }
                
        for (Organizador organizador : semana.getListaOrganizadores()) 
        {
            modeloOrganizador.addRow(new String[]
            {
              organizador.getNome(),
              organizador.getCurso().getNome()
                    
            });
        }
    }
    
    private boolean verificarDataEventos() throws ParseException
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        try
        {
            for (int i = 0; i < tabelaEvento.getRowCount(); i++) 
            {
                Date data = formato.parse( ((String) tabelaEvento.getValueAt(i, 3)));
                
                if(data.after(textDataInicio.getDate()) && data.before(textDataFim.getDate()))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
        }
        catch(ParseException e)
        {
            System.out.println("Erro na conversão dos tipos (String --> Date)"); 
        }
        
        return true;
        
        
    }
    
    private void labelVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVoltarMouseClicked

        dispose();

    }//GEN-LAST:event_labelVoltarMouseClicked

    private void editarEvento()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaResponsavel.getModel();
        modelo.setRowCount(0);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int linha = tabelaEvento.getSelectedRow();
        
        TelaAdicionarEvento telaAdicionarEvento = new TelaAdicionarEvento((Frame) this.getParent(), true, semana.getListaEventos().get(linha));
        telaAdicionarEvento.setVisible(true);
        
        if(telaAdicionarEvento.getEvento() != null)
        {
             semana.getListaEventos().set(linha, telaAdicionarEvento.getEvento());

            for (Responsavel responsavel : telaAdicionarEvento.getEvento().getListaResponsavel())
             {
                 modelo.addRow(new String[]
                 {
                    responsavel.getParticipante().getNome(),
                    responsavel.getParticipante().getCurso().getNome()

                 });
             }

         preencherTabelaEvento();
        } 
    }
    
    private void preencherTabelaEvento()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEvento.getModel();
        modelo.setRowCount(0);
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (Evento evento : listaEventos) 
        {
            modelo.addRow(new String[]
            {
                evento.getTitulo(),
                String.valueOf(evento.getCargaHoraria()),
                String.valueOf(evento.getNumeroVagas()),
                evento.getData().format(formato)

            });
        }
           
    }
    
    private void preencherTabelaResponsavel()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaResponsavel.getModel();
        modelo.setRowCount(0);
        
        int linha = tabelaEvento.getSelectedRow();
        List<Responsavel> listaAux = new ArrayList<>();
        
        if(linha > -1)
        {           
            listaAux = semana.getListaEventos().get(linha).getListaResponsavel();
            eventoSelecionado = semana.getListaEventos().get(linha);   

            for (Responsavel responsavel : listaAux)
            {
                modelo.addRow(new String[]
                {
                   responsavel.getParticipante().getNome(),
                   responsavel.getParticipante().getCurso().getNome()

                });
            }         
       }        
        
    }
    
    
    private boolean verificarTabelaOrganizador()
    {
        return tabelaOrganizador.getRowCount() == -1;
    }
    
    private boolean verificarDataAtivo() 
    {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataInicio = DateUtils.asLocalDate(textDataInicio.getDate());
        LocalDate dataFim = DateUtils.asLocalDate(textDataFim.getDate());

        return (dataAtual.isEqual(dataInicio) || dataAtual.isAfter(dataInicio)) && (dataAtual.isEqual(dataFim) || dataAtual.isBefore(dataFim));
    }

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
       
        EventoDAO eventoDAO = new EventoDAO();
        OrganizadorDAO organizadorDAO = new OrganizadorDAO();
        int i = 0;

        try
        {
            boolean resultado = verificarDataEventos();
        
            if(textDataInicio.getDate().after(textDataFim.getDate()))
            {
                JOptionPaneUtils.atencao("Data final é anterior a data inicial", "Atenção");
                textDataFim.setBorder(new LineBorder(Color.RED,2));
                textDataInicio.setBorder(new LineBorder(Color.RED,2));
            }
            else
            {
                if(!verificarTabelaOrganizador())
                {
                   if(checkAtivo.isSelected())
                   {
                       semana.setAtiva(Ativo.S);
                   }
                   else
                   {
                       semana.setAtiva(Ativo.N);
                   }

                   semana.setFim(DateUtils.asLocalDate(textDataFim.getDate()));
                   semana.setInicio(DateUtils.asLocalDate(textDataInicio.getDate()));
                   semana.setNome(textNome.getText());
                   semana.setLocal(textLocal.getText());
                   semana.setObservacao(textObservacao.getText());

                   for (Evento evento : listaEventos)
                   {
                       evento.setIdEvento(i+1);
                       evento.setSemana(semana);
                       i++;
                   }

                    
                    for (Organizador organizador : semana.getListaOrganizadores()) 
                    {
                        organizador.setSemana(semana);
                    }
                    
                    semana.setListaEventos(listaEventos);
                    semana.setListaOrganizadores(listaOrganizador);
                    
                    semanaDAO.alterar(semana);
                    
                    JOptionPaneUtils.sucesso("Dados alterados com sucesso!", "Dados Alterados");

                }
                else
                {
                   JOptionPaneUtils.atencao("Selecione ao menos um organizador!", "Atenção");
                }
            }
        }
        catch (ParseException e) 
        {
            System.out.println("Erro ao verificar as datas: " + e.getMessage());
        }
        
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void preencherTabelaOrganizador()
    {
        
        DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizador.getModel();
        modelo.setRowCount(0);
        
        
        for (Organizador organizador : semana.getListaOrganizadores()) 
        {
            modelo.addRow(new String[]
            {
               organizador.getNome(),
               organizador.getCurso().getNome()

            });
        }      
        
    }
    private void labelAdicionarOrganizadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdicionarOrganizadorMouseClicked
     
    TelaAdicionarOrganizador telaAdicionarOrganizador = new TelaAdicionarOrganizador((Frame) this.getParent(), true, semana.getListaOrganizadores());
    telaAdicionarOrganizador.setVisible(true);

    if(!telaAdicionarOrganizador.isCancelar())
    {
        semana.setListaOrganizadores(telaAdicionarOrganizador.getListaOrganizador());
    }

    preencherTabelaOrganizador(); 
               
       
    }//GEN-LAST:event_labelAdicionarOrganizadorMouseClicked

    private void labelExcluirOrganizadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirOrganizadorMouseClicked
       
        DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizador.getModel();
        int linhas[] = tabelaOrganizador.getSelectedRows();   
        OrganizadorDAO organizadorDAO = new OrganizadorDAO();
        
        if(linhas.length == 0)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            Integer resposta = JOptionPaneUtils.confirmacao("Tem certeza da exclusão?", "Atenção");

            if(resposta == JOptionPane.YES_OPTION)
            {
                
                int acumulador = 0;
            
                for (int linha : linhas) 
                {
                    Organizador organizador = new Organizador();
                    
                    organizador = organizadorDAO.buscarOrganizadorPorSemana(listaOrganizador.get(linha - acumulador).getNome(), semana.getIdSemana());
                    
                    if(organizador != null)
                    {
                        organizadorDAO.excluir(organizador);
                    }
                    
                    listaOrganizador.remove(linha - acumulador);
                    acumulador++;
                }

                semana.setListaOrganizadores(listaOrganizador);
                 
                preencherTabelaOrganizador();          
                
            }
                  
        }

    }//GEN-LAST:event_labelExcluirOrganizadorMouseClicked


    private void labelAdicionarEventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdicionarEventoMouseClicked
        
       TelaAdicionarEvento telaAdicionarEvento = new TelaAdicionarEvento((Frame) this.getParent(), true, null);
       telaAdicionarEvento.setVisible(true);
       
       if(telaAdicionarEvento.getEvento() != null)
       {
           listaEventos.add(telaAdicionarEvento.getEvento());
       }
       
        preencherTabelaEvento();
        
        
    }//GEN-LAST:event_labelAdicionarEventoMouseClicked

    private void labelExcluirEventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirEventoMouseClicked
        
        DefaultTableModel modelo = (DefaultTableModel) tabelaEvento.getModel();
        EventoDAO eventoDAO = new EventoDAO();
        int linhas[] = tabelaEvento.getSelectedRows();   
      
        
        if(linhas == null)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            Integer resposta = JOptionPaneUtils.confirmacao("Tem certeza da exclusão?", "Atenção");
            
            if(resposta == JOptionPane.YES_OPTION)
            {
               int acumulador = 0;
            
                for (int linha : linhas) 
                {
                    Evento evento = new Evento();
                    
                    evento = eventoDAO.buscarEventoPorSemana(listaEventos.get(linha - acumulador).getTitulo(), semana.getIdSemana());
                    
                    if(evento != null)
                    {
                        eventoDAO.excluir(evento);
                    }
                    
                    listaEventos.remove(linha - acumulador);
                    acumulador++;
                }
                
               for(int i = linhas.length - 1; i >= 0; i--)
               {
                   modelo.removeRow(linhas[i]);
               }
            }
        }
        
        DefaultTableModel modeloResponsavel = (DefaultTableModel) tabelaResponsavel.getModel();
        modeloResponsavel.setRowCount(0);                  
        
    }//GEN-LAST:event_labelExcluirEventoMouseClicked

    private void textDataInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textDataInicioMouseClicked
        
         LineBorder borda = (LineBorder) textDataInicio.getBorder();
        
        if(borda.getLineColor().equals(RED))
        {
            textDataInicio.setBorder(BorderFactory.createEmptyBorder());
        }
    }//GEN-LAST:event_textDataInicioMouseClicked

    private void textDataFimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textDataFimMouseClicked
       
         LineBorder borda = (LineBorder) textDataFim.getBorder();
        
        if(borda.getLineColor().equals(RED))
        {
            textDataFim.setBorder(BorderFactory.createEmptyBorder());
        }
    }//GEN-LAST:event_textDataFimMouseClicked

    private void labelExcluirResponsavelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirResponsavelMouseClicked
        
    int[] linhas = tabelaResponsavel.getSelectedRows();
    DefaultTableModel modelo = (DefaultTableModel) tabelaResponsavel.getModel();

    if (linhas != null && linhas.length > 0)
    {         
        for(int linha : linhas)
        { 
            for (int i = 0; i < eventoSelecionado.getListaResponsavel().size(); i++) 
            {
                if (eventoSelecionado.getListaResponsavel().get(i).getParticipante().getNome().trim().equals(tabelaResponsavel.getValueAt(linha, 0).toString().trim()))
                {
                    eventoSelecionado.getListaResponsavel().remove(i);
                    
                    break;
                }
 
            }

        }

        for (int i = linhas.length - 1; i >= 0; i--) 
        {
            modelo.removeRow(linhas[i]);
        }
    } 
    else 
    {
        JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
    }
            
    }//GEN-LAST:event_labelExcluirResponsavelMouseClicked

    private void botaoInscritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInscritosActionPerformed
       
        int linha = tabelaEvento.getSelectedRow();
        
        if(linha == -1)
        {
            JOptionPaneUtils.atencao("Selecione uma linha!", "Atenção");
        }
        else
        {
            EventoDAO eventoDAO = new EventoDAO();
            Evento evento = eventoDAO.buscarEventoPorSemana(((String) tabelaEvento.getValueAt(linha, 0)), semana.getIdSemana());
            
            TelaVisualizarInscritos telaVisualizarInscritos = new TelaVisualizarInscritos((Frame) this.getParent(), true, evento);
            telaVisualizarInscritos.setVisible(true);
        }
        
    }//GEN-LAST:event_botaoInscritosActionPerformed

    private void checkAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAtivoActionPerformed
        
        if(!checkAtivo.isSelected())
        {
            if(verificarDataAtivo())
            {
                int resposta = JOptionPaneUtils.confirmacao("Deseja desativas uma semana em andamento?", "Atenção");
                
                if(resposta == JOptionPane.YES_OPTION)
                {
                    checkAtivo.setSelected(false);
                }
                else
                {
                    checkAtivo.setSelected(true);
                }
               
            }
        }
    }//GEN-LAST:event_checkAtivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoInscritos;
    private javax.swing.JCheckBox checkAtivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAdicionarEvento;
    private javax.swing.JLabel labelAdicionarOrganizador;
    private javax.swing.JLabel labelExcluirEvento;
    private javax.swing.JLabel labelExcluirOrganizador;
    private javax.swing.JLabel labelExcluirResponsavel;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JLabel labelNome4;
    private javax.swing.JLabel labelNome5;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelVoltar;
    private javax.swing.JPanel panelEvento;
    private javax.swing.JPanel panelInformacoes;
    private javax.swing.JPanel panelOrganizador;
    private javax.swing.JPanel panelResponsavel;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JTable tabelaEvento;
    private javax.swing.JTable tabelaOrganizador;
    private javax.swing.JTable tabelaResponsavel;
    private com.toedter.calendar.JDateChooser textDataFim;
    private com.toedter.calendar.JDateChooser textDataInicio;
    private javax.swing.JTextField textLocal;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextArea textObservacao;
    // End of variables declaration//GEN-END:variables
}
