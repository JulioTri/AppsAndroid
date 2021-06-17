package juliodiaz.universidadtecnologica.igenieriaelectronica.aplifi

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import cam
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.HashMap

class datos : AppCompatActivity() {
    var b1:EditText?=null
    var b2:EditText?=null
    var b3:EditText?=null
    var b4:EditText?=null
    var b5:EditText?=null
    var btn1:Button?=null

    var co:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)
        b1=findViewById(R.id.edit1)
        b2=findViewById(R.id.edit2)
        b3=findViewById(R.id.edit3)
        b4=findViewById(R.id.edit4)
        b5=findViewById(R.id.edit5)
        btn1=findViewById(R.id.el1)
    }

    override fun onResume() {
        super.onResume()
        co=1

        btn1!!.setOnClickListener{
                val nombre=b1!!.text.toString()
                val apellido=b2!!.text.toString()
                val cedula=b3!!.text.toString()
                val telefono=b4!!.text.toString()
                val direccion=b5!!.text.toString()

            consul(cedula)

            if(nombre.isNotEmpty()&& apellido.isNotEmpty() && cedula.isNotEmpty()&&telefono.isNotEmpty()&&direccion.isNotEmpty()){
                if (nombre.toIntOrNull()==null&&apellido.toIntOrNull()==null&&direccion.toIntOrNull()==null){
                        if (cedula.toIntOrNull()!=null && telefono.toIntOrNull()!=null){
                            if(co!=0){
                            agre(nombre,apellido,cedula,telefono,direccion,cam.da)
                            }
                        //agre("pliplipli","jiji","123","chcuchu","saa","asd")
                        val a= Intent(this, cam::class.java)
                        startActivity(a)}else{Toast.makeText(this,"Cedula o telefono solo llevan numeros enteros",Toast.LENGTH_SHORT).show()}
                }else{ Toast.makeText(this,"Nombre, apellido o direccion no pueden ser numeros",Toast.LENGTH_SHORT).show()}
            }else{
                Toast.makeText(this,"Los espacios no pueden estar vacios",Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun agre(a: String, b: String,c:String,d:String,e:String,f:String) {
        val respuesta = Volley.newRequestQueue(this)

        val stringRequest1 = object : StringRequest(
            Request.Method.POST, "http://${cam.ip}/todo/api/v1/?op=agregar", Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val aa = response
                if (aa == "datos ingresados correctamente") {
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("nombre", a)
                params.put("apellido",b)
                params.put("cedula",c)
                params.put("telefono",d)
                params.put("direccion",e)
                params.put("foto",f)
                return params
            }


        }
        respuesta.add(stringRequest1)
    }
    private fun consul(c:String) {
        val respuesta = Volley.newRequestQueue(this)
        val url = "http://${cam.ip}/todo/app/parcialdos/consultar.php?cedula=${c}"


        val stringRequest = StringRequest(
            Request.Method.GET, url, Response.Listener<String> { response ->
                if (response != "[]"){co=0}
                else{Toast.makeText(this, "El usuario con cedula ${c} ya existe", Toast.LENGTH_LONG).show()}
            },
            Response.ErrorListener { Toast.makeText(this, "No hay respuesta del servidor", Toast.LENGTH_LONG).show() })
        respuesta.add(stringRequest)

    }


}
