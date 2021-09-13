package app.controller.employee_controller;

import java.awt.Dimension;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import app.Main;
import app.controller.employee_controller.Bill_Controller.export_inventory;
import app.controller.employee_controller.Inventory_crud.Inventory_Inset;
import app.controller.homepage.Home_Manage;
import app.controller.manage_controller.Product_controller.Barcode_Create.Barcode_Image;
import app.controller.manage_controller.product_crud.*;
import javax.swing.JOptionPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mysql.cj.result.Row;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

import app.dao.connectDB;
import app.model.Category1;
import app.model.Product;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.dao.connectDB;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import jxl.Workbook; 
import jxl.write.*;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import app.controller.homepage.Info_Employee;
import app.dao.connectDB;
import app.model.Bill;
import app.model.Inventory;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Inventory_employee implements Initializable{
	
	   @FXML
	    private TableColumn<Inventory,String> col_pro_name;

	    @FXML
	    private TableColumn<Inventory,String> col_date_input;

	    @FXML
	    private TableView<Inventory> table_inventory;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_no;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_wh_id;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_inp_de_id;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_amount;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_input_price;
	    
	    @FXML
	    private TextField search_inventory;
	    
	    @FXML
	    private TextField text_wh_id;
	    
	    @FXML
	    private TextField text_pro_id;
	    
	    @FXML
	    private TextField text_pro_name;
	    
	    @FXML
	    private TextField text_amount;
	    
	    @FXML
	    private TextField text_price_input;
	    
	    @FXML
	    private TextField text_date_input;
	    
	    @FXML
	    private Button Inventory_import;
	    
	    @FXML
		private Label amount_warehouse;
	    
	    @FXML
	    private TextField emp_id;
	    
	    @FXML
	    private Label ltotal;
	    
	    int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    ObservableList<Inventory> listM;
	    ObservableList<Inventory> dataList;

		private Result wh_id;
		
		 public void getEmp_id(String id_emp) {
		    	emp_id.setText(id_emp);
		    	System.out.println("emp_id bill employee: "+ emp_id.getText());
		    }
	
	    
	    public void UpdateTable_inventory(){

	    	col_no.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("no"));
	    	col_wh_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("wh_id"));
	    	col_inp_de_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("input_de_id"));
	    	col_amount.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("amount"));
	    	col_input_price.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("input_price"));
	    	col_pro_name.setCellValueFactory(new PropertyValueFactory<Inventory,String>("pro_name"));
	    	col_date_input.setCellValueFactory(new PropertyValueFactory<Inventory,String>("date_input"));
	    	
	    	


	        listM = connectDB.getDataInventory();
	        table_inventory.setItems(listM);
	    }
	    

	    @FXML
	    void search_user_inventory(){
	    	col_no.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("no"));
	    	col_wh_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("wh_id"));
	    	col_inp_de_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("input_de_id"));
	    	col_amount.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("amount"));
	    	col_input_price.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("input_price"));
	    	col_pro_name.setCellValueFactory(new PropertyValueFactory<Inventory,String>("pro_name"));
	    	col_date_input.setCellValueFactory(new PropertyValueFactory<Inventory,String>("date_input"));

		           dataList = connectDB.getDataInventory();
		           table_inventory.setItems(dataList);
		           FilteredList<Inventory> filteredData = new FilteredList<>(dataList, b -> true);  
		           search_inventory.textProperty().addListener((observable, oldValue, newValue) -> {
		    filteredData.setPredicate(person -> {
		       if (newValue == null || newValue.isEmpty()) {
		        return true;
		       }    
		       String lowerCaseFilter = newValue.toLowerCase();
		       
		       if (person.getPro_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
		        return true; // Filter matches name
		       } else if (person.getDate_input().toLowerCase().indexOf(lowerCaseFilter) != -1) {
		        return true; // Filter matches email
		       }
//		       else if (String.valueOf(person.getBusiness()).indexOf(lowerCaseFilter)!=-1)
//		            return true;// Filter matches username
		                                   
		            else  
		             return false; // Does not match.
		      });
		     });  
		     SortedList<Inventory> sortedData = new SortedList<>(filteredData);  
		     sortedData.comparatorProperty().bind(table_inventory.comparatorProperty());  
		     table_inventory.setItems(sortedData);      
		       
	    }
	    
	    
	    @FXML
		void getSelected(MouseEvent event) {
			index = table_inventory.getSelectionModel().getSelectedIndex();
			if (index <= -1) {

				return;
			}
			text_wh_id1.setText(col_wh_id.getCellData(index).toString());
//			text_pro_id.setText(col_pro_id.getCellData(index).toString());
//			text_amount_stock.setText(col_amount_stock.getCellData(index).toString());
//			text_amount_input.setText(col_amount_input.getCellData(index).toString());
//			text_price_input.setText(col_price_input.getCellData(index).toString());
//			text_pro_name.setText(col_pro_name.getCellData(index).toString());
//			text_date_input.setText(col_date_input.getCellData(index).toString());
//	        pass.setText(col_pass.getCellData(index).toString());
		}
	    
	    
		@FXML
		void load_inventory(ActionEvent event) {		
			UpdateTable_inventory();
		}
