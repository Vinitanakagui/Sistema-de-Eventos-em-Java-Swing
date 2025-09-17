package visao.home;

import visao.dados.TelaDadosAdministrador;
import visao.dados.TelaDados;
import visao.evento.TelaHistoricoEventos;
import controle.EventoDAO;
import controle.MatriculaDAO;
import controle.ParticipanteDAO;
import controle.ResponsavelDAO;
import controle.SemanaDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.Administrador;
import modelo.Evento;
import modelo.Matricula;
import modelo.Organizador;
import modelo.Participante;
import modelo.Presenca;
import modelo.Responsavel;
import modelo.Semana;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import utils.JOptionPaneUtils;
import visao.evento.TelaInscreverEvento;
import visao.semana.TelaEditarSemana;
import visao.semana.TelaSemana;

public class TelaMenuPrincipal extends javax.swing.JDialog {

   private Boolean menuAtivo;
    private Administrador administrador;
    private Participante participante;
    private Boolean statusAdmin;
    private EventoDAO eventoDAO;
    private MatriculaDAO matriculaDAO;
    private List<Matricula> listaMatriculaEvento;
    private List<Responsavel> listaResponsavel;
    
    public TelaMenuPrincipal(java.awt.Frame parent, boolean modal, Participante participante, Administrador administrador) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.menuAtivo = false;
        this.administrador = administrador;
        this.participante = participante;
        this.statusAdmin = false;
        this.eventoDAO = new EventoDAO();
        this.matriculaDAO = new MatriculaDAO();
        this.listaMatriculaEvento = new ArrayList<>();
        this.listaResponsavel = new ArrayList<>();

        realizarLogin();
        menuInicializar();
        ajustarTabelasPorPerfil();
        preencherTabela();
        
