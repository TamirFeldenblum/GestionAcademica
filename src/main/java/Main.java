import Controller.CursadaAlumnoController;
import Controller.CursadaController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.JavalinRenderer;
import model.CursadaAlumno;
import utils.HandlebarsRenderer;
import Controller.AlumnoController;


public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
            config.enableDevLogging();
        }).start(7000);

        System.out.println("MAIN TAMIR: ");

        JavalinRenderer.register(new HandlebarsRenderer(), ".hbs");


        // Rutas
        app.get("/alumnos", AlumnoController.listarAlumnos);
        app.get("/asignaturas/{legajo}", AlumnoController.asignaturasPorAlumno);

        app.get("/editar-nota/{id}", CursadaAlumnoController.editarNota);
        app.post("/guardar-nota", CursadaAlumnoController.guardarNota);


    }
}
