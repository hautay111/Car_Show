package app.controller.manage_controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.controller.manage_controller.search_dashhoard.search_date;
import app.dao.connectDB;
import app.model.search_dashboard.Search_date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class search {
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	@FXML
    private BorderPane main;

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker from;

    @FXML
    private DatePicker to;
    
    @FXML
    void back(MouseEvent event) {
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_1(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/quarter_1.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_2(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/quarter_2.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_3(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/quarter_3.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_4(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/quarter_4.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T1(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m1.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T10(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m10.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T11(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m11.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T12(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m12.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T2(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m2.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T3(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m3.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T4(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m4.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T5(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m5.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T6(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m6.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T7(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m7.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T8(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m8.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void T9(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/m9.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public static String a1;
    @FXML
    void search_date1(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/search_date.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                search_date sd=fxmlLoader.getController();
	                sd.getDate(date.getValue().toString());
	                System.out.println(date.getValue().toString());;	                
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    void search0(ActionEvent event) {
    	
    }
//    public String get_date() {
//    	String date1=date.getEditor().getText(); 
//    	return date1;
//    }
//    
//  //----------------------Month Search_date--------------------
//    public static ObservableList<Search_date> getDataSearch_date() {
//        Connection conn = connectDB.ConnectDb();
//        ObservableList<Search_date> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.*,product.pro_name,amount_stock+amount_input AS \"total_amount\" FROM ware_house,product WHERE amount_stock+amount_input<100 AND ware_house.pro_id=product.pro_id AND DATE(ware_house.date_input)=''"+date.getEditor().getText()+"''");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()){   
//                list.add(new Search_date(
//                		Integer.parseInt(rs.getString("pro_id")), 
//                		Integer.parseInt(rs.getString("total_amount")), 
//                		rs.getRow(),
//                		rs.getString("pro_name")
//                	));    
//                
//            }
//        } catch (Exception e) {
//        	System.out.println(e);
//        }
//        return list;
//        
//	}
}
