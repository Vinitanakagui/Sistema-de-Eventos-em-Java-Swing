package visao.evento;

import controle.EventoDAO;
import controle.MatriculaDAO;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.Evento;
import modelo.Matricula;
import static modelo.enuns.Status.A;
import static modelo.enuns.Status.F;
import static modelo.enuns.Status.P;
import utils.DateUtils;
import utils.JOptionPaneUtils;
import visao.matricula.TelaAdicionarMatricula;
import visao.matricula.TelaAdicionarPresenca;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.enuns.Ativo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;


public class TelaVisualizarInscritos extends javax.swing.JDialog
{

    private MatriculaDAO matriculaDAO;
    private List<Matricula> listaMatricula;
    private Evento evento;
    private EventoDAO eventoDAO;
    
    public TelaVisualizarInscritos(java.awt.Frame parent, boolean modal, Evento evento)
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.matriculaDAO = new MatriculaDAO();
        this.listaMatricula = new ArrayList<>();
        this.evento = evento;
        this.eventoDAO = new EventoDAO();
        
        this.listaMatricula = matriculaDAO.buscarInscritos(evento.getIdEvento());
        
        preencherTabelaMatriculados();
        preencherDadosEvento();
        
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
        
        ajustarColunas();
                
    }
    
     private void ajustarColunas()
    {
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
    
        tabelaMatriculado.getColumnModel().getColumn(2).setCellRenderer(centro);
         
    }
    
    private void pesquisar()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMatriculado.getModel();
        modelo.setRowCount(0);
        String situacao = "";
        
        String nome = textPesquisar.getText();
        
        listaMatricula = matriculaDAO.pesquisar(nome);
        
        for (Matricula matricula : listaMatricula) 
        {

            switch (matricula.getStatus())
            {
                case A -> situacao = "Pendente";
                case F -> situacao = "Faltou";
                case P -> situacao = "Presente";
                
            }
            
            modelo.addRow(new String[]
            {
                matricula.getParticipante().getNome(),
                matricula.getParticipante().getCurso().getNome(),
                situacao
            
            });
        }
        
    }
    
    private void preencherTabelaMatriculados()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMatriculado.getModel();
        modelo.setRowCount(0);
        String situacao = "";
        
        for (Matricula matricula : listaMatricula) 
        {

            switch (matricula.getStatus())
            {
                case A -> situacao = "Pendente";
                case F -> situacao = "Faltou";
                case P -> situacao = "Presente";
                
            }
            
            modelo.addRow(new String[]
            {
                matricula.getParticipante().getNome(),
                matricula.getParticipante().getCurso().getNome(),
                situacao
            
            });
        }
    }
    
    private void preencherDadosEvento()
    {
        textNome.setText(evento.getTitulo());
        textData.setDate(DateUtils.asDate(evento.getData()));
        spinnerCargaHoraria.setValue(evento.getCargaHoraria());
        spinnerTotalVagas.setValue(evento.getNumeroVagas());
    }
    
    private void preencherTabelaMatriculadosAdicionados()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMatriculado.getModel();
        modelo.setRowCount(0);
        String situacao = "";
        
        for (Matricula matricula : evento.getListaMatricula()) 
        {
            switch (matricula.getStatus())
            {
                case A -> situacao = "Pendente";
                case F -> situacao = "Faltou";
                case P -> situacao = "Presente";
                
            }
            
            modelo.addRow(new String[]
            {
                matricula.getParticipante().getNome(),
                matricula.getParticipante().getCurso().getNome(),
                situacao
            
            });
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelNome1 = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        spinnerCargaHoraria = new javax.swing.JSpinner();
        labelNome2 = new javax.swing.JLabel();
        spinnerTotalVagas = new javax.swing.JSpinner();
        labelNome3 = new javax.swing.JLabel();
        textData = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMatriculado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        textPesquisar = new javax.swing.JTextField();
        botaoLancarPresenca = new javax.swing.JButton();
        labelExcluirMatricula = new javax.swing.JLabel();
        labelAdicionarMatricula = new javax.swing.JLabel();
        labelImprimir = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        labelNome1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome1.setText("Carga Horária: ");

        labelNome.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome.setText("Nome: ");

        textNome.setEditable(false);
        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        spinnerCargaHoraria.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        spinnerCargaHoraria.setEnabled(false);

        labelNome2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome2.setText("Total Vagas:");

        spinnerTotalVagas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        spinnerTotalVagas.setEnabled(false);

        labelNome3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome3.setText("Data:");

        textData.setDateFormatString("d'/'M'/'y");
        textData.setEnabled(false);
        textData.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inscritos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaMatriculado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Curso", "Presença"
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
        jScrollPane1.setViewportView(tabelaMatriculado);
        if (tabelaMatriculado.getColumnModel().getColumnCount() > 0) {
            tabelaMatriculado.getColumnModel().getColumn(1).setMinWidth(200);
            tabelaMatriculado.getColumnModel().getColumn(1).setMaxWidth(200);
            tabelaMatriculado.getColumnModel().getColumn(2).setMinWidth(150);
            tabelaMatriculado.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setText("Pesquisar:");

        textPesquisar.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        botaoLancarPresenca.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoLancarPresenca.setText("Lançar Presenças");
        botaoLancarPresenca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLancarPresencaActionPerformed(evt);
            }
        });

        labelExcluirMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirMatriculaMouseClicked(evt);
            }
        });

        labelAdicionarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/adicionar.png"))); // NOI18N
        labelAdicionarMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelAdicionarMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdicionarMatriculaMouseClicked(evt);
            }
        });

        labelImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/imprimir.png"))); // NOI18N
        labelImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelImprimirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoLancarPresenca)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelImprimir)
                            .addGap(18, 18, 18)
                            .addComponent(labelAdicionarMatricula)
                            .addGap(18, 18, 18)
                            .addComponent(labelExcluirMatricula))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelAdicionarMatricula, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelExcluirMatricula, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(labelImprimir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(botaoLancarPresenca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoCancelar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoEditar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(botaoCancelar)
                        .addGap(41, 41, 41)
                        .addComponent(botaoEditar))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(labelNome)
                            .addGap(6, 6, 6)
                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelNome3)
                            .addGap(6, 6, 6)
                            .addComponent(textData, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(labelNome1)
                            .addGap(6, 6, 6)
                            .addComponent(spinnerCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelNome2)
                            .addGap(18, 18, 18)
                            .addComponent(spinnerTotalVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNome)
                            .addComponent(labelNome3))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerTotalVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNome1)
                            .addComponent(labelNome2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoEditar))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelAdicionarMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdicionarMatriculaMouseClicked

        TelaAdicionarMatricula telaAdicionarMatricula = new TelaAdicionarMatricula((Frame) this.getParent(), true, evento);
        telaAdicionarMatricula.setVisible(true);
        
        if(telaAdicionarMatricula.getListaMatricula() != null)
        {
            evento.setListaMatricula(telaAdicionarMatricula.getListaMatricula());
            eventoDAO.alterar(evento);
            preencherTabelaMatriculadosAdicionados();
        }
   
    }//GEN-LAST:event_labelAdicionarMatriculaMouseClicked

    private void labelExcluirMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirMatriculaMouseClicked
        
        int linhas[] = tabelaMatriculado.getSelectedRows();
        
        if(linhas.length == 0)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            for (int linha : linhas)
            {
                for (int i = listaMatricula.size() -1; i >=0 ; i--) 
                {
                    if(listaMatricula.get(i).getParticipante().getNome().equals(tabelaMatriculado.getValueAt(linha, 0)))
                    {
                        Matricula matricula = new Matricula();
                        
                        matricula = matriculaDAO.buscarPorEvento((String) tabelaMatriculado.getValueAt(linha, 0), evento.getIdEvento());
                        
                        if(matricula != null)
                        {System.out.println("Entrou");
                            matriculaDAO.excluir(matricula);
                        }
                        
                        listaMatricula.remove(i);
                    }
                }
            }
            
            evento.setListaMatricula(listaMatricula);
            preencherTabelaMatriculadosAdicionados();
        }
    }//GEN-LAST:event_labelExcluirMatriculaMouseClicked

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        
        eventoDAO.alterar(evento);
        dispose();
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void botaoLancarPresencaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLancarPresencaActionPerformed
         
       if(evento.getSemana().getAtiva().equals(Ativo.N))
       {
            eventoDAO.alterar(evento);
            TelaAdicionarPresenca telaAdicionarPresenca = new TelaAdicionarPresenca((Frame) this.getParent(), true, evento);
            telaAdicionarPresenca.setVisible(true);

            if(!telaAdicionarPresenca.isCancelar())
            {
                evento.setListaMatricula(telaAdicionarPresenca.getListaMatricula());
                eventoDAO.alterar(evento);
                preencherTabelaMatriculadosAdicionados();
            }
       }
       else
       {
           JOptionPaneUtils.atencao("Não é possível lançar presença em uma semana em andamento", "Atenção");
       }
        
    }//GEN-LAST:event_botaoLancarPresencaActionPerformed

    private void labelImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImprimirMouseClicked
       
          try {
                JasperReport relatorioCompilado
                        = JasperCompileManager.compileReport("src/relatorio/ListaPresenca.jrxml");

                JasperPrint relatorioPreenchido = JasperFillManager.fillReport(relatorioCompilado, null, 
                        new JRBeanCollectionDataSource(evento.getListaMatricula()));

                JDialog tela = new JDialog(this, "Lista Presença", true);
                tela.setSize(800, 400);

                JRViewer painelRelatorio = new JRViewer(relatorioPreenchido);

                tela.getContentPane().add(painelRelatorio);

                tela.setVisible(true);
            
        } 
        catch (JRException ex) 
        {
            Logger.getLogger(TelaVisualizarInscritos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao gerar o relatório.");
        }
    }//GEN-LAST:event_labelImprimirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoLancarPresenca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAdicionarMatricula;
    private javax.swing.JLabel labelExcluirMatricula;
    private javax.swing.JLabel labelImprimir;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JLabel labelNome3;
    private javax.swing.JSpinner spinnerCargaHoraria;
    private javax.swing.JSpinner spinnerTotalVagas;
    private javax.swing.JTable tabelaMatriculado;
    private com.toedter.calendar.JDateChooser textData;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textPesquisar;
    // End of variables declaration//GEN-END:variables
}
