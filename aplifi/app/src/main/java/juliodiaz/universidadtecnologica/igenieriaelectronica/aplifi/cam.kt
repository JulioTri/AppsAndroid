import juliodiaz.universidadtecnologica.igenieriaelectronica.aplifi.R
import juliodiaz.universidadtecnologica.igenieriaelectronica.aplifi.consulta
import juliodiaz.universidadtecnologica.igenieriaelectronica.aplifi.datos



import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import java.util.*
import android.media.MediaScannerConnection
import android.util.Base64.*
import android.util.Log
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.*


class cam : AppCompatActivity() {
    var c: ImageView? = null
    var bot1:ImageView?=null
    var bot2:ImageView?=null
    var bot3:ImageView?=null
    var bot4:ImageView?=null
    var dire:Int?=null



    var bitmap:Bitmap?=null
    var thumbnail:Bitmap?=null


    var GALLERY = 1
    var CAMERA = 2






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cam)
        bot1=findViewById(R.id.boton1)
        bot2=findViewById(R.id.boton2)
        bot3=findViewById(R.id.boton3)
        bot4=findViewById(R.id.boton4)

        c = findViewById(R.id.imageView2)
        dire=0

    }

    override fun onResume() {
        super.onResume()

        bot1!!.setOnClickListener {
            showPictureDialog()
        }
        bot2!!.setOnClickListener {
            if(dire!=0){
            val a= Intent(this, datos::class.java)
            startActivity(a)
            }else{
                Toast.makeText(this,"Primero escoja una imagen",Toast.LENGTH_SHORT).show()
            }
        }
        bot3!!.setOnClickListener {

        }
        bot4!!.setOnClickListener {
            consul()
            val a= Intent(this, consulta::class.java)
            startActivity(a)

        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            return
            dire=0
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    da=parasubir(bitmap!!)
                    dire=1
                    c!!.setImageBitmap(bitmap)
                    Toast.makeText(this, "Imagen escogida", Toast.LENGTH_SHORT).show()



                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show()
                }

            }

        } else if (requestCode == CAMERA) {
            thumbnail = data!!.extras!!.get("data") as Bitmap
            c!!.setImageBitmap(thumbnail)
            saveImage(thumbnail!!)
            da=parasubir(thumbnail!!)
            dire=1


        }
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            Environment.getExternalStorageDirectory(), Environment.DIRECTORY_PICTURES
        )

        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }

        try {
            val f = File(
                wallpaperDirectory, Calendar.getInstance()
                    .timeInMillis.toString() + ".jpg"
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }


    private fun consul() {
        val respuesta = Volley.newRequestQueue(this)
        val url = "http://$ip/todo/app/parcialdos/consultar.php?name=1"


        val stringRequest = StringRequest(
            Request.Method.GET, url, Response.Listener<String> { response ->
                val ra = JSONObject(response)
                val array = ra.getJSONArray("datos")

                val r = array.getJSONObject(0)
                mo1 = r.getString("nombre")
                mo2=r.getString("apellido")
                mo3=r.getString("cedula")
                mo4=r.getString("telefono")
                mo5=r.getString("direccion")
                mo6=r.getString("foto")


            },
            Response.ErrorListener { Toast.makeText(this, "No hay respuesta del servidor", Toast.LENGTH_LONG).show() })
        respuesta.add(stringRequest)

    }



    fun parasubir(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        val shu=encodeToString(imageBytes, DEFAULT)
        return shu
    }



    companion object {
        var da:String=""
        //var da:ByteArray?=null
        var mo1:String=""
        var mo2:String=""
        var mo3:String=""
        var mo4:String=""
        var mo5:String=""
        var mo6:String=""
        var ip:String="192.168.0.9"
    }
}
