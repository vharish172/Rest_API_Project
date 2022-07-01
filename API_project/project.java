package API_project;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class project {
	

	//DataProvider 
	@DataProvider(name="provider")
	public Object[][] data(){
		Object[][] UserData=new Object[2][8];
		UserData[0][0]="1";
		UserData[0][1]="vharish172";
		UserData[0][2]="Harish";
		UserData[0][3]="V";
		UserData[0][4]="test@test.com";
		UserData[0][5]="123456789";
		UserData[0][6]="9488630936";
		UserData[0][7]="1";
		UserData[1][0]="2";
		UserData[1][1]="rangu123";
		UserData[1][2]="Rangan";
		UserData[1][3]="Kabilan";
		UserData[1][4]="rangi@gmail.com";
		UserData[1][5]="123456789";
		UserData[1][6]="971234567";
		UserData[1][7]="2";
	
		return UserData;
	}
	
	//Create the user using the data provider
	@Test(enabled=true,priority=1,dataProvider="provider")
	public void PostUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		JSONObject obj =new JSONObject();
		
		obj.put("id",id);
		obj.put("username",un);
		obj.put("firstName",fn);
		obj.put("lastName",ln);
		obj.put("email",email);
		obj.put("password",pass);
		obj.put("phone",ph);
		obj.put("userstatus",us);
		
		
		given()
		.contentType(ContentType.JSON)	       
		.body(obj.toJSONString()).
		
		 when()
		 .post("/user").
		 
		 then()
		 .statusCode(200)
		 .log().all();
		
	}
	
	//Get user data 
	@Test(enabled=true,priority=2)
	public void GetUser(){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		given()
		.get("/user/vharish172").
		
		then()
		.statusCode(200).log().all();
		
		
		given()
		.get("/user/rangu123").
		
		then()
		.statusCode(200).log().all();
	}
	
	//Login into the account using the dataProvider
	@Test(enabled=true,priority=3,dataProvider="provider")
	public void LoginUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
       RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
	
	given()
	.queryParam("username", un)
	.queryParam("password", pass).
	
	when()
	  .get("/user/login").
	  
	then()
	   .statusCode(200).log().all();
		
		
	}
	
	//Update the user using the put method
	@Test(enabled=true,priority=4)
	public void PutUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 
		 JSONObject obj=new JSONObject();
			
		 obj.put("id","1");
			obj.put("username","naren123");
			obj.put("firstName","Naren");
			obj.put("lastName","Santhanabarathi");
			obj.put("email","ns@gmail.com");
			obj.put("password","12345678");
			obj.put("phone","9910512345");
			obj.put("userstatus","1");
			
			given()
				.contentType(ContentType.JSON)
			   .body(obj.toJSONString())
			   
			.when()
			   .put("/user/naren123")
			   .then()
			   .statusCode(200).log().all();
	}
	
	//delete the user
	@Test(enabled=true,priority=5)
	public void DeleteUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 given()
		 	.delete("/user/naren123")
		 
		 .then()
		 .statusCode(200).log().all();
		 
		 given()
		 .delete("/user/rangu123")
		 .then().statusCode(200).log().all();
	}

}
