//Jorge Chaves Montiel
//Cèdula: 116910248
package tienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private Departamento[] pilaDepartamentos = new Departamento[20];
    private int deptTop = 0;
    private int nextDeptId = 1, nextArtId = 1;

    private DefaultTableModel modelDept;
    private JTable tableDept;

    public MainFrame() {
        super("Gestión de Tienda por Departamentos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Registrar Departamentos", createPanelRegistrarDept());
        tabs.addTab("Registrar Artículos", createPanelRegistrarArt());
        tabs.addTab("Eliminar Artículos",   createPanelEliminarArt());
        tabs.addTab("Trasladar Artículos",   createPanelTrasladarArt());
        tabs.addTab("Eliminar Departamentos", createPanelEliminarDept());
        add(tabs);
    }

    private JPanel createPanelRegistrarDept() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel();
        JTextField tfNombre = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar Departamento");
        form.add(new JLabel("Nombre:")); form.add(tfNombre); form.add(btnAgregar);
        panel.add(form, BorderLayout.NORTH);

        modelDept = new DefaultTableModel(new Object[]{"ID","Nombre"},0);
        tableDept = new JTable(modelDept);
        panel.add(new JScrollPane(tableDept), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            String nombre = tfNombre.getText().trim();
            if (nombre.isEmpty() || deptTop == pilaDepartamentos.length) return;
            Departamento d = new Departamento(nextDeptId++, nombre);
            pilaDepartamentos[deptTop++] = d;
            modelDept.addRow(new Object[]{d.getId(), d.getNombre()});
            tfNombre.setText("");
        });
        return panel;
    }

    private JPanel createPanelRegistrarArt() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel();
        JComboBox<String> cbDept = new JComboBox<>();
        JTextField tfArt = new JTextField(15);
        JComboBox<String> cbCat = new JComboBox<>(new String[]{
            "Ropa y accesorios","Electrónica","Hogar y muebles",
            "Belleza y cuidado personal","Deportes y aire libre",
            "Juguetes y juegos","Alimentos y bebidas"
        });
        JButton btnArt = new JButton("Agregar Artículo");
        form.add(new JLabel("Depto:")); form.add(cbDept);
        form.add(new JLabel("Nombre:")); form.add(tfArt);
        form.add(new JLabel("Categoría:")); form.add(cbCat);
        form.add(btnArt);
        panel.add(form, BorderLayout.NORTH);

        DefaultTableModel modelArt = new DefaultTableModel(new Object[]{"ID","Nombre","Cat."},0);
        JTable tableArt = new JTable(modelArt);
        panel.add(new JScrollPane(tableArt), BorderLayout.CENTER);

        modelDept.addTableModelListener(evt -> {
            cbDept.removeAllItems();
            for (int i = 0; i < deptTop; i++)
                cbDept.addItem(pilaDepartamentos[i].getNombre());
        });

        btnArt.addActionListener(e -> {
            int idx = cbDept.getSelectedIndex();
            if (idx<0) return;
            String nombre = tfArt.getText().trim();
            if (nombre.isEmpty()) return;
            Articulo art = new Articulo(nextArtId++, nombre, (String)cbCat.getSelectedItem());
            if (!pilaDepartamentos[idx].enqueue(art)) {
                JOptionPane.showMessageDialog(this, "Cola llena.");
                return;
            }
            modelArt.addRow(new Object[]{art.getId(), art.getNombre(), art.getCategoria()});
            tfArt.setText("");
        });
        return panel;
    }

    private JPanel createPanelEliminarArt() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        JComboBox<String> cbDept = new JComboBox<>();
        JButton btnDel = new JButton("Eliminar artículo");
        top.add(new JLabel("Depto:")); top.add(cbDept); top.add(btnDel);
        panel.add(top, BorderLayout.NORTH);

        DefaultTableModel md = new DefaultTableModel(new Object[]{"ID","Nombre","Cat."},0);
        JTable tbl = new JTable(md);
        panel.add(new JScrollPane(tbl), BorderLayout.CENTER);

        modelDept.addTableModelListener(e -> {
            cbDept.removeAllItems();
            for (int i = 0; i < deptTop; i++)
                cbDept.addItem(pilaDepartamentos[i].getNombre());
        });
        cbDept.addActionListener(e -> {
            md.setRowCount(0);
            int i = cbDept.getSelectedIndex();
            if (i<0) return;
            for (Articulo a : pilaDepartamentos[i].getArticulos())
                md.addRow(new Object[]{a.getId(), a.getNombre(), a.getCategoria()});
        });
        btnDel.addActionListener(e -> {
            int i = cbDept.getSelectedIndex();
            if (i<0) return;
            pilaDepartamentos[i].dequeue();
            cbDept.getActionListeners()[0].actionPerformed(null);
        });
        return panel;
    }

    private JPanel createPanelTrasladarArt() {
        JPanel panel = new JPanel();
        JComboBox<String> cbO = new JComboBox<>(), cbD = new JComboBox<>();
        JButton btnT = new JButton("Trasladar todos");
        panel.add(new JLabel("Origen:")); panel.add(cbO);
        panel.add(new JLabel("Destino:")); panel.add(cbD);
        panel.add(btnT);

        modelDept.addTableModelListener(e -> {
            cbO.removeAllItems(); cbD.removeAllItems();
            for (int i = 0; i < deptTop; i++) {
                cbO.addItem(pilaDepartamentos[i].getNombre());
                cbD.addItem(pilaDepartamentos[i].getNombre());
            }
        });

        btnT.addActionListener(e -> {
            int o = cbO.getSelectedIndex(), d = cbD.getSelectedIndex();

            if (deptTop < 2) {
                JOptionPane.showMessageDialog(this, "Debe haber al menos dos departamentos para trasladar artículos.");
                return;
            }
            if (o < 0 || d < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar ambos departamentos.");
                return;
            }
            if (o == d) {
                JOptionPane.showMessageDialog(this, "No se puede trasladar: Origen y destino son iguales.");
                return;
            }
            if (pilaDepartamentos[o].getCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se puede trasladar: El departamento origen no tiene artículos.");
                return;
            }

            while (pilaDepartamentos[o].getCount() > 0) {
                pilaDepartamentos[d].enqueue(pilaDepartamentos[o].dequeue());
            }
            
        });

        return panel;
    }


    private JPanel createPanelEliminarDept() {
        JPanel panel = new JPanel();
        JButton btn = new JButton("Eliminar último depto");
        panel.add(btn);
        btn.addActionListener(e -> {
            if (deptTop==0) return;
            Departamento last = pilaDepartamentos[deptTop-1];
            if (last.getCount()>0) {
                JOptionPane.showMessageDialog(this, "No puede eliminar: cola no vacía.");
                return;
            }
            deptTop--;
            modelDept.removeRow(deptTop);
        });
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
