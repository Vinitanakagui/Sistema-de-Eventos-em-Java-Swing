package visao.evento;

import controle.EventoDAO;
import controle.MatriculaDAO;
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
import java.util.List;
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
import modelo.Participante;
import modelo.Semana;
import visao.home.TelaMenuPrincipal;
import static modelo.enuns.Status.A;
import static modelo.enuns.Status.F;
import static modelo.enuns.Status.P;
import utils.JOptionPaneUtils;
import visao.dados.TelaDados;
import visao.dados.TelaDadosAdministrador;
import visao.semana.TelaSemana;

public class TelaHistoricoEventos extends javax.swing.JDialog {

    private Boolean menuAtivo;
    private Administrador administrador;
    private Participante participante;
    private DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Semana semana;
    private  List<Evento> listaEventoAux;
    
    public TelaHistoricoEventos(java.awt.Frame parent, boolean modal, Participante participante, Administrador administrador) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.menuAtivo = false;
        this.administrador = administrador;
        this.participante = participante;
        this.semana = new Semana();
        this.listaEventoAux = new ArrayList<>();
        
        
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

        
        menuInicializar();
        preencherTabelaEvento();
        ajustarLabel();
        
        if(participante == null)
        {
            ajustarCampoBemVindo(administrador.getLogin());
        }
        else
        {
            ajustarCampoBemVindo(participante.getNome());
        }
        