//		
	    
	    
	    
	    
	    @FXML
	    void Update_inventory(ActionEvent event) {
	    	
	    	
	    	
	    	
	    	
	    	try {
				if (wh_id.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select the data you want to delete!");
				} else {
					conn = connectDB.ConnectDb();
					String value1 = text_wh_id.getText();
		            String value2 = text_pro_id.getText();
		            String value3 = text_amount.getText();
		            String value4 = text_price_input.getText();
		            String value5 = text_pro_name.getText();
		            String value6 = text_date_input.getText();
					
					String sql = "update ware_house set pro_id= '" + value2 + "',amount '" + value3 + "',price_input '" + value4 + "',"
							+ "pro_name '" + value5 + "',date_input '" + value6 + "' where wh_id= '" + value1 + "' ";
					pst = conn.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Update");
					search_user_inventory();
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
	    }
	    
	    @FXML
	    private TextField text_product_id;
	    
	    @FXML
	    private TextField text_wh_id1;
	    
		@FXML
		void Delete_inventory(ActionEvent event) {
			conn = connectDB.ConnectDb();
			String sql = "delete from ware_house where wh_id = ?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, text_wh_id1.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Delete");
//				UpdateTable1();
				search_user_inventory();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		    try {
		        conn=connectDB.ConnectDb();
		        String query1="SELECT * FROM product ORDER BY pro_id DESC LIMIT 1;";
				pst= conn.prepareStatement(query1);
			    rs=pst.executeQuery();
			    while(rs.next()) {
			    	text_product_id.setText(rs.getString("pro_id"));
			    	System.out.println(text_product_id.getText());
			    	 
			    }	
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No2");
				System.out.println(e);
			}
		    
			conn = connectDB.ConnectDb();
			String sql1 = "delete from product where pro_id = ?";
			try {
				pst = conn.prepareStatement(sql1);
				pst.setString(1, text_product_id.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Delete");
//				UpdateTable1();
				search_user_inventory();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		    
		}

	    
	    
	    @FXML
	    void Inventory_import(MouseEvent event) {
		    try {
		        FXMLLoader Loader = new FXMLLoader(getClass().getResource("../../ui/employee/Inventory_import.fxml"));
		                Parent root = (Parent) Loader.load();
		                Stage stage = new Stage();
		                stage.setScene(new Scene(root)); 
		                Inventory_Inset id_emp=  Loader.getController();
		                String a1=emp_id.getText();
		                id_emp.getEmp_id(a1);
		                stage.show();             
		                UpdateTable_inventory();
		                search_user_inventory();
		                
		        } catch(Exception e) {
		        	
		           e.printStackTrace();
		          }
	    }
	    
	    
	    @FXML
	    void Inventory_bill(MouseEvent event) {
		    try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/employee/Inventory_bill.fxml"));
		                Parent root = (Parent) fxmlLoader.load();
		                Stage stage = new Stage();
		                stage.setScene(new Scene(root));  
		                stage.show();             
		                UpdateTable_inventory();
		                search_user_inventory();
		                
		        } catch(Exception e) {
		        	
		           e.printStackTrace();
		          }
	    }

	    
	    
	@FXML
	void exit(MouseEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../ui/homepage/Home_Employee.fxml"));
			Parent parent;
			
			parent = loader.load();
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		
    @FXML
    void export_product(ActionEvent event){
    	
    	
    }
	
	public static int product;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		UpdateTable_inventory();
		search_user_inventory();
		
		try {
			conn = connectDB.ConnectDb();
			String sql7 = "select count(pro_name) from product";
			pst = conn.prepareStatement(sql7);
			rs = pst.executeQuery();
			if (rs.next()) {
				product = rs.getInt(1);
				amount_warehouse.setText(Integer.toString(product));
				System.out.println("Tong product : --->" + product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
