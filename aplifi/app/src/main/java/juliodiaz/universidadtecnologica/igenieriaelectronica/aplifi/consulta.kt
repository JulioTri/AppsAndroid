package juliodiaz.universidadtecnologica.igenieriaelectronica.aplifi

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView

class consulta : AppCompatActivity() {
    var j1:TextView?=null
    var j2:TextView?=null
    var j3:TextView?=null
    var j4:TextView?=null
    var j5:TextView?=null
    var j6:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)
        j1=findViewById(R.id.te1)
        j2=findViewById(R.id.te2)
        j3=findViewById(R.id.te3)
        j4=findViewById(R.id.te4)
        j5=findViewById(R.id.te5)
        j6=findViewById(R.id.te6)
        j1!!.text=cam.mo1
        j2!!.text=cam.mo2
        j3!!.text=cam.mo3
        j4!!.text=cam.mo4
        j5!!.text=cam.mo5
        paramostrar(cam.mo6)
    }

    override fun onResume() {
        super.onResume()
        j1!!.text=cam.mo1
        j2!!.text=cam.mo2
        j3!!.text=cam.mo3
        j4!!.text=cam.mo4
        j5!!.text=cam.mo5
        paramostrar(cam.mo6)

    }

    fun paramostrar(base64Str: String){

        val imageByteArray = Base64.decode(base64Str, Base64.DEFAULT)
        val bitmap=BitmapFactory.decodeByteArray(imageByteArray,0,imageByteArray.size)
        j6!!.setImageBitmap(bitmap)

    }
}
