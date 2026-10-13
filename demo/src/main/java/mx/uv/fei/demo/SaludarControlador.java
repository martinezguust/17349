package mx.uv.fei.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludarControlador {

    String nombre;
    Saludador saludador;

    // ruta en sustantivo plural
    @GetMapping("/saludos")
    public String saludar(){
        return "¡hola mundo! " + nombre;
    }

    @GetMapping("/despedidas")
    public String adios(){
        return "Adiós mundo!";
    }

    @PostMapping("/nombramientos")
    public void nombre(){
        this.nombre = "Gus";
    }

    @PutMapping("/nombramientos")
    public void met1(){
        nombre = "otro nombre";
    }

    @DeleteMapping("/nombramientos")
    public void met2(){
        nombre = null;
    }

    @GetMapping("/saludar/{nombre}")
    public Saludador met3(@PathVariable String nombre){
        System.out.println(nombre);
        return new Saludador(nombre);
    }

    @GetMapping("/saludar2/{nombreX}")
    public Saludador met4(@PathVariable("nombreX") String nombre){
        System.out.println(nombre);
        saludador = new Saludador(nombre);
        return saludador;
    }

    @PostMapping("/crearSaludador")
    public String crearSaludador(@RequestBody String nombre){
        this.saludador = new Saludador(nombre);
        return "Saludar creado " + nombre;
    }

    @GetMapping("/obtenerSaludador")
    public Saludador obtenerSaludador(){
        return saludador;
    }

    @RequestMapping("/")
    public String raiz(){
        return """
        <h1>Menú de opciones</h1>
        <ul>
            <li><a href='/saludos'>/saludos</a></li>
            <li><a href='/despedidas'>/despedidas</a></li>
            <li>/nombramientos (POST | PUT | DELETE)</li>
            <li><a href='/saludar/default'>/saludar/{nombre}</a></li>
            <li><a href='/saludar2/default2'>/saludar2/{nombreX}</a></li>
            <li>/crearSaludador (POST - body texto)</li>
            <li><a href='/obtenerSaludador'>/obtenerSaludador</a></li>
            <li>/query1?</li>
        </ul>
        """;
    }

    @GetMapping("/query1")
    public String query1(@RequestParam String nombre, @RequestParam String paterno, @RequestParam String materno){
        System.out.println(nombre);
        return nombre + " " + paterno + " " + materno;
    }

    @GetMapping("/query2")
    public String query2(@RequestParam(defaultValue = "default") String nombre){
        return "Nombre recibido: " + nombre;
    }
}