package app.controller.manage_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import app.model.Account1;
import app.model.Brand1;
import app.model.Category1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class category_brand1 implements Initializable {
	Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int index=-1;
    ObservableList<Category1> listC;
	ObservableList<Category1> dataListC;
	ObservableList<Brand1> listB;
	ObservableList<Brand1> dataListB;
//------------------------Category-----------------------------
    @FXML
    private TextField cat_id;
    
    @FXML
    private TextField cat_name;

    @FXML
    private TextField search_cat;
    
    @FXML
    private TableView<Category1> table_cat;
    
    @FXML
    private TableColumn<Category1, Integer> col_no_cat;

    @FXML
    private TableColumn<Category1, Integer> col_cat_id;

    @FXML
    private TableColumn<Category1, String> col_cat_name;
    
    @FXML
    void Add_cat(ActionEvent event) {
    	conn = connectDB.ConnectDb();
		String sql1 = "insert into category (cat_name) values (?)";
		try {
			String value_cat_name=cat_name.getText();			
				if (value_cat_name.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Fill Please!!");
				} else {
					String query1="Select * from category where cat_name like '%"+value_cat_name+"%'";
					pst = conn.prepareStatement(query1);
					rs=pst.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "This Category Name Exist.");
						search_cat();
					}else {
						pst = conn.prepareStatement(sql1);
						pst.setString(1, cat_name.getText());
						pst.execute();
						JOptionPane.showMessageDialog(null, "ADD new Category successfully!!");
						search_cat();
					}
					
				}						
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    @FXML
    void Update_cat(ActionEvent event) {
    	try {
			if (cat_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to delete!");
			} else {
				conn = connectDB.ConnectDb();
				String value1 = cat_id.getText();
				String value2 = cat_name.getText();
				String sql = "update category set cat_name= '" + value2 + "' where cat_id= '" + value1 + "' ";
				pst = conn.prepareStatement(sql);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Update");
				search_cat();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }
    
    @FXML
    void Delete_cat(ActionEvent event) {
    	conn = connectDB.ConnectDb();
		String sql = "delete from category where cat_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat_id.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Delete");
			search_cat();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }
//-----------------------Brand--------------------------------
    @FXML
    private TextField brand_id;
    
    @FXML
    private TextField brand_name;

    @FXML
    private TextField search_brand;
    
    @FXML
    private TableView<Brand1> table_brand;

    @FXML
    private TableColumn<Brand1, Integer> col_no_brand;
    
    @FXML
    private TableColumn<Brand1, Integer> col_brand_id;

    @FXML
    private TableColumn<Brand1, String> col_brand_name;
    
    @FXML
    void Add_brand(ActionEvent event) {
    	conn = connectDB.ConnectDb();
    	conn = connectDB.ConnectDb();
		String sql1 = "insert into brand (brand_name) values (?)";
		try {
			String value_brand_name=brand_name.getText();			
				if (value_brand_name.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Fill Please!!");
				} else {
					String query="Select * from brand where brand_name like '%"+value_brand_name+"%'";
					pst = conn.prepareStatement(query);
					rs=pst.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "This Brand Name Exist.");
						search_brand();
					}else {
						pst = conn.prepareStatement(sql1);
						pst.setString(1, brand_name.getText());
						pst.execute();
						JOptionPane.showMessageDialog(null, "ADD new Brand successfully!!");
						search_brand();
					}
					
				}						
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Delete_brand(ActionEvent event) {
    	conn = connectDB.ConnectDb();
		String sql = "delete from brand where brand_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, brand_id.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Delete");
			search_brand();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }

    @FXML
    void Update_brand(ActionEvent event) {
    	try {
			if (brand_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to delete!");
			} else {
				conn = connectDB.ConnectDb();
				String value1 = brand_id.getText();
				String value2 = brand_name.getText();
				String sql = "update brand set brand_name= '" + value2 + "' where brand_id= '" + value1 + "' ";
				pst = conn.prepareStatement(sql);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Update");
				search_brand();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }

//----------------------------------Function----------------------------------------
    @FXML
    void getSelected_brand(MouseEvent event) {
    	index = table_brand.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		brand_id.setText(col_brand_id.getCellData(index).toString());
		brand_name.setText(col_brand_name.getCellData(index).toString());
    }

    @FXML
    void getSelected_cat(MouseEvent event) {
    	index = table_cat.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		cat_id.setText(col_cat_id.getCellData(index).toString());
		cat_name.setText(col_cat_name.getCellData(index).toString());
    }


	@FXML
	void search_cat() {
		col_no_cat.setCellValueFactory(new PropertyValueFactory<Category1, Integer>("no"));
		col_cat_id.setCellValueFactory(new PropertyValueFactory<Category1, Integer>("cat_id"));
		col_cat_name.setCellValueFactory(new PropertyValueFactory<Category1, String>("cat_name"));
		
		dataListC = connectDB.getDataCategory1();
		table_cat.setItems(dataListC);
		FilteredList<Category1> filteredData = new FilteredList<>(dataListC, b -> true);
		search_cat.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getCat_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else
					return false;
			});
		});
		SortedList<Category1> sortedData = new SortedList<>(filteredData);		
		sortedData.comparatorProperty().bind(table_cat.comparatorProperty());
		table_cat.setItems(sortedData);

	}
	
	@FXML
	void search_brand() {
		col_no_brand.setCellValueFactory(new PropertyValueFactory<Brand1, Integer>("no"));
		col_brand_id.setCellValueFactory(new PropertyValueFactory<Brand1, Integer>("brand_id"));
		col_brand_name.setCellValueFactory(new PropertyValueFactory<Brand1, String>("brand_name"));
		
		dataListB = connectDB.getDataBrand1();
		table_brand.setItems(dataListB);
		FilteredList<Brand1> filteredData = new FilteredList<>(dataListB, b -> true);
		search_brand.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getBrand_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else
					return false;
			});
		});
		SortedList<Brand1> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table_brand.comparatorProperty());
		table_brand.setItems(sortedData);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		

		search_cat();
		search_brand();
	}
    

}
