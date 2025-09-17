package visao.home;

import com.formdev.flatlaf.FlatLightLaf;
import controle.AdministradorDAO;
import controle.ParticipanteDAO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Administrador;
import modelo.Participante;
import utils.JOptionPaneUtils;


public class TelaLogin extends javax.swing.JFrame {

    private static Participante participante;
    private static Administrador administrador;
    private AdministradorDAO administradorDAO;
    private ParticipanteDAO participanteDAO;
    
    public TelaLogin() 
    {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
       
        this.administradorDAO = new AdministradorDAO();
        this.participanteDAO = new ParticipanteDAO();
  
    }

    public Participante getParticipante() {
        return participante;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelPrincipal = new javax.swing.JPanel();
        logoIF = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        botaoEntrar = new javax.swing.JButton();
        textUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelRedefinirSenha = new javax.swing.JLabel();
        labelPrimeiroAcesso = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textSenha = new javax.swing.JPasswordField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 909, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        logoIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/LogoIF.png"))); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(0, 153, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        botaoEntrar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        botaoEntrar.setText("Entrar");
        botaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEntrarActionPerformed(evt);
            }
        });

        textUser.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel1.setText("User:");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel2.setText("Senha:");

        labelRedefinirSenha.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        labelRedefinirSenha.setText("Esqueci minha senha");
        labelRedefinirSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelRedefinirSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRedefinirSenhaMouseClicked(evt);
            }
        });

        labelPrimeiroAcesso.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        labelPrimeiroAcesso.setText("Primeiro Acesso");
        labelPrimeiroAcesso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelPrimeiroAcesso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPrimeiroAcessoMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel5.setText("Entrar");

        textSenha.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(logoIF)
                .addGap(35, 35, 35)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textUser, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                    .addComponent(textSenha)))
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(botaoEntrar))
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPrimeiroAcesso)
                                    .addComponent(labelRedefinirSenha))))
                        .addContainerGap(84, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(264, 264, 264))))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(49, 49, 49)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(botaoEntrar)
                .addGap(157, 157, 157)
                .addComponent(labelRedefinirSenha)
                .addGap(18, 18, 18)
                .addComponent(labelPrimeiroAcesso)
                .addGap(24, 24, 24))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(logoIF)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(panelPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelPrimeiroAcessoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPrimeiroAcessoMouseClicked
       
        TelaCadastrarUsuario telaCadastrarUsuario = new TelaCadastrarUsuario(this, true);
        telaCadastrarUsuario.setVisible(true);
    }//GEN-LAST:event_labelPrimeiroAcessoMouseClicked

    private void botaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEntrarActionPerformed
       
        String login = textUser.getText().trim();
        String senha = String.valueOf(textSenha.getPassword());
        
        this.administrador = administradorDAO.buscarLogin(login, senha);
        this.participante = participanteDAO.buscarLogin(login, senha);
        
        if(administrador == null && participante == null)
        {
            JOptionPane.showMessageDialog(this, "Usuário e/ou senha incorretos", "Atenção!", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            
           this.setVisible(false);
            
            if(administrador == null)
            {
                TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal(this, true, participante, null);
                telaMenuPrincipal.setVisible(true);
            }
            else
            {
                TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal(this, true, null, administrador);
                telaMenuPrincipal.setVisible(true);
            }
            
            this.setVisible(true);
            textSenha.setText("");
            textUser.setText("");
        }
        
        
    }//GEN-LAST:event_botaoEntrarActionPerformed

    private void labelRedefinirSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRedefinirSenhaMouseClicked
       
        JOptionPaneUtils.sucesso("Que pena! :(", null);
    }//GEN-LAST:event_labelRedefinirSenhaMouseClicked
    public static void main(String args[]) 
    {
        try
        {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch(UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelPrimeiroAcesso;
    private javax.swing.JLabel labelRedefinirSenha;
    private javax.swing.JLabel logoIF;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPasswordField textSenha;
    private javax.swing.JTextField textUser;
    // End of variables declaration//GEN-END:variables
}
