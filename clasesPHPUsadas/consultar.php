<?PHP
$hostname = "localhost";
$database = "unnombre";
$username = "root";
$password = "";
$info = array();
	if (isset($_GET["cedula"])){
		$cedula=$_GET["cedula"];
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$consulta = "SELECT * FROM informacion WHERE cedula LIKE '{$cedula}'";
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


