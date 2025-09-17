package visao.dados;

import com.formdev.flatlaf.FlatLightLaf;
import controle.CursoDAO;
import controle.ParticipanteDAO;
import java.awt.Color;
import java.awt.Frame;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import modelo.Curso;
import modelo.Participante;
import utils.JOptionPaneUtils;




public class TelaDados extends javax.swing.JDialog {

    private boolean statusCPF;
    private boolean statusCEP;
    private boolean statusCampos;
    private ParticipanteDAO participanteDAO;
    private CursoDAO cursoDAO;
    private Curso curso;
    private Participante participante;
    private Boolean modoEditar;
    
    public TelaDados(java.awt.Frame parent, boolean modal, Participante participante) 
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.statusCPF = true;
        this.statusCEP = true;
        this.participanteDAO = new ParticipanteDAO();
        this.statusCampos = true;
        this.cursoDAO = new CursoDAO();
        this.curso = null;
        this.participante = participante;
        this.modoEditar = false;
        
        ((MaskFormatter)textProntuario.getFormatter()).setAllowsInvalid(false);  
        ((MaskFormatter)textCPF.getFormatter()).setAllowsInvalid(false);
        ((MaskFormatter)textCEP.getFormatter()).setAllowsInvalid(false);
        ((MaskFormatter)textTelefone.getFormatter()).setAllowsInvalid(false);
        
