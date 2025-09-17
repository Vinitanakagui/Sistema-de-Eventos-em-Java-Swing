package visao.evento;

import controle.CursoDAO;
import controle.ParticipanteDAO;
import static java.awt.Color.RED;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Evento;
import modelo.Participante;
import modelo.Responsavel;
import utils.DateUtils;
import utils.JOptionPaneUtils;
import utils.ValidarCampo;

public class TelaAdicionarEvento extends javax.swing.JDialog {

 
    private Evento evento;
    private List<Curso> listaCurso;
    private CursoDAO cursoDAO;
    private ParticipanteDAO participanteDAO;
    private List<Responsavel> listaResponsavel;
    private List<Participante> listaParticipante;

    
    public TelaAdicionarEvento(java.awt.Frame parent, boolean modal, Evento evento) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.listaCurso = new ArrayList<>();
        this.cursoDAO = new CursoDAO();
        this.participanteDAO = new ParticipanteDAO();
        this.listaParticipante = new ArrayList<>();
        this.listaResponsavel = new ArrayList<>();
        
        ajustarSpinner();
                
        if(evento == null)
        {
            this.evento = new Evento();    
        }
        else
        {
            this.evento = evento;
            carregarComponentesEditar();
        }
        
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
        
        preencherComboCurso();
        preencherTabelaRepresentante();
        ajustarColunas();
                
    }

    public Evento getEvento()
    {
        return evento;
    }
    
    private void ajustarColunas()
    {
        DefaultTableCellRenderer centralizeRenderer = new DefaultTableCellRenderer();
        centralizeRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer leftAlignRenderer = new DefaultTableCellRenderer();
        leftAlignRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        tabelaRepresentante.getColumnModel().getColumn(1).setCellRenderer(leftAlignRenderer);
        tabelaRepresentante.getColumnModel().getColumn(0).setCellRenderer(centralizeRenderer);
        tabelaRepresentante.getColumnModel().getColumn(2).setCellRenderer(leftAlignRenderer);
        tabelaRepresentantesAdicionados.getColumnModel().getColumn(1).setCellRenderer(leftAlignRenderer);
        tabelaRepresentantesAdicionados.getColumnModel().getColumn(0).setCellRenderer(centralizeRenderer);
        tabelaRepresentantesAdicionados.getColumnModel().getColumn(2).setCellRenderer(leftAlignRenderer);

            
            
    }
    private void carregarComponentesEditar()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaRepresentantesAdicionados.getModel();
        modelo.setRowCount(0);
        
        List<Responsavel> listaAux = evento.getListaResponsavel();
        
        for (Responsavel responsavel : listaAux) 
        {
            modelo.addRow(new String[]
            {
                responsavel.getParticipante().getProntuario(),
                responsavel.getParticipante().getNome(),
                responsavel.getParticipante().getCurso().getNome()
            
            
            });
        }
        
        botaoFinalizarEditar.setText("Editar");
        
        textNome.setText(evento.getTitulo());
        textData.setDate(DateUtils.asDate(evento.getData()));
        spinnerCargaHoraria.setValue(evento.getCargaHoraria());
        spinnerTotalVagas.setValue(evento.getNumeroVagas());
        
    }
    private void preencherTabelaRepresentante()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaRepresentante.getModel();
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
    
    private void ajustarSpinner()
    {
        SpinnerNumberModel modeloHora = new SpinnerNumberModel(1, 1,24, 1);
        SpinnerNumberModel modeloVagas = new SpinnerNumberModel(1, 1, 100, 1);
        
        spinnerCargaHoraria.setModel(modeloHora);
        spinnerTotalVagas.setModel(modeloVagas);
    }
    private void pesquisar()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaRepresentante.getModel();
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
        
        ajustarColunas();
    }
    
    private void preencherComboCurso()
    {
        listaCurso = cursoDAO.buscarTodos();
        
        for (Curso curso : listaCurso) 
        {
            comboCurso.addItem(curso.getNome());
        }
        
        comboCurso.setPreferredSize(new Dimension(200, 28));

    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        textNome = new javax.swing.JTextField();
        textData = new com.toedter.calendar.JDateChooser();
        labelNome = new javax.swing.JLabel();
        labelNome3 = new javax.swing.JLabel();
        labelNome1 = new javax.swing.JLabel();
        spinnerCargaHoraria = new javax.swing.JSpinner();
        labelNome2 = new javax.swing.JLabel();
        spinnerTotalVagas = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaRepresentante = new javax.swing.JTable();
        textPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboCurso = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        botaoSelecionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        labelExcluirCurso = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaRepresentantesAdicionados = new javax.swing.JTable();
        botaoCancelar = new javax.swing.JButton();
        botaoFinalizarEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        panelPrincipal.add(textNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 30, 477, -1));

        textData.setDateFormatString("d'/'M'/'y");
        textData.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        textData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textDataMouseClicked(evt);
            }
        });
        panelPrincipal.add(textData, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 30, 140, -1));

        labelNome.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome.setText("Nome: ");
        panelPrincipal.add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 31, -1, -1));

        labelNome3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome3.setText("Data:");
        panelPrincipal.add(labelNome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 31, -1, -1));

        labelNome1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome1.setText("Carga Horária: ");
        panelPrincipal.add(labelNome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 77, -1, -1));

        spinnerCargaHoraria.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        panelPrincipal.add(spinnerCargaHoraria, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 76, -1, -1));

        labelNome2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome2.setText("Total Vagas:");
        panelPrincipal.add(labelNome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 77, -1, -1));

        spinnerTotalVagas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        panelPrincipal.add(spinnerTotalVagas, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 76, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Representante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(856, 362));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaRepresentante.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaRepresentante);
        if (tabelaRepresentante.getColumnModel().getColumnCount() > 0) {
            tabelaRepresentante.getColumnModel().getColumn(0).setMinWidth(100);
            tabelaRepresentante.getColumnModel().getColumn(0).setMaxWidth(100);
            tabelaRepresentante.getColumnModel().getColumn(2).setMinWidth(200);
            tabelaRepresentante.getColumnModel().getColumn(2).setMaxWidth(200);
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

        panelPrincipal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 141, 860, 340));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Representantes Adicionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N
        jPanel5.setMaximumSize(new java.awt.Dimension(856, 362));

        labelExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirCursoMouseClicked(evt);
            }
        });

        tabelaRepresentantesAdicionados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabelaRepresentantesAdicionados);
        if (tabelaRepresentantesAdicionados.getColumnModel().getColumnCount() > 0) {
            tabelaRepresentantesAdicionados.getColumnModel().getColumn(0).setMinWidth(100);
            tabelaRepresentantesAdicionados.getColumnModel().getColumn(0).setMaxWidth(100);
            tabelaRepresentantesAdicionados.getColumnModel().getColumn(2).setMinWidth(200);
            tabelaRepresentantesAdicionados.getColumnModel().getColumn(2).setMaxWidth(200);
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

        panelPrincipal.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 499, 860, -1));

        botaoCancelar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });
        panelPrincipal.add(botaoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 804, -1, -1));

        botaoFinalizarEditar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoFinalizarEditar.setText("Finalizar");
        botaoFinalizarEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarEditarActionPerformed(evt);
            }
        });
        panelPrincipal.add(botaoFinalizarEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 804, -1, -1));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 849));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textDataMouseClicked

        LineBorder borda = (LineBorder) textData.getBorder();

        if(borda.getLineColor().equals(RED))
        {
            textData.setBorder(BorderFactory.createEmptyBorder());
        }
    }//GEN-LAST:event_textDataMouseClicked

    private boolean verificarExistencia(String prontuario)
    {     
        for (int i = 0; i < tabelaRepresentantesAdicionados.getRowCount(); i++) 
        {
            if(tabelaRepresentantesAdicionados.getValueAt(i, 0).equals(prontuario))
            {
                return true;
            }
            
        }
        
        return false;
    }
    private void botaoSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSelecionarActionPerformed

        DefaultTableModel modelo = (DefaultTableModel) tabelaRepresentantesAdicionados.getModel();

        int linhas[] = tabelaRepresentante.getSelectedRows();

        if(linhas.length == 0)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            for (int i = 0; i < linhas.length; i++)
            {
                if(!verificarExistencia((String) tabelaRepresentante.getValueAt(linhas[i], 0)))
                {
                    modelo.addRow(new String[]
                    {
                        (String) tabelaRepresentante.getValueAt(linhas[i], 0),
                        (String) tabelaRepresentante.getValueAt(linhas[i], 1),
                        (String) tabelaRepresentante.getValueAt(linhas[i], 2)

                    });
                }
                
                }
                
            }

    }//GEN-LAST:event_botaoSelecionarActionPerformed

    private void labelExcluirCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirCursoMouseClicked

        DefaultTableModel modelo = (DefaultTableModel) tabelaRepresentantesAdicionados.getModel();
        int[] linhas = tabelaRepresentantesAdicionados.getSelectedRows();

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
       
        if(validarCampos())
        { 
            preencherListaResponsavel();
            evento.setCargaHoraria((Integer) spinnerCargaHoraria.getValue());
            evento.setData(DateUtils.asLocalDate(textData.getDate()));
            evento.setTitulo(textNome.getText());
            evento.setListaMatricula(null);
            evento.setListaResponsavel(listaResponsavel); 
            evento.setNumeroVagas((Integer) spinnerTotalVagas.getValue());
            
   
            this.dispose();
        }
        
        
    }//GEN-LAST:event_botaoFinalizarEditarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        
        evento = null;
        
        dispose();
     
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private boolean validarCampos()
    {
        boolean resultado = true;
        
        if(!ValidarCampo.validarTextField(textNome))
        {
            resultado = false;
        }
        
        if(!ValidarCampo.validarDataChooser(textData))
        {
            resultado = false;
        }
        
        if(tabelaRepresentantesAdicionados.getRowCount() == -1)
        {
            JOptionPaneUtils.atencao("Selecione ao menos um representante!", "Atenção");
            
            resultado = false;
        }
        
        return resultado;
    }
    
    private void preencherListaResponsavel() 
    {
        Participante participante;
        Responsavel responsavel;

        for (int i = 0; i < tabelaRepresentantesAdicionados.getRowCount(); i++) 
        {
           
            participante = participanteDAO.buscarParticipante((String) tabelaRepresentantesAdicionados.getValueAt(i, 0));

            responsavel = new Responsavel();
            responsavel.setEvento(evento);
            responsavel.setParticipante(participante);
            responsavel.setIdResponsavel(i+1);  

            listaResponsavel.add(responsavel);
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoFinalizarEditar;
    private javax.swing.JButton botaoSelecionar;
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelExcluirCurso;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JLabel labelNome3;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JSpinner spinnerCargaHoraria;
    private javax.swing.JSpinner spinnerTotalVagas;
    private javax.swing.JTable tabelaRepresentante;
    private javax.swing.JTable tabelaRepresentantesAdicionados;
    private com.toedter.calendar.JDateChooser textData;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textPesquisar;
    // End of variables declaration//GEN-END:variables
}
