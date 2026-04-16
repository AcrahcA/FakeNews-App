package fakeNews.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import fakeNews.dao.RefutadorDAO;
import fakeNews.dao.tipoMedioDAO;
import fakeNews.modelo.Refutador;

public class AltaModificacionRefutador extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField FieldNombre;
	private JTextField FieldApellido;
	private JTextField Legajo;
	private int legajoOriginal;



	
	/**
	 * Create the frame.
	 * @param fakeNewsAModificar 
	 */
	public AltaModificacionRefutador(Refutador refutadorAModificar) {
	
		setLayout(null);
		
		FieldNombre = new JTextField();
		FieldNombre.setBounds(20, 92, 280, 22);
		add(FieldNombre);
		FieldNombre.setColumns(10);
		
		JLabel NombreFake = new JLabel("Nombre");
		NombreFake.setForeground(new Color(255, 0, 128));
		NombreFake.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		NombreFake.setBounds(20, 70, 256, 22);
		add(NombreFake);
		
		JLabel tituloPantalla = new JLabel("Refutador");
		tituloPantalla.setForeground(new Color(255, 128, 192));
		tituloPantalla.setBackground(new Color(255, 128, 255));
		tituloPantalla.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 50));
		tituloPantalla.setBounds(20, 11, 554, 45);
		add(tituloPantalla);
		
		JLabel ApellidoFake = new JLabel("Ingresa el apellido");
		ApellidoFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		ApellidoFake.setForeground(new Color(255, 0, 128));
		ApellidoFake.setBounds(20, 116, 240, 29);
		add(ApellidoFake);
		
		FieldApellido = new JTextField();
		FieldApellido.setBounds(20, 144, 280, 20);
		add(FieldApellido);
		FieldApellido.setColumns(10);
		
		JLabel MediosFake = new JLabel("Ingresa medios Presentes");
		MediosFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		MediosFake.setForeground(new Color(255, 0, 128));
		MediosFake.setBounds(20, 171, 280, 29);
		add(MediosFake);
		
		JLabel lblLegajo = new JLabel("Legajo");
		lblLegajo.setForeground(new Color(255, 0, 128));
		lblLegajo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		lblLegajo.setBounds(20, 230, 256, 22);
		add(lblLegajo);
		
		Legajo = new JTextField();
		Legajo.setColumns(10);
		Legajo.setBounds(20, 255, 280, 22);
		add(Legajo);
		
		
		
		
		JButton botonSiguiente1 = new JButton("Tablas Fake");
		botonSiguiente1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new Consulta());
				marco.validate();
			}
		});
		botonSiguiente1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		botonSiguiente1.setForeground(new Color(255, 0, 128));
		botonSiguiente1.setBackground(new Color(255, 128, 255));
		botonSiguiente1.setBounds(322, 374, 108, 22);
		add(botonSiguiente1);
		
		// ComboBox de medios
		JComboBox<String> boxMedios = new JComboBox<String>();
		boxMedios.setMaximumRowCount(3);
		boxMedios.setBounds(20, 197, 280, 22);
		add(boxMedios);
		tipoMedioDAO mDao = new tipoMedioDAO();
		for (String medios : mDao.traerTodas()) {
		    boxMedios.addItem(medios);
		}

		// Si estamos modificando, cargamos los datos
		if (refutadorAModificar != null) {
		    FieldNombre.setText(refutadorAModificar.getNombre());
		    FieldApellido.setText(refutadorAModificar.getApellido());
		    boxMedios.setSelectedItem(refutadorAModificar.getMedios_presentes());
		    Legajo.setText(String.valueOf(refutadorAModificar.getLegajo()));

		    // Guardamos el legajo original para el DAO
		    legajoOriginal = refutadorAModificar.getLegajo();
		}

		JButton Guardar = new JButton("Guardar");
	    Guardar.setBounds(20, 375, 89, 23);
	    add(Guardar);
	    if (refutadorAModificar != null) {
	        FieldNombre.setText(refutadorAModificar.getNombre());
	        FieldApellido.setText(refutadorAModificar.getApellido());
	        boxMedios.setSelectedItem(refutadorAModificar.getMedios_presentes());
	        Legajo.setText(String.valueOf(refutadorAModificar.getLegajo()));
	    }
		// Botón Guardar
	    
		Guardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        RefutadorDAO dao = new RefutadorDAO();

		        if (refutadorAModificar == null) {
		            // Alta
		            Refutador nuevo = new Refutador();
		            nuevo.setNombre(FieldNombre.getText());
		            nuevo.setApellido(FieldApellido.getText());
		            nuevo.setMedios_presentes(boxMedios.getSelectedItem().toString());
		            nuevo.setLegajo(Integer.valueOf(Legajo.getText()));
		            dao.guardar(nuevo);
		        } else {
		            // Modificación
		            refutadorAModificar.setNombre(FieldNombre.getText());
		            refutadorAModificar.setApellido(FieldApellido.getText());
		            refutadorAModificar.setMedios_presentes(boxMedios.getSelectedItem().toString());
		            refutadorAModificar.setLegajo(Integer.valueOf(Legajo.getText()));

		            // Llamada correcta al DAO
		            dao.modificar(refutadorAModificar, legajoOriginal);
		        }

		        // Volvemos a la tabla
		        JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		        marco.setContentPane(new ConsultaRefutador());
		        marco.validate();
		    }
		});

		Guardar.setBounds(20, 375, 89, 23);
		add(Guardar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new Pantalla());
				marco.validate();
			}
		});
		btnVolver.setBounds(171, 375, 89, 23);
		add(btnVolver);
		
		
	}
}
