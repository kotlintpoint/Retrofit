# Retrofit 
A type-safe HTTP client for Android and Java

Retrofit is a REST Client for Android and Java by Square. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. Retrofit uses the OkHttp library for HTTP requests. Retrofit can be configured with converter is used for its data serialization. Typically for JSON you use GSon, but you can add custom converters to process XML or other protocols.

First Of All Create Php Service with following Code:
```
<?php  
mysql_connect("localhost","root",""); 
mysql_select_db("mydatabase"); 


if(isset($_REQUEST["insert"])) 
{ 
	$username = $_REQUEST['username']; 
	$password = $_REQUEST['password'];
   
	$ins = "insert into mytable(username,password)values('$username','$password')"; 
	echo mysql_query($ins) or die(mysql_query()); 
} 


if(isset($_REQUEST["select"])) 
{ 

	$sel = "select * from mytable"; 
  
	$res = mysql_query($sel); 
	while($fet = mysql_fetch_assoc($res)) 
	{ 
		$users["data"][] = $fet;  
	} 
	echo json_encode($users); 
} 

?> 
```
Android Code:

##Step 1: Add Dependencies 
```
compile 'com.google.code.gson:gson:2.6.2'
compile 'com.squareup.retrofit2:retrofit:2.0.2'
compile 'com.squareup.retrofit2:converter-gson:2.0.2'
compile 'com.android.support:recyclerview-v7:23.4.0'
```
##Step 2: Internet Permission
```
<uses-permission android:name="android.permission.INTERNET"/>
 ```
 
##Step 3: Create Model Class
```
public class User {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public User(String username,String password)
    {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```
##Also Create UserResponse Class
```
public class UserResponse {

    @SerializedName("data")
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
```
##Step 4: Creating the Retrofit instance
```
public class ApiClient {
    public static final String BASE_URL = "http://192.168.43.29:8080/myproject/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
```
##Step 5: Define the EndPoint
```
public interface ApiInterface {

    @GET("myservice.php")
    Call<UserResponse> getAllUser(@Query("select") String select);

    @GET("myservice.php?insert")
    Call<String> insertUser(@Query("username") String username, @Query("password") String password);
}
```
Each endpoint specifies an annotation of the HTTP method (GET, POST, etc.) and the parameters of this method can also have special annotations (@Query, @Path, @Body etc.)

Take a look to other annotations:

@Path – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.

@Query – specifies the query key name with the value of the annotated parameter.

@Body – payload for the POST call

@Header – specifies the header with the value of the annotated parameter

##Step 6: Fetch All Users
```
private void fetchUsers() {
        Call<UserResponse> call = apiService.getAllUser(SELECT);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<User> users = response.body().getData();
                Log.d(TAG, "Number of Users received: " + users.size());
                recyclerView.setAdapter(new MyDataAdapter(users, R.layout.list_item_user, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<UserResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
}
```
##Step 7: To insert a new User
```
private void InsertDummyUser(String username, String password) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        
        Call<String> call=apiService.insertUser(username,password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp=response.body().toString();
                Toast.makeText(MainActivity.this, resp, Toast.LENGTH_SHORT).show();
                fetchUsers();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
```


