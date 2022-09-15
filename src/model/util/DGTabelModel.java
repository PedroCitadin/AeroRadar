package model.util;

import model.bean.Aeronave;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DGTabelModel extends AbstractTableModel {

    private List<Aeronave> aeronaves;
    private String[] colunas = new String[]{
            "","ID", "X", "Y", "R", "A", "V", "D"
    };

    public DGTabelModel(List<Aeronave> aeronaves) {
        this.aeronaves = aeronaves;
    }

    public DGTabelModel() {
        this.aeronaves = new ArrayList<Aeronave>();
    }
    public int getRowCount() {
        return aeronaves.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }


    public void setValueAt(Aeronave aValue, int rowIndex) {
        Aeronave Aeronave = aeronaves.get(rowIndex);

        Aeronave.setId(aValue.getId());
        Aeronave.setX(aValue.getX());
        Aeronave.setY(aValue.getY());
        Aeronave.setRaio(aValue.getRaio());
        Aeronave.setAngulo(aValue.getAngulo());
        Aeronave.setVelocidade(aValue.getVelocidade());
        Aeronave.setDistancia(aValue.getDistancia());
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);
        fireTableCellUpdated(rowIndex, 4);
        fireTableCellUpdated(rowIndex, 5);
        fireTableCellUpdated(rowIndex, 6);
        fireTableCellUpdated(rowIndex, 7);



    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Aeronave Aeronave = aeronaves.get(rowIndex);

        switch (columnIndex) {
            case 1:
                Aeronave.setId(Integer.parseInt( aValue.toString()));
            case 2:
                Aeronave.setX(Float.parseFloat(aValue.toString()));
            case 3:
                Aeronave.setY(Float.parseFloat(aValue.toString()));
            case 4:
                Aeronave.setRaio(Float.parseFloat(aValue.toString()));
            case 5:
                Aeronave.setAngulo(Float.parseFloat(aValue.toString()));
            case 6:
                Aeronave.setVelocidade(Float.parseFloat(aValue.toString()));
            case 7:
                Aeronave.setDistancia(Float.parseFloat(aValue.toString()));

            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        Aeronave AeronaveSelecionado = aeronaves.get(rowIndex);
        String valueObject = null;
        switch(columnIndex){
            case 1: valueObject = String.valueOf(AeronaveSelecionado.getId()); break;
            case 2: valueObject = String.valueOf(AeronaveSelecionado.getX()); break;
            case 3: valueObject = String.valueOf(AeronaveSelecionado.getY()); break;
            case 4: valueObject = String.valueOf(AeronaveSelecionado.getRaio()); break;
            case 5: valueObject = String.valueOf(AeronaveSelecionado.getAngulo()); break;
            case 6: valueObject = String.valueOf(AeronaveSelecionado.getVelocidade()); break;
            case 7: valueObject = String.valueOf(AeronaveSelecionado.getDistancia()); break;
            default: System.err.println("Índice inválido para propriedade do bean Usuario.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public Aeronave getAeronave(int indiceLinha) {
        return aeronaves.get(indiceLinha);
    }

    public void addAeronave(Aeronave u) {
        aeronaves.add(u);


        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }


    public void removeUsuario(int indiceLinha) {
        aeronaves.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }


    public void addListaDeUsuarios(List<Aeronave> novosUsuarios) {

        int tamanhoAntigo = getRowCount();
        aeronaves.addAll(novosUsuarios);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        aeronaves.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return aeronaves.isEmpty();
    }


}
