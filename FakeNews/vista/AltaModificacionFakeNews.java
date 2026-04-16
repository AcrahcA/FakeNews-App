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

import fakeNews.dao.CategoriaDAO;
import fakeNews.dao.FakeNewsDAO;
import fakeNews.dao.tipoMedioDAO;
import fakeNews.modelo.FakeNews;
import fakeNews.dao.RefutadorDAO;


public class AltaModificacionFakeNews extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField FieldTitulo;
	private JTextField FieldDesc;
	private JTextField FakeCreador;
	private JTextField FakeFecha;
	private JTextField legajo;
	private int legajoOriginal;


	
	/**
	 * Create the frame.
	 * @param fakeNewsAModificar 
	 */
	public AltaModificacionFakeNews(FakeNews fakeNewsAModificar) {
	
		setLayout(null);
		
		
		FieldTitulo = new JTextField();
		FieldTitulo.setBounds(20, 92, 280, 22);
		add(FieldTitulo);
		FieldTitulo.setColumns(10);
		legajo = new JTextField();
		legajo.setBounds(336, 124, 129, 20);
		add(legajo);
		legajo.setColumns(10);
		
		JLabel Legajo = new JLabel("Legajo");
		Legajo.setForeground(new Color(255, 0, 128));
		Legajo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		Legajo.setBounds(337, 90, 256, 22);
		add(Legajo);
		
		JLabel TituloFake = new JLabel("Ingresa el Titulo");
		TituloFake.setForeground(new Color(255, 0, 128));
		TituloFake.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 21));
		TituloFake.setBounds(20, 70, 256, 22);
		add(TituloFake);
		
		JLabel tituloPantalla = new JLabel("Creador de Fakenews");
		tituloPantalla.setForeground(new Color(255, 128, 192));
		tituloPantalla.setBackground(new Color(255, 128, 255));
		tituloPantalla.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 50));
		tituloPantalla.setBounds(20, 11, 554, 45);
		add(tituloPantalla);
		
		JLabel DescFake = new JLabel("Ingresa la descripción");
		DescFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		DescFake.setForeground(new Color(255, 0, 128));
		DescFake.setBounds(20, 116, 240, 29);
		add(DescFake);
		
		FieldDesc = new JTextField();
		FieldDesc.setBounds(20, 144, 280, 20);
		add(FieldDesc);
		FieldDesc.setColumns(10);
		
		JLabel MediosFake = new JLabel("Ingresa nombre de medios");
		MediosFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		MediosFake.setForeground(new Color(255, 0, 128));
		MediosFake.setBounds(20, 171, 280, 29);
		add(MediosFake);
		
		JLabel CatFake = new JLabel("Elegí una categoría");
		CatFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		CatFake.setForeground(new Color(255, 0, 128));
		CatFake.setBounds(20, 217, 270, 29);
		add(CatFake);
		
		
		
		
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
		botonSiguiente1.setBounds(182, 457, 108, 22);
		add(botonSiguiente1);
		
		JComboBox<String> boxCategoria = new JComboBox<>();
		boxCategoria.setMaximumRowCount(7);
		boxCategoria.setBounds(20, 246, 280, 22);
		add(boxCategoria);
		
		CategoriaDAO cDao = new CategoriaDAO();
    	for (String categoria : cDao.traerTodas()) {
			boxCategoria.addItem(categoria);
			
		}
		
		

		
		JLabel creadorFake = new JLabel("Ingresa nombre de creador");
		creadorFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		creadorFake.setForeground(new Color(255, 0, 128));
		creadorFake.setBounds(20, 276, 315, 22);
		add(creadorFake);
		
		FakeCreador = new JTextField();
		FakeCreador.setBounds(20, 305, 280, 20);
		add(FakeCreador);
		FakeCreador.setColumns(10);
		
		JLabel fechaFake = new JLabel("Ingresa fecha");
		fechaFake.setForeground(new Color(255, 0, 128));
		fechaFake.setFont(new Font("Tahoma", Font.BOLD, 20));
		fechaFake.setBackground(new Color(255, 0, 128));
		fechaFake.setBounds(20, 323, 280, 29);
		add(fechaFake);
		
		FakeFecha = new JTextField();
		FakeFecha.setBounds(20, 351, 280, 20);
		add(FakeFecha);
		FakeFecha.setColumns(10);
		
		JComboBox<String> boxMedios = new JComboBox<String>();
		boxMedios.setMaximumRowCount(3);
		boxMedios.setBounds(20, 197, 280, 22);
		add(boxMedios);
		tipoMedioDAO mDao = new tipoMedioDAO();
		for (String medios : mDao.traerTodas()) {
			boxMedios.addItem(medios);
			
		}
		if (fakeNewsAModificar != null) {
			FieldTitulo.setText(fakeNewsAModificar.getTitulo());
			FakeCreador.setText(fakeNewsAModificar.getCreador());
			FakeFecha.setText(fakeNewsAModificar.getFecha());
			FieldDesc.setText(fakeNewsAModificar.getDescripcion());
		    boxMedios.setSelectedItem(fakeNewsAModificar.getMedios());
		    boxCategoria.setSelectedItem(fakeNewsAModificar.getCategoria());
		    Legajo.setText(String.valueOf(fakeNewsAModificar.getLegajo()));

		    // Guardamos el legajo original para el DAO
		    legajoOriginal = fakeNewsAModificar.getLegajo();
		}
		
		
		JButton Guardar = new JButton("Guardar");
		Guardar.setBounds(24, 458, 89, 23);
		add(Guardar);
		if (fakeNewsAModificar != null) {
			FieldTitulo.setText(fakeNewsAModificar.getTitulo());
			FakeCreador.setText(fakeNewsAModificar.getCreador());
			FakeFecha.setText(fakeNewsAModificar.getFecha());
			FieldDesc.setText(fakeNewsAModificar.getDescripcion());
		    boxMedios.setSelectedItem(fakeNewsAModificar.getMedios());
		    boxCategoria.setSelectedItem(fakeNewsAModificar.getCategoria());
		    Legajo.setText(String.valueOf(fakeNewsAModificar.getLegajo()));

		   
		}
		Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FakeNewsDAO dao = new FakeNewsDAO();
				if(fakeNewsAModificar == null) {
					FakeNews fake = new FakeNews();
					fake.setTitulo(FieldTitulo.getText());
					fake.setDescripcion(FieldDesc.getText());
					fake.setMedios(boxMedios.getSelectedItem().toString());
					fake.setCategoria(boxCategoria.getSelectedItem().toString());
					fake.setCreador(FakeCreador.getText());
					fake.setFecha(FakeFecha.getText());
					fake.setLegajo(Integer.valueOf(legajo.getText()));
					dao.guardar(fake);

				}else {
					
					fakeNewsAModificar.setTitulo(FieldTitulo.getText());
					fakeNewsAModificar.setDescripcion(FieldDesc.getText());
					fakeNewsAModificar.setMedios(boxMedios.getSelectedItem().toString());
					fakeNewsAModificar.setCategoria(boxCategoria.getSelectedItem().toString());
					fakeNewsAModificar.setCreador(FakeCreador.getText());
					fakeNewsAModificar.setFecha(FakeFecha.getText());
					fakeNewsAModificar.setLegajo(Integer.valueOf(legajo.getText()));
					dao.modificar(fakeNewsAModificar, legajoOriginal);
				}
				// Volvemos a la tabla
		        JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		        marco.setContentPane(new Consulta());
		        marco.validate();
				
				
				
				
			}
		});
		
		
		JLabel lblRefutador = new JLabel("Refutador");
		lblRefutador.setForeground(new Color(255, 0, 128));
		lblRefutador.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRefutador.setBounds(20, 368, 270, 29);
		add(lblRefutador);
		
		JComboBox<String> boxRefutador = new JComboBox<String>();
		boxRefutador.setToolTipText("");
		boxRefutador.setMaximumRowCount(7);
		boxRefutador.setBounds(20, 398, 280, 22);
		add(boxRefutador);
		RefutadorDAO rDao = new RefutadorDAO();
		for(String refu : rDao.traerTodas()) {
			boxRefutador.addItem(refu);
		}
	
		JButton btnNewRefutador = new JButton("Crear Refutador");
		btnNewRefutador.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new AltaModificacionRefutador(null));
				marco.validate();
			}
		});
		btnNewRefutador.setBounds(310, 398, 129, 23);
		add(btnNewRefutador);
		
		
		
		
		
		
	}
}
