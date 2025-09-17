package visao.organizador;

import controle.CursoDAO;
import controle.ParticipanteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Organizador;
import modelo.Participante;
import utils.JOptionPaneUtils;

public class TelaAdicionarOrganizador extends javax.swing.JDialog {

    private List<Participante> listaParticipante;
    private ParticipanteDAO participanteDAO;
    private List<Curso> listaCurso;
    private CursoDAO cursoDAO;
    private List<Organizador> listaOrganizador;
    private boolean cancelar;
   
    public TelaAdicionarOrganizador(java.awt.Frame parent, boolean modal, List<Organizador> listaOrganizador) 
    {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
  
        this.listaParticipante = new ArrayList<>();
        this.participanteDAO = new ParticipanteDAO();
        this.cursoDAO = new CursoDAO();
        this.listaCurso = new ArrayList<>();
        this.cancelar = false;
        this.listaOrganizador = new ArrayList<>();
        
        if(listaOrganizador == null)
        {
            
            this.listaOrganizador = new ArrayList<>();
        }
        else
        {
            this.listaOrganizador = listaOrganizador;
           
            preencherDados();
        }
        
        
        preencherTabela();
        preencherComboCurso();
        
        
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
    }

    public boolean isCancelar() 
    {
        return cancelar;
    }

    public List<Organizador> getListaOrganizador()
    {
        return listaOrganizador;
    }
    
    private void preencherDados()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizadorSelecionado.getModel();
        modelo.setRowCount(0);
        
        String prontuario;
        
        for (Organizador organizador : listaOrganizador) 
        {
            
            prontuario = participanteDAO.buscarProntuarioPorNome(organizador.getNome());
            
            modelo.addRow(new String[]
            {           
               prontuario,
               organizador.getNome(),
               organizador.getCurso().getNome()
            
            });
        }
        
         this.listaOrganizador = new ArrayList<>();
    }
       
    private void preencherTabela()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizador.getModel();
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
        DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizador.getModel();
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
    
    private void preencherComboCurso()
    {
        listaCurso = cursoDAO.buscarTodos();
        
        for (Curso curso : listaCurso) 
        {
            comboCurso.addItem(curso.getNome());
        }
    }
   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelExcluirCurso = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaOrganizadorSelecionado = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textPesquisar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboCurso = new javax.swing.JComboBox<>();
        botaoSelecionar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaOrganizador = new javax.swing.JTable();
        botaoCancelar = new javax.swing.JButton();
        botaoFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 300));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Organizadores Selecionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N

        labelExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirCursoMouseClicked(evt);
            }
        });

        tabelaOrganizadorSelecionado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tabelaOrganizadorSelecionado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelExcluirCurso))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelExcluirCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Organizador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setText("Pesquisar:");

        textPesquisar.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel2.setText("Curso:");

        comboCurso.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        comboCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        comboCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCursoActionPerformed(evt);
            }
        });

        botaoSelecionar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoSelecionar.setText("Selecionar");
        botaoSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSelecionarActionPerformed(evt);
            }
        });

        tabelaOrganizador.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabelaOrganizador);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoSelecionar)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoSelecionar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        botaoCancelar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoFinalizar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoFinalizar.setText("Finalizar");
        botaoFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoFinalizar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoFinalizar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean verificarExistencia(String prontuario)
    {     
        for (int i = 0; i < tabelaOrganizadorSelecionado.getRowCount(); i++) 
        {
            if(tabelaOrganizadorSelecionado.getValueAt(i, 0).equals(prontuario))
            {
                return true;
            }
            
        }
        
        return false;
    }
    
    private void botaoSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSelecionarActionPerformed
       
       DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizadorSelecionado.getModel();

        int linhas[] = tabelaOrganizador.getSelectedRows();

        if(linhas.length == 0)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            for (int i = 0; i < linhas.length; i++)
            {
                if(!verificarExistencia((String) tabelaOrganizador.getValueAt(linhas[i], 0)))
                {
                    modelo.addRow(new String[]
                    {
                        (String) tabelaOrganizador.getValueAt(linhas[i], 0),
                        (String) tabelaOrganizador.getValueAt(linhas[i], 1),
                        (String) tabelaOrganizador.getValueAt(linhas[i], 2)

                    });
                }
                
                }
                
            }

    }//GEN-LAST:event_botaoSelecionarActionPerformed

    private void botaoFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizarActionPerformed
            
        for (int i = 0; i < tabelaOrganizadorSelecionado.getRowCount(); i++) 
        {
       
            Organizador organizador = new Organizador();
            Curso curso = new Curso();
            
            curso = cursoDAO.buscarCurso((String)tabelaOrganizadorSelecionado.getValueAt(i, 2));
            organizador.setNome((String) tabelaOrganizadorSelecionado.getValueAt(i, 1));
            organizador.setCurso(curso);
            organizador.setIdOrganizador(i+1);  

            listaOrganizador.add(organizador);
        }
    
      this.dispose();
    }//GEN-LAST:event_botaoFinalizarActionPerformed

    private void labelExcluirCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirCursoMouseClicked
        
        DefaultTableModel modelo = (DefaultTableModel) tabelaOrganizadorSelecionado.getModel();
        int[] linhas = tabelaOrganizadorSelecionado.getSelectedRows();

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

    private void comboCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCursoActionPerformed
        
        
    }//GEN-LAST:event_comboCursoActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
       
        cancelar = true;
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoFinalizar;
    private javax.swing.JButton botaoSelecionar;
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelExcluirCurso;
    private javax.swing.JTable tabelaOrganizador;
    private javax.swing.JTable tabelaOrganizadorSelecionado;
    private javax.swing.JTextField textPesquisar;
    // End of variables declaration//GEN-END:variables
}
