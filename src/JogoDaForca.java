	import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
	
public class JogoDaForca {
	
		private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
		private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
		private String dica=""; //dica da palavra sorteada
		private String[] letras; // letras da palavra sorteada
		private int acertos; // contador de acertos
		private int penalidade; // penalidade atual
		private String palavraSorteada=""; //palavra sorteada
		
		
		public JogoDaForca(String palavras) throws Exception{
			try {
			BufferedReader arquivo = new BufferedReader(new FileReader("palavras.csv"));
			String palavra, dica;
	        String[] partes;
			while(arquivo.ready()) {
				partes = arquivo.readLine().split(";");
	            palavra = partes[0];
	            dica = partes[1];
	            this.palavras.add(palavra);
	            this.dicas.add(dica);
			}
			arquivo.close();
			}
			catch(Exception e) {
				throw new Exception("Arquivo não encontrado");
			}
	     }
//		lê as palavras + dicas do arquivo e as coloca nas respectivas listas. Lança (throw) a exceção “arquivo inexistente”,
//		caso o arquivo não exista.

		public void iniciar() {
			 Random random = new Random();

			 int indexsort;
			 indexsort = random.nextInt(palavras.size());
			 
			 palavraSorteada = palavras.get(indexsort);
			 dica = dicas.get(indexsort);
			
		//realiza o sorteio de uma das palavras existentes na lista de palavras.
		}
		
		public String getDica() {
			return dica;
		//retorna a dica associada à palavra sorteada no momento.
		}
		
		public int getTamanho() {
			return palavraSorteada.length();
		//retorna o tamanho da palavra sorteada no momento
		}
		
		public ArrayList<Integer> getPosicoes(String letra) throws Exception {
			try {
			letras = palavraSorteada.split("");	
			ArrayList<Integer> indices = new ArrayList<Integer>();
			for (int i = 0; i < palavraSorteada.length(); i++) {

				String sLetra = palavraSorteada.toUpperCase().substring(i, i + 1);//Pegando o indice da palavra e substituindo pela letra digitada.

				if (sLetra.toUpperCase().equals(letra.trim())) {//Só adiciona o valor se for igual.
					indices.add(i);
					acertos++;
				}
			}
			if (indices.size() == 0) {
				penalidade++;
				
			}
			return indices;
			}
			catch(Exception e) {
				throw new Exception("Você já digitou essa letra");
			}
//		retorna uma lista com as posições encontradas da letra na palavra sorteada ou retorna uma lista vazia. Substitui as
//		letras encontradas na palavra por “*”. Contabiliza um acerto, para cada ocorrência encontrada, ou incrementa a
//		penalidade, no caso da inexistência da letra. O parâmetro letra é válido se tem apenas 1 caractere alfabético sem
//		acento – pode ser maiúscula ou minúscula.
		}
		
		public boolean terminou() {
			if(acertos==letras.length)
				return true;
			else
				if(penalidade==6)
					return true;
				else 
					return false;

		//retorna true, se o total de acertos é igual ao tamanho da palavra sorteada ou se a penalidade é 6.
		}
		
		public int getAcertos() {
			return acertos;
		//retorna o total de acertos
		}
		
		public int getPenalidade() {
			return penalidade;
		//retorna a penalidade atual (0, 1, 2, ... 6)
		}
		
		public String getResultado() throws Exception {
			if (! terminou())
				throw new Exception("não tem resultado - jogo em andamento");
			if (acertos==letras.length)
				return "você acertou a palavra," + " " + palavraSorteada;
			else 
				return "você foi enforcado";
				
		//retorna “jogo em andamento”, “você venceu” ou “você foi enforcado”.
		}

}
