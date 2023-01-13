package Integradora;

import com.formdev.flatlaf.FlatDarkLaf;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.Color;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatLightLaf;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.Stack;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.io.FileUtils;

public class Menu extends javax.swing.JFrame {

    // Creamos una instancia de la clase Contactos
    Contactos contactos = new Contactos();
    Stack<Contacto> pilaContactos = new Stack<>();
    Queue<Contacto> colaContactos = new LinkedList<>();
    // Declaramos el orden de las columnas
    String orden = "AS";

    public Menu() throws IOException, CsvValidationException {
        this.setExtendedState(this.MAXIMIZED_BOTH);
        initComponents();
        // llamamos al metodo mostarDatos
        mostrarDatos(orden);
        // Cambiamos el color del frame
        getContentPane().setBackground(new Color(248, 252, 193));

    }

    void exportarSeleccionados() throws IOException, CsvException {
        // se preguntara el nombre del archivo
        String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo");
        nombreArchivo.replace(" ", "");
        if ("".equals(nombreArchivo) || nombreArchivo != null) {
            nombreArchivo = "contactosExportados-";
            Date fecha = new Date();
            SimpleDateFormat fechaMex = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            nombreArchivo += fechaMex.format(fecha);
        }
        // se pregunta si se desea exportar
        String botones[] = {"Si", "No"};
        int opcion = JOptionPane.showOptionDialog(this, "¿Desea exportar?", "Acciones",
                0, JOptionPane.QUESTION_MESSAGE, null, botones, this);
        // si se desea exportar
        if (opcion == 0) {
            contactos.exportarSeleccion(nombreArchivo, pilaContactos);
            JFileChooser fileChooser = new JFileChooser();
            // Se crea un filtro para que solo se puedan seleccionar archivos .csv
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // Se crea un filtro para que solo se puedan seleccionar archivos .csv
            fileChooser.setDialogTitle("¡Exportar a CSV!");
            // Se abre el fileChooser
            int userSelection = fileChooser.showSaveDialog(this);
            // Si se selecciona un archivo
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Se crea un String con la ruta y el nombre del archivo CSV
                String nombre = "C:\\Contactos\\" + nombreArchivo + ".csv";
                // Se apunta al archivo CSV
                File archivo = new File(nombre);
                // Se crea un archivo que contiene la ruta del archivo CSV que se guardara
                File carpeta = fileChooser.getSelectedFile();
                try {
                    // Se copia el archivo CSV a la ruta seleccionada
                    FileUtils.copyFileToDirectory(archivo, carpeta);
                    // Se muestra un mensaje de exito
                    JOptionPane.showMessageDialog(null, "Archivo exportado correctamente", "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                    FileUtils.delete(archivo);
                } catch (Exception ex) {
                    // Si no se pudo exportar el archivo se muestra un mensaje de error
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al exportar", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                // Si no se desea exportar se muestra un mensaje de error
                JOptionPane.showMessageDialog(null, "No se exporto el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                tabla.clearSelection();
                pilaContactos.clear();
                colaContactos.clear();
            }

        } else {
            // Si no se desea exportar se muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "No se exporto el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            tabla.clearSelection();
            pilaContactos.clear();
            colaContactos.clear();
        }

    }

    // Metodo para mostrar los datos en la tabla
    void buscar() {
        String busqueda = textoBuscar.getText().toString();
        try {
            // Creamos un modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel();
            // Añadir las columnas
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Telefono");
            modelo.addColumn("Correo");
            modelo.addColumn("Direccion");
            // Le colocamos el modelo a la tabla
            tabla.setModel(modelo);
            if (contactos.listarContactos() != null) {
                LinkedList<Contacto> busquedaContactos = contactos.buscar(busqueda);
                int i = 0;
                if (orden.equals("AS")) {
                    busquedaContactos.sort((Contacto c1, Contacto c2) -> c1.getNombre().compareTo(c2.getNombre()));
                } else {
                    busquedaContactos.sort((Contacto c1, Contacto c2) -> c2.getNombre().compareTo(c1.getNombre()));
                }
                while (i != busquedaContactos.size()) {
                    Contacto contacto = (Contacto) busquedaContactos.get(i);
                    String[] datos = {String.valueOf(contacto.getId()), contacto.getNombre(), contacto.getTelefono(),
                        contacto.getEmail(), contacto.getDireccion()};
                    modelo.addRow(datos);
                    i++;
                }
            } else {
                // Si la lista esta vacia, mostramos un mensaje
                JOptionPane.showMessageDialog(null, "No hay datos aún, se creara el archivo");
            }

        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para mostrar los datos en la tabla
    void mostrarDatos(String orden) {
        // Creamos un modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();
        // Añadir las columnas
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");
        modelo.addColumn("Direccion");
        // Le colocamos el modelo a la tabla
        tabla.setModel(modelo);

        //Cuando la tabla  se seleccionen varios elementos, se mostrara el mensaje
        tabla.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        // se mostrara la lista de selecciones en un option pane
        tabla.getSelectionModel().addListSelectionListener((javax.swing.event.ListSelectionEvent e) -> {
            // se espera a que se termine de seleccionar
            if (!e.getValueIsAdjusting()) {
                // se pregunta si se selecciono mas de un elemento
                if (tabla.getSelectedRowCount() > 1) {
                    pilaContactos.clear();
                    colaContactos.clear();
                    int[] selectedRows = tabla.getSelectedRows();
                    for (int i = 0; i < selectedRows.length; i++) {
                        String id = tabla.getValueAt(selectedRows[i], 0).toString();
                        String nombre = tabla.getValueAt(selectedRows[i], 1).toString();
                        String telefono = tabla.getValueAt(selectedRows[i], 2).toString();
                        String email = tabla.getValueAt(selectedRows[i], 3).toString();
                        String direccion = tabla.getValueAt(selectedRows[i], 4).toString();
                        Contacto contacto = new Contacto(id, nombre, telefono, email, direccion);
                        pilaContactos.push(contacto);
                        colaContactos.add(contacto);
                    }
                }
            }
            // se preguntara si se desea hacer con esta pila
            if (pilaContactos.size() > 0) {
                String botones[] = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this, "¿Desea hacer algo con esta pila de contactos?", "Acciones",
                        0, JOptionPane.QUESTION_MESSAGE, null, botones, this);
                if (opcion == 0) {
                    // se mostrara un menu para seleccionar la opcion
                    String[] opciones = {"Eliminar", "Exportar", "Editar"};
                    String opcionSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione una opción",
                            "Opciones", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    // se eliminara el contacto
                    if (opcionSeleccionada.equals("Eliminar")) {
                        int opcionEliminar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar estos contactos?",
                                "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (opcionEliminar == 0) {
                            while (!pilaContactos.empty()) {
                                Contacto contacto = pilaContactos.pop();
                                try {
                                    contactos.eliminarContacto(contacto.getId());
                                } catch (IOException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (CsvException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            mostrarDatos(orden);
                        } else {
                            pilaContactos.clear();
                            colaContactos.clear();
                            tabla.clearSelection();
                        }
                    }
                    if (opcionSeleccionada.equals("Editar")) {
                        // Se crea una nueva ventana de modificar
                        try {
                            this.setVisible(false);
                            Modificar ini = new Modificar();
                            ini.colaContactos = this.colaContactos;
                            Contacto contactoMod = colaContactos.remove();
                            ini.nombre.setText(contactoMod.getNombre());
                            ini.telefono.setText(contactoMod.getTelefono());
                            ini.correo.setText(contactoMod.getEmail());
                            ini.direccion.setText(contactoMod.getDireccion());
                            ini.id.setText(contactoMod.getId());
                            ini.setExtendedState(ini.MAXIMIZED_BOTH);
                            // Se hace visible la ventana
                            ini.setVisible(true);
                        } catch (CsvValidationException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    if (opcionSeleccionada.equals("Exportar")) {
                        try {
                            exportarSeleccionados();
                            pilaContactos.clear();
                            colaContactos.clear();
                            tabla.clearSelection();
                        } catch (IOException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CsvException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                } else {
                    pilaContactos.clear();
                    colaContactos.clear();
                    tabla.clearSelection();
                }
            }
        });

        // Segun el orden que se le pase, se ordenara la lista
        switch (orden) {
            case "AS":

                if (contactos.listarContactos() != null) {
                    ArrayList<Contacto> listaContactos = contactos.listarContactos();
                    // se ordenaran por el nombre ascendente 
                    listaContactos.sort((Contacto c1, Contacto c2) -> c1.getNombre().compareTo(c2.getNombre()));
                    int i = 0;
                    while (i != listaContactos.size()) {
                        Contacto contacto = (Contacto) listaContactos.get(i);
                        String[] datos = {String.valueOf(contacto.getId()), contacto.getNombre(),
                            contacto.getTelefono(), contacto.getEmail(), contacto.getDireccion()};
                        modelo.addRow(datos);
                        i++;
                    }
                } else {
                    // Si la lista esta vacia, mostramos un mensaje
                    JOptionPane.showMessageDialog(null, "No hay datos aún, se creara el archivo");
                }
                break;
            case "DSC":
                if (contactos.listarContactos() != null) {
                    ArrayList<Contacto> listaContactos = contactos.listarContactos();
                    // se ordenaran por el nombre descendente
                    listaContactos.sort((Contacto c1, Contacto c2) -> c2.getNombre().compareTo(c1.getNombre()));
                    int i = 0;
                    while (i != listaContactos.size()) {
                        Contacto contacto = (Contacto) listaContactos.get(i);
                        String[] datos = {String.valueOf(contacto.getId()), contacto.getNombre(),
                            contacto.getTelefono(), contacto.getEmail(), contacto.getDireccion()};
                        modelo.addRow(datos);
                        i++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay datos aún, se creara el archivo");
                }
                break;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agregar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        recargar = new javax.swing.JButton();
        idMod = new javax.swing.JTextField();
        idElm = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        as = new javax.swing.JButton();
        dsc = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        textoBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnExportarArchivo = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contactos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Contactos"); // NOI18N

        agregar.setBackground(new java.awt.Color(22, 147, 167));
        agregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        modificar.setBackground(new java.awt.Color(22, 147, 167));
        modificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        modificar.setForeground(new java.awt.Color(255, 255, 255));
        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        eliminar.setBackground(new java.awt.Color(22, 147, 167));
        eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eliminar.setForeground(new java.awt.Color(255, 255, 255));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        recargar.setBackground(new java.awt.Color(22, 147, 167));
        recargar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        recargar.setForeground(new java.awt.Color(255, 255, 255));
        recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/recargar.png"))); // NOI18N
        recargar.setText("Recargar");
        recargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarActionPerformed(evt);
            }
        });

        idMod.setToolTipText("");
        idMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idModActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 120, 30), 1, true));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla.setGridColor(new java.awt.Color(230, 120, 30));
        tabla.setShowGrid(true);
        jScrollPane1.setViewportView(tabla);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        as.setBackground(new java.awt.Color(22, 147, 167));
        as.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        as.setForeground(new java.awt.Color(255, 255, 255));
        as.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ascendente.png"))); // NOI18N
        as.setText("Ascendente");
        as.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asActionPerformed(evt);
            }
        });

        dsc.setBackground(new java.awt.Color(22, 147, 167));
        dsc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dsc.setForeground(new java.awt.Color(255, 255, 255));
        dsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descendente.png"))); // NOI18N
        dsc.setText("Descendente");
        dsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dscActionPerformed(evt);
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

        btnCargarArchivo.setBackground(new java.awt.Color(22, 147, 167));
        btnCargarArchivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCargarArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/csv.png"))); // NOI18N
        btnCargarArchivo.setText("Cargar Archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        textoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoBuscarActionPerformed(evt);
            }
        });
        textoBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(22, 147, 167));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Texto a buscar");

