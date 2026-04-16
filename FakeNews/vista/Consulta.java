package fakeNews.vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import fakeNews.dao.FakeNewsDAO;
import fakeNews.modelo.FakeNews;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class Consulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modeloTabla;

	/**
	 * Create the panel.
	 * 
	 * @param marco
	 */
	public Consulta() {

		setLayout(null);
		
		JTextField fieldBuscar = new JTextField();
		fieldBuscar.setBounds(10, 5, 200, 22);
		add(fieldBuscar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(220, 5, 89, 22);
		add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tituloBuscado = fieldBuscar.getText();
		        modeloTabla.setRowCount(0);
		        FakeNewsDAO dao = new FakeNewsDAO();
		        ArrayList<FakeNews> fk = dao.traerTodos();

		        if (tituloBuscado.equals("")) {  
		            for (FakeNews t : fk) {
		                modeloTabla.addRow(new Object[] {t.getLegajo(), t.getCategoria(),t.getCreador(),t.getDescripcion(),t.getFecha(),t.getMedios(),t.getTitulo()
		                });
		            }
		        } else {
		            for (FakeNews t : fk) {
		                if (tituloBuscado.equals(t.getTitulo())) {
		                    modeloTabla.addRow(new Object[] {t.getLegajo(), t.getCategoria(),t.getCreador(),t.getDescripcion(),t.getFecha(),t.getMedios(),t.getTitulo()
		                    });
		                }
		            }
		        }
					
		  
				//modeloTabla.addRow(new Object[] {fk.getIdFakeNews(), fk.getCategoria(), fk.getCreador(), fk.getDescripcion(), fk.getFecha(), fk.getMedios(), fk.getTitulo() });
		        
		    }
		});



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 30, 1283, 299);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTabla = new DefaultTableModel(new Object[] {"legajo" ,"categoria", "nombre creador", "descripcion", "fecha", "medios", "titulo" }, 0
		);
		table.setModel(modeloTabla);

		cargarTabla(); 

		

		JButton btnNuevo = new JButton("Agregar");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new AltaModificacionFakeNews(null));
				marco.validate();

			}
		});
		btnNuevo.setBounds(139, 480, 89, 23);
		add(btnNuevo);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FakeNewsDAO eDao = new FakeNewsDAO();
				int legajoSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
		        int id = eDao.obtenerIDporLegajo(legajoSeleccionado);
		        eDao.eliminar(id);
		        cargarTabla();
			}
		});
		btnEliminar.setBounds(274, 480, 89, 23);
		add(btnEliminar);

		JButton btnModificcar = new JButton("Modificar");
		btnModificcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FakeNewsDAO fDao = new FakeNewsDAO();
				// Opto por buscar el estudiante en la base de datos (puede haber otras
				// soluciones más óptimas).
				 int legajoSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
			        int id = fDao.obtenerIDporLegajo(legajoSeleccionado);
			        FakeNews refAModificar = fDao.buscarPorId(id);
			        JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			        marco.setContentPane(new AltaModificacionFakeNews(refAModificar));
			        marco.validate();

			}
		});
		btnModificcar.setBounds(406, 480, 89, 23);
		add(btnModificcar);
		
		JButton Volver = new JButton("Volver");
		Volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new Consulta());
				marco.validate();

			}
		});
		Volver.setBounds(548, 480, 89, 23);
		add(Volver);
        JButton btnDivulgacion = new JButton("Divulgacion");
        btnDivulgacion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*int filaSeleccionada = table.getSelectedRow();
        		int idFake =(int) table.getValueAt(filaSeleccionada, 0);
        		FakeNewsDAO fDao = new FakeNewsDAO();
        		FakeNews fakeSeleccionada = fDao.buscarPorId(idFake);*/
        		FakeNewsDAO fDao = new FakeNewsDAO();
				 int legajoSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
			     int id = fDao.obtenerIDporLegajo(legajoSeleccionado);
			     FakeNews refAModificar = fDao.buscarPorId(id);
        		JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				String medio = refAModificar.getMedios();
        		if (medio.equals("3")) {
                    marco.setContentPane(new AltaModificacionTraduccionEscrita(null));
                } else if (medio.equals("2")) {
                    marco.setContentPane(new AltaModificacionTraduccionAudioVisual(null));
                } else if (medio.equals("1")) {
                	marco.setContentPane(new AltaModificacionRedSocial(null));
                }
                    
        		

                marco.validate();
        	}
        
        });
		btnDivulgacion.setBounds(139, 532, 89, 23);
		add(btnDivulgacion);
		
		JButton btnRefutacion = new JButton("Refutacion");
		btnRefutacion.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

		        int legajoSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);

		        // Pasás el LEGAJO directamente al panel de refutación
		        marco.setContentPane(new AltaModificacionRefutacion(legajoSeleccionado));

		        marco.validate();
		    }
		});

		btnRefutacion.setBounds(274, 532, 89, 23);
		add(btnRefutacion);
		
	}
	
	private void cargarTabla() {
	    modeloTabla.setRowCount(0);
	    FakeNewsDAO rDao = new FakeNewsDAO();
	    ArrayList<FakeNews> lista = rDao.traerTodos();

	    for (FakeNews f : lista) {
	        modeloTabla.addRow(new Object[]{f.getLegajo(),f.getCategoria(),f.getCreador(),f.getDescripcion(),f.getFecha(),f.getMedios(),f.getTitulo()
	        });
	    }
	}

}
