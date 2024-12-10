package model;
import javax.persistence.*;

@Entity
@Table(name = "cursada_alumnos")
public class CursadaAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cursada_id", nullable = false)
    private Cursada cursada;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @Column(name = "nota_parcial_1")
    private Double notaParcial1;

    @Column(name = "nota_parcial_2")
    private Double notaParcial2;

    public CursadaAlumno() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cursada getCursada() {
        return cursada;
    }

    public void setCursada(Cursada cursada) {
        this.cursada = cursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Double getNotaParcial1() {
        return notaParcial1;
    }

    public void setNotaParcial1(Double notaParcial1) {
        this.notaParcial1 = notaParcial1;
    }

    public Double getNotaParcial2() {
        return notaParcial2;
    }

    public void setNotaParcial2(Double notaParcial2) {
        this.notaParcial2 = notaParcial2;
    }
}
