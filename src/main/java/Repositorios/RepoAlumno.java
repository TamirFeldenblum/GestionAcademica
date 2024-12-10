package Repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Alumno;
import model.Materia;

import java.util.List;

public class RepoAlumno {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionAcademicaPU");

    public List<Alumno> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> obtenerAsignaturasPorAlumno(String legajo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT a.nombre, a.apellido, m.nombre, c.anio, c.cuatrimestre " +
                                    "FROM Cursada c " +
                                    "JOIN c.materia m " +
                                    "JOIN c.alumnos a " +
                                    "WHERE a.legajo = :legajo", Object[].class)
                    .setParameter("legajo", legajo)
                    .getResultList();
        } finally {
            em.close();
        }
    }




}
