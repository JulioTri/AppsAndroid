<?PHP
$hostname = "localhost";
$database = "claseapp";
$username = "root";
$password = "";
$info = array();
	if (isset($_GET["id"])&&isset($_GET["nombre"])&&isset($_GET["apellido"])&&isset($_GET["celular"])&isset($_GET["email"])&&isset($_GET["proximidad"])&&isset($_GET["acelerometro"])){
		$id=$_GET["id"];
		$nombre=$_GET["nombre"];
		$apellido=$_GET["apellido"];
		$celular=$_GET["celular"];
		$email=$_GET["email"];
		$proximidad=$_GET["proximidad"];
		$acelerometro=$_GET["acelerometro"];
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$consulta = "UPDATE celulares SET nombre='{$nombre}', apellido='{$apellido}',celular='{$celular}',email='{$email}',proximidad='{$proximidad}',acelerometro='{$acelerometro}' WHERE id='{$id}'";
		$resultado = mysqli_query($conexion,$consulta);
		echo "OK";
	}
	else{
		echo "NO";
	}
	
?>