package app.controller.manage_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import app.model.Supplier;
import app.model.Unit;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class sup_unit implements Initializable {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int index = -1;
	ObservableList<Supplier> listC;
	ObservableList<Supplier> dataListC;
	ObservableList<Unit> listB;
	ObservableList<Unit> dataListB;

	@FXML
	private Label sup_id;
	
	@FXML
	private Label unit_id;

	@FXML
	private TextField unit;

	@FXML
	private TableView<Unit> table_unit;

	@FXML
	private TableColumn<Unit, Integer> col_no_unit;

	@FXML
	private TableColumn<Unit, Integer> col_id_unit;

	@FXML
	private TableColumn<Unit, String> col_name_unit;

	@FXML
	private TextField sup_phone;

	@FXML
	private TextField sup_address;

	@FXML
	private TextField sup_name;

	@FXML
	private TableView<Supplier> table_sup;

	@FXML
	private TableColumn<Supplier, Integer> col_no_sup;

	@FXML
	private TableColumn<Supplier, Integer> col_id_sup;

	@FXML
	private TableColumn<Supplier, String> col_name_sup;

	@FXML
	private TableColumn<Supplier, String> col_phone_sup;

	@FXML
	private TableColumn<Supplier, String> col_address_sup;

	public void reset_sup() {
		sup_id.setText(null);
		sup_name.setText(null);
		sup_phone.setText(null);
		sup_address.setText(null);
	}
	public void reset_unit() {
		unit_id.setText(null);
		unit.setText(null);
	}
	
	@FXML
	void Add_sup(ActionEvent event) {
		conn = connectDB.ConnectDb();
		String sql1 = "INSERT INTO supplier(sup_name, sup_address, sup_phone) VALUES (?,?,?)";
		try {
			String value_cat_name = sup_name.getText();
			if (value_cat_name.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Fill Please!!");
			} else {

				pst = conn.prepareStatement(sql1);
				pst.setString(1, sup_name.getText());
				pst.setString(2, sup_address.getText());
				pst.setString(3, sup_phone.getText());

				pst.execute();
				JOptionPane.showMessageDialog(null, "ADD successfully!!");
				search_sup();
				reset_sup();
			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Name's Supplier or Phone's Supplier existed. ");
		}
	}

	@FXML
	void Add_unit(ActionEvent event) {
		conn = connectDB.ConnectDb();
		String sql1 = "INSERT INTO unit(unit_name) VALUES (?)";
		try {
			String value_cat_name = unit.getText();
			if (value_cat_name.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Fill Please!!");
			} else {

				pst = conn.prepareStatement(sql1);
				pst.setString(1, unit.getText());

				pst.execute();
				JOptionPane.showMessageDialog(null, "ADD successfully!!");
				search_unit();
				reset_unit();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unit existed. ");
		}
	}

	@FXML
	void Delete_sup(ActionEvent event) {
		conn = connectDB.ConnectDb();
		String sql = "delete from supplier where sup_id = ?";
		try {
			if (sup_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to delete!");
			}else {
				pst = conn.prepareStatement(sql);
				pst.setString(1, sup_id.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Delete");
				search_sup();
				reset_sup();				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@FXML
	void Delete_unit(ActionEvent event) {
		conn = connectDB.ConnectDb();
		String sql = "delete from unit where unit_id = ?";
		try {
			if (unit_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to delete!");
			}else {
				pst = conn.prepareStatement(sql);
				pst.setString(1,unit_id.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Delete");
				search_unit();
				reset_unit();
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@FXML
	void Update_sup(ActionEvent event) {
		try {
			if (sup_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to update!");
			} else {
				conn = connectDB.ConnectDb();
				String value1 = sup_id.getText();
				String value2 = sup_name.getText();
				String value3 = sup_phone.getText();
				String value4 = sup_address.getText();
				String sql = "UPDATE `supplier` SET `sup_name`='"+value2+"',`sup_address`='"+value4+"',`sup_phone`='"+value3+"'  where brand_id= '" + value1 + "' ";
				pst = conn.prepareStatement(sql);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Update");
				search_sup();
				reset_sup();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@FXML
	void Update_unit(ActionEvent event) {
		try {
			if (unit_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to update!");
			} else {
				conn = connectDB.ConnectDb();
				String value1 = unit_id.getText();
				String value2 = unit.getText();
				String sql = "UPDATE `unit` SET `unit_name`='"+value2+"'  where brand_id= '" + value1 + "' ";
				pst = conn.prepareStatement(sql);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Update");
				search_unit();
				reset_unit();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@FXML
	void getSelected_sup(MouseEvent event) {
		index = table_sup.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		sup_id.setText(col_id_sup.getCellData(index).toString());
		sup_phone.setText(col_phone_sup.getCellData(index).toString());
		sup_name.setText(col_name_sup.getCellData(index).toString());
		sup_address.setText(col_address_sup.getCellData(index).toString());
	}

	@FXML
	void getSelected_unit(MouseEvent event) {
		index = table_unit.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		unit_id.setText(col_id_unit.getCellData(index).toString());
		unit.setText(col_name_unit.getCellData(index).toString());
	}

	@FXML
	void search_sup() {
		col_no_sup.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("no"));
		col_id_sup.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
		col_name_sup.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
		col_phone_sup.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
		col_address_sup.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));

		dataListC = connectDB.getDataSupplier();
		table_sup.setItems(dataListC);
		FilteredList<Supplier> filteredData = new FilteredList<>(dataListC, b -> true);
		sup_name.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});

		});
		sup_phone.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});

		});
		SortedList<Supplier> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table_sup.comparatorProperty());
		table_sup.setItems(sortedData);

	}

	@FXML
	void search_unit() {
		col_no_unit.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("no"));
		col_id_unit.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("id"));
		col_name_unit.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));

		dataListB = connectDB.getDataUint();
		table_unit.setItems(dataListB);
		FilteredList<Unit> filteredData = new FilteredList<>(dataListB, b -> true);
		unit.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});
		SortedList<Unit> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table_unit.comparatorProperty());
		table_unit.setItems(sortedData);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		search_sup();
		search_unit();
	}

}
