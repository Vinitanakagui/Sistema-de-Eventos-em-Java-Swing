package visao.evento;

import controle.EventoDAO;
import controle.MatriculaDAO;
import controle.ParticipanteDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Evento;
import modelo.Matricula;
import modelo.Participante;
import modelo.Responsavel;
import modelo.enuns.Status;
import utils.DateUtils;
import utils.JOptionPaneUtils;


public class TelaInscreverEvento extends javax.swing.JDialog
{
    private Evento evento;
    private Participante participante;
    private Matricula matricula;
    private MatriculaDAO matriculaDAO;
    private EventoDAO eventoDAO;
    private ParticipanteDAO participanteDAO;
    private boolean excluir;
    private Matricula matriculaAux;
    
    public TelaInscreverEvento(java.awt.Frame parent, boolean modal, Evento evento, Participante participante)
    {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.evento = evento;
        this.participante = participante;
        this.matricula = new Matricula();
        this.matriculaDAO = new MatriculaDAO();
        this.eventoDAO = new EventoDAO();
        this.participanteDAO = new ParticipanteDAO();
        
        preencherDadosEvento();
        verificarVagas();
        preencherTabelaResponsaveis();
        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                matricula = null;
               dispose();
            }
        });
    }

    public Matricula getMatricula() 
    {
        return matricula;
    }

    public boolean isExcluir()
    {
        return excluir;
    }
    
    
    
    private void preencherTabelaResponsaveis()
    {
        DefaultTableModel modelo = (DefaultTableModel) tabelaResponsaveis.getModel();
        modelo.setRowCount(0);
        
        for (Responsavel responsavel : evento.getListaResponsavel()) 
        {
            modelo.addRow(new String[]
            {
                responsavel.getParticipante().getNome(),
                responsavel.getParticipante().getCurso().getNome()
            });
        }
    }
    
    private void verificarVagas()
    {
        if(evento.getNumeroVagas().equals(evento.getQntdeIncrito()))
        {
            JOptionPaneUtils.atencao("Evento lotado", "Atenção");
            botaoInscricao.setVisible(false);
        }
    }
    public boolean verificarInscricao()
    {
        for (Matricula matricula : participante.getListaMatricula()) 
        {
            if(matricula.getEvento().equals(evento))
            {
                matriculaAux = matricula;
                return true;
            }
        }
        
        return false;
    }
    
    public int buscarQuantidadeVagas()
    {
        return Math.abs(evento.getNumeroVagas() - evento.getQntdeIncrito());
    }
    
    public void preencherDadosEvento()
    {
        textNome.setText(evento.getTitulo());
        textData.setDate(DateUtils.asDate(evento.getData()));
        textCargaHoraria.setText(String.valueOf(evento.getCargaHoraria()));
        textVagasRestantes.setText(String.valueOf(buscarQuantidadeVagas()));
        
        if(verificarInscricao())
        {
            if(matriculaAux.getListaPresenca().isEmpty())
            {
                botaoInscricao.setText("Cancelar Inscrição");
            }
            else
            {
                botaoInscricao.setVisible(false);
            }
            
        }
       
    } 
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        labelNome1 = new javax.swing.JLabel();
        textCargaHoraria = new javax.swing.JTextField();
        labelNome2 = new javax.swing.JLabel();
        textVagasRestantes = new javax.swing.JTextField();
        labelNome3 = new javax.swing.JLabel();
        textData = new com.toedter.calendar.JDateChooser();
        botaoInscricao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaResponsaveis = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        labelNome.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome.setText("Nome: ");

        textNome.setEditable(false);
        textNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelNome1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome1.setText("Carga Horária: ");

        textCargaHoraria.setEditable(false);
        textCargaHoraria.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelNome2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome2.setText("Vagas Restantes:");

        textVagasRestantes.setEditable(false);
        textVagasRestantes.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        labelNome3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        labelNome3.setText("Data:");

        textData.setDateFormatString("d'/'M'/'y");
        textData.setEnabled(false);
        textData.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N

        botaoInscricao.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        botaoInscricao.setText("Inscrever");
        botaoInscricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInscricaoActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responsáveis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 20))); // NOI18N

        tabelaResponsaveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaResponsaveis);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoInscricao)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(labelNome)
                            .addGap(6, 6, 6)
                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelNome3)
                            .addGap(6, 6, 6)
                            .addComponent(textData, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(labelNome1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelNome2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textVagasRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNome)
                            .addComponent(labelNome3))))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNome1)
                        .addComponent(textCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNome2)
                        .addComponent(textVagasRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoInscricao)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void removerMatricula(Matricula matriculaAux) 
    {
        List<Matricula> listaMatricula = participante.getListaMatricula();
        
        for (int i = 0; i < listaMatricula.size(); i++)
        {
            if (listaMatricula.get(i).equals(matriculaAux))
            {
                listaMatricula.remove(i);
                break; 
            }
        }
    }

    private void botaoInscricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInscricaoActionPerformed
        
        
        if(botaoInscricao.getText().equals("Inscrever"))
        {
            Integer idMatricula = matriculaDAO.buscarUltimoID(participante.getProntuario()) + 1;
            
            evento.setQntdeIncrito(evento.getQntdeIncrito()+1);
            
            matricula.setEvento(evento);
            matricula.setIdMatricula(idMatricula);
            matricula.setData(LocalDate.now());
            matricula.setListaPresenca(null);
            matricula.setStatus(Status.A);
            matricula.setParticipante(participante);
            
            eventoDAO.alterar(evento);
            
            JOptionPaneUtils.sucesso("Inscrição realizada com sucesso!", "Confirmação");
            dispose();
            
           
        }
        else
        {
            
           int resposta = JOptionPaneUtils.confirmacao("Deseja se desinscrever do evento?", "Confirmação");
           
            if(resposta == JOptionPane.YES_OPTION)
            {
                matricula = matriculaDAO.buscarPorEvento(participante.getNome(), evento.getIdEvento());              
                matriculaDAO.excluir(matricula);
 
                removerMatricula(matricula);
                participanteDAO.alterar(participante);
                
                JOptionPaneUtils.sucesso("Matricula cancelada!", "Sucesso");
                
                matricula = null;
                
                Evento eventoAux = eventoDAO.buscarEventoPorSemana(evento.getTitulo(), evento.getSemana().getIdSemana());
                eventoAux.setQntdeIncrito(eventoAux.getQntdeIncrito()-1);
                eventoDAO.alterar(eventoAux);
                
                dispose();
            }
            else
            {
                JOptionPaneUtils.sucesso("Operação cancelada", "Cancelameto");
            }
            
            
            
        }
    }//GEN-LAST:event_botaoInscricaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoInscricao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JLabel labelNome3;
    private javax.swing.JTable tabelaResponsaveis;
    private javax.swing.JTextField textCargaHoraria;
    private com.toedter.calendar.JDateChooser textData;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textVagasRestantes;
    // End of variables declaration//GEN-END:variables
}
