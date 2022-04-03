
package viw;

import DAO.PessoaDAO;
import bean.Pessoa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import reconhecimento.Reconhecimento;





public class Principal extends javax.swing.JFrame {

    private List<Pessoa> lista = new ArrayList<Pessoa>();
    
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);//inicializa o JFrame no centro da tela
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //para impedir o fechamento pelo X da barra de titulo
        setaBotoes();
    }
    /*
    // altera a cor da barra de menu, chamando o metodo no construtor *COR MENU
    private void customizeMenuBar(JMenuBar menuBar) {
    menuBar.setUI(new BasicMenuBarUI() {
        @Override
        public void paint(Graphics g, JComponent c) {
            g.setColor(Color.black);
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
    });
  
    MenuElement[] menus = menuBar.getSubElements();
    for (MenuElement menuElement : menus) {
        JMenu menu = (JMenu) menuElement.getComponent();
        changeComponentColors(menu);
        menu.setOpaque(true);
        MenuElement[] menuElements = menu.getSubElements();
        for (MenuElement popupMenuElement : menuElements) {
            JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
            popupMenu.setBorder(null);
            MenuElement[] menuItens = popupMenuElement.getSubElements();
            for (MenuElement menuItemElement : menuItens) {
                JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                changeComponentColors(menuItem);
                menuItem.setOpaque(true);
            }
        }
    }
}
private void changeComponentColors(Component comp) {
    comp.setBackground(Color.black);
    comp.setForeground(Color.white);
}
// COR MENU 
*/

    public void setaBotoes(){
        btnRelatorio.setContentAreaFilled(false);
        btnConfig.setContentAreaFilled(false);
        btnHorario.setContentAreaFilled(false);
        btnSistema.setContentAreaFilled(false);
        btnUsuarios.setContentAreaFilled(false);
        btnPlay.setContentAreaFilled(false);        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRelatorio = new javax.swing.JButton();
        btnSistema = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnHorario = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("V-1.1.1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, -1, -1));

        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/relatorio.png"))); // NOI18N
        btnRelatorio.setText("Relatório");
        btnRelatorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRelatorio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnRelatorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRelatorio.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRelatorioMouseMoved(evt);
            }
        });
        btnRelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRelatorioMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRelatorioMouseExited(evt);
            }
        });
        jPanel1.add(btnRelatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 130, 110));

        btnSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/laptop90.png"))); // NOI18N
        btnSistema.setText("Sistema");
        btnSistema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSistema.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSistema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSistema.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnSistemaMouseMoved(evt);
            }
        });
        btnSistema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSistemaMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSistemaMouseExited(evt);
            }
        });
        jPanel1.add(btnSistema, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 130, 110));

        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/config.png"))); // NOI18N
        btnConfig.setText("Configurações");
        btnConfig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfig.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnConfig.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfig.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnConfigMouseMoved(evt);
            }
        });
        btnConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnConfigMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigMouseExited(evt);
            }
        });
        jPanel1.add(btnConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 130, 110));

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        btnPlay.setText("Play");
        btnPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlay.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlay.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnPlayMouseMoved(evt);
            }
        });
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPlayMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPlayMouseExited(evt);
            }
        });
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        jPanel1.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 130, 110));

        btnHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Relogio.png"))); // NOI18N
        btnHorario.setText("Horário");
        btnHorario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHorario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnHorario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHorario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnHorarioMouseMoved(evt);
            }
        });
        btnHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHorarioMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHorarioMouseExited(evt);
            }
        });
        jPanel1.add(btnHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 130, 110));

        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users.png"))); // NOI18N
        btnUsuarios.setText("Usuários");
        btnUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUsuarios.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUsuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseMoved(evt);
            }
        });
        btnUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseExited(evt);
            }
        });
        jPanel1.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 130, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRelatorioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatorioMouseMoved
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        btnRelatorio.setBorder(emptyBorder);
        //btn1.setOpaque(true);
        btnRelatorio.setContentAreaFilled(true);
        //btn1.setBorderPainted(true);
       
    }//GEN-LAST:event_btnRelatorioMouseMoved

    private void btnRelatorioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatorioMouseExited
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnRelatorio.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnRelatorioMouseExited

    private void btnSistemaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSistemaMouseMoved
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        btnSistema.setBorder(emptyBorder);
        //btn1.setOpaque(true);
        btnSistema.setContentAreaFilled(true);
        //btn1.setBorderPainted(true);
    }//GEN-LAST:event_btnSistemaMouseMoved

    private void btnSistemaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSistemaMouseExited
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnSistema.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnSistemaMouseExited

    private void btnConfigMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseMoved
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        btnConfig.setBorder(emptyBorder);
        //btn1.setOpaque(true);
        btnConfig.setContentAreaFilled(true);
        //btn1.setBorderPainted(true);
    }//GEN-LAST:event_btnConfigMouseMoved

    private void btnConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseExited
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnConfig.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnConfigMouseExited

    private void btnPlayMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseMoved
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        btnPlay.setBorder(emptyBorder);
        //btn1.setOpaque(true);
        btnPlay.setContentAreaFilled(true);
        //btn1.setBorderPainted(true);
    }//GEN-LAST:event_btnPlayMouseMoved

    private void btnPlayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseExited
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnPlay.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnPlayMouseExited

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnPlay.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnHorarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHorarioMouseMoved
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        btnHorario.setBorder(emptyBorder);
        //btn1.setOpaque(true);
        btnHorario.setContentAreaFilled(true);
        //btn1.setBorderPainted(true);
    }//GEN-LAST:event_btnHorarioMouseMoved

    private void btnHorarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHorarioMouseExited
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnHorario.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnHorarioMouseExited

    private void btnUsuariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseMoved
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        btnUsuarios.setBorder(emptyBorder);
        //btn1.setOpaque(true);
        btnUsuarios.setContentAreaFilled(true);
        //btn1.setBorderPainted(true);
    }//GEN-LAST:event_btnUsuariosMouseMoved

    private void btnUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseExited
        //btn1.setFocusPainted(false);
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        //btn1.setBorder(emptyBorder);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        //btn1.setBorder(null);
       // btn1.setOpaque(false);
          btnUsuarios.setContentAreaFilled(false);
       // btn1.setBorderPainted(false);
    }//GEN-LAST:event_btnUsuariosMouseExited

    private void btnUsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseReleased
        //Criar JDialog para criar tela filha
        ListaPessoas p = new ListaPessoas(this, true);
        p.setVisible(true);
        //labelRelatorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));

    }//GEN-LAST:event_btnUsuariosMouseReleased

    private void btnSistemaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSistemaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSistemaMouseReleased

    private void btnHorarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHorarioMouseReleased
        ListaControle l = new ListaControle(this, true);
        l.setVisible(true);
    }//GEN-LAST:event_btnHorarioMouseReleased

    private void btnConfigMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseReleased
        Config c = new Config(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_btnConfigMouseReleased

    private void btnPlayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseReleased
        PessoaDAO p = new PessoaDAO();
        try {
            
            lista=p.listarReconhecimento();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* apagarrrrr
            System.out.println(lista.get(0));
            System.out.println(lista.get(1).getNome());
            System.out.println(lista.get(2).getNome());
            System.out.println(lista.get(3).getNome());
        */
        Reconhecimento r = new Reconhecimento();
        r.reconhece(lista);  
    }//GEN-LAST:event_btnPlayMouseReleased

    private void btnRelatorioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatorioMouseReleased
        RelatorioViw r = new RelatorioViw(this, true);
        r.setVisible(true);
    }//GEN-LAST:event_btnRelatorioMouseReleased

  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnHorario;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSistema;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
