public class Usuario {
    private String nombre;
    private int edad;
    private String alias;

    public Person(String nombre, int edad, String alias) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
    }

    public List<Object> getInfo() {
        List<Object> info = new ArrayList<>();
        info.add(nombre);
        info.add(edad);
        info.add(alias);
        return info;
    }
}