package Controller;

import Repositorios.RepoCursadaAlumno;
import io.javalin.http.Handler;
import model.CursadaAlumno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

public class CursadaAlumnoController {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionAcademicaPU");
    private static final RepoCursadaAlumno repoCursadaAlumno = new RepoCursadaAlumno();

    public static Handler editarNota = ctx -> {
        Long id = Long.valueOf(ctx.pathParam("id"));
        EntityManager em = emf.createEntityManager();

        try {
            CursadaAlumno cursadaAlumno = em.find(CursadaAlumno.class, id);

            if (cursadaAlumno == null) {
                ctx.status(404).result("No se encontró la cursada para el ID especificado");
            } else {
                ctx.render("/templates/notas.hbs", Map.of("cursadaAlumno", cursadaAlumno));
            }
        } finally {
            em.close();
        }
    };

    public static Handler guardarNota = ctx -> {
        Long id = Long.valueOf(ctx.formParam("id"));
        Double notaParcial1 = Double.valueOf(ctx.formParam("notaParcial1"));
        Double notaParcial2 = Double.valueOf(ctx.formParam("notaParcial2"));

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            CursadaAlumno cursadaAlumno = em.find(CursadaAlumno.class, id);

            if (cursadaAlumno != null) {
                cursadaAlumno.setNotaParcial1(notaParcial1);
                cursadaAlumno.setNotaParcial2(notaParcial2);
                em.getTransaction().commit();
                ctx.redirect("/asignaturas/" + cursadaAlumno.getAlumno().getLegajo());
            } else {
                ctx.status(404).result("No se encontró la cursada para el ID especificado");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            ctx.status(500).result("Error al guardar las notas");
        } finally {
            em.close();
        }
    };

}
