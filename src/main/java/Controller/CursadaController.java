package Controller;

import io.javalin.http.Handler;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.CursadaAlumno;

import java.util.Map;

public class CursadaController {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionAcademicaPU");

    // Handler para mostrar el formulario de edición de notas
    public static Handler editarNotas = ctx -> {
        Long id = Long.parseLong(ctx.pathParam("id"));
        EntityManager em = emf.createEntityManager();

        try {
            // Obtener la relación CursadaAlumno por su ID
            CursadaAlumno cursadaAlumno = em.find(CursadaAlumno.class, id);

            if (cursadaAlumno == null) {
                ctx.status(404).result("Cursada no encontrada.");
                return;
            }

            // Renderizar la vista con la información actual
            ctx.render("/templates/editar_notas.hbs", Map.of(
                    "cursadaAlumno", cursadaAlumno
            ));
        } finally {
            em.close();
        }
    };

    // Handler para procesar el formulario y guardar las notas
    public static Handler guardarNotas = ctx -> {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Double nota1 = Double.parseDouble(ctx.formParam("nota1"));
        Double nota2 = Double.parseDouble(ctx.formParam("nota2"));

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CursadaAlumno cursadaAlumno = em.find(CursadaAlumno.class, id);

            if (cursadaAlumno == null) {
                ctx.status(404).result("Cursada no encontrada.");
                return;
            }

            // Actualizar las notas
            cursadaAlumno.setNotaParcial1(nota1);
            cursadaAlumno.setNotaParcial2(nota2);

            em.getTransaction().commit();

            ctx.redirect("/alumnos/" + cursadaAlumno.getAlumno().getLegajo() + "/asignaturas");
        } finally {
            em.close();
        }
    };
}
