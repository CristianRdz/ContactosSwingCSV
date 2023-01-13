package Integradora;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Se implementa el TDA Contacto
public class Contactos implements ContactoTDA {

    private ArrayList<Contacto> contactos = new ArrayList<>();

    //Se declara el constructor vacio que recarga los contactos
    public Contactos() throws IOException, CsvValidationException {
        recargar();
    }

    //Se implementa el metodo recargar que carga los contactos del archivo CSV
    @Override
    public void recargar() throws IOException, CsvValidationException {
        try {
            //Se limpia la lista de contactos
            contactos.clear();
            //Se crea un String con la ruta del archivo CSV
            String carpeta = "C:\\Contactos";
            //Se apunta al directorio donde se encuentra el archivo CSV
            File directorio = new File(carpeta);
            //Si el directorio no existe se crea
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            //Se crea un String con la ruta y el nombre del archivo CSV
            String nombre = "C:\\Contactos\\contactos.csv";
            //Se apunta al archivo CSV
            File file = new File(nombre);
            //Si el archivo no existe se crea
            if (!file.exists()) {
                file.createNewFile();
            }
            //Se crea un lector de archivos CSV
            FileReader archivo = new FileReader(nombre);
            //Se crea un lector de CSV
            CSVReader csvReader = new CSVReader(archivo);
            //Se crea una lista de String para guardar los datos de cada contacto
            String[] fila = null;
            //Se lee el archivo CSV
            while ((fila = csvReader.readNext()) != null) {
                //Se crea un contacto con los datos leidos
                contactos.add(new Contacto(fila[0], fila[1], fila[2], fila[3], fila[4]));
            }
            //Se cierra el lector de CSV
            csvReader.close();
        } catch (Exception ex) {
            reconstruir();
        }
    }

    //Se implementa el metodo ExisteContacto que verifica si un contacto existe
    @Override
    public boolean existeContacto(String id) {
        //Si la lista de contactos no está vacia
        if (!contactos.isEmpty()) {
            //Se recorre la lista de contactos
            for (int i = 0; i < contactos.size(); i++) {
                // Se castea el contacto de la lista a Contacto
                Contacto c = (Contacto) contactos.get(i);
                //Si el id del contacto es igual al ID del contacto a buscar
                if (c.getId().equals(id)) {
                    //Se retorna true
                    return true;
                }
            }
        } else {
            //Si la lista esta vacia se retorna false
            return false;
        }
        //Se retorna falso si no se encuentra el contacto
        return false;
    }

    //Se implementa el metodo agregarContacto que agrega un contacto a la lista
    @Override
    public void agregarContacto(String nombre, String telefono, String email, String direccion) throws IOException {
        //Se crea un String que genera un ID para el contacto
        String id = java.util.UUID.randomUUID().toString();
        // se elimina el guion del id
        id = id.replaceAll("-", "");
        // Se limita el id a 6 caracteres
        id = id.substring(0, 6);
        //Se crea un contacto con los datos ingresados
        contactos.add(new Contacto(id, nombre, telefono, email, direccion));
        //Se crea un String con la ruta del archivo CSV
        String archCSV = "C:\\Contactos\\contactos.csv";
        //Se crea un escritor de archivos CSV al cual se le pasa la ruta del archivo CSV
        CSVWriter writer = new CSVWriter(new FileWriter(archCSV, true));
        //Se crea un String con los datos del contacto
        String singleRecord = id + "," + nombre + "," + telefono + "," + email + "," + direccion;
        //Se crea un String con los datos del contacto separados por comas
        String[] record = singleRecord.split(",");
        System.out.println(record.toString());
        //Se escribe el contacto en el archivo CSV
        writer.writeNext(record, true);
        //Se cierra el escritor de archivos CSV
        writer.close();
    }

