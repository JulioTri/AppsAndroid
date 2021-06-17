package juliodiaz.universidadtecnologica.igenieriaelectronica.clase_14022019


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class algo : AppCompatActivity() {
    var text1:TextView?=null
    var text2:TextView?=null
    var text3:TextView?=null
    var text4:TextView?=null
    var boton1:Button?=null
    var boton2:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algo)
        text1=findViewById(R.id.te1)
        text2=findViewById(R.id.te2)
        text3=findViewById(R.id.te3)
        text4=findViewById(R.id.te4)
        boton1=findViewById(R.id.bo1)
        boton2=findViewById(R.id.bo2)

    }

    override fun onResume() {
        super.onResume()
        boton1!!.setOnClickListener {

            latitud1=text1!!.text.toString()
            longitud1=text2!!.text.toString()
            latitud2=text3!!.text.toString()
            contador=2
            lanzar()
        }
        boton2!!.setOnClickListener {
            latitud1=text1!!.text.toString()
            longitud1=text2!!.text.toString()
            latitud2=text3!!.text.toString()
            longitud2=text4!!.text.toString()
            contador=1
            lanzar()
        }
    }

    companion object {
        var latitud1:String=""
        var longitud1:String=""
        var latitud2:String=""
        var longitud2:String=""
        var contador:Int=0
    }
    fun lanzar(){
        val x=Intent(this,MapsActivity::class.java)
        startActivity(x)
    }
}
