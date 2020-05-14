package Dominio;

public class EntidadBase implements EntidadOrganizacional {
	
	private String nombreFicticio;
	private String descripcion;
	private EntidadJuridica entidadJuridica;
	
	public EntidadBase( String _nombreFicticio,String _descripcion)
	{
		this(_nombreFicticio,_descripcion, EntidadJuridica.EMPTY);
	}
	
	public EntidadBase( String _nombreFicticio,String _descripcion, EntidadJuridica _entidadJuridica)
	{
		nombreFicticio =_nombreFicticio;
		descripcion = _descripcion;
		entidadJuridica = _entidadJuridica;
	}
	
	public String getNombreFicticio()
	{
		return nombreFicticio;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public EntidadJuridica getentidadJuridica()
	{
		return entidadJuridica;
	}
}
