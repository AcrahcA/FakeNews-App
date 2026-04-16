package fakeNews.vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import fakeNews.dao.RefutadorDAO;
import fakeNews.modelo.Refutador;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class ConsultaRefutador extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modeloTabla;

	/**
	 * Create the panel.
	 * 
	 * @param marco
	 */
	public ConsultaRefutador() {

		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 32, 309, 197);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTabla = new DefaultTableModel(new Object[] {"legajo", "Nombre", "Apellido", "Medios Presentes" }, 0);
		table.setModel(modeloTabla);

		cargarTabla();

		JButton btnNuevo = new JButton("Agregar");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new AltaModificacionRefutador(null));
				marco.validate();

			}
		});
		btnNuevo.setBounds(400, 29, 89, 23);
		add(btnNuevo);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefutadorDAO rDao = new RefutadorDAO();
		        int legajoSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
		        int id = rDao.obtenerIDporLegajo(legajoSeleccionado);
		        rDao.eliminar(id);
		        cargarTabla();
			}
		});
		btnEliminar.setBounds(400, 63, 89, 23);
		add(btnEliminar);

		JButton btnModificcar = new JButton("Modificar");
		btnModificcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefutadorDAO rDao = new RefutadorDAO();

		        int legajoSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
		        int id = rDao.obtenerIDporLegajo(legajoSeleccionado);
		        Refutador refAModificar = rDao.buscarPorId(id);
		        JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		        marco.setContentPane(new AltaModificacionRefutador(refAModificar));
		        marco.validate();

			}
		});
		btnModificcar.setBounds(400, 97, 89, 23);
		add(btnModificcar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new Pantalla());
				marco.validate();
			}
		});
		btnVolver.setBounds(400, 146, 89, 23);
		add(btnVolver);

	}

	private void cargarTabla() {
		// Vacio la tabla
		modeloTabla.setRowCount(0);
		RefutadorDAO eDao = new RefutadorDAO();
		ArrayList<Refutador> refutadores = eDao.traerTodos();
		for (Refutador e : refutadores) {
			modeloTabla.addRow(new Object[] { e.getLegajo(), e.getNombre(), e.getApellido(), e.getMedios_presentes() });
		}
	}
	

}
