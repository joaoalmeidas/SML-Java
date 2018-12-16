import java.util.Scanner;

public class SML {

	public static void main(String[] args) {
		
		final int READ = 10;
		final int WRITE = 11;
		final int LOAD = 20;
		final int STORE = 21;
		final int ADD = 30;
		final int SUBTRACT = 31;
		final int DIVIDE = 32;
		final int MULTIPLY = 33;
		final int BRANCH = 40;
		final int BRANCHNEG = 41;
		final int BRANCHZERO = 42;
		final int HALT = 43;
		
		String instrucao = new String();
		Scanner input = new Scanner(System.in);
		
		int[] memoria = new int[100];
		int posicao = 0, acumulador = 0, operacao = 0, indice = 0;
		boolean halt = false;
		
		System.out.println("SIMPLETRON MACHINE LANGUAGE\n\nDigite o seu código abaixo:\nc + ENTER = EXECUTAR");

		//inputa os valores para a memoria
		while(!instrucao.equals("c") && !instrucao.equals("C")) {
			
			instrucao = input.nextLine();
			if(!instrucao.equals("c") && !instrucao.equals("C")) {
				operacao = Integer.parseInt(instrucao)/100;
				indice = Integer.parseInt(instrucao)/100;
			}
			
			if(!instrucao.equals("c") && !instrucao.equals("C")){
				if(indice >=0 && indice < 100 && instrucao.length() == 4 && operacao == READ || operacao == WRITE || operacao == LOAD || operacao == STORE || operacao == ADD || operacao == SUBTRACT || 
						operacao == DIVIDE || operacao == MULTIPLY || operacao == BRANCH || operacao == BRANCHNEG || operacao == BRANCHZERO || operacao == HALT) {
					
						memoria[posicao] = Integer.parseInt(instrucao);
						posicao++;
				}else{
					System.out.println("Comando incorreto, insira a linha de código novamente.");	
				}
			}
		}
		
		//percorre a memoria executando os comandos
		for(int i = 0; i < 100 && halt == false; i++) {
			
			//separa a operacao e a posicao contidos na posicao da memoria
			operacao = memoria[i]/100;
			indice = memoria[i]%100;
			
			if(operacao == READ) {
				System.out.printf("Insira um valor:");
				memoria[indice] = input.nextInt();
			}else if(operacao == WRITE){
				System.out.println(memoria[indice]);
			}else if(operacao == LOAD){
				acumulador = memoria[indice];
				System.out.println(acumulador);
			}else if(operacao == STORE){
				memoria[indice] = acumulador;
			}else if(operacao == ADD) {
				acumulador += memoria[indice];
			}else if(operacao == SUBTRACT) {
				acumulador -= memoria[indice];
			}else if(operacao == DIVIDE) {
				acumulador /= memoria[indice];
			}else if(operacao == MULTIPLY) {
				acumulador *= memoria[indice];
			}else if(operacao == BRANCH) {
				i = indice;
			}else if(operacao == BRANCHNEG) {
				if(acumulador < 0) {
					i = indice;
				}
			}else if(operacao == BRANCHZERO) {
				if(acumulador == 0) {
					i = indice;
				}
			}else if(operacao == HALT) {
				halt = true;	
			}	
		}	
	}
}