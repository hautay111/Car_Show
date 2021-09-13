package app.controller.manage_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RollEmployee implements Initializable{

    @FXML
    private Label emp_name;

    @FXML
    private Label emp_position;

    @FXML
    private Label emp_id;
    
    @FXML
    private Label date_today;

    @FXML
    private Pane pane;
    
    @FXML
    private ComboBox<String> roll_combox;
    
    @FXML
    private TextField emp_id_scanner;

    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<String> list = FXCollections.observableArrayList("Shift 1", "Shift 2","Shift 3");

    public void initialize(URL url, ResourceBundle rb) {
    	showdate();
    	roll_combox.setItems(list);
    	emp_id_scanner.setText(" ");
    }
    
    public void getEmp_id(String id_emp,String title,String name ) {
    	emp_id.setText(id_emp);
    	emp_name.setText(name);
    	emp_position.setText(title);
    	emp_id_scanner.setText(id_emp);
    }
    
    public static  String DateFormat = "yyyy-MM-dd";
    public void showdate()
    {
      Calendar cal= Calendar.getInstance();
      SimpleDateFormat format = new SimpleDateFormat(DateFormat);
      date_today.setText(format.format(cal.getTime()));
      
      
      
     }  
    
    
    @FXML
    void btn_canel(ActionEvent event) {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();	
    }


    @FXML
    void scanner_emp(KeyEvent event) {
    	
    }
    @FXML
    void box_roll(ActionEvent event) {	
    	
    	String value = roll_combox.getSelectionModel().getSelectedItem().toString();
    	System.out.println(value);
    	
    	
    }
    
    
    
    
    
    @FXML
    private Label date_data;
    @FXML
    void btn_ok_roll(ActionEvent event) {
    	
	   
	    
    	String value = roll_combox.getSelectionModel().getSelectedItem().toString();
   		conn = connectDB.ConnectDb();
	        String sql = "insert into timesheets (emp_id,shift)values(?,?)";
	        		try {;
     		            pst = conn.prepareStatement(sql);
     		            pst.setString(1, emp_id.getText());
     		           pst.setString(2, value);
     		            pst.execute();
		     		} catch (Exception e) {
		     			System.out.println(e);
		     			// TODO: handle exception
		     		}

    }
	
	@FXML
	void exit(MouseEvent event) {
			
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.close();	
			
	}
	
}
