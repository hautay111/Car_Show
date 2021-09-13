package app.controller.manage_controller.Product_controller;

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
import javafx.util.Callback;
import javafx.util.StringConverter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

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

public class product implements Initializable{
    @FXML
    private TableView<Product> table_product;
    @FXML
    private TableColumn<Product, Integer> col_product_id;

    @FXML
    private TableColumn<Product, String> col_product_name;

    @FXML
    private TableColumn<Product, String> col_product_price;

    @FXML
    private TableColumn<Product, String> col_product_amount;
    
    @FXML
    private TableColumn<Product, String> col_product_barcode;

    @FXML
    private TableColumn<Product, String> col_product_expiry;

    @FXML
    private TableColumn<Product, String> col_product_unit;

    @FXML
    private TableColumn<Product, String> col_product_brand;
    

    @FXML
    private TableColumn<Product, String> col_product_category;


    @FXML
    private TableColumn<Product, Integer> col_product_number;
    
    @FXML
    private TextField text_product_id;
    @FXML
    private TextField text_product_name;

    @FXML
    private TextField text_product_price;
    
    @FXML
    private TextField text_product_expiry;


    @FXML
    private TextField search_user_product;

    @FXML
    private TextField text_product_barcode;
    
    @FXML
    private BorderPane main;

    @FXML
    private Label label_amount;
    

    @FXML
    private DatePicker date1;
    
    @FXML
    private TextField test;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Product> listM;
    ObservableList<Product> dataList;
    
    @FXML
    private ComboBox<String> myComboBox;
    
