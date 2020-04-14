package br.com.bexs.travel.route;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BexsApplicationConsole {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		File arquivoCSV;
		
	    while(true){
			Scanner s = new Scanner(System.in);
		    if (args.length == 0) {
				System.out.println("Plese, enter the file name!");
				break;
			}
		    try {

				Path currentDir = Paths.get("");

				//arquivoCSV = new File("/home/paulodiniz/IdeaProjects/bexs/src/main/java/br/com/bexs/travel/route/" + args[0]);

				arquivoCSV = new File(currentDir.toAbsolutePath() + "/input-routes.csv");

		    	Scanner leitor = new Scanner(arquivoCSV);
		    	String linhasDoArquivo = new String();

                while (leitor.hasNext()) {

                    linhasDoArquivo = leitor.nextLine();
                    String[] valores = linhasDoArquivo.split(",");

                    if(valores.length == 3){
						String origin = valores[0];
						String destiny = valores[1];
						String cost = valores[2];
						sendByURL(origin, destiny, cost);
					}

                    //RouteDTO route = new RouteDTO(origin, destiny, Integer.valueOf(cost));
                    
                }
		    }
		    catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


			System.out.println("please enter the route ini: ");

		    String routeIn = s.next();

			System.out.println("please enter the route fim: ");

			String routeFim = s.next();

			String bestRoute = bestRoute(routeIn,routeFim);

		    System.out.println("best route: " + bestRoute);
		    
		    continue;
	    }

	}

	private static void sendByURL(String origin, String destiny, String cost)
			throws IOException, ProtocolException {
		
		URL url = new URL("http://localhost:8080/routes/route/"+origin+"/"+destiny+"/"+cost+"/");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout( 20000 );
		conn.getHeaderField("Content-Type");
		
		conn.setRequestMethod("GET");
		
		//conn.connect();
	}

	private static String bestRoute(String origin, String destiny)
			throws IOException, ProtocolException {

		StringBuilder result = new StringBuilder();

		URL url = new URL("http://localhost:8080/routes/route/getBestCost"+origin+"/"+destiny + "");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout( 20000 );
		conn.getHeaderField("Content-Type");

		conn.setRequestMethod("GET");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;

		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();

		return result.toString();
	}

}
