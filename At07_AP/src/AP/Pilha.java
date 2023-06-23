package AP;

public class Pilha {
		private Nodo topo;
		private int tamanho = 0;
		private int limite = 100;
		
		public Pilha() {
			
		}
		
		public Pilha(int limite) {
			this.limite=limite;
		}
		public String getTopo() {
			return topo.getDado();
		}
		public boolean estaVazio() {
			return tamanho == 0;
		}
		public boolean temEspaco() {
			return limite > tamanho;
		}
		// inserir (push)
		public void empilhar(String dado) {
			if(temEspaco()) {
				Nodo novo = new Nodo(dado);
				novo.setProx(topo);
				topo = novo;
				tamanho++;
				System.out.println(dado+" empilhado.");
			}else {
				System.out.println("Pilha cheia!!!");
			}
		}
		// remover (pop)
		public void desempilhar() {
			if(!estaVazio()) {
				System.out.println(topo.getDado()+" foi retirado da pilha!");
				topo = topo.getProx();
				tamanho--;
			}else {
				System.out.println("Pilha está vazia!");
			}
		}
		

		public class Nodo {
			private String dado;
			private Nodo Prox;
			
			public Nodo(String dado) {
				this.dado = dado;
				this.Prox = null;
			}
			
			public String getDado() {
				return dado;
			}
			public void setDado(String dado) {
				this.dado = dado;
			}
			public Nodo getProx() {
				return Prox;
			}
			public void setProx(Nodo prox) {
				Prox = prox;
			}
			
		}
}
