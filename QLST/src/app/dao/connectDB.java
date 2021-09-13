package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.model.Account1;
import app.model.Bill;
import app.model.Bill_Mange;
import app.model.Inventory;
import app.model.ChangeShift;

import app.model.Product;
import app.model.Run_Out;
import app.model.Supplier;
import app.model.Title;
import app.model.Unit;
import app.model.Ware_house;
import app.model.search_dashboard.M1;
import app.model.search_dashboard.M10;
import app.model.search_dashboard.M11;
import app.model.search_dashboard.M12;
import app.model.search_dashboard.M2;
import app.model.search_dashboard.M3;
import app.model.search_dashboard.M4;
import app.model.search_dashboard.M5;
import app.model.search_dashboard.M6;
import app.model.search_dashboard.M7;
import app.model.search_dashboard.M8;
import app.model.search_dashboard.M9;
import app.model.search_dashboard.Quarter_1;
import app.model.search_dashboard.Quarter_2;
import app.model.search_dashboard.Quarter_3;
import app.model.search_dashboard.Quarter_4;
import app.model.search_dashboard.Search_date;
import app.model.Brand1;
import app.model.Category1;
import app.model.Dashboard;
import app.model.Order_Detail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;

public class connectDB {
	Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/qlst21","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            System.out.println("ket noi database thanh cong");
            return conn;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	Connection conn=connectDB.ConnectDb();
    	
    }
    
    public static ObservableList<Bill> getDatausers_bill() {
        Connection conn = ConnectDb();
        ObservableList<Bill> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from product INNER JOIN ware_house ON product.pro_id=ware_house.pro_id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   

                list.add(new Bill(rs.getRow(),Integer.parseInt(rs.getString("pro_id")),
                		Integer.parseInt(rs.getString("amount_stock")),
                		Integer.parseInt(rs.getString("amount_input")),
                		rs.getString("pro_name"),
                		rs.getString("pro_brand"),
                		rs.getString("pro_sale_price"),
                		rs.getString("barcode"),
                		rs.getString("pro_category")));       


            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
	}
    
    
    //---------------------Inventory_employee-------------------------------
    public static ObservableList<Inventory> getDataInventory() {
        Connection conn = ConnectDb();
        ObservableList<Inventory> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.wh_id,input_detail.pro_id, product.pro_name,input_detail.amount,input_detail.input_price,input_detail.input_detail_id,ware_house.date_input FROM ware_house ,input_detail, product WHERE ware_house.input_detail_id=input_detail.input_detail_id AND input_detail.pro_id=product.pro_id AND input_detail.amount != 0");
            ResultSet rs = ps.executeQuery();				
            
            while (rs.next()){   
                list.add(new Inventory(
                		Integer.parseInt(rs.getString("wh_id")),
                		Integer.parseInt(rs.getString("input_detail_id")),
                		Integer.parseInt(rs.getString("amount")),
                		Integer.parseInt(rs.getString("input_price")),
                		rs.getRow(),
                		rs.getString("pro_name"), 
                		rs.getString("date_input")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        //SELECT ware_house.*,product.* FROM ware_house,product WHERE ware_house.pro_name=product.pro_name
        //SELECT ware_house.pro_name, product.pro_name FROM ware_house INNER JOIN product ON ware_house.pro_name = product.pro_name
        //SELECT * FROM ware_house INNER JOIN product ON ware_house.pro_id= product.pro_id
	} 
    
    
  //---------------------Change_shift-------------------------------
    public static ObservableList<ChangeShift> getDataChangeShift() {
        Connection conn = ConnectDb();
        ObservableList<ChangeShift> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT change_shift.* FROM change_shift");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new ChangeShift(
                		Integer.parseInt(rs.getString("shift_id")),
                		rs.getRow(),
                		rs.getString("main_shift"), 
                		rs.getString("switch_shift"),
                		rs.getString("date_switch"), 
                		rs.getString("shift_change_date"),
                		rs.getString("emp_name"), 
                		rs.getString("emp_email")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;

        
	} 
    
//    public static ObservableList<Bill> getDatausers_bill() {
//        Connection conn = ConnectDb();
//        ObservableList<Bill> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select product.pro_name,product.pro_brand, brand.brand_name ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()){   
//                list.add(new Bill(rs.getString("pro_name"),rs.getString("brand_name"),rs.getString("pro_sale_price"),rs.getString("barcode")));       
//            }
//        } catch (Exception e) {
//        	System.out.println(e);
//        }
//        return list;
//	}
//---------------------account-------------------------------
    public static ObservableList<Account1> getDataAccount1() {
        Connection conn = ConnectDb();
        ObservableList<Account1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT employee.*,title.* FROM employee,title WHERE employee.title_id=title.title_id ORDER BY employee.emp_id DESC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Account1(
                		Integer.parseInt(rs.getString("emp_id")),
                		Integer.parseInt(rs.getString("emp_status")),
                		rs.getRow(),
                		rs.getString("emp_name"), 
                		rs.getString("emp_email"),
                		rs.getString("emp_phone"), 
                		rs.getString("emp_address"),  
                		rs.getString("emp_gender"),
                		rs.getString("title_name"),
                		rs.getString("emp_user"), 
                		rs.getString("emp_pass")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;

        
	} 
  //---------------------Category-brand-------------------------------
    public static ObservableList<Category1> getDataCategory1() {
        Connection conn = ConnectDb();
        ObservableList<Category1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT category.* FROM category");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Category1(
                		Integer.parseInt(rs.getString("cat_id")), 
                		rs.getRow(),
                		rs.getString("cat_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}    
    public static ObservableList<Brand1> getDataBrand1() {
        Connection conn = ConnectDb();
        ObservableList<Brand1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT brand.* FROM brand");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Brand1( 
                		Integer.parseInt(rs.getString("brand_id")),
                		rs.getRow(),
                		rs.getString("brand_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}
    
    public static ObservableList<Title> getDataTitle() {
        Connection conn = ConnectDb();
        ObservableList<Title> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM title");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Title(
                		Integer.parseInt(rs.getString("title_id")),
                		rs.getRow(), 
                		rs.getString("title_name"), 
                		rs.getString("description")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}
    
    public static ObservableList<Product> getDataProduct(){
        Connection conn = ConnectDb();
        ObservableList<Product> list = FXCollections.observableArrayList();
        try {
//            PreparedStatement ps = conn.prepareStatement("select product.pro_id, product.pro_name,product.pro_expiry,product.pro_unit,product.pro_category,product.pro_brand ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
        	PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Product(Integer.parseInt(rs.getString("pro_id")),rs.getRow(),rs.getString("pro_sale_price"),rs.getString("barcode"),rs.getString("pro_name"),rs.getString("pro_expiry")));       
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
    }


    public static ObservableList<Order_Detail> getDataOrder_detail(){
        Connection conn = ConnectDb();
        ObservableList<Order_Detail> list = FXCollections.observableArrayList();
        try {
//            PreparedStatement ps = conn.prepareStatement("select product.pro_id, product.pro_name,product.pro_expiry,product.pro_unit,product.pro_category,product.pro_brand ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
        	PreparedStatement ps = conn.prepareStatement("SELECT *, SUM(quantity) AS amount , sum(total) as total1 FROM orders_detail GROUP BY order_id, name");
            ResultSet rs = ps.executeQuery();
//            
            while (rs.next()){   
                list.add(new Order_Detail(rs.getRow(),
                		Integer.parseInt(rs.getString("order_id")),
                		Integer.parseInt(rs.getString("order_detail_id")),
                		Integer.parseInt(rs.getString("amount")),
                		Integer.parseInt(rs.getString("price")),
                		rs.getString("name"),
                		Integer.parseInt(rs.getString("total1"))));       
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
    }
 
  //----------------------RUN OUT--------------------
    public static ObservableList<Dashboard> getDataRunOut() {
        Connection conn = ConnectDb();
        ObservableList<Dashboard> list = FXCollections.observableArrayList();
        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.*,product.pro_name,amount_stock+amount_input AS \"total_amount\" FROM ware_house,product WHERE amount_stock+amount_input<100 AND ware_house.pro_id=product.pro_id"
//            		+ "");
//            ResultSet rs = ps.executeQuery();
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product WHERE input_detail.pro_id=product.pro_id GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   
                list.add(new Dashboard(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Quater 1--------------------
    public static ObservableList<Quarter_1> getDataQuater1() {
        Connection conn = ConnectDb();
        ObservableList<Quarter_1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND QUARTER(input.time)=1 GROUP BY input_detail.pro_id ORDER BY total_amount ASC ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Quarter_1(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Quater 2--------------------
    public static ObservableList<Quarter_2> getDataQuater2() {
        Connection conn = ConnectDb();
        ObservableList<Quarter_2> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND QUARTER(input.time)=2 GROUP BY input_detail.pro_id ORDER BY total_amount ASC ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Quarter_2(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    
  //----------------------Quater 3--------------------
    public static ObservableList<Quarter_3> getDataQuater3() {
        Connection conn = ConnectDb();
        ObservableList<Quarter_3> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND QUARTER(input.time)=3 GROUP BY input_detail.pro_id ORDER BY total_amount ASC ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Quarter_3(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    
  //----------------------Quater 4--------------------
    public static ObservableList<Quarter_4> getDataQuater4() {
        Connection conn = ConnectDb();
        ObservableList<Quarter_4> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND QUARTER(input.time)=4 GROUP BY input_detail.pro_id ORDER BY total_amount ASC ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Quarter_4(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    //----------------------Month 1--------------------
    public static ObservableList<M1> getDataM1() {
        Connection conn = ConnectDb();
        ObservableList<M1> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=1 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M1(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 2--------------------
    public static ObservableList<M2> getDataM2() {
        Connection conn = ConnectDb();
        ObservableList<M2> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=2 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M2(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 3--------------------
    public static ObservableList<M3> getDataM3() {
        Connection conn = ConnectDb();
        ObservableList<M3> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=3 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M3(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 4--------------------
    public static ObservableList<M4> getDataM4() {
        Connection conn = ConnectDb();
        ObservableList<M4> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=4 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M4(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 5--------------------
    public static ObservableList<M5> getDataM5() {
        Connection conn = ConnectDb();
        ObservableList<M5> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=5 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M5(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 6--------------------
    public static ObservableList<M6> getDataM6() {
        Connection conn = ConnectDb();
        ObservableList<M6> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=6 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M6(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 7--------------------
    public static ObservableList<M7> getDataM7() {
        Connection conn = ConnectDb();
        ObservableList<M7> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=7 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M7(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    
  //----------------------Month 8--------------------
    public static ObservableList<M8> getDataM8() {
        Connection conn = ConnectDb();
        ObservableList<M8> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=8 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M8(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 9--------------------
    public static ObservableList<M9> getDataM9() {
        Connection conn = ConnectDb();
        ObservableList<M9> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=9 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M9(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 10--------------------
    public static ObservableList<M10> getDataM10() {
        Connection conn = ConnectDb();
        ObservableList<M10> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=10 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M10(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
  //----------------------Month 11--------------------
    public static ObservableList<M11> getDataM11() {
        Connection conn = ConnectDb();
        ObservableList<M11> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=11 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M11(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    //----------------------Month 12--------------------
    public static ObservableList<M12> getDataM12() {
        Connection conn = ConnectDb();
        ObservableList<M12> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(input_detail.amount) AS 'total_amount',input_detail.pro_id,product.pro_name FROM input_detail,product,input WHERE input_detail.pro_id=product.pro_id AND input.input_id=input_detail.input_id AND Month(input.time)=12 GROUP BY input_detail.pro_id ORDER BY total_amount ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new M12(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	} 
    
    public static String h1;
    public void ngay(String ngay_gui) {
    	h1=ngay_gui;
    }
  //----------------------Month Search_date--------------------
    public static ObservableList<Search_date> getDataSearch_date() {
        Connection conn = ConnectDb();
        ObservableList<Search_date> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.*,product.pro_name,amount_stock+amount_input AS \"total_amount\" FROM ware_house,product WHERE amount_stock+amount_input<100 AND ware_house.pro_id=product.pro_id AND DATE(ware_house.date_input)='2021-08-31'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Search_date(
                		Integer.parseInt(rs.getString("pro_id")), 
                		Integer.parseInt(rs.getString("total_amount")), 
                		rs.getRow(),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;       
	}
    
    public static ObservableList<Bill_Mange> getBill_manage() {
        Connection conn = ConnectDb();
        ObservableList<Bill_Mange> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT orders.order_id,orders.total_price,orders.point,orders.discount,orders_detail.name,orders_detail.quantity,orders_detail.price,orders_detail.total,orders.time FROM orders INNER JOIN orders_detail ON orders.order_id = orders_detail.order_id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Bill_Mange(
                		rs.getRow(),
                		rs.getRow(),
                		Integer.parseInt(rs.getString("order_id")),
                		Integer.parseInt(rs.getString("total_price")),
                		Integer.parseInt(rs.getString("point")),
                		Integer.parseInt(rs.getString("discount")),              		
                		Integer.parseInt(rs.getString("quantity")),
                		Integer.parseInt(rs.getString("price")),
                		Integer.parseInt(rs.getString("total")),
                		rs.getString("name"),
                		rs.getString("time")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}


    
  //---------------------account-------------------------------
    public static ObservableList<Run_Out> getDataRunOut1() {
        Connection conn = ConnectDb();
        ObservableList<Run_Out> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("Select tbl_expiry.*,product.pro_name,supplier.sup_name FROM tbl_expiry,product ,supplier WHERE tbl_expiry.pro_id=product.pro_id AND tbl_expiry.sup_id=supplier.sup_id AND tbl_expiry.amount>0 ORDER BY tbl_expiry.expiry DESC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Run_Out(
                		rs.getRow(), 
                		Integer.parseInt(rs.getString("expiry_id")), 
                		rs.getString("sup_name"), 
                		rs.getString("expiry"), 
                		rs.getString("amount"),  
                		rs.getString("total"),  
                		rs.getString("input_price"),
                		rs.getString("pro_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;

    }
//    --------------------------------ware_house----------------------
    public static ObservableList<Ware_house> getDataWarehouse() {
        Connection conn = ConnectDb();
        ObservableList<Ware_house> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ware_house.wh_id,input_detail.input_detail_id,product.pro_id,product.pro_name,supplier.sup_name,input_detail.amount,input_detail.input_price,ware_house.date_input,input_detail.expiry FROM input_detail,ware_house,product,supplier WHERE input_detail.input_detail_id=ware_house.input_detail_id AND input_detail.pro_id=product.pro_id AND supplier.sup_id=input_detail.sup_id  AND input_detail.amount!=0 ORDER BY input_detail.expiry ASC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Ware_house(
                		rs.getInt("input_detail_id"),
                		rs.getRow(), 
                		rs.getInt("pro_id"), 
                		rs.getString("wh_id"), 
                		rs.getInt("amount"), 
                		rs.getInt("input_price"), 
                		rs.getString("pro_name"), 
                		rs.getString("sup_name"), 
                		rs.getString("date_input"), 
                		rs.getString("expiry")
                	));    
                
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;

    }

    //---------------------Supplier-Unit-------------------------------
    public static ObservableList<Supplier> getDataSupplier() {
        Connection conn = ConnectDb();
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM supplier");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Supplier(
                		rs.getRow(), 
                		rs.getInt("sup_id"), 
                		rs.getString("sup_name"), 
                		rs.getString("sup_address"),
                		rs.getString("sup_phone")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}    
    public static ObservableList<Unit> getDataUint() {
        Connection conn = ConnectDb();
        ObservableList<Unit> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM unit");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Unit(
                		rs.getRow(), 
                		rs.getInt("unit_id"),
                		rs.getString("unit_name")
                	));    
                
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
        
	}
}
