package Controller;

import Repositorios.RepoAlumno;
import io.javalin.http.Handler;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;
import model.Alumno;
import model.Cursada;
import model.CursadaAlumno;

public class AlumnoController {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionAcademicaPU");

    // Handler para listar alumnos
    public static Handler listarAlumnos = ctx -> {
        EntityManager em = emf.createEntityManager();
        List<Alumno> alumnos = em.createQuery("FROM Alumno", Alumno.class).getResultList();
        em.close();
        ctx.render("/templates/alumnos.hbs", Map.of("alumnos", alumnos));
    };

    public static Handler asignaturasPorAlumno = ctx -> {
        String legajo = ctx.pathParam("legajo");
        EntityManager em = emf.createEntityManager();

        try {
            List<Object[]> resultados = em.createQuery(
                    "SELECT m.nombre, c.anio, c.cuatrimestre, ca.notaParcial1, ca.notaParcial2, ca.id " +
                            "FROM CursadaAlumno ca " +
                            "JOIN ca.cursada c " +
                            "JOIN ca.alumno a " +
                            "JOIN c.materia m " +
                            "WHERE a.legajo = :legajo"
            ).setParameter("legajo", legajo).getResultList();

            if (resultados.isEmpty()) {
                ctx.status(404).result("No se encontraron asignaturas para el alumno con legajo " + legajo);
            } else {
                ctx.render("/templates/asignaturas.hbs", Map.of("resultados", resultados));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al obtener asignaturas");
        } finally {
            em.close();
        }
    };


}


