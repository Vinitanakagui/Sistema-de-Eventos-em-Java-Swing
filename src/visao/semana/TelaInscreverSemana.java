package visao.semana;

import controle.CursoDAO;
import controle.EventoDAO;
import controle.MatriculaDAO;
import controle.ParticipanteDAO;
import controle.SemanaDAO;
import static java.awt.Color.RED;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import modelo.Matricula;
import modelo.Organizador;
import modelo.Participante;
import modelo.Semana;
import modelo.enuns.Ativo;
import utils.DateUtils;
import utils.JOptionPaneUtils;
import visao.evento.TelaInscreverEvento;


public class TelaInscreverSemana extends javax.swing.JDialog 
{

    private Semana semana;
    private SemanaDAO semanaDAO;
    private List<Evento> listaEventos;
    private List<Curso> listaCurso;
    private CursoDAO cursoDAO;
    private List<Organizador> listaOrganizador;
    private Evento eventoSelecionado;
    private Participante participante;
    private ParticipanteDAO participanteDAO;
    private List<Matricula> listaMatricula;
    private MatriculaDAO matriculaDAO;
    
    public TelaInscreverSemana(java.awt.Frame parent, boolean modal, Semana semana, Participante participante) 
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
        this.participante = participante;
        this.participanteDAO = new ParticipanteDAO();
        this.listaMatricula = new ArrayList<>();
        this.matriculaDAO = new MatriculaDAO();
        
