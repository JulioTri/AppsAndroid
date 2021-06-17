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
		$consulta = "INSERT INTO celulares (id,nombre,apellido,celular,email,proximidad,acelerometro) VALUES ('{$id}','{$nombre}','{$apellido}','{$celular}','{$email}','{$proximidad}','{$acelerometro}')";
		$resultado = mysqli_query($conexion,$consulta);
		echo "OK";
	}else{
		echo "NO";
	}
?>