//package py.com.progweb.prueba;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//
//@ExtendWith(MockitoExtension.class)
//@Testcontainers
//public abstract class BaseTest {
//
//    private AutoCloseable closeable;
//
//    // // Define un contenedor de Testcontainers para PostgreSQL si estás usando una base de datos
//    // public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13");
//
//    // Si estás utilizando JPA directamente en tus pruebas
//    protected EntityManagerFactory emf;
//    protected EntityManager em;
//
//    @BeforeEach
//    public void setUp() {
//        // Inicializa Testcontainers. Nota que esto se puede hacer de manera estática para todos los tests.
//        // postgreSQLContainer.start();
//
//        // Inicializa los mocks anotados con @Mock antes de cada test.
//        closeable = MockitoAnnotations.openMocks(this);
//
//        // Inicializa EntityManager para pruebas de JPA (si aplica)
//        // Asegúrate de que "testPersistenceUnit" sea reemplazado por tu unidad de persistencia de prueba
//        emf = Persistence.createEntityManagerFactory("testPersistenceUnit");
//        em = emf.createEntityManager();
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception {
//
//        // Limpieza después de cada test
//        // postgreSQLContainer.stop();
//
//        // Cierra recursos/mockitos después de cada test.
//        closeable.close();
//
//        // Cierra EntityManager y Factory después de cada test (si aplica)
//        if (em != null) {
//            em.close();
//        }
//        if (emf != null) {
//            emf.close();
//        }
//    }
//
//    // Métodos de utilidad que podrías querer agregar para apoyar tus pruebas unitarias
//    protected void beginTransaction() {
//        if (em != null) {
//            em.getTransaction().begin();
//        }
//    }
//
//    protected void commitTransaction() {
//        if (em != null) {
//            em.getTransaction().commit();
//        }
//    }
//
//    // Otros métodos de utilidad...
//}
