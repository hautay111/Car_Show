package app.controller.manage_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import app.model.Brand1;
import app.model.Title;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class title implements Initializable{
	
	Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int index=-1;
    
    ObservableList<Title> listB;
	ObservableList<Title> dataListB;

    @FXML
    private TextField search;

    @FXML
    private TextField title_id;

    @FXML
    private TableView<Title> table;

    @FXML
    private TableColumn<Title, Integer> col_no;

    @FXML
    private TableColumn<Title, Integer> col_id;

    @FXML
    private TableColumn<Title, String> col_name;

    @FXML
    private TableColumn<Title, String> col_description;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    void Add(ActionEvent event)  {
    	conn = connectDB.ConnectDb();
		String sql1 = "insert into title (title_name,description) values (?,?)";
		try {
			String value_name=name.getText();			
				if (value_name.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Fill Please!!");
				} else {
					String query1="Select * from title where title_name like '%"+value_name+"%'";
					pst = conn.prepareStatement(query1);
					rs=pst.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "This Title Name Exist.");
						search();
					}else {
						pst = conn.prepareStatement(sql1);
						pst.setString(1, name.getText());
						pst.setString(2, description.getText());
						pst.execute();
						JOptionPane.showMessageDialog(null, "ADD new Title successfully!!");
						search();
					}
					
				}						
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Delete(ActionEvent event) {
    	conn = connectDB.ConnectDb();
		String sql = "delete from title where title_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title_id.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Delete");
			search();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }

    @FXML
    void Update(ActionEvent event) {
    	try {
			if (title_id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select the data you want to delete!");
			} else {
				conn = connectDB.ConnectDb();
				String value1 = title_id.getText();
				String value2 = name.getText();
				String value3 = description.getText();
				String sql = "update title set title_name= '" + value2 + "',description= '" + value3 + "'  where title_id= '" + value1 + "' ";
				pst = conn.prepareStatement(sql);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Update");
				search();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }
   //----------------------------------Function----------------------------------------

    @FXML
    void getSelected(MouseEvent event) {
    	index = table.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		title_id.setText(col_id.getCellData(index).toString());
		name.setText(col_name.getCellData(index).toString());
		description.setText(col_description.getCellData(index).toString());
    }


    @FXML
	void search() {
		col_no.setCellValueFactory(new PropertyValueFactory<Title, Integer>("no"));
		col_id.setCellValueFactory(new PropertyValueFactory<Title, Integer>("title_id"));
		col_name.setCellValueFactory(new PropertyValueFactory<Title, String>("title_name"));
		col_description.setCellValueFactory(new PropertyValueFactory<Title, String>("description"));
		
		dataListB = connectDB.getDataTitle();
		table.setItems(dataListB);
		FilteredList<Title> filteredData = new FilteredList<>(dataListB, b -> true);
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getTitle_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else
					return false;
			});
		});
		SortedList<Title> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		search();
		
	}
	

}
