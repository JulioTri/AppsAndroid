<?PHP
$hostname = "localhost";
$database = "prueba";//base de datos
$username = "root";
$password = "";
$info = array();
	if (isset($_GET["nombre"]) ){
        $nombre=$_GET["nombre"];
        
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$consulta = "SELECT * FROM gente WHERE nombre LIKE '{$nombre}' ";//tabla e items
		$resultado = mysqli_query($conexion,$consulta); 
		if($resultado){
            echo "SI";
			mysqli_close($conexion);
			
        }else{echo"NO";}
            
    }
	
?>