        btnExportarArchivo.setBackground(new java.awt.Color(22, 147, 167));
        btnExportarArchivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExportarArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardarCSV.png"))); // NOI18N
        btnExportarArchivo.setText("Exportar archivo");
        btnExportarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarArchivoActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(22, 147, 167));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idElm, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idMod, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificar)
                        .addGap(18, 18, 18)
                        .addComponent(agregar)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(textoBuscar))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExportarArchivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCargarArchivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(as)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dsc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recargar)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(btnCargarArchivo)
                    .addComponent(btnExportarArchivo))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idElm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(eliminar)
                                .addComponent(idMod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(recargar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(as)
                            .addComponent(dsc)
                            .addComponent(btnBuscar))
                        .addComponent(btnLimpiar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     // Cuando el boton de modificar es presionado

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_modificarActionPerformed
        // Si el campo de texto de id no esta vacio
        if (!idMod.getText().equals("")) {
            // Se crea un String con el id del campo de texto
            String idInMod = idMod.getText();
            // si existe el contacto con el id ingresado
            if (contactos.existeContacto(idInMod)) {
                try {
                    // Se crea un nuevo contacto con los datos ingresados
                    Contacto contactoMod = contactos.buscarContacto(idInMod);
                    // Se hace invisible la ventana actual
                    this.setVisible(false);
                    // Se crea una nueva ventana de modificar
                    Modificar ini = new Modificar();
                    // Se asignan los valores del contacto a modificar
                    ini.nombre.setText(contactoMod.getNombre());
                    ini.telefono.setText(contactoMod.getTelefono());
                    ini.correo.setText(contactoMod.getEmail());
                    ini.direccion.setText(contactoMod.getDireccion());
                    ini.id.setText(contactoMod.getId());
                    // Se pone en pantalla completa la ventana
                    ini.setExtendedState(ini.MAXIMIZED_BOTH);
                    // Se hace visible la ventana
                    ini.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Si no existe el contacto con el id ingresado
                JOptionPane.showMessageDialog(null, "No existe ese contacto", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            // Si el campo de texto de id esta vacio
            JOptionPane.showMessageDialog(null, "Primero llena el campo", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }// GEN-LAST:event_modificarActionPerformed
    // Cuando el boton de agregar es presionado

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_agregarActionPerformed
        // Se hace invisible la ventana actual
        this.setVisible(false);
        try {
            // Se crea una nueva ventana de agregar
            Agregar ini = new Agregar();
            // Se pone en pantalla completa la ventana
            ini.setExtendedState(ini.MAXIMIZED_BOTH);
            // Se hace visible la ventana
            ini.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_agregarActionPerformed

    // Cuando el boton recargar es presionado
    private void recargarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_recargarActionPerformed
        try {
            // Se recarga la tabla
            contactos.recargar();
            // Se actualiza la tabla
            mostrarDatos(orden);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (CsvValidationException ex) {
            System.out.println(ex.toString());
        }
    }// GEN-LAST:event_recargarActionPerformed

    private void idModActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_idModActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_idModActionPerformed

    // Cuando el boton eliminar es presionado
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_eliminarActionPerformed
        // Si el campo de texto de id no esta vacio
        if (!idElm.getText().equals("")) {
            // Se crea un String con el id del campo de texto
            String idInElm = idElm.getText();
            // si existe el contacto con el id ingresado
            if (contactos.existeContacto(idInElm)) {
                try {
                    // Se crea un arreglo con las opciones de eliminar
                    String botones[] = {"Si", "No"};
                    // Se crea un mensaje de confirmacion
                    int eleccion = JOptionPane.showOptionDialog(this, "Esta acción no se puede deshacer", "¿Eliminar?",
                            0, 0, null, botones, this);
                    // Si se presiona el boton de si
                    if (eleccion == JOptionPane.YES_OPTION) {
                        // Se elimina el contacto
                        contactos.eliminarContacto(idInElm);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    // Se recarga la tabla
                    contactos.recargar();
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Se actualiza la tabla
                mostrarDatos(orden);
                // Se muestra un mensaje de exito
                JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
            } else {
                // Si no existe el contacto con el id ingresado
                JOptionPane.showMessageDialog(null, "No existe ese contacto");
            }

        } else {
            // Si el campo de texto de id esta vacio
            JOptionPane.showMessageDialog(null, "Primero llena el campo");
        }
    }// GEN-LAST:event_eliminarActionPerformed

    // Cuando el boton de ordenar dsc es presionado
    private void dscActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dscActionPerformed
        // LA TABLA SE ORDENA DE MANERA DESCENDENTE
        ArrayList<Contacto> listaContactos = contactos.listarContactos();
        int filas = tabla.getRowCount();
        orden = "DSC";
        if (filas != listaContactos.size()) {
            buscar();
        } else {
            mostrarDatos(orden);
        }

    }// GEN-LAST:event_dscActionPerformed

    private void asActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_asActionPerformed
        ArrayList<Contacto> listaContactos = contactos.listarContactos();
        int filas = tabla.getRowCount();
        orden = "AS";
        if (filas != listaContactos.size()) {
            buscar();
        } else {
            mostrarDatos(orden);
        }

    }// GEN-LAST:event_asActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_salirActionPerformed
        // Se crea un arreglo con las opciones de salir
        String botones[] = {"Si", "No"};
        // Se crea un mensaje de confirmacion
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Salir?", "¿Salir?",
                0, 0, null, botones, this);
        // Si se presiona el boton de si
        if (eleccion == JOptionPane.YES_OPTION) {
            // Se cierra el programa
            System.exit(0);
        }
    }// GEN-LAST:event_salirActionPerformed

    // Cuando el boton de cargar archivo es presionado
    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCargarArchivoActionPerformed
        // Se crea un objeto de tipo JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        // Se crea un filtro para que solo se puedan seleccionar archivos .csv
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Se crea un filtro para que solo se puedan seleccionar archivos .csv
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("CSV file", "csv");
        // Se agrega el filtro al fileChooser
        fileChooser.setFileFilter(filtro);
        // Se abre el fileChooser
        int result = fileChooser.showOpenDialog(this);
        // Si se selecciona un archivo
        if (result != JFileChooser.CANCEL_OPTION) {
            // Se crea un objeto de tipo File con el archivo seleccionado
            File fileName = fileChooser.getSelectedFile();
            // Si el archivo seleccionado no es .csv
            if ((fileName == null) || (fileName.getName().equals(""))) {
                JOptionPane.showMessageDialog(null, "No se cargo ningun archivo");
            } else {
                // Si el archivo seleccionado es .csv se carga
                String file = fileName.getAbsolutePath();
                try {
                    // Se agrega el archivo al objeto contactos
                    if (contactos.AgregarArchivo(file)) {
                        // Se recarga la tabla
                        contactos.recargar();
                        // Se actualiza la tabla
                        mostrarDatos(orden);
                        // Se muestra un mensaje de exito
                        JOptionPane.showMessageDialog(null, "Archivo cargado correctamente", "Exito",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // si el archivo no se cargo se muestra un mensaje de error
                        JOptionPane.showMessageDialog(null, "El archivo tuvo que ser reconstruido...", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        // Se recarga la tabla
                        mostrarDatos(orden);
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "El archivo no se cargo correctamente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (CsvException ex) {
                    JOptionPane.showMessageDialog(null, "El archivo no se cargo correctamente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "El archivo no se cargo correctamente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }// GEN-LAST:event_btnCargarArchivoActionPerformed

    private void textoBuscarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textoBuscarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textoBuscarActionPerformed

    private void textoBuscarKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textoBuscarKeyTyped

    }// GEN-LAST:event_textoBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // Se llama al metodo buscar
        buscar();
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarActionPerformed
        // Se limpia el campo de texto de buscar
        textoBuscar.setText("");
        // Se recarga la tabla
        buscar();
    }// GEN-LAST:event_btnLimpiarActionPerformed

    private void btnExportarArchivoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnExportarArchivoActionPerformed
        String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo");
        nombreArchivo.replace(" ", "");
        if (nombreArchivo == "" || nombreArchivo != null) {
            nombreArchivo = "contactosExportados-";
            Date fecha = new Date();
            SimpleDateFormat fechaMex = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            nombreArchivo += fechaMex.format(fecha);
        }
        // Se crea un objeto de tipo JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        // Se crea un filtro para que solo se puedan seleccionar archivos .csv
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Se crea un filtro para que solo se puedan seleccionar archivos .csv
        fileChooser.setDialogTitle("¡Exportar a CSV!");
        // Se abre el fileChooser
        int userSelection = fileChooser.showSaveDialog(this);
        // Si se selecciona un archivo
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Se crea un String con la ruta y el nombre del archivo CSV
            String nombre = "C:\\Contactos\\contactos.csv";
            // Se apunta al archivo CSV
            File archivo = new File(nombre);
            // Se crea un archivo que contiene la ruta del archivo CSV que se guardara
            File carpeta = fileChooser.getSelectedFile();
            try {
                // Se copia el archivo CSV a la ruta seleccionada
                FileUtils.copyFileToDirectory(archivo, carpeta);
                // Le aplicamos el nuevo nombre al archivo
                File archivoNuevo = new File(carpeta + "\\contactos.csv");
                archivoNuevo.renameTo(new File(carpeta + "\\" + nombreArchivo + ".csv"));
                // Se muestra un mensaje de exito
                JOptionPane.showMessageDialog(null, "Archivo exportado correctamente", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                // Si no se pudo exportar el archivo se muestra un mensaje de error
                JOptionPane.showMessageDialog(null, "Ocurrio un error al exportar", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }// GEN-LAST:event_btnExportarArchivoActionPerformed
    // Cuando se escribe en el campo de texto de buscar

    private void textoBuscarKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textoBuscarKeyReleased
        // Se llama al metodo buscar
        buscar();
    }// GEN-LAST:event_textoBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Menu().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton as;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnExportarArchivo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton dsc;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField idElm;
    private javax.swing.JTextField idMod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificar;
    private javax.swing.JButton recargar;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textoBuscar;
    // End of variables declaration//GEN-END:variables
}
