package app.controller.employee_controller.Bill_Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import javax.swing.JOptionPane;

import app.controller.employee_controller.Bill_Manage;
import app.controller.employee_controller.security;
import app.controller.homepage.Home_Employee;
import app.controller.homepage.Home_Manage;
import app.dao.connectDB;
import app.model.Bill;
import app.model.Order_Detail;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Bill_Controller implements Initializable{
	
	public static ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null;
	public static PreparedStatement pst1 = null, pst2 = null, pst3 = null, pst4 = null, pst5 = null;
	public static Integer kho, mua, thieu, du, spmua, spkho;
	public static String sql1, sql2, sql3, sql4, sql5;
	
	@FXML
    private AnchorPane main;
	
    @FXML
    private Label text_code;

    @FXML
    private Label text_name;
    
    @FXML
    private Label text_total;

    @FXML
    private Label label_tien;

    @FXML
    private Label label_point_bill;
    
    @FXML
    private Label text_price;

    @FXML
    private TextField text_amount;

    @FXML
    private Label text_discount;

    @FXML
    private Button btn_add_product;

    @FXML
    private TextField text_cus_code;
    
    @FXML
    private TextField emp_id;

    @FXML
    private TextField id_order;

    @FXML
    private TextField order_id1;
    
    @FXML
    private TextField text_barcode_barcode;

    @FXML
    private Label label_cus_name;

    @FXML
    private Label total_bill_order;

    @FXML
    private Label total_bill_pay;

    @FXML
    private TextField tienthoi;

    @FXML
    private Label tienphaitrakhach;

    @FXML
    private TextField text_cus_id;
    
    @FXML
    private TextField text_pro_id;
    
    @FXML
    private Label label_cus_poit; 
	
    @FXML
    private Label date_text;
    
    @FXML
    private Label label_show_discount;
    
    @FXML
    private Label label_cus_point_end;
    
    @FXML
    private Button btn_create_order;
    
    @FXML
    private Label label_thongbao;
    
    @FXML
    private Button btn_print;

    @FXML
    private Button btn_buy_order;

    @FXML
    private Button btn_remove;

    @FXML
    private Button btn_refresh;
    
    @FXML
    private TableView<Order_Detail> table_order;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_no;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_id;

    @FXML
    private TableColumn<Order_Detail, String> col_bill_name;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_price;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_amount;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_total;
    
    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_order_detail;

    @FXML
    private ImageView image_emp;
    
    @FXML
    private ImageView image_order;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Order_Detail> listM;
    ObservableList<Order_Detail> dataList;
@Override  
public void initialize(URL url, ResourceBundle rb) {

//    	Image image = new Image(getClass().getResourceAsStream("/app/image/ordeer.gif"));
//    	image_order.setImage(image);
	

	   	
//    String text = image_qrcode_emp.getText();              
//    File file = new File("app/image/"+text);
//    Image image = new Image(file.toURI().toString());
//    image_emp.setImage(image);
//    System.out.println(file+"/  /"+text);
	
    	UpdateTable_Order_detail();
    	showdate();
    	text_cus_id.setText("0");
        label_cus_poit.setText("0");
        label_cus_name.setText("Walk-in Guest");    	
        search_user_bill_order();
        id_order.setText("");
        label_cus_point_end.setText("0");
    }
private String imageUrl;
@FXML
private void handleUpload(ActionEvent t) {
    FileChooser fileChooser = new FileChooser();

    //Set extension filter


    //Show open file dialog
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
        try {
            imageUrl = file.toURI().toURL().toExternalForm();
            Image image = new Image(imageUrl);
            System.out.println(imageUrl);
            image_emp.setImage(image);
        } catch (MalformedURLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
    public static  String DateFormat = "yyyy-MM-dd";
    public void showdate()
    {
      Calendar cal= Calendar.getInstance();
      SimpleDateFormat format = new SimpleDateFormat(DateFormat);
      date_text.setText(format.format(cal.getTime()));

     }     
//      public void image_qrcode_emp() {
//    	  
//    	    String text = image_qrcode_emp.getText();              
////    	    File file = new File("/app/image/ordeer.gif");
//    	    Image image = new Image("/app/image/"+text);
//    	    image_emp.setImage(image);
//    	    System.out.println("/"+text);
//    	  
////    	  image_emp.imageProperty().bind(Bindings.createObjectBinding(() -> {
////  	        File file = new File("/app/image/"+image_qrcode_emp.getText());
////  	        if (file.exists()) {
////  	            return new Image(file.toURI().toString());
////  	        } else {
////  	            return null ;
////  	        }
////  	    }, image_qrcode_emp.textProperty()
////  	    
////  	    ));
////     
//      }
      
        

    
    @FXML
    void add_cus(ActionEvent event) {
	    try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../ui/employee/Customer.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	          }
    }
    
    @FXML
    void handleScanCustomer(KeyEvent event) {
    	
    	scannBarcode();
    }
    private void scannBarcode(){
        try {
                            
	    	conn=connectDB.ConnectDb();
	    	String query= "Select * from customer where cus_code = ? ";
			pst = conn.prepareStatement(query);
			pst.setString(1, text_cus_code.getText());
			pst.execute();
			rs=pst.executeQuery();
            
            if(rs.next()){
            	
                int cus_id,cus_code,cus_point;
                String cus_name;
                cus_id = rs.getInt(1);
                cus_name = rs.getString(2); 
                cus_code = rs.getInt(4);
                cus_point = rs.getInt(8);

                text_cus_id.setText(String.valueOf(cus_id));
                label_cus_poit.setText(String.valueOf(cus_point));
                label_cus_name.setText(cus_name);
                
                if (text_cus_code.getText().trim().equals("")) {
                	text_cus_id.setText("0");
                	label_cus_point_end.setText("0");
				}
                
                if (cus_point>=1000 && cus_point<=30000) {
                	
                    int diem = (cus_point/1000);
                    text_discount.setText(Integer.toString(diem));
                    label_cus_point_end.setText("0");
                    label_show_discount.setText( diem +"% off");
                  }else if (cus_point > 30000 ) {
                  	int x = 0;
                                 	
                  	int point = (30000/1000);            	
                      int diem = (cus_point-30000);
                      
                      text_discount.setText(Integer.toString(point));
                      label_cus_point_end.setText(String.valueOf(diem));
  					System.out.println(point);
  					System.out.println(diem);
  					label_show_discount.setText("30% off");
  					
  				}else if (cus_point < 1000) {
  					
  					
  					
  					label_cus_point_end.setText(String.valueOf(cus_point));
  					label_cus_poit.setText(String.valueOf(cus_point));
  					System.out.println("You don't have enough points to get a discount. Let's try to steal!");
  					label_show_discount.setText("0% off");
  					text_discount.setText("0");
  				}
                
                
                  

            } 
	
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	
    }
    
     @FXML
    private Label name_emp_bill;
    private String a ,b;
    public void getEmp_id(String id_emp, String name_emp ,String title) {
    	emp_id.setText(id_emp);
    	name_emp_bill.setText(name_emp);
    	System.out.println("emp_id bill employee: "+ emp_id.getText() +"name bill employee:"+name_emp_bill.getText());
    	
    	a = emp_id.getText();
    	b=name_emp_bill.getText();
    	
    }   
    
	@FXML
		void exit(MouseEvent event) {
			try {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../../../ui/homepage/Home_Employee.fxml"));
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
    void new_orders(ActionEvent event) {
    	try {
	    	conn=connectDB.ConnectDb();
	    	String query= "insert into orders (emp_id,cus_id) VALUES (?,?)";
			pst = conn.prepareStatement(query);
			pst.setString(1, emp_id.getText());
			pst.setString(2, text_cus_id.getText());
			pst.execute();
			System.out.println("tao new orders thanh cong");
		    try {
		        conn=connectDB.ConnectDb();
		        String query1="SELECT orders.*,employee.emp_name FROM orders,employee WHERE orders.emp_id=employee.emp_id ORDER BY orders.order_id DESC LIMIT 1 ";
				pst= conn.prepareStatement(query1);
			    rs=pst.executeQuery();
					    if(rs.next()) {
					    	id_order.setText(rs.getString("order_id"));
					    	 order_id1.setText(rs.getString("order_id"));
					    	 label_thongbao.setText("Create Succeed !!!");
					    	 
					    	 btn_refresh.setDisable(false);
					    	 btn_buy_order.setDisable(false);
					    	 btn_print.setDisable(false);
					    	 btn_add_product.setDisable(false);
					    	 total_bill_order.setText("");
					    	 tienphaitrakhach.setText("");
					    	 tienthoi.setText("");
					    	 total_bill_pay.setText("");
					    	 text_barcode_barcode.setText("");
					    	    
					    }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    
		    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }


    @FXML
    void Scanner_barcode_product(KeyEvent event) {
    	search_barcode_product();
    }
    
    public void search_barcode_product() {
    	try {
		    	conn=connectDB.ConnectDb();
		    	String query= "Select * from product where barcode = ? ";
				pst = conn.prepareStatement(query);
				pst.setString(1, text_barcode_barcode.getText());
				pst.execute();
				rs=pst.executeQuery();
	            
	            if(rs.next()){
	            	
	            	int pro_id = rs.getInt("pro_id");
	            	String pro_name = rs.getString("pro_name");
	            	int pro_price = rs.getInt("pro_sale_price");
	            	text_name.setText(pro_name);
	            	text_price.setText(String.valueOf(pro_price));
	            	text_pro_id.setText(String.valueOf(pro_id));

	            }if (text_barcode_barcode.getText().trim().equals("")) {
	                	text_name.setText("");
	                	text_price.setText("");
	                	text_amount.setText("");
	                	text_total.setText("");
					}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    
    @FXML
    void tinh_total(KeyEvent event) {
    	int amount = Integer.parseInt(text_amount.getText());
    	int price = Integer.parseInt(text_price.getText());
    	int total = amount * price;
    	
    	text_total.setText(String.valueOf(total));
    	
    }
    
    @FXML
    void Add_product_order(ActionEvent event) {
    	
    	
    	
    	
    	if (text_amount.getText().trim().equals("")||text_name.getText().trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Please choose a product or amount");
    	}
    	try {
	    	conn=connectDB.ConnectDb();
	    	String query= "Select *,sum(amount) from input_detail WHERE pro_id = ? group by pro_id=?";
			pst = conn.prepareStatement(query);
			pst.setString(1, text_pro_id.getText());
			pst.setString(2, text_pro_id.getText());
			pst.execute();
			rs=pst.executeQuery();
    		if (rs.next()) {
    			
                int pro_id,amount_stock;
                int input_detail_id = rs.getInt(1); 
                pro_id = rs.getInt(4); 
                amount_stock = rs.getInt(9);
                System.out.println(pro_id+"   "+amount_stock);              
                int amount_product = Integer.parseInt(text_amount.getText());
   
                int amount = amount_stock - amount_product;
                System.out.println(amount);
                
                
                if (amount_stock == 0) {
                	label_thongbao.setText("out of stock !!!");
				}
                
                if (amount>=0) {
					
//                    conn = connectDB.ConnectDb();
//                    String sqlz = "update input_detail set amount= '"+amount+"' where input_detail_id = '"+input_detail_id+"' ";
//                    pst= conn.prepareStatement(sqlz);
//                    pst.execute();
                	
           		conn = connectDB.ConnectDb();
     	        String sql = "insert into orders_detail (name,quantity,price,total,order_id,pro_id)values(?,?,?,?,?,?)";
     	        		try {
     	        			 	id_order.setText("");
		     		            pst = conn.prepareStatement(sql);
		     		            pst.setString(1, text_name.getText());
		     		            pst.setString(2, text_amount.getText());       
		     		            pst.setString(3, text_price.getText());
		     		            pst.setString(4, text_total.getText());      
		     		            pst.setString(5, order_id1.getText());
		     		            pst.setString(6, text_pro_id.getText());
		     		            pst.execute();
		     		           search_user_bill_order();
				     		} catch (Exception e) {
				     			System.out.println(e);
				     			// TODO: handle exception
				     		}
//     	           		conn = connectDB.ConnectDb();
//     	     	        String sql1 = "insert into output (emp_id,order_id)values(?,?)";
//     	     	        		try {
//     			     		            pst = conn.prepareStatement(sql1);
//     			     		            pst.setString(1, emp_id.getText());
//     			     		            pst.setString(2, order_id1.getText());       
//     			     		            pst.execute();
//     	     		            
//     					     		} catch (Exception e) {
//     					     			// TODO: handle exception
//     					     			System.out.println(e);
//     					     		}	
     	     	        		
							    try {
							        conn=connectDB.ConnectDb();
							        String query1e="SELECT orders.*,employee.emp_name FROM orders,employee WHERE orders.emp_id=employee.emp_id ORDER BY orders.order_id DESC LIMIT 1 ";
									pst= conn.prepareStatement(query1e);
								    rs=pst.executeQuery();
								    while(rs.next()) {
								    	 id_order.setText(rs.getString("order_id"));
								    	 btn_remove.setDisable(false);
								    }		   
					            
							        
						        } catch (Exception e) {
						        	System.out.println(e);
						        	
						            JOptionPane.showMessageDialog(null,e);
						        }
			                	text_name.setText("");
			                	text_price.setText("");
			                	text_amount.setText("");
			                	text_total.setText("");
			                	text_barcode_barcode.setText("");
     	        		          	
					}else {
						JOptionPane.showMessageDialog(null, " shortage! Stock: "+amount_stock +" Product");
					}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
    	
//    	output_ammount();
    	

    }

    
   public void UpdateTable_Order_detail(){
        	col_bill_no.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("no"));
        	col_bill_id.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("id"));
        	col_bill_order_detail.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("id_detail"));
        	col_bill_name.setCellValueFactory(new PropertyValueFactory<Order_Detail,String>("name"));
        	col_bill_amount.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("quantity"));
        	col_bill_price.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("price"));
        	col_bill_total.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("total"));
        	
        	

            listM = connectDB.getDataOrder_detail();
            table_order.setItems(listM);
        } 
   
   
   
  public void search_user_bill_order(){
   	col_bill_no.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("no"));
   	col_bill_id.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("id"));
   	col_bill_order_detail.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("id_detail"));
   	col_bill_name.setCellValueFactory(new PropertyValueFactory<Order_Detail,String>("name"));
   	col_bill_amount.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("quantity"));
   	col_bill_price.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("price"));
   	col_bill_total.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("total"));
   			
		    dataList = connectDB.getDataOrder_detail();
		    table_order.setItems(dataList);
		    FilteredList<Order_Detail> filteredData = new FilteredList<>(dataList, b -> true);  
		    id_order.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(person -> {
		if (newValue == null || newValue.isEmpty()) {
		 return true;
		}    
		String lowerCaseFilter = newValue.toLowerCase();
		
		if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
		 return true; // Filter matches name
		}
		else if (String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1)
		     return true;// Filter matches username
		                            
		     else  
		      return false; // Does not match.
		});
		});  
		SortedList<Order_Detail> sortedData = new SortedList<>(filteredData);  
		sortedData.comparatorProperty().bind(table_order.comparatorProperty());  
		table_order.setItems(sortedData);      
   	
	       
   }
   
  @FXML
  void Buy_order(ActionEvent event) throws SQLException {		
		  
	      try {
	        	
	      	   Integer TotalPrice = 0;
	      	   TotalPrice = table_order.getItems().stream().map(
	    	        (item) -> item.getTotal()).reduce(TotalPrice, (accumulator, _item) -> accumulator + _item);
	      	   total_bill_order.setText((String.valueOf(TotalPrice)));
	      	   
	      	  	double z = 0;
	      	  	double q = 0;	     	  	
	      	  	z = Integer.parseInt(text_discount.getText());	
	      	  	q = 100 - z;   
	    
	      	  	double discount = (TotalPrice*(q/100));
	      	  	total_bill_pay.setText(String.valueOf(discount));
	           conn = connectDB.ConnectDb();
	           String value1 = order_id1.getText();
	           String value2 = total_bill_order.getText();
	           String value3 = text_cus_id.getText();
	           String sql = "update orders set total_price= '"+discount+"',cus_id= '"+value3+"' where order_id = '"+value1+"' ";
	           pst= conn.prepareStatement(sql);
	           pst.execute();
	   	      } catch (Exception e) {
//	   	          JOptionPane.showMessageDialog(null, e);
	   	      }
		  

      try {
      	
	   	   Integer TotalPrice = 0;
	   	   TotalPrice = table_order.getItems().stream().map(
	 	        (item) -> item.getTotal()).reduce(TotalPrice, (accumulator, _item) -> accumulator + _item);
	   	   total_bill_order.setText((String.valueOf(TotalPrice)));
	   	   
	   	  	double z = 0;
	   	  	double q = 0;	     	  	
	   	  	z = Integer.parseInt(text_discount.getText());	
	   	  	q = 100 - z;   
	 
	   	  	double discount = (TotalPrice*(q/100));
	   	  	total_bill_pay.setText(String.valueOf(discount));
	   	  	double point;
	   	  	double point_bill;
	   	  	
	   	  double a = 100000;
	        double b = 200000;
	        double c = 300000;
	        double d = 500000;
	        double e = 1000000;
	        double f = 2000000;
	        
	
	        
	       int p = Integer.parseInt(label_cus_point_end.getText());	
	
	        if (discount < a) {
	      	   point = p + 50;
	      	   point_bill = 50;
	      	   System.out.println(point);
	      	   label_tien.setText((String.valueOf(point)));
	      	   label_point_bill.setText((String.valueOf(point_bill)));
				}else if(discount >= a && discount <= b ) {
		      	   		point = (p + 100);
		      	   	point_bill = 100;
		      	   		System.out.println(point);
					label_tien.setText((String.valueOf(point)));
					label_point_bill.setText((String.valueOf(point_bill)));
		          }else if (discount > b && discount <= c) {
		          	   point = (p + 200);
		          	 point_bill = 200;
		          	 label_tien.setText((String.valueOf(point)));
		          	label_point_bill.setText((String.valueOf(point_bill)));
		  			System.out.println(point);
				}else if (discount > c && discount <= d) {
			      	   point = (p + 300);
			      	 point_bill = 300;
			      	 label_tien.setText((String.valueOf(point)));
			      	label_point_bill.setText((String.valueOf(point_bill)));
						System.out.println(point);
				}else if (discount > d && discount <= e) {
			      	   point = (p + 500);
			      	 point_bill = 500;
			      	 label_tien.setText((String.valueOf(point)));
			      	label_point_bill.setText((String.valueOf(point_bill)));
						System.out.println(point);
				}else if (discount > e && discount <= f) {
			      	   point = (p + 1000);
			      	   point_bill = 1000;
			      	 label_tien.setText((String.valueOf(point)));
			      	label_point_bill.setText((String.valueOf(point_bill)));
						System.out.println(point);
				}
	  	  			int value = Integer.parseInt(text_cus_id.getText());
	  	  			if (value != 0) {
			          conn = connectDB.ConnectDb();
			          String sql = "update orders set point= '"+label_point_bill.getText()+"', cus_id = '"+text_cus_id.getText()+"', discount = '"+text_discount.getText()+"' where order_id = '"+order_id1.getText()+"' ";
			          pst= conn.prepareStatement(sql);
			          pst.execute();
	  	  			}else {
	  	  				int diem=0;
				          conn = connectDB.ConnectDb();
				          String sql = "update orders set point= '"+diem+"', cus_id = '"+text_cus_id.getText()+"', discount = '"+text_discount.getText()+"' where order_id = '"+order_id1.getText()+"' ";
				          pst= conn.prepareStatement(sql);
				          pst.execute();
					}
		      } catch (Exception e) {
//		          JOptionPane.showMessageDialog(null, e);
		      }
	      
	      
	      try {
	   	  	int z = 0;
	   	  	z = Integer.parseInt(label_cus_poit.getText());	
	          int t = Integer.parseInt(label_cus_poit.getText());
	          int id_cus = Integer.parseInt(text_cus_id.getText());
	          int point = t - z;
	          
	          
	          
	        conn = connectDB.ConnectDb();
	        String sql = "update customer set cus_point= '"+label_point_bill.getText()+"', last_purchase_date = '"+date_text.getText()+"' where cus_id = '"+id_cus+"' ";
	        pst= conn.prepareStatement(sql);
	        pst.execute();
		      } catch (Exception e) {
//		          JOptionPane.showMessageDialog(null, e);
		      }
	      
	      
//	      ----------------------------------------------------------
	      conn = connectDB.ConnectDb();
			sql1 = "SELECT orders_detail.order_detail_id,orders_detail.order_id,orders_detail.pro_id,SUM(orders_detail.quantity) AS 'quantity',product.pro_name,product.pro_sale_price,(SUM(orders_detail.quantity)*product.pro_sale_price) AS'total' FROM orders_detail,product WHERE orders_detail.pro_id=product.pro_id AND orders_detail.order_id=(SELECT orders.order_id FROM orders ORDER BY orders.order_id DESC LIMIT 1) GROUP BY orders_detail.pro_id";
			pst1 = conn.prepareStatement(sql1);
			rs1 = pst1.executeQuery();// orders:
										// order_detail_id,order_id,pro_id,pro_name,quantity,pro_sale_price,total
			while (rs1.next()) {
				mua = rs1.getInt("quantity");
				spmua = rs1.getInt("pro_id");

				sql2 = "SELECT input_detail.*,ware_house.wh_id FROM input_detail,ware_house WHERE ware_house.input_detail_id=input_detail.input_detail_id AND input_detail.pro_id='"
						+ spmua + "' AND input_detail.amount!=0";
				pst2 = conn.prepareStatement(sql2);
				rs2 = pst2.executeQuery();

				System.out.println(sql2);// kho: input_detail_id,input_id,pro_id,expiry,amount,input_price,total,wh_id
				System.out.println("");

				while (rs2.next()) {
					sql3 = "SELECT * FROM output ORDER BY output_id DESC LIMIT 1";
					pst3 = conn.prepareStatement(sql3);// output_id,emp_id,time,order_id,status
					rs3 = pst3.executeQuery();
					if (rs2.getInt("amount") >= mua && rs3.next()) {
						kho = rs2.getInt("amount") - mua;

						sql4 = "UPDATE input_detail SET input_detail.amount='" + kho
								+ "' WHERE input_detail.input_detail_id='" + rs2.getInt("input_detail_id") + "'";
						pst4 = conn.prepareStatement(sql4);
						pst4.execute();
						System.out.println("sql4: " + sql4);
						sql5 = "INSERT INTO output_detail (output_id,wh_id, quantity) VALUES ('" + rs3.getInt("output_id")
								+ "', '" + rs2.getInt("wh_id") + "', '" + mua + "');";
							pst5 = conn.prepareStatement(sql5);
							pst5.execute();
						System.out.println(sql5);
						System.out.println("sp mua : " + spmua + " / sl luong mua: " + mua);
						System.out.println("cap nha so luong lai vao kho : " + kho);
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("");

						mua = 0;
					}

					while (mua > rs2.getInt("amount") && mua > 0 && rs3.next()) {
						mua = mua - rs2.getInt("amount");

						System.out.println("sql4: " + sql4);
						sql5 = "INSERT INTO `output_detail` (`output_id`, `wh_id`, `quantity`) VALUES ('"
								+ rs3.getInt("output_id") + "', '" + rs2.getInt("wh_id") + "', '" + rs2.getInt("amount")
								+ "');";
							pst5 = conn.prepareStatement(sql5);
							pst5.execute();
						System.out.println("sql5: " + sql5);
						System.out.println("sp mua : " + spmua + " / sl luong mua: " + mua);
						System.out.println("sl mua con thieu: " + mua);
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("");
						kho = 0;

						sql4 = "UPDATE input_detail SET input_detail.amount='" + kho
								+ "' WHERE input_detail.input_detail_id='" + rs2.getInt("input_detail_id") + "'";
						pst4 = conn.prepareStatement(sql4);
						pst4.execute();
					}

				}
//				-----------------------------------------------
//				System.out.println("sp mua : "+spmua+" / sl luong mua: "+mua);
//				System.out.println("");
//				System.out.println("-------------------------------------");
//				System.out.println("");
			}

	      System.out.println("up date staus output_status = 1");
	  }

  public void output_ammount() {
	  
  	try {
    	conn=connectDB.ConnectDb();
    	String query= "Select *,sum(amount) from input_detail where pro_id = ? ";
		pst = conn.prepareStatement(query);
		pst.setString(1, text_pro_id.getText());
		pst.execute();
		rs=pst.executeQuery();
		if (rs.next()) {
			
            int pro_id,amount_stock;

            pro_id = rs.getInt(4); 
            amount_stock = rs.getInt(5);
            System.out.println(pro_id+"   "+amount_stock);              
            int amount_product = Integer.parseInt(text_amount.getText());

            int amount = amount_stock - amount_product;
            System.out.println(amount);
	  
		      while(amount_product>=0) {
		    	if (amount_product > amount_stock) {
		    		
		             amount_product = amount_product - amount_stock;
		            System.out.println(amount_product);
    		
			    	}else {
			    		
			    	}
			    } 
      
		}
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
  }
  
  
  @FXML
  void select_order_row(MouseEvent event) {
	  
      index = table_order.getSelectionModel().getSelectedIndex();
      if (index <= -1){
      
          return;
      }
      
      text_pro_id.setText(col_bill_order_detail.getCellData(index).toString());
	  
  }
  
  @FXML
  void btn_remove_order_row(ActionEvent event) {
	  id_order.setText("");
      conn = connectDB.ConnectDb();
      String sql = "delete from orders_detail where order_detail_id = ?";
          try {
              pst = conn.prepareStatement(sql);
              pst.setString(1, text_pro_id.getText());
              pst.execute();
              label_thongbao.setText("successful delete!");
              search_user_bill_order();
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          
		    try {
		        conn=connectDB.ConnectDb();
		        String query1e="SELECT orders.*,employee.emp_name FROM orders,employee WHERE orders.emp_id=employee.emp_id ORDER BY orders.order_id DESC LIMIT 1 ";
				pst= conn.prepareStatement(query1e);
			    rs=pst.executeQuery();
			    while(rs.next()) {
			    	 id_order.setText(rs.getString("order_id"));
			    	 
			    }	
	          } catch (Exception e) {
	              JOptionPane.showMessageDialog(null, e);
	          }
		    
		    
		    
  }  
  
  
  @FXML
  void Scanner_cash(KeyEvent event) {
  	double tienbill = Double.parseDouble(total_bill_pay.getText());	
  	double tienkhachdua = Double.parseDouble(tienthoi.getText());	
  	
  	double tienphaitra = tienkhachdua - tienbill;
  	
  	tienphaitrakhach.setText((String.valueOf(tienphaitra))+"$");
  	
  	if (tienphaitra < 0 ) {
  		tienphaitrakhach.setText("0$");
		}
  }
  
  
  @FXML
  void print_bill(ActionEvent event) throws SQLException {
	  total_bill_pay.setText(total_bill_pay.getText()+" $");
	  tienthoi.setText(tienthoi.getText()+" $");
      try {
          JasperDesign jasdi=JRXmlLoader.load("C:\\Users\\hau\\git\\Project_QLST\\QLST\\src\\app\\ui\\employee\\Invoice.jrxml");
          String sql1s="SELECT *, SUM(quantity) AS amount,sum(total) as total1 FROM orders_detail WHERE order_id ='"+order_id1.getText()+"' GROUP BY order_id, name";
          JRDesignQuery newQuery=new JRDesignQuery();
          newQuery.setText(sql1s);

          jasdi.setQuery(newQuery);
          /////////////
          HashMap<String, Object> para=new HashMap<>();
          
          para.put("total",total_bill_pay.getText());
          para.put("cash",tienthoi.getText());
          para.put("tiendu",tienphaitrakhach.getText());
          btn_refresh.setDisable(true);
          id_order.setText("   ");
          text_cus_code.setText("0");
          btn_print.setDisable(true);
          total_bill_order.setText("");
          tienthoi.setText("");
          total_bill_pay.setText("");
          text_barcode_barcode.setText("");
          JasperReport js=JasperCompileManager.compileReport(jasdi);
          JasperPrint jp=JasperFillManager.fillReport(js,para,conn);
          // JasperExportManager.exportReportToHtmlFile(jp ,ore);
          JasperViewer.viewReport(jp,false);
      } catch (Exception e) {
          System.out.println(e);
      }
	  


	  
  }
  
  @FXML
  void btn_refresh_order(ActionEvent event) {
	  String ID=order_id1.getText();
	  System.out.println(order_id1.getText().toString());
	  id_order.setText("   ");
	  try {
	      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../ui/employee/security.fxml"));
	      Parent root = (Parent) fxmlLoader.load();
	      Stage stage = new Stage();
	 security home=fxmlLoader.getController();
      home.getOrderId(ID);
	      stage.setScene(new Scene(root));  
	      stage.show();
	      
			} catch(Exception e) {
			 e.printStackTrace();
			}
	  
  }
  
  
  @FXML
  void btn_view_order(ActionEvent event) {
  	try {
		Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("../../../ui/employee/Manage_Bill.fxml"));							
		Parent parent=loader.load();
//		Bill_Manage id_emp=  loader.getController();
//		id_emp.getEmp1(a,b);
		Scene scene=new Scene(parent);				
		stage.setScene(scene);
		stage.show();
		

	    } catch (Exception ex) {
	        System.out.println("y"+ex.getMessage());
	    }
  }
  
  @FXML
  void btn_dua_du(MouseEvent event) {
	  tienthoi.setText(total_bill_pay.getText());
	  tienphaitrakhach.setText("0 $");
  }


}
