package Vista;

import Controlador.Controlador;
import Modelo.Filtros.Filtrar;
import Modelo.Gestor.Tarea;
import Modelo.Filtros.FiltraCompletado;
import Modelo.Filtros.FiltraPrioridad;
import Modelo.InterrogaModelo;
import Vista.clasesPersonalizadas.Miventana;
import Vista.clasesPersonalizadas.escuchadorVentana;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class ImplementaVista implements InformaVista, InterrogaVista {

    private Controlador controlador;
    private InterrogaModelo modelo;

    private Miventana ventana = new Miventana();
    private JDialog dialogo = new JDialog();
    private MiModelo x = new MiModelo();
    private JTable tabla =new JTable(x);

    private JTextField txtTitul=new JTextField(38);
    private JTextArea txtDescrip=new JTextArea();
    private JTextField txtClave=new JTextField(45);

    private JButton nuevo=new JButton("Nueva");
    private JButton actualiza=new JButton("Actualiza");
    private JButton borra=new JButton("Borrar");

    private JRadioButton Dalta = new JRadioButton ("Alta");
    private JRadioButton Dnormal = new JRadioButton ("Normal");
    private JRadioButton Dbaja = new JRadioButton ("Baja");

    private JRadioButton alta = new JRadioButton ("Alta");
    private JRadioButton normal = new JRadioButton ("Normal");
    private JRadioButton baja = new JRadioButton ("Baja");
    private JRadioButton todas = new JRadioButton ("Todas");

    private JRadioButton Com = new JRadioButton ("Completadas");
    private JRadioButton Nocom = new JRadioButton ("No completas");
    private JRadioButton todasC = new JRadioButton ("Todas");

    private JCheckBox complet=new JCheckBox("Completada");

    public ImplementaVista() {
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }


    static class MiModelo extends DefaultTableModel{

        @Override
        public boolean isCellEditable (int row, int column)
        {
            return false;
        }
    }


    class EscuchadorTabla extends MouseAdapter {
        public void mouseClicked(MouseEvent e){

            int fila = tabla.rowAtPoint(e.getPoint());
            int columna = tabla.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
                rellenar((String) tabla.getValueAt(fila,0));
        }
    }



    class EscuchadorBotones implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();
            switch (texto) {
                case "Nueva":
                    nuevaOactTarea(true);
                    break;

                case "Actualiza":
                    nuevaOactTarea(false);
                    break;

                case "Borrar":
                    if(getClave().isEmpty()){ seleccioneTareaE(); break;}
                    dialogo=dialoguillo();
                    break;

                case "Aplicar Filtros":
                    filtraTareas();
                    break;

                case "Confirmar":
                    dialogo.dispose();
                    controlador.eliminarTarea();
                    break;

                case "Cancelar":
                    dialogo.dispose();
                    break;
            }
        }
    }

    public void nuevaOactTarea(boolean nueva){
        Modelo.Gestor.Tarea.Prioridad prio= Tarea.Prioridad.Alta;
        boolean compl=false;
        if(Dnormal.isSelected()) prio= Tarea.Prioridad.Normal;
        if(Dbaja.isSelected()) prio= Tarea.Prioridad.Baja;

        if(complet.isSelected()) compl= true;

        if(nueva) controlador.anyadirTarea(prio,compl);
        else controlador.actualizarTarea(prio,compl);
    }

    public void filtraTareas(){
        Map<String,Tarea> mapita = modelo.getGestor().getTareas();
        Modelo.Gestor.Tarea.Prioridad prio;
        boolean com = true;
        Filtrar filtro;

        if(!todas.isSelected()){
            if(alta.isSelected()) prio=Tarea.Prioridad.Alta;
            else if(normal.isSelected()) prio= Tarea.Prioridad.Normal;
            else prio = Tarea.Prioridad.Baja;
            filtro = new FiltraPrioridad(prio);
            mapita = filtro.filtra(mapita);
        }

        if(!todasC.isSelected()){
            if(Nocom.isSelected()) com= false;
            filtro = new FiltraCompletado(com);
            mapita = filtro.filtra(mapita);
        }

        vaciaInfo();
        actualizaTabla(mapita);
    }

    public void rellenar(String clave){
        Tarea tarea = modelo.devuelveTarea(clave);
        txtClave.setText(tarea.getCodigo());
        txtDescrip.setText(tarea.getDescripcion());
        txtTitul.setText(tarea.getTitulo());

        if(tarea.getPrioridad().equals("Alta")) Dalta.setSelected(true);
        else if(tarea.getPrioridad().equals("Normal")) Dnormal.setSelected(true);
        else Dbaja.setSelected(true);

        if(tarea.isCompletada()) complet.setSelected(true);
        else complet.setSelected(false);
    }


    @Override
    public void vaciaInfo(){
        txtClave.setText("");
        txtDescrip.setText("");
        txtTitul.setText("");
        Dnormal.setSelected(true);
        complet.setSelected(false);
    }


    @Override
    public void actualizaTabla(Map<String,Tarea> mapa){

        String[] Cnombres={"Codigo","Titulo","Prioridad","Completada"};
        Object[][] data = modelo.matrizTabla(mapa);

        x.setDataVector(data,Cnombres);

        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);

        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(420);
    }



    public void creaGUI() {
        SwingUtilities.invokeLater(this::GUI);
    }


    private void GUI(){

        EscuchadorBotones escuchador =new EscuchadorBotones();
        EscuchadorTabla escuchadorT = new EscuchadorTabla();


        //Paneles
        JPanel panelSup = new JPanel(new GridLayout(1, 3));
        JPanel panelCen = new JPanel(new BorderLayout());
        JPanel panelInf = new JPanel(new GridLayout(2, 1));

        JPanel FiltroP = new JPanel(new GridLayout(4, 1));
        JPanel FiltroC = new JPanel(new GridLayout(4, 1));
        JPanel BotonA = new JPanel(new FlowLayout());

        JPanel Titulo = new JPanel(new FlowLayout());
        JPanel PrioridadBajo = new JPanel(new FlowLayout());
        JPanel Botones = new JPanel(new FlowLayout());
        JPanel InferiorAbajo=new JPanel(new GridLayout(2,1));


        //GruposBotones
        ButtonGroup prioridad=new ButtonGroup();
        ButtonGroup Bprioridad=new ButtonGroup();
        ButtonGroup completado=new ButtonGroup();

        //Tabla
        actualizaTabla(modelo.getGestor().getTareas());
        tabla.addMouseListener(escuchadorT);
        panelCen.add(tabla.getTableHeader(),BorderLayout.NORTH);
        panelCen.add(tabla);

        //Titulos
        FiltroP.setBorder(new TitledBorder("Prioridad"));
        FiltroC.setBorder(new TitledBorder("Completado"));
        panelCen.setBorder(new TitledBorder("Lista de tareas"));
        panelInf.setBorder(new TitledBorder("Detalle de la tarea"));
        PrioridadBajo.setBorder(new TitledBorder("Prioridad"));

        //Filtro Prioridad
        prioridad.add(alta);prioridad.add(normal);prioridad.add(baja);prioridad.add(todas);
        FiltroP.add(alta);FiltroP.add(normal);FiltroP.add(baja);FiltroP.add(todas);
        todas.setSelected(true);
        panelSup.add(FiltroP);

        //Filtro Completado
        completado.add(Com);completado.add(Nocom);completado.add(todasC);
        FiltroC.add(Com);FiltroC.add(Nocom);FiltroC.add(todasC);
        todasC.setSelected(true);
        panelSup.add(FiltroC);

        //Imagen y boton Filtrar
        ImageIcon iconoEscalado = new ImageIcon (new ImageIcon("src/icono.png").getImage().getScaledInstance(70,66,Image.SCALE_DEFAULT));
        JLabel imagen=new JLabel("",iconoEscalado,SwingConstants.CENTER);
        imagen.setPreferredSize(new Dimension(100,80));

        JButton Aplic = new JButton ("Aplicar Filtros");
        Aplic.addActionListener(escuchador);
        BotonA.add(Aplic); BotonA.add(imagen);panelSup.add(BotonA);

        //Titulo y descrip
        JLabel titul=new JLabel("Titulo:");
        JLabel descrip=new JLabel("Descripción:");

        Titulo.add(titul);Titulo.add(txtTitul);Titulo.add(complet);
        Titulo.add(descrip);Titulo.add(txtDescrip);
        txtDescrip.setPreferredSize(new Dimension(480,68));
        txtTitul.setBorder(new BevelBorder(BevelBorder.LOWERED));
        txtDescrip.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panelInf.add(Titulo);

        //Selector Prioridad
        Bprioridad.add(Dalta);Bprioridad.add(Dnormal);Bprioridad.add(Dbaja);
        PrioridadBajo.add(Dalta);PrioridadBajo.add(Dnormal);PrioridadBajo.add(Dbaja);
        Dnormal.setSelected(true);
        InferiorAbajo.add(PrioridadBajo);

        //Botones Inferiores
        nuevo.addActionListener(escuchador);actualiza.addActionListener(escuchador);borra.addActionListener(escuchador);
        Botones.add(nuevo);Botones.add(actualiza);Botones.add(borra);
        InferiorAbajo.add(Botones);
        panelInf.add(InferiorAbajo);

        ventana.addWindowListener(new escuchadorVentana(modelo));
        ventana.add(panelSup, BorderLayout.NORTH);
        ventana.add(panelCen, BorderLayout.CENTER);
        ventana.add(panelInf, BorderLayout.SOUTH);
        ventana.setVisible(true);
    }

    public JDialog dialoguillo(){

        JDialog dial=new JDialog();
        JPanel nuevo =new JPanel(new GridLayout(2,1));
        JPanel abajo =new JPanel();
        JPanel arriba =new JPanel();

        JLabel mensaje = new JLabel("Estas seguro de que desea eliminar la tarea seleccionada" );
        arriba.add(mensaje);nuevo.add(arriba);

        JButton aceptar =new JButton("Confirmar");
        JButton cancelar =new JButton("Cancelar");

        abajo.add(aceptar);abajo.add(cancelar);nuevo.add(abajo);
        aceptar.addActionListener(new EscuchadorBotones());
        cancelar.addActionListener(new EscuchadorBotones());

        dial.add(nuevo);
        dial.setSize(400,120);
        dial.setLocationRelativeTo(null);
        dial.setResizable(false);
        dial.setVisible(true);
        return  dial;
    }


    @Override
    public void faltanParametros() {
        Container cont=new Container();
        JOptionPane.showMessageDialog(cont,"Rellene los campos para continuar","Adverencia", JOptionPane.INFORMATION_MESSAGE);
        cont.setSize(300,100);
        cont.setVisible(true);
    }

    @Override
    public void yaExistia() {
        Container cont=new Container();
        JOptionPane.showMessageDialog(cont,"La tarea que desea añadir ya existe","Error", JOptionPane.WARNING_MESSAGE);
        cont.setSize(300,100);
        cont.setVisible(true);
    }

    @Override
    public void noExistia() {
        Container cont=new Container();
        JOptionPane.showMessageDialog(cont,"La tarea que desea eliminar o actualizar no existe","Error", JOptionPane.WARNING_MESSAGE);
        cont.setSize(300,100);
        cont.setVisible(true);
    }

    @Override
    public void seleccioneTarifaA() {
        Container cont=new Container();
        JOptionPane.showMessageDialog(cont,"Seleccione la tarea que desea actualizar","Advertencia", JOptionPane.INFORMATION_MESSAGE);
        cont.setSize(300,100);
        cont.setVisible(true);
    }

    @Override
    public void seleccioneTareaE() {
        Container cont=new Container();
        JOptionPane.showMessageDialog(cont,"Seleccione la tarea que desea eliminar","Advertencia", JOptionPane.INFORMATION_MESSAGE);
        cont.setSize(300,100);
        cont.setVisible(true);
    }

    @Override
    public String getTitulo(){
        return txtTitul.getText();
    }

    @Override
    public String getDescrip(){
        return txtDescrip.getText();
    }

    @Override
    public String getClave(){
        return txtClave.getText();
    }

}
