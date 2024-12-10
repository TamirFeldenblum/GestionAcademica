import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.JavalinRenderer;
import utils.HandlebarsRenderer;
import Controller.AlumnoController;


public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
         //   config.addStaticFiles("/public", Location.CLASSPATH); // Archivos estáticos
            config.enableDevLogging(); // Logs detallados
        }).start(7000);

        System.out.println("MAIN TAMIR: ");

        // Registrar el renderizador Handlebars
        JavalinRenderer.register(new HandlebarsRenderer(), ".hbs");


        // Rutas
        app.get("/alumnos", AlumnoController.listarAlumnos); // Listar alumnos
        app.get("/asignaturas/{legajo}", AlumnoController.asignaturasPorAlumno);

    }
}
