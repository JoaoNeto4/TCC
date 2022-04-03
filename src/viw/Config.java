
package viw;

import bean.TreinamentoBEAN;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import reconhecimento.Treinamento;
import reconhecimento.Treinamento_LBPH;

public class Config extends javax.swing.JDialog {
    
    TreinamentoBEAN tb = new TreinamentoBEAN();
    
    public Config(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);//inicializa o JFrame no centro da tela
        try {
            getProp2();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abaAjustes = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNumAmostras = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCamClassificador = new javax.swing.JTextField();
        btnCamClassificador = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCam = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtRaio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtVizinhos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGridx = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGridy = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLimiar = new javax.swing.JTextField();
        btnTreinar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));

        abaAjustes.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));

        jPanel3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Num. Amostras:");

        jLabel8.setText("Classificador:");

        btnCamClassificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/LupaPesquisar.png"))); // NOI18N
        btnCamClassificador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCamClassificadorMouseReleased(evt);
            }
        });

        jLabel9.setText("Câmera:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCamClassificador, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtNumAmostras))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCamClassificador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCam))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumAmostras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtCamClassificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnCamClassificador, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        abaAjustes.addTab("Ajustes", jPanel2);

        jPanel4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Raio:");

        jLabel2.setText("Vizinhos:");

        jLabel3.setText("Grid_X:");

        jLabel4.setText("Grid_Y:");

        jLabel7.setText("Limiar:");

        btnTreinar.setText("Treinar");
        btnTreinar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTreinarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTreinar)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtRaio)
                        .addComponent(txtVizinhos)
                        .addComponent(txtGridx)
                        .addComponent(txtGridy)
                        .addComponent(txtLimiar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtVizinhos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGridx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtGridy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLimiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTreinar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        abaAjustes.addTab("Treinamento", jPanel1);

        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSalvarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(abaAjustes)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(abaAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void getProp2() throws IOException{
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("/home/melena/NetBeansProjects/TCC-JoaoNeto/src/Config/config.properties");
        prop.load(file);
        try {
            tb.setNumAmostras(Integer.parseInt(prop.getProperty("prop.server.amostras")));
            tb.setClassificador(prop.getProperty("prop.server.classificador"));
            tb.setCamera(prop.getProperty("prop.server.camera"));
            tb.setRaio(Integer.parseInt(prop.getProperty("prop.server.raio")));
            tb.setVizinhos(Integer.parseInt(prop.getProperty("prop.server.vizinhos")));
            tb.setEixo_x(Integer.parseInt(prop.getProperty("prop.server.eixo_x")));
            tb.setEixo_y(Integer.parseInt(prop.getProperty("prop.server.eixo_y")));
            tb.setLimiar(Integer.parseInt(prop.getProperty("prop.server.limiar")));
            setViw();   
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    
    public TreinamentoBEAN retornoTreinamento() throws IOException{ 
        TreinamentoBEAN bean = new TreinamentoBEAN();
        try {
            bean.setRaio(Integer.parseInt(txtRaio.getText()));
            bean.setVizinhos(Integer.parseInt(txtVizinhos.getText()));
            bean.setEixo_x(Integer.parseInt(txtGridx.getText()));
            bean.setEixo_y(Integer.parseInt(txtGridy.getText()));
            bean.setLimiar(Integer.parseInt(txtLimiar.getText()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bean;
    }
    
    public void setViw() throws IOException{  
        try {
            txtNumAmostras.setText(String.valueOf(tb.getNumAmostras()));
            txtCamClassificador.setText(tb.getClassificador());
            txtCam.setText(tb.getCamera());
            txtRaio.setText(String.valueOf(tb.getRaio()));
            txtVizinhos.setText(String.valueOf(tb.getVizinhos()));
            txtGridx.setText(String.valueOf(tb.getEixo_x()));
            txtGridy.setText(String.valueOf(tb.getEixo_y()));
            txtLimiar.setText(String.valueOf(tb.getLimiar()));   
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
    }
       
    
    private void btnCamClassificadorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCamClassificadorMouseReleased
        JFileChooser ch = new JFileChooser();
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int se = ch.showSaveDialog(null);
        if(se == JFileChooser.APPROVE_OPTION){
            String st = ch.getSelectedFile().getPath();

            //String caminho=procuraEspaco(st);
            txtCamClassificador.setText(st);
        }
    }//GEN-LAST:event_btnCamClassificadorMouseReleased

    private void btnSalvarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseReleased
        //Object[] options = { "Confirmar", "Cancelar" };
        //JOptionPane.showOptionDialog(null, "Clique Confirmar para continuar", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
       // int resp;
       // resp = JOptionPane.showConfirmDialog(null, "sa");
        File file = new File("/home/melena/NetBeansProjects/TCC-JoaoNeto/src/Config/config.properties");
        Properties prop = new Properties();
        try{
            FileInputStream f = new FileInputStream(file);
            prop.load(f);
        } catch (IOException e){
            e.printStackTrace();
        }
      
        try {
            prop.setProperty("prop.server.amostras", txtNumAmostras.getText());
            prop.setProperty("prop.server.classificador", txtCamClassificador.getText());
            prop.setProperty("prop.server.camera", txtCam.getText());
            prop.setProperty("prop.server.raio", txtRaio.getText());
            prop.setProperty("prop.server.vizinhos", txtVizinhos.getText());
            prop.setProperty("prop.server.eixo_x", txtGridx.getText());
            prop.setProperty("prop.server.eixo_y", txtGridy.getText());
            prop.setProperty("prop.server.limiar", txtLimiar.getText());
            /*
            prop.setProperty("prop.server.login", txtLoginBD.getText());
            prop.setProperty("prop.server.password", txtSenhaBD.getPassword().toString());
            prop.setProperty("prop.server.host", txtHost.getText());
            prop.setProperty("prop.server.port", txtPortaBD.getText());
            prop.setProperty("prop.server.bd", txtHost.getText());
            */
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try{
            FileOutputStream fos = new FileOutputStream(file);
            //a linha abaixo apenas coloca a data de modificação com o titulo
            prop.store(fos, "Arquivo de Propriedades");
            fos.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSalvarMouseReleased

    private void btnTreinarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTreinarMouseReleased
        //Treinamento_LBPH t = new Treinamento_LBPH();//linha abaixo para teste
        Treinamento_LBPH t = new Treinamento_LBPH();
        File arquivo = new File("src/recursos/classificadorLBPH.yml");
        try {
            arquivo.delete();
            t.treinamento(retornoTreinamento());
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnTreinarMouseReleased

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
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Config dialog = new Config(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane abaAjustes;
    private javax.swing.JButton btnCamClassificador;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnTreinar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtCam;
    private javax.swing.JTextField txtCamClassificador;
    private javax.swing.JTextField txtGridx;
    private javax.swing.JTextField txtGridy;
    private javax.swing.JTextField txtLimiar;
    private javax.swing.JTextField txtNumAmostras;
    private javax.swing.JTextField txtRaio;
    private javax.swing.JTextField txtVizinhos;
    // End of variables declaration//GEN-END:variables
}
