package tienda;

public class Departamento {
    private int id;
    private String nombre;
    private Articulo[] colaArticulos;
    private int front, rear, count;
    private static final int MAX_ART = 20;

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.colaArticulos = new Articulo[MAX_ART];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public boolean enqueue(Articulo art) {
        if (count == MAX_ART) return false;
        rear = (rear + 1) % MAX_ART;
        colaArticulos[rear] = art;
        count++;
        return true;
    }
    public Articulo dequeue() {
        if (count == 0) return null;
        Articulo art = colaArticulos[front];
        front = (front + 1) % MAX_ART;
        count--;
        return art;
    }
    public Articulo[] getArticulos() {
        Articulo[] arr = new Articulo[count];
        for (int i = 0; i < count; i++) {
            arr[i] = colaArticulos[(front + i) % MAX_ART];
        }
        return arr;
    }
    public int getCount() { return count; }
}
