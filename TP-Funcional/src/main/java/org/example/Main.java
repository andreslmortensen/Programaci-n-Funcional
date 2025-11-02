package org.example;


import org.example.entidades.Alumno;
import org.example.entidades.Empleado;
import org.example.entidades.Libro;
import org.example.entidades.Producto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        caso1Alumnos();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso2Productos();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso3Libros();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso4Empleados();
    }

    // ============= CASO 1: ALUMNOS =============
    public static void caso1Alumnos() {
        System.out.println("CASO 1: ALUMNOS");
        System.out.println("=".repeat(80));

        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Juan", 8.5, "3A"),
                new Alumno("María", 9.0, "3B"),
                new Alumno("Pedro", 6.0, "3A"),
                new Alumno("Ana", 7.5, "3B"),
                new Alumno("Luis", 5.5, "3A"),
                new Alumno("Sofía", 9.5, "3C"),
                new Alumno("Carlos", 7.0, "3B"),
                new Alumno("Laura", 8.0, "3C")
        );

        // 1. Nombres de aprobados (nota ≥ 7) en mayúsculas y ordenados
        System.out.println("1. Alumnos aprobados (nota ≥ 7) en mayúsculas:");
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        aprobados.forEach(System.out::println);

        // 2. Promedio general de notas
        System.out.println("\n2. Promedio general de notas:");
        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio: %.2f\n", promedioGeneral);

        // 3. Agrupar alumnos por curso
        System.out.println("\n3. Alumnos agrupados por curso:");
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        alumnosPorCurso.forEach((curso, lista) -> {
            System.out.println("Curso " + curso + ":");
            lista.forEach(a -> System.out.println("  - " + a));
        });

        // 4. Los 3 mejores promedios
        System.out.println("\n4. Los 3 mejores promedios:");
        alumnos.stream()
                .sorted((a1, a2) -> Double.compare(a2.getNota(), a1.getNota()))
                .limit(3)
                .forEach(a -> System.out.println(a.getNombre() + ": " + a.getNota()));
    }

    // ============= CASO 2: PRODUCTOS =============
    public static void caso2Productos() {
        System.out.println("CASO 2: PRODUCTOS");
        System.out.println("=".repeat(80));

        List<Producto> productos = Arrays.asList(
                new Producto("Laptop", "Electrónica", 1500.00, 10),
                new Producto("Mouse", "Electrónica", 25.00, 50),
                new Producto("Teclado", "Electrónica", 80.00, 30),
                new Producto("Monitor", "Electrónica", 300.00, 15),
                new Producto("Silla", "Muebles", 150.00, 20),
                new Producto("Escritorio", "Muebles", 250.00, 8),
                new Producto("Lámpara", "Iluminación", 45.00, 40),
                new Producto("Auriculares", "Electrónica", 120.00, 25)
        );

        // 1. Productos con precio > 100, ordenados por precio descendente
        System.out.println("1. Productos con precio mayor a $100 (descendente):");
        productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                .forEach(System.out::println);

        // 2. Agrupar por categoría y calcular stock total
        System.out.println("\n2. Stock total por categoría:");
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        stockPorCategoria.forEach((cat, stock) ->
                System.out.println(cat + ": " + stock + " unidades"));

        // 3. String con nombre y precio separado por ";"
        System.out.println("\n3. Productos (nombre;precio) separados por ';':");
        String productosString = productos.stream()
                .map(p -> p.getNombre() + ";" + p.getPrecio())
                .collect(Collectors.joining(";"));
        System.out.println(productosString);

        // 4. Precio promedio general y por categoría
        System.out.println("\n4. Precio promedio:");
        double precioPromedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio general: $%.2f\n", precioPromedioGeneral);

        System.out.println("Promedio por categoría:");
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        promedioPorCategoria.forEach((cat, prom) ->
                System.out.printf("  %s: $%.2f\n", cat, prom));
    }

    // ============= CASO 3: LIBROS =============
    public static void caso3Libros() {
        System.out.println("CASO 3: LIBROS");
        System.out.println("=".repeat(80));

        List<Libro> libros = Arrays.asList(
                new Libro("Cien años de soledad", "Gabriel García Márquez", 496, 25.99),
                new Libro("El principito", "Antoine de Saint-Exupéry", 96, 12.50),
                new Libro("1984", "George Orwell", 328, 18.99),
                new Libro("Don Quijote", "Miguel de Cervantes", 863, 35.00),
                new Libro("Rayuela", "Julio Cortázar", 600, 28.50),
                new Libro("La sombra del viento", "Carlos Ruiz Zafón", 565, 22.99),
                new Libro("El amor en tiempos de cólera", "Gabriel García Márquez", 368, 24.99),
                new Libro("Ficciones", "Jorge Luis Borges", 174, 15.99)
        );

        // 1. Títulos de libros con más de 300 páginas, ordenados alfabéticamente
        System.out.println("1. Libros con más de 300 páginas (orden alfabético):");
        libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .forEach(System.out::println);

        // 2. Promedio de páginas de todos los libros
        System.out.println("\n2. Promedio de páginas:");
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio: %.2f páginas\n", promedioPaginas);

        // 3. Agrupar por autor y contar libros
        System.out.println("\n3. Cantidad de libros por autor:");
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        librosPorAutor.forEach((autor, cantidad) ->
                System.out.println(autor + ": " + cantidad + " libro(s)"));

        // 4. Libro más caro
        System.out.println("\n4. Libro más caro:");
        libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .ifPresent(l -> System.out.println(l));
    }

    // ============= CASO 4: EMPLEADOS =============
    public static void caso4Empleados() {
        System.out.println("CASO 4: EMPLEADOS");
        System.out.println("=".repeat(80));

        List<Empleado> empleados = Arrays.asList(
                new Empleado("Roberto", "Ventas", 2500.00, 35),
                new Empleado("Carmen", "Marketing", 3000.00, 28),
                new Empleado("Diego", "IT", 3500.00, 30),
                new Empleado("Lucía", "Ventas", 1800.00, 24),
                new Empleado("Fernando", "IT", 4000.00, 42),
                new Empleado("Patricia", "Marketing", 2800.00, 32),
                new Empleado("Miguel", "RRHH", 2200.00, 26),
                new Empleado("Valentina", "RRHH", 1900.00, 23)
        );

        // 1. Empleados con salario > 2000, ordenados por salario descendente
        System.out.println("1. Empleados con salario mayor a $2000 (descendente):");
        empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted((e1, e2) -> Double.compare(e2.getSalario(), e1.getSalario()))
                .forEach(System.out::println);

        // 2. Salario promedio general
        System.out.println("\n2. Salario promedio general:");
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio: $%.2f\n", salarioPromedio);

        // 3. Agrupar por departamento y sumar salarios
        System.out.println("\n3. Suma de salarios por departamento:");
        Map<String, Double> salariosPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        salariosPorDepartamento.forEach((dept, total) ->
                System.out.printf("%s: $%.2f\n", dept, total));

        // 4. Nombres de los 2 empleados más jóvenes
        System.out.println("\n4. Los 2 empleados más jóvenes:");
        empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .forEach(System.out::println);
    }
}