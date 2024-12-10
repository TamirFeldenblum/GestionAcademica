package Repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import model.Materia;

public class RepoMateria {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionAcademicaPU");

    public List<Materia> obtenerTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Materia m", Materia.class).getResultList();
        } finally {
            em.close();
        }
    }

    public static void cerrarEntityManagerFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
