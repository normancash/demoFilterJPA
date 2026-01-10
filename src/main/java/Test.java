import config.Global;
import config.SecurityContext;
import model.Autor;
import model.Libro;
import model.Usuario;
import model.Ventas;
import service.IDAO;
import service.ImplDAO;
import service.ServiceLibro;

import java.util.List;

public class Test {
    public static void deleteAutor() {
        ImplDAO dao = new ImplDAO();
        dao.delete("a3a7c00e-17b3-4403-a5e5-bf48edc2646d",Autor.class);
    }

    public static void crearAutor(){
        ImplDAO implDAO = new ImplDAO();
        Autor autor = new Autor();
        autor.setNombre("Norman");
        autor.setApellido("Cash");
        implDAO.insert(autor);
    }

    public static void handleLibro(){
        ServiceLibro serviceLibro = new ServiceLibro();
        /*Libro libro = new Libro();
        libro.setName("Azul");
        libro.setIsbn("234234dr23");*/
        Libro libro = serviceLibro.findById("40c8a155-bdfa-475d-86fe-c0f3ef2e7102",Libro.class);
        serviceLibro.delete(libro.getId());
        //serviceLibro.update(libro);
        serviceLibro.getAll("Libro.all",Libro.class)
                .forEach(u-> System.out.println(u));
    }

    public static void crearUsuarioVenta(){
        ImplDAO implDAO = new ImplDAO();
        Usuario usuario = new Usuario();
        usuario.setNombre("Norman");
        usuario.setApellido("Cash");
        implDAO.insert(usuario);
        vender(usuario,"Lapicero",1000);
        Usuario usuario1 =  new Usuario();
        usuario1.setNombre("Andrea");
        usuario1.setApellido("Duarte");
        implDAO.insert(usuario1);
        vender(usuario1,"Lapicero",1000);
        vender(usuario1,"Cartulina",1000);
        vender(usuario1,"Engrapador",1000);
        Usuario usuario2 =  new Usuario();
        usuario2.setNombre("Sofia");
        usuario2.setApellido("Fuentes");
        implDAO.insert(usuario2);
        vender(usuario2,"Lapicero",1000);
    }

    public static void vender(Usuario usuario,String producto,double cantidad){
        ImplDAO implDAO = new ImplDAO();
        Ventas ventas = new Ventas();
        ventas.setUsuario(usuario);
        ventas.setProducto(producto);
        ventas.setTotalVenta(cantidad);
        implDAO.insert(ventas);
    }

    public static void main(String[] args) {
        ServiceLibro serviceLibro = new ServiceLibro();
        serviceLibro.getAll("Libro.all",Libro.class);
        //crearUsuarioVenta();
        //Simular login.
        ImplDAO implDAO = new ImplDAO();
        Usuario usuario = implDAO.findById("dbafa463-f4d7-42b6-bade-43ef851ec9a3",Usuario.class);
        SecurityContext.setCurrentUser(usuario.getId());
        implDAO.getAll("Ventas.all",Ventas.class).forEach(u-> System.out.println(u));
    }


}
