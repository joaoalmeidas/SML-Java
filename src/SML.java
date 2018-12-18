import java.text.DecimalFormat;
import java.util.Scanner;

public class SML {

	public static void main(String[] args) {
		/*
		final int READ = 10;
		final int WRITE = 11;
		final int ENTER = 12
		final int ENTERSTRING = 13;
		final int SHOWSTRING = 14;
		final int LOAD = 20;
		final int STORE = 21;
		final int ADD = 30;
		final int SUBTRACT = 31;
		final int DIVIDE = 32;
		final int MULTIPLY = 33;
		final int POW = 34;
		final int REST = 35;
		final int BRANCH = 40;
		final int BRANCHNEG = 41;
		final int BRANCHZERO = 42;
		final int HALT = 43;
		final int CONTINUE = 44;
		*/
		
		final String READ = "a";
		final String WRITE = "b";
		final String ENTER = "c";
		final String ENTERSTRING = "d";
		final String SHOWSTRING = "e";
		final String LOAD = "14";
		final String STORE = "15";
		final String ADD = "1e";
		final String SUBTRACT = "1f";
		final String DIVIDE = "20";
		final String MULTIPLY = "21";
		final String POW = "22";
		final String REST = "23";
		final String BRANCH = "28";
		final String BRANCHNEG = "29";
		final String BRANCHZERO = "2a";
		final String HALT = "2b";
		final String CONTINUE = "2c";
		
		int instrucao = 0;
		Scanner input = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("00000");
		DecimalFormat dfMemoria = new DecimalFormat("+00000");
		
		double acumulador = 0;
		double[] memoria = new double[1000];
		int posicao = 0, indice = 0;
		String operacao;
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
			input.nextLine();
			
			operacao = converteDecimalParaHexadecimal(instrucao/1000);
			indice = instrucao%1000;
			
			//valida a operacao e o indice
			if(indice >= 0 && indice < 1000 && operacao.equals(READ) || operacao.equals(WRITE) || operacao.equals(LOAD) || operacao.equals(STORE) || 
			operacao.equals(ADD) || operacao.equals(SUBTRACT) || operacao.equals(DIVIDE) || operacao.equals(MULTIPLY) || operacao.equals(BRANCH) || 
			operacao.equals(BRANCHNEG) || operacao.equals(BRANCHZERO) || operacao.equals(HALT) || operacao.equals(CONTINUE) || operacao.equals(POW) ||
			operacao.equals(REST) || operacao.equals(ENTER) || operacao.equals(ENTERSTRING) || operacao.equals(SHOWSTRING)) {
				
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
			
			
			int registroInstrucao = (int) memoria[i];
			
			operacao = converteDecimalParaHexadecimal(registroInstrucao/1000);
			indice = registroInstrucao%1000;
			
			if(operacao.equals(READ)) {
				System.out.printf("Insira um valor:");
				memoria[indice] = input.nextDouble();
			}else if(operacao.equals(WRITE)){
				System.out.println(memoria[indice]);
			}else if(operacao.equals(ENTER)) {
				System.out.println();
			}else if(operacao.equals(ENTERSTRING)){
				
				System.out.printf("Escreva uma frase: ");
				String frase = new String();
				frase = input.nextLine();
				
				while(frase.length() > 100) {
					System.out.printf("Frases com mais de 100 caracteres n�o s�o permitidos!\nEscreva a frase novamente:");
					frase = input.nextLine();
				}
				
				
				for(int k = 0; k < frase.length(); k++) {
					String caractere = frase.length()+""+(int)frase.charAt(k);
					memoria[indice+k] = Double.parseDouble(caractere);
				}
				
			}else if(operacao.equals(SHOWSTRING)) {
				
				int tamanhoString = 0, caractere = 0;
				String frase = new String();
				
				if(memoria[indice] < 10000) {
					tamanhoString = (int) memoria[indice]/100;
				}else {
					tamanhoString = (int) memoria[indice]/1000;
				}
				
				for(int k = indice; k < indice + tamanhoString; k++) {
					if(memoria[k] < 10000) {
						caractere = (int) memoria[k]%100;
					}else {
						caractere = (int) memoria[k]%1000;
					}
					frase += (char) caractere;
				}
				
				System.out.println(frase);
				
			}else if(operacao.equals(LOAD)){
				if(acumulador < -99999 || acumulador > +99999){
					System.out.println("Estouro de mem�ria!\nExecu��o do programa finalizada.");
					halt=true;
				}else {
					acumulador = memoria[indice];
				}
			}else if(operacao.equals(STORE)){
				memoria[indice] = acumulador;
			}else if(operacao.equals(ADD)) {
				acumulador += memoria[indice];
			}else if(operacao.equals(SUBTRACT)) {
				acumulador -= memoria[indice];
			}else if(operacao.equals(DIVIDE)) {
				if(acumulador == 0 || memoria[indice] == 0){
					//exibe dump de memoria
					System.out.println("Tentativa de dividir por zero!\nExecu��o do programa finalizada.");
					halt = true;
				}else {
					acumulador /= memoria[indice];
				}
			}else if(operacao.equals(MULTIPLY)) {
				acumulador *= memoria[indice];
			}else if(operacao.equals(POW)) {
				acumulador = (int)Math.pow(acumulador, memoria[indice]);
			}else if(operacao.equals(REST)) {
				acumulador %= memoria[indice];
			}else if(operacao == BRANCH) {
				i = indice - 1;
			}else if(operacao.equals(BRANCHNEG)) {
				if(acumulador < 0) {
					i = indice - 1;
				}
			}else if(operacao.equals(BRANCHZERO)) {
				if(acumulador == 0) {
					i = indice - 1;
				}
			}else if(operacao.equals(HALT)) {
				halt = true;
			}else if(operacao.equals(CONTINUE)) {
				
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
	
	public static void exibeDump(double acumulador, int posicaoMemoria, int registroInstrucao, String operacao, int indice, double[] memoria, DecimalFormat dfMemoria){
		System.out.println("Execu��o do programa finalizada!!");
		System.out.println("REGISTRADORES");
		System.out.printf("Acumulador:\t%f\n", acumulador);
		System.out.printf("Contador de instru��es:\t%d\n", posicaoMemoria);
		System.out.printf("Instru��o atual:\t%d\n", registroInstrucao);
		System.out.printf("Opera��o:\t%s\n", operacao);
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
	
	public static String converteDecimalParaHexadecimal(int decimal) {
		return Integer.toHexString(decimal);
	}
	
	public static int converterHexadecimalParaDecimal(String hexadecimal) {
		return Integer.decode("0x"+hexadecimal);
	}
	
}