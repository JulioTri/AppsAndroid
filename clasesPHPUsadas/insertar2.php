<?PHP
$hostname = "localhost";
$database = "pruebassensores";
$username = "root";
$password = "";
$info = array();
	if (isset($_GET["celular"])&&isset($_GET["name"])&&isset($_GET["vendor"])&isset($_GET["version"])&&isset($_GET["type"])&&isset($_GET["resolution"])&&isset($_GET["power"])&&isset($_GET["maxrange"])&&isset($_GET["mindelay"])){
		$celular=$_GET["celular"];		
		$name=$_GET["name"];
		$vendor=$_GET["vendor"];
		$version=$_GET["version"];
		$type=$_GET["type"];
		$resolution=$_GET["resolution"];
		$power=$_GET["power"];
		$maxrange=$_GET["maxrange"];
		$mindelay=$_GET["mindelay"];
		$conexion = mysqli_connect($hostname,$username,$password,$database);
		$consulta = "INSERT INTO celucos (celular,name,vendor,version,type,resolution,power,maxrange,mindelay) VALUES ('{$celular}','{$name}','{$vendor}','{$version}','{$type}','{$resolution}','{$power}','{$maxrange}','{$mindelay}')";
		$resultado = mysqli_query($conexion,$consulta);
		echo "OK";
	}else{
		echo "NO";
	}
?>