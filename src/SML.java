import java.util.Scanner;

public class SML {

	public static void main(String[] args) {
		
		String instrucao = new String();
		Scanner input = new Scanner(System.in);
		
		int[] memoria = new int[100];
		
		int posicao = 0;
		
		System.out.println("SIMPLETRON MACHINE LANGUAGE\n\nDigite o seu código abaixo:\nC + ENTER = EXECUTAR");

		while(!instrucao.equals("c")) {
			
			instrucao = input.nextLine();
			
			if(!instrucao.equals("c")){
				memoria[posicao] = Integer.parseInt(instrucao);
				posicao++;
			}
		}
	}
	
	

}