    //Se implementa el metodo EliminarContacto que elimina un contacto de la lista
    @Override
    public boolean eliminarContacto(String id) throws IOException, CsvException {
        // Si la lista de contactos no esta vacia
        if (!contactos.isEmpty()) {
            for (int i = 0; i < contactos.size(); i++) {
                // Se castea el contacto de la lista a Contacto
                Contacto c = (Contacto) contactos.get(i);
                //Si el id del contacto es igual al id del contacto a eliminar
                if (c.getId().equals(id)) {
                    //Se elimina el contacto de la lista
                    contactos.remove(i);
                    //Se crea un String con la ruta del archivo CSV
                    String archCSV = "C:\\Contactos\\contactos.csv";
                    //Se crea un lector de archivos CSV que recibe la ruta del archivo CSV
                    CSVReader reader2 = new CSVReader(new FileReader(archCSV));
                    //Se crea una lista de String para guardar los datos de cada contacto
                    List<String[]> allElements = reader2.readAll();
                    // Se elimina el contacto de la lista de String que se saco del archivo CSV
                    allElements.remove(i);
                    // Se crea un archivo CSV con la ruta del archivo CSV
                    FileWriter sw = new FileWriter(archCSV);
                    //Se crea un escritor de archivos CSV que recibe el escritor de archivos CSV
                    CSVWriter writer2 = new CSVWriter(sw);
                    //Se escribe la lista de String en el archivo CSV
                    writer2.writeAll(allElements);
                    //Se cierra el escritor de archivos CSV
                    writer2.close();
                    //Se retorna true
                    return true;
                }
            }
        } else {
            //Si la lista esta vacia se retorna false
            return false;
        }
        //Se retorna false si no se encuentra el contacto
        return false;
    }

    //Se implementa el metodo ModificarContacto que modifica un contacto de la lista
    @Override
    public boolean modificarContacto(String id, String nombre, String telefono, String email, String direccion) throws IOException, CsvException {
        // Si la lista de contactos no esta vacia
        if (!contactos.isEmpty()) {
            //Se recorre la lista de contactos
            for (int i = 0; i < contactos.size(); i++) {
                // Se castea el contacto de la lista a Contacto
                Contacto c = (Contacto) contactos.get(i);
                //Si el id del contacto es igual al id del contacto a modificar
                if (c.getId().equals(id)) {
                    //Se modifica el contacto de la lista
                    c.setNombre(nombre);
                    c.setTelefono(telefono);
                    c.setEmail(email);
                    c.setDireccion(direccion);
                    //Se crea un String con la ruta del archivo CSV
                    String archCSV = "C:\\Contactos\\contactos.csv";
                    //Se crea un lector de archivos CSV que recibe la ruta del archivo CSV
                    CSVReader reader2 = new CSVReader(new FileReader(archCSV));
                    //Se crea una lista de String para guardar los datos de cada contacto
                    List<String[]> allElements = reader2.readAll();
                    // Se modifica el contacto de la lista de String que se saco del archivo CSV
                    allElements.get(i)[1] = nombre;
                    allElements.get(i)[2] = telefono;
                    allElements.get(i)[3] = email;
                    allElements.get(i)[4] = direccion;
                    // Se crea un archivo CSV con la ruta del archivo CSV
                    FileWriter sw = new FileWriter(archCSV);
                    //Se crea un escritor de archivos CSV que recibe el escritor de archivos CSV
                    CSVWriter writer2 = new CSVWriter(sw);
                    //Se escribe la lista de String en el archivo CSV
                    writer2.writeAll(allElements);
                    //Se cierra el escritor de archivos CSV
                    writer2.close();
                    //Se retorna true
                    return true;
                }
            }
        } else {
            //Si la lista esta vacia se retorna false
            return false;
        }
        //Se retorna false si no se encuentra el contacto
        return false;
    }

    //Se implementa el metodo ListarContactos que retorna la lista de contactos
    @Override
    public ArrayList<Contacto> listarContactos() {
        //Si la lista de contactos no esta vacia
        if (!contactos.isEmpty()) {
            //Se retorna la lista de contactos
            return contactos;
        } else {
            //Si la lista esta vacia se retorna null
            return null;
        }
    }

    //Se implementa el metodo buscarContacto que busca un contacto en la lista
    @Override
    public Contacto buscarContacto(String id) throws IOException {
        // Si la lista de contactos no está vacia
        if (!contactos.isEmpty()) {
            //Se recorre la lista de contactos
            for (int i = 0; i < contactos.size(); i++) {
                // Se castea el contacto de la lista a Contacto
                Contacto c = (Contacto) contactos.get(i);
                //Si el id del contacto es igual al ID del contacto a buscar
                if (c.getId().equals(id)) {
                    //Se retorna el contacto
                    return (Contacto) contactos.get(i);
                }
            }
        } else {
            //Si la lista esta vacia se retorna null
            return null;
        }
        //Se retorna null si no se encuentra el contacto
        return null;
    }

    @Override
    public void reconstruir() throws IOException {
        contactos.clear();
        //Se crea un String con la ruta del archivo CSV
        String carpeta = "C:\\Contactos";
        //Se apunta al directorio donde se encuentra el archivo CSV
        File directorio = new File(carpeta);
        //Si el directorio no existe se crea
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        //Se crea un String con la ruta del archivo CSV
        String archCSV = "C:\\Contactos\\contactos.csv";
        File file = new File(archCSV);
        //Si el archivo no existe se crea
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
    }

