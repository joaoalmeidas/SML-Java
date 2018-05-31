import java.util.Scanner;

public class SML {

	public static void main(String[] args) {
		
		final int READ = 10;
		final int WRITE = 11;
		final int LOAD = 20;
		final int STORE = 22;
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
		int posicao = 0;
		
		System.out.println("SIMPLETRON MACHINE LANGUAGE\n\nDigite o seu código abaixo:\nC + ENTER = EXECUTAR");

		//inputa os valores para a memoria
		while(!instrucao.equals("c") || !instrucao.equals("C")) {
			
			instrucao = input.nextLine();
			if(!instrucao.equals("c") || !instrucao.equals("C")){
				memoria[posicao] = Integer.parseInt(instrucao);
				posicao++;
			}
		}
		
		//percorre a memoria executando os comandos
		for(int i = 0; i < memoria.length || memoria[i] == 4300; i++) {
			
			int operacao, indice;
			
			//separa a operacao e a posicao contidos na posicao da memoria
			operacao = memoria[i]/100;
			indice = memoria[i]%100;
			
			if(operacao == READ) {
				
			}else if(operacao == WRITE){
				
			}else if(operacao == LOAD){
				
			}else if(operacao == STORE){
				
			}else if(operacao == ADD) {
				
			}else if(operacao == SUBTRACT) {
				
			}else if(operacao == DIVIDE) {
				
			}else if(operacao == MULTIPLY) {
				
			}else if(operacao == BRANCH) {
				
			}else if(operacao == BRANCHNEG) {
				
			}else if(operacao == BRANCHZERO) {
				
			}else if(operacao == HALT) {
				
			}
		}
		
	}

}