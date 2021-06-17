<?PHP
$hostname = "localhost";
$database = "claseapp";
$username = "root";
$password = "";
$info = array();
	if (isset($_GET["id"])){
		$id=$_GET["id"];
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$consulta = "DELETE FROM celulares WHERE id LIKE '{$id}'";
		$resultado = mysqli_query($conexion,$consulta);
		if($resultado){
			echo "OK";
			mysqli_close($conexion);
		}
		else{
			echo "NO";
		}
	}
	
?>


