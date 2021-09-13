package app.controller.employee_controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Create_Customer implements Initializable {

    @FXML
    private TextField text_cus_id;

    @FXML
    private TextField text_cus_code;

    @FXML
    private TextField text_cus_name;

    @FXML
    private TextField text_cus_address;

    @FXML
    private DatePicker date;
    
    @FXML
    private Label text_date;
    
    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<String> combobox_male_female;

    @FXML
    private ComboBox<String> combobox_product_category;

    @FXML
    private Label label_thongbao;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void box_cus(ActionEvent event) {

    }
    ObservableList<String> list = FXCollections.observableArrayList("Male", "Female");
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combobox_product_category.setItems(list);
		showdate();
	}
	
    public static  String DateFormat = "yyyy-MM-dd";
    public void showdate()
    {
      Calendar cal= Calendar.getInstance();
      SimpleDateFormat format = new SimpleDateFormat(DateFormat);
      text_date.setText(format.format(cal.getTime()));

        
    }
    
    @FXML
    void scanner_barcode_cus(KeyEvent event) {
    	scanner_cus();
    }
    
	public void scanner_cus() {
    	

        try {
            
 	    	conn=connectDB.ConnectDb();
 	    	String query= "Select * from customer where cus_code = ? ";
 			pst = conn.prepareStatement(query);
 			pst.setString(1, text_cus_code.getText());
 			pst.execute();
 			rs=pst.executeQuery();
             
             if(rs.next()){
            	 String code_input = text_cus_code.getText();
            	 String code = rs.getString(4);
            	 
            	  if(code_input.equals(code)) {
            		 label_thongbao.setText("Code Customer Already Exists!");
            		 System.out.println(code_input+"  "+ code);            		
            		 label_thongbao.setTextFill(Color.RED);	
				}else {
				 	System.out.println(code_input+"  "+ code);					
 					label_thongbao.setText("Code Customer valid!");
					label_thongbao.setTextFill(Color.GREEN);	
				}
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	      if (text_cus_code.getText().trim().isEmpty()) {
//				label_thongbao.setText(" ");
//	     } 
    } 
    
	
    @FXML
    void btn_cus_add(ActionEvent event) {

    	
		conn = connectDB.ConnectDb();
		String sql = "insert into customer (cus_name,cus_code,cus_address,cus_issue,cus_gender,last_purchase_date,cus_point) values(?,?,?,?,?,?,?)";
        try {
        	int a = 0;
        	
            pst = conn.prepareStatement(sql);
            pst.setString(1, text_cus_name.getText());
            pst.setString(2, text_cus_code.getText());
            pst.setString(3, text_cus_address.getText());
            pst.setString(4, text_date.getText());
            String value = combobox_product_category.getValue();
            System.out.println(value);
            pst.setString(5, value);
            pst.setString(6, text_date.getText());
            pst.setInt(7, a);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Users Add succes");
            Stage stage = (Stage) root.getScene().getWindow();
            // do what you have to do
            stage.close();
            
        } catch (Exception e) {
        	System.out.println(e);
        	
            JOptionPane.showMessageDialog(null, "Please double check, you are entering missing Data or Barcode already exists!");
        }
    }


    
    
    
    
    
    
    
}
