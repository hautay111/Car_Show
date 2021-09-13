package app.controller.manage_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import app.model.Bill;
import app.model.Bill_Mange;
import app.model.Order_Detail;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Bill_Manage implements Initializable{

	
    @FXML
    private BorderPane mainPane;

    @FXML
    private TableView<Bill_Mange> table_order;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_no;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_code_order;
    
    @FXML
    private TableColumn<Bill_Mange, Integer> col_total;
    
    @FXML
    private TableColumn<Bill_Mange, String> col_time;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_point;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_discount;
    
    @FXML
    private TableView<Bill_Mange> table_order1;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_bill_no;

    @FXML
    private TableColumn<Bill_Mange, String> col_bill_name;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_bill_price;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_bill_amount;

    @FXML
    private TableColumn<Bill_Mange, Integer> col_bill_total;
    
    
    
    @FXML
    private TextField serach_bill;
    
    @FXML
    private TextField search_order;
    
    @FXML
    private DatePicker text_search_date;
    

    @FXML
    private TextField text_id;
    
    @FXML
    private Label emp_id;

    @FXML
    private Label emp_tille_name;

    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Bill_Mange> listM;
    ObservableList<Bill_Mange> dataList;
    public void initialize(URL url, ResourceBundle rb) {
    	UpdateTable_bill();
    	search_user_bill_order();
    	search_user_bill();
    }
    
    @FXML
    void select_table(MouseEvent event) {
        index = table_order.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        
        serach_bill.setText(col_code_order.getCellData(index).toString());
        text_id.setText(col_code_order.getCellData(index).toString());
    }
    

    
    @FXML
    void btn_delete(ActionEvent event) {
    	if(emp_tille_name.getText().trim().equals("emp")) {
    		JOptionPane.showMessageDialog(null, "Only Security Guards or Manager have the right to delete!!");
    	}
    	
    	if(emp_tille_name.getText().trim().equals("emp_security") || emp_tille_name.getText().trim().equals("mana")) {
	        conn = connectDB.ConnectDb();
	        String sql = "delete from orders_detail where order_id = ?";
	            try {
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, text_id.getText());
	                pst.execute();
	            	UpdateTable_bill();
	            	search_user_bill_order();
	            	search_user_bill();
	//                JOptionPane.showMessageDialog(null, "Delete");
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e);
	            }
	            conn = connectDB.ConnectDb();
	            String sql1 = "delete from orders where order_id = ?";
	                try {
	                    pst = conn.prepareStatement(sql1);
	                    pst.setString(1, text_id.getText());
	                    pst.execute();
	                	UpdateTable_bill();
	                	search_user_bill_order();
	                	search_user_bill();
	                    JOptionPane.showMessageDialog(null, "Delete");
	                } catch (Exception e) {
	                    JOptionPane.showMessageDialog(null, e);
	                }
    	}
    }

    @FXML
    void load(ActionEvent event) {
    	serach_bill.setText("");
    	search_order.setText("");
    }

    @FXML
    void btn_search_date(ActionEvent event) {
    	LocalDate dateValue = text_search_date.getValue();
    	
    	serach_bill.setText(""+dateValue);
    	search_order.setText(""+dateValue);
    	
    }
    
    @FXML
   public void search_user_bill(){
    	col_no.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("no"));
    	col_code_order.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("ma_id"));
    	col_total.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("total_price"));
    	col_point.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("point"));
    	col_discount.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("discount"));
    			
    			dataList = connectDB.getBill_manage();
	           table_order.setItems(dataList);
	           FilteredList<Bill_Mange> filteredData = new FilteredList<>(dataList, b -> true);  
	           search_order.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	       if (newValue == null || newValue.isEmpty()) {
	        return true;
	       }    
	       String lowerCaseFilter = newValue.toLowerCase();
	       
	       if (person.getName_product().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	        return true; // Filter matches name
	       }else if (String.valueOf(person.getMa_id()).indexOf(lowerCaseFilter)!=-1)
	            return true;// Filter matches username
	       else if (person.getTime().toLowerCase().indexOf(lowerCaseFilter) != -1 )
		        return true; // Filter matches name                            
	            else  
	             return false; // Does not match.
	      });
	     });  
	     SortedList<Bill_Mange> sortedData = new SortedList<>(filteredData);  
	     sortedData.comparatorProperty().bind(table_order.comparatorProperty());  
	     table_order.setItems(sortedData);      
	       
    }
    public void UpdateTable_bill(){
    	col_no.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("no"));
    	col_code_order.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("ma_id"));
    	col_time.setCellValueFactory(new PropertyValueFactory<Bill_Mange,String>("time"));
    	col_total.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("total_price"));
    	col_point.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("point"));
    	col_discount.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("discount"));

    	
    	


        listM = connectDB.getBill_manage();
        table_order.setItems(listM);
    }

    
    
    @FXML
   public void search_user_bill_order(){
    	col_bill_no.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("no2"));
    	col_bill_name.setCellValueFactory(new PropertyValueFactory<Bill_Mange,String>("name_product"));
    	col_bill_amount.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("quantity_detail"));
    	col_bill_price.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("price_detail"));
    	col_bill_total.setCellValueFactory(new PropertyValueFactory<Bill_Mange,Integer>("total_detail"));
    			
    			dataList = connectDB.getBill_manage();
	           table_order1.setItems(dataList);
	           FilteredList<Bill_Mange> filteredData = new FilteredList<>(dataList, b -> true);  
	           serach_bill.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	       if (newValue == null || newValue.isEmpty()) {
	        return true;
	       }    
	       String lowerCaseFilter = newValue.toLowerCase();
	       
	       if (person.getName_product().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	        return true; // Filter matches name
	       }else if (String.valueOf(person.getMa_id()).indexOf(lowerCaseFilter)!=-1)
	            return true;// Filter matches username
	       else if (person.getTime().toLowerCase().indexOf(lowerCaseFilter) != -1 )
		        return true; // Filter matches name
	                                   
	            else  
	             return false; // Does not match.
	      });
	     });  
	     SortedList<Bill_Mange> sortedData = new SortedList<>(filteredData);  
	     sortedData.comparatorProperty().bind(table_order1.comparatorProperty());  
	     table_order1.setItems(sortedData);      
	       
    }
    
    
    
    
	@FXML
	void exit(MouseEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../ui/homepage/Home_Manage.fxml"));
			Parent parent;
	
			parent = loader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void getEmp_id1(String a1, String a3) {
		// TODO Auto-generated method stub
    	emp_id.setText(a1);
    	emp_tille_name.setText(a3);
	}
    

}
