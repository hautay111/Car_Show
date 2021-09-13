package app.controller.manage_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import app.dao.connectDB;
import app.model.Run_Out;
import app.model.Run_Out;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class run_out implements Initializable{
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int index = -1;
	ObservableList<Run_Out> listM;
	ObservableList<Run_Out> dataList;

    @FXML
    private TextField search;
    
    @FXML
    private TableView<Run_Out> table_run_out;

    @FXML
    private TableColumn<Run_Out, Integer> col_no;

    @FXML
    private TableColumn<Run_Out, Integer> col_ex_id;

    @FXML
    private TableColumn<Run_Out, String> col_sup;

    @FXML
    private TableColumn<Run_Out, String> sol_pro;

    @FXML
    private TableColumn<Run_Out, String> col_ex;

    @FXML
    private TableColumn<Run_Out, String> col_amount;

    @FXML
    private TableColumn<Run_Out, String> col_price;

    @FXML
    private TableColumn<Run_Out, String> col_total;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		search_runout();
	}
	@FXML
	void search_runout() {
		col_no.setCellValueFactory(new PropertyValueFactory<Run_Out, Integer>("no"));
		col_ex_id.setCellValueFactory(new PropertyValueFactory<Run_Out, Integer>("ex_id"));
		col_sup.setCellValueFactory(new PropertyValueFactory<Run_Out, String>("sup"));
		col_ex.setCellValueFactory(new PropertyValueFactory<Run_Out, String>("ex"));
		col_amount.setCellValueFactory(new PropertyValueFactory<Run_Out, String>("amount"));
		col_total.setCellValueFactory(new PropertyValueFactory<Run_Out, String>("total"));
		col_price.setCellValueFactory(new PropertyValueFactory<Run_Out, String>("pice"));
		sol_pro.setCellValueFactory(new PropertyValueFactory<Run_Out, String>("pro"));

		dataList = connectDB.getDataRunOut1();
		table_run_out.setItems(dataList);
		FilteredList<Run_Out> filteredData = new FilteredList<>(dataList, b -> true);
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getSup().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(person.getEx_id()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
		});
		SortedList<Run_Out> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table_run_out.comparatorProperty());
		table_run_out.setItems(sortedData);

	}
    

}
