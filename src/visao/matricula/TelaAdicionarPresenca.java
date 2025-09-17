package visao.matricula;

import controle.MatriculaDAO;
import controle.PresencaDAO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Evento;
import modelo.Matricula;
import modelo.Presenca;
import modelo.enuns.Status;
import utils.JOptionPaneUtils;


public class TelaAdicionarPresenca extends javax.swing.JDialog {

    private Evento evento;
    private List<Matricula> listaMatricula;
    private List<Presenca> listaPresenca;
    private PresencaDAO presencaDAO;
    private MatriculaDAO matriculaDAO;
    private List<Matricula> listaMatriculaAux;
    private boolean cancelar;
            
    public TelaAdicionarPresenca(java.awt.Frame parent, boolean modal, Evento evento)
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.listaMatricula = new ArrayList<>();
        this.listaPresenca = new ArrayList<>();
        this.presencaDAO = new PresencaDAO();
        this.evento = evento;
        this.listaMatricula = evento.getListaMatricula();
        this.matriculaDAO = new MatriculaDAO();
        this.listaMatriculaAux = new ArrayList<>();
        
        preencherTabelaPresenca();
        ajustarSpinner();
        
        tabelaPresenca.getColumnModel().getColumn(2).setCellRenderer(tabelaPresenca.getDefaultRenderer(Boolean.class));
        tabelaPresenca.getColumnModel().getColumn(2).setCellEditor(tabelaPresenca.getDefaultEditor(Boolean.class));
        