    @Override
    public LinkedList<Contacto> buscar(String palabra) throws IOException {
        LinkedList<Contacto> contactosEncontrados = new LinkedList<>();
        // Si la lista de contactos no está vacia
        if (!contactos.isEmpty()) {
            //Se recorre la lista de contactos
            for (int i = 0; i < contactos.size(); i++) {
                // Se castea el contacto de la lista a Contacto
                Contacto c = (Contacto) contactos.get(i);
                //Si el contacto contiene la palabra a buscar
                if (c.getId().toLowerCase().contains(palabra.toLowerCase())
                        || c.getTelefono().toLowerCase().contains(palabra.toLowerCase())
                        || c.getNombre().toLowerCase().contains(palabra.toLowerCase())
                        || c.getEmail().toLowerCase().contains(palabra.toLowerCase())
                        || c.getDireccion().toLowerCase().contains(palabra.toLowerCase())) {
                    //Se agrega el contacto a la lista de contactos encontrados
                    contactosEncontrados.add(c);
                }

            }
        } else {
            //Si la lista esta vacia se retorna null
            return null;
        }
        //Se retorna la lista de contactos encontrados
        return contactosEncontrados;
    }

    @Override
    public boolean AgregarArchivo(String archivo) throws IOException, CsvValidationException, CsvException {
        try {
            contactos.clear();
            //Se crea un String con la ruta del archivo CSV
            String carpeta = "C:\\Contactos";
            //Se apunta al directorio donde se encuentra el archivo CSV
            File directorio = new File(carpeta);
            //Si el directorio no existe se crea
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            //Se crea un String con la ruta del archivo CSV
            String archCSV = "C:\\Contactos\\contactos.csv";
            File file = new File(archCSV);
            //Si el archivo no existe se crea
            if (!file.exists()) {
                file.createNewFile();
            }
            //Se crea un lector de archivos CSV que recibe la ruta del archivo CSV donde se importaran los datos
            CSVReader reader2 = new CSVReader(new FileReader(archivo));
            //Se crea una lista de String para guardar los datos de cada contacto
            List<String[]> allElements = null;
            allElements = reader2.readAll();
            // Se crea un archivo CSV con la ruta del archivo CSV
            FileWriter sw = new FileWriter(archCSV);
            String[] fila = null;
            //Se lee el archivo CSV
            while ((fila = reader2.readNext()) != null) {
                //Se crea un contacto con los datos leidos
                contactos.add(new Contacto(fila[0], fila[1], fila[2], fila[3], fila[4]));
            }
            CSVWriter writer2 = new CSVWriter(sw);
            //Se escribe la lista de String en el archivo CSV
            writer2.writeAll(allElements);
            //Se cierra el escritor de archivos CSV
            writer2.close();
            //Se retorna true
            return true;
        } catch (Exception ex) {
            //Si ocurre un error se retorna false y reconstruye el archivo CSV
            reconstruir();
            return false;
        }
    }

    @Override
    public void exportarSeleccion(String nuevoNombre,Stack<Contacto> pilaContactos) throws IOException {
        String carpeta = "C:\\Contactos";
            //Se apunta al directorio donde se encuentra el archivo CSV
            File directorio = new File(carpeta);
            //Si el directorio no existe se crea
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            //Se crea un String con la ruta y el nombre del archivo CSV
            String nombre = "C:\\Contactos\\" + nuevoNombre + ".csv";
            //Se apunta al archivo CSV
            File file = new File(nombre);
            //Si el archivo no existe se crea
            if (!file.exists()) {
                file.createNewFile();
            }
        while (!pilaContactos.isEmpty()) {
            Contacto contacto = pilaContactos.pop();
            //Se crea un escritor de archivos CSV al cual se le pasa la ruta del archivo CSV
            CSVWriter writer = new CSVWriter(new FileWriter(nombre, true));
            //Se crea un String con los datos del contacto
            String singleRecord = contacto.getId() + "," + contacto.getNombre() + "," + contacto.getTelefono() + "," + contacto.getEmail() + "," + contacto.getDireccion();
            //Se crea un String con los datos del contacto separados por comas
            String[] record = singleRecord.split(",");
            //Se escribe el contacto en el archivo CSV
            writer.writeNext(record, true);
            //Se cierra el escritor de archivos CSV
            writer.close();
        }
    }

}
