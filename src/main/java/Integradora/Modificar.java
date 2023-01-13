package Integradora;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Modificar extends javax.swing.JFrame {
    
    Queue<Contacto> colaContactos = new LinkedList<>();
    Contactos contactos = new Contactos();
    RegexCheck checker = new RegexCheck();

    public Modificar() throws IOException, CsvValidationException {
        initComponents();
        id.setVisible(false);
        getContentPane().setBackground(new Color(248, 252, 193));
    }

    // Se creo el metodo estadoBoton para que se compruebe los textos con patrones
    // de expresiones regulares (REGEX)
    void estadoBoton() {
        if (checker.verificarCampos(nombre.getText())
                && checker.verificarCorreo(correo.getText())
                && checker.verificarTelefono(telefono.getText())
                && checker.verificarDir(direccion.getText())) {
            // si todos los textos son correctos entonces habilita el botón
            btnModificar.setEnabled(true);
        } else {
            // si alguno de los textos no es correcto el boton se deshabilita
            btnModificar.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModificar = new javax.swing.JButton();
        tet1 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        volver = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contactos");
        setPreferredSize(new java.awt.Dimension(1352, 653));

        btnModificar.setBackground(new java.awt.Color(22, 147, 167));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        tet1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tet1.setText("Nombre");

        correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correoKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Telefono");

        nombre.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                nombreInputMethodTextChanged(evt);
            }
        });
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
        });

        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Correo");

        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Direccion");

        volver.setBackground(new java.awt.Color(22, 147, 167));
        volver.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        volver.setForeground(new java.awt.Color(255, 255, 255));
        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/volver.png"))); // NOI18N
        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        salir.setBackground(new java.awt.Color(22, 147, 167));
        salir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        id.setEnabled(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(603, 603, 603)
                                .addComponent(btnModificar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(tet1)
                                        .addComponent(nombre)
                                        .addComponent(telefono)
                                        .addComponent(correo)
                                        .addComponent(direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)))))
                        .addGap(0, 174, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(volver))
                .addGap(117, 117, 117)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tet1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (!id.getText().equals("") && !nombre.getText().equals("") && !telefono.getText().equals("") && !correo.getText().equals("") && !direccion.getText().equals("")) {
            try {
                String idInMod = id.getText();
                contactos.modificarContacto(idInMod, nombre.getText(), telefono.getText(), correo.getText(), direccion.getText());
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Modificado exitosamente");

            try {
                if (!colaContactos.isEmpty()) {
                    Contacto contactoTemp = colaContactos.remove();
                    id.setText(contactoTemp.getId());
                    nombre.setText(contactoTemp.getNombre());
                    telefono.setText(contactoTemp.getTelefono());
                    correo.setText(contactoTemp.getEmail());
                    direccion.setText(contactoTemp.getDireccion());
                                    } else {
                    this.setVisible(false);
                    // se abre la ventana del menu
                    Menu ini = new Menu();
                    ini.setExtendedState(ini.MAXIMIZED_BOTH);
                    ini.setVisible(true);
                }

            } catch (IOException ex) {
                Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Primero llena todos los campos");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.setVisible(false);
        try {
            Menu ini = new Menu();
            ini.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_volverActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        String botones[] = {"Si", "No"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Salir?", "¿Salir?",
                0, 0, null, botones, this);
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        if (checker.verificarCampos(nombre.getText())) {
            nombre.setBackground(new Color(183, 255, 190));
        } else {
            nombre.setBackground(new Color(255, 183, 183));
        }
        estadoBoton();
    }//GEN-LAST:event_nombreKeyReleased

    private void telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyReleased
        if (checker.verificarTelefono(telefono.getText())) {
            telefono.setBackground(new Color(183, 255, 190));
        } else {
            telefono.setBackground(new Color(255, 183, 183));
        }
        estadoBoton();
    }//GEN-LAST:event_telefonoKeyReleased

    private void correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correoKeyReleased
        if (checker.verificarCorreo(correo.getText())) {
            correo.setBackground(new Color(183, 255, 190));
        } else {
            correo.setBackground(new Color(255, 183, 183));
        }
        estadoBoton();
    }//GEN-LAST:event_correoKeyReleased

    private void nombreInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nombreInputMethodTextChanged
        estadoBoton();
    }//GEN-LAST:event_nombreInputMethodTextChanged

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {
        if (checker.verificarDir(direccion.getText())) {
            direccion.setBackground(new Color(183, 255, 190));
        } else {
            direccion.setBackground(new Color(255, 183, 183));
        }
        estadoBoton();
    }

    /**
     * @param args the command line arguments
     */
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
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Modificar().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    public javax.swing.JTextField correo;
    public javax.swing.JTextField direccion;
    public javax.swing.JTextField id;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JTextField nombre;
    private javax.swing.JButton salir;
    public javax.swing.JTextField telefono;
    private javax.swing.JLabel tet1;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
