package gcp.billing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.amazonaws.util.FileUtils;

public class GcpBillingInformationUtils {
	private static final String TARGET_URL ="https://cloudbilling.googleapis.com/v1/services/6F81-5844-456A/skus?";
	private static final String API_KEY = "key=<insert your key>";
	
	/**
	 * Fetches Json from the Google Billing Service and persists in the File system
	 * @param zone
	 * @param serviceId
	 * @throws IOException 
	 */
	public void persistComputeEngine(String region, String serviceId) {
		URLConnection urlConnection = null;
		HttpURLConnection httpConnection = null;
		BufferedReader br = null;
		try {
		URL serverUrl = new URL(TARGET_URL + API_KEY);
		urlConnection = serverUrl.openConnection();
		httpConnection = (HttpURLConnection)urlConnection;
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Content-Type", "application/json");
		httpConnection.setDoOutput(true);
		if (httpConnection.getInputStream() == null) {
			   System.out.println("No stream");
			   return;
			}
		StringBuilder sb=new StringBuilder();
		br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		String read;
		while((read=br.readLine()) != null) {
		    sb.append(read);   
		}
		String data = sb.toString();
		System.out.println(data);
		FileUtils.appendDataToTempFile(new File("D:\\Study\\Sem 3\\Research Project\\Demo\\CatalogJSON\\gcp.json"), data);
		}catch(IOException e ) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		GcpBillingInformationUtils gcpBillingInformationUtils = new GcpBillingInformationUtils();
		gcpBillingInformationUtils.persistComputeEngine("london","27BB-501A-FC87");
		
	}

	private static void extracted() throws MalformedURLException, IOException, ProtocolException {
		URL serverUrl = new URL(TARGET_URL + API_KEY);
		URLConnection urlConnection = serverUrl.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Content-Type", "application/json");
		httpConnection.setDoOutput(true);
//		String response = httpConnection.getResponseMessage();
		if (httpConnection.getInputStream() == null) {
			   System.out.println("No stream");
			   return;
			}

			Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
			String resp = "";
			while (httpResponseScanner.hasNext()) {
			   String line = httpResponseScanner.nextLine();
			   resp += line;
			}
			httpResponseScanner.close();
			FileUtils.createTempFileForTesting("D:\\Study\\Sem 3\\Research Project\\Demo\\CatalogJSON\\gcp.json", resp);
	}

}
