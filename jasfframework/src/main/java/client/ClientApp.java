package client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dao.backend.Employee;

public class ClientApp {
	
	public String url()
	{
		return "http://localhost:8080/jasfframework/rest/";
	}
	
public Employee get(String id)
	{	ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource service = client.resource(url());
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("id",id);
		ClientResponse response = service.path("json/test").path("get").queryParams(queryParams)
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		Employee e=null;
		//String e = service.path("emp").path("get?id=6").accept(MediaType.APPLICATION_JSON).get(String.class);
		if (response.getStatus() != 201) {
			System.out.println("iogogo");
			}
		else{
			 e = response.getEntity(Employee.class);			 
			System.out.println("Output from Server .... \n");
			System.out.println(e);
		}
		
		return e;	
	}
	
public Employee put(Employee e)
	{
	ClientConfig clientConfig = new DefaultClientConfig();
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
	Client client = Client.create(clientConfig);
	WebResource service = client.resource(url());
	
		ClientResponse response = service.path("json/test").path("put").type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class, e);
		e=null;
		if (response.getStatus() != 201) {
		}
		else{
		e = response.getEntity(Employee.class);
		}
		return e;
	}
	public Employee post(Employee e)
	{	
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource service = client.resource(url());
		//Employee e = new Employee(11,"Rajesh","Engx",77563);
		ClientResponse response = service.path("json/test").path("post").type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, e);
		e=null;
		if (response.getStatus() != 201) {
		}
		else{
			System.out.println("Output from Server .... \n");
			e = response.getEntity(Employee.class);
			System.out.println(e);
		}
		return e;
	}
	public Employee delete(String id)
	{
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource service = client.resource(url());
		//String s = webResource.get(String.class);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("id",id);
		ClientResponse response = service.path("json/test").path("delete").queryParams(queryParams).accept(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);
		//String e = service.path("emp").path("get?id=6").accept(MediaType.APPLICATION_JSON).delete(String.class);
		Employee e=null;
		if (response.getStatus() != 201) {
			}

			else{
				System.out.println("Output from Server .... \n");
				e = response.getEntity(Employee.class);
				System.out.println(e);
			}
		return e;
	}
	
}