        try
        {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch(UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        
        preencherComboCurso();
        preencherCampos();
    }
    
    private void preencherComboCurso()
    {
        List<Curso> listaCurso = cursoDAO.buscarTodos();
        
        for (Curso curso : listaCurso) 
        {
            comboCurso.addItem(curso.getNome());
        }
        
    }
    
    private void preencherCampos()
    {
        textProntuario.setText(participante.getProntuario());
        textNome.setText(participante.getNome());
        textCPF.setText(participante.getCpf());
        textCEP.setText(participante.getCep());
        textEndereco.setText(participante.getEndereco());
        textCidade.setText(participante.getCidade());
        comboUF.setSelectedItem(participante.getUf());
        textEmail.setText(participante.getEmail());
        textTelefone.setText(participante.getTelefone());
        textUsuario.setText(participante.getUsario());
        textSenha.setText(participante.getSenha());
  
        if(participante.getCurso() == null)
        {
            comboCurso.setSelectedItem("Nenhum");
        }
        else
        {
             comboCurso.setSelectedItem(participante.getCurso().getNome());
        }
        
        comboUF.setSelectedItem(participante.getUf());
        
    }
    
    private void validarCEP()
    {
        String CEP = textCEP.getText();
        CEP = CEP.replaceAll("[^0-9]", "");
        
        if(CEP.length() != 8)
        {
            this.statusCPF = false;
        }
        else
        {
            this.statusCEP = true;
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textProntuario = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textCPF = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textCEP = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        textCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboUF = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textTelefone = new javax.swing.JFormattedTextField();
        textUsuario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textSenha = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        comboCurso = new javax.swing.JComboBox<>();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel1.setText("Prontuário:");

        textProntuario.setEditable(false);
        try {
            textProntuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textProntuario.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel2.setText("Nome:");

        textNome.setEditable(false);
        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel3.setText("Usuário:");

        textCPF.setEditable(false);
        try {
            textCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textCPF.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        textCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCPFActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel4.setText("CPF:");

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel5.setText("CEP:");

        textEndereco.setEditable(false);
        textEndereco.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel6.setText("Endereço:");

        textCEP.setEditable(false);
        try {
            textCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textCEP.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel7.setText("Cidade:");

        textCidade.setEditable(false);
        textCidade.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel8.setText("UF:");

        comboUF.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        comboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        comboUF.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel9.setText("Email:");

        textEmail.setEditable(false);
        textEmail.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel10.setText("Telefone:");

        textTelefone.setEditable(false);
        try {
            textTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textTelefone.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        textUsuario.setEditable(false);
        textUsuario.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel11.setText("Senha:");

        textSenha.setEditable(false);
        textSenha.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        textSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textSenhaMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel12.setText("Curso:");

        comboCurso.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        comboCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhum" }));
        comboCurso.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(comboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(textCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(32, 32, 32)
                        .addComponent(textCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(textCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(textCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        botaoCadastrar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        botaoCadastrar.setText("Editar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoCancelar.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(botaoCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCadastrar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void validarCampos()
    {
        Color corBorda = Color.RED;
        
        if(!this.statusCampos)
        {
            this.statusCampos = true;
            
            textProntuario.setBorder(new LineBorder(Color.BLACK,1));
            textNome.setBorder(new LineBorder(Color.BLACK,1));
            textCPF.setBorder(new LineBorder(Color.BLACK,1));
            textCEP.setBorder(new LineBorder(Color.BLACK,1));
            textEndereco.setBorder(new LineBorder(Color.BLACK,1));
            textCidade.setBorder(new LineBorder(Color.BLACK,1));
            comboUF.setBorder(new LineBorder(Color.BLACK,1));
            textEmail.setBorder(new LineBorder(Color.BLACK,1));
            textTelefone.setBorder(new LineBorder(Color.BLACK,1));
            textUsuario.setBorder(new LineBorder(Color.BLACK,1));
            textSenha.setBorder(new LineBorder(Color.BLACK,1));

        }
        
        if(!textProntuario.isEditValid())
        {
           this.statusCampos = false;
           textProntuario.setBorder(new LineBorder(corBorda,1));
          
        }
        if(textNome.getText().isEmpty())
        {
            this.statusCampos = false;
            textNome.setBorder(new LineBorder(corBorda,1));
        }
        if(!textCPF.isEditValid())
        {
            this.statusCampos = false;
            textCPF.setBorder(new LineBorder(corBorda,1));
        }
        if(textCEP.getText().isEmpty())
        {
            this.statusCampos = false;
            textCEP.setBorder(new LineBorder(corBorda,1));
        }
        if(textEndereco.getText().isEmpty())
        {
            this.statusCampos = false;
            textEndereco.setBorder(new LineBorder(corBorda,1));
        }
        if(textCidade.getText().isEmpty())
        {
            this.statusCampos = false;
            textCidade.setBorder(new LineBorder(corBorda,1));
        }
        if(comboUF.getSelectedItem().equals("Selecione..."))
        {
            this.statusCampos = false;
            comboUF.setBorder(new LineBorder(corBorda,1));
        }
        if(textEmail.getText().isEmpty())
        {
            this.statusCampos = false;
            textEmail.setBorder(new LineBorder(corBorda,1));
        }
        if(!textTelefone.isEditValid())
        {
            this.statusCampos = false;
            textTelefone.setBorder(new LineBorder(corBorda,1));
        }
        if(textUsuario.getText().isEmpty())
        {
            this.statusCampos = false;
            textUsuario.setBorder(new LineBorder(corBorda,1));
        }
        if(textSenha.getPassword().length == 0)
        {
            this.statusCampos = false;
            textSenha.setBorder(new LineBorder(corBorda,1));
        }
        
    }
    
    private void editarComponentes()
    {
        botaoCadastrar.setText("Alterar");
        textNome.setEditable(true);
        textCEP.setEditable(true);
        textEndereco.setEditable(true);
        textCidade.setEditable(true);
        comboUF.setEnabled(true);
        textEmail.setEditable(true);
        textEmail.setEditable(true);
        textUsuario.setEditable(true);
        comboCurso.setEditable(true);
        textTelefone.setEditable(true);
        
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        
        if(botaoCadastrar.getText().equals("Alterar"))
        {         
            validarCEP();
            validarCampos();

            if(!this.statusCEP)
            {
                JOptionPaneUtils.atencao("Digite um CEP válido!", "Atenção");
            }

            if(!this.statusCPF)
            {
                JOptionPaneUtils.atencao("Digite um CPF válido!", "Atenção");
            }

            if(this.statusCEP && this.statusCPF && statusCampos)
            {

                String prontuario = textProntuario.getText();
                String nome = textNome.getText();
                String cpf = textCPF.getText();
                String cep = textCEP.getText();
                String endereco = textEndereco.getText();
                String cidade = textCidade.getText();
                String uf = (String) comboUF.getSelectedItem();
                String email = textEmail.getText();
                String telefone = textTelefone.getText();
                String usuario = textUsuario.getText();

                participante.setProntuario(prontuario);
                participante.setNome(nome);
                participante.setCpf(cpf);
                participante.setCep(cep);
                participante.setEndereco(endereco);
                participante.setCidade(cidade);
                participante.setUf(uf);
                participante.setEmail(email);
                participante.setTelefone(telefone);
                participante.setUsario(usuario);

                if(comboCurso.getSelectedItem() == "Nenhum")
                {
                    participante.setCurso(this.curso);
                }
                else
                {
                    this.curso = cursoDAO.buscarCurso((String) comboCurso.getSelectedItem());
                    participante.setCurso(curso);
                }

                participanteDAO.alterar(participante);

                JOptionPaneUtils.sucesso("Usuário Cadastrado com Sucesso!", "Atenção");
                dispose();

            }
            
            modoEditar = false;
        }
        else
        {
            editarComponentes();
            modoEditar = true;
        }
        
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed

        dispose();
        
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void textCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCPFActionPerformed

    private void textSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSenhaMouseClicked
        
        if(modoEditar)
        {
            int resposta = JOptionPaneUtils.confirmacao("Deseja alterar sua senha?", "Alterar senha");
            
            if(resposta == JOptionPane.YES_OPTION)
            {
                TelaAlterarSenha telaAlterarSenha = new TelaAlterarSenha((Frame) this.getParent(), true, participante, null);
                telaAlterarSenha.setVisible(true);
                textSenha.setText(participante.getSenha());
            }
        }
    }//GEN-LAST:event_textSenhaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.JComboBox<String> comboUF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JFormattedTextField textCEP;
    private javax.swing.JFormattedTextField textCPF;
    private javax.swing.JTextField textCidade;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textNome;
    private javax.swing.JFormattedTextField textProntuario;
    private javax.swing.JPasswordField textSenha;
    private javax.swing.JFormattedTextField textTelefone;
    private javax.swing.JTextField textUsuario;
    // End of variables declaration//GEN-END:variables
}
