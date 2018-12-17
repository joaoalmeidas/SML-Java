import java.text.DecimalFormat;
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
		
		int instrucao = 0;
		Scanner input = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("00");
		DecimalFormat dfMemoria = new DecimalFormat("+0000");
		
		int[] memoria = new int[100];
		int posicao = 0, acumulador = 0, operacao = 0, indice = 0;
		boolean halt = false;
		
		System.out.println("SIMPLETRON MACHINE LANGUAGE\n"
				+ "Bem-vindo ao Simpleton Machine Language!\n"
				+ "Por favor, insira uma instrução por vez\n"
				+ "\nDigite o seu código abaixo:\n-99999 + ENTER = EXECUTAR\n");

		//inputa os valores para a memoria
		
		int posicaoMemoria = 0;
		
		while(instrucao != -99999) {
			
			System.out.printf(df.format(posicaoMemoria) + " ? ");
			
			//insere linha de codigo
			instrucao = input.nextInt();
			
			operacao = instrucao/100;
			indice = instrucao%100;
			
			//valida a operacao e o indice
			if(indice >= 0 && indice < 100 && operacao == READ || operacao == WRITE || operacao == LOAD || operacao == STORE || operacao == ADD || operacao == SUBTRACT || 
			operacao == DIVIDE || operacao == MULTIPLY || operacao == BRANCH || operacao == BRANCHNEG || operacao == BRANCHZERO || 
			operacao == HALT) {
				
				memoria[posicao] = instrucao;
				posicao++;
				posicaoMemoria++;
				
			}else if(instrucao != -99999){
				System.out.println("Indice ou operação inválida");
			}
		}
		
		System.out.println("Carregamento do programa completa!");
		System.out.println("Iniciando a execução do programa...");
		
		
		//percorre a memoria executando os comandos
		for(int i = 0; i < 100 && halt == false; i++) {
			
			//separa a operacao e a posicao contidos na posicao da memoria
			
			int registroInstrucao = memoria[i];
			
			operacao = registroInstrucao/100;
			indice = registroInstrucao%100;
			
			if(operacao == READ) {
				System.out.printf("Insira um valor:");
				memoria[indice] = input.nextInt();
			}else if(operacao == WRITE){
				System.out.println(memoria[indice]);
			}else if(operacao == LOAD){
				if(acumulador < -9999 || acumulador > +9999){
					System.out.println("Estouro de memória!\nExecução do programa finalizada.");
					halt=true;
					System.out.println("Execução do programa finalizada!!");
					System.out.println("REGISTRADORES");
					System.out.printf("Acumulador:\t%d\n", acumulador);
					System.out.printf("Contador de instruções:\t%d\n", posicaoMemoria);
					System.out.printf("Instrução atual:\t%d\n", registroInstrucao);
					System.out.printf("Operação:\t%d\n", operacao);
					System.out.printf("Indice:\t%d\n", indice);
					
					System.out.println("MEMÓRIA");
					
					System.out.printf("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
					
					for(int j = 0; j < memoria.length; j++){
						if(j%10 == 0) {
							System.out.println();
							System.out.printf("%dx\t",j/10);
						}
						System.out.printf(dfMemoria.format(memoria[j]) +"\t");
					}
				}else {
					acumulador = memoria[indice];
				}
			}else if(operacao == STORE){
				memoria[indice] = acumulador;
			}else if(operacao == ADD) {
				acumulador += memoria[indice];
			}else if(operacao == SUBTRACT) {
				acumulador -= memoria[indice];
			}else if(operacao == DIVIDE) {
				if(acumulador == 0 || memoria[indice] == 0){
					System.out.println("Tentativa de dividir por zero!\nExecução do programa finalizada.");
					halt = true;
					System.out.println("Execução do programa finalizada!!");
					System.out.println("REGISTRADORES");
					System.out.printf("Acumulador:\t%d\n", acumulador);
					System.out.printf("Contador de instruções:\t%d\n", posicaoMemoria);
					System.out.printf("Instrução atual:\t%d\n", registroInstrucao);
					System.out.printf("Operação:\t%d\n", operacao);
					System.out.printf("Indice:\t%d\n", indice);
					
					System.out.println("MEMÓRIA");
					
					System.out.printf("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
					
					for(int j = 0; j < memoria.length; j++){
						if(j%10 == 0) {
							System.out.println();
							System.out.printf("%dx\t",j/10);
						}
						System.out.printf(dfMemoria.format(memoria[j]) +"\t");
					}
				}else {
					acumulador /= memoria[indice];
				}
			}else if(operacao == MULTIPLY) {
				acumulador *= memoria[indice];
			}else if(operacao == BRANCH) {
				i = indice - 1;
			}else if(operacao == BRANCHNEG) {
				if(acumulador < 0) {
					i = indice - 1;
				}
			}else if(operacao == BRANCHZERO) {
				if(acumulador == 0) {
					i = indice - 1;
				}
			}else if(operacao == HALT) {
				halt = true;
				System.out.println("Execução do programa finalizada!!");
				System.out.println("REGISTRADORES");
				System.out.printf("Acumulador:\t%d\n", acumulador);
				System.out.printf("Contador de instruções:\t%d\n", posicaoMemoria);
				System.out.printf("Instrução atual:\t%d\n", registroInstrucao);
				System.out.printf("Operação:\t%d\n", operacao);
				System.out.printf("Indice:\t%d\n", indice);
				
				System.out.println("MEMÓRIA");
				
				System.out.printf("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
				
				for(int j = 0; j < memoria.length; j++){
					if(j%10 == 0) {
						System.out.println();
						System.out.printf("%dx\t",j/10);
					}
					System.out.printf(dfMemoria.format(memoria[j]) +"\t");
				}
				
			}	
		}
		
		
	}
	
}