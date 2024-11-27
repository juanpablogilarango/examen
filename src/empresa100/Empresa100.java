package empresa100;

import java.util.ArrayList;
import java.util.Scanner;

public class Empresa100 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario100> usuarios = new ArrayList<>();

        // Usuario inicial como administrador
        usuarios.add(new Usuario100("Admin Principal", "admin@empresa100.com", "Administrador"));

        while (true) {
            System.out.println("\nGestión de Usuarios:");
            System.out.println("1. Ingresar como Empleado");
            System.out.println("2. Ingresar como Supervisor");
            System.out.println("3. Ingresar como Administrador");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir línea

            switch (opcion) {
                case 1:
                    // Acceso de Empleado
                    accesoEmpleado(scanner, usuarios);
                    break;
                case 2:
                    // Acceso de Supervisor
                    accesoSupervisor(scanner, usuarios);
                    break;
                case 3:
                    // Acceso de Administrador
                    accesoAdministrador(scanner, usuarios);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void accesoEmpleado(Scanner scanner, ArrayList<Usuario100> usuarios) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        Usuario100 empleado = buscarUsuarioPorNombre(usuarios, nombre, "Empleado");
        if (empleado != null) {
            System.out.println("\n--- Detalles del Empleado ---");
            empleado.mostrarDetalles100();
        } else {
            System.out.println("Empleado no encontrado o no tiene permisos.");
        }
    }

    private static void accesoSupervisor(Scanner scanner, ArrayList<Usuario100> usuarios) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        Usuario100 supervisor = buscarUsuarioPorNombre(usuarios, nombre, "Supervisor");
        if (supervisor != null) {
            System.out.println("\n--- Detalles de todos los usuarios ---");
            for (Usuario100 usuario : usuarios) {
                usuario.mostrarDetalles100();
                System.out.println("----");
            }
        } else {
            System.out.println("Supervisor no encontrado o no tiene permisos.");
        }
    }

    private static void accesoAdministrador(Scanner scanner, ArrayList<Usuario100> usuarios) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        Usuario100 administrador = buscarUsuarioPorNombre(usuarios, nombre, "Administrador");
        if (administrador != null) {
            while (true) {
                System.out.println("\n--- Opciones del Administrador ---");
                System.out.println("1. Agregar Usuario");
                System.out.println("2. Ver Usuarios");
                System.out.println("3. Eliminar Usuario");
                System.out.println("4. Salir al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir línea

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del nuevo usuario: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Email del nuevo usuario: ");
                        String nuevoEmail = scanner.nextLine();
                        System.out.print("Rol del nuevo usuario (Empleado/Supervisor/Administrador): ");
                        String nuevoRol = scanner.nextLine();
                        usuarios.add(new Usuario100(nuevoNombre, nuevoEmail, nuevoRol));
                        System.out.println("Usuario agregado.");
                        break;
                    case 2:
                        System.out.println("\n--- Lista de Usuarios ---");
                        for (Usuario100 usuario : usuarios) {
                            usuario.mostrarDetalles100();
                            System.out.println("----");
                        }
                        break;
                    case 3:
                        System.out.print("Nombre del usuario a eliminar: ");
                        String eliminarNombre = scanner.nextLine();
                        boolean eliminado = usuarios.removeIf(usuario -> usuario.getNombre().equals(eliminarNombre));
                        System.out.println(eliminado ? "Usuario eliminado." : "Usuario no encontrado.");
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("Administrador no encontrado o no tiene permisos.");
        }
    }

    private static Usuario100 buscarUsuarioPorNombre(ArrayList<Usuario100> usuarios, String nombre, String rol) {
        for (Usuario100 usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre) && usuario.getRol().equalsIgnoreCase(rol)) {
                return usuario;
            }
        }
        return null;
    }
}
