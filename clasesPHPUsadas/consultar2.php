<?PHP
$hostname = "localhost";
$database = "claseapp";
$username = "root";
$password = "";
$info = array();
	if (isset($_GET["id"])){
		$id=$_GET["id"];
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$consulta = "SELECT * FROM celulares WHERE id LIKE '{$id}'";
		$resultado = mysqli_query($conexion,$consulta);
		if($resultado){
			if($reg=mysqli_fetch_array($resultado)){
				$info['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($info);
		}
	}
	
?>


