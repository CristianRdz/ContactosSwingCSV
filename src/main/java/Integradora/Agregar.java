package Integradora;

import com.opencsv.exceptions.CsvValidationException;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Agregar extends javax.swing.JFrame {

// se crea un objeto de la clase Contactos
    Contactos contactos = new Contactos();
    // Se crea un objeto de la clase RegexCheck que contiene los patrones ReGex para
    // verificar textos
    RegexCheck checker = new RegexCheck();

    // Se inicializan los componentes
    public Agregar() throws IOException, CsvValidationException {
        initComponents();
        estadoBoton();
        getContentPane().setBackground(new Color(248, 252, 193));
    }

    void estadoBoton() {
        // Se creo el metodo estadoBoton para que se compruebe los textos con patrones
        // de expresiones regulares (REGEX)
        if (checker.verificarCampos(nombre.getText())
                && checker.verificarCorreo(correo.getText())
                && checker.verificarTelefono(telefono.getText())
                && checker.verificarDir(direccion.getText())) {
            // si todos los textos son correctos entonces habilita el botón
            btnAgregar.setEnabled(true);
        } else {
            // si alguno de los textos no es correcto el boton se deshabilita
            btnAgregar.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contactos");

        btnAgregar.setBackground(new java.awt.Color(22, 147, 167));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon("C:\\Users\\losti\\Documents\\NetBeansProjects\\IntegradoraEstructuras_2.1\\src\\main\\java\\img\\guardar.png")); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tet1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tet1.setText("Nombre");

        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });
        correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correoKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Telefono");

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

        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });
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
        volver.setIcon(new javax.swing.ImageIcon("C:\\Users\\losti\\Documents\\NetBeansProjects\\IntegradoraEstructuras_2.1\\src\\main\\java\\img\\volver.png")); // NOI18N
        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        salir.setBackground(new java.awt.Color(22, 147, 167));
        salir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setIcon(new javax.swing.ImageIcon("C:\\Users\\losti\\Documents\\NetBeansProjects\\IntegradoraEstructuras_2.1\\src\\main\\java\\img\\cerrar.png")); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
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
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(tet1)
                                    .addComponent(nombre)
                                    .addComponent(telefono)
                                    .addComponent(correo)
                                    .addComponent(direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(603, 603, 603)
                                .addComponent(btnAgregar)))
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
                .addGap(145, 145, 145)
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
                .addComponent(btnAgregar)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Cuando se presiona el boton agregar
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        // solo se ejecuta si los campos no estan vacios
        if (!nombre.getText().equals("") && !telefono.getText().equals("") && !correo.getText().equals("")
                && !direccion.getText().equals("")) {
            // se prueba si no hay ningun error
            try {
                // se agrega un contacto al archivo CSV
                contactos.agregarContacto(nombre.getText(), telefono.getText(), correo.getText(), direccion.getText());
                // se muestra un mensaje de que se agrego correctamente
                JOptionPane.showMessageDialog(null, "Contacto agregado");
                // se limpian los campos
                nombre.setText("");
                telefono.setText("");
                correo.setText("");
                direccion.setText("");
                // se cierra la ventana
                this.setVisible(false);
                try {
                    // se abre la ventana del menu
                    Menu ini = new Menu();
                    ini.setExtendedState(ini.MAXIMIZED_BOTH);
                    ini.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // si los campos estan vacios se muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Primero llena todos los campos");
        }
    }

    //Cuando se presiona el boton volver
    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // se cierra la ventana
        this.setVisible(false);
        try {
            // se abre la ventana del menu
            Menu ini = new Menu();
            ini.setExtendedState(ini.MAXIMIZED_BOTH);
            ini.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_volverActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    //Cuando se presiona el boton salir
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // Se crea un mensaje de confirmacion
        String botones[] = {"Si", "No"};
        // se muestra el mensaje
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Salir?", "¿Salir?",
                0, 1, null, botones, this);
        if (eleccion == JOptionPane.YES_OPTION) {
            // si se presiona el boton si se cierra el programa
            System.exit(0);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        // Si el texto del campo es un nombre valido
        if (checker.verificarCampos(nombre.getText())) {
            // Se pinta de color verde el campo de texto
            nombre.setBackground(new Color(183, 255, 190));
        } else {
            // Se pinta de color rojo el campo de texto
            nombre.setBackground(new Color(255, 183, 183));
        }
        // Se verifica el estdo del boton
        estadoBoton();
    }//GEN-LAST:event_nombreKeyReleased

    private void telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyReleased
        // Si el texto del campo es un numero valido
        if (checker.verificarTelefono(telefono.getText())) {
            // Se pinta de color verde el campo de texto
            telefono.setBackground(new Color(183, 255, 190));
        } else {
            // Si no es un numero se pinta de color rojo el campo de texto
            telefono.setBackground(new Color(255, 183, 183));
        }
        estadoBoton();
    }//GEN-LAST:event_telefonoKeyReleased

    private void correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correoKeyReleased
        // Si el texto del campo es un correo valido
        if (checker.verificarCorreo(correo.getText())) {
            // Se pinta de color verde el campo de texto
            correo.setBackground(new Color(183, 255, 190));
        } else {
            // Si no es un correo se pinta de color rojo el campo de texto
            correo.setBackground(new Color(255, 183, 183));
        }
        // Se verifica el estdo del boton
        estadoBoton();
    }//GEN-LAST:event_correoKeyReleased

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        // Si el texto del campo es una direccion valida
        if (checker.verificarDir(direccion.getText())) {
            // Se pinta de color verde el campo de texto
            direccion.setBackground(new Color(183, 255, 190));
        } else {
            // Si no es una direccion se pinta de color rojo el campo de texto
            direccion.setBackground(new Color(255, 183, 183));
        }
        // Se verifica el estado del boton
        estadoBoton();
    }//GEN-LAST:event_direccionKeyReleased

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionActionPerformed

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
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
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
                    new Agregar().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField direccion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton salir;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel tet1;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