        tabelaEventos.addMouseListener(new MouseAdapter() 
         {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int linha = tabelaEventos.rowAtPoint(e.getPoint());
                
                if (linha != -1) 
                { 
                    if (e.getClickCount() == 1) 
                    {
                       preencherPanelSemana(linha);
                        
                    } 
                    else if (e.getClickCount() >= 2) 
                    {
                        visualizarEvento(linha);
                    }
                }
            }
        });
        
        tabelaCertificado.addMouseListener(new MouseAdapter() 
         {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int linha = tabelaCertificado.rowAtPoint(e.getPoint());
                
                if (linha != -1) 
                { 
                     if (e.getClickCount() >= 2) 
                    {
                       abrirCertificado(linha);
                    }
                }
            }
        });
        
        panelInfoSemana.setVisible(false);
        preencherTabelaCertificadoEventoParticipado();
        preencherTabelaCertificadoEventoMinistrado();
        
        if(participante != null)
        {
             ajustarLabelTotalHoras();
        }
       
        
        Color cor = new Color(1, 114, 18);
        this.panelInicio.setBackground(cor);
        
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
        
        tabelaEventos.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        tabelaEventos.getColumnModel().getColumn(1).setCellRenderer(centro);
        tabelaEventos.getColumnModel().getColumn(2).setCellRenderer(centro);

        TableColumn coluna1 = tabelaEventos.getColumnModel().getColumn(1);
        TableColumn coluna2 = tabelaEventos.getColumnModel().getColumn(2);

        coluna1.setMinWidth(100); 
        coluna1.setMaxWidth(100);

        coluna2.setMinWidth(100); 
        coluna2.setMaxWidth(100);
        
        tabelaCertificado.getColumnModel().getColumn(1).setCellRenderer(centro);
        
    }
    
    private void ajustarTabelasPorPerfil()
    {
        if(statusAdmin)
        {
            DefaultTableModel modelo = (DefaultTableModel) tabelaEventos.getModel();
            modelo.setColumnCount(0);
            modelo.setRowCount(0);
            
            modelo.addColumn("Nome");
            modelo.addColumn("Inicio");
            modelo.addColumn("Fim");
            
        }
        else
        {     
            DefaultTableModel modelo = (DefaultTableModel) tabelaEventos.getModel();
            modelo.setColumnCount(0);
            modelo.setRowCount(0);
            
            modelo.addColumn("Nome");
            modelo.addColumn("Total Horas");
            modelo.addColumn("Data");
                       
        }
    }
    
    private void ajustarLabelTotalHoras()
    {
        if(tabelaCertificado.getRowCount() != 0)
        {
            Integer totalHoras = 0;
        
            for (int i = 0; i < tabelaCertificado.getRowCount(); i++) 
            {
                totalHoras += Integer.valueOf((String) tabelaCertificado.getValueAt(i, 1));
            }

            labelTotalHoras.setText("Total Horas: " + totalHoras);
        }
        
    }
    private void visualizarEvento(int linha)
    {
        if(participante != null)
        {
            ParticipanteDAO participanteDAO = new ParticipanteDAO();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String nome = (String) tabelaEventos.getValueAt(linha, 0);
            LocalDate data = LocalDate.parse((String) tabelaEventos.getValueAt(linha, 2), formato);

            Evento evento = eventoDAO.buscarEventoPorData(nome, data);

            TelaInscreverEvento telaInscreverEvento = new TelaInscreverEvento((Frame) this.getParent(), true, evento, participante);
            telaInscreverEvento.setVisible(true);

            participante = participanteDAO.buscarParticipante(participante.getProntuario());
            preencherTabela();
        }
        else
        {
            SemanaDAO semanaDAO = new SemanaDAO();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate inicio = LocalDate.parse((String) tabelaEventos.getValueAt(linha, 1), formato);
            LocalDate fim = LocalDate.parse((String) tabelaEventos.getValueAt(linha, 2), formato);
            String nome = (String) tabelaEventos.getValueAt(linha, 0);
            
            Semana semana = semanaDAO.buscarSemana(nome, inicio, fim);
            TelaEditarSemana telaEditarSemana = new TelaEditarSemana((Frame) this.getParent(), true, semana);
            telaEditarSemana.setVisible(true);
        }
        
        
    }
    
    private void preencherTabelaCertificadoEventoMinistrado()
    {
        
        if(participante != null)
        {
            DefaultTableModel modelo = (DefaultTableModel) tabelaCertificado.getModel();
            ResponsavelDAO responsavelDAO = new ResponsavelDAO();
            
            listaResponsavel = responsavelDAO.buscarResponsavel(participante.getProntuario());
            
            for (Responsavel responsavel : listaResponsavel) 
            {   
                modelo.addRow(new String []
                {
                   responsavel.getEvento().getTitulo(),
                   String.valueOf(responsavel.getEvento().getCargaHoraria())
                        
                });
            }
            
        }
    }
    
    private void preencherTabelaCertificadoEventoParticipado()
    {
        if(participante != null)
        {
            DefaultTableModel modelo = (DefaultTableModel) tabelaCertificado.getModel();
            modelo.setRowCount(0);

            Integer quantidade = 0;

            for (Matricula matricula : participante.getListaMatricula()) 
            {
               
                for (Presenca presenca : matricula.getListaPresenca()) 
                {
                    quantidade += presenca.getQuantidadeHoras();

                    if(quantidade.equals(matricula.getEvento().getCargaHoraria()))
                    {                     
                        listaMatriculaEvento.add(matricula);
                        quantidade = 0;
                    }
                }
            }

            for (Matricula matricula : listaMatriculaEvento) 
            {
                modelo.addRow(new String[]
                {
                    matricula.getEvento().getTitulo(),
                    String.valueOf(matricula.getEvento().getCargaHoraria())

                });
            }
        }
        
    }
    
    private int verificarIndicelistaMatriculaEvento(String titulo)
    {
       int indice = 0;
       
        for (Matricula matricula : listaMatriculaEvento) 
        {
            if(matricula.getEvento().getTitulo().equals(titulo))
            {
                return indice;
            }
            
            indice++;
        }
        
        return -1;
        
    }
    
    private int verificarIndiceListaResponsavel(String titulo)
    {
        int indice = 0;
        
        for (Responsavel responsavel : listaResponsavel) 
        {
            if(responsavel.getEvento().getTitulo().equals(titulo))
            { 
                return indice;
            }
            
            indice++;
        }
        
        return -1;
    }
    
    private void abrirCertificado(int linha)
    {
        int indice = verificarIndicelistaMatriculaEvento((String) tabelaCertificado.getValueAt(linha, 0));
        
        if(indice == -1)
        {
            indice = verificarIndiceListaResponsavel((String) tabelaCertificado.getValueAt(linha, 0));
            
            if(indice != -1)
            {
                if(!listaMatriculaEvento.isEmpty())
                {
                    List<Responsavel> listaAux = new ArrayList<>();
                    listaAux.add(listaResponsavel.get(indice));
                    
                     try 
                     {
                        JasperReport relatorioCompilado
                                = JasperCompileManager.compileReport("src/relatorio/CertificadoEventoMinistrante.jrxml");

                        JasperPrint relatorioPreenchido = JasperFillManager.fillReport(relatorioCompilado, null, 
                                new JRBeanCollectionDataSource(listaAux));

                        JDialog tela = new JDialog(this, "Lista Presença", true);
                        tela.setSize(1200, 1000);
                        tela.setLocationRelativeTo(null);

                        JRViewer painelRelatorio = new JRViewer(relatorioPreenchido);

                        tela.getContentPane().add(painelRelatorio);

                        tela.setVisible(true);

                    } 
                    catch (JRException ex) 
                    {
                        java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "Erro ao gerar o relatório.");
                    } 
               }
            }
            else
            {
                JOptionPaneUtils.atencao("Evento ministrado não encontrado!", "Atenção");
            }
        }
        else
        {
           if(!listaMatriculaEvento.isEmpty())
            {
                try {
                    JasperReport relatorioCompilado = JasperCompileManager.compileReport("src/relatorio/CertificadoEventoParticipante.jrxml");
                
                    Matricula matricula = listaMatriculaEvento.get(indice); // Obtendo um único item da lista

                    List<Object> lista = Collections.singletonList(matricula);

                    JasperPrint relatorioPreenchido = JasperFillManager.fillReport(relatorioCompilado, null, new JRBeanCollectionDataSource(lista));

                    JDialog tela = new JDialog(this, "Certificado participante", true);
                    tela.setSize(1200, 1000);
                    tela.setLocationRelativeTo(null);

                    JRViewer painelRelatorio = new JRViewer(relatorioPreenchido);

                    tela.getContentPane().add(painelRelatorio);

                    tela.setVisible(true);

                } catch (JRException ex) {
                    java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao gerar o relatório.");
                }
            }
        
        }
       
    }
    
    private void preencherTabela()
    {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DefaultTableModel modelo = (DefaultTableModel) tabelaEventos.getModel();
        modelo.setRowCount(0);
        
        if(participante != null)
        {
          
            if(participante.getListaMatricula() != null)
            {
                for (Matricula matricula : participante.getListaMatricula()) 
                {
                    if(matricula.getListaPresenca().isEmpty())
                    {
                        modelo.addRow(new String[]
                        {
                            matricula.getEvento().getTitulo(),
                            String.valueOf(matricula.getEvento().getCargaHoraria()),
                            matricula.getEvento().getData().format(formato)

                        });
                    }
                }
            }
        }
        else
        {
            SemanaDAO semanaDAO = new SemanaDAO();
            
            for (Semana semana : semanaDAO.buscarSemanaAtiva()) 
                {
                           
                    modelo.addRow(new String[]
                    {
                        semana.getNome(),
                        semana.getInicio().format(formato),
                        semana.getInicio().format(formato)

                    });
                    
                }
        }
    }
    private void preencherPanelSemana(int linha)
    {
        if(participante != null)
        {
            panelInfoSemana.setVisible(true);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String nome = (String) tabelaEventos.getValueAt(linha, 0);
            LocalDate data = LocalDate.parse((String) tabelaEventos.getValueAt(linha, 2), formato);
        
            Evento evento = eventoDAO.buscarEventoPorData(nome, data);
            
            textNomeSemana.setText(evento.getSemana().getNome());
            textInicioSemana.setText(evento.getSemana().getInicio().format(formato));
            textFimSemana.setText(evento.getSemana().getFim().format(formato));
            textDescricaoSemana.setText(evento.getSemana().getObservacao());
            textLocalSemana.setText(evento.getSemana().getLocal());
            
            comboOrganizadorSemana.removeAll();
            
            for (Organizador organizador : evento.getSemana().getListaOrganizadores()) 
            {
                comboOrganizadorSemana.addItem(organizador.getNome());
            }
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
    
    private void realizarLogin()
    {
       
        if(administrador == null)
        {           
            String nome = participante.getNome().split(" ")[0];
  
            ajustarCampoBemVindo(nome);
            labelTituloTabela.setText("Minhas Incrições");
        }
        else
        {
            statusAdmin = true;
            ajustarCampoBemVindo(administrador.getLogin());
            labelTituloTabela.setText("Próximas Semanas");
        }
    }
    
    private void ajustarCampoBemVindo(String nome)
    {
        labelBemVindo.setText(nome);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelBemVindo = new javax.swing.JLabel();
        labelMenu = new javax.swing.JLabel();
        separadorAtividades1 = new javax.swing.JSeparator();
        labelSair = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaEventos = new javax.swing.JTable();
        labelTituloTabela = new javax.swing.JLabel();
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
        panelInicio = new javax.swing.JPanel();
        labelIconInicio = new javax.swing.JLabel();
        labelInicio = new javax.swing.JLabel();
        separadorAtividades = new javax.swing.JSeparator();
        panelInfoSemana = new javax.swing.JPanel();
        labelTituloTabela1 = new javax.swing.JLabel();
        labelTituloTabela2 = new javax.swing.JLabel();
        labelTituloTabela3 = new javax.swing.JLabel();
        labelTituloTabela4 = new javax.swing.JLabel();
        labelTituloTabela5 = new javax.swing.JLabel();
        textNomeSemana = new javax.swing.JTextField();
        textInicioSemana = new javax.swing.JTextField();
        textFimSemana = new javax.swing.JTextField();
        textLocalSemana = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textDescricaoSemana = new javax.swing.JTextArea();
        labelTituloTabela6 = new javax.swing.JLabel();
        comboOrganizadorSemana = new javax.swing.JComboBox<>();
        labelInvisivel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaCertificado = new javax.swing.JTable();
        labelTotalHoras = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(1, 114, 18));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        labelBemVindo.setBackground(new java.awt.Color(255, 255, 255));
        labelBemVindo.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelBemVindo.setForeground(new java.awt.Color(255, 255, 255));

        labelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/icon__menu.png"))); // NOI18N
        labelMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuMouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelBemVindo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorAtividades1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSair)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelSair)
                        .addComponent(labelMenu)
                        .addComponent(separadorAtividades1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabelaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título", "Total Horas", "Total Incritos", "Vagas Restantes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        jScrollPane3.setViewportView(tabelaEventos);

        labelTituloTabela.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela.setText("Minhas Atividades");

        panelMenuSuspenso.setBackground(new java.awt.Color(1, 155, 45));
        panelMenuSuspenso.setPreferredSize(new java.awt.Dimension(189, 649));

        panelEventos.setBackground(new java.awt.Color(1, 155, 45));

        labelIconEventos.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelIconEventos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconEventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/calendario.png"))); // NOI18N
        labelIconEventos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIconEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconEventosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconEventosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIconEventosMouseExited(evt);
            }
        });

        labelEventos.setBackground(new java.awt.Color(255, 255, 255));
        labelEventos.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelEventos.setText("Semanas");
        labelEventos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEventosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelEventosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelEventosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelEventosLayout = new javax.swing.GroupLayout(panelEventos);
        panelEventos.setLayout(panelEventosLayout);
        panelEventosLayout.setHorizontalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventosLayout.createSequentialGroup()
                .addComponent(labelIconEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(labelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPerfilLayout.setVerticalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIconPerfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
            .addComponent(labelPerfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCertificado.setBackground(new java.awt.Color(1, 155, 45));
        panelCertificado.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconCertificado.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconCertificado.setForeground(new java.awt.Color(255, 255, 255));
        labelIconCertificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/historico.png"))); // NOI18N
        labelIconCertificado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIconCertificado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconCertificadoMouseClicked(evt);
            }
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
        labelCerificado.setText("Histórico");
        labelCerificado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addGap(0, 0, 0)
                .addComponent(labelCerificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCertificadoLayout.setVerticalGroup(
            panelCertificadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCerificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelIconCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        panelInicio.setBackground(new java.awt.Color(1, 155, 45));
        panelInicio.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconInicio.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconInicio.setForeground(new java.awt.Color(255, 255, 255));
        labelIconInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/casa.png"))); // NOI18N
        labelIconInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelInicio.setBackground(new java.awt.Color(255, 255, 255));
        labelInicio.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelInicio.setForeground(new java.awt.Color(255, 255, 255));
        labelInicio.setText("Início");
        labelInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addComponent(labelIconInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIconInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
            .addComponent(labelInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(0, 636, Short.MAX_VALUE))
        );

        panelInfoSemana.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoSemana.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informação Semana", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        labelTituloTabela1.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela1.setText("Fim:");

        labelTituloTabela2.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela2.setText("Nome:");

        labelTituloTabela3.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela3.setText("Início:");

        labelTituloTabela4.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela4.setText("Descrição:");

        labelTituloTabela5.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela5.setText("Local:");

        textNomeSemana.setEditable(false);
        textNomeSemana.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        textInicioSemana.setEditable(false);
        textInicioSemana.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        textFimSemana.setEditable(false);
        textFimSemana.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        textLocalSemana.setEditable(false);
        textLocalSemana.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        textDescricaoSemana.setEditable(false);
        textDescricaoSemana.setColumns(20);
        textDescricaoSemana.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        textDescricaoSemana.setLineWrap(true);
        textDescricaoSemana.setRows(5);
        textDescricaoSemana.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textDescricaoSemana);

        labelTituloTabela6.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela6.setText("Organizadores:");

        comboOrganizadorSemana.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        labelInvisivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/invisivel.png"))); // NOI18N
        labelInvisivel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelInvisivel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelInvisivelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelInfoSemanaLayout = new javax.swing.GroupLayout(panelInfoSemana);
        panelInfoSemana.setLayout(panelInfoSemanaLayout);
        panelInfoSemanaLayout.setHorizontalGroup(
            panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                        .addComponent(labelTituloTabela2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNomeSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(labelTituloTabela6)
                        .addGap(18, 18, 18)
                        .addComponent(comboOrganizadorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(labelInvisivel))
                    .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                        .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                                .addComponent(labelTituloTabela4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                                .addComponent(labelTituloTabela5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textLocalSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelTituloTabela3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textInicioSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelTituloTabela1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFimSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInfoSemanaLayout.setVerticalGroup(
            panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInvisivel)
                    .addGroup(panelInfoSemanaLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTituloTabela2)
                            .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textNomeSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelTituloTabela6)
                                .addComponent(comboOrganizadorSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30)
                .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTituloTabela5)
                    .addComponent(textLocalSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTituloTabela3)
                    .addComponent(textInicioSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTituloTabela1)
                    .addComponent(textFimSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(panelInfoSemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTituloTabela4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Certificados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaCertificado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Evento", "Horas"
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
        jScrollPane4.setViewportView(tabelaCertificado);
        if (tabelaCertificado.getColumnModel().getColumnCount() > 0) {
            tabelaCertificado.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaCertificado.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        labelTotalHoras.setBackground(new java.awt.Color(255, 255, 255));
        labelTotalHoras.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        labelTotalHoras.setText("Total Horas: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalHoras)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotalHoras)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTituloTabela)
                            .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(897, 897, 897))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelInfoSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelTituloTabela)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelInfoSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 41, Short.MAX_VALUE))))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void labelCerificadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerificadoMouseExited
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelCerificadoMouseExited

    private void labelCerificadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerificadoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelCerificadoMouseEntered

    private void labelPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPerfilMouseExited
        Color cor = new Color(1, 155, 45);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelPerfilMouseExited

    private void labelPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPerfilMouseEntered
        Color cor = new Color(1, 114, 18);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelPerfilMouseEntered

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

    private void labelIconPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconPerfilMouseExited
        Color cor = new Color(1, 155, 45);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelIconPerfilMouseExited

    private void labelIconPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconPerfilMouseEntered
        Color cor = new Color(1, 114, 18);
        panelPerfil.setBackground(cor);
    }//GEN-LAST:event_labelIconPerfilMouseEntered

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

    private void labelEventosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEventosMouseExited

        Color cor = new Color(1, 155, 45);
        panelEventos.setBackground(cor);
    }//GEN-LAST:event_labelEventosMouseExited

    private void labelEventosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEventosMouseEntered
        Color cor = new Color(1, 114, 18);
        panelEventos.setBackground(cor);
    }//GEN-LAST:event_labelEventosMouseEntered

    private void labelEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEventosMouseClicked

        if(statusAdmin)
        {
            this.dispose();
            TelaSemana telaEventos = new TelaSemana((Frame) this.getParent(), true, null, administrador);
            telaEventos.setVisible(true);
            dispose();
        }
        else
        {
            this.dispose();
            TelaSemana telaEventos = new TelaSemana((Frame) this.getParent(), true, participante, null);
            telaEventos.setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_labelEventosMouseClicked

    private void labelIconEventosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconEventosMouseExited

        Color cor = new Color(1, 155, 45);
        panelEventos.setBackground(cor);
    }//GEN-LAST:event_labelIconEventosMouseExited

    private void labelIconEventosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconEventosMouseEntered

        Color cor = new Color(1, 114, 18);
        panelEventos.setBackground(cor);
    }//GEN-LAST:event_labelIconEventosMouseEntered

    private void labelIconEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconEventosMouseClicked

        if(statusAdmin)
        {
            this.dispose();
            TelaSemana telaEventos = new TelaSemana((Frame) this.getParent(), true, null, administrador);
            telaEventos.setVisible(true);
        }
        else
        {
            this.dispose();
            TelaSemana telaEventos = new TelaSemana((Frame) this.getParent(), true, participante, null);
            telaEventos.setVisible(true);
        }

    }//GEN-LAST:event_labelIconEventosMouseClicked

    private void labelInvisivelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInvisivelMouseClicked
       
        panelInfoSemana.setVisible(false);
    }//GEN-LAST:event_labelInvisivelMouseClicked

    private void labelIconCertificadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconCertificadoMouseExited
        Color cor = new Color(1, 155, 45);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelIconCertificadoMouseExited

    private void labelIconCertificadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconCertificadoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelCertificado.setBackground(cor);
    }//GEN-LAST:event_labelIconCertificadoMouseEntered

    private void labelIconCertificadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconCertificadoMouseClicked

        dispose();
        TelaHistoricoEventos telaCertificado = new TelaHistoricoEventos((Frame) this.getParent(), true, participante, administrador);
        telaCertificado.setVisible(true);

    }//GEN-LAST:event_labelIconCertificadoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboOrganizadorSemana;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelBemVindo;
    private javax.swing.JLabel labelCerificado;
    private javax.swing.JLabel labelEventos;
    private javax.swing.JLabel labelIconCertificado;
    private javax.swing.JLabel labelIconEventos;
    private javax.swing.JLabel labelIconInicio;
    private javax.swing.JLabel labelIconPerfil;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JLabel labelInvisivel;
    private javax.swing.JLabel labelMenu;
    private javax.swing.JLabel labelPerfil;
    private javax.swing.JLabel labelSair;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelTituloTabela1;
    private javax.swing.JLabel labelTituloTabela2;
    private javax.swing.JLabel labelTituloTabela3;
    private javax.swing.JLabel labelTituloTabela4;
    private javax.swing.JLabel labelTituloTabela5;
    private javax.swing.JLabel labelTituloTabela6;
    private javax.swing.JLabel labelTotalHoras;
    private javax.swing.JPanel panelCertificado;
    private javax.swing.JPanel panelEventos;
    private javax.swing.JPanel panelInfoSemana;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelMenuSuspenso;
    private javax.swing.JPanel panelPerfil;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JSeparator separadorAtividades1;
    private javax.swing.JTable tabelaCertificado;
    private javax.swing.JTable tabelaEventos;
    private javax.swing.JTextArea textDescricaoSemana;
    private javax.swing.JTextField textFimSemana;
    private javax.swing.JTextField textInicioSemana;
    private javax.swing.JTextField textLocalSemana;
    private javax.swing.JTextField textNomeSemana;
    // End of variables declaration//GEN-END:variables
}
