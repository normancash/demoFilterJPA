import model.Autor;
import model.Libro;
import service.IDAO;
import service.ImplDAO;
import service.ServiceLibro;

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

    public static void main(String[] args) {
        //crearAutor();
        //deleteAutor();
        ServiceLibro serviceLibro = new ServiceLibro();
        serviceLibro.getAll("Libro.all",Libro.class)
                .forEach(u-> System.out.println(u));
    }
}
