package Controller;

import io.javalin.http.Handler;
import Repositorios.RepoAlumno;

import java.util.Map;

public class MateriasController {
    private static final RepoAlumno repoAlumno = new RepoAlumno();

    public static Handler listarMateriasPorAlumno = ctx -> {
        String legajo = ctx.pathParam("legajo"); // Parámetro de la URL
        var materias = repoAlumno.obtenerAsignaturasPorAlumno(legajo);

        ctx.render("/templates/asignaturas_por_alumno.hbs", Map.of("legajo", legajo, "materias", materias));
    };
}
