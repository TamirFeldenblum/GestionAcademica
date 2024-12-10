package Repositorios;
import model.CursadaAlumno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepoCursadaAlumno {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionAcademicaPU");

    public CursadaAlumno obtenerPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(CursadaAlumno.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizarNotas(Long cursadaAlumnoId, Double nota1, Double nota2) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            CursadaAlumno cursadaAlumno = em.find(CursadaAlumno.class, cursadaAlumnoId);
            cursadaAlumno.setNotaParcial1(nota1);
            cursadaAlumno.setNotaParcial2(nota2);
            em.merge(cursadaAlumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