        carregarInformacoesSemana();
        
       
         tabelaEvento.addMouseListener(new MouseAdapter() 
         {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int linha = tabelaEvento.rowAtPoint(e.getPoint());
                
                if (linha != -1) 
                { 
                   if (e.getClickCount() >= 2) 
                    {
                        visualizarEvento(linha);
                    }
                }
            }
        });
              
        
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
        tabelaEventosInscrito.getColumnModel().getColumn(1).setCellRenderer(centro);
         
    }
        

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        separadorAtividades = new javax.swing.JSeparator();
        labelTituloTabela = new javax.swing.JLabel();
        labelVoltar = new javax.swing.JLabel();
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
        panelEvento = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEvento = new javax.swing.JTable();
        panelResponsavel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaEventosInscrito = new javax.swing.JTable();
        labelExcluirResponsavel = new javax.swing.JLabel();
        labelTotalHoras = new javax.swing.JLabel();

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

        panelInformacoes.setBackground(new java.awt.Color(255, 255, 255));
        panelInformacoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        labelNome.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome.setText("Nome: ");

        textNome.setEditable(false);
        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelNome2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome2.setText("Início:");

        textDataInicio.setDateFormatString("d'/'M'/'y");
        textDataInicio.setEnabled(false);
        textDataInicio.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        textDataInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textDataInicioMouseClicked(evt);
            }
        });

        labelNome4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome4.setText("Observação:");

        textObservacao.setEditable(false);
        textObservacao.setColumns(20);
        textObservacao.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        textObservacao.setLineWrap(true);
        textObservacao.setRows(5);
        textObservacao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textObservacao);

        labelNome1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome1.setText("Fim:");

        textDataFim.setDateFormatString("d'/'M'/'y");
        textDataFim.setEnabled(false);
        textDataFim.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        textDataFim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textDataFimMouseClicked(evt);
            }
        });

        checkAtivo.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        checkAtivo.setText("Ativo?");
        checkAtivo.setEnabled(false);

        labelNome5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome5.setText("Local:");

        textLocal.setEditable(false);
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

        javax.swing.GroupLayout panelOrganizadorLayout = new javax.swing.GroupLayout(panelOrganizador);
        panelOrganizador.setLayout(panelOrganizadorLayout);
        panelOrganizadorLayout.setHorizontalGroup(
            panelOrganizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrganizadorLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panelOrganizadorLayout.setVerticalGroup(
            panelOrganizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrganizadorLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout panelEventoLayout = new javax.swing.GroupLayout(panelEvento);
        panelEvento.setLayout(panelEventoLayout);
        panelEventoLayout.setHorizontalGroup(
            panelEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEventoLayout.setVerticalGroup(
            panelEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelResponsavel.setBackground(new java.awt.Color(255, 255, 255));
        panelResponsavel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eventos Inscritos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaEventosInscrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Carga Horária"
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
        jScrollPane4.setViewportView(tabelaEventosInscrito);
        if (tabelaEventosInscrito.getColumnModel().getColumnCount() > 0) {
            tabelaEventosInscrito.getColumnModel().getColumn(1).setMinWidth(130);
            tabelaEventosInscrito.getColumnModel().getColumn(1).setMaxWidth(130);
        }

        labelExcluirResponsavel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirResponsavel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirResponsavel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirResponsavelMouseClicked(evt);
            }
        });

        labelTotalHoras.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTotalHoras.setText("Total Horas: ");

        javax.swing.GroupLayout panelResponsavelLayout = new javax.swing.GroupLayout(panelResponsavel);
        panelResponsavel.setLayout(panelResponsavelLayout);
        panelResponsavelLayout.setHorizontalGroup(
            panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResponsavelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalHoras)
                    .addGroup(panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelExcluirResponsavel)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelResponsavelLayout.setVerticalGroup(
            panelResponsavelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResponsavelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelExcluirResponsavel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTotalHoras)
                .addGap(15, 15, 15))
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
                            .addComponent(panelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
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
                .addGap(20, 20, 20))
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
    
    private int buscarQuantidadeVagas(Evento evento)
    {
        return Math.abs(evento.getNumeroVagas() - evento.getQntdeIncrito());
    }
    
    private void atualizarTabelaEventos()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEvento.getModel();
        modelo.setRowCount(0);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        semana = semanaDAO.buscarSemana(semana.getNome(), semana.getInicio(), semana.getFim());
        
        for (Evento evento : semana.getListaEventos()) 
        {
            modelo.addRow(new String[]
            {
               evento.getTitulo(),
               String.valueOf(evento.getCargaHoraria()),
               String.valueOf(buscarQuantidadeVagas(evento)),
               evento.getData().format(formato)
            
            });
        }
    }
    
    private void visualizarEvento(Integer linha)
    {
        EventoDAO eventoDAO = new EventoDAO();
        Evento evento = eventoDAO.buscarEventoPorSemana((String) tabelaEvento.getValueAt(linha, 0), semana.getIdSemana());
        
        TelaInscreverEvento telaInscreverEvento = new TelaInscreverEvento((Frame) this.getParent(), true, evento, participante);
        telaInscreverEvento.setVisible(true);
        
        if(telaInscreverEvento.getMatricula() != null)
        {
            participante.getListaMatricula().add(telaInscreverEvento.getMatricula());
            participanteDAO.alterar(participante);
            preencherTabelaEventosInscritos();
            atualizarTabelaEventos();
            return;
        }
         
        participante = participanteDAO.buscarParticipante(participante.getProntuario());
      
        preencherTabelaEventosInscritos();
        atualizarTabelaEventos();
        
    }
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
        
        preencherTabelaEventosInscritos();
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
    
    private int totalHoras()
    {
       int total = 0;
       
       if(participante.getListaMatricula() == null)
       {
           return 0;
       }
       else
       {
           for (Matricula matricula : listaMatricula) 
           {
               total += matricula.getEvento().getCargaHoraria();
           }
           
           return total;
       }
       
    }
    private void preencherTabelaEventosInscritos()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEventosInscrito.getModel();
        modelo.setRowCount(0);
       
        listaMatricula = matriculaDAO.buscarMatriculasEvento(participante.getProntuario(), semana.getIdSemana());
        
        
        for (Matricula matricula : listaMatricula) 
        {
            modelo.addRow(new String []
            {
               matricula.getEvento().getTitulo(),
               String.valueOf(matricula.getEvento().getCargaHoraria())
            
            });
        }
        
        labelTotalHoras.setText("Total Horas: " + totalHoras());
    }
    
    
    private boolean verificarTabelaOrganizador()
    {
        return tabelaOrganizador.getRowCount() == -1;
    }
    
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

    private void excluirEventosLista(List<Evento> listaAux)
    {
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        EventoDAO eventoDAO = new EventoDAO();
        
        for (int i = 0; i < participante.getListaMatricula().size(); i++) 
        {
            for (Evento evento : listaAux)
            {
                if(participante.getListaMatricula().get(i).getEvento().equals(evento))
                {
                    Matricula matricula = matriculaDAO.buscarPorEvento(participante.getListaMatricula().get(i).getParticipante().getNome(), participante.getListaMatricula().get(i).getEvento().getIdEvento());
                    matriculaDAO.excluir(matricula);
                    Evento eventoAux = eventoDAO.buscarEventoPorSemana(evento.getTitulo(), semana.getIdSemana());
                    
                    if(eventoAux!=null)
                    {                    
                        eventoAux.setQntdeIncrito(eventoAux.getQntdeIncrito()-1);
                        eventoDAO.alterar(eventoAux);
                    }
                       
                    participante.getListaMatricula().remove(i);
                }
            }
        }
    }
    private void labelExcluirResponsavelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirResponsavelMouseClicked
        
    int[] linhas = tabelaEventosInscrito.getSelectedRows();
    DefaultTableModel modelo = (DefaultTableModel) tabelaEventosInscrito.getModel();
    List<Evento> listaEventosAux = new ArrayList<>();
    EventoDAO eventoDAO = new EventoDAO();

    if (linhas != null && linhas.length > 0)
    {         
        int resposta = JOptionPaneUtils.confirmacao("Confirma a desinscrição do evento?", "Atenção");
        
        if(resposta == JOptionPane.YES_OPTION)
        {
            for (int i = 0; i < linhas.length; i++)
            {
                Evento evento = eventoDAO.buscarEventoPorSemana((String) tabelaEventosInscrito.getValueAt(linhas[i], 0), semana.getIdSemana());
                listaEventosAux.add(evento);
            }
        
            excluirEventosLista(listaEventosAux);

            participanteDAO.alterar(participante);

            participante = participanteDAO.buscarParticipante(participante.getProntuario());

            for (int i = (linhas.length - 1); i >= 0; i--) 
            {
                modelo.removeRow(linhas[i]);
            }

            preencherTabelaEventosInscritos();
            atualizarTabelaEventos();
        }
        else
        {
            JOptionPaneUtils.sucesso("Operação cancelada!", "Cancelamento");
        }
    } 
    else 
    {
        JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
    }
            
    }//GEN-LAST:event_labelExcluirResponsavelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAtivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelExcluirResponsavel;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JLabel labelNome4;
    private javax.swing.JLabel labelNome5;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelTotalHoras;
    private javax.swing.JLabel labelVoltar;
    private javax.swing.JPanel panelEvento;
    private javax.swing.JPanel panelInformacoes;
    private javax.swing.JPanel panelOrganizador;
    private javax.swing.JPanel panelResponsavel;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JTable tabelaEvento;
    private javax.swing.JTable tabelaEventosInscrito;
    private javax.swing.JTable tabelaOrganizador;
    private com.toedter.calendar.JDateChooser textDataFim;
    private com.toedter.calendar.JDateChooser textDataInicio;
    private javax.swing.JTextField textLocal;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextArea textObservacao;
    // End of variables declaration//GEN-END:variables
}
