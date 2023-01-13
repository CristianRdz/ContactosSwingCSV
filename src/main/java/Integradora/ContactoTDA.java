package Integradora;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public interface ContactoTDA {
    void recargar() throws IOException, CsvValidationException;
    boolean existeContacto(String id);
    void agregarContacto(String nombre, String telefono, String email, String direccion) throws IOException;
    boolean eliminarContacto(String id) throws IOException,CsvException;
    boolean modificarContacto(String id, String nombre, String telefono, String email, String direccion)throws IOException,CsvException;
    Contacto buscarContacto(String id) throws IOException;
    ArrayList<Contacto> listarContactos();
    boolean AgregarArchivo (String nombre) throws IOException, CsvValidationException,CsvException;
    public void reconstruir() throws IOException;
    LinkedList<Contacto> buscar(String palabra) throws IOException;
    public void exportarSeleccion(String nuevoNombre,Stack<Contacto> pilaContactos) throws IOException;
}

