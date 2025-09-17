package visao.matricula;

import controle.MatriculaDAO;
import controle.ParticipanteDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.Evento;
import modelo.Matricula;
import modelo.Participante;
import modelo.enuns.Status;
import utils.JOptionPaneUtils;


public class TelaAdicionarMatricula extends javax.swing.JDialog {

    private List<Matricula> listaMatricula;
    private List<Participante> listaParticipante;
    private MatriculaDAO matriculaDAO;
    private ParticipanteDAO participanteDAO;
    private Evento evento;
    
    public TelaAdicionarMatricula(java.awt.Frame parent, boolean modal, Evento evento) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.listaMatricula = new ArrayList<>();
        this.listaParticipante = new ArrayList<>();
        this.matriculaDAO = new MatriculaDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.evento = evento;
        
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
        
        preencherTabelaParticipante();
        preencherTabelaParticipanteAdicionado(); 
        preencherListaMatricula();
    }

    public List<Matricula> getListaMatricula() 
    {
        return listaMatricula;
    }
    
    

    private void preencherListaMatricula()
    {
        for (int i = 0; i < tabelaParticipanteAdicionado.getRowCount(); i++) 
        {
            Matricula matricula = matriculaDAO.buscarPorEvento((String) tabelaParticipante.getValueAt(i, 1), evento.getIdEvento());
            
            if(matricula != null)
            {
                listaMatricula.add(matricula);
            }
        }
    }
    private void preencherTabelaParticipanteAdicionado()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaParticipanteAdicionado.getModel();
        modelo.setRowCount(0);
        
        for (Matricula matricula : evento.getListaMatricula()) 
        {
            modelo.addRow(new String[]
            {
                matricula.getParticipante().getProntuario(),
                matricula.getParticipante().getNome(),
                matricula.getParticipante().getCurso().getNome()
            });
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaParticipante = new javax.swing.JTable();
        textPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboCurso = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        botaoSelecionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        labelExcluirCurso = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaParticipanteAdicionado = new javax.swing.JTable();
        botaoFinalizarEditar = new javax.swing.JButton();
        separadorAtividades = new javax.swing.JSeparator();
        labelTituloTabela = new javax.swing.JLabel();
        labelVoltar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Participante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(856, 362));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaParticipante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prontuário", "Nome", "Curso"
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
        jScrollPane1.setViewportView(tabelaParticipante);
        if (tabelaParticipante.getColumnModel().getColumnCount() > 0) {
            tabelaParticipante.getColumnModel().getColumn(0).setMinWidth(100);
            tabelaParticipante.getColumnModel().getColumn(0).setMaxWidth(100);
            tabelaParticipante.getColumnModel().getColumn(2).setMinWidth(200);
            tabelaParticipante.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 91, 770, 191));

        textPesquisar.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jPanel3.add(textPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 45, 370, -1));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setText("Pesquisar:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 48, -1, -1));

        comboCurso.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        comboCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        comboCurso.setPreferredSize(new java.awt.Dimension(200, 28));
        jPanel3.add(comboCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 200, 20));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel2.setText("Curso:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        botaoSelecionar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoSelecionar.setText("Selecionar");
        botaoSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSelecionarActionPerformed(evt);
            }
        });
        jPanel3.add(botaoSelecionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 300, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Participantes Adicionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N
        jPanel5.setMaximumSize(new java.awt.Dimension(856, 362));

        labelExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirCursoMouseClicked(evt);
            }
        });

        tabelaParticipanteAdicionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prontuário", "Nome", "Curso"
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
        jScrollPane3.setViewportView(tabelaParticipanteAdicionado);
        if (tabelaParticipanteAdicionado.getColumnModel().getColumnCount() > 0) {
            tabelaParticipanteAdicionado.getColumnModel().getColumn(0).setMinWidth(100);
            tabelaParticipanteAdicionado.getColumnModel().getColumn(0).setMaxWidth(100);
            tabelaParticipanteAdicionado.getColumnModel().getColumn(2).setMinWidth(200);
            tabelaParticipanteAdicionado.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelExcluirCurso))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelExcluirCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        botaoFinalizarEditar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoFinalizarEditar.setText("Finalizar");
        botaoFinalizarEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarEditarActionPerformed(evt);
            }
        });

        labelTituloTabela.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela.setText("Matricular Participante");

        labelVoltar.setBackground(new java.awt.Color(255, 255, 255));
        labelVoltar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/voltar.png"))); // NOI18N
        labelVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelVoltarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTituloTabela)
                            .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botaoFinalizarEditar)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTituloTabela)
                    .addComponent(labelVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFinalizarEditar)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void preencherTabelaParticipante()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaParticipante.getModel();
        modelo.setRowCount(0);
        
        listaParticipante = participanteDAO.buscarTodos();
        
        for (Participante participante : listaParticipante)
        {
           modelo.addRow(new String[]
           {
               participante.getProntuario(),
               participante.getNome(),
               participante.getCurso().getNome()
           
           });
        }
        
    }
     private void pesquisar()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaParticipante.getModel();
        modelo.setRowCount(0);
        
        String nome = textPesquisar.getText();
        
        if(comboCurso.getSelectedItem().equals("Selecione"))
        {
            listaParticipante = participanteDAO.pesquisarPorNome(nome);
            
        }
        else
        {
            String curso = (String) comboCurso.getSelectedItem();
            
            listaParticipante = participanteDAO.pesquisarPorCurso(nome, curso);
        }
        
        for (Participante participante : listaParticipante)
        {
           modelo.addRow(new String[]
           {
               participante.getProntuario(),
               participante.getNome(),
               participante.getCurso().getNome()
           
           });
        }
    }
     
    private boolean verificarExistencia(String prontuario)
    {     
        for (int i = 0; i < tabelaParticipanteAdicionado.getRowCount(); i++) 
        {
            if(tabelaParticipanteAdicionado.getValueAt(i, 0).equals(prontuario))
            {
                return true;
            }
            
        }
        
        return false;
    }
    
    private boolean verificarDuplicidade(Matricula matricula)
    {
        for (Matricula matriculaAux : listaMatricula)
        {
            if(matriculaAux.getParticipante().getProntuario().equals(matricula.getParticipante().getProntuario()))
            {
                return true;
            }
        }
        
        return false;
    }
     
    private void botaoSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSelecionarActionPerformed

        DefaultTableModel modelo = (DefaultTableModel) tabelaParticipanteAdicionado.getModel();

        int linhas[] = tabelaParticipante.getSelectedRows();

        if(linhas.length == 0)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            for (int i = 0; i < linhas.length; i++)
            {
                if(!verificarExistencia((String) tabelaParticipante.getValueAt(linhas[i], 0)))
                {
                    modelo.addRow(new String[]
                        {
                            (String) tabelaParticipante.getValueAt(linhas[i], 0),
                            (String) tabelaParticipante.getValueAt(linhas[i], 1),
                            (String) tabelaParticipante.getValueAt(linhas[i], 2)

                        });
                    }

                }

            }
    }//GEN-LAST:event_botaoSelecionarActionPerformed

    private void labelExcluirCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirCursoMouseClicked

        DefaultTableModel modelo = (DefaultTableModel) tabelaParticipanteAdicionado.getModel();
        int[] linhas = tabelaParticipanteAdicionado.getSelectedRows();

        if (linhas.length == 0)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            for (int i = linhas.length - 1; i >= 0; i--)
            {
                modelo.removeRow(linhas[i]);
            }
        }

    }//GEN-LAST:event_labelExcluirCursoMouseClicked

    private void botaoFinalizarEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizarEditarActionPerformed
        
        
        for (int i = 0; i < tabelaParticipanteAdicionado.getRowCount(); i++) 
        {
            Matricula matricula = new Matricula();
            Participante participante = new Participante();

            participante = participanteDAO.buscarParticipante((String) tabelaParticipanteAdicionado.getValueAt(i, 0));

            matricula.setParticipante(participante);
            matricula.setData(LocalDate.now());
            matricula.setIdMatricula(i+1);
            matricula.setEvento(evento);
            matricula.setStatus(Status.A);

            if(listaMatricula.isEmpty())
            {
                listaMatricula.add(matricula);
            }
            if(!verificarDuplicidade(matricula))
            {
                listaMatricula.add(matricula);
            }
 
            dispose();
        }

    }//GEN-LAST:event_botaoFinalizarEditarActionPerformed

    private void labelVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVoltarMouseClicked

        listaMatricula = null;
        
        dispose();
    }//GEN-LAST:event_labelVoltarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFinalizarEditar;
    private javax.swing.JButton botaoSelecionar;
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelExcluirCurso;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelVoltar;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JTable tabelaParticipante;
    private javax.swing.JTable tabelaParticipanteAdicionado;
    private javax.swing.JTextField textPesquisar;
    // End of variables declaration//GEN-END:variables
}