        ajustarColunas();
   }
    
    private void ajustarColunas()
    {
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        
        tabelaEvento.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        tabelaEvento.getColumnModel().getColumn(1).setCellRenderer(esquerda);
        tabelaEvento.getColumnModel().getColumn(2).setCellRenderer(centro);

        
    }
    
    private void ajustarLabel()
    {
        
        if(participante == null)
        {
            EventoDAO eventoDAO = new EventoDAO();
            
            labelTotalEventos.setText(labelTotalEventos.getText() + eventoDAO.contarEventos());
            labelTotalHoras.setText(labelTotalHoras.getText() + eventoDAO.somarCargaHoraria());
        }
        else
        {
            MatriculaDAO matriculaDAO = new MatriculaDAO();
            
            labelTotalEventos.setText(labelTotalEventos.getText() + matriculaDAO.contarEventos(participante.getProntuario()));
            labelTotalHoras.setText(labelTotalHoras.getText() + matriculaDAO.somarCargaHoraria(participante.getProntuario()));
        }
    }
    
    private void visualizarEvento(int linha)
    {
        EventoDAO eventoDAO = new EventoDAO();
        SemanaDAO semanaDAO = new SemanaDAO();
        
        String nomeEvento = (String) tabelaEvento.getValueAt(linha, 0);
        String nomeSemana = (String) tabelaEvento.getValueAt(linha, 1);
       
        Evento evento = eventoDAO.buscarPorNomeSemana(nomeEvento, nomeSemana);
        
        if(evento != null)
        {
            if(participante == null)
            {
                TelaVisualizarInscritos telaVisualizarInscritos = new TelaVisualizarInscritos((Frame) this.getParent(), true, evento);
                telaVisualizarInscritos.setVisible(true);
            }
            else
            {
                TelaInscreverEvento telaInscreverEvento = new TelaInscreverEvento((Frame) this.getParent(), true, evento, participante);
                telaInscreverEvento.setVisible(true);
            }
        }
        
      
        
    }
    
    private void ajustarCampoBemVindo(String nome)
    {
        labelBemVindo.setText(nome);
    }
    
    private String verificarSituacaoEvento(Evento evento)
    {
        if(evento.getData().isAfter(LocalDate.now()))
        {
            return "Concluído";
        }
        else
        {
            return "Em andamento";
        }
    }
    private void preencherTabelaEvento()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEvento.getModel();
        modelo.setRowCount(0);
             
        if(participante != null)
        {
           
            String situacao = "";
            
           for (Matricula matricula : participante.getListaMatricula()) 
            {
                switch (matricula.getStatus())
                {
                    case A -> situacao = "Em andamento";
                    case F -> situacao = "Faltou";
                    case P -> situacao = "Concluído";

                }
                
                modelo.addRow(new String[]
                {
                    matricula.getEvento().getTitulo(),
                    matricula.getEvento().getSemana().getNome(),
                    situacao
                
                });
            } 
        }
        else
        {
            EventoDAO eventoDAO = new EventoDAO();
            List<Evento> listaEvento = eventoDAO.buscarTodos();
            String situacao;
            
            for (Evento evento : listaEvento) 
            {
                situacao = verificarSituacaoEvento(evento);
                
                modelo.addRow(new String[]
                {
                    evento.getTitulo(),
                    evento.getSemana().getNome(),
                    situacao
                
                });
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
            
            Color cor = new Color(1, 114, 18);
            this.panelHistorico.setBackground(cor);
            
            
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelMenu = new javax.swing.JLabel();
        labelBemVindo = new javax.swing.JLabel();
        separadorAtividades1 = new javax.swing.JSeparator();
        labelSair = new javax.swing.JLabel();
        labelTituloTabela = new javax.swing.JLabel();
        panelMenuSuspenso = new javax.swing.JPanel();
        panelEventos = new javax.swing.JPanel();
        labelIconEventos = new javax.swing.JLabel();
        labelEventos = new javax.swing.JLabel();
        panelPerfil = new javax.swing.JPanel();
        labelIconPerfil = new javax.swing.JLabel();
        labelPerfil = new javax.swing.JLabel();
        panelHistorico = new javax.swing.JPanel();
        labelIconCertificado = new javax.swing.JLabel();
        labelCerificado = new javax.swing.JLabel();
        panelInicio2 = new javax.swing.JPanel();
        labelIconInicio2 = new javax.swing.JLabel();
        labelInicio2 = new javax.swing.JLabel();
        separadorAtividades = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEvento = new javax.swing.JTable();
        labelTotalEventos = new javax.swing.JLabel();
        labelTotalHoras = new javax.swing.JLabel();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        labelTituloTabela.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela.setText("Histórico Eventos");

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
                .addGap(0, 0, 0)
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
                .addGap(0, 0, 0)
                .addComponent(labelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPerfilLayout.setVerticalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelIconPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        panelHistorico.setBackground(new java.awt.Color(1, 155, 45));
        panelHistorico.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconCertificado.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconCertificado.setForeground(new java.awt.Color(255, 255, 255));
        labelIconCertificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/historico.png"))); // NOI18N
        labelIconCertificado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        javax.swing.GroupLayout panelHistoricoLayout = new javax.swing.GroupLayout(panelHistorico);
        panelHistorico.setLayout(panelHistoricoLayout);
        panelHistoricoLayout.setHorizontalGroup(
            panelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoricoLayout.createSequentialGroup()
                .addComponent(labelIconCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelCerificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHistoricoLayout.setVerticalGroup(
            panelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCerificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelIconCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        panelInicio2.setBackground(new java.awt.Color(1, 155, 45));
        panelInicio2.setPreferredSize(new java.awt.Dimension(196, 58));

        labelIconInicio2.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelIconInicio2.setForeground(new java.awt.Color(255, 255, 255));
        labelIconInicio2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconInicio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/casa.png"))); // NOI18N
        labelIconInicio2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelIconInicio2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconInicio2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconInicio2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelIconInicio2MouseExited(evt);
            }
        });

        labelInicio2.setBackground(new java.awt.Color(255, 255, 255));
        labelInicio2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelInicio2.setForeground(new java.awt.Color(255, 255, 255));
        labelInicio2.setText("Início");
        labelInicio2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelInicio2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelInicio2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelInicio2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelInicio2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelInicio2Layout = new javax.swing.GroupLayout(panelInicio2);
        panelInicio2.setLayout(panelInicio2Layout);
        panelInicio2Layout.setHorizontalGroup(
            panelInicio2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicio2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(labelIconInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelInicio2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelInicio2Layout.setVerticalGroup(
            panelInicio2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelIconInicio2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
            .addComponent(labelInicio2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMenuSuspensoLayout = new javax.swing.GroupLayout(panelMenuSuspenso);
        panelMenuSuspenso.setLayout(panelMenuSuspensoLayout);
        panelMenuSuspensoLayout.setHorizontalGroup(
            panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                .addGroup(panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                        .addComponent(panelHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(panelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                        .addGroup(panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMenuSuspensoLayout.setVerticalGroup(
            panelMenuSuspensoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuSuspensoLayout.createSequentialGroup()
                .addComponent(panelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelaEvento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Semana", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            tabelaEvento.getColumnModel().getColumn(2).setMinWidth(130);
            tabelaEvento.getColumnModel().getColumn(2).setMaxWidth(130);
        }

        labelTotalEventos.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTotalEventos.setText("Total Eventos:  ");

        labelTotalHoras.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTotalHoras.setText("Total Horas: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTituloTabela)
                            .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTotalEventos)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTotalHoras))))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuSuspenso, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(labelTituloTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTotalEventos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTotalHoras)
                        .addContainerGap(235, Short.MAX_VALUE))))
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

    private void labelCerificadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerificadoMouseExited
        Color cor = new Color(1, 155, 45);
        panelHistorico.setBackground(cor);
    }//GEN-LAST:event_labelCerificadoMouseExited

    private void labelCerificadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerificadoMouseEntered
        Color cor = new Color(1, 114, 18);
        panelHistorico.setBackground(cor);
    }//GEN-LAST:event_labelCerificadoMouseEntered

    private void labelInicio2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInicio2MouseClicked

        dispose();
        TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal((Frame) this.getParent(), true, participante, administrador);
        telaMenuPrincipal.setVisible(true);

    }//GEN-LAST:event_labelInicio2MouseClicked

    private void labelInicio2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInicio2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelInicio2MouseEntered

    private void labelInicio2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelInicio2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelInicio2MouseExited

    private void labelIconInicio2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconInicio2MouseClicked

        dispose();
        TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal((Frame) this.getParent(), true, participante, administrador);
        telaMenuPrincipal.setVisible(true);


    }//GEN-LAST:event_labelIconInicio2MouseClicked

    private void labelIconInicio2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconInicio2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelIconInicio2MouseEntered

    private void labelIconInicio2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconInicio2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelIconInicio2MouseExited

    private void labelEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEventosMouseClicked
        
        dispose();
        TelaSemana telaSemana = new TelaSemana((Frame) this.getParent(), true, participante, administrador);
        telaSemana.setVisible(true);

    }//GEN-LAST:event_labelEventosMouseClicked

    private void labelIconEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconEventosMouseClicked
        
        dispose();
        TelaSemana telaSemana = new TelaSemana((Frame) this.getParent(), true, participante, administrador);
        telaSemana.setVisible(true);

    }//GEN-LAST:event_labelIconEventosMouseClicked

    private void labelIconEventosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconEventosMouseEntered
        
        Color cor = new Color(1, 114, 18);
        panelHistorico.setBackground(cor);
    }//GEN-LAST:event_labelIconEventosMouseEntered

    private void labelIconEventosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconEventosMouseExited
        
        Color cor = new Color(1, 155, 45);
        panelEventos.setBackground(cor);
        
    }//GEN-LAST:event_labelIconEventosMouseExited

    private void labelEventosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEventosMouseEntered
        
        Color cor = new Color(1, 114, 18);
        panelEventos.setBackground(cor);
    }//GEN-LAST:event_labelEventosMouseEntered

    private void labelEventosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEventosMouseExited
        
         Color cor = new Color(1, 155, 45);
        panelInicio2.setBackground(cor);
        
    }//GEN-LAST:event_labelEventosMouseExited

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBemVindo;
    private javax.swing.JLabel labelCerificado;
    private javax.swing.JLabel labelEventos;
    private javax.swing.JLabel labelIconCertificado;
    private javax.swing.JLabel labelIconEventos;
    private javax.swing.JLabel labelIconInicio2;
    private javax.swing.JLabel labelIconPerfil;
    private javax.swing.JLabel labelInicio2;
    private javax.swing.JLabel labelMenu;
    private javax.swing.JLabel labelPerfil;
    private javax.swing.JLabel labelSair;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelTotalEventos;
    private javax.swing.JLabel labelTotalHoras;
    private javax.swing.JPanel panelEventos;
    private javax.swing.JPanel panelHistorico;
    private javax.swing.JPanel panelInicio2;
    private javax.swing.JPanel panelMenuSuspenso;
    private javax.swing.JPanel panelPerfil;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JSeparator separadorAtividades1;
    private javax.swing.JTable tabelaEvento;
    // End of variables declaration//GEN-END:variables
}