    public void initialize(URL url, ResourceBundle rb) {
//    UpdateTable_product();
    	search_user_product();
//    showamount();

    
    // Code Source in description
    
//    DecimalFormat formatter = new DecimalFormat("###,###,###");
//    
//    double money = Double.parseDouble(text_product_price.getText()); 
//    
//    String moneyString = formatter.format(money);
//    System.out.println(moneyString);
       
    
    } 
    ObservableList<Category1> listM1;
    ObservableList<Category1> dataList1;
   
    
//    @FXML
//    void home(MouseEvent event) throws SQLException, IOException {
//    	Connection conn=connectDB.ConnectDb();
//		pst = conn.prepareStatement("select * from employee where emp_id=?");
//		pst.setString(1,a1);
//		ResultSet rs = pst.executeQuery();
//					
//		
//		if (rs.next()) {
//				System.out.println("dang nhap thanh cong");
//				Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
//				FXMLLoader loader=new FXMLLoader();
//				loader.setLocation(getClass().getResource("../../ui/manage/product/Product_sales.fxml"));								
//				Parent parent=loader.load();
//				Scene scene=new Scene(parent);				
//				stage.setScene(scene);			
//			}else{
//        		JOptionPane.showMessageDialog(null, "Usename or Password Blank.");	
//        	}		
//    }
    
    
//   void showamount() {
//	   conn = connectDB.ConnectDb();
//        try {
//        String sql ="SELECT COUNT(*) FROM product WHERE pro_id > 1";
//        ResultSet rs = conn.createStatement().executeQuery(sql); 
//			if (rs.next()) {
//				int i = rs.getInt(1); // access first column in result
//				    System.out.println(i);
//				    label_amount.setText("Total Product: "+i);
//				}
//		} catch (SQLException e) {
//			System.out.println(e);
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//   }
    
    
    @FXML
    void search_user_product(){
    	
//    	DecimalFormat formatter = new DecimalFormat("###,###,###");
//    	int a = 123;
//    	System.out.println(formatter.format(a)+" VNĐ");
    	
    	col_product_number.setCellValueFactory(new PropertyValueFactory<Product,Integer>("no"));
    	col_product_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
    	col_product_barcode.setCellValueFactory(new PropertyValueFactory<Product,String>("barcode"));
    	col_product_name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
    	col_product_price.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
    	col_product_expiry.setCellValueFactory(new PropertyValueFactory<Product,String>("expiry"));

	           dataList = connectDB.getDataProduct();
	           table_product.setItems(dataList);
	           FilteredList<Product> filteredData = new FilteredList<>(dataList, b -> true);  
	           search_user_product.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	       if (newValue == null || newValue.isEmpty()) {
	        return true;
	       }    
	       String lowerCaseFilter = newValue.toLowerCase();
	       
	       if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	        return true; // Filter matches name
	       }else if (person.getBarcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	        return true; // Filter matches barcode
	       }                     
	            else  
	             return false; // Does not match.
	      });
	     });  
	     SortedList<Product> sortedData = new SortedList<>(filteredData);  
	     sortedData.comparatorProperty().bind(table_product.comparatorProperty()); 
	     table_product.setItems(sortedData);      

    }    	
    
    
    
    
    
    

    public void UpdateTable_product(){
   
    	col_product_number.setCellValueFactory(new PropertyValueFactory<Product,Integer>("no"));
    	col_product_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
    	col_product_barcode.setCellValueFactory(new PropertyValueFactory<Product,String>("barcode"));
    	col_product_name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
    	col_product_price.setCellValueFactory(new PropertyValueFactory<Product,String>(("price")));
    	col_product_expiry.setCellValueFactory(new PropertyValueFactory<Product,String>("expiry"));
    	
        
        listM = connectDB.getDataProduct();
        table_product.setItems(listM);
    }

    @FXML
    void getSelected_product(MouseEvent event) {
        index = table_product.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        
        text_product_id.setText(col_product_id.getCellData(index).toString());
        text_product_barcode.setText(col_product_barcode.getCellData(index).toString());
        text_product_name.setText(col_product_name.getCellData(index).toString());
        text_product_price.setText(col_product_price.getCellData(index).toString());
        text_product_expiry.setText(col_product_expiry.getCellData(index).toString());
 
    }

    @FXML
    void btn_product_reset() {
    	text_product_id.setText("");
    	text_product_name.setText("");
    	text_product_price.setText("");
    	text_product_expiry.setText("");
    	text_product_barcode.setText("");
    	UpdateTable_product();
    }
		 
		


    @FXML
    void product_add(MouseEvent event) {
	    try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../ui/manage/product/crud_product.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             
	                UpdateTable_product();
//	                showamount();
	                search_user_product();
	                
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	          }
    }

    @FXML
    void product_delete(MouseEvent event) {
        conn = connectDB.ConnectDb();
        String sql = "delete from product where pro_id = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, text_product_id.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete");
                UpdateTable_product();
                search_user_product();
                btn_product_reset();
