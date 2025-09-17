package visao.dados;

import controle.AdministradorDAO;
import java.awt.Frame;
import javax.swing.JOptionPane;
import modelo.Administrador;
import utils.JOptionPaneUtils;


public class TelaDadosAdministrador extends javax.swing.JDialog {

    private Administrador  administrador;
    private AdministradorDAO administradorDAO;
    
    public TelaDadosAdministrador(java.awt.Frame parent, boolean modal, Administrador administrador) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.administrador = administrador;
        this.administradorDAO = new AdministradorDAO();
        carregarComponentes();
        
        
    }

   private void carregarComponentes()
   {
      textNome.setText(administrador.getLogin());
      textSenha.setText(administrador.getSenha());
   }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textSenha = new javax.swing.JPasswordField();
        separadorAtividades = new javax.swing.JSeparator();
        labelTituloTabela = new javax.swing.JLabel();
        labelVoltar = new javax.swing.JLabel();
        botaoEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel2.setText("Nome:");

        textNome.setEditable(false);
        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel11.setText("Senha:");

        textSenha.setEditable(false);
        textSenha.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        labelTituloTabela.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloTabela.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelTituloTabela.setText("Meus Dados");

        labelVoltar.setBackground(new java.awt.Color(255, 255, 255));
        labelVoltar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/voltar.png"))); // NOI18N
        labelVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelVoltarMouseClicked(evt);
            }
        });

        botaoEditar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        botaoEditar.setText("Alterar Senha");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTituloTabela)
                    .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoEditar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelVoltar)
                    .addComponent(labelTituloTabela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorAtividades, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(botaoEditar)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelVoltarMouseClicked

        dispose();
    }//GEN-LAST:event_labelVoltarMouseClicked

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed

         
    TelaAlterarSenha telaAlterarSenha = new TelaAlterarSenha((Frame) this.getParent(), true, null, administrador);
    telaAlterarSenha.setVisible(true);
    textSenha.setText(administrador.getSenha());
            

    }//GEN-LAST:event_botaoEditarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelTituloTabela;
    private javax.swing.JLabel labelVoltar;
    private javax.swing.JSeparator separadorAtividades;
    private javax.swing.JTextField textNome;
    private javax.swing.JPasswordField textSenha;
    // End of variables declaration//GEN-END:variables
}
