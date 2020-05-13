package Dominio;

public enum CategoriaDeEntidad {
	OSC{
		@Override
		public boolean esEmpresa(){
			return false;
		}
	},
	Micro{
		@Override
		public boolean esEmpresa(){
			return true;
		}
	}, 
	Pequenia{
		@Override
		public boolean esEmpresa(){
			return true;
		}
	}, 
	MedianaTramo1{
		@Override
		public boolean esEmpresa(){
			return true;
		}
	}, 
	MedianaTramo2{
		@Override
		public boolean esEmpresa(){
			return true;
		}
	};
	public abstract boolean esEmpresa();
}
