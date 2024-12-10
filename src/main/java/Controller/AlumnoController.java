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
        System.out.println("Handler asignaturasPorAlumno invocado");
        String legajo = ctx.pathParam("legajo");
        System.out.println("Legajo recibido: " + legajo);
        EntityManager em = emf.createEntityManager();

        try {
            // Consulta directa para obtener materias basadas en el legajo del alumno
            List<Object[]> resultados = em.createQuery(
                    "SELECT a.nombre, a.apellido, m.nombre, c.anio, c.cuatrimestre " +
                            "FROM Alumno a " +
                            "JOIN a.cursadas c " +
                            "JOIN c.materia m " +
                            "WHERE a.legajo = :legajo"
            ).setParameter("legajo", legajo).getResultList();

            // Renderizar los datos a la plantilla
            ctx.render("/templates/asignaturas.hbs", Map.of("resultados", resultados));
        } catch (Exception e) {
            ctx.status(404).result("No se encontraron asignaturas para el alumno con legajo " + legajo);
        } finally {
            em.close();
        }
    };



}
