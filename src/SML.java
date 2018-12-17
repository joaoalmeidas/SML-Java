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
		final int POW = 34;
		final int BRANCH = 40;
		final int BRANCHNEG = 41;
		final int BRANCHZERO = 42;
		final int HALT = 43;
		final int CONTINUE = 44;
		
		int instrucao = 0;
		Scanner input = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("00000");
		DecimalFormat dfMemoria = new DecimalFormat("+00000");
		
		int[] memoria = new int[1000];
		int posicao = 0, acumulador = 0, operacao = 0, indice = 0;
		boolean halt = false;
		
		System.out.println("SIMPLETRON MACHINE LANGUAGE\n"
				+ "Bem-vindo ao Simpleton Machine Language!\n"
				+ "Por favor, insira uma instru��o por vez\n"
				+ "\nDigite o seu c�digo abaixo:\n-999999 + ENTER = EXECUTAR\n");

		//inputa os valores para a memoria
		
		int posicaoMemoria = 0;
		
		while(instrucao != -999999) {
			
			System.out.printf(df.format(posicaoMemoria) + " ? ");
			
			//insere linha de codigo
			instrucao = input.nextInt();
			
			operacao = instrucao/1000;
			indice = instrucao%1000;
			
			//valida a operacao e o indice
			if(indice >= 0 && indice < 1000 && operacao == READ || operacao == WRITE || operacao == LOAD || operacao == STORE || operacao == ADD || operacao == SUBTRACT || 
			operacao == DIVIDE || operacao == MULTIPLY || operacao == BRANCH || operacao == BRANCHNEG || operacao == BRANCHZERO || 
			operacao == HALT || operacao == CONTINUE || operacao == POW) {
				
				memoria[posicao] = instrucao;
				posicao++;
				posicaoMemoria++;
				
			}else if(instrucao != -999999){
				System.out.println("Indice ou opera��o inv�lida");
			}
		}
		
		System.out.println("Carregamento do programa completa!");
		System.out.println("Iniciando a execu��o do programa...");
		
		
		//percorre a memoria executando os comandos
		for(int i = 0; i < 1000 && halt == false; i++) {
			
			//separa a operacao e a posicao contidos na posicao da memoria
			
			int registroInstrucao = memoria[i];
			
			operacao = registroInstrucao/1000;
			indice = registroInstrucao%1000;
			
			if(operacao == READ) {
				System.out.printf("Insira um valor:");
				memoria[indice] = input.nextInt();
			}else if(operacao == WRITE){
				System.out.println(memoria[indice]);
			}else if(operacao == LOAD){
				if(acumulador < -99999 || acumulador > +99999){
					System.out.println("Estouro de mem�ria!\nExecu��o do programa finalizada.");
					halt=true;
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
					//exibe dump de memoria
					System.out.println("Tentativa de dividir por zero!\nExecu��o do programa finalizada.");
					halt = true;
				}else {
					acumulador /= memoria[indice];
				}
			}else if(operacao == MULTIPLY) {
				acumulador *= memoria[indice];
			}else if(operacao == POW) {
				acumulador = (int)Math.pow(acumulador, memoria[indice]);
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
			}else if(operacao == CONTINUE) {
				
				System.out.println("Fim da execu��o do programa SML!");
				
				exibeDump(acumulador, posicaoMemoria, registroInstrucao, operacao, indice, memoria, dfMemoria);
				acumulador = 0;
				
				i = indice - 1;
				
				System.out.println("\nInicio da execu��o do novo programa...");
				
			}
			
			//se halt, eximbe dump
			if(halt == true) {
				exibeDump(acumulador, posicaoMemoria, registroInstrucao, operacao, indice, memoria, dfMemoria);
			}
		}
		
		
	}
	
	public static void exibeDump(int acumulador, int posicaoMemoria, int registroInstrucao, int operacao, int indice, int[] memoria, DecimalFormat dfMemoria){
		System.out.println("Execu��o do programa finalizada!!");
		System.out.println("REGISTRADORES");
		System.out.printf("Acumulador:\t%d\n", acumulador);
		System.out.printf("Contador de instru��es:\t%d\n", posicaoMemoria);
		System.out.printf("Instru��o atual:\t%d\n", registroInstrucao);
		System.out.printf("Opera��o:\t%d\n", operacao);
		System.out.printf("Indice:\t%d\n", indice);
		
		System.out.println("MEM�RIA");
		
		System.out.printf("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\tA\tB\tC\tD\tE\tF");
		
		for(int j = 0; j < memoria.length; j++){
			if(j%16 == 0) {
				System.out.println();
				//System.out.printf("%dx\t",j/16);
				System.out.printf(Integer.toHexString(j/16)+"_\t");
			}
			System.out.printf(dfMemoria.format(memoria[j]) +"\t");
		}
	}
	
}