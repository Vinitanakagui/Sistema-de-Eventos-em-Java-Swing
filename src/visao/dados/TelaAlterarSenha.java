package visao.dados;

import controle.AdministradorDAO;
import controle.ParticipanteDAO;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import modelo.Administrador;
import modelo.Participante;
import utils.JOptionPaneUtils;

public class TelaAlterarSenha extends javax.swing.JDialog {

    private String novaSenha;
    private Participante participante;
    private ParticipanteDAO participanteDAO;
    private Administrador administrador;
    private AdministradorDAO administradorDAO;
    private String senhaAtual;
    
    public TelaAlterarSenha(java.awt.Frame parent, boolean modal, Participante participante, Administrador administrador) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.participante = participante;
        this.administrador = administrador;
        this.administradorDAO = new AdministradorDAO();
        this.participanteDAO = new ParticipanteDAO();
        
        if(participante != null)
        {
            this.senhaAtual = participante.getSenha();
        }
        else
            
        {
            this.senhaAtual = administrador.getSenha();
        }
        
        textSenha.getDocument().addDocumentListener(new DocumentListener() 
        {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                confirmarSenha();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                confirmarSenha();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                confirmarSenha();
            }
        });
        
        textConfirmaSenha.getDocument().addDocumentListener(new DocumentListener() 
        {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                confirmarSenha();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                confirmarSenha();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                confirmarSenha();
            }
        });
        
        textConfirmaSenha.setBorder(new LineBorder(Color.BLACK,1));  
        textSenha.setBorder(new LineBorder(Color.BLACK,1));  
    }

    public String getNovaSenha() 
    {
        return novaSenha;
    }

    private Boolean verificarIgualdade()
    {
         String senha1 = String.valueOf(textSenha.getPassword());
         String senha2 = String.valueOf(textConfirmaSenha.getPassword());
         
         if(senha1.equals(senha2))
        {
            return true;      
        }
         
         return false;
    }
    private void confirmarSenha()
    {
        Color vermelho = Color.RED;
        Color preto = Color.BLACK;
         
        if(verificarIgualdade())
        {
            textConfirmaSenha.setBorder(new LineBorder(preto,1));        
        }
        else
        {
            textConfirmaSenha.setBorder(new LineBorder(vermelho,1));
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textSenha = new javax.swing.JPasswordField();
        textConfirmaSenha = new javax.swing.JPasswordField();
        botaoEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alterar Senha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel1.setText("Nova Senha:");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel2.setText("Confirme Senha:");

        textSenha.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        textConfirmaSenha.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        botaoEditar.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoEditar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoEditar)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed

       if(!verificarIgualdade())
       {
           JOptionPaneUtils.atencao("Senhas diferentes!", "Atenção");
       }
       else
       {
           boolean senhaValida = false;
            
            while (!senhaValida) 
            {
                JPasswordField passwordField = new JPasswordField();
                Object[] message = {"Digite sua senha:", passwordField};

                int option = JOptionPane.showConfirmDialog
                (
                    null, 
                    message, 
                    "Autenticação", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE
                );

                if (option == JOptionPane.OK_OPTION) 
                {
                    String senhaDigitada = String.valueOf(passwordField.getPassword());
                    
                    if (senhaDigitada.equals(senhaAtual)) 
                    {
                        novaSenha = String.valueOf(textSenha.getPassword());
                        
                        JOptionPaneUtils.sucesso("Senha Alterada!", "Sucesso");
                      
                        if(participante == null)
                        {
                            administrador.setSenha(novaSenha);
                            administradorDAO.alterar(administrador);
                        }
                        else
                        {
                            participante.setSenha(novaSenha);
                            participanteDAO.alterar(participante);
                        }
                        
                        senhaValida = true;
                        dispose();
                    } 
                    else 
                    {
                        JOptionPaneUtils.atencao("Senha Incorreta!", "Erro");
                    }
                }   
                else
                {
                    JOptionPaneUtils.sucesso("Operação Cancelada!", "Cancelamento");
                    break; 
                }
           }
       }

    }//GEN-LAST:event_botaoEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField textConfirmaSenha;
    private javax.swing.JPasswordField textSenha;
    // End of variables declaration//GEN-END:variables
}