//                showamount();
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Products that have been sold cannot be deleted");
            }
    }
    
    @FXML
    void product_edit(MouseEvent event) {
        try {
        	
            conn = connectDB.ConnectDb();
            String value1 = text_product_id.getText();
            String value2 = text_product_name.getText();
            String value3 = text_product_price.getText();
            String value4 = text_product_expiry.getText();
            String value8 = text_product_barcode.getText();

            String sql = "update product set pro_name= '"+value2+"',pro_sale_price= '"+
            		value3+"',pro_expiry= '"+value4+"',barcode= '"+value8+"' where pro_id = '"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            search_user_product();
            btn_product_reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Update!");
            System.out.println(e);
        }
    }
    
    @FXML
    void load_product(MouseEvent event) throws SQLException {
    	UpdateTable_product();
//    	search_user_product();
    	text_product_id.setText("");
    	text_product_name.setText("");
    	text_product_price.setText("");
    	text_product_expiry.setText("");
    	text_product_barcode.setText("");
    	
    }

    
    @FXML
    void create_barcode(ActionEvent event) {
	 	PreparedStatement ps=null;
		Connection connection=null;
		connectDB obj_DBConnection=new connectDB();
		connection=obj_DBConnection.ConnectDb();
		ResultSet rs=null;
	try { 
		String query="select * from product";
		ps = connection.prepareStatement(query);
		rs=ps.executeQuery();
		while(rs.next()){
			Barcode_Image.createImage(rs.getString("barcode")+".png", rs.getString("barcode"));
//			Barcode_PDF.createPDF(rs.getString("barcode")+".pdf", rs.getString("barcode"));
			
			System.out.println("Creating Barcode for "+rs.getString("pro_name")+" :"+rs.getString("barcode"));
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(connection!=null){
			try {
					connection.close();
				}
			 catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(ps!=null){
			try {
				ps.close();
				}
			 catch (Exception e2) {
				 e2.printStackTrace();
			}
		}
		
	}
    }
    @FXML
    void export_product(ActionEvent event)  throws InterruptedException{
    	WritableWorkbook wworkbook;
    	
        try {

       	wworkbook = Workbook.createWorkbook(new File("C:\\Users\\hau\\git\\Project_QLST\\QLST\\src\\app\\view\\Export_product\\Product_Export.xls"));
       	
			connectDB obj_DBConnection_LMC=new connectDB();
			Connection connection=obj_DBConnection_LMC.ConnectDb();
			PreparedStatement ps=null;
			
			ResultSet rs=null;
			 
		 
 			String query="select * from product";
// 			pst.setString(1, btn_date.getText());
 			
// 			where date like '%oct-2015%'
			ps=connection.prepareStatement(query);
			System.out.println(ps);
			rs=ps.executeQuery();
			 WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
			 Label label = new Label(0, 2, "A label record");
			  wsheet.addCell(label);
	          int i=0;
			 
	           
	           int j=1;
			while(rs.next()){
				
				i=0;
				
				 label = new Label(i++, j, j+"");
				  wsheet.addCell(label);					  
				  label = new Label(i++, j, rs.getString("barcode"));
				  wsheet.addCell(label);
				 label = new Label(i++, j, rs.getString("pro_name"));
				  wsheet.addCell(label);
				  label = new Label(i++, j, rs.getString("pro_sale_price"));
				  wsheet.addCell(label);
				  label = new Label(i++, j, rs.getString("pro_expiry"));
				  wsheet.addCell(label);
				j++;
			}
        wworkbook.write();
        wworkbook.close();
        System.out.println("fineshed");
        JOptionPane.showMessageDialog(null, "successfully exported the product file!");
        
        
        } catch (Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "The process cannot access the file because it is being used by another process");
		}
    }
    

    @FXML
    void import_product(ActionEvent event) {
        try {
        	conn=connectDB.ConnectDb();
            String query = "Insert into product (barcode,pro_name,pro_sale_price,pro_unit) values (?,?,?,?)";

            pst = conn.prepareStatement(query);

           

            FileInputStream fileIn = new FileInputStream(new File("C:\\Users\\hau\\git\\Project_QLST\\QLST\\src\\app\\view\\Export_product\\Product_import.xls"));

           

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);

            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFRow row;

            for(int i=1; i<=sheet.getLastRowNum(); i++){

                row = sheet.getRow(i);
                
                pst.setString(1, row.getCell(0).getStringCellValue());

                pst.setString(2, row.getCell(1).getStringCellValue());
                
                pst.setInt(3, (int) row.getCell(2).getNumericCellValue());

                pst.setString(4, row.getCell(3).getStringCellValue());


                pst.execute();

            }

            Alert alert = new Alert(AlertType.INFORMATION);

            alert.setTitle("Information Dialog");

            alert.setHeaderText(null);

            alert.setContentText("User Details Imported From Excel Sheet To Database.");

            alert.showAndWait();

           

            wb.close();

            fileIn.close();

            pst.close();

            rs.close();

        } catch (SQLException | FileNotFoundException ex) {

            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);

        } catch (IOException ex) {

            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);

        }

   }
    
    
    
    
   
    
    
}