        ajustarColunas();
 
    }
    
    private void ajustarColunas()
    {
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaPresencaLancada.getColumnModel().getColumn(0).setCellRenderer(centro);      
        tabelaPresencaLancada.getColumnModel().getColumn(2).setCellRenderer(centro);       
    }

    public boolean isCancelar()
    {
        return cancelar;
    }

    public List<Matricula> getListaMatricula()
    {
        return listaMatricula;
    }
    
    public int retornarHoras(Matricula matricula)
    {
        int quantidade = 0;
        
        for (Presenca presenca : matricula.getListaPresenca())
        {
            quantidade += presenca.getQuantidadeHoras();
        }
        
        return quantidade;
    }
    private void preencherTabelaPresenca()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaPresenca.getModel();
        modelo.setRowCount(0);
        Integer horas;
       
        for (Matricula matricula : listaMatricula) 
        {
            horas = evento.getCargaHoraria() - retornarHoras(matricula);
            
            if(!matricula.getStatus().equals(Status.P))
            {
                modelo.addRow(new String[]
                {
                    matricula.getParticipante().getNome(),
                    String.valueOf(horas)

                });
            }
            
        }
    
    }
    private void ajustarSpinner()
    {
        int quantidadeHoras = evento.getCargaHoraria();
        
        SpinnerNumberModel modeloHora = new SpinnerNumberModel(1, 1,quantidadeHoras, 1);
        SpinnerNumberModel modeloVagas = new SpinnerNumberModel(1, 1, 5, 1);
        
        spinnerHora.setModel(modeloHora);
        spinnerPresenca.setModel(modeloVagas);
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPresencaLancada = new javax.swing.JTable();
        labelExcluirPresenca = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelNome3 = new javax.swing.JLabel();
        spinnerHora = new javax.swing.JSpinner();
        labelNome4 = new javax.swing.JLabel();
        spinnerPresenca = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaPresenca = new javax.swing.JTable();
        checkboxSelecionarTodos = new javax.swing.JCheckBox();
        botaoCadastrar1 = new javax.swing.JButton();
        separadorAtividades = new javax.swing.JSeparator();
        labelTituloTabela = new javax.swing.JLabel();
        labelVoltar = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Presenças Lançadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N

        tabelaPresencaLancada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Presença", "Nome", "Horas"
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
        jScrollPane1.setViewportView(tabelaPresencaLancada);
        if (tabelaPresencaLancada.getColumnModel().getColumnCount() > 0) {
            tabelaPresencaLancada.getColumnModel().getColumn(0).setMinWidth(80);
            tabelaPresencaLancada.getColumnModel().getColumn(0).setMaxWidth(80);
            tabelaPresencaLancada.getColumnModel().getColumn(2).setMinWidth(80);
            tabelaPresencaLancada.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        labelExcluirPresenca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        labelExcluirPresenca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExcluirPresenca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExcluirPresencaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelExcluirPresenca)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelExcluirPresenca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lançar Presenças", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 18))); // NOI18N

        labelNome3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome3.setText("Quantidade Horas:");

        labelNome4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome4.setText("Quantidade Presenças:");

        tabelaPresenca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Horas Restantes", "Presença"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelaPresenca);
        if (tabelaPresenca.getColumnModel().getColumnCount() > 0) {
            tabelaPresenca.getColumnModel().getColumn(1).setMinWidth(130);
            tabelaPresenca.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaPresenca.getColumnModel().getColumn(2).setMinWidth(80);
            tabelaPresenca.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        checkboxSelecionarTodos.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        checkboxSelecionarTodos.setText("Selecionar Todos");
        checkboxSelecionarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxSelecionarTodosActionPerformed(evt);
            }
        });

        botaoCadastrar1.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        botaoCadastrar1.setText("Lançar");
        botaoCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelNome3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelNome4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerPresenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(checkboxSelecionarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar1))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNome3)
                    .addComponent(spinnerPresenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNome4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkboxSelecionarTodos)
                    .addComponent(botaoCadastrar1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        botaoCadastrar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoCadastrar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(labelVoltar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelTituloTabela)
                                .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTituloTabela)
                    .addComponent(labelVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrar)
                .addContainerGap(28, Short.MAX_VALUE))
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

    private void labelVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVoltarMouseClicked

        cancelar = true;
        dispose();
    }//GEN-LAST:event_labelVoltarMouseClicked

    private int verificarExistencia(String nome)
    {
        for (int i = 0; i < tabelaPresenca.getRowCount(); i++) 
        {
            if(tabelaPresenca.getValueAt(i, 0).equals(nome))
            {
                return i;   
            }
        }
        
        return -1;
    }
    
   private void ajustarIDPresenca(String nome) 
   {
        for (int i = 0; i < tabelaPresencaLancada.getRowCount(); i++) 
        {
            if (tabelaPresencaLancada.getValueAt(i, 1).equals(nome))
            {
                tabelaPresencaLancada.setValueAt(String.valueOf(i + 1), i, 0); 
            }
        }
    
    }




    private void ajustarDadosExclusao( ArrayList<String[]> dados)
    {
         DefaultTableModel modeloPresenca = (DefaultTableModel) tabelaPresenca.getModel();
         String nome;
         int indice;
         Integer horaAtual;
         Integer horaAdiconal;
         Integer horaFinal;
         
         for (String[] dado : dados) 
         {
             nome = dado[0];
       
            ajustarIDPresenca(nome);
          
          if(tabelaPresenca.getRowCount() == 0)
          {
               modeloPresenca.addRow(new String[]
                   {
                     dado[0],
                     dado[1]
                   
                   });
          }
          else
          {
            
            indice = verificarExistencia(nome);
                
            if(indice != -1)
            {   
                horaAtual = Integer.valueOf((String) tabelaPresenca.getValueAt(indice, 1));
                horaAdiconal = Integer.valueOf((String) dado[1]);
                horaFinal = horaAtual + horaAdiconal;

                tabelaPresenca.setValueAt(String.valueOf(horaFinal), indice, 1);

            }
            else
            {
                modeloPresenca.addRow(new String[]
                {
                  dado[0],
                  dado[1]

                });
            }
            
         }
        }
    
    }
    private void labelExcluirPresencaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExcluirPresencaMouseClicked

        DefaultTableModel modeloPresencaLancada = (DefaultTableModel) tabelaPresencaLancada.getModel();
       
        int linhas[] = tabelaPresencaLancada.getSelectedRows();
        ArrayList<String[]> dados = new ArrayList<>();

        if(linhas == null)
        {
            JOptionPaneUtils.atencao("Selecione ao menos uma linha!", "Atenção");
        }
        else
        {
            Integer resposta = JOptionPaneUtils.confirmacao("Tem certeza da exclusão?", "Atenção");

            if(resposta == JOptionPane.YES_OPTION)
            {
                for(int i = 0; i < linhas.length; i++)
                {
                    String lista[] = {(String) tabelaPresencaLancada.getValueAt(linhas[i], 1),  (String) tabelaPresencaLancada.getValueAt(linhas[i], 2)};
                    dados.add(lista);              
                }
                
                 for (int i = linhas.length - 1; i >= 0; i--)
                 {
                    modeloPresencaLancada.removeRow(linhas[i]);
                 }
                 
                 ajustarDadosExclusao(dados);
            }
        }  

    }//GEN-LAST:event_labelExcluirPresencaMouseClicked

    private void limparLinhasHorasZero() 
    {  
        DefaultTableModel modeloPresenca = (DefaultTableModel) tabelaPresenca.getModel();

       
        for (int i = tabelaPresenca.getRowCount() - 1; i >= 0; i--)
        {
            if (tabelaPresenca.getValueAt(i, 1).equals("0"))
            {
                modeloPresenca.removeRow(i);
            }
        }
}

    
    private int verificarUltimoID(String nome)
    {
        int ultimoID = 0; 

        for (int i = 0; i < tabelaPresencaLancada.getRowCount(); i++) 
        {
        
            if (tabelaPresencaLancada.getValueAt(i, 1).equals(nome)) 
            {
                try
                {
                    
                    ultimoID = Integer.parseInt(tabelaPresencaLancada.getValueAt(i, 0).toString());

                }
                catch (NumberFormatException e) 
                {
                    System.err.println("Erro ao converter ID na linha " + i + ": " + e.getMessage());
                }
            }
        }
        return ultimoID;
    }
    
    private boolean verificarHora()
    {
        Integer quantidadeHoras = (Integer) spinnerHora.getValue();
        Integer quantidadePresencas = (Integer) spinnerPresenca.getValue();
        Integer totalHoras = quantidadeHoras * quantidadePresencas;
        Integer horasMatriculado;
        
        for (int i = 0; i < tabelaPresenca.getRowCount(); i++) 
        {
            Object valorChecklist = tabelaPresenca.getValueAt(i, 2);
            
            if(valorChecklist != null && valorChecklist instanceof Boolean && (Boolean) valorChecklist)
            {
                horasMatriculado = Integer.valueOf((String) tabelaPresenca.getValueAt(i, 1)); 
                
                if(totalHoras > horasMatriculado) 
                {
                    return false;
                }
            }
           
           
        }
        
        return true;
    }
    
    private void ajustarColunaPresenca()
    {
       
       if(checkboxSelecionarTodos.isSelected())
       { 
           for (int i = 0; i < tabelaPresenca.getRowCount(); i++) 
           {
               tabelaPresenca.setValueAt(true, i, 2);
           }
       }
       else
       {
          for (int i = 0; i < tabelaPresenca.getRowCount(); i++) 
           {
               tabelaPresenca.setValueAt(false, i, 2);
           } 
       }
    }
    
    private void ajustarHoras(String nome, Integer horas)
    {
        DefaultTableModel modeloPresenca = (DefaultTableModel) tabelaPresenca.getModel();
        Integer horaAtual;
        
        for (int i = 0; i < tabelaPresenca.getRowCount(); i++) 
        {
            if(tabelaPresenca.getValueAt(i, 0).equals(nome))
            {
                horaAtual = Integer.valueOf((String) tabelaPresenca.getValueAt(i, 1));
                
                horaAtual = horaAtual - horas;
                
                tabelaPresenca.setValueAt(String.valueOf(horaAtual), i, 1);
            }
        }
    }
     
     private void adicionarDadoOrdenadoTabelaPresencaLancada() 
     {
        DefaultTableModel modeloPresencaLancada = (DefaultTableModel) tabelaPresencaLancada.getModel();
       
        ArrayList<String[]> dados = new ArrayList<>();

        for (int i = 0; i < modeloPresencaLancada.getRowCount(); i++) 
        {
            String[] linha = {modeloPresencaLancada.getValueAt(i, 0).toString(), modeloPresencaLancada.getValueAt(i, 1).toString(),  modeloPresencaLancada.getValueAt(i, 2).toString()};
            dados.add(linha);
        }
        
        dados.sort(Comparator.comparing(o -> o[1]));

        modeloPresencaLancada.setRowCount(0);
        
        for (String[] linha : dados) 
        {
            modeloPresencaLancada.addRow(linha);
        }
    }


    private void preencherListaMatriculaAuxiliar(List<Matricula> listaMatriculaAux)
{      
    for (int i = 0; i < tabelaPresenca.getRowCount(); i++)
    {      
        Object valor =  tabelaPresenca.getValueAt(i, 2);
            
        if(valor instanceof Boolean && (Boolean) valor)
        {
          
            Matricula matricula = matriculaDAO.buscarPorEvento(((String) tabelaPresenca.getValueAt(i, 0)), evento.getIdEvento());
            
            listaMatriculaAux.add(matricula);
        }      
    }
    
   
}

    private void botaoCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrar1ActionPerformed
        
        DefaultTableModel modeloPresenca = (DefaultTableModel) tabelaPresenca.getModel();
        DefaultTableModel modeloPresencaLancada = (DefaultTableModel) tabelaPresencaLancada.getModel();
        
        Integer quantidadeHoras = (Integer) spinnerHora.getValue();
        Integer quantidadePresencas = (Integer) spinnerPresenca.getValue();
        Integer totalHorasCurso = evento.getCargaHoraria();
        Integer totalHora = quantidadeHoras * quantidadePresencas;
            
        if(checkboxSelecionarTodos.isSelected())
        {
            if(!verificarHora())
            {
                JOptionPaneUtils.atencao("Quantidade de horas inserida ultrapassam as horas do evento", "Atenção");
            }
            else
            {
                if(totalHora.equals(totalHorasCurso))
                {
                    for (Matricula matricula : listaMatricula) 
                    {
                        for (int i = 0; i < quantidadePresencas; i++)
                        {
                            modeloPresencaLancada.addRow(new String[]
                            {
                              String.valueOf(verificarUltimoID(matricula.getParticipante().getNome())+1),
                              matricula.getParticipante().getNome(),
                              String.valueOf(quantidadeHoras)
                                    
                            });
                        }
                    }
                    
                    adicionarDadoOrdenadoTabelaPresencaLancada();
                    modeloPresenca.setRowCount(0);
                }
                else
                {
                    for (Matricula matricula : listaMatricula) 
                    {
                        for (int i = 0; i < quantidadePresencas; i++) 
                        {
                            modeloPresencaLancada.addRow(new String[]
                            {
                              String.valueOf(verificarUltimoID(matricula.getParticipante().getNome())+1),
                              matricula.getParticipante().getNome(),
                              String.valueOf(quantidadeHoras)
                                    
                            });
                            
                            ajustarHoras(matricula.getParticipante().getNome(), quantidadeHoras);                         
                        }
                        
                      limparLinhasHorasZero();  
                      adicionarDadoOrdenadoTabelaPresencaLancada();
                    }
                }
                    
            }
        }
        else
        {
            if(!verificarHora())
            {
                JOptionPaneUtils.atencao("Quantidade de horas inserida ultrapassam as horas do evento", "Atenção");
            }
            else
            {
                List<Matricula> listaAuxiliar = new ArrayList<>();
                preencherListaMatriculaAuxiliar(listaAuxiliar);

               if(totalHora.equals(totalHorasCurso))
                {

                    for (Matricula matricula : listaAuxiliar) 
                    {
                        for (int i = 0; i < quantidadePresencas; i++)
                        {
                            modeloPresencaLancada.addRow(new String[]
                            {
                              String.valueOf(verificarUltimoID(matricula.getParticipante().getNome())+1),
                              matricula.getParticipante().getNome(),
                              String.valueOf(quantidadeHoras)

                            });

                           ajustarHoras(matricula.getParticipante().getNome(), quantidadeHoras);
                        }
                    }
                    
                    adicionarDadoOrdenadoTabelaPresencaLancada();
                    limparLinhasHorasZero();


                }
                else
                {
                    for (Matricula matricula : listaAuxiliar) 
                    {
                        for (int i = 0; i < quantidadePresencas; i++) 
                        {
                            modeloPresencaLancada.addRow(new String[]
                            {
                              String.valueOf(verificarUltimoID(matricula.getParticipante().getNome())+1),
                              matricula.getParticipante().getNome(),
                              String.valueOf(quantidadeHoras)

                            });

                            ajustarHoras(matricula.getParticipante().getNome(), quantidadeHoras);                         
                        }

                      limparLinhasHorasZero();  
                      adicionarDadoOrdenadoTabelaPresencaLancada();
                    }
                }
            }
            
        }
        
       if(modeloPresenca.getRowCount() == 0)
       {
           if(checkboxSelecionarTodos.isSelected())
            {
                checkboxSelecionarTodos.setSelected(false);
            }
       }
        
    }//GEN-LAST:event_botaoCadastrar1ActionPerformed

    private void checkboxSelecionarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxSelecionarTodosActionPerformed
       
        ajustarColunaPresenca();
    }//GEN-LAST:event_checkboxSelecionarTodosActionPerformed

    private void ajustarStatusMatricula(Matricula matricula)
    {
        Integer totalHoras = 0;
        
        System.out.println("Matricula: " + matricula.getListaPresenca().size());
        
        for (Presenca presenca : matricula.getListaPresenca())
        {
            totalHoras += presenca.getQuantidadeHoras();
        }
        
        if(totalHoras.equals(evento.getCargaHoraria()))
        {
            matricula.setStatus(Status.P);
        }
        else
        {
            matricula.setStatus(Status.A);
        }
    }
    
    private void ajustarListaMatricula()
    {    
        for (Matricula matriculaAux : listaMatriculaAux) 
        {
            for (Matricula matricula : listaMatricula) 
            {
                if(matriculaAux.getParticipante().getNome().equals(matricula.getParticipante().getNome()))
                {
                    matricula.setListaPresenca(matriculaAux.getListaPresenca());
                    matricula.setStatus(matriculaAux.getStatus());
                    break;
                }
            }
        }
       
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        
       
        if (tabelaPresencaLancada.getRowCount() == 0) 
        {
             JOptionPaneUtils.atencao("Lance ao menos uma presença", "Atenção");
             return;
         }

        for (int i = 0; i < tabelaPresencaLancada.getRowCount();) 
        {
            String nomeAtual = (String) tabelaPresencaLancada.getValueAt(i, 1);
            Matricula matricula = matriculaDAO.buscarPorEvento(nomeAtual, evento.getIdEvento());
            
            if (matricula == null)
            {
                JOptionPaneUtils.erro("Matrícula não encontrada para " + nomeAtual, "Erro");
                return;
            }

             List<Presenca> listaPresenca = new ArrayList<>();
             
             do 
             {
                    try 
                    {
                        Presenca presenca = new Presenca();
                        presenca.setData(evento.getData());
                        presenca.setIdPresenca(presencaDAO.buscarUltimoID(matricula.getIdMatricula())+1);
                        presenca.setQuantidadeHoras(Integer.parseInt((String) tabelaPresencaLancada.getValueAt(i, 2)));
                        presenca.setMatricula(matricula);
                        listaPresenca.add(presenca);
                        i++; 
                    } 
                    catch (NumberFormatException e) 
                    {
                        JOptionPaneUtils.erro("Erro ao converter valores na linha " + (i + 1), "Erro de Formato");
                        return;
                    }
            } while (i < tabelaPresencaLancada.getRowCount() && nomeAtual.equals((String) tabelaPresencaLancada.getValueAt(i, 1)));

            if(!matricula.getListaPresenca().isEmpty())
            {
               matricula.getListaPresenca().addAll(listaPresenca);
            }
            else
            {
                matricula.setListaPresenca(listaPresenca); 
            }
           
            ajustarStatusMatricula(matricula);
            matriculaDAO.alterar(matricula);
            listaMatriculaAux.add(matricula);
    }

        JOptionPaneUtils.sucesso("Presenças lançadas com sucesso!", "Sucesso");
        ajustarListaMatricula();
        dispose();
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCadastrar1;
    private javax.swing.JCheckBox checkboxSelecionarTodos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelExcluirPresenca;
    private javax.swing.JLabel labelNome3;
    private javax.swing.JLabel labelNome4;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelVoltar;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JSpinner spinnerHora;
    private javax.swing.JSpinner spinnerPresenca;
    private javax.swing.JTable tabelaPresenca;
    private javax.swing.JTable tabelaPresencaLancada;
    // End of variables declaration//GEN-END:variables
}
